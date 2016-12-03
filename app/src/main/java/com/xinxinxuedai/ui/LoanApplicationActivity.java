package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinxinxuedai.MVP.LoanApplicationActivity.LoanApplicationActivity_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.view.initAction_Bar;

//零用金借款申请activity
public class LoanApplicationActivity extends BaseActivity implements View.OnClickListener {

    private initAction_Bar mRelativeLayout_title;
    private TextView mLoanapplication_tv;
    private LoanApplicationActivity_P mLoanApplicationActivity_p;
    private TextView mLoanapplication_et_1;
    private TextView mLoanapplication_tv_sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initP();
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_loan_application;
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
                textView.setText("零用金借款申请");
            }
        });

        mLoanapplication_tv = (TextView) findViewById(R.id.loanapplication_tv);
        mLoanapplication_tv.setOnClickListener(this);

        mLoanapplication_et_1 = (TextView) findViewById(R.id.loanapplication_et_1);
        mLoanapplication_et_1.setOnClickListener(this);

        mLoanapplication_tv_sub = (TextView) findViewById(R.id.loanapplication_tv_sub);
        mLoanapplication_tv_sub.setOnClickListener(this);
//        String s = mLoanapplication_et_1.getText().toString();
//        String s2 = mLoanapplication_et_1.getHint().toString();
//        LogUtils.i("测试"+"tv默认的内容是"+s+"提示内容是"+s2);
//        mLoanapplication_et_1.setText("666");
//        String s1 = mLoanapplication_et_1.getText().toString();
//        String s3 = mLoanapplication_et_1.getHint().toString();
//        LogUtils.i("测试"+"tv现在的内容是"+s1+"提示内容是"+s3);

    }

    @Override
    public void initP() {
        mLoanApplicationActivity_p = LoanApplicationActivity_P.getLoanApplicationActivity_P(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loanapplication_tv:
                v.setTag(1);
                mLoanApplicationActivity_p.clicks(v);
                break;
        }
    }
}
