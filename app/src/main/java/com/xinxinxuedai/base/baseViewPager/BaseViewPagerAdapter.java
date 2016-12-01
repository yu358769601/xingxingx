package com.xinxinxuedai.base.baseViewPager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

//import com.bumptech.glide.Glide;

/**
 * 广告图的  适配器
 *///
public abstract class BaseViewPagerAdapter<T> extends PagerAdapter {

    private List<T> mList;
    private List<ImageView> viewlist = new ArrayList<>();
    public Context c;
    private LayoutInflater mInflater;
    private ImageView view;
    public CallBack callBack;
    public BaseViewPagerAdapter(Context context , List<T> mList, CallBack callBack) {
        this.callBack = callBack;
        this.mList = mList;
        c = context;
        mInflater = LayoutInflater.from(c);
        //initData();
    }

    public interface CallBack{
        void onItemPos(int pos);
    }
    public void setData(List<T> mList) {


        this.mList = mList;
        notifyDataSetChanged();
    }

//    /**
//     * 给集合 添加数据的方法
//     */
//    private void initData() {
//        setItemViewData(mList);
//    }
//    public abstract void setItemViewData(List<T> mList);
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        //对ViewPager页号求模取出View列表中要显示的项

        View view = addItem(container, position, mList);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.onItemPos(position);
            }
        });
//        Glide.with(c)
//                .load(paths.get(position).file_path)
//                .into(view);
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(view);
        }
        container.addView(view);
        //add listeners here if necessary
        return view;
    }
    public abstract View addItem(ViewGroup container, final int position,List<T> mList);
}
