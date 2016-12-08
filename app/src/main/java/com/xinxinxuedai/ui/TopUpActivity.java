package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xinxinxuedai.MVP.TopUpActivity.TopUpActivity_C;
import com.xinxinxuedai.MVP.TopUpActivity.TopUpActivity_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsDialog;
import com.xinxinxuedai.Utils.UtilsToast;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.view.initAction_Bar;

import java.util.ArrayList;

//账户充值activity
public class TopUpActivity extends BaseActivity implements View.OnClickListener, TopUpActivity_C
{

    private TextView topup_title;
    private View View1;
    private TextView topup_tv1;
    private EditText topup_ed1;
    private LinearLayout topup_ll1;
    private View View2;
    private TextView topup_tv2;
    private EditText topup_ed2;
    private LinearLayout topup_ll2;
    private View View3;
    private TextView topup_tv3;
    private EditText topup_ed3;
    private LinearLayout topup_ll3;
    private View View4;
    private TextView topup_tv4;
    private EditText topup_ed4;
    private LinearLayout topup_ll4;
    private RelativeLayout topup_rl;
    private TextView topup_tv_sub;
    private TextView topup_tv_info;
    private initAction_Bar relativeLayout_title;
    private TopUpActivity_P mTopUpActivity_p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initP();
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_top_up;
    }

    @Override
    public void initView() {

        topup_title = (TextView) findViewById(R.id.topup_title);
        topup_title.setOnClickListener(this);

        topup_tv1 = (TextView) findViewById(R.id.topup_tv1);
        topup_tv1.setOnClickListener(this);

//        topup_ed1 = (EditText) findViewById(R.id.topup_ed1);
//        topup_ed1.setOnClickListener(this);

        topup_ll1 = (LinearLayout) findViewById(R.id.topup_ll1);
        topup_ll1.setOnClickListener(this);

        topup_tv2 = (TextView) findViewById(R.id.topup_tv2);
        topup_tv2.setOnClickListener(this);

        topup_ed2 = (EditText) findViewById(R.id.topup_ed2);
        topup_ed2.setOnClickListener(this);

        topup_ll2 = (LinearLayout) findViewById(R.id.topup_ll2);
        topup_ll2.setOnClickListener(this);

        topup_tv3 = (TextView) findViewById(R.id.topup_tv3);
        topup_tv3.setOnClickListener(this);

        topup_ed3 = (EditText) findViewById(R.id.topup_ed3);
        topup_ed3.setOnClickListener(this);

        topup_ll3 = (LinearLayout) findViewById(R.id.topup_ll3);
        topup_ll3.setOnClickListener(this);

        topup_tv4 = (TextView) findViewById(R.id.topup_tv4);
        topup_tv4.setOnClickListener(this);

        topup_ed4 = (EditText) findViewById(R.id.topup_ed4);
        topup_ed4.setOnClickListener(this);

        topup_ll4 = (LinearLayout) findViewById(R.id.topup_ll4);
        topup_ll4.setOnClickListener(this);

        topup_rl = (RelativeLayout) findViewById(R.id.topup_rl);
        topup_rl.setOnClickListener(this);

        topup_tv_sub = (TextView) findViewById(R.id.topup_tv_sub);
        topup_tv_sub.setOnClickListener(this);

        topup_tv_info = (TextView) findViewById(R.id.topup_tv_info);
        topup_tv_info.setOnClickListener(this);

        relativeLayout_title = (initAction_Bar) findViewById(R.id.relativeLayout_title);

        relativeLayout_title.setCallBack(new initAction_Bar.Action_bar_call_back() {
            @Override
            public void getAction_barView_backbutton(Button button) {

            }

            @Override
            public void getAction_barView_title(TextView textView) {
                textView.setText("账户充值");
            }
        });
    }

    @Override
    public void initP() {
        mTopUpActivity_p = TopUpActivity_P.getTopUpActivity_p(AppContext.getApplication());
        mTopUpActivity_p.setCallBack(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    private void submit() {
        // validate
        String ed1 = topup_ed1.getText().toString().trim();
        if (TextUtils.isEmpty(ed1)) {
            Toast.makeText(this, "请输入金额", Toast.LENGTH_SHORT).show();
            return;
        }

        String ed2 = topup_ed2.getText().toString().trim();
        if (TextUtils.isEmpty(ed2)) {
            Toast.makeText(this, "请输入卡号", Toast.LENGTH_SHORT).show();
            return;
        }

        String ed3 = topup_ed3.getText().toString().trim();
        if (TextUtils.isEmpty(ed3)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        String ed4 = topup_ed4.getText().toString().trim();
        if (TextUtils.isEmpty(ed4)) {
            Toast.makeText(this, "请输入身份证", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.topup_tv_sub:
                ArrayList<EditText> editTexts = new ArrayList<>();
                topup_ed1.setTag(1);
                topup_ed2.setTag(2);
                topup_ed3.setTag(3);
                topup_ed4.setTag(4);
                editTexts.add(topup_ed1);
                editTexts.add(topup_ed2);
                editTexts.add(topup_ed3);
                editTexts.add(topup_ed4);
                mTopUpActivity_p.setEditTextViews(editTexts);
            break;
            //点了 支持的银行
            case R.id.topup_tv_info:
                ArrayList<String> strings = new ArrayList<>();
               // topup_ed1.setTag(1);
                strings.add("花果山银行");
                strings.add("锦州银行");
                strings.add("内蒙古银行");
                strings.add("花果山银行");
                strings.add("锦州银行");
                strings.add("内蒙古银行");
                strings.add("花果山银行");
                strings.add("锦州银行");
                strings.add("内蒙古银行");
                strings.add("花果山银行");
                strings.add("锦州银行");
                strings.add("内蒙古银行");
                strings.add("花果山银行");
                strings.add("锦州银行");
                strings.add("内蒙古银行");
                strings.add("花果山银行");
                strings.add("锦州银行");
                strings.add("内蒙古银行");
                UtilsDialog.showDialogLinerLayout(this,"支持的银行有",strings);
            break;
        }
    }


    /**
     * 网络返回数据
     *
     * @param s
     */
    @Override
    public void getData(String s) {
        UtilsToast.showToast(AppContext.getApplication(),s);
    }
}
