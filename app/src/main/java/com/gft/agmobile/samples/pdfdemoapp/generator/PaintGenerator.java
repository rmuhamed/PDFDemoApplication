package com.gft.agmobile.samples.pdfdemoapp.generator;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.gft.agmobile.samples.pdfdemoapp.R;
import com.gft.agmobile.samples.pdfdemoapp.canvas.GenericPaint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmuhamed on 1/3/17.
 */

public class PaintGenerator {

    public static List<GenericPaint> generate(Context context) {
        List<GenericPaint> paintToBeDrawn = new ArrayList<>();
        paintToBeDrawn.add(new TextPaintGenerator(100, 150, context.getString(R.string.TITLE), ContextCompat.getColor(context, android.R.color.holo_purple), 30).generate());
        paintToBeDrawn.add(new TextPaintGenerator(100, 300, context.getString(R.string.SECONDARY_TEXT), ContextCompat.getColor(context, android.R.color.holo_blue_dark), 30).generate());
        paintToBeDrawn.add(new RectPaintGenerator(200, 400, 300, 500, ContextCompat.getColor(context, android.R.color.holo_orange_dark)).generate());

        return paintToBeDrawn;
    }
}
