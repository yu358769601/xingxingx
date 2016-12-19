package com.xinxinxuedai.MVP.PersonalDetailsActivity;

import android.content.Context;
import android.widget.EditText;

import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsHashtable;
import com.xinxinxuedai.Utils.UtilsMyText;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.app.Share;
import com.xinxinxuedai.bean.GetInfo;
import com.xinxinxuedai.bean.SetInfo1;
import com.xinxinxuedai.request.GetInfo_Request;
import com.xinxinxuedai.request.NetWorkCallBack;
import com.xinxinxuedai.request.SetInfo1_Request;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by 35876 于萌萌
 * 创建日期: 12:38 . 2016年12月05日
 * 描述:个人信息_P
 * <p>
 * <p>
 * 备注:
 */

public class PersonalDetailsActivity_P extends BaseMvp<PersonalDetailsActivity_C> implements PersonalDetailsActivity_M{


    static  PersonalDetailsActivity_P mPersonalDetailsActivity_p;
    Context context;
    public PersonalDetailsActivity_P(Context context) {
        this.context = context;
    }

    public static PersonalDetailsActivity_P getmPersonalDetailsActivity_p(Context context){
        if(null==mPersonalDetailsActivity_p){
            return mPersonalDetailsActivity_p = new PersonalDetailsActivity_P(context);
        }else{
            return mPersonalDetailsActivity_p;
        }
    }
    PersonalDetailsActivity_C personalDetailsActivity_c;

    @Override
    public void setCallBack(PersonalDetailsActivity_C personalDetailsActivity_c) {
        this.personalDetailsActivity_c = personalDetailsActivity_c;
    }


    /**
     * 获取 这个页面上所有的 editText 还有一个 radiobutton 选择的号码
     *
     * @param editTexts
     * @param select
     */
    @Override
    public void getEdtexts(List<EditText> editTexts, int select) {
        if (editTexts.size()==0||null == editTexts){
            return;
        }
        EditText editText1 = new EditText(context);
        EditText editText2 = new EditText(context);
        EditText editText3 = new EditText(context);
        EditText editText4 = new EditText(context);
        EditText editText5 = new EditText(context);
        EditText editText6 = new EditText(context);
        for (int i = 0; i < editTexts.size(); i++) {
            EditText editText = editTexts.get(i);
            //String string = editText.getText().toString();
            int tag = (int) editText.getTag();



            //判断是否为空
            if (UtilsMyText.isEmptys(editText)){
                switch (tag) {
                    case 1:
                        UtilsToast.showToast(context, "姓名为空");
                        return;
                    case 2:
                        UtilsToast.showToast(context, "身份证为空");
                        return;
                    case 3:
                        UtilsToast.showToast(context, "微信号为空");
                        return;
                    case 4:
                        UtilsToast.showToast(context, "家庭地址为空");
                        return;
                    case 5:
                        UtilsToast.showToast(context, "紧急联系人姓名为空");
                        return;
                    case 6:
                        UtilsToast.showToast(context, "紧急联系人电话为空");
                        return;
                }
            }


        }
        for (int i = 0; i < editTexts.size(); i++) {
            EditText editText = editTexts.get(i);
            //String string = editText.getText().toString();
            int tag = (int) editText.getTag();

            //判断长度是否够用
            switch (tag) {
                case 1:
                    break;
                case 2:
                    if (UtilsMyText.getLengh(editText)<=11){
                        UtilsToast.showToast(context, "身份证长度不正确");
                        return;
                    }

                case 3:
                    break;
                case 4:
                    if (UtilsMyText.getLengh(editText)<=8){
                        UtilsToast.showToast(context, "家庭住址长度不正确");
                        return;
                    }
                case 5:
                    break;
                case 6:
                    if (UtilsMyText.getLengh(editText)<11){
                        UtilsToast.showToast(context, "紧急联系人电话长度小于11位");
                        return;
                    }
            }
        }


        if (select!=-1){
            //调用网络交互方法
            call_setInfo1_Request(editTexts, select);
        }

    }

    /**
     * 访问网路 获取 回显信息
     */
    @Override
    public void setCallBackData() {
        GetInfo_Request.request(context, new NetWorkCallBack<GetInfo>() {
            @Override
            public void onSucceed(GetInfo getInfo,int dataMode) {
                personalDetailsActivity_c.setCallBackData(getInfo);
            }

            @Override
            public void onError(String jsonObject) {

            }
        });
    }

    private void call_setInfo1_Request(List<EditText> editTexts, int select) {
        UtilsToast.showToast(context, "网络请求中~");

        Hashtable<String, String> hashtable =
                UtilsHashtable.getHashtable();
        //身份认证
        hashtable.put("loan_id", Share.getToken(context));
        String sex = select==0?"男":"女";
        //性别
        hashtable.put("loan_sex",sex);
        //名字
        hashtable.put("loan_realname",editTexts.get(0).getText().toString().trim());
        //身份证
        hashtable.put("loan_card_id",editTexts.get(1).getText().toString().trim());
        //微信号码
        hashtable.put("loan_weixin",editTexts.get(2).getText().toString().trim());
        //地址
        hashtable.put("loan_address",editTexts.get(3).getText().toString().trim());
        //父母名字
        hashtable.put("loan_parent",editTexts.get(4).getText().toString().trim());
        //父母电话
        hashtable.put("loan_parent_mobile",editTexts.get(5).getText().toString().trim());


        SetInfo1_Request.request(context, hashtable, new NetWorkCallBack<SetInfo1>() {
            @Override
            public void onSucceed(SetInfo1 info1,int dataMode) {
                UtilsToast.showToast(context, info1.message);
                //关掉界面
                personalDetailsActivity_c.closeActivity();
            }

            @Override
            public void onError(String jsonObject) {
                UtilsToast.showToast(context, jsonObject);
            }

        });


    }
}
