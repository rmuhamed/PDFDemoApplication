package com.gft.agmobile.samples.pdfdemoapp.generator;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;

import com.gft.agmobile.samples.pdfdemoapp.R;
import com.gft.agmobile.samples.pdfdemoapp.canvas.DrawablePaint;
import com.gft.agmobile.samples.pdfdemoapp.canvas.GenericPaint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmuhamed on 1/3/17.
 */

public class PaintGenerator {

    public static List<GenericPaint> generate(Context context) {
        List<GenericPaint> paintToBeDrawn = new ArrayList<>();

        paintToBeDrawn.add(new DrawablePaintGenerator(200, 300, 100, 200, context.getResources(), R.drawable.o2banking_logo).generate());


        paintToBeDrawn.add(new TextPaintGenerator(100, 300,
                context.getString(R.string.txdetails_print_footer_imprint),
                ContextCompat.getColor(context, android.R.color.transparent),
                ContextCompat.getColor(context, R.color.black), 30)
                .generate());

        paintToBeDrawn.add(new TextPaintGenerator(200, 400,
                context.getString(R.string.txdetails_print_pdf_timestamp),
                ContextCompat.getColor(context, R.color.main),
                ContextCompat.getColor(context, R.color.white), 20)
                .generate());


        return paintToBeDrawn;
    }
}
