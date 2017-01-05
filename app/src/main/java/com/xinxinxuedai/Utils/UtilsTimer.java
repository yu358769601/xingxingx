package com.xinxinxuedai.Utils;

import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 10:57 . 2017年01月05日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class UtilsTimer {
    public static String getFormat(){
//        Date date = new Date();
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        String t=format.format(date);

        return "未放款";
    }

    @NonNull
    private static  DateFormat getSimpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static String getFormat(long l){
        String t="";
        try{
           // Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
           // Date parse = format.parse(l);
            if (l!=0){
                t=format.format(new Date(l));
            }else{
                t = "未放款";
            }
        }catch (Exception e){
            LogUtils.e(e);

        }
        return t;

    }

}
