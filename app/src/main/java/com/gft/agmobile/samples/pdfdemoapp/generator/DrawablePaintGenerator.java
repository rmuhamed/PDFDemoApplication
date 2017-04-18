package com.gft.agmobile.samples.pdfdemoapp.generator;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.gft.agmobile.samples.pdfdemoapp.canvas.DrawablePaint;

/**
 * Created by romh on 18/04/2017.
 */

class DrawablePaintGenerator {
    private final int left;
    private final int top;
    private final int right;
    private final int bottom;

    private final int resourceId;
    private final Resources resources;

    public DrawablePaintGenerator(int left, int right, int bottom, int top, Resources resources, int resourceId) {
        this.left = left;
        this.right = right;
        this.bottom = bottom;
        this.top = top;

        this.resources = resources;
        this.resourceId = resourceId;
    }
    DrawablePaint generate() {
        return new DrawablePaint.Builder()
                .setLeft(this.left)
                .setTop(this.top)
                .setBottom(this.bottom)
                .setRight(this.right)
                .setResources(resources)
                .setResourceId(resourceId)
                .build();
    }

}
