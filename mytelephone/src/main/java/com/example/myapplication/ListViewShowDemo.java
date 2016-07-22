package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewShowDemo extends AppCompatActivity {
    private ListView mLV;
    ArrayList<DBRead.Telclassinfo> mList;
    Context mcotext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_show_demo);
        mList = new ArrayList<>();
        mcotext=getApplicationContext();
        mLV= (ListView) findViewById(R.id.lv);
        mList=DBRead.readteldbclasslist();
        MyAdapter adapter=new MyAdapter(mList,mcotext);
        mLV.setAdapter(adapter);
//        mLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                for (int i = 0; i <mList.size() ; i++) {
//                    if (i==position){
//                        Intent intent=new Intent(ListViewShowDemo.this,Main2Activity.class);
//                        intent.putExtra("table",i);
//                        startActivity(intent);
//                    }
//                }
//
//
//            }
//        });



    }
}
