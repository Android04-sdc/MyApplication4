package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Path;
import android.os.Bundle;
import android.widget.ListView;

import java.io.File;
import java.io.IOException;

public class MyTelephoneViewDemo extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    public void copy(){
        try {
            AssetsDBMannger.copyAssetsToFile(getApplicationContext(),"commonnum.db",DBRead.telfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
