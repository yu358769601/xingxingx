package com.xinxinxuedai.base.baselistAdapter.interfaces;


import com.xinxinxuedai.base.baselistAdapter.BaseAdapterHelper;

/**
 */
public interface IAdapter<T> {

    void onUpdate(BaseAdapterHelper helper, T item, int position);

    int getLayoutResId(T item, int position);
}
