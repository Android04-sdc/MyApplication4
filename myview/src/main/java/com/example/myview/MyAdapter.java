package com.example.myview;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/12.
 */
public class MyAdapter extends PagerAdapter{
    ArrayList<View> mlist;
    public MyAdapter(ArrayList<View> list){
        mlist=list;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {//该方法会反复调用，直到添加完所有 的imageview
        container.addView(mlist.get(position),0);//0代表的是第一张出现的图片
        return mlist.get(position);//返回具体的imageview
    }

    @Override
    public int getCount() {
       if (mlist!=null){
           return mlist.size();
       }
        return 0;
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
