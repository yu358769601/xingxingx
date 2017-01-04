package com.xinxinxuedai.Utils.UtilsCheBoxs;

import android.content.Context;

import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.Utils.UtilsCheBoxs.bean.RadioGroupData;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton_5;

import java.util.ArrayList;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 14:29 . 2016年12月30日
 * 描述:单选
 * <p>
 * <p>
 * 备注:
 */

public class UtilsCheck extends UtilsCheckBoxsBase<RadioGroupData> {

    @Override
    public XueDaiButton_5 setCheckBox(Context context, RadioGroupData radioGroupData) {
        XueDaiButton_5 xueDaiButton_5 = new XueDaiButton_5(context);
        String format = String.format("%.2f", radioGroupData.money);
        xueDaiButton_5.setTv1_Text(format);
        xueDaiButton_5.setTv2_Text( radioGroupData.guoqi_time1);

        return xueDaiButton_5;
    }

    @Override
    public double getCheckSum(double interest_money, ArrayList<RadioGroupData> arrayList, int tag) {
        double sum = 0;

        LogUtils.i("目前sum是多少"+sum+" 进来的是多少"+interest_money);
        for (int i = 0; i < arrayList.size(); i++) {
            RadioGroupData radioGroupData = arrayList.get(i);
            if (i==tag){
                //如果他原本就是 按下去的
                if (radioGroupData.getItemFlag()==1){
                    radioGroupData.setItemFlag(0);
                }else {
                    radioGroupData.setItemFlag(1);

                }
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {

            RadioGroupData radioGroupData = arrayList.get(i);
            if (radioGroupData.getItemFlag() ==1){
                sum+=radioGroupData.money;
            }

        }
        LogUtils.i("每点一次的钱是"+sum);
        return sum;
    }


}
