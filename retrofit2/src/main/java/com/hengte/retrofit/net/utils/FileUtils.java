package com.hengte.retrofit.net.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    public static String SDPATH = Environment.getExternalStorageDirectory().getPath()
            + "/baolimanager/";


    public static File createSDDir(String dirName) throws IOException {
        File dir = new File(SDPATH + dirName);
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.e("test", dir.getAbsolutePath());

        }
        return dir;
    }


}
