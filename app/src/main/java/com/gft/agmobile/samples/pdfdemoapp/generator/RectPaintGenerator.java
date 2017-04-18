package com.gft.agmobile.samples.pdfdemoapp.generator;

import com.gft.agmobile.samples.pdfdemoapp.canvas.RectPaint;


/**
 * Created by rmuhamed on 1/3/17.
 */

class RectPaintGenerator {
    private final int left;
    private final int right;
    private final int top;
    private final int bottom;
    private final int backgroundColor;

    RectPaintGenerator(int left, int right, int top, int bottom, int backgroundColor) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        this.backgroundColor = backgroundColor;
    }

    RectPaint generate() {
        return new RectPaint.Builder()
                .setLeft(this.left)
                .setRight(this.right)
                .setTop(this.top)
                .setBottom(this.bottom)
                .setColor(this.backgroundColor)
                .build();
    }
}
