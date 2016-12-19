package com.xinxinxuedai.view.xuedai_button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.LogUtils;

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
    private LinearLayout mXuedai_button_3_ll;

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

    public XueDaiButton_4 setCallBack(button_CallBack mButton_callBack) {
        this.mButton_callBack = mButton_callBack;
        return this;
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.xuedai_button_4, this);
        //外框
        // mXuedai_button_4 = (RelativeLayout) mView.findViewById(R.id.xuedai_button_4);
        mXuedai_button_3_ll = (LinearLayout) mView.findViewById(R.id.xuedai_button_3_ll);


    }

    public void setData(int day, int moneny, Context context,int classTag) {
        double tag = 0;
        switch (day){
            case 28:
                tag = 1;
            break;
            case 56:
                tag = 0.5;
            break;
            case 84:
                tag = 0.33;
            break;
            case 112:
                tag = 0.25;
            break;
        }

//        android:id="@+id/xuedai_button_4_tv1"
//        android:text="第一周服务费:"
//        android:layout_marginTop="@dimen/size_base480_5dp"
//        android:layout_gravity="center_vertical"
//        android:gravity="center_horizontal"
//        android:textColor="@color/white"
//        android:layout_height="wrap_content"
//        android:layout_width="match_parent"
        mXuedai_button_3_ll.removeAllViews();
        LogUtils.i("创建tv_清除所有TV");
        ViewGroup.LayoutParams layoutParams = mXuedai_button_3_ll.getLayoutParams();
        int i1 = day / 7;
        //=A2*0.0275+A2*0.01+A2*0.2  A2 等于本金
        for (int i = 1; i <= i1; i++) {
          //  LogUtils.i("创建tv"+i);
            TextView textView = new TextView(context);
            ViewGroup.LayoutParams layoutParams1 =
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i%4 ==0){
                if (classTag==1){
                    LogUtils.i("我是等额本息");
                    String format = String.format("%.2f元", (moneny * 0.0275) + (moneny * 0.01) + (moneny*tag));
                    textView.setText("第" + i + "周" + ":" + format);
                }else{
                    //如果是最后一期
                    if (i==i1){
                        String format = String.format("%.2f元", (moneny * 0.0275) + (moneny * 0.01)+moneny);
                        textView.setText("第" + i + "周" + ":" + format);
                    }else{
                        String format = String.format("%.2f元", (moneny * 0.0275) + (moneny * 0.01));
                        textView.setText("第" + i + "周" + ":" + format);
                    }

                }

            }else{
                LogUtils.i("我是先息后本");
                String format = String.format("%.2f元", (moneny * 0.0275));
                textView.setText("第" + i + "周" + ":" + format);
            }
            textView.setPadding(0, 10, 0, 10);
            textView.setTextColor(getResources().getColor(R.color.white));
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setLayoutParams(layoutParams1);
            mXuedai_button_3_ll.addView(textView);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 1:

                break;
        }
    }


}
