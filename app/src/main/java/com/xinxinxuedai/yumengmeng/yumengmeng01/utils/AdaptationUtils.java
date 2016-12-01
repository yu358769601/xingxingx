package com.xinxinxuedai.yumengmeng.yumengmeng01.utils;

import android.content.Context;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:59 . 2016年09月05日
 * 描述:
 * <p/>
 * <p/>
 * 备注:
 */
public class AdaptationUtils {
    /**
     * 输入 dp值 在代码中自动就是设置成px
     * @param context
     * @param dp
     * @return
     */
    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
