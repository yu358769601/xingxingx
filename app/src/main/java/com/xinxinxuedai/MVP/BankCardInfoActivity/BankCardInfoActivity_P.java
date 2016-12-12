package com.xinxinxuedai.MVP.BankCardInfoActivity;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsHashtable;
import com.xinxinxuedai.Utils.UtilsLoop.UtilsLoopCallBack;
import com.xinxinxuedai.Utils.UtilsLoop.UtilsLoopTextView;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.request.NetWorkCallBack;
import com.xinxinxuedai.request.setInfo3_Request;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by 35876 于萌萌
 * 创建日期: 14:13 . 2016年12月05日
 * 描述:银行卡信息_逻辑
 * <p>
 * <p>
 * 备注:
 */

public class BankCardInfoActivity_P extends BaseMvp<BankCardInfoActivity_C> implements BankCardInfoActivity_M{

    Context context;
    static BankCardInfoActivity_P mBankCardInfoActivity_p;

    public BankCardInfoActivity_P(Context context) {
        this.context = context;
    }

    public  static BankCardInfoActivity_P getmBankCardInfoActivity_p(Context context){
        if (null ==mBankCardInfoActivity_p){
            return mBankCardInfoActivity_p = new BankCardInfoActivity_P(context);
        }
        return mBankCardInfoActivity_p;
    }
    BankCardInfoActivity_C bankCardInfoActivity_c;

    @Override
    public void setCallBack(BankCardInfoActivity_C bankCardInfoActivity_c) {
        this.bankCardInfoActivity_c = bankCardInfoActivity_c;
    }


    /**
     * 获取 这个页面上所有的 editText
     *
     * @param editTexts
     */
    @Override
    public void getEdtexts(ArrayList<TextView> editTexts, ArrayList<String> strings) {
        UtilsLoopTextView.startLoop(editTexts, strings, new UtilsLoopCallBack() {
            @Override
            public void onSucceed() {
                //访问网络
                //UtilsToast.showToast(context, "网络请求中~");
                //  call_setInfo3_Request(editTexts);
            }

            @Override
            public void onError(View view, int numTag, String rroreInfo) {
                UtilsToast.showToast(context, rroreInfo);
                return;
            }
        });

    }

    private void call_setInfo3_Request(ArrayList<TextView> editTexts) {

        Hashtable<String, String> hashtable = UtilsHashtable.getHashtable();
        //银行名字(选择得到)
        hashtable.put("loan_bank_name",editTexts.get(0).getText().toString().trim());
        //开户行 名字
        hashtable.put("loan_bank_open",editTexts.get(1).getText().toString().trim());
        //银行卡号
        hashtable.put("loan_bank_card",editTexts.get(2).getText().toString().trim());

        setInfo3_Request.request(context, hashtable, new NetWorkCallBack() {
            @Override
            public void onSucceed(JSONObject jsonObject) {

            }

            @Override
            public void onError(String jsonObject) {

            }
        });
    }
}
