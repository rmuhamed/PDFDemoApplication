package com.gft.agmobile.samples.pdfdemoapp.controller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.gft.agmobile.samples.pdfdemoapp.R;
import com.gft.agmobile.samples.pdfdemoapp.canvas.GenericPaint;

import java.util.List;

/**
 * Created by romh on 20/04/2017.
 */

public class DummyPDFDrawerController extends AbstractPDFDrawerController{

    public DummyPDFDrawerController(Context aContext, int displayWidth, int displayHeight, PDFDrawerControllerResultListener listener) {
        super(aContext, displayWidth, displayHeight, listener);
    }

    @Override
    public void make(@NonNull List<GenericPaint> genericPaintList) {
        // crate a page description
        PdfDocument.Page page = document.startPage(this.pageInfo);
        Canvas c = page.getCanvas();

        this.paintTitle(c);
        this.paintSubtitle(c);
        this.paintTableHeader(c);

        this.paintFooter(c);

        this.document.finishPage(page);

        this.write();
    }

    public void paintTitle(Canvas canvas) {
        Paint p = new Paint();
        p.setTextSize(80);
        p.setColor(ContextCompat.getColor(this.context, R.color.main));

        canvas.drawText(this.context.getString(R.string.document_title), 100, 150, p);
    }

    public void paintSubtitle(Canvas canvas) {
        Paint p2 = new Paint();
        p2.setTextSize(30);
        p2.setColor(ContextCompat.getColor(this.context, R.color.black));

        canvas.drawText(this.context.getString(R.string.txdetails_print_title), 100, 300, p2);
    }

    public void paintTableHeader(Canvas canvas) {
        String text = context.getString(R.string.txdetails_print_table_type);

        Paint p3 = new Paint();
        p3.setTextSize(40);
        p3.setColor(ContextCompat.getColor(this.context, R.color.black));

        Paint rectPaint = new Paint();
        rectPaint.setColor(ContextCompat.getColor(this.context, R.color.mediumGrey));

        canvas.drawRect(marginLeft, 550, marginRight, 700, rectPaint);
        canvas.drawText(text, 250, 600, p3);
    }

    private void paintFooter(Canvas canvas) {
        String footer = context.getString(R.string.txdetails_print_footer_imprint);

        Paint p3 = new Paint();
        p3.setTextSize(40);
        p3.setColor(ContextCompat.getColor(this.context, R.color.white));

        canvas.drawText(footer, this.marginLeft, this.marginBottom, p3);
    }
}
