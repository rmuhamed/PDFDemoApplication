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

public class DummyPDFDrawerController extends AbstractPDFDrawerController {

    public DummyPDFDrawerController(Context aContext, String fileName, int displayWidth, int displayHeight, PDFDrawerControllerResultListener listener) {
        super(aContext, fileName, displayWidth, displayHeight, listener);
    }

    @Override
    public void make(@NonNull List<GenericPaint> genericPaintList) {
        // crate a page description
        PdfDocument.Page page = this.document.startPage(this.pageInfo);
        Canvas c = page.getCanvas();

        this.paintTitle(c);
        this.paintSubtitle(c);
        this.paintTableHeader(c);
        this.paintFooter(c);

        this.document.finishPage(page);

        this.write();
    }

    private void paintTitle(Canvas canvas) {
        Paint p = new Paint();
        p.setTextSize(this.context.getResources().getDimension(R.dimen.title_text_size));
        p.setColor(ContextCompat.getColor(this.context, R.color.main));

        float titleMarginTop = this.context.getResources().getDimension(R.dimen.document_title_margin_top);

        canvas.drawText(this.context.getString(R.string.document_title), this.marginLeft, titleMarginTop, p);
    }

    private void paintSubtitle(Canvas canvas) {
        Paint aPaint = new Paint();
        aPaint.setTextSize(this.context.getResources().getDimension(R.dimen.text_size));
        aPaint.setColor(ContextCompat.getColor(this.context, R.color.black));

        float titleX = this.context.getResources().getDimension(R.dimen.document_left_margin);
        float titleY = this.context.getResources().getDimension(R.dimen.txdetails_print_title_margin_top);

        canvas.drawText(this.context.getString(R.string.txdetails_print_title), titleX, titleY, aPaint);
    }

    private void paintTableHeader(Canvas canvas) {
        Paint aPaint = new Paint();
        aPaint.setTextSize(this.context.getResources().getDimension(R.dimen.text_size));
        aPaint.setColor(ContextCompat.getColor(this.context, R.color.black));

        Paint rectPaint = new Paint();
        rectPaint.setColor(ContextCompat.getColor(this.context, R.color.mediumGrey));

        float marginTop = this.context.getResources().getDimension(R.dimen.table_header_margin_top);
        float tableHeaderHeight = marginTop + this.context.getResources().getDimension(R.dimen.table_header_height);

        canvas.drawRect(this.marginLeft, marginTop, this.marginRight, tableHeaderHeight, rectPaint);

        canvas.drawText(this.context.getString(R.string.txdetails_print_table_timestamp), this.marginLeft + 10, 400, aPaint);
        canvas.drawText(this.context.getString(R.string.txdetails_print_table_value_date), 250, 400, aPaint);
        canvas.drawText(this.context.getString(R.string.txdetails_print_table_summary), 300, 400, aPaint);
        canvas.drawText(this.context.getString(R.string.txdetails_print_table_summary), 320, 400, aPaint);

    }

    private void paintFooter(Canvas canvas) {
        String footer = this.context.getString(R.string.txdetails_print_footer_imprint);

        Paint aPaint = new Paint();
        aPaint.setTextSize(this.context.getResources().getDimension(R.dimen.text_size));
        aPaint.setColor(ContextCompat.getColor(this.context, R.color.white));

        canvas.drawText(footer, this.marginLeft, this.marginBottom, aPaint);
    }
}
