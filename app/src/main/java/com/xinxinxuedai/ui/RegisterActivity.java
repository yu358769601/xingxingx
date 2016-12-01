package com.xinxinxuedai.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private TextView mRegister_ok_;
    private TextView mRegister_ok_again;
    private EditText mRegister_et_passwrold;
    private EditText mRegister_et_passwrold_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initReceiver();
        getbox();
        initView();
        initP();
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
                if (mClasstag == REGISTERCLASS){
                    textView.setText("注册界面");
                }else if (mClasstag == AGAINCLASS){
                    textView.setText("重置密码");
                }


            }
        });
        //确定注册(注册页面)
        mRegister_ok_ = (TextView) findViewById(R.id.register_ok_);
        //确定重置(重置页面)
        mRegister_ok_again = (TextView) findViewById(R.id.register_ok_again);
        //密码
        mRegister_et_passwrold = (EditText) findViewById(R.id.register_et_passwrold);
        //确定密码
        mRegister_et_passwrold_ok = (EditText) findViewById(R.id.register_et_passwrold_ok);



    }

    @Override
    public void initP() {
        mRegisterActivity_p = RegisterActivity_P.getLoginActivity_P(this);
        Register_countTime_P login_countTime_p = Register_countTime_P.getLogin_countTime_P(this);
        login_countTime_p.setCallBack(this);
        LogUtils.i("注册页面的对象"+login_countTime_p);
        //如果是 注册页面
        if (mClasstag == REGISTERCLASS){
            mRegister_ok_.setVisibility(View.VISIBLE);
            mRegister_ok_again.setVisibility(View.INVISIBLE);

            mRegister_et_passwrold.setHint("密码");
            mRegister_et_passwrold_ok.setHint("确认密码");

        }else
        //如果是 重置页面
        if(mClasstag == AGAINCLASS){
            mRegister_ok_.setVisibility(View.INVISIBLE);
            mRegister_ok_again.setVisibility(View.VISIBLE);

            mRegister_et_passwrold.setHint("新密码");
            mRegister_et_passwrold_ok.setHint("确认新密码");
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
