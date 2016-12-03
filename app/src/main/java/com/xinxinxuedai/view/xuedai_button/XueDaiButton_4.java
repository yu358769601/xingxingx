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

public class XueDaiButton_4 extends RelativeLayout implements View.OnClickListener {

    private View mView;
    private RelativeLayout mXuedai_button_4;
    private TextView mXuedai_button_4_tv1;
    private TextView mXuedai_button_4_tv2;
    private TextView mXuedai_button_4_tv3;
    private TextView mXuedai_button_4_tv4;

    public XueDaiButton_4(Context context) {
        super(context);
        initView(context);
    }



    public XueDaiButton_4(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public XueDaiButton_4(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    button_CallBack mButton_callBack;
    public XueDaiButton_4 setCallBack(button_CallBack mButton_callBack){
        this.mButton_callBack = mButton_callBack;
        return this;
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.xuedai_button_4, this);
        //外框
        mXuedai_button_4 = (RelativeLayout) mView.findViewById(R.id.xuedai_button_4);
        //第一个
        mXuedai_button_4_tv1 = (TextView) mView.findViewById(R.id.xuedai_button_4_tv1);
        //第二个
        mXuedai_button_4_tv2 = (TextView) mView.findViewById(R.id.xuedai_button_4_tv2);
        //第三个
        mXuedai_button_4_tv3 = (TextView) mView.findViewById(R.id.xuedai_button_4_tv3);
        //第四个
        mXuedai_button_4_tv4 = (TextView) mView.findViewById(R.id.xuedai_button_4_tv4);


    }

    /**
     * 设置 第几周
     * @param text
     * @return
     */
    public XueDaiButton_4 setText_tv1(String text){
        mXuedai_button_4_tv1.setText(text);
        return  this;
    }
    /**
     * 设置 第几周
     * @param text
     * @return
     */
    public XueDaiButton_4 setText_tv2(String text){
        mXuedai_button_4_tv2.setText(text);
        return  this;
    }
    /**
     * 设置 第几周
     * @param text
     * @return
     */
    public XueDaiButton_4 setText_tv3(String text){
        mXuedai_button_4_tv3.setText(text);
        return  this;
    }
    /**
     * 设置 第几周
     * @param text
     * @return
     */
    public XueDaiButton_4 setText_tv4(String text){
        mXuedai_button_4_tv4.setText(text);
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
