package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxinxuedai.MVP.PersonalDetailsActivity.PersonalDetailsActivity_C;
import com.xinxinxuedai.MVP.PersonalDetailsActivity.PersonalDetailsActivity_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.bean.GetInfo;
import com.xinxinxuedai.view.initAction_Bar;

import java.util.ArrayList;

//个人信息activity
public class PersonalDetailsActivity extends BaseActivity implements PersonalDetailsActivity_C, View.OnClickListener {
    ArrayList<String> mStrings = new ArrayList<>();
    private initAction_Bar mRelativeLayout_title;
    private PersonalDetailsActivity_P mPersonalDetailsActivity_p;
    private TextView personalpdetails_title;
    private RadioGroup personalpdetails_rg;
    private EditText personalpdetails_et1_name;
    private EditText personalpdetails_et2_name_num;
    private EditText personalpdetails_et3_weixin_num;
    private TextView personalpdetails_tv_address;
    private EditText cancellation_ed;
    private TextView personalpdetails_tv_tellname;
    private EditText personalpdetails_et4_tellname;
    private EditText personalpdetails_et5_tellname_tell;
    private TextView personalpdetails_tv_sub;
    private RelativeLayout personalpdetails_rl;
    private initAction_Bar relativeLayout_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initP();
        initView();
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_personal_details;
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
                textView.setText("个人信息");
            }
        });
        //标题
        personalpdetails_title = (TextView) findViewById(R.id.personalpdetails_title);
        personalpdetails_title.setOnClickListener(this);
        //男生女生的那个
        personalpdetails_rg = (RadioGroup) findViewById(R.id.personalpdetails_rg);
        //真实姓名1
        personalpdetails_et1_name = (EditText) findViewById(R.id.personalpdetails_et1_name);
        personalpdetails_et1_name.setOnClickListener(this);
        //身份证2
        personalpdetails_et2_name_num = (EditText) findViewById(R.id.personalpdetails_et2_name_num);
        personalpdetails_et2_name_num.setOnClickListener(this);
        //微信号码3
        personalpdetails_et3_weixin_num = (EditText) findViewById(R.id.personalpdetails_et3_weixin_num);
        personalpdetails_et3_weixin_num.setOnClickListener(this);
        //
        personalpdetails_tv_address = (TextView) findViewById(R.id.personalpdetails_tv_address);
        personalpdetails_tv_address.setOnClickListener(this);
        //家庭住址4
        cancellation_ed = (EditText) findViewById(R.id.cancellation_ed);
        cancellation_ed.setOnClickListener(this);
        personalpdetails_tv_tellname = (TextView) findViewById(R.id.personalpdetails_tv_tellname);
        personalpdetails_tv_tellname.setOnClickListener(this);
        //紧急联系人姓名
        personalpdetails_et4_tellname = (EditText) findViewById(R.id.personalpdetails_et4_tellname);
        personalpdetails_et4_tellname.setOnClickListener(this);
        //紧急联系人电话
        personalpdetails_et5_tellname_tell = (EditText) findViewById(R.id.personalpdetails_et5_tellname_tell);
        personalpdetails_et5_tellname_tell.setOnClickListener(this);
        //提交按钮
        personalpdetails_tv_sub = (TextView) findViewById(R.id.personalpdetails_tv_sub);
        personalpdetails_tv_sub.setOnClickListener(this);

        personalpdetails_rl = (RelativeLayout) findViewById(R.id.personalpdetails_rl);
        personalpdetails_rl.setOnClickListener(this);

        initRadioButton();



    }

    private void initRadioButton() {
        mStrings.add("我是男生Man");
        mStrings.add("我是女生Gril");
        for (int i = 0; i < personalpdetails_rg.getChildCount(); i++) {
            RadioButton childAt = (RadioButton) personalpdetails_rg.getChildAt(i);
            childAt.setTag(i);
            childAt.setText(mStrings.get(i));
        }
        //获取网络数据
        mPersonalDetailsActivity_p.setCallBackData();
    }

    @Override
    public void initP() {
        mPersonalDetailsActivity_p = PersonalDetailsActivity_P.getmPersonalDetailsActivity_p(this);
        mPersonalDetailsActivity_p.setCallBack(this);
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
            switch (v.getId()){
                case R.id.personalpdetails_tv_sub:
                    if (personalpdetails_rg.getCheckedRadioButtonId()==-1){
                        UtilsToast.showToast(this, "性别按钮没有被选择");
                        return;
                    }
                    int tag = 0;
                    for (int i = 0; i < personalpdetails_rg.getChildCount(); i++) {
                        RadioButton childAt = (RadioButton) personalpdetails_rg.getChildAt(i);
                        if (childAt.isChecked()){
                             tag = (int) childAt.getTag();
                            LogUtils.i("我选的是"+tag);
                            break;
                        }
                    }
                    ArrayList<EditText> editTexts =  new ArrayList<>();

                    personalpdetails_et1_name.setTag(1);
                    editTexts.add(personalpdetails_et1_name);

                    personalpdetails_et2_name_num.setTag(2);
                    editTexts.add(personalpdetails_et2_name_num);

                    personalpdetails_et3_weixin_num.setTag(3);
                    editTexts.add(personalpdetails_et3_weixin_num);

                    cancellation_ed.setTag(4);
                    editTexts.add(cancellation_ed);

                    personalpdetails_et4_tellname.setTag(5);
                    editTexts.add(personalpdetails_et4_tellname);

                    personalpdetails_et5_tellname_tell.setTag(6);
                    editTexts.add(personalpdetails_et5_tellname_tell);

                    mPersonalDetailsActivity_p.getEdtexts(editTexts,tag);

                break;
            }
    }

    /**
     * 设置 个人信息 回显
     *
     * @param getInfo
     */
    @Override
    public void setCallBackData(GetInfo getInfo) {
        //性别
        if (!TextUtils.isEmpty(getInfo.loan_sex)){
            RadioButton childAt1 = (RadioButton) personalpdetails_rg.getChildAt(0);
            RadioButton childAt2 = (RadioButton) personalpdetails_rg.getChildAt(1);
            if ("0".equals(getInfo.loan_sex)) {
                childAt1.setChecked(true);
                childAt2.setChecked(false);
            }
            if ("1".equals(getInfo.loan_sex)){
                childAt1.setChecked(false);
                childAt2.setChecked(true);
            }
        }
        //姓名
        if (!TextUtils.isEmpty(getInfo.loan_realname))
            personalpdetails_et1_name.setText(getInfo.loan_realname);
        //身份证
        if (!TextUtils.isEmpty(getInfo.loan_card_id))
            personalpdetails_et2_name_num.setText(getInfo.loan_card_id);
        //微信号码
        if (!TextUtils.isEmpty(getInfo.loan_weixin))
            personalpdetails_et3_weixin_num.setText(getInfo.loan_weixin);
        //地址
        if (!TextUtils.isEmpty(getInfo.loan_address))
            cancellation_ed.setText(getInfo.loan_address);
        //紧急联系人
        if (!TextUtils.isEmpty(getInfo.loan_parent))
            personalpdetails_et4_tellname.setText(getInfo.loan_parent);
        //紧急联系人电话
        if (!TextUtils.isEmpty(getInfo.loan_parent_mobile))
            personalpdetails_et5_tellname_tell.setText(getInfo.loan_parent_mobile);


    }

    /**
     * 关掉界面
     */
    @Override
    public void closeActivity() {
        finish();
    }
}
