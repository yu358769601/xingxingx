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
import com.xinxinxuedai.bean.FuYouChongZhi;
import com.xinxinxuedai.request.FuYouChongZhi_Request;
import com.xinxinxuedai.request.NetWorkCallBack;
import com.xinxinxuedai.ui.TopUpActivity;

import java.util.Hashtable;
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

    Context context;
    TopUpActivity activity;
    private String mMchntOrdId;

    public TopUpActivity_P(Context context) {
        this.context = context;
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

        double v = Double.parseDouble(editTextViews.get(0).getText().toString());
        int i = (int) v;
        if (i<10){
            UtilsToast.showToast(context, "充值金额小于10元");

            return;
        }
        //先获取订单
        getoOrder_number(editTextViews,true);



    }

    private void getoOrder_number(final List<EditText> editTextViews, final boolean b) {
        EditText AmtE = editTextViews.get(0);
         EditText BankCardE = editTextViews.get(1);
         EditText NameE = editTextViews.get(2);
         EditText IdNoE = editTextViews.get(3);
        String Amt = UtilsMyText.getTextView(AmtE);
        //int Amt = (int)(Double.parseDouble(UtilsMyText.getTextView(AmtE)));
        String BankCard = UtilsMyText.getTextView(BankCardE);
        String Name = UtilsMyText.getTextView(NameE);
        String IdNo = UtilsMyText.getTextView(IdNoE);

        Hashtable<String, String> hashtable= new Hashtable<>();
        hashtable.put("money",Amt+"");
        hashtable.put("bank_card",BankCard+"");
        hashtable.put("real_name",Name+"");
        hashtable.put("id_card",IdNo+"");
        FuYouChongZhi_Request.request(context, hashtable, new NetWorkCallBack<FuYouChongZhi>() {
            @Override
            public void onSucceed(FuYouChongZhi fuYouChongZhi, int dataMode) {
                if (1==fuYouChongZhi.result){
                    mMchntOrdId = fuYouChongZhi.data;
                    //然后有订单了 再开心的支付
                    pay(editTextViews,b,mMchntOrdId);
                }

            }

            @Override
            public void onError(String jsonObject) {

            }
        });
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
     * @param mchntOrdId
     */
    private void pay(final List<EditText> editTextViews, boolean b, String mchntOrdId) {
        //设定环境 trun 正式 false 测试
        FyPay.setDev(b);
        FyPay.init(activity);
        EditText AmtE = editTextViews.get(0);
        final EditText BankCardE = editTextViews.get(1);
        final EditText NameE = editTextViews.get(2);
        final EditText IdNoE = editTextViews.get(3);
        //商户号
        String MchNtCd = "";//测试
        //String MchNtCd = "0002610F0315943";//正式
        if (b){
             MchNtCd = "0002610F0315943";//正式
        }else{
            MchNtCd = "0002900F0096235";//测试
        }

        //订单号 后台获得
        //mchntOrdId = "186036630280";

        String IdType = "0";
        //用户ID
        String UserId = "321341564654";
        //元
        int Amt = (int)(Double.parseDouble(UtilsMyText.getTextView(AmtE))*100);
        String BankCard = UtilsMyText.getTextView(BankCardE);
        String Name = UtilsMyText.getTextView(NameE);
        String IdNo = UtilsMyText.getTextView(IdNoE);
        String mMchnt_Key = "";//测试
        if (b){
            mMchnt_Key= "2yasvrx1prkh3g71ywxuryrgk9mfhp8r";//正式
        }else{
            mMchnt_Key = "5old71wihg2tqjug9kkpxnhx9hiujoqj";//测试
        }
      //  http://www-1.fuiou.com:18670/mobile_pay/update/receive.pay
        //给自己 后台服务器的 回调地址
        String backUrl = "http://qc518.com/user_recharge.php?act=appHuidiao";
        //String backUrl = "http://www-1.fuiou.com:18670/mobile_pay/update/receive.pay";
        String Sing = MD5UtilString.MD5Encode("02" + "|" + "2.0" + "|"
                + MchNtCd + "|"
                +mchntOrdId + "|"
                + UserId+ "|"
                + Amt + "|"
                + BankCard + "|" + backUrl + "|"
                + Name + "|"
                + IdNo + "|"
                + IdType + "|" +mMchnt_Key);
        Bundle bundle =new Bundle();
        //商户号
        bundle.putString(AppConfig.MCHNT_CD, MchNtCd);
        //金额
        bundle.putString(AppConfig.MCHNT_AMT, Amt+"");
        //支付结果回调地址	支付结果回调地址
        bundle.putString(AppConfig.MCHNT_BACK_URL, backUrl);
        //银行卡号
        bundle.putString(AppConfig.MCHNT_BANK_NUMBER, BankCard);
        //订单号
        bundle.putString(AppConfig.MCHNT_ORDER_ID, mchntOrdId);
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


        LogUtils.i("富友充值发给服务器"+bundle.toString());
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
               // String ss = "<VERSION>2.0</VERSION><RESPONSECODE>1029</RESPONSECODE><RESPONSEMSG>交易金额超限</RESPONSEMSG><MCHNTORDERID>1482298456107811946</MCHNTORDERID><ORDERID>16122113294400209806</ORDERID><AMT>20000</AMT><BANKCARD>6217001140002857131</BANKCARD><REM1>1.5</REM1><REM2/><REM3/><SIGNTP>MD5</SIGNTP><MCHNTCD>0002900F0096235</MCHNTCD><SIGN>6a7eb58022b42bc56e9806e8bd881fcf</SIGN><TYPE>02</TYPE><VER>sdk2.0</VER>";
              //  "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>
                // <RESPONSE>
                //      <VERSION>2.0</VERSION>
                //      <RESPONSECODE>1029</RESPONSECODE>
                //      <RESPONSEMSG>交易金额超限</RESPONSEMSG>
                //      <MCHNTORDERID>1482299822200911946</MCHNTORDERID>
                //      <ORDERID>16122113523100209835</ORDERID>
                //      <AMT>20000</AMT>
                //      <BANKCARD>6217001140002857131</BANKCARD>
                //      <REM1>1.5</REM1>
                //      <REM2/>
                //      <REM3/>
                //      <SIGNTP>MD5</SIGNTP>
                //      <MCHNTCD>0002900F0096235</MCHNTCD>
                //          <SIGN>f611a9a007fe53db8d2d5907f9889212</SIGN><TYPE>02</TYPE><VER>sdk2.0</VER></RESPONSE>"

                String s1 = "<RESPONSEMSG>";
                String s2 = "</RESPONSEMSG>";
                LogUtils.i("是否包含"+extraData.contains(s1));
                    int i1 = extraData.indexOf(s1);
                    int i2 = extraData.indexOf(s2);
                Share.save(context, "BankCardE",UtilsMyText.getTextView(BankCardE));
                Share.save(context, "NameE",UtilsMyText.getTextView(NameE));
                Share.save(context, "IdNoE",UtilsMyText.getTextView(IdNoE));
                String substring = extraData.substring(i1+s1.length(), i2);
                LogUtils.i("第一次发现标记的位置"+i1+"第二次发现标记的位置"+i2+"中间的内容是"+substring);
                topUpActivity_c.getData(substring);
                topUpActivity_c.closeActivity();

//                XStream xstream = new XStream();
//                xstream.processAnnotations(FuyouChongzhiHuiDiao.class);
//                String s = xstream.toXML(extraData);
//
//                FuyouChongzhiHuiDiao result = (FuyouChongzhiHuiDiao) xstream.fromXML(s);
//                FuyouChongzhiHuiDiao.RESPONSEBean response = result.RESPONSE;
//                String responsemsg = response.RESPONSEMSG;
//                UtilsToast.showToast(context,responsemsg );
//                LogUtils.i("xml内容是"+s);
            }
        });
    }
}
