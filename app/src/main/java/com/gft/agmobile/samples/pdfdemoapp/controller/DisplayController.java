package com.gft.agmobile.samples.pdfdemoapp.controller;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by romh on 19/04/2017.
 */

public class DisplayController {

    private final Point point;

    private static DisplayController instance;
    private final Display display;

    public static DisplayController getInstance(Context context) {
        if (instance == null) {
            instance = new DisplayController(context);
        }

        return instance;
    }

    private DisplayController(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        this.display = wm.getDefaultDisplay();

        this.point = new Point();

        this.display.getSize(this.point);
    }

    public int getScreenWidth() {
        return this.point.x;
    }

    public int getScreenHeight() {
        return this.point.y;
    }

    public float getDensityScale() {
        DisplayMetrics metrics = new DisplayMetrics();
        this.display.getMetrics(metrics);

        return metrics.density;
    }
}
