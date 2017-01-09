package com.xinxinxuedai.yumengmeng.yumengmeng01.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xinxinxuedai.base.baseViewPager.BaseViewPagerAdapter;
import com.xinxinxuedai.yumengmeng.yumengmeng01.bean.Banner;

import java.util.List;

//import com.bumptech.glide.Glide;

/**
 * 广告图的  适配器
 *///
public class ImageAdapter extends BaseViewPagerAdapter<Banner> {


    private ImageView mImageView;

    public ImageAdapter(Context context, List<Banner> mList, CallBack callBack) {
        super(context, mList, callBack);
    }



    @Override
    public View addItem(ViewGroup container, int position, List<Banner> mList) {
        Banner path = mList.get(position);
        mImageView = new ImageView(c);
//        Uri parse = Uri.parse("res://" + path.getFile_path());
//        LogUtils.i("图片内容是"+parse.toString());
        Glide
                .with(c)
                .load(path.file_path)
                .centerCrop()
                .into(mImageView);

        return mImageView;
    }


}
