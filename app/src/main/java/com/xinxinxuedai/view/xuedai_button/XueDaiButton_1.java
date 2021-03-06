package com.xinxinxuedai.view.xuedai_button;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xinxinxuedai.R;
import com.xinxinxuedai.Utils.GlideUtils.GlideCircleTransform;
import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsMeasure;
import com.xinxinxuedai.app.AppContext;

/**
 * Created by 35876 于萌萌
 * 创建日期: 13:32 . 2016年11月30日
 * 描述:新新学贷 自定义透明按钮 借贷里面的 带星星的
 * <p>
 * <p>
 * 备注:
 */

public class XueDaiButton_1 extends RelativeLayout implements View.OnClickListener {

    private View mView;
    private RelativeLayout mXuedai_button1;
    int num;
    private ImageView mXuedai_1_iv_star;
    private TextView mXuedai_tv_1_button;
    private TextView mXuedai_tv_infotext;
    private RelativeLayout.LayoutParams mParams;
    private ImageView mXuedai_tv_iv;

    public XueDaiButton_1(Context context) {
        super(context);
        initView(context);
    }



    public XueDaiButton_1(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public XueDaiButton_1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    button_CallBack mButton_callBack;
    public XueDaiButton_1 setCallBack(button_CallBack mButton_callBack){
        this.mButton_callBack = mButton_callBack;
        return this;
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.xuedai_button_1, this);
        //外框 就是整体
        mXuedai_button1 = (RelativeLayout) findViewById(R.id.xuedai_button);
       mXuedai_button1.setOnClickListener(this);
        //宽高啥的
        mParams = (RelativeLayout.LayoutParams)mXuedai_button1.getLayoutParams();

        //右上角 小星星
        mXuedai_1_iv_star = (ImageView) mView.findViewById(R.id.xuedai_1_iv_star);
        mXuedai_1_iv_star.setVisibility(View.GONE);

        //中间的字 带着 图标的
        mXuedai_tv_1_button = (TextView) findViewById(R.id.xuedai_tv_1_button);
        //mXuedai_tv_1_button.setOnClickListener(this);

        //注释
        mXuedai_tv_infotext = (TextView) findViewById(R.id.xuedai_tv_infotext);

        //大图片
        mXuedai_tv_iv = (ImageView) findViewById(R.id.xuedai_tv_iv);
    }
    public XueDaiButton_1 setText(String text){
        mXuedai_tv_1_button.setText(text);
        return  this;
    }
    public XueDaiButton_1 setText_info(String text){
        mXuedai_tv_infotext.setText(text);
        return  this;
    }
    public XueDaiButton_1 setRelativeLayout_Pading(int left ,int top ,int right ,int down){
       // mParams.setMargins(left,top,right,down);//4个参数按顺序分别是左上右下

        mXuedai_button1.setPadding(left,top,right,down);
        //mXuedai_tv_1_button.setLayoutParams(mParams);
        return  this;
    }
    public XueDaiButton_1 setText_buttonmargin(int left ,int top ,int right ,int down){
        mParams.setMargins(left,top,right,down);//4个参数按顺序分别是左上右下
        mXuedai_tv_1_button.setLayoutParams(mParams);
        return  this;
    }


    public XueDaiButton_1 setText_infoColor(int color){
        mXuedai_tv_infotext.setTextColor(color);
        return  this;
    }
    public XueDaiButton_1 setVisbilityInfotext(boolean b){
        if(b){

            mXuedai_tv_infotext.setVisibility(VISIBLE);
        }else{
            mXuedai_tv_infotext.setVisibility(GONE);
        }

        return  this;
    }
    public XueDaiButton_1 setVisbilityStar(boolean b){
        if(b){
            mXuedai_1_iv_star.setVisibility(VISIBLE);
        }else{
            mXuedai_1_iv_star.setVisibility(GONE);
        }

        return  this;
    }
    public XueDaiButton_1 setImage(String image){
        //整到这里
        int[] measure = UtilsMeasure.measure(mXuedai_button1);
        //layoutParams.height =measure[1];
       RelativeLayout.LayoutParams  layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,measure[1] );
        //RelativeLayout.LayoutParams  layoutParams =(RelativeLayout.LayoutParams) mXuedai_button1.getLayoutParams();
        mXuedai_button1.setLayoutParams(layoutParams);


//        load SD卡资源：load("file://"+ Environment.getExternalStorageDirectory().getPath()+"/test.jpg")
//        load assets资源：load("file:///android_asset/f003.gif")
        LogUtils.i("资源是"+image);

        Glide
                .with(AppContext.getApplication())
                .load(image)
                .transform(new GlideCircleTransform(AppContext.getApplication(),10,AppContext.getApplication().getResources().getColor(R.color.white)))
                //.override(measure[0],measure[1])
                .centerCrop()
                .into(mXuedai_tv_iv);
//
//                ;
        mXuedai_tv_iv.setVisibility(VISIBLE);
        mXuedai_tv_iv.setLayoutParams(layoutParams);
        //mXuedai_tv_iv.setBackgroundResource(R.drawable.an);
//       UtilsMeasure.measure(mXuedai_tv_iv);
        return  this;
    }


    public RelativeLayout getXuedai_button1_rl(){
        return mXuedai_button1;
    }

    public TextView getXuedai_button1_tv(){
        return mXuedai_tv_1_button;
    }

    public XueDaiButton_1 setTextColor(int color){

        mXuedai_tv_1_button.setTextColor(color);
        return  this;
    }
    public XueDaiButton_1 setTopDrawable(int Drawable_Id){
        Drawable drawableTop;
        Resources res = getResources();
        drawableTop = res.getDrawable(Drawable_Id);
//调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
        drawableTop.setBounds(0, 0, drawableTop.getMinimumWidth(), drawableTop.getMinimumHeight());
        mXuedai_tv_1_button.setCompoundDrawables(null, drawableTop, null, null); //设置左图标
        return  this;
    }
    public XueDaiButton_1 setTopDrawableVisibility(boolean b){
        if(b){
           // mXuedai_1_iv_star.setVisibility(VISIBLE);
        }else{
            mXuedai_tv_1_button.setCompoundDrawables(null, null, null, null); //设置左图标
        }
        return  this;
    }
    public XueDaiButton_1 set_weight(){
        //mXuedai_button1
        return  this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.xuedai_button:
                if (null!=mButton_callBack)
                mButton_callBack.button_Click(v);
            break;
        }
    }
    public void setType(int num){
        this. num =  num;
    }
    public int getType(){
        return num;
    }


}
