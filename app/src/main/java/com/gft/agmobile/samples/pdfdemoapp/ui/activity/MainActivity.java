package com.gft.agmobile.samples.pdfdemoapp.ui.activity;

import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gft.agmobile.samples.pdfdemoapp.R;
import com.gft.agmobile.samples.pdfdemoapp.canvas.GenericPaint;
import com.gft.agmobile.samples.pdfdemoapp.canvas.TextPaint;
import com.gft.agmobile.samples.pdfdemoapp.controller.PDFController;
import com.gft.agmobile.samples.pdfdemoapp.controller.PDFControllerResultListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    private TextPaint buildTextCanvas(String text, int textColor, float textSize, float x, float y) {
        return new TextPaint.Builder()
                .text(text)
                .setX(x)
                .setY(y)
                .textColor(textColor)
                .textSize(textSize).build();
    }

    private void generatePDF() {
        List<GenericPaint> paintToBeDrawn = new ArrayList<>();
        paintToBeDrawn.add(this.buildTextCanvas(this.getString(R.string.TITLE), ContextCompat.getColor(this, android.R.color.holo_purple), 30, 100, 150));
        paintToBeDrawn.add(this.buildTextCanvas(this.getString(R.string.SECONDARY_TEXT), ContextCompat.getColor(this, android.R.color.holo_blue_dark), 30, 100, 300));

        new PDFController(this, this).make(paintToBeDrawn);
    }
}
