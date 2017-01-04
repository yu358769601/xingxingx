package com.xinxinxuedai.view.xuedai_button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxinxuedai.R;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 11:43 . 2016年12月30日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class XueDaiButton_5 extends RelativeLayout implements View.OnClickListener {

    private TextView xuedai_button_5_tv1;
    private TextView xuedai_button_5_tv2;
    private ImageView xuedai_button_5_iv;
    private RelativeLayout xuedai_button_5;
    private boolean check = false;
    public XueDaiButton_5(Context context) {
        super(context);
        initView(context);
    }


    public XueDaiButton_5(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public XueDaiButton_5(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      //  View mView = inflater.inflate(R.layout.xuedai_button_5, this);
       View mView = inflater.inflate(R.layout.xuedai_button_5, this);
        //外框
        xuedai_button_5 = (RelativeLayout) mView.findViewById(R.id.xuedai_button_5);
       // xuedai_button_5.setOnClickListener(this);
        //代金券金额
        xuedai_button_5_tv1=  (TextView)mView.findViewById(R.id.xuedai_button_5_tv1);
        //过期时间
        xuedai_button_5_tv2=  (TextView)mView.findViewById(R.id.xuedai_button_5_tv2);
        //小星星
        xuedai_button_5_iv=  (ImageView) mView.findViewById(R.id.xuedai_button_5_iv);
    }

    /**
     * 设置 代金券金额
     * @param text 钱数
     * @return
     */
    public XueDaiButton_5 setTv1_Text(String text){
        //String format = String.format("%.2f", text);
        xuedai_button_5_tv1.setText(xuedai_button_5_tv1.getHint()+text+"元");
        return this;
    }
    /**
     * 设置 过期时间
     * @param text 钱数
     * @return
     */
    public XueDaiButton_5 setTv2_Text(String text){
        xuedai_button_5_tv2.setText(xuedai_button_5_tv2.getHint()+text);
        return this;
    }

    /**
     * 设置 是否显示星星
     * @param b 是否显示星星
     * @return
     */
    public XueDaiButton_5 setIV_star(boolean b){
        if (b){
            xuedai_button_5_iv.setVisibility(VISIBLE);
        }else{
            xuedai_button_5_iv.setVisibility(INVISIBLE);
        }
        return this;
    }

    /**
     * 改变
     * @return
     */
    public XueDaiButton_5 setCheck(){
        if (!check){
            xuedai_button_5_iv.setVisibility(VISIBLE);
            check = true;
        }else{
            xuedai_button_5_iv.setVisibility(INVISIBLE);
            check = false;
        }
        return this;
    }

    /**
     * 是否点击
     * @return
     */
    public boolean isCheck(){
        return check;
    }
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.xuedai_button_5:
//                setCheck();
//                break;
//        }
    }
}
