package com.gft.agmobile.samples.pdfdemoapp.generator;

import com.gft.agmobile.samples.pdfdemoapp.canvas.TextPaint;


/**
 * Created by rmuhamed on 1/3/17.
 */

class TextPaintGenerator {
    private final int x;
    private final int y;
    private final String text;
    private final int backgroundColor;
    private final int textColor;
    private final  float textSize;

    TextPaintGenerator(int x, int y, String text, int backgroundColor, int textColor, float textSize) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.backgroundColor = backgroundColor;
        this.textColor = textColor;
        this.textSize = textSize;
    }

    TextPaint generate() {
        return new TextPaint.Builder()
                .text(this.text)
                .setX(this.x)
                .setY(this.y)
                .setBackgroundColor(this.backgroundColor)
                .textColor(this.textColor)
                .textSize(this.textSize).build();
    }
}
