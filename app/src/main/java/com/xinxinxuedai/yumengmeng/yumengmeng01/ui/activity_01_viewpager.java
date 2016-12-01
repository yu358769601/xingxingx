//package com.xinxinxuedai.yumengmeng.yumengmeng01.ui;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.v4.view.ViewPager;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.example.mylibrarybase.R;
//import com.example.mylibrarybase.bar.initAction_Bar;
//import com.example.mylibrarybase.base.baseui.BaseActivity;
//import com.example.mylibrarybase.utils.LogUtils;
//import com.example.mylibrarybase.yumengmeng01.adapter.ImageAdapter;
//import com.example.mylibrarybase.yumengmeng01.bean.Banner;
//import com.example.mylibrarybase.yumengmeng01.utils.intctorUtils;
//import com.example.mylibrarybase.yumengmeng01.view.MyViewPger;
//
//import java.util.ArrayList;
//
//public class activity_01_viewpager extends BaseActivity {
//
//
//    private RelativeLayout mAction;
//    public static final int BANNER_LOOP_TIME = 5000;
//    public MyViewPger viewPager;
//    ArrayList<Banner> paths = new ArrayList<>();
//    public CallBack callBack;
//    private ImageView iv_red_point;
//    private Handler mHandler = new Handler();
//
//    //当前选中的banner 索引
//    private int currentBannerPos = 0;
//
//    private LinearLayout activity_01_ll;
//    private Runnable loopRunnable = new Runnable() {
//        @Override
//        public void run() {
//            if(++currentBannerPos>=paths.size()){
//                currentBannerPos = 0;
//            }
//            viewPager.setCurrentItem(currentBannerPos);
//            mHandler.postDelayed(loopRunnable, BANNER_LOOP_TIME);
//        }
//    };
//    private initAction_Bar mBase_action_bar;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        initView();
//    }
//
//    @Override
//    public int getlayouXML() {
//        return R.layout.activity_main_01_activity;
//    }
//
//    @Override
//    public void initView() {
//        //广告图 对象
//        viewPager = (MyViewPger) findViewById(R.id.activity_01_viewpager);
//        activity_01_ll = (LinearLayout) findViewById(R.id.activity_01_ll);
//        iv_red_point = (ImageView) findViewById(R.id.activity_01_point_red);
//
//        mBase_action_bar = (initAction_Bar) findViewById(R.id.base_action_bar);
//        mBase_action_bar
//                .setMode(initAction_Bar.AUTO_ONCLICK_MODE)
//                .setCallBack(new initAction_Bar.Action_bar_call_back() {
//                    @Override
//                    public void getAction_barView_backbutton(TextView textView) {
//                        textView.setText("返回");
//                    }
//
//                    @Override
//                    public void getAction_barView_backbutton_icon(ImageView imageView) {
//
//                    }
//
//                    @Override
//                    public void getAction_barView_title(TextView textView) {
//                        textView.setText("图片轮播带有圆点指示器的");
//                    }
//
//                    @Override
//                    public void getAction_barView_right_icon(ImageView imageView) {
//
//                    }
//                });
//
//        initData();
//    }
//
//    @Override
//    public void initData() {
//        paths.add(new Banner("http://pic.yesky.com/uploadImages/2015/026/38/MG734XC8AM7T.jpg"));
//        paths.add(new Banner("http://www.gamemei.com/background/uploads/160829/30-160R9143H3343.jpg"));
//        paths.add(new Banner("http://www.gamemei.com/background/uploads/160829/30-160R9143612P5.jpg"));
//        paths.add(new Banner("http://img3.duitang.com/uploads/item/201608/20/20160820091508_diTXf.thumb.700_0.jpeg"));
//        paths.add(new Banner("http://joymepic.joyme.com/article/uploads/allimg/201609/1473906749455425.jpg?watermark/1/image/aHR0cDovL2pveW1lcGljLmpveW1lLmNvbS9hcnRpY2xlL3VwbG9hZHMvMTYwODE5LzgwLTE2MFE5MUZaMzQzOC5wbmc=/dissolve/70"));
//        setViewPager(paths);
//    }
//    public void setCallBack(CallBack callBack){
//        this.callBack =callBack;
//    }
//    //获取网络成功之后调用的方法
//    public void setViewPager(ArrayList<Banner> paths) {
//        this.paths =paths;
//        ImageAdapter imageAdapter = new ImageAdapter(this, paths, new ImageAdapter.CallBack() {
//            @Override
//            public void onItemPos(int pos) {
//                LogUtils.i("我点的号是"+pos);
//
//            }
//        });
//        viewPager.setAdapter(imageAdapter);
//        LogUtils.i("设置完成ViewPager");
//
//        activity_01_ll.removeAllViews();
//        intctorUtils.startDrawable(this, paths,activity_01_ll,viewPager,iv_red_point );
//
//        mHandler.postDelayed(loopRunnable, BANNER_LOOP_TIME);
//
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                currentBannerPos = position;
//                mHandler.removeCallbacks(loopRunnable);
//                mHandler.postDelayed(loopRunnable, BANNER_LOOP_TIME);
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
//    }
//
//    public interface CallBack{
//        void setFragmentAdapter(ViewPager viewPager);
//    }
//
//}
