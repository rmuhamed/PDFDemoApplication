package com.gft.agmobile.samples.pdfdemoapp.controller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.support.annotation.NonNull;

import com.gft.agmobile.samples.pdfdemoapp.canvas.GenericPaint;

import java.util.List;

/**
 * Created by romh on 20/04/2017.
 */

public class PDFDrawerController extends AbstractPDFDrawerController {

    public PDFDrawerController(Context aContext, int displayWidth, int displayHeight, PDFDrawerControllerResultListener listener) {
        super(aContext, displayWidth, displayHeight, listener);
    }

    @Override
    public void make(@NonNull List<GenericPaint> genericPaintList) {
        PdfDocument.Page page = this.document.startPage(this.pageInfo);
        Canvas c = page.getCanvas();

        for (GenericPaint genericPaint : genericPaintList) {
            genericPaint.drawIn(c);
        }

        this.document.finishPage(page);

        this.write();
    }
}
