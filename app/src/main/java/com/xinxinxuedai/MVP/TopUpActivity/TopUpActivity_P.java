package com.xinxinxuedai.MVP.TopUpActivity;

import android.content.Context;
import android.widget.EditText;

import com.xinxinxuedai.MVP.baseMVP.BaseMvp;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsHashtable;
import com.xinxinxuedai.Utils.UtilsMyText;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.request.NetWorkCallBack;
import com.xinxinxuedai.request.fuYouChongZhi_Request;

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
    static TopUpActivity_P sTopUpActivity_p;

    Context context;

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
        Hashtable<String, String> hashtable =
                UtilsHashtable.getHashtable();
        hashtable.put("money","");
        hashtable.put("bank_card","");
        hashtable.put("real_name","");
        hashtable.put("id_card","");
        hashtable.put("mobile","");
        fuYouChongZhi_Request.request(context, hashtable, new NetWorkCallBack() {

            @Override
            public void onSucceed(Object o) {

            }

            @Override
            public void onError(String jsonObject) {

            }
        });


        topUpActivity_c.getData("正常");

    }
}
