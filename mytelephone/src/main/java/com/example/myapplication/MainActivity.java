package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity  {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn= (Button) findViewById(R.id.button);


    }


        public void onClick(View v) {
            if (!DBRead.isExistsDBfile()) {
                try {
                    AssetsDBMannger.copyAssetsToFile(getApplicationContext(),"commonnum.db", DBRead.telfile);
                    Toast.makeText(MainActivity.this, "打印成功没", Toast.LENGTH_SHORT).show();
                }catch (IOException e) {
                    //Toast.makeText(MainActivity.this, "111", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
    }
}
