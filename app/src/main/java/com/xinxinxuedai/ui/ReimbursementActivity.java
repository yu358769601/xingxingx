package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxinxuedai.MVP.ReimbursementActivity.ReimbursementActivity_C;
import com.xinxinxuedai.MVP.ReimbursementActivity.ReimbursementActivity_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.view.initAction_Bar;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton_2;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton_3;
import com.xinxinxuedai.view.xuedai_button.button_CallBack;

//我要还款activity
public class ReimbursementActivity extends BaseActivity implements View.OnClickListener, ReimbursementActivity_C {

    private initAction_Bar mRelativeLayout_title;
    private TextView reimbursement_tv_hint;
    private TextView reimbursement_tv_logo;
    private XueDaiButton_2 reimbursement_xdb_1;
    private XueDaiButton_3 reimbursement_xdb_2;
    private XueDaiButton_3 reimbursement_xdb_3;
    private RelativeLayout reimbursement_rl;
    private initAction_Bar relativeLayout_title;
    private ReimbursementActivity_P mReimbursementActivity_p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initP();
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_reimbursement;
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
                textView.setText("我要还款");
            }
        });


        reimbursement_tv_hint = (TextView) findViewById(R.id.reimbursement_tv_hint);
        reimbursement_tv_hint.setOnClickListener(this);

        reimbursement_tv_logo = (TextView) findViewById(R.id.reimbursement_tv_logo);
        reimbursement_tv_logo.setOnClickListener(this);

        reimbursement_xdb_1 = (XueDaiButton_2) findViewById(R.id.reimbursement_xdb_1);
        reimbursement_xdb_1.setOnClickListener(this);

        reimbursement_xdb_2 = (XueDaiButton_3) findViewById(R.id.reimbursement_xdb_2);
        reimbursement_xdb_2.setOnClickListener(this);

        reimbursement_xdb_3 = (XueDaiButton_3) findViewById(R.id.reimbursement_xdb_3);
        reimbursement_xdb_3.setOnClickListener(this);

        reimbursement_rl = (RelativeLayout) findViewById(R.id.reimbursement_rl);
        reimbursement_rl.setOnClickListener(this);

        initData();
    }

    @Override
    public void initP() {
        mReimbursementActivity_p = ReimbursementActivity_P.getReimbursementActivity_p(AppContext.getApplication());
        mReimbursementActivity_p.setCallBack(this);
    }

    @Override
    public void initData() {
        reimbursement_xdb_1.setCallBack(new button_CallBack() {
            @Override
            public void button_Click() {
                UtilsToast.showToast(AppContext.getApplication(),"充值");

            }
        });

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reimbursement_xdb_1:
                mReimbursementActivity_p.topUp();
                break;
            case R.id.reimbursement_xdb_2:

                break;
            case R.id.reimbursement_xdb_3:

                break;
        }
    }
}
