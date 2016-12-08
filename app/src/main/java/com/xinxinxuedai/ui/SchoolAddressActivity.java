package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxinxuedai.R;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.view.initAction_Bar;

//学校地址activity
public class SchoolAddressActivity extends BaseActivity implements View.OnClickListener {

    private TextView schooladdress_tv;
    private EditText schooladdress_et_1;
    private EditText schooladdress_et_2;
    private TextView schooladdress_tv2;
    private RelativeLayout schooladdress_rl;
    private initAction_Bar relativeLayout_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_school_address;
    }

    @Override
    public void initView() {

        schooladdress_tv = (TextView) findViewById(R.id.schooladdress_tv);
        schooladdress_tv.setOnClickListener(this);

        schooladdress_et_1 = (EditText) findViewById(R.id.schooladdress_et_1);
        schooladdress_et_1.setOnClickListener(this);

        schooladdress_et_2 = (EditText) findViewById(R.id.schooladdress_et_2);
        schooladdress_et_2.setOnClickListener(this);

        schooladdress_tv2 = (TextView) findViewById(R.id.schooladdress_tv2);
        schooladdress_tv2.setOnClickListener(this);

        schooladdress_rl = (RelativeLayout) findViewById(R.id.schooladdress_rl);
        schooladdress_rl.setOnClickListener(this);

        relativeLayout_title = (initAction_Bar) findViewById(R.id.relativeLayout_title);
        relativeLayout_title.setCallBack(new initAction_Bar.Action_bar_call_back() {
            @Override
            public void getAction_barView_backbutton(Button button) {

            }

            @Override
            public void getAction_barView_title(TextView textView) {
                textView.setText("选择地区");
            }
        });
    }

    @Override
    public void initP() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }
}
