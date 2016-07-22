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
public class MyAdapter extends BaseAdapter {
    ArrayList<DBread.TelClasslist> mlist;
    Context mcontext;

    public MyAdapter(ArrayList<DBread.TelClasslist> mlist, Context mcontext) {
        this.mlist = mlist;
        this.mcontext = mcontext;
    }



    @Override
    public int getCount() {
        if (mlist!=null){
            return mlist.size();

        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView= LayoutInflater.from(mcontext).inflate(R.layout.item_list_show,null);

        }
        TextView mtv= (TextView) convertView.findViewById(R.id.tv);
        mtv.setText(mlist.get(position).name);
        return convertView;
    }
}
