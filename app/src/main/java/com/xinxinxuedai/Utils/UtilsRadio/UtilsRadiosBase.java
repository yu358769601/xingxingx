package com.xinxinxuedai.Utils.UtilsRadio;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.xinxinxuedai.Utils.LogUtils;
import com.xinxinxuedai.view.xuedai_button.XueDaiButton_5;

import java.util.ArrayList;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 13:40 . 2016年12月30日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public abstract class UtilsRadiosBase<T> {

    public void setRadioGroupData(Context context, final LinearLayout group, final ArrayList<T> arrayList, final ItemNumCallBack callBack){
        if (null==arrayList||arrayList.size()==0){
            return;
        }
        group.removeAllViews();
        for (int i = 0; i < arrayList.size(); i++) {
            T t = arrayList.get(i);
            XueDaiButton_5 radioButton = setRadioButton(context, t);
            radioButton.setTag(i);

            group.addView(radioButton);

        }
        for (int i = 0; i < group.getChildCount(); i++) {
            if (group.getChildAt(i)instanceof XueDaiButton_5 ){
                final XueDaiButton_5 childAt = (XueDaiButton_5) group.getChildAt(i);
                childAt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int tag = (int) v.getTag();

                        change(tag,group);
                        T t1 = arrayList.get(tag);
                        LogUtils.i("我现在点的是编号"+tag);
                        callBack.getNum(tag,t1);
                    }


                });
            }
        }



    }

    private void change(int tag, LinearLayout group) {
        for (int i = 0; i < group.getChildCount(); i++) {
            if (group.getChildAt(i)instanceof XueDaiButton_5 ) {
                XueDaiButton_5 childAt = (XueDaiButton_5) group.getChildAt(i);
                if (tag == i){
                    childAt.setIV_star(true);
                }else{
                    childAt.setIV_star(false);
                }

            }
        }
    }
    public abstract XueDaiButton_5 setRadioButton(Context context, T t);

}
