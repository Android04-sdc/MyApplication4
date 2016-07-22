package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/15.
 */
class MyAdapter extends BaseAdapter {
    ArrayList<String> mList;
    Context mcontext;


    public MyAdapter(ArrayList<String> mList, Context mcontext) {
        this.mList = mList;
        this.mcontext = mcontext;
    }

    @Override
    public int getCount() {

        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {

        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String s = mList.get(position);
        System.out.println(s);
        if (convertView == null) {
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.item_list_view_show, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tv);

        tv.setText(s);

        return convertView;
    }
}