package com.example.mylistviewdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.camera2.params.Face;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/7/17.
 */
public class CopyFile {
    public static void copyAssetsToFile(Context context, String path,
                                        File toFile) throws IOException {
        InputStream inStream = null;
        //  输入流( 用来读取当前项目的 Assets  内的 db  文本)
        BufferedInputStream buffInputStream = null;
        //  输出流( 用来将读取到的 db  信息写到指定目录文件 toFile  中去)
        BufferedOutputStream buffOutputStream = null;
        try {
            inStream = context.getAssets().open(path);
            buffInputStream = new BufferedInputStream(inStream);
            buffOutputStream = new BufferedOutputStream(
                    new FileOutputStream(toFile, false));
            // IO  操作
            int len = 0;
            byte[] buff = new byte[ 2*1024];
            while ((len = buffInputStream.read(buff)) != -1) {
                buffOutputStream.write(buff, 0, len);
            }
            buffOutputStream.flush();
        } catch (IOException e) {
            throw e;
        } finally {
            // IO  关闭
            buffOutputStream.close();
            buffInputStream.close();
            inStream.close();

        }


    }
}
