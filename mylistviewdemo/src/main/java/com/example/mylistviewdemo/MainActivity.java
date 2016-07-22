package com.example.mylistviewdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView mlv;
    Context mcontext;
    ArrayList<DBread.TelClasslist> mlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        copy();
        mlv= (ListView) findViewById(R.id.lv);
        mlist= new ArrayList<DBread.TelClasslist>();
        mlist=DBread.readTelClasslist();
        mcontext=getApplicationContext();
        MyAdapter myadapter=new MyAdapter(mlist,mcontext);
        mlv.setAdapter(myadapter);
       mlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               for (int i =0; i <=mlist.size() ; i++) {
                   if (position==i){
                       Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                       intent.putExtra("table",i+1);// 名字和值
                       startActivity(intent);

                   }

               }
           }
       });









    }
    public void copy(){
        if (!DBread.isExistsTelfile()){
            try {
                CopyFile.copyAssetsToFile(getApplicationContext(),"commonnum.db",DBread.telfile);
                Toast.makeText(MainActivity.this, "好了没", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
