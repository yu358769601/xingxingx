package com.xinxinxuedai.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinxinxuedai.MVP.RegisterActivity.RegisterActivity_P;
import com.xinxinxuedai.MVP.RegisterActivity.countTime.Register_countTime_P;
import com.xinxinxuedai.MVP.RegisterActivity.countTime.Register_countTime_P_CallBack;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.view.initAction_Bar;

import static com.xinxinxuedai.R.id.register_cunttime;

//注册activity  重置密码activity
public class RegisterActivity extends BaseActivity implements View.OnClickListener, Register_countTime_P_CallBack {
    public static final int REGISTERCLASS = 100;
    public static final int AGAINCLASS = 200;

    //当前类的状态
    private int mClasstag;
    private TextView mRegister_cunttime;
    private RegisterActivity_P mRegisterActivity_p;
    private initAction_Bar mRelativeLayout_title;
    private InnerReceiver1 mReceiver1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initReceiver();
        getbox();
        initP();
        initView();
    }



    private void getbox() {
        Bundle extras = getIntent().getExtras();
        mClasstag = extras.getInt("classtag");

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver1);

    }

    private void initReceiver() {
        //注册广播接收者"countTime","count"
        IntentFilter filter = new IntentFilter("countTime");
        mReceiver1 = new InnerReceiver1();
        registerReceiver(mReceiver1, filter);
    }
    public class InnerReceiver1 extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            String count = intent.getStringExtra("count");
            int i = Integer.parseInt(count);
            if (i<1){
                mRegister_cunttime.setText("发送验证码");
                mRegister_cunttime.setEnabled(true);
            }else{
                mRegister_cunttime.setText(count);
                mRegister_cunttime.setEnabled(false);
            }
        }
    }


    @Override
    public void getCountTimeStatus(int status) {
        LogUtils.i("倒计时状态"+status);
        switch (status){
            case Register_countTime_P.STOP:
            LogUtils.i("倒计时结束");
            break;
            case Register_countTime_P.RUNING:
                LogUtils.i("倒计时进行中");
            break;
        }
    }





    @Override
    public int getlayouXML() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        mRegister_cunttime = (TextView) findViewById(register_cunttime);
        mRegister_cunttime.setOnClickListener(this);
        if (Register_countTime_P.status == Register_countTime_P.RUNING ){
            mRegister_cunttime.setText(Register_countTime_P.numCount+"");
            mRegister_cunttime.setEnabled(false);
        } else if (Register_countTime_P.status == Register_countTime_P.STOP){
            mRegister_cunttime.setEnabled(true);
        }

        mRelativeLayout_title = (initAction_Bar) findViewById(R.id.relativeLayout_title);
        mRelativeLayout_title.setCallBack(new initAction_Bar.Action_bar_call_back() {
            @Override
            public void getAction_barView_backbutton(Button button) {

            }

            @Override
            public void getAction_barView_title(TextView textView) {
                textView.setText("注册界面");
            }
        });
    }

    @Override
    public void initP() {
        mRegisterActivity_p = RegisterActivity_P.getLoginActivity_P(this);
        Register_countTime_P login_countTime_p = Register_countTime_P.getLogin_countTime_P(this);
        login_countTime_p.setCallBack(this);
        LogUtils.i("注册页面的对象"+login_countTime_p);
        if (mClasstag == REGISTERCLASS){

        }else if(mClasstag == AGAINCLASS){

        }

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
            case register_cunttime:
                v.setTag(1);

                mRegisterActivity_p.onClick(v);
                mRegister_cunttime.setEnabled(false);
            break;
        }
    }
}
