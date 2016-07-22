package com.example.messagephone;

import android.app.ActivityManager;
import android.hardware.Camera;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
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
    private static final String TAG = "SpeedPhone";
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
        long memory = getMemory();//获取手机的内存
        String s = Formatter.formatFileSize(this, memory);

        Log.d(TAG, "onCreate: " + s);
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


        String[] str= {" 设备品牌："+brand,"手机内存："+s,
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
    static class CPUFile implements FileFilter{
        @Override
        public boolean accept(File pathname) {
            if (Pattern.matches("cpu[0-9]",pathname.getName())){
                return true;
            }
            return false;
        }
    }
    private static String cpunumber(){
        File dir=new File("/sys/devices/system/cpu/");
        File[] files=dir.listFiles(new CPUFile());
        return files.length+"";
    }
    private long getMemory(){
        try {
            FileReader file=new FileReader("/proc/meminfo");
            BufferedReader br=new BufferedReader(file);
            String s = br.readLine();
            String[] split=s.split("\\s+");
            return Long.valueOf(split[1])*1024;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    private static DecimalFormat sformat=new DecimalFormat("#00");
    private static String getFileSize(long filesize) {//判断内存的大小
        StringBuffer sb = new StringBuffer();
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
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        String s = metrics.widthPixels + "*" + metrics.heightPixels;
        return s;

    }
    private String getCameraMetrics(){//相机分辨率
        Camera camera=Camera.open();
        Camera.Parameters parameters = camera.getParameters();
        List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
        Camera.Size size=null;
        for (Camera.Size s:supportedPictureSizes ) {
            if (size==null){
                size=s;
            }else if(size.width*size.height<s.width*s.height){
                size=s;

            }
        }
        String maxsize=size.width+"*"+size.height;
        camera.release();;
        return maxsize;

    }
    private boolean getisRoot(){//判断是否root
        boolean isRoot=false;
        if (!new File("system/bin/su").exists()&&!new File("system/xbin/su").exists()){
            isRoot=false;
        }else{
            isRoot=true;
        }
        return isRoot;
    }
}
