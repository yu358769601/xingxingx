package com.xinxinxuedai.MVP.Re_stagingActivity.contract;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 15:52 . 2016年12月29日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */
public interface Re_stagingActivity_mvpContract {
    public interface View {

        void getData(ArrayList<String> list);
    }

    public interface Presenter {
        /**
         * 初始化
         * @param context
         * @param view
         */
        void method(Context context,View view);

        /**
         * 按了按钮了
         *
         * @param view
         */
        void SetOnClick(android.view.View view);

    }

    public interface Model{

        void SetDialog(Dialog dialog);
    }

    /**
     * 返回 集合
     */
    public interface Dialog{
        void getList(ArrayList<String> list);
    }
}
