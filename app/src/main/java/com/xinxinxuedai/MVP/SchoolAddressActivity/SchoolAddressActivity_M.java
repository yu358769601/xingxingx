package com.xinxinxuedai.MVP.SchoolAddressActivity;

import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 16:09 . 2016年12月12日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public interface SchoolAddressActivity_M {
    /**
     * 提交 信息
     * @param sub
     * @param strings
     * @param stringarray
     */
    void setSub(ArrayList<TextView> sub,ArrayList<String> strings , String[] stringarray);


    void getCallBackData();
}
