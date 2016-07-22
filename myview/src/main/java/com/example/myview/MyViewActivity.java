package com.example.myview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MyViewActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mVP;
    ArrayList<View> mlist;
    Button btn;
    int[] in={R.drawable.ab,R.drawable.cd,R.drawable.ef};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences("first", MODE_PRIVATE);
        boolean b = sp.getBoolean("first", true);
        if (!b) {
            startActivity(new Intent(MyViewActivity.this,SplashActivity.class));
        } else {

            setContentView(R.layout.activity_my_view);
            mVP = (ViewPager) findViewById(R.id.vp);
            btn = (Button) findViewById(R.id.btn);
            btn.setOnClickListener(this);
            mlist = new ArrayList<>();
            for (int i = 0; i < in.length; i++) {
                ImageView imageview = new ImageView(this);
                imageview.setImageResource(in[i]);
                mlist.add(imageview);
            }
            MyAdapter MA = new MyAdapter(mlist);
            mVP.setAdapter(MA);
            mVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position == 2) {
                        btn.setVisibility(View.VISIBLE);

                    } else {
                        btn.setVisibility(View.INVISIBLE);
                    }

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
        }

    @Override
    public void onClick(View v) {


        SharedPreferences sp=getSharedPreferences("first",MODE_PRIVATE);
        boolean b=sp.getBoolean("first",true);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("first",false);
        editor.apply();
        startActivity(new Intent(MyViewActivity.this,SplashActivity.class));
        finish();

    }
}

