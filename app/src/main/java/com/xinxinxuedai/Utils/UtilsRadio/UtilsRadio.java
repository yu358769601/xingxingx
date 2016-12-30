package com.xinxinxuedai.Utils.UtilsRadio;

import android.content.Context;

import com.xinxinxuedai.Utils.UtilsRadio.bean.RadioGroupData;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton_5;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:29 . 2016年12月30日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class UtilsRadio extends UtilsRadiosBase<RadioGroupData> {

    @Override
    public XueDaiButton_5 setRadioButton(Context context, RadioGroupData radioGroupData) {
        XueDaiButton_5 xueDaiButton_5 = new XueDaiButton_5(context);
        xueDaiButton_5.setTv1_Text(radioGroupData.money+"");
        xueDaiButton_5.setTv2_Text( radioGroupData.date);

        return xueDaiButton_5;
    }
}
