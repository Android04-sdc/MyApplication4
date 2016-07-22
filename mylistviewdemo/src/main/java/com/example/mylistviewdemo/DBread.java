package com.example.mylistviewdemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/17.
 */
public class DBread {
    static File telfile;

    static {//静态代码块，加载的时候先执行找到数据库
        String packname = "data/data/com.example.mylistviewdemo/";
       File path = new File(packname);
       path.mkdir();
       telfile = new File(packname, "commonnum.db");
}

    public static boolean isExistsTelfile() {//只有是静态 的在能在别的类中使用

        if (!telfile.exists() || telfile.length() <= 0) {
            return false;

        }else {
            return true;
        }
    }

    public static ArrayList<TelClasslist> readTelClasslist() {
        ArrayList<TelClasslist> arrayList = new ArrayList<TelClasslist>();
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(telfile, null);
        Cursor cursor = db.rawQuery("select * from classlist", null);//遍历这个表

            if (cursor.moveToFirst()) {
                do{
                String name = cursor.getString(cursor.getColumnIndex("name"));
                TelClasslist telClasslist = new TelClasslist(name);
                arrayList.add(telClasslist);
        }while (cursor.moveToNext()) ;
                cursor.close();
                db.close();
        }

        return arrayList;


    }


    static class TelClasslist {//定义一个类，用于上边添加
        String name;


        public TelClasslist(String name) {
            super();
            this.name = name;
        }
    }
}