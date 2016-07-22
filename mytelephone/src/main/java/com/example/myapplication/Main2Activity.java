package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private ListView mLV;
    ArrayList<DBRead.Telnumberinfo> mList;
    Context mcotext;
    int idx;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//        Intent intent=getIntent();
//        idx = intent.getIntExtra("table", -1);
        mList = new ArrayList<>();
        mcotext=getApplicationContext();
        mLV= (ListView) findViewById(R.id.lv2);
        mList=DBRead.readteldbtable(idx);

//        mLV.setAdapter(new Myadapter(mList,mcotext));




    }
//    class Myadapter extends BaseAdapter{
//
//        public Myadapter(ArrayList<Telnumberinfo> list,Context context) {
//            mList=list;
//            mcotext=context;
//        }
//
//        @Override
//        public int getCount() {
//            return mList.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//
//            return mList.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if (convertView!=null){
//                convertView= LayoutInflater.from(Main2Activity.this).inflate(R.layout.item_list_show2,null);
//            }
//            TextView tv= (TextView) convertView.findViewById(R.id.vt);
//            TextView tv1= (TextView) convertView.findViewById(R.id.vt1);
//
//            tv.setText(mList.get(position).name);
//            tv1.setText(mList.get(position).number);
//            return convertView;
//        }
//    }
//    public static ArrayList<Telnumberinfo> readteldbtable(int idx){
//        ArrayList<Telnumberinfo> telnumberinfos=new ArrayList<>();
//        SQLiteDatabase db=null;
//        Cursor cursor=null;
//        db=SQLiteDatabase.openOrCreateDatabase(DBRead.telfile,null);
//        cursor=db.rawQuery("select * from table"+idx,null);
//        if (cursor.moveToFirst()){
//            do {
//                String name = cursor.getString(cursor.getColumnIndex("name"));
//                String number = cursor.getString(cursor.getColumnIndex("number"));
//                Telnumberinfo telnumberinfo = new Telnumberinfo(name, number);
//                telnumberinfos.add(telnumberinfo);
//            }while (cursor.moveToNext());
//
//        }
//        return telnumberinfos;
//
//
//
//    }
//    public static class Telnumberinfo{
//        String name;
//        String number;
//
//        public Telnumberinfo(String name, String number) {
//            super();
//            this.name = name;
//            this.number = number;
//        }
//    }

}
