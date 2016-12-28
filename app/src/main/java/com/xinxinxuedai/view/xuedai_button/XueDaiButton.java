package com.xinxinxuedai.view.xuedai_button;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xinxinxuedai.R;

/**
 * Created by 35876 于萌萌
 * 创建日期: 13:32 . 2016年11月30日
 * 描述:新新学贷 自定义透明按钮
 * <p>
 * <p>
 * 备注:
 */

public class XueDaiButton extends RelativeLayout implements View.OnClickListener {

    private View mView;
    private TextView mXuedai_button_small;
    private TextView mXuedai_button;
    private RelativeLayout mXuedai_button1;
    int num;
    private ImageView mXuedai_image;
    private TextView mXuedai_tv_button_info;

    public XueDaiButton(Context context) {
        super(context);
        initView(context);
    }



    public XueDaiButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public XueDaiButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    button_CallBack mButton_callBack;
    public XueDaiButton setCallBack(button_CallBack mButton_callBack){
        this.mButton_callBack = mButton_callBack;
        return this;
    }

    private void initView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.xuedai_button, this);
        //外框
        mXuedai_button1 = (RelativeLayout) mView.findViewById(R.id.xuedai_button);
        mXuedai_button1.setOnClickListener(this);
        //下面提示
        mXuedai_tv_button_info = (TextView) mView.findViewById(R.id.xuedai_tv_button_info);
        mXuedai_tv_button_info.setVisibility(INVISIBLE);
        //左上角小字
        mXuedai_button_small = (TextView) mView.findViewById(R.id.xuedai_tv_button_small);
       //中间字
        mXuedai_button = (TextView) mView.findViewById(R.id.xuedai_tv_button);
        //右上角星星
        mXuedai_image = (ImageView) mView.findViewById(R.id.xuedai_image);
        mXuedai_image.setVisibility(View.INVISIBLE);

    }

    /**
     * 设置中间字内容
     * @param text
     * @return
     */
    public XueDaiButton setText(String text){
        mXuedai_button.setText(text);
        return  this;
    }
    /**
     * 设置下面提示的文字
     * @param text
     * @return
     */
    public XueDaiButton setInfoText(String text){
        mXuedai_tv_button_info.setText(text);
        return  this;
    }

    /**
     * 设定提示文字是否显示
     * @param b true 显示  false 不显示
     * @return
     */
    public XueDaiButton setInfoStuaus(boolean b){
        if (b){
            mXuedai_tv_button_info.setVisibility(View.VISIBLE);
        }else{
            mXuedai_tv_button_info.setVisibility(View.INVISIBLE);
        }
        return  this;
    }

    /**
     * 设置左上角字的内容
     * @param text
     * @return
     */
    public XueDaiButton setTextSmall(String text){
        mXuedai_button_small.setText(text);
        return  this;
    }

    /**
     * 设定左上角字的颜色
     * @param color
     * @return
     */
    public XueDaiButton setTextSmallColor(int color){
        mXuedai_button_small.setTextColor(color);
        return  this;
    }

    /**
     * 设定中间的字的颜色
     * @param color
     * @return
     */
    public XueDaiButton setTextColor(int color){

        mXuedai_button.setTextColor(color);
        return  this;
    }

    /**
     * 设定小星星是否显示
     * @param b true 显示  false 不显示
     * @return
     */
    public XueDaiButton setStarStuaus(boolean b){
        if (b){
            mXuedai_image.setVisibility(View.VISIBLE);
        }else{
            mXuedai_image.setVisibility(View.INVISIBLE);
        }
        return  this;
    }

    /**
     * 设定中间图表示什么
     * @param Drawable_Id 资源id
     * @return
     */
    public XueDaiButton setTopDrawable(int Drawable_Id){
        Drawable drawableTop;
        Resources res = getResources();
        drawableTop = res.getDrawable(Drawable_Id);
//调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
        drawableTop.setBounds(0, 0, drawableTop.getMinimumWidth(), drawableTop.getMinimumHeight());
        mXuedai_button.setCompoundDrawables(null, drawableTop, null, null); //设置左图标
        //mXuedai_button.setCompoundDrawablesWithIntrinsicBounds();
       // mXuedai_button.setTextColor(color);
        return  this;
    }
    public XueDaiButton set_weight(){
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
