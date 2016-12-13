package com.xinxinxuedai.MVP.RegisterActivity;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xinxinxuedai.MVP.RegisterActivity.countTime.Register_countTime_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsHashtable;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.app.Share;
import com.xinxinxuedai.bean.RegistSms;
import com.xinxinxuedai.bean.UserRegist;
import com.xinxinxuedai.request.NetWorkCallBack;
import com.xinxinxuedai.request.registSms_Request;
import com.xinxinxuedai.request.userRegist_Request;
import com.xinxinxuedai.ui.RegisterActivity;

import java.net.HttpURLConnection;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by 35876 于萌萌
 * 创建日期: 11:12 . 2016年12月01日
 * 描述:注册界面的逻辑
 * <p>
 * <p>as
 * 备注:
 */

public class RegisterActivity_P implements RegisterActivity_M {
    static RegisterActivity_P mRegisterActivity_p;
    Context context;
    private Register_countTime_P mRegister_countTime_p;

    public RegisterActivity_P(Context context){
        this.context = context;
        initcountTime_P();
    }
    public static RegisterActivity_P getLoginActivity_P(Context context){
        if (null== mRegisterActivity_p){
            return mRegisterActivity_p = new RegisterActivity_P(context);
        }else{

            return mRegisterActivity_p;
        }

    }


    private void initcountTime_P() {
        //mRegister_countTime_p = new Register_countTime_P(context);
        mRegister_countTime_p = Register_countTime_P.getLogin_countTime_P(context);

    }


    @Override
    public void onClick(View view) {
        if ((TextView) view.getTag(R.id.phone_num) instanceof TextView){

            TextView tag = (TextView) view.getTag(R.id.phone_num);
            //发送验证码的请求  registSms_Request  请求成功了 之后 才开始倒计时
            sendSMS(tag);
        }else{
            switch ((int)view.getTag()){
                //按下去的是倒计时
                case 1:
                    break;
                //按下去确定注册
                case 2:
                    LogUtils.i("点击了注册");
                    break;
                //按下去的是确认重置
                case 3:
                    LogUtils.i("点击了重置");
                    break;
            }
        }



    }

    private void sendSMS(TextView tag) {
        Hashtable<String, String> hashtable = UtilsHashtable.getHashtable();
        hashtable.put("loan_mobile",tag.getText().toString().trim());
        HttpURLConnection request = registSms_Request.request(context, hashtable, new NetWorkCallBack<RegistSms>() {
            @Override
            public void onSucceed(RegistSms registSms) {
                String code = registSms.data.code;
                String message = registSms.message;
                UtilsToast.showToast(context, message);
                LogUtils.i("点击了注册/重置验证码倒计时");
                mRegister_countTime_p.startCountDown();
            }

            @Override
            public void onError(String jsonObject) {
                UtilsToast.showToast(context, jsonObject);
            }
        });



    }

    @Override
    public void startService() {

    }

    /**
     * 负责获取 所有的填写数据的方法
     *
     * @param editTextViews
     * @param classtag
     */
    @Override
    public void setEditTextViews(List<EditText> editTextViews, int classtag) {
        EditText editText1 = new EditText(context);
        EditText editText2  = new EditText(context);
        EditText editText3 = new EditText(context);
        EditText editText4  = new EditText(context);
        if (null==editTextViews||editTextViews.size()==0){
            return;
        }
        for (int i = 0; i <editTextViews.size() ; i++) {
            EditText editText = editTextViews.get(i);
            String s = editText.getText().toString();
            //1判断他俩是否为空
            if (TextUtils.isEmpty(s)){
                switch ((int) editText.getTag()){
                    case 1:
                        UtilsToast.showToast(context, "手机号为空");
                        return;
                    case 2:
                        UtilsToast.showToast(context, "验证码为空");
                        return;
                    case 3:
                        UtilsToast.showToast(context, "密码为空");
                        return;
                    case 4:
                        UtilsToast.showToast(context, "确认密码为空");
                        return;
                }
            }
            if ((int) editText.getTag()==1){
                editText1 =editTextViews.get(i);
            }

            if ((int) editText.getTag()==2){
                editText2 =editTextViews.get(i);
            }
            if ((int) editText.getTag()==3){
                editText3 =editTextViews.get(i);
            }

            if ((int) editText.getTag()==4){
                editText4 =editTextViews.get(i);
            }

        }
        String s3 = editText3.getText().toString();
        String s4 = editText4.getText().toString();
        //2在判断是否够长
        //手机号码是否够11位
        if (editText1.length()==11){
            //密码是否大于最小位数8
            if (editText3.length()>=8&&s3.equals(s4)){
                // userRegist_Request
                //发送注册的请求
                if (classtag ==RegisterActivity.REGISTERCLASS){

                    UtilsToast.showToast(context, "注册中~");
                    //去注册
                    call_Userlogin(editText1, editText3);

                }else if (classtag ==RegisterActivity.AGAINCLASS){

                    UtilsToast.showToast(context, "重置中~");
                }

            }else if (!s3.equals(s4)){
                UtilsToast.showToast(context, "两次密码不一致");
                return;
            }else if (editText3.length()<8){
                UtilsToast.showToast(context, "密码长度小于8位");
                return;
            }

        }else{
            UtilsToast.showToast(context, "手机号码长度不正确");
            return;
        }


    }

    /**
     * 登陆的方法
     * @param editText1 手机号码
     * @param editText3 密码
     */
    private void call_Userlogin(EditText editText1, EditText editText3) {
        Hashtable<String, String> hashtable = UtilsHashtable.getHashtable();
        //手机号码
        hashtable.put("loan_mobile",editText1.getText().toString().trim());
        //手机密码
        hashtable.put("loan_pwd",editText3.getText().toString().trim());
        //注册来源
        hashtable.put("loan_from","android");
        //推荐码
        hashtable.put("tuijianma","");
        userRegist_Request.request(context, hashtable, new NetWorkCallBack<UserRegist>() {
            @Override
            public void onSucceed(UserRegist userRegist) {
                String user_id = userRegist.data.user_id;
                Share.saveToken(context, user_id);
                UtilsToast.showToast(context, userRegist.message);
            }

            @Override
            public void onError(String jsonObject) {
                UtilsToast.showToast(context, jsonObject);
            }
        });
    }
}
