package com.example.mylistviewdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class GridViewDemo1 extends AppCompatActivity {
    GridView mgv;


    int[] i = {R.drawable.icon_filemgr, R.drawable.icon_rocket, R.drawable.icon_phonemgr,
            R.drawable.icon_sdclean, R.drawable.icon_softmgr, R.drawable.icon_telmgr};
    String[] title = {"文件清理", "手机加速", "手机检测", "垃圾清理", "软件管理", "通讯大全"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_demo1);
        mgv = (GridView) findViewById(R.id.gv);
        mgv.setAdapter( new adapter());
        mgv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==5){
                    Intent intent=new Intent(GridViewDemo1.this,MainActivity.class);
                    startActivity(intent);
                }
                if (position==4){
                    Intent intent=new Intent(GridViewDemo1.this,application.class);
                    startActivity(intent);
                }
                if (position==2){
                    Intent intent=new Intent(GridViewDemo1.this,SpeedPhone.class);
                    startActivity(intent);
                }

            }
        });

    }
    class adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public Object getItem(int position) {
            return  title[position];
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                    convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.textview_show, null);
                }
                TextView tv = (TextView) convertView.findViewById(R.id.tv);
                tv.setText(title[position]);
                ImageView iv= (ImageView) convertView.findViewById(R.id.iv);
                iv.setImageResource(i[position]);
                return convertView;
        }
    }
}