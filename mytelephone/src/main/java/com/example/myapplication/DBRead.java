package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/14.
 */
public class DBRead {
     static File telfile;

    static {//先找到数据库
        String packagename="data/data/com.example.myapplication/";//指定地址
        File path=new File(packagename);
      path.mkdirs();
        telfile=new File(packagename,"commonnum.db");//数据库的绝对地址




    }
    public static  boolean isExistsDBfile(){
        File file =DBRead.telfile;
        if(!file.exists()||file.length()<=0){
            return false;

        }
        return true;

    }
    public  static ArrayList<Telclassinfo> readteldbclasslist() {
        ArrayList<Telclassinfo> telclassinfos = new ArrayList<Telclassinfo>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        db = SQLiteDatabase.openOrCreateDatabase(telfile, null);
        cursor = db.rawQuery("select * from classlist", null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                int idx = cursor.getInt(cursor.getColumnIndex("idx"));
                Telclassinfo telclassinfo = new Telclassinfo(name, idx);
                telclassinfos.add(telclassinfo);

            } while (cursor.moveToNext());
            cursor.close();
            db.close();

        }
            return telclassinfos;
    }
    public static ArrayList<Telnumberinfo> readteldbtable(int idx){
        ArrayList<Telnumberinfo> telnumberinfos=new ArrayList<>();
        SQLiteDatabase db=null;
        Cursor cursor=null;
        db=SQLiteDatabase.openOrCreateDatabase(telfile,null);
        cursor=db.rawQuery("select * from table"+idx,null);
        if (cursor.moveToFirst()){
           do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String number = cursor.getString(cursor.getColumnIndex("number"));
                Telnumberinfo telnumberinfo = new Telnumberinfo(name, number);
                telnumberinfos.add(telnumberinfo);
           }while (cursor.moveToNext());
            cursor.close();
            db.close();

        }
        return telnumberinfos;



    }





    public static class Telclassinfo{
        String name;
        int idx;
        public Telclassinfo(String name,int  idx) {
            super();
            this.name=name;
            this.idx=idx;


        }
    }
    public static class Telnumberinfo{
        String name;
        String number;

        public Telnumberinfo(String name, String number) {
            super();
            this.name = name;
            this.number = number;
        }
    }



}
