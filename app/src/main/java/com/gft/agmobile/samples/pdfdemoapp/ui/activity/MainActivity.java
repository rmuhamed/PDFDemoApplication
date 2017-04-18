package com.gft.agmobile.samples.pdfdemoapp.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
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
import com.gft.agmobile.samples.pdfdemoapp.controller.PDFDrawerController;
import com.gft.agmobile.samples.pdfdemoapp.controller.PDFDrawerControllerResultListener;
import com.gft.agmobile.samples.pdfdemoapp.controller.PDFHandlerCallback;
import com.gft.agmobile.samples.pdfdemoapp.controller.PDFHandlerController;
import com.gft.agmobile.samples.pdfdemoapp.generator.PaintGenerator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PDFDrawerControllerResultListener, PDFHandlerCallback {

    public static final String APPLICATION_PDF = "application/pdf";
    public static final String MARKET_LINK_TO_ADOBE_READER = "market://details?id=com.adobe.reader";
    private static final int PERMISSION_REQUEST = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int hasStoragePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (hasStoragePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, getPermissionsToCheck(hasStoragePermission), PERMISSION_REQUEST);
            } else {
                this.generatePDF(this);
            }
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
                        generatePDF(MainActivity.this);
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
        Intent intent;
        intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(aPDFUri, APPLICATION_PDF);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        try {
            this.startActivityForResult(intent, Activity.RESULT_FIRST_USER);
        } catch (ActivityNotFoundException e) {
            this.showPDFReaderNotFoundDialog();
        }
    }

    public void showPDFReaderNotFoundDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No Application Found");
        builder.setMessage("Download one from Android Market?");
        builder.setPositiveButton("Yes, Please",
                (dialog, which) -> {
                    Intent marketIntent = new Intent(Intent.ACTION_VIEW);
                    marketIntent.setData(Uri.parse(MARKET_LINK_TO_ADOBE_READER));
                    MainActivity.this.startActivity(marketIntent);
                });
        builder.setNegativeButton("No, Thanks", null);
        builder.create().show();
    }

    private void showResultNotification(String textToDisplay) {
        Snackbar
                .make(this.getWindow().getDecorView(), textToDisplay, Snackbar.LENGTH_LONG)
                .show();
    }

    private void generatePDF(PDFDrawerControllerResultListener listener) {
        PDFDrawerController controller = new PDFDrawerController(this, listener);
        if (BuildConfig.DUMMY) {
            controller.makeDummyPage();
        } else {
            controller.make(PaintGenerator.generate(this));
        }

    }

    private String[] getPermissionsToCheck(int hasStoragePermission) {
        List<String> permissionList = new ArrayList<>();
        if (hasStoragePermission != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        return  permissionList.toArray(new String[permissionList.size()]);
    }
}
