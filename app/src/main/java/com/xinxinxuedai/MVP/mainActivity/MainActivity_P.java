package com.xinxinxuedai.MVP.mainActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsDrawable;
import com.xinxinxuedai.WebView.ui.WebViewActivity;
import com.xinxinxuedai.ui.ApplyForActivity;
import com.xinxinxuedai.ui.MainActivity;
import com.xinxinxuedai.ui.RegisterActivity;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton;
import com.xinxinxuedai.yumengmeng.yumengmeng01.adapter.ImageAdapter;
import com.xinxinxuedai.yumengmeng.yumengmeng01.bean.Banner;
import com.xinxinxuedai.yumengmeng.yumengmeng01.utils.intctorUtils;
import com.xinxinxuedai.yumengmeng.yumengmeng01.view.MyViewPger;

import java.util.ArrayList;

/**
 * Created by 35876 于萌萌
 * 创建日期: 11:22 . 2016年11月30日
 * 描述:处理MainActivity 逻辑的类
 * <p>
 * <p>
 * 备注:
 */

public class MainActivity_P implements MainActivity_method{
    Context context;
    private final MainActivity mMainActivity;
    public static final int BANNER_LOOP_TIME = 5000;
    private int displayWidth;
    private int displayHeight;
    private RelativeLayout.LayoutParams mParams;
    //当前选中的banner 索引
    private int currentBannerPos = 0;
    private ArrayList<Banner> mPaths;
    MyViewPger myViewPger;
    private Handler mHandler = new Handler();
    private Runnable loopRunnable = new Runnable() {
        @Override
        public void run() {
            if(++currentBannerPos>=mPaths.size()){
                currentBannerPos = 0;
            }
            myViewPger.setCurrentItem(currentBannerPos);
            mHandler.postDelayed(loopRunnable, BANNER_LOOP_TIME);
        }
    };


    public MainActivity_P(Context context){
        this.context = context;
        mMainActivity = (MainActivity) context;
    }


    @Override
    public void initGetWindow() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        mMainActivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        displayWidth = displayMetrics.widthPixels;
        displayHeight = displayMetrics.heightPixels;
        // 第一个按钮，宽度100%，高度25%
        mParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                (int) (displayHeight * 0.25f + 0.5f));


//        // 第二个按钮，宽度100%，高度30%
//        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
//                LayoutParams.FILL_PARENT,
//                (int) (Constant.displayHeight * 0.3f + 0.5f));
//        btn2.setLayoutParams(params2);
//        // 第三个按钮，宽度50%，高度20%
//        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
//                (int) (Constant.displayWidth * 0.5f + 0.5f),
//                (int) (Constant.displayHeight * 0.2f + 0.5f));
//        btn3.setLayoutParams(params3);
//        // 第三个按钮，宽度70%，高度填满剩下的空间
//        LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(
//                (int) (Constant.displayWidth * 0.7f + 0.5f),
//                LayoutParams.FILL_PARENT);
//        btn4.setLayoutParams(params4);

    }

    @Override
    public void initViewPager(MyViewPger myViewPger) {
        myViewPger.setLayoutParams(mParams);
        this.myViewPger = myViewPger;
    }

    @Override
    public void initViewData(LinearLayout activity_01_ll , ImageView iv_red_point) {
        mPaths = new ArrayList<>();
        Drawable drawable = context.getResources().getDrawable(R.drawable.home_viewpager);
        Bitmap bitmap = UtilsDrawable.drawable2Bitmap(drawable);
        byte[] bytes = UtilsDrawable.Bitmap2Bytes(bitmap);

        //Uri res = new Uri.Builder().scheme("res").path(String.valueOf(R.drawable.home)).build();
//        mPaths.add(new Banner("http://pic.yesky.com/uploadImages/2015/026/38/MG734XC8AM7T.jpg"));
//        mPaths.add(new Banner("http://www.gamemei.com/background/uploads/160829/30-160R9143H3343.jpg"));
//        mPaths.add(new Banner("http://www.gamemei.com/background/uploads/160829/30-160R9143612P5.jpg"));
//        mPaths.add(new Banner("http://img3.duitang.com/uploads/item/201608/20/20160820091508_diTXf.thumb.700_0.jpeg"));
//        mPaths.add(new Banner("http://joymepic.joyme.com/article/uploads/allimg/201609/1473906749455425.jpg?watermark/1/image/aHR0cDovL2pveW1lcGljLmpveW1lLmNvbS9hcnRpY2xlL3VwbG9hZHMvMTYwODE5LzgwLTE2MFE5MUZaMzQzOC5wbmc=/dissolve/70"));
        mPaths.add(new Banner(bytes));
         mPaths.add(new Banner(bytes));
         mPaths.add(new Banner(bytes));
         mPaths.add(new Banner(bytes));
         mPaths.add(new Banner(bytes));


        ImageAdapter imageAdapter = new ImageAdapter(context, mPaths, new ImageAdapter.CallBack() {
            @Override
            public void onItemPos(int pos) {
                LogUtils.i("我点的号是"+pos);

            }
        });
        myViewPger.setAdapter(imageAdapter);
        LogUtils.i("设置完成ViewPager");

        activity_01_ll.removeAllViews();
        intctorUtils.startDrawable(context, mPaths,activity_01_ll,myViewPger,iv_red_point );

        mHandler.postDelayed(loopRunnable, BANNER_LOOP_TIME);

        myViewPger.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentBannerPos = position;
                mHandler.removeCallbacks(loopRunnable);
                mHandler.postDelayed(loopRunnable, BANNER_LOOP_TIME);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }

    @Override
    public void initClick(XueDaiButton xueDaiButton) {
        Intent intent = new Intent();
        switch (xueDaiButton.getType()){
            case 1:
                intent.setClass(context, ApplyForActivity.class);
                context.startActivity(intent);
                break;
            case 2:
                intent.setClass(context, RegisterActivity.class);
                context.startActivity(intent);
                break;
            case 3:

                break;
            case 4:
                intent.setClass(context, WebViewActivity.class);
                context.startActivity(intent);
                break;
        }

    }

    @Override
    public void initClickView(View view) {

    }

}
