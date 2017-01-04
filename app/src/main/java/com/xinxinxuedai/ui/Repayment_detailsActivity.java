package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinxinxuedai.MVP.Repayment_detailsActivity.contract.Repayment_detailsActivity_mvpContract;
import com.xinxinxuedai.MVP.Repayment_detailsActivity.presenter.Repayment_detailsActivity_mvpPresenter;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsDialog;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.bean.RepaymentList;
import com.xinxinxuedai.view.MyRadioGroup;
import com.xinxinxuedai.view.dialog.DialogCallBack;
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
    private int mPositon;
    private boolean subTag;
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
            mPositon = extras.getInt("positon");
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
        relativeLayout_title.setCallBack(new initAction_Bar.Action_bar_call_back() {
            @Override
            public void getAction_barView_backbutton(Button button) {

            }

            @Override
            public void getAction_barView_title(TextView textView) {
                textView.setText("还款详情");
            }
        });
        initData();
    }

    @Override
    public void initP() {
        mPresenter = new Repayment_detailsActivity_mvpPresenter(AppContext.getApplication(), this);

    }

    @Override
    public void initData() {

        if (null!=mDataList){
            //应该还款的
            double real_money = mDataList.real_money;
            //已还金额
            double money = mDataList.money;
            //加在一起
            double interest_money = mDataList.interest_money;
            //计划还款日
            String plan_date = mDataList.plan_date;
            String format = String.format("%.2f", (mDataList.money + mDataList.service_fee + mDataList.interest_money + mDataList.weiyue_money));
            double v = Double.parseDouble(format);
            repayment_details_xd3
                    .setIv(mDataList.repay_status)
                    .setTv1(format+"元")
                    .setTv2(real_money+"元")
                    .setTv4(plan_date)
                    .setTvfenqi(false);

            mPresenter.setMoney(v);
            LogUtils.i("应该还款"+format);
        }
        mPresenter.getNetData(repayment_details_ll);

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
                //不能提交
                if (subTag){
                    UtilsToast.showToast(AppContext.getApplication(),"你所选择的代金券金额叠加超过还款金额请重新选择");
                    return;
                }

                LogUtils.i("点了还款详情里面的 还款按钮");
            UtilsDialog.showDialog_Text(this, "还款", "是否还款", new DialogCallBack() {
            @Override
            public void confirm() {
                LogUtils.i("我点了还款号码是"+mPositon);
                mPresenter.subHuanKuan(mPositon,mDataList);
                setResult(99);
            }

            @Override
            public void cancel() {
                LogUtils.i("取消还款");
                setResult(100);
            }
        });
            break;
        }

    }

    @Override
    public void CallBack(double v) {
        String format = String.format("%.2f", (mDataList.money + mDataList.service_fee + mDataList.interest_money + mDataList.weiyue_money));
        double v1 = Double.parseDouble(format);
        String v3 = String.format("%.2f", v);
        double v4 = Double.parseDouble(v3);

        double v2=  (v1)- v4;
        String sub = String.format("%.2f", (mDataList.money + mDataList.service_fee + mDataList.interest_money + mDataList.weiyue_money)-v);
        LogUtils.i("我应该还款金额"+format+"选择的代金券"+v3);
        if (v2<=0){
            UtilsToast.showToast(AppContext.getApplication(),"你所选择的代金券金额叠加超过还款金额请重新选择");
            subTag = true;
            return;
        }else{
            subTag = false;
        }
     //   repayment_details_xd3.setTv1((v2)+"元");
        repayment_details_xd3.setTv1(sub+"元");
        //mPresenter.setMoney(v1);


    }

    @Override
    public void closeActivity() {
        finish();

    }
}
