package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinxinxuedai.MVP.Re_stagingActivity.contract.Re_stagingActivity_mvpContract;
import com.xinxinxuedai.MVP.Re_stagingActivity.presenter.Re_stagingActivityPresenter;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsDialog;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsDialogCallBack;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsDialogSelect;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.bean.GetInfo;
import com.xinxinxuedai.bean.RepaymentList;
import com.xinxinxuedai.view.initAction_Bar;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton_4;

import java.util.ArrayList;

/**
 * 申请再分期activity
 * 再分期申请actviity
 */
public class Re_stagingActivity extends BaseActivity implements Re_stagingActivity_mvpContract.View, View.OnClickListener {
    //当前选择的钱数
    public double mSelectNumMoney;
    //当前选择的天数
    public int mSelectNumDay;
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
    private TextView sub;
    private String mZaiFenQDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBox();
        initView();

        initP();
        initData();
    }

    RepaymentList.DataBean mDataList;

    private void getBox() {
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            mDataList = (RepaymentList.DataBean) extras.getSerializable("zaifenqi");

        }
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

        sub = (TextView) findViewById(R.id.sub);
        sub.setOnClickListener(this);
    }

    @Override
    public void initP() {
        mPresenter = new Re_stagingActivityPresenter(AppContext.getApplication(), this);
        mPresenter.method(AppContext.getApplication(), this);

    }

    public void setText(TextView text, String s) {
        text.setText(text.getHint().toString() + s);
    }

    @Override
    public void initData() {
        mPresenter.getData();

        // mPresenter.setData(mDataList);
       try{
           String plan_date = mDataList.plan_date;
           String real_data = mDataList.real_data;
           int repay_status = mDataList.repay_status;
           double service_fee = mDataList.service_fee;
           double real_money = mDataList.real_money;
           double money = mDataList.money;
//        mDataList.
//        mDataList.                            //还款本金                  还款利息             还款服务费                  违约金
           // String format = String.format("%.2f", (mDataList.money + mDataList.service_fee + mDataList.interest_money + mDataList.weiyue_money));
           String format = String.format("%.2f", (mDataList.benjin));
           double v = Double.parseDouble(format) / 10;
           //本期可以再分期的钱数
           setText(re_staging_tv2, format + "元");
           //再分期费用
           setText(re_staging_tv4, (v) + "元");
           //本期末部分应还金额
           String s = String.format("%.2f", (mDataList.money + mDataList.service_fee + mDataList.interest_money + mDataList.weiyue_money)-mDataList.benjin);
           setText(re_staging_tv5, (s) + "元");
           //开始预览
           mSelectNumMoney = mDataList.benjin;
       }catch (Exception e){
           LogUtils.e(e);
       }



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
            case R.id.sub:
                if (TextUtils.isEmpty(mZaiFenQDay)){
                    UtilsToast.showToast(AppContext.getApplication(),"还未选择天数");
                    return;
                }
                v.setTag(2);
                mPresenter.subClick(mZaiFenQDay,mDataList);
                break;
        }
    }

    @Override
    public void getData(final ArrayList<String> list) {
        UtilsDialog.showDialogRadioGroup(this, "是否再分期", list, new UtilsDialogCallBack() {
            @Override
            public void RadioGroupNum(int selectNum, String selectNumInfo) {
                re_staging_1.setText(selectNumInfo + "天");
                mZaiFenQDay = selectNumInfo;
            }
        }, new UtilsDialogSelect() {
            @Override
            public void selectCallBack(int selectNum) {
                mSelectNumDay = Integer.parseInt(list.get(selectNum));

                setText_Info();
            }
        });
    }

    @Override
    public void InfoData(GetInfo getInfo) {
        //账户余额
        setText(re_staging_tv3, getInfo.loan_money + "元");
                                                //还款本金              还款利息                还款服务费                   违约金
        String format = String.format("%.2f", (mDataList.money + mDataList.service_fee + mDataList.interest_money + mDataList.weiyue_money));
        //手续费
        double money_ = mDataList.money / 10;
        double money= Double.parseDouble(format);

        double v1 = mDataList.service_fee + mDataList.interest_money;
        //真实还款金额
        double real_money = mDataList.real_money;
        double v = v1 + money_;

        //再分期算法  还款本金/10  + 还款利息 + 还款服务费  如果 余额  减去  他们的结果  小于等于0  就不让在分期  反之亦然


        //钱不够
        if ((getInfo.loan_money - v) <= 0) {
           re_staging_tv6.setText("金额不足请充值后在申请");
            sub.setVisibility(View.INVISIBLE);
        } else {
            re_staging_tv6.setText("金额充足可以再分期");
            sub.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void closeActivity() {
        finish();
    }

    /**
     * 动态改变 显示提示计算  服务费什么的
     */
    private void setText_Info() {
        if (0 == mSelectNumMoney || 0 == mSelectNumDay) {
            return;
        }

        re_staging_info.setData(mSelectNumDay, mSelectNumMoney, AppContext.getApplication(), 1);


    }
}
