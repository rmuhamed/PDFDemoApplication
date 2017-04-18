package com.gft.agmobile.samples.pdfdemoapp.canvas;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by romh on 24/02/2017.
 */

public class RectPaint implements GenericPaint {
    private final Rect rect;
    private final Paint paint;

    private RectPaint(int left, int top, int right, int bottom, int backgroundColor) {
        this.rect = new Rect(left, top, right, bottom);

        this.paint = new Paint();
        this.paint.setColor(backgroundColor);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setTextSize(30);
    }

    @Override
    public void drawIn(Canvas canvas) {
        canvas.drawRect(this.rect, this.paint);
    }

    public static final class Builder {
        private Integer left;
        private Integer right;
        private Integer bottom;
        private Integer top;
        private Integer color;

        public RectPaint build() {
            return new RectPaint(this.left != null ? this.left.intValue() : 0,
                    this.top != null ? this.top.intValue() : 0,
                    this.right != null ? this.right.intValue() : 0,
                    this.bottom != null ? this.bottom.intValue() : 0,
                    this.color);
        }

        public Builder setLeft(int left) {
            this.left = Integer.valueOf(left);

            return this;
        }

        public Builder setRight(int right) {
            this.right = Integer.valueOf(right);

            return this;
        }

        public Builder setBottom(int bottom) {
            this.bottom = Integer.valueOf(bottom);

            return this;
        }

        public Builder setTop(int top) {
            this.top = Integer.valueOf(top);

            return this;
        }

        public Builder setColor(int color) {
            this.color = Integer.valueOf(color);

            return this;
        }
    }
}
