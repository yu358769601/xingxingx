package com.xinxinxuedai.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xinxinxuedai.MVP.mainActivity.MainActivity_CallBack;
import com.xinxinxuedai.MVP.mainActivity.MainActivity_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.app.Share;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.bean.GetInfo;
import com.xinxinxuedai.view.initAction_Bar;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton;
import com.xinxinxuedai.view.xuedai_button.button_CallBack;
import com.xinxinxuedai.yumengmeng.yumengmeng01.view.MyViewPger;

//首页activity 主页
public class MainActivity extends BaseActivity implements View.OnClickListener, MainActivity_CallBack {

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
        mActivity_title.setBack(false);
        mActivity_title.setCallBack(new initAction_Bar.Action_bar_call_back() {
            @Override
            public void getAction_barView_backbutton(Button button) {

            }

            @Override
            public void getAction_barView_title(TextView textView) {
                textView.setText("主页");
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(AppContext.getApplication(),Test2Activity.class));
                    }
                });
            }
        });



        mTv1 = (XueDaiButton) findViewById(R.id.tv1);
        mTv2 = (XueDaiButton) findViewById(R.id.tv2);
        mTv3 = (XueDaiButton) findViewById(R.id.tv3);
        mTv4 = (XueDaiButton) findViewById(R.id.tv4);

        //看数据库的(测试)
        TextView test = (TextView) findViewById(R.id.tv_test);
        test.setOnClickListener(this);
        //签到
        TextView tv_qiandao = (TextView) findViewById(R.id.tv_qiandao);
        tv_qiandao.setOnClickListener(this);
        //服务和借款
        TextView fuwu = (TextView) findViewById(R.id.fuwu);
        fuwu.setVisibility(View.GONE);
        fuwu.setOnClickListener(this);


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

    private long firstPressTime = 0;
    @Override
    public void onBackPressed() {
        long now = System.currentTimeMillis();
        if((now - firstPressTime) > 2000)
        {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            firstPressTime = now;
        }else
        {
            finish();
            System.exit(0);
        }
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
                .setTextSmall("Loan")
                .setInfoStuaus(true)
                .setInfoText("多种借款")
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
                .setTextSmall("Repayment")
                .setInfoStuaus(true)
                .setInfoText("管理您的欠款")
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
                .setTextSmall("State")
                .setInfoStuaus(true)
                .setInfoText("申请状态查询")
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
                .setTextSmall("help")
                .setInfoStuaus(true)
                .setInfoText("帮助客服")
        ;




    }

    @Override
    public void initP() {


        mMainActivity_p = new MainActivity_P(this);
        mMainActivity_p.setCallBack(this);
    }


    @Override
    public void initData() {




        //获取当前设备的 高度宽度
        mMainActivity_p.initGetWindow();
        //给ViewPager 设置高度
        mMainActivity_p.initViewPager(mMain_vp);
        //给ViewPager 设置数据 并且画小红点 小白点
        mMainActivity_p.initViewData(mActivity_01_ll,iv_red_point);

        mMainActivity_p.getCallBackData();


    }


    @Override
    public void initListener() {

//        mTv1.setOnClickListener(this);
//        mTv2.setOnClickListener(this);
//        mTv3.setOnClickListener(this);
//        mTv4.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dump();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Share.saveToken(AppContext.getApplication(),"2");
        if (!Share.checkLogin(AppContext.getApplication())){
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //如果是登录状态 我就查询钱数
        if (Share.checkLogin(AppContext.getApplication())){
            mMainActivity_p.getCallBackData();
        }
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
            case R.id.tv_test:
             startActivity(new Intent(AppContext.getApplication(),TestActivity.class));

            break;
            case R.id.tv_qiandao:
             startActivity(new Intent(AppContext.getApplication(),SigninActivity.class));

            break;
            case R.id.fuwu:
                LogUtils.i("我要打开");
                Intent intent = new Intent(AppContext.getApplication(), Fuwu_and_xieyi_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("classTag",1);
                intent.putExtras(bundle);
                startActivity(intent);

            break;

        }
    }

    /**
     * 关掉界面
     */
    @Override
    public void closeActivity() {

    }

    /**
     * 清除的方法
     */
    @Override
    public void dump() {
        mMainActivity_p = null;
      mActivity_title= null;
        mTv1 = null;
        mTv2= null;
        mTv3= null;
        mTv4= null;
        mMain_vp= null;
       iv_red_point= null;
        mMainActivity_p= null;
        mActivity_01_ll= null;
        mMain_iv= null;
        mMain_tv_name= null;
        mMain_tv_money= null;

    }

    /**
     * 左下角数据
     *
     * @param dataMoney
     */
    @Override
    public void setDataMoney(GetInfo dataMoney) {
        mMain_tv_money.setText(mMain_tv_money.getHint()+dataMoney.loan_money+"元");
    }
}
