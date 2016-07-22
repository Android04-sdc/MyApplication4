package com.example.my;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.IOException;

public class CNActivity extends AppCompatActivity {
    Context mcontext;
    String path;
    File tofile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cn);
        try {
            DBManger.copyAssetsToFile(getApplicationContext(),"assets/commonnum.db",tofile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
