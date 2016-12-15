package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xinxinxuedai.MVP.LoanApplicationActivity.LoanApplicationActivity_CallBack;
import com.xinxinxuedai.MVP.LoanApplicationActivity.LoanApplicationActivity_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsDialog;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsDialogCallBack;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsDialogSelect;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.bean.GetLoanDetail;
import com.xinxinxuedai.view.dialog.CustomDialog;
import com.xinxinxuedai.view.initAction_Bar;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton_4;

import java.util.ArrayList;

//零用金借款申请activity
public class LoanApplicationActivity extends BaseActivity implements View.OnClickListener, LoanApplicationActivity_CallBack {

    private initAction_Bar mRelativeLayout_title;
    private TextView mLoanapplication_tv;
    private LoanApplicationActivity_P mLoanApplicationActivity_p;
    private TextView mLoanapplication_et_1;
    private TextView mLoanapplication_tv_sub;
    private TextView mLoanapplication_et_2;
    private TextView mLoanapplication_et_3;
    private XueDaiButton_4 mLoanapplication_tv_zhou;
    private EditText mLoanApplication_ed_1;
    private int mClassTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        View viewToLoad = LayoutInflater.from(this.getParent()).inflate(R.layout.activity_loan_application, null);
//        this.setContentView(viewToLoad);
        getBox();
        initP();
        initView();
    }

    private void getBox() {
        Bundle extras = getIntent().getExtras();
        mClassTag = extras.getInt("tag");

    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_loan_application;
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
                textView.setText("零用金借款申请");
            }
        });

        mLoanapplication_tv = (TextView) findViewById(R.id.loanapplication_tv);
        mLoanapplication_tv.setOnClickListener(this);
        mLoanapplication_tv.setVisibility(View.GONE);
        //描述
        mLoanApplication_ed_1 = (EditText) findViewById(R.id.loanApplication_ed_1);

        //第一个
        mLoanapplication_et_1 = (TextView) findViewById(R.id.loanapplication_et_1);
        mLoanapplication_et_1.setOnClickListener(this);
       //第二个
        mLoanapplication_et_2 = (TextView) findViewById(R.id.loanapplication_et_2);
        mLoanapplication_et_2.setOnClickListener(this);
        //第三个
        mLoanapplication_et_3 = (TextView) findViewById(R.id.loanapplication_et_3);
        mLoanapplication_et_3.setOnClickListener(this);
        //点击提交
        mLoanapplication_tv_sub = (TextView) findViewById(R.id.loanapplication_tv_sub);
        mLoanapplication_tv_sub.setOnClickListener(this);

        //下面四周那个
        mLoanapplication_tv_zhou = (XueDaiButton_4) findViewById(R.id.loanapplication_tv_zhou);
        //获取回显数据
        mLoanApplicationActivity_p.getCallBackData();
//        String s = mLoanapplication_et_1.getText().toString();
//        String s2 = mLoanapplication_et_1.getHint().toString();
//        LogUtils.i("测试"+"tv默认的内容是"+s+"提示内容是"+s2);
//        mLoanapplication_et_1.setText("666");
//        String s1 = mLoanapplication_et_1.getText().toString();
//        String s3 = mLoanapplication_et_1.getHint().toString();
//        LogUtils.i("测试"+"tv现在的内容是"+s1+"提示内容是"+s3);

    }

    @Override
    public void initP() {
        mLoanApplicationActivity_p = LoanApplicationActivity_P.getLoanApplicationActivity_P(this);
        mLoanApplicationActivity_p.setCallBack(this);
        mLoanApplicationActivity_p.setClassTag(mClassTag);

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
            //测试
            case R.id.loanapplication_tv:
                v.setTag(99);
                mLoanApplicationActivity_p.clicks(v);
                break;
            case R.id.loanapplication_et_1:
                v.setTag(1);
                mLoanApplicationActivity_p.clicks(v);
                break;
            case R.id.loanapplication_et_2:
                v.setTag(2);
                mLoanApplicationActivity_p.clicks(v);
                break;
            case R.id.loanapplication_et_3:
                v.setTag(3);
                mLoanApplicationActivity_p.clicks(v);
                break;
            case R.id.loanapplication_tv_sub:
                v.setTag(4);
                mLoanApplicationActivity_p.clicks(v);
                ArrayList<TextView> textViews = new ArrayList<>();
                mLoanapplication_et_1.setTag(1);
                mLoanapplication_et_2.setTag(2);
                mLoanapplication_et_3.setTag(3);
                textViews.add(mLoanapplication_et_1);
                textViews.add(mLoanapplication_et_2);
                textViews.add(mLoanapplication_et_3);
                mLoanApplicationActivity_p.request_SetLoanInfo_Request(textViews,mLoanApplication_ed_1);
                break;
        }
    }

//    /**
//     * 从另外一个接口返回的  选择的号码 和 选择号码对应的 内容
//     *
//     * @param selectNum     选择的号码
//     * @param selectNumInfo 对应的选择内容
//     */
//    @Override
//    public void getSelectInfo1(int selectNum, String selectNumInfo) {
//        mLoanapplication_et_1.setText(selectNumInfo);
//    }
//
//    @Override
//    public void getSelectInfo2(int selectNum, String selectNumInfo) {
//        mLoanapplication_et_2.setText(selectNumInfo);
//    }
//
//    @Override
//    public void getSelectInfo3(int selectNum, String selectNumInfo) {
//        mLoanapplication_et_3.setText(selectNumInfo);
//    }

    @Override
    public void getTextInfo4(String TextInfo4) {
        mLoanapplication_tv_zhou.setText_tv1(TextInfo4);
        mLoanapplication_tv_zhou.setText_tv2(TextInfo4);
        mLoanapplication_tv_zhou.setText_tv3(TextInfo4);
        mLoanapplication_tv_zhou.setText_tv4(TextInfo4);

    }

    /**
     * 回调到主界面去show
     *
     * @param strings
     */
    @Override
    public void showDialog1(final ArrayList<String> strings, String title) {
        CustomDialog customDialog = UtilsDialog.showDialogRadioGroup(this, title, strings, new UtilsDialogCallBack() {
            /**
             * 点了确定并且 已经有了选择的号码 之后产生的回调
             *
             * @param selectNum     选择的号码
             * @param selectNumInfo 号码 里面对应的内容
             */
            @Override
            public void RadioGroupNum(int selectNum, String selectNumInfo) {
                //loanApplicationActivity_callBack.getSelectInfo2(selectNum, selectNumInfo);
                mLoanapplication_et_1.setText(selectNumInfo);
            }
        }, new UtilsDialogSelect() {
            @Override
            public void selectCallBack(int selectNum) {
                LogUtils.i("title"+strings.get(selectNum));
            }
        });

    }

    /**
     * 回调到主界面去show
     *
     * @param strings
     */
    @Override
    public void showDialog2(final ArrayList<String> strings, String title) {
        CustomDialog customDialog = UtilsDialog.showDialogRadioGroup(this, title, strings, new UtilsDialogCallBack() {
            /**
             * 点了确定并且 已经有了选择的号码 之后产生的回调
             *
             * @param selectNum     选择的号码
             * @param selectNumInfo 号码 里面对应的内容
             */
            @Override
            public void RadioGroupNum(int selectNum, String selectNumInfo) {
                //loanApplicationActivity_callBack.getSelectInfo2(selectNum, selectNumInfo);
                mLoanapplication_et_2.setText(selectNumInfo);
            }
        }, new UtilsDialogSelect() {
            @Override
            public void selectCallBack(int selectNum) {
                LogUtils.i("title"+strings.get(selectNum));
            }
        });
    }

    /**
     * 回调到主界面去show
     *
     * @param strings
     */
    @Override
    public void showDialog3(final ArrayList<String> strings, String title) {
        CustomDialog customDialog = UtilsDialog.showDialogRadioGroup(this, title, strings, new UtilsDialogCallBack() {
            /**
             * 点了确定并且 已经有了选择的号码 之后产生的回调
             *
             * @param selectNum     选择的号码
             * @param selectNumInfo 号码 里面对应的内容
             */
            @Override
            public void RadioGroupNum(int selectNum, String selectNumInfo) {
                //loanApplicationActivity_callBack.getSelectInfo2(selectNum, selectNumInfo);
                mLoanapplication_et_3.setText(selectNumInfo);
            }
        }, new UtilsDialogSelect() {
            @Override
            public void selectCallBack(int selectNum) {
                LogUtils.i("title"+strings.get(selectNum));
            }
        });
    }

    /**
     * 网络数据回来了设置回显数据
     *
     * @param callBackData
     */
    @Override
    public void setCallBackData(GetLoanDetail callBackData) {
        LogUtils.i("回显数据是什么"+callBackData);

        if (!TextUtils.isEmpty(callBackData.loan_describe))
            mLoanApplication_ed_1.setText(callBackData.loan_describe);
        if (!TextUtils.isEmpty(callBackData.loan_small_describe))
        mLoanapplication_et_1.setText(callBackData.loan_small_describe);
        if (!TextUtils.isEmpty(callBackData.money))
        mLoanapplication_et_2.setText((int) Double.parseDouble(callBackData.money)+"元");
        if (!TextUtils.isEmpty(callBackData.loan_term))
        mLoanapplication_et_3.setText(callBackData.loan_term+"天");

    }


    /**
     * 关掉界面
     */
    @Override
    public void closeActivity() {
        finish();
    }
}
