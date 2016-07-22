package com.example.mygridviewdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewDemo extends AppCompatActivity {
    GridView mgv;
    Context mcontext;
    int[] i={R.drawable.icon_filemgr,R.drawable.icon_rocket,R.drawable.icon_phonemgr,
            R.drawable.icon_sdclean,R.drawable.icon_softmgr,R.drawable.icon_telmgr};
    String[] title={"文件清理","手机加速","手机检测","垃圾清理","软件管理","通讯大全"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_demo);
        mgv= (GridView) findViewById(R.id.gv);



    }
    class grimAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return i.length;
        }

        @Override
        public Object getItem(int position) {
            return i[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView==null){
                convertView= LayoutInflater.from(mcontext).inflate(R.layout.gridview_list,null);

            }
            TextView tv= (TextView) convertView.findViewById(R.id.tv);
            tv.setText(title[position]);
            tv.setBackgroundResource(i[position]);


        }
    }
}
