package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinxinxuedai.MVP.LoanProductsActivity.LoanProductsActivity_CallBack;
import com.xinxinxuedai.MVP.LoanProductsActivity.LoanProductsActivity_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.bean.GetLoanDetail;
import com.xinxinxuedai.view.initAction_Bar;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton_1;
import com.xinxinxuedai.view.xuedai_button.button_CallBack;

//借贷产品activity(二选一)
public class LoanProductsActivity extends BaseActivity implements LoanProductsActivity_CallBack {

    private initAction_Bar mRelativeLayout_title;
    private LoanProductsActivity_P mLoanProductsActivity_p;
    private XueDaiButton_1 mXuedaibutton_1;
    private XueDaiButton_1 mXuedaibutton_2;
    private GetLoanDetail mHomeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getBox();
        initP();
        initView();
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_loan_products;
    }
    private void getBox() {
        Bundle extras = getIntent().getExtras();
        mHomeData = (GetLoanDetail) extras.getSerializable("HomeData");

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
            public void button_Click(View v) {
                mXuedaibutton_1.setTag(1);
                mLoanProductsActivity_p.onclicks(mXuedaibutton_1);
            }
        }).setTextColor(getResources().getColor(R.color.home_tv1))
                .setVisbilityStar(true)
//                .setText_info("我是注释")
                .setText_infoColor(getResources().getColor(R.color.home_tv1))
                .setTopDrawable(R.drawable.home_tv01)
                .setText("先本后息");
        //B计划
        mXuedaibutton_2 = (XueDaiButton_1) findViewById(R.id.xuedaibutton_2);
        mXuedaibutton_2.setCallBack(new button_CallBack() {
            @Override
            public void button_Click(View v) {
                mXuedaibutton_2.setTag(2);
                mLoanProductsActivity_p.onclicks(mXuedaibutton_2);
            }
        }).setTextColor(getResources().getColor(R.color.home_tv1))
                .setVisbilityStar(true)
                .setText_info("适用于中长期借款\n借款金额10000元以下")
                .setText_infoColor(getResources().getColor(R.color.home_tv1))
                .setTopDrawable(R.drawable.home_tv01)
                .setText("等额本息");

        initData();
    }
    @Override
    public void initP() {
        mLoanProductsActivity_p = LoanProductsActivity_P.getLoanProductsActivity_P(this);
        mLoanProductsActivity_p.setCallBack(this);

    }

    @Override
    public void initData() {
        mLoanProductsActivity_p.getCallBackData();
        if (null!=mHomeData){
            LogUtils.i("主页有下级页面的数据"+mHomeData);
            setCallBackData(mHomeData);
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dump();
    }

    /**
     * 回调到 设置二选一的 图
     *
     * @param callBackData
     */
    @Override
    public void setCallBackData(GetLoanDetail callBackData) {
        //0先息后本  1等额本息
        if (Integer.parseInt(callBackData.loan_plan)==0){
            //0先息后本
            mXuedaibutton_1.setVisbilityStar(true);
            mXuedaibutton_2.setVisbilityStar(false);
        }else if(Integer.parseInt(callBackData.loan_plan)==1){
            //1等额本息
            mXuedaibutton_1.setVisbilityStar(false);
            mXuedaibutton_2.setVisbilityStar(true);
        }
    }

    /**
     * 关掉当前 activity
     */
    @Override
    public void closeActivity() {
        finish();
    }
    /**
     * 清除的方法
     */
    @Override
    public void dump() {
        mRelativeLayout_title = null;
         mLoanProductsActivity_p= null;
         mXuedaibutton_1= null;
         mXuedaibutton_2= null;
         mHomeData= null;
    }
}
