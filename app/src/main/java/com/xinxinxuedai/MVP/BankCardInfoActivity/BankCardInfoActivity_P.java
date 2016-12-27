package com.xinxinxuedai.MVP.BankCardInfoActivity;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsHashtable;
import com.xinxinxuedai.Utils.UtilsLoop.UtilsLoopCallBack;
import com.xinxinxuedai.Utils.UtilsLoop.UtilsLoopTextView;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.bean.GetInfo;
import com.xinxinxuedai.bean.SetInfo3;
import com.xinxinxuedai.request.GetInfo_Request;
import com.xinxinxuedai.request.NetWorkCallBack;
import com.xinxinxuedai.request.SetInfo3_Request;

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

    public BankCardInfoActivity_P(Context context) {
        this.context = context;
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
    public void getEdtexts(final ArrayList<TextView> editTexts, ArrayList<String> strings) {
        UtilsLoopTextView.startLoop(editTexts, strings, new UtilsLoopCallBack() {
            @Override
            public void onSucceed() {
                //访问网络
              //  UtilsToast.showToast(context, "网络请求中~");
                  call_setInfo3_Request(editTexts);
            }

            @Override
            public void onError(View view, int numTag, String rroreInfo) {
                UtilsToast.showToast(context, rroreInfo);
                return;
            }
        });

    }

    /**
     * 获取回显信息
     */
    @Override
    public void getCallBackData() {
        GetInfo_Request.request(context, new NetWorkCallBack<GetInfo>() {
            @Override
            public void onSucceed(GetInfo getInfo,int dataMode) {
                LogUtils.i("银行卡信息"+getInfo);
                bankCardInfoActivity_c.setCallBackData(getInfo);
            }

            @Override
            public void onError(String jsonObject) {

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

        SetInfo3_Request.request(context, hashtable, new NetWorkCallBack<SetInfo3>() {
            @Override
            public void onSucceed(SetInfo3 info3,int dataMode) {
               UtilsToast.showToast(context, info3.message);
                //关掉界面
                bankCardInfoActivity_c.closeActivity();
            }

            @Override
            public void onError(String jsonObject) {

            }
        });
    }
}
