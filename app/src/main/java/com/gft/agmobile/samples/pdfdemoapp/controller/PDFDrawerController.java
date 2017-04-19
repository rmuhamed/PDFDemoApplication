package com.gft.agmobile.samples.pdfdemoapp.controller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfDocument.Page;
import android.graphics.pdf.PdfDocument.PageInfo;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.view.WindowManager;

import com.gft.agmobile.samples.pdfdemoapp.R;
import com.gft.agmobile.samples.pdfdemoapp.canvas.GenericPaint;
import com.gft.agmobile.samples.pdfdemoapp.utils.FileUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by romh on 23/02/2017.
 */

public class PDFDrawerController {

    public static final String FILE_NAME = "demoPDF.pdf";
    public static final String TITLE = "O2 Banking";

    public static final String SECONDARY_TEXT = "Umsatzayskunft O2 Banking Konto";


    private final Context context;
    private final PDFDrawerControllerResultListener PDFDrawerControllerResultListener;

    private final PageInfo pageInfo;
    private final PdfDocument document;

    private final int marginRight;
    private final int marginLeft;
    private final int marginTop;
    private final int marginBottom;


    public PDFDrawerController(Context aContext, int displayWidth, int displayHeight, PDFDrawerControllerResultListener listener) {
        this.context = aContext;
        this.PDFDrawerControllerResultListener = listener;

        this.marginLeft = 100;
        this.marginRight = displayWidth - 100;
        this.marginTop = 100;
        this.marginBottom = displayHeight - 100;

        this.document = new PdfDocument();
        this.pageInfo = new PageInfo.Builder(displayWidth, displayHeight, 1).create();
    }

    public void make(@NonNull List<GenericPaint> genericPaintList) {
        Page page = this.document.startPage(this.pageInfo);
        Canvas c = page.getCanvas();

        for (GenericPaint genericPaint : genericPaintList) {
            genericPaint.drawIn(c);
        }

        this.document.finishPage(page);

        this.write();
    }

    public void write() {

        try {
            FileOutputStream fos = FileUtils.getOutputStream(this.context, FILE_NAME);
            this.document.writeTo(fos);

            this.PDFDrawerControllerResultListener.onSuccess(FILE_NAME);
        } catch (IOException e) {
            this.PDFDrawerControllerResultListener.onSomeError(e.toString());
        } finally {
            // close the document
            this.document.close();
        }
    }

    //Dummy test
    public void makeDummyPage()  {
        // crate a page description
        Page page = document.startPage(this.pageInfo);
        Canvas c = page.getCanvas();



        this.paintTitle(c);
        this.paintSubtitle(c);
        this.paintTableHeader(c);

        this.paintFooter(c);


        this.document.finishPage(page);

        this.write();
    }

    private void paintFooter(Canvas canvas) {
        String footer = context.getString(R.string.txdetails_print_footer_imprint);

        Paint p3 = new Paint();
        p3.setTextSize(40);
        p3.setColor(ContextCompat.getColor(this.context, R.color.white));

        canvas.drawText(footer, marginLeft, marginBottom, p3);
    }

    public void paintTitle(Canvas canvas) {
        Paint p = new Paint();
        p.setTextSize(80);
        p.setColor(ContextCompat.getColor(this.context, R.color.main));

        canvas.drawText(TITLE, 100, 150, p);
    }

    public void paintSubtitle(Canvas canvas) {
        Paint p2 = new Paint();
        p2.setTextSize(30);
        p2.setColor(ContextCompat.getColor(this.context, R.color.black));

        canvas.drawText(SECONDARY_TEXT, 100, 300, p2);
    }

    public void paintTableHeader(Canvas canvas) {
        String text = context.getString(R.string.txdetails_print_title);

        Paint p3 = new Paint();
        p3.setTextSize(40);
        p3.setColor(ContextCompat.getColor(this.context, R.color.black));

        Paint rectPaint = new Paint();
        rectPaint.setColor(ContextCompat.getColor(this.context, R.color.mediumGrey));

        canvas.drawRect(marginLeft, 550, marginRight, 700, rectPaint);
        canvas.drawText(text, 250, 600, p3);
    }
}
