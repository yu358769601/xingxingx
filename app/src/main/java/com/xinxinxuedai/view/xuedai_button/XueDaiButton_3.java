package com.xinxinxuedai.view.xuedai_button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class XueDaiButton_3 extends RelativeLayout implements View.OnClickListener {

    private View mView;
    private RelativeLayout mXuedai_button_3;
    private TextView mXuedai_button_3_tv2_plan_day;
    private TextView mXuedai_button_3_tv3_plan_day;
    private ImageView mXuedai_button3_iv;
    private TextView mXuedai_button3_tv1;
    private TextView mXuedai_button3_tv2;
    private TextView mXuedai_button3_tv3;
    private TextView mXuedai_button3_tv4;
    private TextView mXuedai_button3_tv5;
    private TextView mXuedai_button3_tv6;
    private TextView mXuedai_button3_tv7;
    private LinearLayout ll2;

    public XueDaiButton_3(Context context) {
        super(context);
        initView(context);
    }



    public XueDaiButton_3(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public XueDaiButton_3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    button_CallBack mButton_callBack;
    public XueDaiButton_3 setCallBack(button_CallBack mButton_callBack){
        this.mButton_callBack = mButton_callBack;
        return this;
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.xuedai_button_3, this);
        //外框
        mXuedai_button_3 = (RelativeLayout) mView.findViewById(R.id.xuedai_button_3);
        mXuedai_button_3.setOnClickListener(this);
        mXuedai_button_3.setTag(1);
        //状态星星
        mXuedai_button3_iv = (ImageView) mView.findViewById(R.id.xuedai_button3_iv);
        mXuedai_button3_iv.setTag(2);
        //应还金额
        mXuedai_button3_tv1 = (TextView) mView.findViewById(R.id.xuedai_button3_tv1);
        mXuedai_button3_tv1.setTag(3);
        //已还金额
        mXuedai_button3_tv2 = (TextView) mView.findViewById(R.id.xuedai_button3_tv2);
        mXuedai_button3_tv2.setTag(4);
        //12/12
        mXuedai_button3_tv3 = (TextView) mView.findViewById(R.id.xuedai_button3_tv3);
        mXuedai_button3_tv3.setTag(5);
        //计划还款日
        mXuedai_button3_tv4 = (TextView) mView.findViewById(R.id.xuedai_button3_tv4);
        mXuedai_button3_tv4.setTag(6);
        //还款按钮
        mXuedai_button3_tv5 = (TextView) mView.findViewById(R.id.xuedai_button3_tv5);
        mXuedai_button3_tv5.setTag(7);
        mXuedai_button3_tv5.setOnClickListener(this);
        //再分期提示
        mXuedai_button3_tv6 = (TextView) mView.findViewById(R.id.xuedai_button3_tv6);
        mXuedai_button3_tv6.setTag(8);
        //再分期按钮
        mXuedai_button3_tv7 = (TextView) mView.findViewById(R.id.xuedai_button3_tv7);
        mXuedai_button3_tv7.setTag(9);
        //是否显示
        ll2 = (LinearLayout) mView.findViewById(R.id.ll2);
        ll2.setTag(10);



    }

    /**
     * 设置应还金额
     * @param s
     * @return
     */
    public XueDaiButton_3 setTv1(String s){
        mXuedai_button3_tv1.setText(mXuedai_button3_tv1.getHint()+s);
        return this;
    }

    public double getXuedai_button3_tv1(){
        String substring = mXuedai_button3_tv1.getText().toString().substring(5, mXuedai_button3_tv1.getText().toString().length()-1);

        double v = Double.parseDouble(substring);
        return v;
    }
    /**
     * 设置已还金额
     * @param s
     * @return
     */
    public XueDaiButton_3 setTv2(String s){
        mXuedai_button3_tv2.setText(mXuedai_button3_tv2.getHint()+s);
        return this;
    }
    /**
     * 设置12/12
     * @param s
     * @return
     */
    public XueDaiButton_3 setTv3(String s){
        mXuedai_button3_tv3.setText(s);
        return this;
    }
    /**
     * 计划还款日
     * @param s
     * @return
     */
    public XueDaiButton_3 setTv4(String s){
        mXuedai_button3_tv4.setText(mXuedai_button3_tv4.getHint()+s);
        return this;
    }
    /**
     * 还款按钮
     * @param s
     * @return
     */
    public XueDaiButton_3 setTv5(String s){
        mXuedai_button3_tv5.setText(mXuedai_button3_tv5.getHint()+s);
        return this;
    }
    /**
     * 是否显示
     * @param
     * @return
     */
    public XueDaiButton_3 setll_Show(boolean b){
        if (b){
            ll2.setVisibility(VISIBLE);
        }else{
            ll2.setVisibility(GONE);
        }
        return this;
    }

    @Override
    public void onClick(View v) {
        if (null!=mButton_callBack)
        mButton_callBack.button_Click(v);
    }


}
