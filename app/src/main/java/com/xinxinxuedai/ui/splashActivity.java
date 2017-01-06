package com.xinxinxuedai.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsDrawable;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.app.Share;
import com.xinxinxuedai.yumengmeng.yumengmeng01.adapter.ImageAdapter;
import com.xinxinxuedai.yumengmeng.yumengmeng01.bean.Banner;
import com.xinxinxuedai.yumengmeng.yumengmeng01.view.MyViewPger;

import java.util.ArrayList;


public class splashActivity extends Activity implements View.OnClickListener {

    private MyViewPger splash_vp;
    private RelativeLayout activity_splash;
    private ArrayList<Banner> mPaths;
    private TextView splash_go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (Share.getFirst(AppContext.getApplication())){
//            startActivity(new Intent(AppContext.getApplication(),MainActivity.class));
//        }
        initView();
    }

    private void initView() {
        setContentView(R.layout.activity_splash);

        splash_vp = (MyViewPger) findViewById(R.id.splash_vp);
        splash_vp.setOnClickListener(this);
        activity_splash = (RelativeLayout) findViewById(R.id.activity_splash);
        activity_splash.setOnClickListener(this);

        initData();
        splash_go = (TextView) findViewById(R.id.splash_go);
        splash_go.setVisibility(View.INVISIBLE);
        splash_go.setOnClickListener(this);
    }

    private void initData() {

        mPaths = new ArrayList<>();
        byte[] bytes1 = getBytes(R.drawable.loading1);
        byte[] bytes2 = getBytes(R.drawable.loading2);
        byte[] bytes3 = getBytes(R.drawable.loading3);
        mPaths.add(new Banner(bytes1));
        mPaths.add(new Banner(bytes2));
        mPaths.add(new Banner(bytes3));


        ImageAdapter imageAdapter = new ImageAdapter(AppContext.getApplication(), mPaths, new ImageAdapter.CallBack() {
            @Override
            public void onItemPos(int pos) {
                LogUtils.i("我点的号是" + pos);

            }
        });
        splash_vp.setAdapter(imageAdapter);
        LogUtils.i("设置完成ViewPager");


        splash_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int mInt = 0;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                LogUtils.i("当前页面是" + position);
                if (position == 2) {
                    mInt = position;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mInt == 2 && state == 2) {
                    splash_go.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private byte[] getBytes(int drawableId) {
        Drawable drawable = getResources().getDrawable(drawableId);
        Bitmap bitmap = UtilsDrawable.drawable2Bitmap(drawable);
        return UtilsDrawable.Bitmap2Bytes(bitmap);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.splash_go:
                startActivity(new Intent(AppContext.getApplication(),MainActivity.class));
                Share.savefirst(AppContext.getApplication(),true);
                finish();

                break;
        }
    }
}
