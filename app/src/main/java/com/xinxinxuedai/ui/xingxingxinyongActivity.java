package com.xinxinxuedai.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xinxinxuedai.MVP.xingxingxinyongActivity.contract.xingxingxinyongContract;
import com.xinxinxuedai.MVP.xingxingxinyongActivity.presenter.xingxingxinyongPresenter;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.UtilsMyText;
import com.xinxinxuedai.app.AppContext;
import com.xinxinxuedai.base.BaseActivity;
import com.xinxinxuedai.bean.GetInfo;
import com.xinxinxuedai.view.initAction_Bar;

/**
 * 星星信用activity
 */
public class xingxingxinyongActivity extends BaseActivity implements xingxingxinyongContract.View, View.OnClickListener {

    private xingxingxinyongPresenter mXingxingxinyongPresenter;
    private TextView xinyong_money;
    private TextView xinyong_fen;
    private initAction_Bar relativeLayout_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initP();
        initView();
    }

    @Override
    public int getlayouXML() {
        return R.layout.activity_xingxingxinyong;
    }

    @Override
    public void initView() {

        xinyong_money = (TextView) findViewById(R.id.xinyong_money);
        xinyong_money.setOnClickListener(this);
        xinyong_fen = (TextView) findViewById(R.id.xinyong_fen);
        xinyong_fen.setOnClickListener(this);


        relativeLayout_title = (initAction_Bar) findViewById(R.id.relativeLayout_title);
        relativeLayout_title.setCallBack(new initAction_Bar.Action_bar_call_back() {
            @Override
            public void getAction_barView_backbutton(Button button) {

            }

            @Override
            public void getAction_barView_title(TextView textView) {
                textView.setText("星星信用");
            }
        });
        initData();
    }

    @Override
    public void initP() {
        mXingxingxinyongPresenter = new xingxingxinyongPresenter(AppContext.getApplication(), this);

    }

    @Override
    public void initData() {
        mXingxingxinyongPresenter.getData();
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

    @Override
    public void setData(GetInfo getInfo) {
        UtilsMyText.setHintAddText(xinyong_money, getInfo.loan_money + "元");
        UtilsMyText.setHintAddText(xinyong_fen, getInfo.xinyong_money + "分");

    }
}
