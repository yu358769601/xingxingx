package com.xinxinxuedai.MVP.ApplyForActivity;

import com.xinxinxuedai.view.xuedai_button.XueDaiButton;

/**
 * Created by 35876 于萌萌
 * 创建日期: 16:53 . 2016年12月01日
 * 描述:申请贷款的方法
 * <p>
 * <p>
 * 备注:
 */

public interface ApplyForActivity_method {
    //按钮被点击了
    void initClick(XueDaiButton xueDaiButton);

    /**
     * 获取 info 信息
     */
    void getCallBackData();

    /**
     * 提交申请
     */
    void setSub();
}
