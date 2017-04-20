package com.gft.agmobile.samples.pdfdemoapp.controller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfDocument.Page;
import android.graphics.pdf.PdfDocument.PageInfo;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.gft.agmobile.samples.pdfdemoapp.R;
import com.gft.agmobile.samples.pdfdemoapp.canvas.GenericPaint;
import com.gft.agmobile.samples.pdfdemoapp.utils.FileUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by romh on 23/02/2017.
 */

public abstract class AbstractPDFDrawerController {

    private final PDFDrawerControllerResultListener PDFDrawerControllerResultListener;
    private final String fileName;

    final Context context;

    final PageInfo pageInfo;
    final PdfDocument document;

    final float marginRight;
    final float marginLeft;
    final float marginTop;
    final float marginBottom;


    public AbstractPDFDrawerController(Context aContext, String fileName, int displayWidth, int displayHeight, PDFDrawerControllerResultListener listener) {
        this.context = aContext;
        this.PDFDrawerControllerResultListener = listener;

        this.marginLeft = this.context.getResources().getDimension(R.dimen.document_left_margin);
        this.marginRight = displayWidth - this.context.getResources().getDimension(R.dimen.document_right_margin);
        this.marginTop = this.context.getResources().getDimension(R.dimen.document_top_margin);
        this.marginBottom = displayHeight - this.context.getResources().getDimension(R.dimen.document_bottom_margin);

        this.document = new PdfDocument();
        this.pageInfo = new PageInfo.Builder(displayWidth, displayHeight, 1).create();

        this.fileName = fileName;
    }

    public abstract void make(@NonNull List<GenericPaint> genericPaintList);

    void write() {

        try {
            FileOutputStream fos = FileUtils.getOutputStream(this.context, this.fileName);
            this.document.writeTo(fos);

            this.PDFDrawerControllerResultListener.onSuccess(this.fileName);
        } catch (IOException e) {
            this.PDFDrawerControllerResultListener.onSomeError(e.toString());
        } finally {
            // close the document
            this.document.close();
        }
    }
}
