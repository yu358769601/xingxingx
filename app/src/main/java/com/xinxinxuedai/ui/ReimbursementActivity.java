package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.xinxinxuedai.MVP.ReimbursementActivity.ReimbursementActivity_C;
import com.xinxinxuedai.MVP.ReimbursementActivity.ReimbursementActivity_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.view.MyListView;
import com.xinxinxuedai.view.initAction_Bar;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton_2;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton_3;
import com.xinxinxuedai.view.xuedai_button.button_CallBack;

import java.net.HttpURLConnection;

//我要还款activity
public class ReimbursementActivity extends BaseActivity implements View.OnClickListener, ReimbursementActivity_C {
    HttpURLConnection httpURLConnection;
    private initAction_Bar mRelativeLayout_title;
    private TextView reimbursement_tv_hint;
    private TextView reimbursement_tv_logo;
    private XueDaiButton_2 reimbursement_xdb_1;
    private XueDaiButton_3 reimbursement_xdb_2;
    private XueDaiButton_3 reimbursement_xdb_3;
    private RelativeLayout reimbursement_rl;
    private initAction_Bar relativeLayout_title;
    private ReimbursementActivity_P mReimbursementActivity_p;
    private MyListView mReimbursement_lv;
    private XRefreshView xrefreshview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initP();
        initView();
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

        //reimbursement_xdb_1.setOnClickListener(this);

//        reimbursement_xdb_2 = (XueDaiButton_3) findViewById(R.id.reimbursement_xdb_2);
//        reimbursement_xdb_2.setOnClickListener(this);
//
//        reimbursement_xdb_3 = (XueDaiButton_3) findViewById(R.id.reimbursement_xdb_3);
//        reimbursement_xdb_3.setOnClickListener(this);

        reimbursement_rl = (RelativeLayout) findViewById(R.id.reimbursement_rl);
        reimbursement_rl.setOnClickListener(this);
        //listView
        mReimbursement_lv = (MyListView) findViewById(R.id.reimbursement_lv);
        xrefreshview = (XRefreshView) findViewById(R.id.xrefreshview);

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
            public void button_Click(View v) {
                // UtilsToast.showToast(AppContext.getApplication(),"充值");
                switch ((int) v.getTag()) {
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:
                        UtilsToast.showToast(AppContext.getApplication(), "充值");
                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:
                        UtilsToast.showToast(AppContext.getApplication(), "点到了左面");
                        break;
                    case 7:
                        UtilsToast.showToast(AppContext.getApplication(), "点到了右面");
                        break;
                }
            }
        });

        //获取网络添加列表数据
        mReimbursementActivity_p.initListViewData(mReimbursement_lv);


        mReimbursementActivity_p.initRefurbish(xrefreshview);


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

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null!=httpURLConnection){
            //断开连接
            httpURLConnection.disconnect();
        }
    }

    /**
     * 获取网络请求
     *
     * @param httpURLConnection
     */
    @Override
    public void getNetRequest(HttpURLConnection httpURLConnection) {
        this.httpURLConnection = httpURLConnection;
    }
}
