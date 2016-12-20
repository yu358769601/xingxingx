package com.xinxinxuedai.view.xuedai_button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxinxuedai.R;

/**
 * Created by 35876 于萌萌
 * 创建日期: 13:32 . 2016年11月30日
 * 描述:新新学贷 自定义透明按钮 借贷里面的 带星星的
 * <p>
 * <p>
 * 备注:
 */

public class XueDaiButton_2 extends RelativeLayout implements View.OnClickListener {

    private View mView;
    private RelativeLayout mXuedai_button_1;
    private TextView mXuedai_button_2_balance_of_account;
    private TextView mXuedai_button_2_top_up;
    private TextView mXuedai_button_2_borrow_money;
    private TextView mXuedai_button_2_return_the_principal;
    private TextView mXuedai_button_2_tv1;
    private TextView mXuedai_button_2_tv2;
    private TextView mXuedai_button_2_rightbutton_fenqi;
    private TextView xuedai_button_2_info;
    private TextView xuedai_button_2_tiqian;

    public XueDaiButton_2(Context context) {
        super(context);
        initView(context);
    }



    public XueDaiButton_2(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public XueDaiButton_2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    button_CallBack mButton_callBack;
    public XueDaiButton_2 setCallBack(button_CallBack mButton_callBack){
        this.mButton_callBack = mButton_callBack;
        return this;
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.xuedai_button_2, this);
        //外框
        mXuedai_button_1 = (RelativeLayout) mView.findViewById(R.id.xuedai_button_1);
        mXuedai_button_1.setOnClickListener(this);
        mXuedai_button_1.setTag(1);
        //账户余额
        mXuedai_button_2_balance_of_account = (TextView) mView.findViewById(R.id.xuedai_button_2_balance_of_account);
        mXuedai_button_2_balance_of_account.setOnClickListener(this);
        mXuedai_button_2_balance_of_account.setTag(2);
        //充值按钮
        mXuedai_button_2_top_up = (TextView) mView.findViewById(R.id.xuedai_button_2_top_up);
        mXuedai_button_2_top_up.setOnClickListener(this);
        mXuedai_button_2_top_up.setTag(3);
        //借款金额
        mXuedai_button_2_borrow_money = (TextView) mView.findViewById(R.id.xuedai_button_2_borrow_money);
        mXuedai_button_2_borrow_money.setOnClickListener(this);
        mXuedai_button_2_borrow_money.setTag(4);
        //借款方式
        mXuedai_button_2_return_the_principal = (TextView) mView.findViewById(R.id.xuedai_button_2_return_the_principal);
        mXuedai_button_2_return_the_principal.setOnClickListener(this);
        mXuedai_button_2_return_the_principal.setTag(5);
        //左面条款
        mXuedai_button_2_tv1 = (TextView) mView.findViewById(R.id.xuedai_button_2_tv1);
        mXuedai_button_2_tv1.setOnClickListener(this);
        mXuedai_button_2_tv1.setTag(6);
        //右面条款
        mXuedai_button_2_tv2 = (TextView) mView.findViewById(R.id.xuedai_button_2_tv2);
        mXuedai_button_2_tv2.setOnClickListener(this);
        mXuedai_button_2_tv2.setTag(7);

        //月分期
        mXuedai_button_2_rightbutton_fenqi = (TextView) mView.findViewById(R.id.xuedai_button_2_rightbutton_fenqi);
        mXuedai_button_2_rightbutton_fenqi.setOnClickListener(this);
        mXuedai_button_2_rightbutton_fenqi.setTag(8);

        //还款提示
        xuedai_button_2_info = (TextView) mView.findViewById(R.id.xuedai_button_2_info);
        xuedai_button_2_info.setOnClickListener(this);
        xuedai_button_2_info.setTag(9);

        //提前结清
        xuedai_button_2_tiqian = (TextView) mView.findViewById(R.id.xuedai_button_2_tiqian);
        xuedai_button_2_tiqian.setOnClickListener(this);
        xuedai_button_2_tiqian.setTag(10);



    }
    /**
     * 设置 提前结清按钮文字
     * @param text
     * @return
     */
    public XueDaiButton_2 setText_tiqian(String text){
        xuedai_button_2_tiqian.setText(text);
        return  this;
    }
    /**
     * 设置 分期提示文字
     * @param text
     * @return
     */
    public XueDaiButton_2 setText_info(String text){
        xuedai_button_2_info.setText(text);
        return  this;
    }
    /**
     * 设置 月分期按钮  月分期84天
     * @param text
     * @return
     */
    public XueDaiButton_2 setText_rightbutton_fenqi(String text){
        mXuedai_button_2_rightbutton_fenqi.setText(mXuedai_button_2_rightbutton_fenqi.getHint()+text);
        return  this;
    }
    /**
     * 设置 账户余额
     * @param text
     * @return
     */
    public XueDaiButton_2 setText_balance_of_account(String text){
        mXuedai_button_2_balance_of_account.setText(mXuedai_button_2_balance_of_account.getHint()+text);
        return  this;
    }

    /**
     * 充值按钮
     * @param text
     * @return
     */
    public XueDaiButton_2 setText_top_up(String text){
        mXuedai_button_2_top_up.setText(text);
        return  this;
    }

    /**
     *借款金额
     * @param text
     * @return
     */
    public XueDaiButton_2 setText_borrow_money(String text){
        mXuedai_button_2_borrow_money.setText(mXuedai_button_2_borrow_money.getHint()+text);
        return  this;
    }

    /**
     * 借款方式
     * @param
     * @return
     */
    public XueDaiButton_2 setText_return_the_principal(String text){
        mXuedai_button_2_return_the_principal.setText(mXuedai_button_2_return_the_principal.getHint()+text);
        return  this;
    }


    /**
     * 左面条款
     * @param text
     * @return
     */
    public XueDaiButton_2 setText_mbutton_2_tv1(String text){

        mXuedai_button_2_tv1.setText(text);
        return  this;
    }

    /**
     * 右面条款
     * @param text
     * @return
     */
    public XueDaiButton_2 setText_mbutton_2_tv2(String text){
        mXuedai_button_2_tv2.setText(text);
        return  this;
    }



    @Override
    public void onClick(View v) {
                mButton_callBack.button_Click(v);
        }
    }


