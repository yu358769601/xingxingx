package com.xinxinxuedai.MVP.BankCardInfoActivity;

import android.content.Context;
import android.widget.EditText;

import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.Utils.UtilsMyText;
import com.xinxinxuedai.Utils.UtilsToast;

import java.util.List;

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
    public void getEdtexts(List<EditText> editTexts) {
        if (editTexts.size()==0||null == editTexts){
            return;
        }
        for (int i = 0; i < editTexts.size(); i++) {
            EditText editText = editTexts.get(i);
            int tag = (int) editText.getTag();
            //判断是否为空
            if (UtilsMyText.isEmptys(editText)){
                switch (tag) {
                    case 1:
                        UtilsToast.showToast(context, "未选择银行卡");
                        return;
                    case 2:
                        UtilsToast.showToast(context, "开户行名称没有填写");
                        return;
                    case 3:
                        UtilsToast.showToast(context, "银行卡号没有填写");
                        return;
                }
            }


        }
//        for (int i = 0; i < editTexts.size(); i++) {
//            EditText editText = editTexts.get(i);
//            //String string = editText.getText().toString();
//            int tag = (int) editText.getTag();
//
//            //判断长度是否够用
//            switch (tag) {
//                case 1:
//                    break;
//                case 2:
//                    if (UtilsMyText.getLengh(editText)<=11){
//                        UtilsToast.showToast(context, "身份证长度不正确");
//                        return;
//                    }
//
//                case 3:
//                    break;
//                case 4:
//                    if (UtilsMyText.getLengh(editText)<=8){
//                        UtilsToast.showToast(context, "家庭住址长度不正确");
//                        return;
//                    }
//                case 5:
//                    break;
//                case 6:
//                    if (UtilsMyText.getLengh(editText)<=11){
//                        UtilsToast.showToast(context, "紧急联系人电话长度小于11位");
//                        return;
//                    }
//            }
//        }


            UtilsToast.showToast(context, "网络请求中~");

    }
}
