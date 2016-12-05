package com.xinxinxuedai.MVP.LoanApplicationActivity;

import android.content.Context;
import android.view.View;

import java.util.Hashtable;

/**
 * Created by 35876 于萌萌
 * 创建日期: 16:38 . 2016年12月02日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public interface LoanApplicationActivity_method {

    /**
     * 按了按钮
     * @param view
     */
    void clicks(View view);

    /**
     * 调用网络请求 把 需要的参数 传过去
     * @param context 上下文
     * @param hashtable 字段 内容等
     */
    void requestOperation(Context context , Hashtable hashtable);

}
