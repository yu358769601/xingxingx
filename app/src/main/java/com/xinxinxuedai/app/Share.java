package com.xinxinxuedai.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**sp
 * Created by Administrator on 2016/5/7.
 */
public class Share {
    private static final String SHARE_NAME = "xuedai_share_db";

    public static void saveToken(Context context,String token){
        save(context,"token",token);
    }
    public static void savePhoneNum(Context context,String phoneNum){
        save(context,"phoneNum",phoneNum);
    }


    public static void savefirst(Context context,boolean mboolean){
        save(context,"mboolean",mboolean);
    }
    public static void saveClientid (Context context,String clientid){
        save(context,"clientid",clientid);
    }
    public static String getClientid (Context context){
        return getString(context,"clientid");
    }

    public static boolean checkLogin(Context context){
        return !TextUtils.isEmpty(getToken(context));
    }
    public static boolean checkPhoneNum(Context context){
        return !TextUtils.isEmpty(getPhoneNum(context));
    }
    public static boolean checkLocation(Context context){
        return !TextUtils.isEmpty(getToken(context));
    }

    public static String getToken(Context context){
        return getString(context,"token");
    }
    public static String getPhoneNum(Context context){
        return getString(context,"phoneNum");
    }

    public static String getLocation(Context context){
        if (TextUtils.isEmpty(getString(context,"location"))){
            return "全国";
        }
        return getString(context,"location");
    }
    public static String getLocationCode(Context context){
        if (TextUtils.isEmpty(getString(context,"locationCode"))){
            return "0";
        }
        return getString(context,"locationCode");
    }
    public static String getLocationSheng(Context context){
        if (TextUtils.isEmpty(getString(context,"locationSheng"))){
            return "";
        }
        return getString(context,"locationSheng");
    }
    public static String getLocation1(Context context){
        if (TextUtils.isEmpty(getString(context,"location1"))){
            return "";
        }
        return getString(context,"location1");
    }
    public static String getLocation1Code(Context context){
        if (TextUtils.isEmpty(getString(context,"location1Code"))){
            return "";
        }
        return getString(context,"location1Code");
    }
    public static String getLocation2(Context context){
        if (TextUtils.isEmpty(getString(context,"location2"))){
            return "";
        }
        return getString(context,"location2");
    }
    public static String getLocation2Code(Context context){
        if (TextUtils.isEmpty(getString(context,"location2Code"))){
            return "";
        }
        return getString(context,"location2Code");
    }


    public static void setLocation(Context context,String location){
        save(context,"location",location);
    }
    public static void setLocation1(Context context,String location1){
        save(context,"location1",location1);
    }
    public static void setLocation1Code(Context context,String location1Code){
        save(context,"location1Code",location1Code);
    }
    public static void setLocation2(Context context,String location2){
        save(context,"location2",location2);
    }
    public static void setLocation2Code(Context context,String location2Code){
        save(context,"location2Code",location2Code);
    }

    public static void setLocationSheng(Context context,String locationSheng){
        save(context,"locationSheng",locationSheng);
    }
    public static void setLocationcode(Context context,String locationCode){
        save(context,"locationCode",locationCode);
    }

    public static boolean getFirst(Context context){
        return getBoolean(context,"mboolean");
    }

    public static void save(Context context,String key,String value){
        getEditor(context).putString(key,value).commit();
    }
    public static void saveInt(Context context,String key,int value){
        getEditor(context).putInt(key,value).commit();
    }
    public static void save(Context context,String key ,boolean b){
        getEditor(context).putBoolean(key,b).commit();
    }

    public static String getString(Context context,String key){
        return  getShare(context).getString(key,null);
    }
    public static int getInt(Context context,String key){
        return  getShare(context).getInt(key,0);
    }
    public static boolean getBoolean(Context context,String key){
        return  getShare(context).getBoolean(key,false);
    }

    private static SharedPreferences getShare(Context context){
        return context.getSharedPreferences(SHARE_NAME,Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(Context context){
        return getShare(context).edit();
    }

}
