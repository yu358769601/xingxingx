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

public class XueDaiButton_3 extends RelativeLayout implements View.OnClickListener {

    private View mView;
    private RelativeLayout mXuedai_button_3;
    private TextView mXuedai_button_3_tv1_plan;
    private TextView mXuedai_button_3_tv_fenqi;
    private TextView mXuedai_button_3_tv2_plan_day;
    private TextView mXuedai_button_3_tv3_plan_day;

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
        //计划还款金额
        mXuedai_button_3_tv1_plan = (TextView) mView.findViewById(R.id.xuedai_button_3_tv1_plan);
        //分期
        mXuedai_button_3_tv_fenqi = (TextView) mView.findViewById(R.id.xuedai_button_3_tv_fenqi);
        //计划还款日
        mXuedai_button_3_tv2_plan_day = (TextView) mView.findViewById(R.id.xuedai_button_3_tv2_plan_day);
        //逾期天数
        mXuedai_button_3_tv3_plan_day = (TextView) mView.findViewById(R.id.xuedai_button_3_tv3_plan_day);


    }

    /**
     * 设置 计划还款金额
     * @param text
     * @return
     */
    public XueDaiButton_3 setText_plan(String text){
        mXuedai_button_3_tv1_plan.setText(text);
        return  this;
    }

    /**
     * 分期
     * @param text
     * @return
     */
    public XueDaiButton_3 setText_tv_fenqi(String text){
        mXuedai_button_3_tv_fenqi.setText(text);
        return  this;
    }

    /**
     *计划还款日
     * @param text
     * @return
     */
    public XueDaiButton_3 setText_button_tv3_plan_day(String text){
        mXuedai_button_3_tv2_plan_day.setText(text);
        return  this;
    }


    /**
     * 逾期天数
     * @param text
     * @return
     */
    public XueDaiButton_3 setText_tv3_plan_day(String text){

        mXuedai_button_3_tv3_plan_day.setText(text);
        return  this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case 1:

            break;
        }
    }


}
