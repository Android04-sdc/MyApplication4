package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/14.
 */
public class MyAdapter extends BaseAdapter {
    ArrayList<DBRead.Telclassinfo> mList;//
    Context mcontext;


    public MyAdapter(ArrayList<DBRead.Telclassinfo> mList, Context mcontext) {
        this.mList = mList;
        this.mcontext = mcontext;
    }

    @Override
    public int getCount() {
        if (mList.size() != 0) {

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
        if (convertView == null) {
            convertView = LayoutInflater.from(mcontext).inflate(R.layout.item_list_show, null);
        }

        TextView tv = (TextView) convertView.findViewById(R.id.tv1);
        tv.setText( mList.get(position).name);//text不能是数字，只能是字符串
            return convertView;
        }
    }

