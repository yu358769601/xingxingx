package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.xinxinxuedai.MVP.LoanProductsActivity.LoanProductsActivity_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.view.initAction_Bar;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton_1;
import com.xinxinxuedai.view.xuedai_button.button_CallBack;

//借贷产品activity
public class LoanProductsActivity extends BaseActivity {

    private initAction_Bar mRelativeLayout_title;
    private LoanProductsActivity_P mLoanProductsActivity_p;
    private XueDaiButton_1 mXuedaibutton_1;
    private XueDaiButton_1 mXuedaibutton_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initP();
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_loan_products;
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
                textView.setText("借贷产品");
            }
        });
        //A计划
        mXuedaibutton_1 = (XueDaiButton_1) findViewById(R.id.xuedaibutton_1);
        mXuedaibutton_1.setCallBack(new button_CallBack() {
            @Override
            public void button_Click() {
                mXuedaibutton_1.setTag(1);
                mLoanProductsActivity_p.onclicks(mXuedaibutton_1);
            }
        }).setTextColor(getResources().getColor(R.color.home_tv1))
                .setStar(true)
//                .setText_info("我是注释")
                .setText_infoColor(getResources().getColor(R.color.home_tv1))
                .setTopDrawable(R.drawable.home_tv01)
                .setText("先本后息");
        //B计划
        mXuedaibutton_2 = (XueDaiButton_1) findViewById(R.id.xuedaibutton_2);
        mXuedaibutton_2.setCallBack(new button_CallBack() {
            @Override
            public void button_Click() {
                mXuedaibutton_2.setTag(2);
                mLoanProductsActivity_p.onclicks(mXuedaibutton_2);
            }
        }).setTextColor(getResources().getColor(R.color.home_tv1))
                .setStar(true)
                .setText_info("适用于中长期借款\n借款金额10000元以下")
                .setText_infoColor(getResources().getColor(R.color.home_tv1))
                .setTopDrawable(R.drawable.home_tv01)
                .setText("等额本息");


    }
    @Override
    public void initP() {
        mLoanProductsActivity_p = LoanProductsActivity_P.getLoanProductsActivity_P(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
