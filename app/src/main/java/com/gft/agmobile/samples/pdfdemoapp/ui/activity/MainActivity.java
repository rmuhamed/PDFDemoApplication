package com.gft.agmobile.samples.pdfdemoapp.ui.activity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.gft.agmobile.samples.pdfdemoapp.BuildConfig;
import com.gft.agmobile.samples.pdfdemoapp.R;
import com.gft.agmobile.samples.pdfdemoapp.canvas.GenericPaint;
import com.gft.agmobile.samples.pdfdemoapp.controller.DisplayController;
import com.gft.agmobile.samples.pdfdemoapp.controller.AbstractPDFDrawerController;
import com.gft.agmobile.samples.pdfdemoapp.controller.DummyPDFDrawerController;
import com.gft.agmobile.samples.pdfdemoapp.controller.PDFDrawerController;
import com.gft.agmobile.samples.pdfdemoapp.controller.PDFDrawerControllerResultListener;
import com.gft.agmobile.samples.pdfdemoapp.controller.PDFHandlerCallback;
import com.gft.agmobile.samples.pdfdemoapp.controller.PDFHandlerController;
import com.gft.agmobile.samples.pdfdemoapp.generator.PaintGenerator;
import com.gft.agmobile.samples.pdfdemoapp.ui.navigation.NavigationHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements PDFDrawerControllerResultListener, PDFHandlerCallback {
    private static final int PERMISSION_REQUEST = 200;
    private static final String PDF_EXTENSION = "pdf";
    private static final String DATE_PATTERN = "yyyy-MM-dd hh:mm";

    private int documentHeight;
    private int documentWith;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.calculatePageDimensionsFromDisplay();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.handlePermissions();
        } else {
            this.generatePDF(this, this.getDocumentName());
        }
    }

    @Override
    public void onSuccess(String fileName) {
        //Open PDF into some convenience application
        new PDFHandlerController(this, this).openPdf(fileName);
    }

    @Override
    public void onSomeError(String errorCause) {
        this.showResultNotification(errorCause);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST: {
                if (grantResults.length > 0) {
                    boolean permissionDenied = false;
                    for (int result : grantResults) {
                        if (result == PackageManager.PERMISSION_DENIED) {
                            permissionDenied = true;
                        }
                    }
                    if (permissionDenied) {
                        //Inform to user
                    } else {
                        this.generatePDF(this, this.getDocumentName());
                    }
                }
                break;
            }

            default:
                break;
        }
    }

    @Override
    public void onViewPDF(Uri aPDFUri) {
        try {
            NavigationHandler.getInstance(this).openAdobeReaderFor(aPDFUri);
            //Close out application (NO GO BACK Logic)
            this.finish();
        } catch (ActivityNotFoundException e) {
            this.showPDFReaderNotFoundDialog();
        }
    }

    public void showPDFReaderNotFoundDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.no_pdf_reader_installed_title);
        builder.setMessage(R.string.install_a_reader);
        //Positive button action
        builder.setPositiveButton(R.string.ok_go_to_market_action,
                (dialog, which) -> {
                    NavigationHandler.getInstance(MainActivity.this).lookUPForAdobeReader();
                });
        //Negative button action
        builder.setNegativeButton(R.string.cancel_go_to_market_action, (dialog, which) -> {
            NavigationHandler.getInstance(MainActivity.this).closeApp();
        });

        builder.create().show();
    }

    private void showResultNotification(String textToDisplay) {
        Snackbar
                .make(this.getWindow().getDecorView(), textToDisplay, Snackbar.LENGTH_LONG)
                .show();
    }

    private void handlePermissions() {
        int hasStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (hasStoragePermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, getPermissionsToCheck(hasStoragePermission), PERMISSION_REQUEST);
        } else {
            this.generatePDF(this, this.getDocumentName());
        }
    }

    private void generatePDF(PDFDrawerControllerResultListener listener, String documentName) {
        AbstractPDFDrawerController controller = null;
        List<GenericPaint> paintsToBeDrawnIntoPDF = new ArrayList<>();
        if (BuildConfig.DUMMY) {
            controller = new DummyPDFDrawerController(this, documentName, this.documentWith, this.documentHeight, listener);
        } else {
            controller = new PDFDrawerController(this, documentName, this.documentWith, this.documentHeight, listener);
            paintsToBeDrawnIntoPDF = PaintGenerator.generate(this);
        }

        controller.make(paintsToBeDrawnIntoPDF);
    }

    private String[] getPermissionsToCheck(int hasStoragePermission) {
        List<String> permissionList = new ArrayList<>();
        if (hasStoragePermission != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        return  permissionList.toArray(new String[permissionList.size()]);
    }

    private void calculatePageDimensionsFromDisplay() {
        int actionBarHeight = (int) (56 * DisplayController.getInstance(this).getDensityScale());

        this.documentWith = DisplayController.getInstance(this).getScreenWidth();
        this.documentHeight = DisplayController.getInstance(this).getScreenHeight() - actionBarHeight;
    }

    private String getDocumentName() {
        return new StringBuilder(new SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).format(new Date()))
                .append('_')
                .append("o2Banking")
                .append('_')
                .append("Umsatzauskunft")
                .append('.')
                .append(PDF_EXTENSION).toString();
    }
}
