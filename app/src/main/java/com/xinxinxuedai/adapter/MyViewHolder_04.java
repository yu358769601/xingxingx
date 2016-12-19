package com.xinxinxuedai.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;


/**
 * Created by 35876 于萌萌
 * 创建日期: 23:03 . 2016年09月25日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */
public class MyViewHolder_04 {

    private final SparseArray<View> mViews;
    private View mConvertView;

    private MyViewHolder_04(Context context, int type, ViewGroup parent, int itemViewType,
                            int position)
    {
        this.mViews = new SparseArray<View>();

        mConvertView = LayoutInflater.from(context).inflate(itemViewType, parent,
                false);
        //View inflate = mInflater.inflate(mItemLayoutMore,parent,false);
//        if (itemViewType== R.layout.item_listview_more_04) {
//            callBack.loadMore(mConvertView, position, type);
//        }
        //setTag
        mConvertView.setTag(this);


    }

    /**
     * 拿到一个ViewHolder对象
     * @param context
     * @param convertView
     * @param type
     *@param parent
     * @param itemViewType
     * @param position
     * @param      @return
     */
    public static MyViewHolder_04 get(Context context, View convertView,
                                      int type, ViewGroup parent, int itemViewType, int position )
    {

        if (convertView == null)
        {
            return new MyViewHolder_04(context, type,parent, itemViewType, position);
        }
        return (MyViewHolder_04) convertView.getTag();
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public TextView initText(int viewId, String text)
    {
        TextView view = getView(viewId);
        if (!TextUtils.isEmpty(text)){

            view.setText(text);
        }
        return view;
    }
    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public TextView initText_hint(int viewId, String text)
    {
        TextView view = getView(viewId);
        String s = view.getHint() + text;
        view.setText(s);
        return view;
    }

    public LinearLayout initLinearLayout(int viewId){
        LinearLayout view = getView(viewId);
        return view;
    }
    public LinearLayout initLinearLayout_Show(int viewId,int b){
        LinearLayout view = getView(viewId);
        if (b==0){
            view.setVisibility(View.VISIBLE);
        }else{
            view.setVisibility(View.GONE);
        }
        return view;
    }

    /**
     *
     * @param context
     * @param viewId
     * @param url
     * @return
     */
    public ImageView initImage(Context context, int viewId, String url){
        ImageView view = getView(viewId);
        try{
            Glide.with(context)
                    .load(url)
                    .into(view);
        }catch (Exception e){
            LogUtils.i(e.toString());
        }finally {
            return view;
        }

        //view.initText(text);
    }
    /**
     *
     * @param context
     * @param viewId
     * @param
     * @return
     */
    public ImageView initImage(Context context, int viewId){
        ImageView view = getView(viewId);
            return view;

        //view.initText(text);
    }
    /**
     *
     * @param viewId
     * @param
     * @return
     */
    public ImageView initImage_status(int viewId,int status){
       //0 待还款  1 已还款 2 逾期 3提前还款 4坏账5减免

        ImageView view = getView(viewId);
      switch (status){
              case 0:
                  view.setBackgroundResource(R.drawable.home_tv01);
              break;
              case 1:
                  view.setBackgroundResource(R.drawable.home_tv02);
              break;
              case 2:
                  view.setBackgroundResource(R.drawable.home_tv03);
              break;
              case 3:
                  view.setBackgroundResource(R.drawable.home_tv04);
              break;
              case 4:
                  view.setBackgroundResource(R.drawable.home_tv01);
              break;
              case 5:
                  view.setBackgroundResource(R.drawable.home_tv02);
              break;
      }

        return view;

        //view.initText(text);
    }


    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId)
    {

        View view = mViews.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView()
    {
        return mConvertView;
    }


}