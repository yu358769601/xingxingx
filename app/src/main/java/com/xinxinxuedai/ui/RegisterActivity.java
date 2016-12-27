package com.xinxinxuedai.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.text.method.NumberKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xinxinxuedai.MVP.RegisterActivity.RegisterActivity_C;
import com.xinxinxuedai.MVP.RegisterActivity.RegisterActivity_P;
import com.xinxinxuedai.MVP.RegisterActivity.countTime.Register_countTime_P;
import com.xinxinxuedai.MVP.RegisterActivity.countTime.Register_countTime_P_CallBack;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsMyText;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.app.Share;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.view.initAction_Bar;

import java.util.ArrayList;


//注册activity  重置密码activity
public class RegisterActivity extends BaseActivity implements View.OnClickListener, Register_countTime_P_CallBack, RegisterActivity_C {
    //注册
    public static final int REGISTERCLASS = 100;
    //重置
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
    private EditText register_et_phonenum;
    private EditText register_et_cunttime;
    private String mPhoneNum;

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
        mPhoneNum = extras.getString("phoneNum");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver1);
        dump();
    }

    private void initReceiver() {
        //注册广播接收者"countTime","count"
        IntentFilter filter = new IntentFilter("countTime");
        mReceiver1 = new InnerReceiver1();
        registerReceiver(mReceiver1, filter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Share.checkLogin(AppContext.getApplication())){
            closeActivity();
        }
    }

    /**
     * 关掉界面
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
          mRegister_cunttime = null;
          mRegisterActivity_p= null;
          mRelativeLayout_title= null;
          mReceiver1= null;
          mRegister_ok_= null;
          mRegister_ok_again= null;
          mRegister_et_passwrold= null;
          mRegister_et_passwrold_ok= null;
          register_et_phonenum= null;
          register_et_cunttime= null;
          mPhoneNum= null;
    }


    public class InnerReceiver1 extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            String count = intent.getStringExtra("count");
            int i = Integer.parseInt(count);
            if (i < 1) {
                mRegister_cunttime.setText("发送验证码");
                mRegister_cunttime.setEnabled(true);
            } else {
                mRegister_cunttime.setText(count);
                mRegister_cunttime.setEnabled(false);
            }
        }
    }


    @Override
    public void getCountTimeStatus(int status) {
        LogUtils.i("倒计时状态" + status);
        switch (status) {
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
        mRegister_cunttime = (TextView) findViewById(R.id.register_cunttime);
        mRegister_cunttime.setOnClickListener(this);
        if (Register_countTime_P.status == Register_countTime_P.RUNING) {
            mRegister_cunttime.setText(Register_countTime_P.numCount + "");
            mRegister_cunttime.setEnabled(false);
        } else if (Register_countTime_P.status == Register_countTime_P.STOP) {
            mRegister_cunttime.setEnabled(true);
        }

        mRelativeLayout_title = (initAction_Bar) findViewById(R.id.relativeLayout_title);
        mRelativeLayout_title.setCallBack(new initAction_Bar.Action_bar_call_back() {
            @Override
            public void getAction_barView_backbutton(Button button) {

            }

            @Override
            public void getAction_barView_title(TextView textView) {
                if (mClasstag == REGISTERCLASS) {
                    textView.setText("注册界面");
                } else if (mClasstag == AGAINCLASS) {
                    textView.setText("重置密码");
                }


            }
        });
        //确定注册(注册页面)
        mRegister_ok_ = (TextView) findViewById(R.id.register_ok_);
        mRegister_ok_.setOnClickListener(this);
        //确定重置(重置页面)
        mRegister_ok_again = (TextView) findViewById(R.id.register_ok_again);
        mRegister_ok_again.setOnClickListener(this);
        //密码
        mRegister_et_passwrold = (EditText) findViewById(R.id.register_et_passwrold);
        // 方法1:建立一个DigitsKeyListener,然后把它设为你的EditText的KeyListener
        DigitsKeyListener numericOnlyListener = new DigitsKeyListener(false,true);
        mRegister_et_passwrold.setKeyListener(numericOnlyListener);
// 方法2:为EditText设置一个NumberKeyListener,然后重写getAcceptedChars()方法和getInputType()方法
        mRegister_et_passwrold.setKeyListener(new NumberKeyListener() {
            @Override
            protected char[] getAcceptedChars() {

                return UtilsMyText.getChar();
            }
            @Override
            public int getInputType() {
                // TODO Auto-generated method stub
                return InputType.TYPE_TEXT_VARIATION_PASSWORD;
            }
        });


        //确定密码
        mRegister_et_passwrold_ok = (EditText) findViewById(R.id.register_et_passwrold_ok);

        DigitsKeyListener numericOnlyListener1 = new DigitsKeyListener(false,true);
        mRegister_et_passwrold_ok.setKeyListener(numericOnlyListener1);
// 方法2:为EditText设置一个NumberKeyListener,然后重写getAcceptedChars()方法和getInputType()方法
        mRegister_et_passwrold_ok.setKeyListener(new NumberKeyListener() {
            @Override
            protected char[] getAcceptedChars() {

                return UtilsMyText.getChar();
            }
            @Override
            public int getInputType() {
                // TODO Auto-generated method stub
                return InputType.TYPE_TEXT_VARIATION_PASSWORD;
            }
        });

        //手机号码
        register_et_phonenum = (EditText) findViewById(R.id.register_et_phonenum);
        register_et_phonenum.setOnClickListener(this);
        //验证码
        register_et_cunttime = (EditText) findViewById(R.id.register_et_cunttime);
        register_et_cunttime.setOnClickListener(this);

        mRegisterActivity_p.setButton(mRegister_cunttime);

        initData();
    }

    @Override
    public void initP() {
        mRegisterActivity_p =new RegisterActivity_P(AppContext.getApplication());
        Register_countTime_P login_countTime_p = Register_countTime_P.getLogin_countTime_P(this);
        mRegisterActivity_p.setStuaus(mClasstag);
        mRegisterActivity_p.setCallBack(this);
        login_countTime_p.setCallBack(this);


    }

    @Override
    public void initData() {
        register_et_phonenum.setText(mPhoneNum);
        if (null!=mPhoneNum){
            register_et_phonenum.setSelection(mPhoneNum.length());
        }

        //如果是 注册页面
        if (mClasstag == REGISTERCLASS) {
            mRegister_ok_.setVisibility(View.VISIBLE);
            mRegister_ok_again.setVisibility(View.INVISIBLE);

            mRegister_et_passwrold.setHint("密码");
            mRegister_et_passwrold_ok.setHint("确认密码");

        } else
            //如果是 重置页面
            if (mClasstag == AGAINCLASS) {
                mRegister_ok_.setVisibility(View.INVISIBLE);
                mRegister_ok_again.setVisibility(View.VISIBLE);

                mRegister_et_passwrold.setHint("新密码");
                mRegister_et_passwrold_ok.setHint("确认新密码");
            }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {
        ArrayList<EditText> editTexts = new ArrayList<>();
        register_et_phonenum.setTag(1);
        register_et_cunttime.setTag(2);
        mRegister_et_passwrold.setTag(3);
        mRegister_et_passwrold_ok.setTag(4);
        editTexts.add(register_et_phonenum);
        editTexts.add(register_et_cunttime);
        editTexts.add(mRegister_et_passwrold);
        editTexts.add(mRegister_et_passwrold_ok);
        switch (v.getId()) {
            //点击了验证码
            case R.id.register_cunttime:
                if (!TextUtils.isEmpty(register_et_phonenum.getText())&&register_et_phonenum.length()==11){
                    v.setTag(R.id.phone_num,register_et_phonenum);
                    mRegisterActivity_p.onClick(v);
                    mRegister_cunttime.setEnabled(false);

                }else{
                    UtilsToast.showToast(this, "电话号码格式不正确");
                    mRegister_cunttime.setEnabled(true);
                }

                break;
            //确定注册
            case R.id.register_ok_:
                v.setTag(2);

                mRegisterActivity_p.setEditTextViews(editTexts,mClasstag);
                mRegisterActivity_p.onClick(v);
                break;
            //确定重置
            case R.id.register_ok_again:
                v.setTag(3);

                mRegisterActivity_p.setEditTextViews(editTexts,mClasstag);
                mRegisterActivity_p.onClick(v);
                break;
        }
    }
}
