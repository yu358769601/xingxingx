package com.xinxinxuedai.MVP.TopUpActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.EditText;

import com.fuiou.pay.FyPay;
import com.fuiou.pay.FyPayCallBack;
import com.fuiou.pay.util.AppConfig;
import com.fuiou.pay.util.MD5UtilString;
import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsMyText;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.app.Share;
import com.xinxinxuedai.ui.TopUpActivity;

import java.util.List;

/**
 * Created by 35876 于萌萌
 * 创建日期: 17:24 . 2016年12月07日
 * 描述:账户充值_P
 * <p>
 * <p>
 * 备注:
 */

public class TopUpActivity_P extends BaseMvp<TopUpActivity_C> implements  TopUpActivity_M{
    static TopUpActivity_P sTopUpActivity_p;

    Context context;
    TopUpActivity activity;
    public TopUpActivity_P(Context context) {
        this.context = context;
    }

    public static TopUpActivity_P getTopUpActivity_p(Context context){
        if (null ==sTopUpActivity_p){
            return sTopUpActivity_p = new TopUpActivity_P(context);
        }
        return sTopUpActivity_p;
    }

    TopUpActivity_C topUpActivity_c;
    @Override
    public void setCallBack(TopUpActivity_C topUpActivity_c) {
       this.topUpActivity_c = topUpActivity_c;
    }


    /**
     * 本页面所有的 输入框
     *
     * @param editTextViews
     */
    @Override
    public void setEditTextViews(List<EditText> editTextViews) {
        if (null==editTextViews||editTextViews.size()==0){
            return;
        }

        for (int i = 0; i <editTextViews.size() ; i++) {
            EditText editText = editTextViews.get(i);
            int tag = (int) editText.getTag();
            //判断是否为空
            if (UtilsMyText.isEmptys(editText)){
                switch (tag){
                    case 1:
                        UtilsToast.showToast(context, "金额为空");
                    return;
                    case 2:
                        UtilsToast.showToast(context, "卡号为空");
                    return;
                    case 3:
                        UtilsToast.showToast(context, "姓名为空");
                    return;
                    case 4:
                        UtilsToast.showToast(context, "身份证为空");
                    return;
                }
            }


        }
        //不为空就 访问网络
        UtilsToast.showToast(context, "网络请求中~");
        //把网络获取到的参数返回给 activity
//        Hashtable<String, String> hashtable =
//                UtilsHashtable.getHashtable();
//        hashtable.put("money","");
//        hashtable.put("bank_card","");
//        hashtable.put("real_name","");
//        hashtable.put("id_card","");
//        hashtable.put("mobile","");
//        FuYouChongZhi_Request.request(context, hashtable, new NetWorkCallBack() {
//
//            @Override
//            public void onSucceed(Object o) {
//
//            }
//
//            @Override
//            public void onError(String jsonObject) {
//
//            }
//        });
        pay(editTextViews,true);


    }

    /**
     * 需要有activity
     *
     * @param activity
     */
    @Override
    public void serActivity(TopUpActivity activity) {
        this.activity = activity;
    }

    /**
     * 开心的去支付
     * @param editTextViews
     * @param b
     */
    private void pay(final List<EditText> editTextViews, boolean b) {
        //设定环境 trun 正式 false 测试
        FyPay.setDev(b);
        FyPay.init(activity);
        EditText AmtE = editTextViews.get(0);
        final EditText BankCardE = editTextViews.get(1);
        final EditText NameE = editTextViews.get(2);
        final EditText IdNoE = editTextViews.get(3);
        //商户号
        //String MchNtCd = "0002900F0096235";
        String MchNtCd = "0002610F0315943";
        //订单号 后台获得
        String MchntOrdId = "186036630280";

        String IdType = "0";
        //用户ID
        String UserId = "321341564654";
        String Amt = UtilsMyText.getTextView(AmtE);
        String BankCard = UtilsMyText.getTextView(BankCardE);
        String Name = UtilsMyText.getTextView(NameE);
        String IdNo = UtilsMyText.getTextView(IdNoE);
        //String mMchnt_Key = "5old71wihg2tqjug9kkpxnhx9hiujoqj";//测试
        String mMchnt_Key = "2yasvrx1prkh3g71ywxuryrgk9mfhp8r";//正式


        String Sing = MD5UtilString.MD5Encode("02" + "|" + "2.0" + "|"
                + MchNtCd + "|"
                +MchntOrdId + "|"
                + UserId+ "|"
                + Amt + "|"
                + BankCard + "|" + "http://www-1.fuiou.com:18670/mobile_pay/update/receive.pay" + "|"
                + Name + "|"
                + IdNo + "|"
                + IdType + "|" +mMchnt_Key);
        Bundle bundle =new Bundle();
        //商户号
        bundle.putString(AppConfig.MCHNT_CD, MchNtCd);
        //金额
        bundle.putString(AppConfig.MCHNT_AMT, Amt);
        //支付结果回调地址	支付结果回调地址
        bundle.putString(AppConfig.MCHNT_BACK_URL, "http://www-1.fuiou.com:18670/mobile_pay/update/receive.pay");
        //银行卡号
        bundle.putString(AppConfig.MCHNT_BANK_NUMBER, BankCard);
        //订单号
        bundle.putString(AppConfig.MCHNT_ORDER_ID, MchntOrdId);
        //证件类型  0.身份证 1.护照 2.军官证 3.士兵证 4.回乡证 6.户口本 7.其它（目前只支持身份证)
        bundle.putString(AppConfig.MCHNT_USER_IDCARD_TYPE,IdType);
        //用户ID
        bundle.putString(AppConfig.MCHNT_USER_ID,UserId);
        //证件号 18位
        bundle.putString(AppConfig.MCHNT_USER_IDNU,IdNo);
        //姓名不能有空格 数字
        bundle.putString(AppConfig.MCHNT_USER_NAME,Name);
        //加密字符串
        bundle.putString(AppConfig.MCHNT_SING_KEY,Sing);
        //签名类型
        bundle.putString(AppConfig.MCHNT_SDK_SIGNTP,"MD5");
        //交易类型
        bundle.putString(AppConfig.MCHNT_SDK_TYPE,"02");
        //版本
        bundle.putString(AppConfig.MCHNT_SDK_VERSION,"2.0");

        int i= FyPay.pay(activity, bundle, new FyPayCallBack() {

            @Override
            public void onPayComplete(String rspCode, String rspDesc, Bundle extraData) {
                LogUtils.i("富友支付完成"+rspCode+"\t"+rspDesc+"\t"+extraData.toString());
//                EditText BankCardE = editTextViews.get(1);
//                EditText NameE = editTextViews.get(2);
//                EditText IdNoE = editTextViews.get(3);
                Share.save(context, "BankCardE",UtilsMyText.getTextView(BankCardE));
                Share.save(context, "NameE",UtilsMyText.getTextView(NameE));
                Share.save(context, "IdNoE",UtilsMyText.getTextView(IdNoE));

                topUpActivity_c.getData(rspDesc);
                topUpActivity_c.closeActivity();
            }

            @Override
            public void onPayBackMessage(String extraData) {
                LogUtils.i("富友支付返回信息"+extraData.toString());
            }
        });
    }
}
