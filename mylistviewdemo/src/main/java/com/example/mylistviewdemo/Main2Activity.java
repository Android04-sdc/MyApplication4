package com.example.mylistviewdemo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ListView mlv1;
    int idx;
    ArrayList<NumberTableList> mlist;
    Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final Intent intent = getIntent();//得到intent对象
        idx = intent.getIntExtra("table", -1);//接收前边传过来的值，只接受名字即可，值也就带过来了，-1是定值
        mlist = readnumberDemo(idx);
        mcontext = getApplicationContext();
        mlv1 = (ListView) findViewById(R.id.lv1);
        YouAdapter adapter = new YouAdapter(mlist, mcontext);
        mlv1.setAdapter(adapter);
        mlv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String number = mlist.get(position).number.toString().trim();
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + number));

                startActivity(intent);

                    }



        });




    }
    public static ArrayList<NumberTableList> readnumberDemo(int idx){
        ArrayList<NumberTableList> arrayList=new ArrayList<>();
        SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase(DBread.telfile,null);
        Cursor cursor=db.rawQuery("select * from table"+idx,null);
        if (cursor.moveToFirst()){
            do {
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String number=cursor.getString(cursor.getColumnIndex("number"));
                NumberTableList numberTableList=new NumberTableList(number,name);
                arrayList.add(numberTableList);
            }while (cursor.moveToNext());
            cursor.close();
            db.close();

        }
        return arrayList;



    }
    static class NumberTableList{
        String number;
        String name;

        public NumberTableList(String number, String name) {
            super();
            this.number = number;
            this.name = name;
        }
    }

}
