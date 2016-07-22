package com.example.mylistviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    ImageView imageView;
   // ImageButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //btn= (ImageButton) findViewById(R.id.ib);
        imageView= (ImageView) findViewById(R.id.iv);
       // Animation animationUtils=AnimationUtils.loadAnimation(this,R.anim.alpha);
        //imageView.startAnimation(animationUtils);
        Animation animation=new AlphaAnimation(1.0f,0.0f);
        animation.setAnimationListener(animationListener);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        imageView.startAnimation(animation);


       // animationUtils.setAnimationListener(animationListener);

//    Animation animation=new ScaleAnimation(0.0f,1.0f,0.0f,0.5f,0.5f,0.5f);
//
//        animation.setAnimationListener( animationListener);
//       // btn.startAnimation(animation);
//        animation.setRepeatCount(2);
//        animation.setStartOffset(1000);
//        animation.setDuration(1000);
//        animation.setFillAfter(true);





    }
    Animation.AnimationListener animationListener=new Animation.AnimationListener() {
        @Override
       public void onAnimationStart(Animation animation) {

        }

        @Override
       public void onAnimationEnd(Animation animation) {
            Intent intent=new Intent(SplashActivity.this,GridViewDemo1.class);
            startActivity(intent);
            finish();
        }

      @Override
       public void onAnimationRepeat(Animation animation) {

        }
   };

}
