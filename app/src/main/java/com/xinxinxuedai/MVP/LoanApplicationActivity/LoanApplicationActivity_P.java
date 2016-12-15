package com.xinxinxuedai.MVP.LoanApplicationActivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsHashtable;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.app.Share;
import com.xinxinxuedai.bean.GetLoanDetail;
import com.xinxinxuedai.bean.SetLoanInfo;
import com.xinxinxuedai.request.NetWorkCallBack;
import com.xinxinxuedai.request.getLoanDetail_Request;
import com.xinxinxuedai.request.setLoanInfo_Request;
import com.xinxinxuedai.ui.ApplyForActivity;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by 35876 于萌萌
 * 创建日期: 16:38 . 2016年12月02日
 * 描述:零用金借款申请P
 * <p>
 * <p>
 * 备注:
 */

public class LoanApplicationActivity_P  extends BaseMvp<LoanApplicationActivity_CallBack> implements  LoanApplicationActivity_method{
    LoanApplicationActivity_CallBack loanApplicationActivity_callBack;
    static LoanApplicationActivity_P sLoanApplicationActivity_p;
    Context context;
    //点了什么进来的
    int classTag;

    public LoanApplicationActivity_P(Context context) {
        this.context = context;
    }

    @Override
    public void setCallBack(LoanApplicationActivity_CallBack loanApplicationActivity_callBack) {
        this.loanApplicationActivity_callBack = loanApplicationActivity_callBack;
    }

    public static LoanApplicationActivity_P getLoanApplicationActivity_P(Context context){
       if (null == sLoanApplicationActivity_p){
           return sLoanApplicationActivity_p = new LoanApplicationActivity_P(context);
       }else{
           return sLoanApplicationActivity_p;
       }
    }


    @Override
    public void clicks(View view) {
       // Intent intent = new Intent();
        switch ((int)view.getTag()){
            case 99:
//                intent.setClass(context, ApplyForActivity.class);
//                context.startActivity(intent);
            break;
            case 1:
                //用途
                ArrayList<String> strings1 = new ArrayList<>();
                    strings1.add("培训");
                    strings1.add("图书");
                    strings1.add("教材");
                    strings1.add("考级");
                    strings1.add("技能");
                    strings1.add("发明");
                loanApplicationActivity_callBack.showDialog1(strings1,"借款用途");
            break;
            case 2:
                //额度
                ArrayList<String> strings2 = new ArrayList<>();
                    strings2.add("500"+"元");
                    strings2.add("800"+"元");
                    strings2.add("1000"+"元");
                    strings2.add("1500"+"元");
                    strings2.add("2000"+"元");
                    strings2.add("2500"+"元");
                    strings2.add("3000"+"元");
                    strings2.add("4000"+"元");
                    strings2.add("5000"+"元");
                loanApplicationActivity_callBack.showDialog2(strings2,"借款额度");
                break;
            case 3:
                //期限
                ArrayList<String> strings3 = new ArrayList<>();
                    strings3.add("28"+"天");
                    strings3.add("45"+"天");
                    strings3.add("60"+"天");
                loanApplicationActivity_callBack.showDialog3(strings3,"借款期限");
            break;
            case 4:
//                Hashtable<String,String> hashtable= new Hashtable<>();
//                hashtable.put("1","1");
//                //发送网络请求
//                requestOperation(context, hashtable);
            break;
        }
    }


    /**
     * 传送结果集 上传服务器 发送请求
     *
     * @param textViews
     */
    @Override
    public void request_SetLoanInfo_Request(List<TextView> textViews , EditText editText) {
            if (textViews==null||textViews.size()==0){
                return;
            }
            if (editText.length()==0){
                UtilsToast.showToast(context, "描述为空");
                return;
        }
        for (int i = 0; i < textViews.size(); i++) {
            TextView textView = textViews.get(i);
            int tag = (int) textView.getTag();

            if (textView.length()==0){
                switch (tag){
                    case 1:
                        UtilsToast.showToast(context, "借款用途未选择");
                    return;
                    case 2:
                        UtilsToast.showToast(context, "借款额度未选择");
                    return;
                    case 3:
                        UtilsToast.showToast(context, "借款期限未选择");
                    return;
                }
            }
        }
        //去网络交互提交
        call_setLoanInfo_Request(textViews, editText);

    }

    private void call_setLoanInfo_Request(List<TextView> textViews, EditText editText) {
        UtilsToast.showToast(context, "提交中~");

        final Hashtable<String, String> hashtable = UtilsHashtable.getHashtable();
        //入口
        hashtable.put("action", "SetLoanInfo");
        //id
        hashtable.put("loan_userid", Share.getToken(context));
        //借款描述
        hashtable.put("loan_describe",editText.getText().toString());
        //借款简略描述
        hashtable.put("loan_small_describe",textViews.get(0).getText().toString());
        //多少钱
        hashtable.put("money",textViews.get(1).getText().toString());
        //期限
        hashtable.put("loan_term",textViews.get(2).getText().toString());
        //借款类型 1 有很多类型 (以后扩展功能)
        hashtable.put("loan_category","1");
        //判断怎么进来的 (二选一) 0 先息后本  1 等额本息
        hashtable.put("loan_plan",classTag+"");
        setLoanInfo_Request.request(context, hashtable, new NetWorkCallBack<SetLoanInfo>() {
            @Override
            public void onSucceed(SetLoanInfo info, int dataMode) {
                String message = info.message;
                UtilsToast.showToast(context, message);
                loanApplicationActivity_callBack.getTextInfo4(hashtable.toString());
                loanApplicationActivity_callBack.closeActivity();
                if (dataMode ==NetWorkCallBack.NETDATA) {
                    context.startActivity(new Intent(context,ApplyForActivity.class));
                }
                //网络请求后
            }


            @Override
            public void onError(String jsonObject) {

            }
        });
    }

    /**
     * 我是点了什么而 进来这个界面的
     *
     * @param classTag
     */
    @Override
    public void setClassTag(int classTag) {
        this.classTag = classTag;
    }

    /**
     * 开始回显
     */
    @Override
    public void getCallBackData() {
        getLoanDetail_Request.request(context, new NetWorkCallBack<GetLoanDetail>() {
            @Override
            public void onSucceed(GetLoanDetail detail,int dataMode) {
                //技能
                loanApplicationActivity_callBack.setCallBackData(detail);
            }

            @Override
            public void onError(String jsonObject) {

            }
        });
    }
}
