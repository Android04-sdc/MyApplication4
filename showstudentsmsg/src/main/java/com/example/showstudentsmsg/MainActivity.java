package com.example.showstudentsmsg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
   static EditText met1;
   static EditText met2;
   static EditText met3;
    Button mbtn;
    ListView mlist;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        met1= (EditText) findViewById(R.id.et1);
        met2= (EditText) findViewById(R.id.et2);
        met3= (EditText) findViewById(R.id.et3);
        mbtn= (Button) findViewById(R.id.button);
        mlist= (ListView) findViewById(R.id.lv);
        mbtn.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
    DBManager.add();
        Toast.makeText(MainActivity.this, "插入成功", Toast.LENGTH_SHORT).show();
    }
}
