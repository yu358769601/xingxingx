package com.xinxinxuedai.MVP.LoanApplicationActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

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
     * 传送结果集 上传服务器 发送请求
     * @param textViews
     */
    void request_SetLoanInfo_Request(List<TextView> textViews, EditText editText);

    /**
     * 我是点了什么而 进来这个界面的
     * @param classTag
     */
    void setClassTag(int classTag);

    /**
     * 开始回显
     */
    void getCallBackData();
}
