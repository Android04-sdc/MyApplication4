package com.example.showstudentsmsg;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

//参考：http://blog.csdn.net/liuhe688/article/details/6715983
class DBManager {
    private DBHelper helper;
    static SQLiteDatabase db;

    public DBManager(Context context) {

        helper = new DBHelper(context);
        // 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
        // mFactory);
        // 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }


    public static void add() {

//    try {
        // 采用事务处理，确保数据完整性
//        db.beginTransaction(); // 开始事务

//                db.execSQL("INSERT INTO " + DBHelper.TABLE_NAME
//                        + " VALUES(null, ?, ?, ?)", new Object[]{MainActivity.met1.getText(),
//                        MainActivity.met2.getText(), MainActivity.met3.getText()});
                // 带两个参数的execSQL()方法，采用占位符参数？，把参数值放在后面，顺序对应
                // 一个参数的execSQL()方法中，用户输入特殊字符时需要转义
                // 使用占位符有效区分了这种情况

//            db.setTransactionSuccessful(); // 设置事务成功完成
//        } finally{
//            db.endTransaction(); // 结束事务
//        }
//        db.execSQL("CREATE TABLE mytable (_id INTEGER PRIMARY KEY" +
//                "AUTOINCREMENT,number VARCHAR(20),name VARCHAR(20), telnumber VARCHAR(20)");
        ContentValues cv=new ContentValues();
        cv.put("number", String.valueOf(MainActivity.met1.getText()));
        cv.put("name", String.valueOf(MainActivity.met2.getText()));
        cv.put("telnumber", String.valueOf(MainActivity.met3.getText()));
        db.insert("mytable",null,cv);

    }
}