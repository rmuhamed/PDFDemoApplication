package com.gft.agmobile.samples.pdfdemoapp.controller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfDocument.Page;
import android.graphics.pdf.PdfDocument.PageInfo;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.gft.agmobile.samples.pdfdemoapp.canvas.GenericPaint;
import com.gft.agmobile.samples.pdfdemoapp.utils.FileGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by romh on 23/02/2017.
 */

public class PDFController {

    public static final String FILE_NAME = "demoPDF.pdf";
    public static final String TITLE = "O2 Banking";

    public static final String SECONDARY_TEXT = "Umsatzayskunft O2 Banking Konto";


    private final Context context;
    private final PDFControllerResultListener PDFControllerResultListener;

    private final PageInfo pageInfo;
    private final PdfDocument document;


    public PDFController(Context aContext, PDFControllerResultListener PDFControllerResultListener) {
        this.context = aContext;
        this.PDFControllerResultListener = PDFControllerResultListener;

        this.document = new PdfDocument();
        this.pageInfo = new PageInfo.Builder(1000, 1000, 1).create();
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
            FileOutputStream fos = FileGenerator.getOutputStream(this.context, FILE_NAME);
            this.document.writeTo(fos);

            this.PDFControllerResultListener.onSuccess(new File(FILE_NAME));
        } catch (IOException e) {
            this.PDFControllerResultListener.onSomeError(e.toString());
        } finally {
            // close the document
            this.document.close();
        }
    }

    //Dummy test
    public void makeDummyPage()  {
        String date = new SimpleDateFormat("dd. MMMM yyyy hh:ss").format(new Date());
        // crate a page description
        PdfDocument.PageInfo pageInfo = new PageInfo.Builder(1000, 1000, 1).create();

        Page page = document.startPage(pageInfo);
        Canvas c = page.getCanvas();

        Paint p = new Paint();
        p.setTextSize(80);
        p.setColor(ContextCompat.getColor(this.context, android.R.color.holo_purple));

        Paint p2 = new Paint();
        p2.setTextSize(30);
        p2.setColor(ContextCompat.getColor(this.context, android.R.color.background_dark));

        Paint p3 = new Paint();
        p2.setTextSize(30);
        p2.setColor(ContextCompat.getColor(this.context, android.R.color.background_dark));

        Rect aRectangle = new Rect(100, 600, 700, 700);

        Paint rectPaint = new Paint();
        rectPaint.setColor(ContextCompat.getColor(this.context, android.R.color.holo_blue_dark));

        c.drawText(TITLE, 100, 150, p);
        c.drawText(SECONDARY_TEXT, 100, 300, p2);
        c.drawRect(aRectangle, rectPaint);
        c.drawText(date, 100, 500, p3);

        document.finishPage(page);

        this.write();
    }

}
