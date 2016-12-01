package com.xinxinxuedai.base.baseui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by 35876 于萌萌
 * 创建日期: 16:35 . 2016年09月04日
 * 描述:
 * <p/>
 * <p/>
 * 备注:
 */
public abstract class BaseFragment extends Fragment {

    public View rootView;
    public Context mContext;
   // public LayoutInflater mConvertView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
      //  mConvertView = LayoutInflater.from(mContext);
        getBundle();
        rootView =initViewFragment(inflater,container);

        initDataFragment(rootView);
        return rootView;
    }


    public abstract void initListener();
    public abstract void  setStatus(int status);
    public abstract View initViewFragment(LayoutInflater inflater, ViewGroup container);
    public abstract void initDataFragment(View rootView);
    public abstract void getBundle();
    public abstract int getStatus();
}
