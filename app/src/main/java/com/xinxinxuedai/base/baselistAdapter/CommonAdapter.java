package com.xinxinxuedai.base.baselistAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.xinxinxuedai.base.baselistAdapter.interfaces.IAdapter;
import com.xinxinxuedai.base.baselistAdapter.interfaces.IData;

import java.util.ArrayList;
import java.util.List;

import static com.xinxinxuedai.base.baselistAdapter.BaseAdapterHelper.get;


/**
 */
public abstract class CommonAdapter<T> extends BaseAdapter implements IData<T>, IAdapter<T> {
    protected final Context mContext;
    protected final int     mLayoutResId;
    protected final List<T> mData;


    public CommonAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }


    public CommonAdapter(Context context, int layoutResId, List<T> data) {
        this.mData = data == null ? new ArrayList<T>() : new ArrayList<>(data);
        this.mContext = context;
        this.mLayoutResId = layoutResId;
    }


    @Override public int getCount() {
        return mData.size();
    }


    @Override public T getItem(int position) {
        return position >= mData.size() ? null : mData.get(position);
    }


    @Override public long getItemId(int position) {
        return position;
    }


    @Override public int getLayoutResId(T item, int position) {
        return this.mLayoutResId;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        final T item = getItem(position);
        final BaseAdapterHelper helper =
            get(mContext, convertView, parent, getLayoutResId(item, position), position);
        onUpdate(helper, item, position);
        helper.setAssociatedObject(item);
        return helper.getView();
    }

    @Override public boolean isEnabled(int position) {
        return position < mData.size();
    }

    @Override public void add(T elem) {
        mData.add(elem);
        notifyDataSetChanged();
    }

    @Override public void addAll(List<T> elem) {
        mData.addAll(elem);
        notifyDataSetChanged();
    }

    @Override public void set(T oldElem, T newElem) {
        set(mData.indexOf(oldElem), newElem);
    }

    @Override public void set(int index, T elem) {
        mData.set(index, elem);
        notifyDataSetChanged();
    }

    @Override public void remove(T elem) {
        mData.remove(elem);
        notifyDataSetChanged();
    }

    @Override public void remove(int index) {
        mData.remove(index);
        notifyDataSetChanged();
    }

    @Override public void replaceAll(List<T> elem) {
        mData.clear();
        mData.addAll(elem);
        notifyDataSetChanged();
    }

    @Override public boolean contains(T elem) {
        return mData.contains(elem);
    }

    @Override public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }
}
