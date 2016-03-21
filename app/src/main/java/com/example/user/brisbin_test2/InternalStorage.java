package com.example.user.brisbin_test2;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by gibsond on 11/20/2015.
 */
public class InternalStorage
{

    public static void write(Context context, String filename,String data) throws IOException
    {

        FileOutputStream fos = context.openFileOutput(filename,Context.MODE_PRIVATE);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);

        outputStreamWriter.write(data);
        outputStreamWriter.close();
    }

    public static String read(Context context, String filename) throws IOException
    {
        FileInputStream fis = context.openFileInput(filename);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");

        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();

    }
    public static String writePNG(Context context, Bitmap bitmapImage,String imgDirectory,String filename_wo_ext) throws IOException{
        ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir(imgDirectory, Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,filename_wo_ext + ".png");

        FileOutputStream fos = null;

        fos = new FileOutputStream(mypath);

        // Use the compress method on the BitMap object to write image to the OutputStream
        bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        fos.close();
        return directory.getAbsolutePath();
    }

    public static Bitmap readImage(Context context,String imageDirectory,String imagename) throws FileNotFoundException
    {
        ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir(imageDirectory, Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,imagename);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(mypath));
            return b;

    }

    public static String fileListAsString(Context context, String subDir,String listSeparator)
    {
        ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir(subDir, Context.MODE_PRIVATE);

        String[] list = directory.list();
        String s = "";
        for(int i=0; i < list.length;i++)
        {
            s +=  list[i];
            if(i < list.length-1)
            {
                s+= listSeparator;
            }
        }
        return s;
    }
}
