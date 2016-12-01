package com.xinxinxuedai.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by 35876 于萌萌
 * 创建日期: 22:22 . 2016年10月02日
 * 描述:基类
 * <p/>
 * <p/>
 * 备注:
 */
public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayouXML());


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    public abstract int getlayouXML();

    public abstract void initView();
    public abstract void initP();
    public abstract void initData();
    public abstract void initListener();


}
