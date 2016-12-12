package com.xinxinxuedai.Utils.UtilsLoop;

import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 16:24 . 2016年12月12日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class UtilsLoopTextView {


    /**
     * 能识别 传送进来的 集合是否有内容为空的
     * 如果 有 就回调 有问题的那个 还有出现问题的 集合索引
     * @param textViews
     * @param utilsLoopCallBack
     */
    public static void startLoop(ArrayList<TextView> textViews ,ArrayList<String> textInfo,UtilsLoopCallBack utilsLoopCallBack){
        for (int i = 0; i < textViews.size(); i++) {
            TextView textView = textViews.get(i);
            int tag = (int) textView.getTag();
            if (textView.length()==0){
                utilsLoopCallBack.onError(textView,i,textInfo.get(i));
                return;
            }
        }
        utilsLoopCallBack.onSucceed();
    }

    /**
     * 给这个集合设置了tag
     * @param textViews
     */
    public static ArrayList<TextView>  addTagList(ArrayList<TextView> textViews){
        for (int i = 0; i < textViews.size(); i++) {
            TextView textView = textViews.get(i);
            textView.setTag(i);
        }
        return textViews;
    }
}
