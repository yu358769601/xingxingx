package com.xinxinxuedai.seriver;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.xinxinxuedai.Utils.LogUtils;

/**
 * Created by 35876 于萌萌
 * 创建日期: 10:43 . 2016年12月01日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class login_countTime extends Service {


    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    /**
     * 如果前面是通过bindService()来绑定启动Service的,
     * 那么当解绑停止Service时会调用该方法
     */
    @Override
    public boolean onUnbind(Intent intent)
    {
        // Log.i(TAG, "onUnbind()");
        LogUtils.i("通过绑定开启服务的解绑");
        return true;
    }
    public class  MyBind extends Binder {
        public login_countTime getlogin_countTime()
        {
            return login_countTime.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



}
