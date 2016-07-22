package com.example.mylistviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/17.
 */

    class YouAdapter extends BaseAdapter {
 static ArrayList<Main2Activity.NumberTableList> mlst;
  static   Context mcontext;

    public YouAdapter(ArrayList<Main2Activity.NumberTableList> mlst, Context mcontext) {
        this.mlst = mlst;
        this.mcontext = mcontext;
    }

    @Override
        public int getCount() {
            return mlst.size();
        }

        @Override
        public Object getItem(int position) {
            return mlst.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView= LayoutInflater.from(mcontext).inflate(R.layout.item_list_show1,null);


            }
            TextView tv1= (TextView) convertView.findViewById(R.id.tv1);
            TextView tv2= (TextView) convertView.findViewById(R.id.tv2);
            tv1.setText(mlst.get(position).name);
            tv2.setText(mlst.get(position).number);
            return convertView;
        }
    }

