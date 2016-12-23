package com.xinxinxuedai.db.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xinxinxuedai.db.bean.JsonAll;
import com.xinxinxuedai.db.initDb.MyDB;

import java.util.ArrayList;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 9:17 . 2016年12月23日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class MyDbDAO<T> {
    T t;
    private final MyDB mJson;

    public MyDbDAO(Context context,T t) {
        mJson = new MyDB(context, "MyJson");
        this.t =t;
    }
//    public MyDbDAO(Context context) {
//        mJson = new MyDB(context, "MyJson");
//    }

    /**
     * 添加一个学生
     * @param name 姓名
     * @param msg 性别,male female
     * @return result 添加到数据库的那一行, -1添加失败
     */
    public long add(String tableName,String name,T msg){
        SQLiteDatabase  db = mJson.getWritableDatabase();
        //db.execSQL("insert into student (name,sex) values (?,?)", new Object[]{name,sex});
        ContentValues values =new ContentValues();

        String s = JSONArray.toJSONString(msg);
//        if (name==null){
//            values.put("name", msg.getClass().getSimpleName());
//        }else{
            values.put("name", name);
//        }
        values.put("msg", s);
        long result = db.insert(tableName, null, values); //组拼sql语句实 现的.带返回值
        db.close();//释放资源
        return result;
    }
    /**
     * 添加条目 带判断 的如果之前有 这个条目 我就 修改字段里面的值 如果没有我就添加进去
     * @param name 姓名
     * @param msg 性别,male female
     * @return result 添加到数据库的那一行, -1添加失败
     */
//    public long add(String tableName,String name,T msg){
//        SQLiteDatabase  db = mJson.getWritableDatabase();
//        //db.execSQL("insert into student (name,sex) values (?,?)", new Object[]{name,sex});
//        ContentValues values =new ContentValues();
//
//        String s = JSONArray.toJSONString(msg);
//        if (name==null){
//            values.put("name", msg.getClass().getName());
//        }else{
//            values.put("name", name);
//        }
//        values.put("msg", s);
//        long result = db.insert(tableName, null, values); //组拼sql语句实 现的.带返回值
//        db.close();//释放资源
//        return result;
//    }

    /**
     * 删除一个学生
     * @param name 姓名
     * @return result 删除了几行 0 代表删除失败
     */
    public int delete(String tableName,String name){
        SQLiteDatabase  db = mJson.getWritableDatabase();
        //db.execSQL("delete from student where name=?",new Object[]{name});
        int result = db.delete(tableName, "name=?", new String[]{name});
        db.close();//释放资源
        return result;
    }

    /**
     * 修改一个学生的性别
     * @param name 姓名
     * @param msg 新的性别
     * @return 更新了几行 0更新失败
     */
    public int update(String tableName,String name,T msg){
        SQLiteDatabase  db = mJson.getWritableDatabase();
        //db.execSQL("update student set sex =? where name=?",new Object[]{newsex,name});
        ContentValues values = new ContentValues();
        String s = JSONArray.toJSONString(msg);
        values.put("msg", s);
        int result = db.update(tableName, values, "name=?", new String[]{name});
        db.close();//释放资源
        return result;
    }
    /**
     * 查询学生的性别
     * @param name 学生的姓名
     * @return 学生性别 null代表学生不存在
     */
    public void find(String tableName ,String name,dbCallBackHelper<T> back){
        Class<T> t1 = (Class<T>) t;
        String msg
                = null;
        SQLiteDatabase  db = mJson.getReadableDatabase();
        //结果集 游标
        //Cursor cursor = db.rawQuery("select sex from student where name=?", new String[]{name});
        String names = "";
//        if (null==name){
//            names =t.getClass().getSimpleName();
//            LogUtils.i("类名是"+names);
//        }else{
            names =name;
//        }
        Cursor cursor = db.query(tableName, new String[]{"msg"}, "name=?", new String[]{names}, null, null, null);
        boolean result = cursor.moveToNext();
        if(result){
            msg = cursor.getString(0);
        }
        cursor.close();//释放资源
        db.close();

        if (null==msg){
            back.getDataError("数据库没有此条目");
            return;
        }else{
            JSONObject jsonObject = JSONArray.parseObject(msg);
            back.getDataSuccess(jsonObject.toJavaObject(t1));
        }
    }
//    /**
//     * 获取全部的json信息
//     * @return
//     */
    public void findAll(String table,dbCallBackHelper<JsonAll> back){
        ArrayList<JsonAll> arrayList =new ArrayList<>();
        SQLiteDatabase db = mJson.getReadableDatabase();
        //Cursor cursor = db.rawQuery("select name, sex from student", null);
        Cursor cursor =  db.query(table, new String[]{"name","msg"}, null, null, null, null, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(0);
            String msg = cursor.getString(1);
            JsonAll jsonAll = new JsonAll();
            if (null!=name)
                jsonAll.setName(name);
            if (null!=msg){
                //JSONObject jsonObject = JSONArray.parseObject(msg);
                jsonAll.setMsg(msg);
            }
            arrayList.add(jsonAll);
        }
        cursor.close();
        db.close();
        back.getAllDataSuccess(arrayList);
    }



}
