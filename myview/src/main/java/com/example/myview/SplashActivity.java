package com.example.myview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    ImageView IB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        IB= (ImageView) findViewById(R.id.iv);
        Animation animation=new RotateAnimation(0.0f,360f,0.5f,0.5f);
        animation.setRepeatCount(2);
        animation.setDuration(1000);
        IB.startAnimation(animation);
        Animation.AnimationListener a=new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override

            public void onAnimationEnd(Animation animation) {
                Intent intent=new Intent(SplashActivity.this,MyViewActivity.class);
                startActivity(intent);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        animation.setAnimationListener(a);

    }
}
