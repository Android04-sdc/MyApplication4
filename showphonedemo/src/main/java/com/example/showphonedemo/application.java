package com.example.showphonedemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class application extends AppCompatActivity {
    ArrayList<PackageInfo> mlist;
    ListView mlv;
    ArrayList<Drawable> mlist1;
    ArrayList<CharSequence> mlist2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        mlv= (ListView) findViewById(R.id.lv);
        mlist=new ArrayList<>();
        mlist1=new ArrayList<>();
        mlist2=new ArrayList<>();
        PackageManager packageManager=this.getPackageManager();
        List<PackageInfo> infolist = packageManager.getInstalledPackages(0);//0表示所有的软件
        for (int i = 0; i <infolist.size() ; i++) {
            PackageInfo packageinfo=infolist.get(i);
            Drawable drawable = packageinfo.applicationInfo.loadIcon(packageManager);//得到应用的图标
            CharSequence charSequence = packageinfo.applicationInfo.loadLabel(packageManager);//得到应用的应用名
            ApplicationInfo Info=packageinfo.applicationInfo;
            if ((Info.flags & ApplicationInfo.FLAG_SYSTEM)<=0){//这个是用来判断是不是手机自己的，还是从外界下载的
                mlist.add(packageinfo);//把得到的属于哪些的packageinfo对象加进集合
                mlist1.add(drawable);//把图标加进集合
                mlist2.add(charSequence);//应用名加入集合
            }
        }
        mlv.setAdapter(new adapter());
        mlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(Intent.ACTION_DELETE);
                intent.setData(Uri.parse("package:"+mlist.get(position).packageName));
                startActivity(intent);
            }
        });
    }
    class adapter extends BaseAdapter{
        @Override
        public int getCount() {
            return mlist.size();
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
            viewHolder holder;
            if (convertView==null){
                holder=new viewHolder();
                convertView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.show,null);
                holder.mimageview= (ImageView) convertView.findViewById(R.id.iv);//将他们定义成静态的
                holder.mtextview1= (TextView) convertView.findViewById(R.id.tv);
               holder.mtextview2= (TextView) convertView.findViewById(R.id.tv1);
               holder.mtextview3= (TextView) convertView.findViewById(R.id.tv2);
                convertView.setTag(holder);//来设置 holder,为后边的得到holderd对象做准备

            }else {
                holder= (viewHolder) convertView.getTag();//得到的是holder的对象，holder=new viewHolder();
            }
            holder.mimageview.setPadding(10,10,10,10);//设置图标的位置
            holder.mimageview.setImageDrawable(mlist1.get(position));
            holder.mtextview2.setText(mlist2.get(position));
            holder.mtextview1.setText(mlist.get(position).versionName);
            holder.mtextview3.setText(mlist.get(position).packageName);
            return convertView;
        }
    }
    private static  final class viewHolder{
        ImageView mimageview;
        TextView mtextview1;
        TextView mtextview2;
        TextView mtextview3;
    }
}
