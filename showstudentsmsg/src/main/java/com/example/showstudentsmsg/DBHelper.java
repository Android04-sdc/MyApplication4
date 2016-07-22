package com.example.showstudentsmsg;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/7/18.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;//数据库版本号
    private static final String DATABASE_NAME = "Students";//数据库名
    static final String TABLE_NAME="mytable";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE mytable (_id INTEGER PRIMARY KEY" +
                "AUTOINCREMENT,number VARCHAR(20),name VARCHAR(20), telnumber VARCHAR(20)");
//        ContentValues cv=new ContentValues();
//        cv.put("number", String.valueOf(MainActivity.met1.getText()));
//        cv.put("name", String.valueOf(MainActivity.met2.getText()));
//        cv.put("telnumber", String.valueOf(MainActivity.met3.getText()));
//        db.insert("mytable",null,cv);




    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }


}
