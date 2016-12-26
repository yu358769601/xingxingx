package com.xinxinxuedai.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxinxuedai.MVP.LoginActivity.LoginActivity_C;
import com.xinxinxuedai.MVP.LoginActivity.LoginActivity_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.UtilsMyText;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.view.initAction_Bar;

import java.util.ArrayList;

//登录activity
public class LoginActivity extends BaseActivity implements View.OnClickListener ,LoginActivity_C {


    private initAction_Bar mRelativeLayout_title;
    private ImageView register_iv;
    private EditText login_et_phone_num;
    private View view_1;
    private EditText login_et_passworld;
    private TextView login_tv_login;
    private TextView login_tv_forget_the_password;
    private TextView login_tv_register;
    private RelativeLayout login_rl;
    private initAction_Bar relativeLayout_title;
    private LoginActivity_P mLoginActivity_p;


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
        mRelativeLayout_title.setBack(false);
        mRelativeLayout_title.setCallBack(new initAction_Bar.Action_bar_call_back() {
            @Override
            public void getAction_barView_backbutton(Button button) {

            }

            @Override
            public void getAction_barView_title(TextView textView) {
                textView.setText("登录");
            }
        });


        register_iv = (ImageView) findViewById(R.id.register_iv);
        register_iv.setOnClickListener(this);
        login_et_phone_num = (EditText) findViewById(R.id.login_et_phone_num);
        view_1 = (View) findViewById(R.id.view_1);
        login_et_passworld = (EditText) findViewById(R.id.login_et_passworld);
        //登陆按钮
        login_tv_login = (TextView) findViewById(R.id.login_tv_login);
        login_tv_login.setOnClickListener(this);
        //忘记密码
        login_tv_forget_the_password = (TextView) findViewById(R.id.login_tv_forget_the_password);
        login_tv_forget_the_password.setOnClickListener(this);
//        login_tv_register = (TextView) findViewById(R.id.login_tv_register);
//        login_tv_register.setOnClickListener(this);
        login_rl = (RelativeLayout) findViewById(R.id.login_rl);
        login_rl.setOnClickListener(this);
        //点了注册
        login_tv_register = (TextView) findViewById(R.id.login_tv_register);
        login_tv_register.setOnClickListener(this);

        initListener();
    }

    @Override
    public void initP() {
        mLoginActivity_p = LoginActivity_P.getLoginActivity_p(AppContext.getApplication());
        mLoginActivity_p.setCallBack(this);
    }
    @Override
    public void onBackPressed() {
        //按不了返回键
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
            //点了登陆
            case R.id.login_tv_login:
                v.setTag(1);
                mLoginActivity_p.Clicks(v);
            break;
            //点了忘记密码
            case R.id.login_tv_forget_the_password:
                v.setTag(2);
                mLoginActivity_p.Clicks(v);
            break;
            //点了注册
            case R.id.login_tv_register:
                Intent intent = new Intent(AppContext.getApplication(), RegisterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("classtag",RegisterActivity.REGISTERCLASS);
                bundle.putString("phoneNum",UtilsMyText.getTextView(login_et_phone_num));
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }

    /**
     * 在控制器里面点了什么按钮
     *
     * @param view
     */
    @Override
    public void callBackButton(View view) {
        switch ((int)view.getTag()){
            case 1:
                ArrayList<EditText> editTexts = new ArrayList<>();
                login_et_phone_num.setTag(1);
                login_et_passworld.setTag(2);
                editTexts.add(login_et_phone_num);
                editTexts.add(login_et_passworld);
                mLoginActivity_p.setEditTextViews(editTexts);
            break;
            case 2:

                Intent intent = new Intent();
                intent.setClass(AppContext.getApplication(),RegisterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("phoneNum",UtilsMyText.getTextView(login_et_phone_num));
                bundle.putInt("classtag",RegisterActivity.AGAINCLASS);
                intent.putExtras(bundle);
                startActivity(intent);
                closeActivity();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dump();
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
        mLoginActivity_p = null;
         mRelativeLayout_title= null;
         register_iv= null;
         login_et_phone_num= null;
         view_1= null;
         login_et_passworld= null;
         login_tv_login= null;
        login_tv_forget_the_password= null;
         login_tv_register= null;
         login_rl= null;
          relativeLayout_title= null;
          mLoginActivity_p= null;
    }
}
