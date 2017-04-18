package com.gft.agmobile.samples.pdfdemoapp.generator;

import com.gft.agmobile.samples.pdfdemoapp.canvas.TextPaint;


/**
 * Created by rmuhamed on 1/3/17.
 */

class TextPaintGenerator {
    private int x;
    private int y;
    private String text;
    private int textColor;
    private float textSize;

    TextPaintGenerator(int x, int y, String text, int textColor, float textSize) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.textColor = textColor;
        this.textSize = textSize;
    }

    TextPaint generate() {
        return new TextPaint.Builder()
                .text(this.text)
                .setX(this.x)
                .setY(this.y)
                .textColor(this.textColor)
                .textSize(this.textSize).build();
    }
}
