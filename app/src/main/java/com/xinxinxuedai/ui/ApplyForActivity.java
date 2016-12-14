package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinxinxuedai.MVP.ApplyForActivity.ApplyForActivity_P;
import com.xinxinxuedai.MVP.ApplyForActivity.ApplyForActivity_callback;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.bean.GetInfo;
import com.xinxinxuedai.view.initAction_Bar;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton;
import com.xinxinxuedai.view.xuedai_button.button_CallBack;

//申请借款activity 申请贷款
public class ApplyForActivity extends BaseActivity implements ApplyForActivity_callback {

    private initAction_Bar mRelativeLayout_title;
    private XueDaiButton mTv1;
    private XueDaiButton mTv2;
    private XueDaiButton mTv3;
    private XueDaiButton mTv4;
    private ApplyForActivity_P mApplyForActivity_p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initP();
        initView();
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_apply_for;
    }

    @Override
    public void initView() {
        mRelativeLayout_title = (initAction_Bar) findViewById(R.id.relativeLayout_title);
        mRelativeLayout_title.setCallBack(new initAction_Bar.Action_bar_call_back() {
            @Override
            public void getAction_barView_backbutton(Button button) {

            }

            @Override
            public void getAction_barView_title(TextView textView) {
                textView.setText("申请贷款");
            }
        });

        mTv1 = (XueDaiButton) findViewById(R.id.tv1);
        mTv2 = (XueDaiButton) findViewById(R.id.tv2);
        mTv3 = (XueDaiButton) findViewById(R.id.tv3);
        mTv4 = (XueDaiButton) findViewById(R.id.tv4);

        initText();
        initData();

    }

    private void initText() {
        mTv1.setCallBack(new button_CallBack() {

            @Override
            public void button_Click(View v) {
                LogUtils.i("tv1被点击了");
                mTv1.setType(1);
                mApplyForActivity_p.initClick(mTv1);
            }

        }).setTextColor(getResources().getColor(R.color.home_tv1))
                .setTextSmallColor(getResources().getColor(R.color.home_tv1))
                .setTopDrawable(R.drawable.home_tv01)
                .setText("个人信息")
                .setTextSmall("a")
        ;

        mTv2.setCallBack(new button_CallBack() {

            @Override
            public void button_Click(View v) {
                LogUtils.i("tv2被点击了");
                mTv2.setType(2);
                mApplyForActivity_p.initClick(mTv2);
            }

        }).setTextColor(getResources().getColor(R.color.home_tv2))
                .setTextSmallColor(getResources().getColor(R.color.home_tv2))
                .setTopDrawable(R.drawable.home_tv02)
                .setText("家庭住址")
                .setTextSmall("b")
        ;

        mTv3.setCallBack(new button_CallBack() {

            @Override
            public void button_Click(View v) {
                LogUtils.i("tv3被点击了");
                mTv3.setType(3);
                mApplyForActivity_p.initClick(mTv3);
            }

        }).setTextColor(getResources().getColor(R.color.home_tv3))
                .setTextSmallColor(getResources().getColor(R.color.home_tv3))
                .setTopDrawable(R.drawable.home_tv03)
                .setText("银行卡信息")
                .setTextSmall("c")
        ;

        mTv4.setCallBack(new button_CallBack() {

            @Override
            public void button_Click(View v) {
                LogUtils.i("tv4被点击了");
                mTv4.setType(4);
                mApplyForActivity_p.initClick(mTv4);
            }

        }).setTextColor(getResources().getColor(R.color.home_tv4))
                .setTextSmallColor(getResources().getColor(R.color.home_tv4))
                .setTopDrawable(R.drawable.home_tv04)
                .setText("完善资料")
                .setTextSmall("d")
        ;




    }


    @Override
    public void initP() {
        mApplyForActivity_p = ApplyForActivity_P.getApplyForActivity_P(this);
        mApplyForActivity_p.setCallBack(this);
    }

    @Override
    public void initData() {
        mApplyForActivity_p.getCallBackData();

    }

    @Override
    public void initListener() {

    }

    /**
     * 用于显示
     *
     * @param getInfo
     */
    @Override
    public void setCallBackData(GetInfo getInfo) {

    }

    /**
     * 关掉界面
     */
    @Override
    public void closeActivity() {
        finish();
    }
}
