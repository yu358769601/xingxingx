package com.xinxinxuedai.Utils;

import android.view.View;

/**
 * Created by 35876 于萌萌
 * 创建日期: 17:08 . 2016年12月06日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class UtilsMeasure {
    /**
     * 测量工具类
     * @param view
     * @return
     */
    public  static int[] measure(View view){

        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int height = view.getMeasuredHeight();
        int width = view.getMeasuredWidth();
        LogUtils.i("这个View 宽高"+"高"+height+"宽"+width);
        return new int[]{width,height};
    }
}
