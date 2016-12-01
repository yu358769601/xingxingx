package com.xinxinxuedai.MVP.RegisterActivity.countTime;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;

import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsBroadcastReceiver;
import com.xinxinxuedai.seriver.login_countTime;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 35876 于萌萌
 * 创建日期: 10:45 . 2016年12月01日
 * 描述: 服务的逻辑
 * <p>
 * <p>
 * 备注:
 */

public class Register_countTime_P implements Register_countTime_interface {
    static Register_countTime_P mLoginActivity_p;
    public  static int numCount = 10;
    public static final int RUNING = 100;
    public static final int STOP = 200;


    public  static int status = STOP;
    Handler mHandler =  new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                //倒计时的 消息
                case 1:
                    numCount--;
                    UtilsBroadcastReceiver.sendBroadcastReceiver(context, "countTime","count",numCount+"");
                    if (numCount>0){
                        startCountDown();
                    }else{
                        stopCountDown();
                    }
                break;
            }

        }
    };
    Context context;
    private ExecutorService mExecutorService;

    public Register_countTime_P(Context context){
        this.context = context;
        initExecutors();

    }

    private void initExecutors() {
        //创建一个线程池 这个线程池的作用是 用来倒计时
        mExecutorService = Executors.newSingleThreadExecutor();
    }

    public static Register_countTime_P getLogin_countTime_P(Context context){
        LogUtils.i("注册按钮的对象"+mLoginActivity_p);
        if (null==mLoginActivity_p){
            return mLoginActivity_p = new Register_countTime_P(context);
        }else{

            return mLoginActivity_p;
        }

    }


    Register_countTime_P_CallBack login_countTime_p_callBack ;
    public void setCallBack(Register_countTime_P_CallBack login_countTime_p_callBack){
        this.login_countTime_p_callBack = login_countTime_p_callBack;
    }

    @Override
    public void startCountDown() {
        status = RUNING;
        //如果是 停止状态去 点击开始倒计时
        startThreadPool();
        if (null!= login_countTime_p_callBack)
        login_countTime_p_callBack.getCountTimeStatus(status);
    }

    private void startThreadPool() {

        mExecutorService.submit(new countTime());
    }

    @Override
    public void pauseCountDown() {

    }

    @Override
    public void stopCountDown() {
        numCount = 10;
        status = STOP;
        if (null!= login_countTime_p_callBack)
            login_countTime_p_callBack.getCountTimeStatus(status);
    }


    class countTime implements Runnable{

        @Override
        public void run() {
            Message message = mHandler.obtainMessage();
            message.what = 1;
            mHandler.sendMessage(message);
            SystemClock.sleep(1000);


        }
    }


    public void doBindSerive() {
        Intent intent = new Intent(context, login_countTime.class);
        //bindIntent.setAction("com.test.service.My_MSG");
        //当Service还没创建时,
        //第三个参数如果为0则不自动创建Service,为Service.BIND_AUTO_CREATE则自动创建
        context.bindService(intent, conn, Service.BIND_AUTO_CREATE);
    }
    //代理人对象
    private login_countTime.MyBind myBinder;

    private ServiceConnection conn = new ServiceConnection() {

        //当Activity和Service连接成功时会调用该方法
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            //在这里通过自定义的Binder与Service通信  代理人对象
            myBinder = (login_countTime.MyBind)service;

        }


        //当Activity和Service断开连接时会调用该方法
        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub

        }

    };

}
