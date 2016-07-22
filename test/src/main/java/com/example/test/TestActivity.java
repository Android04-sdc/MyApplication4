package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {
    ListView mlv;
    ArrayList<String> mlist;
    Context mcntext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_test);
        super.onCreate(savedInstanceState);

        mcntext = getApplicationContext();
        mlist=new ArrayList<>();

        mlv= (ListView) findViewById(R.id.lv);
        for (int i = 0; i <50 ; i++) {
            mlist.add(""+i);
        }
    MyAdapter adapter=new MyAdapter(mlist,mcntext);
        mlv.setAdapter(adapter);


    }
}
