package com.gft.agmobile.samples.pdfdemoapp.controller;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.FileProvider;

import com.gft.agmobile.samples.pdfdemoapp.BuildConfig;
import com.gft.agmobile.samples.pdfdemoapp.utils.FileUtils;

import java.io.File;

/**
 * Created by romh on 18/04/2017.
 */

public class PDFHandlerController {
    public static final String PROVIDER = ".provider";
    private final Context context;
    private final PDFHandlerCallback callback;

    public PDFHandlerController(Context context, PDFHandlerCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    public void openPdf(String fileName) {
        File file = new File(this.getPDFUrl(this.context, fileName));

        try {
            Uri pdfUri = FileProvider.getUriForFile(this.context, BuildConfig.APPLICATION_ID + PROVIDER, file);
            this.callback.onViewPDF(pdfUri);
        } catch (IllegalArgumentException e) {
        }
    }

    public String getPDFUrl(Context context, String id) {
        String baseFolder = FileUtils.getBaseFolder(context);
        return baseFolder + "/" + id;
    }
}
