package com.gft.agmobile.samples.pdfdemoapp.controller;

/**
 * Created by romh on 23/02/2017.
 */
public interface PDFDrawerControllerResultListener {
    void onSuccess(String fileName);

    void onSomeError(String errorCause);
}
