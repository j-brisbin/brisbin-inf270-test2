package com.example.user.brisbin_test2;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by gibsond on 11/19/2015.
 */
public class SDCard
{

    public static boolean isExternalStorageAvailable()
    {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
        {
            return true;
        }
        return false;
    }
    public static boolean isExternalStorageWritable()
    {
        String state = Environment.getExternalStorageState();
        if(!Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
        {
            return false;
        }
        return true;

    }
    public static boolean isExternalStorageReadable()
    {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
        {
            return true;
        }
        return false;

    }
    public static boolean write(String basePath,String filename,String data) throws IOException
    {
        if(isExternalStorageWritable())
        {
            File root = Environment.getExternalStorageDirectory();

            if(basePath.charAt(basePath.length()-1) != File.separatorChar)
            {
                basePath += basePath + File.separator;
            }
            File dir = new File (root.getAbsolutePath() + basePath);
            if(!dir.exists());
            {
                dir.mkdirs();
            }

            File file = new File(dir,filename);

            FileOutputStream f = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(f);
            pw.write(data);
            pw.flush();
            pw.close();
            f.close();
            return true;
        }
        else
        {
            return false;
        }

    }
    public static String read(String basePath, String filename) throws  IOException{

        File root = Environment.getExternalStorageDirectory();

        if(basePath.charAt(basePath.length()-1) != File.separatorChar)
        {
            basePath += basePath + File.separator;
        }
        File file = new File (root.getAbsolutePath() + basePath + filename);
        FileInputStream fis = new FileInputStream(file);
        DataInputStream in = new DataInputStream(fis);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        String data = "";
        while ((line = br.readLine()) != null) {
            data += line;
        }
        in.close();
        return data;
    }
}
