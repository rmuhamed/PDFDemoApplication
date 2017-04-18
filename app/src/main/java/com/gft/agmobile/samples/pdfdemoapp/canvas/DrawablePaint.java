package com.gft.agmobile.samples.pdfdemoapp.canvas;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.IntegerRes;

/**
 * Created by romh on 18/04/2017.
 */

public class DrawablePaint implements GenericPaint {
    private final Rect src;
    private final Rect dest;

    private final Bitmap bitmap;
    private final Paint paint;


    private DrawablePaint(int left, int right, int bottom, int top, Resources resources, int resourceId) {
        this.paint = new Paint();

        this.bitmap = BitmapFactory.decodeResource(resources, resourceId);

        this.dest = new Rect(left, top, right, bottom);
        this.src = new Rect(left, top, right, bottom);
    }

    @Override
    public void drawIn(Canvas canvas) {
        canvas.drawBitmap(this.bitmap, this.src, this.dest, this.paint);
    }

    public static final class Builder {
        private Integer left;
        private Integer right;
        private Integer bottom;
        private Integer top;
        private Resources resources;
        @IntegerRes
        private Integer resourceId;

        public DrawablePaint build() {
            return new DrawablePaint(this.left != null ? this.left.intValue() : 0,
                    this.right != null ? this.top.intValue() : 0,
                    this.bottom != null ? this.bottom.intValue() : 0,
                    this.top != null ? this.top.intValue() : 0,
                    this.resources,
                    this.resourceId);
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

        public Builder setResources(Resources resources) {
            this.resources = resources;

            return this;
        }

        public Builder setResourceId(int resourceId) {
            this.resourceId = resourceId;

            return this;
        }
    }
}
