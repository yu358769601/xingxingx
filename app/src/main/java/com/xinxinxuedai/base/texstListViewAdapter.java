package com.xinxinxuedai.base;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xinxinxuedai.R;
import com.xinxinxuedai.bean.Item_base;

import java.util.ArrayList;

/**
 * Created by 35876 于萌萌
 * 创建日期: 22:46 . 2016年10月02日
 * 描述:简单的listView适配器
 * <p/>
 * <p/>
 * 备注:
 */
public class texstListViewAdapter extends BaseAdapter {
    Activity mActivity;
    ArrayList<Item_base> mItem_bases = new ArrayList<>();
    private final LayoutInflater mLayoutInflater;

    public texstListViewAdapter(Activity activity, ArrayList<Item_base> item_bases) {
        mActivity = activity;
        mItem_bases = item_bases;
        mLayoutInflater = mActivity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return mItem_bases.size();
    }

    @Override
    public Item_base getItem(int i) {
        return mItem_bases.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder viewholder =null;
        if (view==null){
            view = mLayoutInflater.inflate(R.layout.item_item_name, viewGroup, false);
            viewholder =  new Viewholder();
            viewholder.title =(TextView)view.findViewById(R.id.list_item_tv_title);

            view.setTag(viewholder);
        }else{
            viewholder = (Viewholder) view.getTag();
        }
        viewholder.title.setText(mItem_bases.get(i).title);

        return view;
    }
    class Viewholder {
        public TextView title;
    }
}
