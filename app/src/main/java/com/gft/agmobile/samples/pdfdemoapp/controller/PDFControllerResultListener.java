package com.gft.agmobile.samples.pdfdemoapp.controller;

import java.io.File;

/**
 * Created by romh on 23/02/2017.
 */
public interface PDFControllerResultListener {
    void onSuccess(File file);

    void onSomeError(String errorCause);
}
