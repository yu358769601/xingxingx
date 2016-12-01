package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.xinxinxuedai.R;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.view.initAction_Bar;

//登录页activity
public class LoginActivity extends BaseActivity {



    private initAction_Bar mRelativeLayout_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initP();
        initView();
    }


    @Override
    public int getlayouXML() {
        return R.layout.activity_login;
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
                    textView.setText("登陆");
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
}
