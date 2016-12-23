package com.xinxinxuedai.db.initDb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator 于萌萌
 * 创建日期: 9:15 . 2016年12月23日
 * 描述:
 * <p>
 * <p>
 * 备注:
 */

public class MyDB extends SQLiteOpenHelper{
    String name;
    public MyDB(Context context, String name) {
        super(context, name, null, 1);
        this.name = name;

    }

    /**
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder builder = new StringBuilder();
        builder.append("create ")
                .append("table ")
                .append("MyJson ")
                .append("(_id integer primary key autoincrement,")
                .append("name varchar(20), ")
                .append("msg varchar(6))");

        db.execSQL(builder.toString());

    }

    /**
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
