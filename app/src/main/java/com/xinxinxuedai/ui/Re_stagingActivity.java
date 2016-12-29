package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinxinxuedai.MVP.Re_stagingActivity.contract.Re_stagingActivity_mvpContract;
import com.xinxinxuedai.MVP.Re_stagingActivity.presenter.Re_stagingActivityPresenter;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsDialog;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsDialogCallBack;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsDialogSelect;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.view.initAction_Bar;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton_4;

import java.util.ArrayList;

/**
 * 申请再分期activity
 *
 */
public class Re_stagingActivity extends BaseActivity implements Re_stagingActivity_mvpContract.View, View.OnClickListener {

    private TextView re_staging_tv1;
    private TextView re_staging_tv2;
    private TextView re_staging_tv3;
    private TextView re_staging_tv4;
    private TextView re_staging_tv5;
    private TextView re_staging_tv6;
    private TextView re_staging_tv7;
    private TextView re_staging_1;
    private XueDaiButton_4 re_staging_info;
    private initAction_Bar relativeLayout_title;
    private Re_stagingActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initP();
        initView();
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_re_staging;
    }

    @Override
    public void initView() {

        re_staging_tv1 = (TextView) findViewById(R.id.re_staging_tv1);
        re_staging_tv1.setOnClickListener(this);
        re_staging_tv2 = (TextView) findViewById(R.id.re_staging_tv2);
        re_staging_tv2.setOnClickListener(this);
        re_staging_tv3 = (TextView) findViewById(R.id.re_staging_tv3);
        re_staging_tv3.setOnClickListener(this);
        re_staging_tv4 = (TextView) findViewById(R.id.re_staging_tv4);
        re_staging_tv4.setOnClickListener(this);
        re_staging_tv5 = (TextView) findViewById(R.id.re_staging_tv5);
        re_staging_tv5.setOnClickListener(this);
        re_staging_tv6 = (TextView) findViewById(R.id.re_staging_tv6);
        re_staging_tv6.setOnClickListener(this);
        re_staging_tv7 = (TextView) findViewById(R.id.re_staging_tv7);
        re_staging_tv7.setOnClickListener(this);
        re_staging_1 = (TextView) findViewById(R.id.re_staging_1);
        re_staging_1.setOnClickListener(this);
        re_staging_info = (XueDaiButton_4) findViewById(R.id.re_staging_info);
        re_staging_info.setOnClickListener(this);
        relativeLayout_title = (initAction_Bar) findViewById(R.id.relativeLayout_title);
        relativeLayout_title.setCallBack(new initAction_Bar.Action_bar_call_back() {
            @Override
            public void getAction_barView_backbutton(Button button) {

            }

            @Override
            public void getAction_barView_title(TextView textView) {
                textView.setText("再分期申请");
            }
        });
    }

    @Override
    public void initP() {
        mPresenter = new Re_stagingActivityPresenter(AppContext.getApplication(), this);
        mPresenter.method(AppContext.getApplication(), this);
        //mPresenter.getData();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.re_staging_1:
                v.setTag(1);
                mPresenter.SetOnClick(v);
                break;
        }
    }
    //当前选择的钱数
    public int mSelectNumMoney;
    //当前选择的天数
    public int mSelectNumDay;
    @Override
    public void getData(final ArrayList<String> list) {
        UtilsDialog.showDialogRadioGroup(this, "是否再分期", list, new UtilsDialogCallBack() {
            @Override
            public void RadioGroupNum(int selectNum, String selectNumInfo) {
                re_staging_1.setText(selectNumInfo+"天");
            }
        }, new UtilsDialogSelect() {
            @Override
            public void selectCallBack(int selectNum) {
                 mSelectNumDay = Integer.parseInt(list.get(selectNum));
                mSelectNumMoney = 1000;
                setText_Info();
            }
        });
    }

    /**
     * 动态改变 显示提示计算  服务费什么的
     */
    private void setText_Info() {
        if (0==mSelectNumMoney || 0==mSelectNumDay){
            return;
        }

        re_staging_info.setData(mSelectNumDay,mSelectNumMoney, AppContext.getApplication(),1);


    }
}
