package com.gft.agmobile.samples.pdfdemoapp.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Locale;

/**
 * Created by romh on 23/02/2017.
 */

public class FileUtils {

    public static FileOutputStream getOutputStream(Context context, String filenName) {
        String baseFolder = FileUtils.getBaseFolder(context);

        File file = new File(String.format(Locale.getDefault(), "%s/%s", baseFolder, filenName));
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return fos;
    }

    public static String getBaseFolder(Context context) {
        String baseFolder;

        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            baseFolder = context.getExternalFilesDir(null).getAbsolutePath();
        } else {
            baseFolder = context.getFilesDir().getAbsolutePath();
        }
        return baseFolder;
    }
}
