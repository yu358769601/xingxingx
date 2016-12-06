package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.xinxinxuedai.MVP.ApplicationStatusActivity.ApplicationStatusActivity_C;
import com.xinxinxuedai.MVP.ApplicationStatusActivity.ApplicationStatusActivity_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.bean.ApplicationStatusData;
import com.xinxinxuedai.view.initAction_Bar;

//申请状态activity
public class ApplicationStatusActivity extends BaseActivity implements ApplicationStatusActivity_C {

    private initAction_Bar relativeLayout_title;
    private TextView applicationstatus_tv1;
    private TextView applicationstatus_tv2;
    private TextView applicationstatus_tv3;
    private TextView applicationstatus_tv4;
    private TextView applicationstatus_tv5;
    private ApplicationStatusActivity_P mApplicationStatusActivity_p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initP();
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_application_status;
    }

    @Override
    public void initView() {

        relativeLayout_title = (initAction_Bar) findViewById(R.id.relativeLayout_title);
        relativeLayout_title.setCallBack(new initAction_Bar.Action_bar_call_back() {
            @Override
            public void getAction_barView_backbutton(Button button) {

            }

            @Override
            public void getAction_barView_title(TextView textView) {
                textView.setText("申请状态");
            }
        });


        applicationstatus_tv1 = (TextView) findViewById(R.id.applicationstatus_tv1);
        applicationstatus_tv2 = (TextView) findViewById(R.id.applicationstatus_tv2);
        applicationstatus_tv3 = (TextView) findViewById(R.id.applicationstatus_tv3);
        applicationstatus_tv4 = (TextView) findViewById(R.id.applicationstatus_tv4);
        applicationstatus_tv5 = (TextView) findViewById(R.id.applicationstatus_tv5);


    }

    @Override
    public void initP() {
        mApplicationStatusActivity_p = ApplicationStatusActivity_P.get(AppContext.getApplication());
        mApplicationStatusActivity_p.setCallBack(this);
        //获取网络数据
        mApplicationStatusActivity_p.getData();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    /**
     * 给V的 数据设置
     *
     * @param data
     */
    @Override
    public void setData(ApplicationStatusData data) {
        String applicationstatus_txt1 = this.getResources().getString(R.string.applicationstatus_txt1);
        String applicationstatus_txt2 = this.getResources().getString(R.string.applicationstatus_txt2);
        String applicationstatus_txt3 = this.getResources().getString(R.string.applicationstatus_txt3);
        String applicationstatus_txt4 = this.getResources().getString(R.string.applicationstatus_txt4);
        String applicationstatus_txt5 = this.getResources().getString(R.string.applicationstatus_txt5);

        applicationstatus_tv1.setText(applicationstatus_txt1+data.s1);
        applicationstatus_tv2.setText(applicationstatus_txt2+data.s2);
        applicationstatus_tv3.setText(applicationstatus_txt3+data.s3);
        applicationstatus_tv4.setText(applicationstatus_txt4+data.s4);
        applicationstatus_tv5.setText(applicationstatus_txt5+data.s5);

    }
}
