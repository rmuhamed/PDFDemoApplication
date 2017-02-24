package com.gft.agmobile.samples.pdfdemoapp.utils;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by romh on 23/02/2017.
 */

public class FileGenerator {

    public static FileOutputStream getOutputStream(Context context, String filenName) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(filenName, Context.MODE_APPEND);
        } catch (FileNotFoundException e) {}

        return fileOutputStream;
    }
}
