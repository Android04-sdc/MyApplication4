package com.example.mylistviewdemo;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.regex.Pattern;

public class SpeedPhone extends AppCompatActivity {
    ListView mlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_speed);
        mlv= (ListView) findViewById(R.id.lv);
        String board = Build.BOARD;//
        String release = Build.VERSION.RELEASE;
        String sdk = Build.VERSION.SDK;
        String id = Build.ID;
        String cpuAbi = Build.CPU_ABI;
        String brand = Build.BRAND;
        String model = Build.MODEL;
        String radio = Build.RADIO;
        WifiManager wifimanager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo connectionInfo =wifimanager.getConnectionInfo();
        int ipAddress = connectionInfo.getIpAddress();//获得ip地址

        TelephonyManager telmanager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        int phoneType = telmanager.getPhoneType();
        String subscriberId = telmanager.getSubscriberId();//获取手机IMSI
        String deviceId = telmanager.getDeviceId();//获取手机IMEI
        String simOperatorName = telmanager.getSimOperatorName();//设备运营商名称
        String cpu= cpunumber();//获取手机的cpu数量
        String fileSize = getFileSize(getMemory());//获取手机的内存

        ActivityManager.MemoryInfo info=new ActivityManager.MemoryInfo();
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        manager.getMemoryInfo(info);
        long availMem = info.availMem;
        long l = availMem /(1024*1024) ;

        String displayMetrics = getDisplayMetrics();
        String cameraMetrics = getCameraMetrics();
        boolean b = getisRoot();


        int[] i={R.drawable.setting_info_icon_version,
                R.drawable.setting_info_icon_space,
                R.drawable.setting_info_icon_cpu,
                R.drawable.setting_info_icon_camera,
                R.drawable.setting_info_icon_root
        };


        String[] str= new String[]{" 设备品牌："+brand,"手机内存："+fileSize,
                "cpu名称："+cpuAbi,"手机分辨率:"+displayMetrics,"基带版本"+radio
//          " 设备系统sdk版本号:"+sdk,
//                " 设备设置版本号:"+id,
//                " 设备型号名称"+model, "ip地址："+ip,
        };
        String[] str1={"系统版本号："+release,"手机可用内存:"+l+"MB","cpu数量："+cpu,
                "相机分辨率:"+cameraMetrics,"是否root:"+b};
        mlv.setAdapter(new myadapter(str,i,str1));
    }
    private String longgetip(long ip){
        StringBuffer sb=new StringBuffer();
        sb.append(String.valueOf(ip & 0x000000ff));
        sb.append(".");
        sb.append(String.valueOf((ip & 0x0000ffff)>>>8));
        sb.append(".");
        sb.append(String.valueOf((ip & 0x00ffffff)>>>16));
        sb.append(".");
        sb.append(String.valueOf((ip & 0xffffffff)>>>24));
        return sb.toString();
    }
    class myadapter extends BaseAdapter{
        String[] str;
        int[] i;
        String[]str1;
        public myadapter(String[] str,int[] i,String[] str1) {
            this.str=str;
            this.i=i;
            this.str1=str1;
        }

        @Override
        public int getCount() {
            return str.length;
        }

        @Override
        public Object getItem(int position) {
            return str[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
            convertView= LayoutInflater.from(SpeedPhone.this).inflate(R.layout.item_show,null);
            }
            TextView mtv= (TextView) convertView.findViewById(R.id.tv);
            TextView mtv1= (TextView) convertView.findViewById(R.id.tv1);
            ImageView miv= (ImageView) convertView.findViewById(R.id.im);
            miv.setImageResource(i[position]);
            mtv.setText(str[position]);
            mtv1.setText(str1[position]);
            return convertView;
        }
    }
    static class CPUFile implements FileFilter{//测试指定的文件夹是否在文件列表中
        @Override
        public boolean accept(File pathname) {
            if (Pattern.matches("cpu[0-9]",pathname.getName())){//找出CPU匹配的字符
                return true;
            }
            return false;
        }
    }
    private static String cpunumber(){
        File dir=new File("/sys/devices/system/cpu/");//找到文件所在的位置
        File[] files=dir.listFiles(new CPUFile());//找出属于CPU的文件，形成集合
        return files.length+"";
    }
    private static long getMemory(){
        try {
            FileReader file=new FileReader("/proc/meminfo");//内存在这个文件夹里放着，先找到这个文件夹
            BufferedReader br=new BufferedReader(file);//把他放在缓存输入流里边
                String s = br.readLine();//读出来
                String[] split=s.split("\\s+");//按照空格拆分形成数组
                return Long.valueOf(split[1])*1024;//解析第一个元素的大小，这就是内存的大小
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    private static DecimalFormat sformat=new DecimalFormat("#00");
    private static String getFileSize(long filesize) {//判断内存的大小，需要传入一个参数：就是需要判断的文件
        StringBuffer sb = new StringBuffer();//定义一个sb对象，用来追加用的
        /**
         * 判断文件具体的大小，然后对应的输出相应的值
         */
        if (filesize < 1024) {
            sb.append(filesize);
            sb.append("byte");
        } else if (filesize < 1024 * 1024) {
            sb.append(sformat.format((double) filesize / 1024));
            sb.append("KB");

        } else if (filesize < 1024 * 1024 * 1024) {
            sb.append(sformat.format((double) filesize / (1024 * 1024)));
            sb.append("MB");
        } else if (filesize < 1024 * 1024 * 1024 * 1024) {
            sb.append(sformat.format((double) filesize / (1024 * 1024 * 1024)));
            sb.append("GB");

        }
        return sb.toString();

    }
    private  String getDisplayMetrics(){//手机分辨率
        DisplayMetrics metrics=new DisplayMetrics();//首先得到一个屏幕测量的对象
        getWindowManager().getDefaultDisplay().getMetrics(metrics);//从窗口管理器到默认的屏幕，在测量，然后把测量的对象放进去
        String s = metrics.widthPixels + "*" + metrics.heightPixels;//测量的宽的像素点，高的像素点
        return s;

    }
    private String getCameraMetrics(){//相机分辨率
        Camera camera=Camera.open();//操作硬件的
        Camera.Parameters parameters = camera.getParameters();//取得当前设备的所有参数的对象
        List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();//参数中得到支持的照片的大小
        Camera.Size size=null;//假设size是空的
        for (Camera.Size s:supportedPictureSizes ) {
            if (size==null){
                size=s;//开始肯定是空的，然后赋值给他
            }else if(size.width*size.height<s.width*s.height){//size已经有值，他就跟集和里所有的元素进行比较，的出最大的值然后再付给size
                size=s;
            }
        }
        String maxsize=size.width+"*"+size.height;//得到,maxsize
        camera.release();;//最后要释放
        return maxsize;//返回最大的值

    }
    private boolean getisRoot(){//判断是否root
        boolean isRoot=false;
        if (!new File("system/bin/su").exists()&&!new File("system/xbin/su").exists()){//判断存不存在这两个文件，如果root了，会存在这两个文件
            isRoot=false;
        }else{
            isRoot=true;
        }
        return isRoot;
    }
    public class BatteryBroadcast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)){
                Bundle bundle = intent.getExtras();
                int  maxPower = (int) bundle.get(BatteryManager.EXTRA_SCALE);
                int  currentPower = (int) bundle.get(BatteryManager.EXTRA_LEVEL);
                int currenttemperature = (int) bundle.get(BatteryManager.EXTRA_TEMPERATURE);
               double f=currenttemperature/10.0;



            }
        }
    }
}
