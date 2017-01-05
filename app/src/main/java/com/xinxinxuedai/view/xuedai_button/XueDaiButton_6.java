package com.xinxinxuedai.view.xuedai_button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxinxuedai.R;

import java.util.ArrayList;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 11:43 . 2016年12月30日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class XueDaiButton_6 extends RelativeLayout implements View.OnClickListener {


    private TextView xuedai_button_6_tv1;
    private TextView xuedai_button_6_tv2;
    private TextView xuedai_button_6_tv3;
    private TextView xuedai_button_6_tv4;
    private TextView xuedai_button_6_tv5;
    private TextView xuedai_button_6_tv6;
    private TextView xuedai_button_6_tv7;
    private RelativeLayout xuedai_button_6;

    public XueDaiButton_6(Context context) {
        super(context);
        initView(context);
    }


    public XueDaiButton_6(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public XueDaiButton_6(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = inflater.inflate(R.layout.xuedai_button_6, this);
        xuedai_button_6_tv1= (TextView)mView.findViewById(R.id.xuedai_button_6_tv1);
        xuedai_button_6_tv2= (TextView)mView.findViewById(R.id.xuedai_button_6_tv2);
        xuedai_button_6_tv3= (TextView)mView.findViewById(R.id.xuedai_button_6_tv3);
        xuedai_button_6_tv4= (TextView)mView.findViewById(R.id.xuedai_button_6_tv4);
        xuedai_button_6_tv5= (TextView)mView.findViewById(R.id.xuedai_button_6_tv5);
        xuedai_button_6_tv6= (TextView)mView.findViewById(R.id.xuedai_button_6_tv6);
        xuedai_button_6_tv7= (TextView)mView.findViewById(R.id.xuedai_button_6_tv7);

    }

    /**
     * 设置 代金券金额
     *
     * @param text 钱数
     * @return
     */
    public XueDaiButton_6 setTv3_Text(String text) {
        //String format = String.format("%.2f", text);
        xuedai_button_6_tv3.setText(xuedai_button_6_tv3.getHint() + text + "元");
        return this;
    }

    /**
     * 设置 过期时间
     *
     * @param text 钱数
     * @return
     */
    public XueDaiButton_6 setTv2_Text(String text) {
        xuedai_button_6_tv2.setText(xuedai_button_6_tv2.getHint() + text);
        return this;
    }

    /**
     * 审核状态
     * @param status
     * @return
     */
    public XueDaiButton_6 setTv1_Text(int status) {
        ArrayList<String> strings = new ArrayList<>();
       // 审核状态 0 待审核 1 审核通过 2 满标放款成功
        strings.add("待审核");
        strings.add("审核通过");
        strings.add("满标放款成功");



        xuedai_button_6_tv1.setText(xuedai_button_6_tv1.getHint() + strings.get(status));
        return this;
    }
    public XueDaiButton_6 setTv4_Text(String text) {
        xuedai_button_6_tv4.setText(xuedai_button_6_tv4.getHint() + text);
        return this;
    }
    public XueDaiButton_6 setTv5_Text(String text) {
        xuedai_button_6_tv5.setText(xuedai_button_6_tv5.getHint() + text);
        return this;
    }
    public XueDaiButton_6 setTv6_Text(String text) {
        xuedai_button_6_tv6.setText(xuedai_button_6_tv6.getHint() + text);
        return this;
    }
    public XueDaiButton_6 setTv7_Text(String text) {
        xuedai_button_6_tv7.setText(xuedai_button_6_tv7.getHint() + text);
        return this;
    }



    @Override
    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.xuedai_button_5:
//                setCheck();
//                break;
//        }
    }
}
