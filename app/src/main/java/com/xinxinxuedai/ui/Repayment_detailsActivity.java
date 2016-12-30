package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinxinxuedai.MVP.Repayment_detailsActivity.contract.Repayment_detailsActivity_mvpContract;
import com.xinxinxuedai.MVP.Repayment_detailsActivity.presenter.Repayment_detailsActivity_mvpPresenter;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.bean.RepaymentList;
import com.xinxinxuedai.view.MyRadioGroup;
import com.xinxinxuedai.view.initAction_Bar;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton_3;
import com.xinxinxuedai.view.xuedai_button.button_CallBack;

/**
 * 还款详情activity
 */
public class Repayment_detailsActivity extends BaseActivity implements View.OnClickListener , Repayment_detailsActivity_mvpContract.View, button_CallBack {

    private XueDaiButton_3 repayment_details_xd3;
    private TextView repayment_details_tv;
    private MyRadioGroup repayment_details_rg;
    private initAction_Bar relativeLayout_title;
    private Repayment_detailsActivity_mvpPresenter mPresenter;
    private LinearLayout repayment_details_ll;
    private RepaymentList.DataBean mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBox();
        initP();
        initView();
    }

    private void getBox() {
        Bundle extras = getIntent().getExtras();
        if (null!=extras){
            mDataList = (RepaymentList.DataBean) extras.getSerializable("dataList");

        }
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_repayment_details;
    }

    @Override
    public void initView() {

        repayment_details_xd3 = (XueDaiButton_3) findViewById(R.id.repayment_details_xd3);
        repayment_details_xd3.setOnClickListener(this);
        repayment_details_tv = (TextView) findViewById(R.id.repayment_details_tv);
        repayment_details_tv.setOnClickListener(this);
        repayment_details_ll = (LinearLayout) findViewById(R.id.repayment_details_ll);
        repayment_details_ll.setOnClickListener(this);
        relativeLayout_title = (initAction_Bar) findViewById(R.id.relativeLayout_title);
        relativeLayout_title.setOnClickListener(this);

        repayment_details_xd3.setCallBack(this);
        initData();
    }

    @Override
    public void initP() {
        mPresenter = new Repayment_detailsActivity_mvpPresenter(AppContext.getApplication(), this);

    }

    @Override
    public void initData() {
        mPresenter.getNetData(repayment_details_ll);
        if (null!=mDataList){
            //应该还款的
            double real_money = mDataList.real_money;
            //已还金额
            double money = mDataList.money;
            //计划还款日
            String plan_date = mDataList.plan_date;
            repayment_details_xd3
                    .setTv1(money+"元")
                    .setTv2(real_money+"元")
                    .setTv4(plan_date);
        }

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.repayment_details_xd3:

                break;
        }
    }

    @Override
    public void button_Click(View v) {
        switch ((int)v.getTag()){
            case 7:
                LogUtils.i("点了还款详情里面的 还款按钮");
            break;
        }

    }

    @Override
    public void CallBack(double v) {
       // double xuedai_button3_tv1 = repayment_details_xd3.getXuedai_button3_tv1();
        repayment_details_xd3.setTv1(mDataList.money-v+"元");
    }
}
