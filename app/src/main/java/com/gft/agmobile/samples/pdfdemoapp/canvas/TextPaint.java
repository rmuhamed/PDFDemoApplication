package com.gft.agmobile.samples.pdfdemoapp.canvas;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by romh on 23/02/2017.
 */

public class TextPaint implements GenericPaint {
    private final String text;
    private final float xPos;
    private final float yPos;
    private final Paint paint;
    private final int backgroundColor;


    private TextPaint(String text, float textSize, int backgroundColor, int textColor, float x, float y) {
        this.paint = new Paint();
        this.paint.setTextSize(textSize);
        this.paint.setColor(textColor);

        this.paint.setStyle(Paint.Style.FILL);

        this.backgroundColor = backgroundColor;

        this.xPos = x;
        this.yPos = y;

        this.text = text;
    }


    public void drawIn(Canvas canvas) {

        canvas.drawText(this.text, this.xPos, this.yPos, this.paint);
    }

    public static final class Builder {
        private static final float DEFAULT_TEXT_SIZE = 30.0f;
        private Integer textColor;
        private Integer backgroundColor;
        private Float textSize;
        private Float xPosition;
        private Float yPosition;

        private String text;

        public TextPaint build() {
            return new TextPaint(this.text,
                    this.textSize != null ? this.textSize.floatValue() : DEFAULT_TEXT_SIZE,
                    this.backgroundColor,
                    this.textColor,
                    this.xPosition!=null ? this.xPosition.floatValue() : 0f,
                    this.yPosition != null ? this.yPosition.floatValue() : 0f);
        }

        public Builder textSize(Float textSize) {
            this.textSize = Float.valueOf(textSize);
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Builder setBackgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder textColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        public Builder setX(float x) {
            this.xPosition = Float.valueOf(x);

            return this;
        }

        public Builder setY(float y) {
            this.yPosition = Float.valueOf(y);

            return this;
        }
    }
}
