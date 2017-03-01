package com.gft.agmobile.samples.pdfdemoapp.ui.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.gft.agmobile.samples.pdfdemoapp.R;
import com.gft.agmobile.samples.pdfdemoapp.controller.PDFControllerResultListener;
import com.gft.agmobile.samples.pdfdemoapp.controller.PDFDocumentController;
import com.gft.agmobile.samples.pdfdemoapp.generator.PaintGenerator;

import java.io.File;

public class MainActivity extends AppCompatActivity implements PDFControllerResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.generatePDF();
    }

    @Override
    public void onSuccess(File file) {
        if (file.exists() && file.isFile()) {
            this.showResultNotification(this.getString(R.string.successful_result));
        }
    }

    @Override
    public void onSomeError(String errorCause) {
        this.showResultNotification(errorCause);
    }

    private void showResultNotification(String textToDisplay) {
        Snackbar
                .make(this.getWindow().getDecorView(), textToDisplay, Snackbar.LENGTH_LONG)
                .show();
    }

    private void generatePDF() {
        new PDFDocumentController(this, this).make(PaintGenerator.generate(this));
    }
}
