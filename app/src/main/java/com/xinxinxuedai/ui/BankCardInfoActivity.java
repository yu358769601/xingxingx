package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xinxinxuedai.MVP.BankCardInfoActivity.BankCardInfoActivity_C;
import com.xinxinxuedai.MVP.BankCardInfoActivity.BankCardInfoActivity_P;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsDialog;
import com.xinxinxuedai.Utils.UtilsDialog.UtilsDialogCallBack;
import com.xinxinxuedai.Utils.UtilsLoop.UtilsLoopTextView;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.view.initAction_Bar;

import java.util.ArrayList;

//银行卡信息activity
public class BankCardInfoActivity extends BaseActivity implements BankCardInfoActivity_C, View.OnClickListener {

    private initAction_Bar mRelativeLayout_title;
    private BankCardInfoActivity_P mBankCardInfoActivity_p;
    private TextView bank_card_info_tv_title;
    private TextView bank_card_info_tv1_bank;
    private EditText bank_card_info_tv2_open_bank_name;
    private EditText bank_card_info_tv3_bank_card_num;
    private TextView bank_card_info_tv_sub;
    private RelativeLayout bank_card_info_rl;
    private initAction_Bar relativeLayout_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initP();
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_bank_card_info;
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
                textView.setText("银行卡信息");
            }
        });


        bank_card_info_tv_title = (TextView) findViewById(R.id.bank_card_info_tv_title);
        bank_card_info_tv_title.setOnClickListener(this);
        //选择银行卡所属银行
        bank_card_info_tv1_bank = (TextView) findViewById(R.id.bank_card_info_tv1_bank);
        bank_card_info_tv1_bank.setOnClickListener(this);
        //开户行名称
        bank_card_info_tv2_open_bank_name = (EditText) findViewById(R.id.bank_card_info_tv2_open_bank_name);
        bank_card_info_tv2_open_bank_name.setOnClickListener(this);
        //银行卡号
        bank_card_info_tv3_bank_card_num = (EditText) findViewById(R.id.bank_card_info_tv3_bank_card_num);
        bank_card_info_tv3_bank_card_num.setOnClickListener(this);
        //提交按钮
        bank_card_info_tv_sub = (TextView) findViewById(R.id.bank_card_info_tv_sub);
        bank_card_info_tv_sub.setOnClickListener(this);

    }

    @Override
    public void initP() {
        mBankCardInfoActivity_p = BankCardInfoActivity_P.getmBankCardInfoActivity_p(this);
        mBankCardInfoActivity_p.setCallBack(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    private void submit() {
        // validate
        String bank = bank_card_info_tv1_bank.getText().toString().trim();
        if (TextUtils.isEmpty(bank)) {
            Toast.makeText(this, "选择银行卡所属银行", Toast.LENGTH_SHORT).show();
            return;
        }

        String name = bank_card_info_tv2_open_bank_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "开户行名称", Toast.LENGTH_SHORT).show();
            return;
        }

        String num = bank_card_info_tv3_bank_card_num.getText().toString().trim();
        if (TextUtils.isEmpty(num)) {
            Toast.makeText(this, "银行卡号", Toast.LENGTH_SHORT).show();
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
            //点了选择银行
            case R.id.bank_card_info_tv1_bank:
                ArrayList<String> strings = new ArrayList<>();
                strings.add("水帘洞银行");
                strings.add("花果山银行");
                strings.add("花果山银行");

                UtilsDialog.showDialogRadioGroup(this, "请选择银行卡所属银行", strings, new UtilsDialogCallBack() {
                    @Override
                    public void RadioGroupNum(int selectNum, String selectNumInfo) {
                        LogUtils.i("我勾选的号码是"+selectNum+"内容是"+selectNumInfo);
                        bank_card_info_tv1_bank.setText(selectNumInfo);
                    }
                });


            break;
            //点了提交
            case R.id.bank_card_info_tv_sub:
                ArrayList<TextView> editTexts = new ArrayList<>();
                editTexts.add(bank_card_info_tv1_bank);
                editTexts.add(bank_card_info_tv2_open_bank_name);
                editTexts.add(bank_card_info_tv3_bank_card_num);
                ArrayList<TextView> editTexts1 = UtilsLoopTextView.addTagList(editTexts);
                ArrayList<String> list = new ArrayList<>();
                list.add("未选择银行卡");
                list.add("开户行名称没有填写");
                list.add("银行卡号没有填写");
                mBankCardInfoActivity_p.getEdtexts(editTexts1,list);

                break;
        }
    }
}
