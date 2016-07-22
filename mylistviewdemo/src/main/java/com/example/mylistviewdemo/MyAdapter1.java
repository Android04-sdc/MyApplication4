package com.example.mylistviewdemo;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/12.
 */
public class MyAdapter1 extends PagerAdapter {
    ArrayList<View> mlist;

    public MyAdapter1(ArrayList<View> list){
        mlist=list;
    }



    @Override
    public int getCount() {
        if (mlist!=null){ return mlist.size();
        }
           return 0;

    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {//这个方法会反复调用
        container.addView( mlist.get(position),0);//默认的谁是第一张图片
        return mlist.get(position);

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView(mlist.get(position));

    }
}
