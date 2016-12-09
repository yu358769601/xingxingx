package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinxinxuedai.MVP.mainActivity.MainActivity_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.view.initAction_Bar;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton;
import com.xinxinxuedai.view.xuedai_button.button_CallBack;
import com.xinxinxuedai.yumengmeng.yumengmeng01.view.MyViewPger;

//首页activity
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private initAction_Bar mActivity_title;
    private XueDaiButton mTv1;
    private XueDaiButton mTv2;
    private XueDaiButton mTv3;
    private XueDaiButton mTv4;

    private MyViewPger mMain_vp;
    private ImageView iv_red_point;
    private MainActivity_P mMainActivity_p;
    private LinearLayout mActivity_01_ll;
    private ImageView mMain_iv;
    private TextView mMain_tv_name;
    private TextView mMain_tv_money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initP();
        initView();
    }



    @Override
    public int getlayouXML() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mActivity_title = (initAction_Bar) findViewById(R.id.relativeLayout_title);
        mActivity_title.setCallBack(new initAction_Bar.Action_bar_call_back() {
            @Override
            public void getAction_barView_backbutton(Button button) {

            }

            @Override
            public void getAction_barView_title(TextView textView) {
                textView.setText("主页");

            }
        });



        mTv1 = (XueDaiButton) findViewById(R.id.tv1);
        mTv2 = (XueDaiButton) findViewById(R.id.tv2);
        mTv3 = (XueDaiButton) findViewById(R.id.tv3);
        mTv4 = (XueDaiButton) findViewById(R.id.tv4);


        mMain_iv = (ImageView) findViewById(R.id.main_iv);
        mMain_iv.setOnClickListener(this);
        mMain_tv_name = (TextView) findViewById(R.id.main_tv_name);
        mMain_tv_name.setOnClickListener(this);


        mMain_vp = (MyViewPger) findViewById(R.id.main_vp);

        iv_red_point = (ImageView) findViewById(R.id.activity_01_point_red);

        mActivity_01_ll = (LinearLayout) findViewById(R.id.activity_01_ll);
        //点击了登陆
        mMain_tv_money = (TextView) findViewById(R.id.main_tv_money);
        mMain_tv_money.setOnClickListener(this);

        initData();
        initListener();
        initText();
    }

    private void initText() {
        mTv1.setCallBack(new button_CallBack() {

            @Override
            public void button_Click(View v) {
                LogUtils.i("tv1被点击了");
                mTv1.setType(1);
                mMainActivity_p.initClick(mTv1);
            }

        }).setTextColor(getResources().getColor(R.color.home_tv1))
                .setTextSmallColor(getResources().getColor(R.color.home_tv1))
                .setTopDrawable(R.drawable.home_tv01)
                .setText("我要借款")
                .setTextSmall("a")
        ;

        mTv2.setCallBack(new button_CallBack() {

            @Override
            public void button_Click(View v) {
                LogUtils.i("tv2被点击了");
                mTv2.setType(2);
                mMainActivity_p.initClick(mTv2);
            }

        }).setTextColor(getResources().getColor(R.color.home_tv2))
                .setTextSmallColor(getResources().getColor(R.color.home_tv2))
                .setTopDrawable(R.drawable.home_tv02)
                .setText("我要还款")
                .setTextSmall("b")
        ;

        mTv3.setCallBack(new button_CallBack() {

            @Override
            public void button_Click(View v) {
                LogUtils.i("tv3被点击了");
                mTv3.setType(3);
                mMainActivity_p.initClick(mTv3);
            }

        }).setTextColor(getResources().getColor(R.color.home_tv3))
                .setTextSmallColor(getResources().getColor(R.color.home_tv3))
                .setTopDrawable(R.drawable.home_tv03)
                .setText("借款状态")
                .setTextSmall("c")
        ;

        mTv4.setCallBack(new button_CallBack() {

            @Override
            public void button_Click(View v) {
                LogUtils.i("tv4被点击了");
                mTv4.setType(4);
                mMainActivity_p.initClick(mTv4);
            }

        }).setTextColor(getResources().getColor(R.color.home_tv4))
                .setTextSmallColor(getResources().getColor(R.color.home_tv4))
                .setTopDrawable(R.drawable.home_tv04)
                .setText("关于我们")
                .setTextSmall("d")
        ;




    }

    @Override
    public void initP() {
        mMainActivity_p = new MainActivity_P(this);

    }


    @Override
    public void initData() {




        //获取当前设备的 高度宽度
        mMainActivity_p.initGetWindow();
        //给ViewPager 设置高度
        mMainActivity_p.initViewPager(mMain_vp);
        //给ViewPager 设置数据 并且画小红点 小白点
        mMainActivity_p.initViewData(mActivity_01_ll,iv_red_point);

    }


    @Override
    public void initListener() {

//        mTv1.setOnClickListener(this);
//        mTv2.setOnClickListener(this);
//        mTv3.setOnClickListener(this);
//        mTv4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //点击了注册
            case R.id.main_iv:
                v.setTag(1);
                mMainActivity_p.initClickView(v);
            break;
            //点击了重置
            case R.id.main_tv_name:
                v.setTag(2);
                mMainActivity_p.initClickView(v);
            break;
            //点击了登陆
            case R.id.main_tv_money:
                v.setTag(3);
                mMainActivity_p.initClickView(v);
            break;

        }
    }
}
