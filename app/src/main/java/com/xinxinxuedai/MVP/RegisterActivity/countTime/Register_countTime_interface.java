package com.xinxinxuedai.MVP.RegisterActivity.countTime;

/**
 * Created by 35876 于萌萌
 * 创建日期: 10:48 . 2016年12月01日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public interface Register_countTime_interface {
    //开始倒计时
    void startCountDown();
    //暂停倒计时
    void pauseCountDown();
    //停止倒计时
    void stopCountDown();
    //绑定服务
    void doBindSerive();

}
