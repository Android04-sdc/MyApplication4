package com.example.mylistviewdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class ViewPagerActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mVP;
    Button btn;
    ArrayList<View> mlist;//方视图控件
    int[] in = {R.drawable.ab, R.drawable.cd, R.drawable.ef};//存放图片资源

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences SP=getSharedPreferences("is_first_run",MODE_PRIVATE);//实例化对象，
        boolean b=SP.getBoolean("is_first_run",true);//判断是不是第一次运行，赋值键值对,只是一个期望值
        if (!b){
            startActivity(new Intent(ViewPagerActivity.this,SplashActivity.class));//如果不是就跳转别的界面
            finish();
        }else{
            initview();//重新加载布局
            initadapter();

        }
        mVP.setPageTransformer(true,new DepthPageTransformer());





    }
    @Override
    public void onClick(View v) {
        SharedPreferences SP=getSharedPreferences("is_first_run",MODE_PRIVATE);

        SharedPreferences.Editor editor= SP.edit();
        editor.putBoolean("is_first_run",false);
        editor.apply();
        startActivity(new Intent(ViewPagerActivity.this,SplashActivity.class));
        finish();//结束界面
//


    }


    private void initadapter() {
        MyAdapter1 MA = new MyAdapter1(mlist);
        mVP.setAdapter(MA);
        mVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==2){
                    btn.setVisibility(View.VISIBLE);


                }else{
                    btn.setVisibility(View.INVISIBLE);
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initview() {
        setContentView(R.layout.activity_viiew_pager);
        mVP = (ViewPager) findViewById(R.id.vp);
        btn= (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        mlist = new ArrayList<>();
        for (int i = 0; i < in.length; i++) {
            ImageView imageview = new ImageView(this);
            imageview.setImageResource(in[i]);
            mlist.add(imageview);


        }
    }



    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }


}

