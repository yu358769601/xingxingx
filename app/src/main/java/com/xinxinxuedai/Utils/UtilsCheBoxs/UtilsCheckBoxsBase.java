package com.xinxinxuedai.Utils.UtilsCheBoxs;

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

public abstract class UtilsCheckBoxsBase<T> {

    public void setCheckBoxsData(Context context, final LinearLayout group, final ArrayList<T> arrayList, final double interest_money, final ItemNumCallBack callBack){
        if (null==arrayList||arrayList.size()==0){
            return;
        }
        group.removeAllViews();
        for (int i = 0; i < arrayList.size(); i++) {
            T t = arrayList.get(i);
            XueDaiButton_5 box = setCheckBox(context, t);
            box.setTag(i);

            group.addView(box);

        }
        for (int i = 0; i < group.getChildCount(); i++) {
            if (group.getChildAt(i)instanceof XueDaiButton_5 ){
                final XueDaiButton_5 childAt = (XueDaiButton_5) group.getChildAt(i);
                childAt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int tag = (int) v.getTag();
                        double checkSum = getCheckSum(interest_money,arrayList, tag);
//                        if (interest_money==checkSum){
//                            UtilsToast.showToast(AppContext.getApplication(),"还款金额不能小于0");
//                            return;
//                        }
                        childAt.setCheck();
                        //change(tag,group);
                        T t1 = arrayList.get(tag);
                        LogUtils.i("我现在点的是编号"+tag);
                        callBack.getNum(tag,t1,checkSum);
                    }


                });
            }
        }



    }

//    private void change(int tag, LinearLayout group) {
//        for (int i = 0; i < group.getChildCount(); i++) {
//            if (group.getChildAt(i)instanceof XueDaiButton_5 ) {
//                XueDaiButton_5 childAt = (XueDaiButton_5) group.getChildAt(i);
//                if (tag == i){
//                    childAt.setIV_star(true);
//                }else{
//                    childAt.setIV_star(false);
//                }
//
//            }
//        }
//    }
    public abstract XueDaiButton_5 setCheckBox(Context context, T t);
    public abstract double getCheckSum(double interest_money, ArrayList<T> arrayList, int tag);
}
