package com.gft.agmobile.samples.pdfdemoapp.ui.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.gft.agmobile.samples.pdfdemoapp.ui.activity.MainActivity;

/**
 * Created by romh on 20/04/2017.
 */

public class NavigationHandler {
    public static final String MARKET_LINK_TO_ADOBE_READER = "market://details?id=com.adobe.reader";
    public static final String APPLICATION_PDF = "application/pdf";

    private static NavigationHandler instance;
    private final Activity anActivityContext;

    private NavigationHandler(Activity activityContext) {
        this.anActivityContext = activityContext;
    }

    public static NavigationHandler getInstance(Activity anActivityContext) {
        if (instance == null) {
            instance = new NavigationHandler(anActivityContext);
        }

        return instance;
    }

    public void lookUPForAdobeReader() {
        Intent marketIntent = new Intent(Intent.ACTION_VIEW);
        marketIntent.setData(Uri.parse(MARKET_LINK_TO_ADOBE_READER));
        this.anActivityContext.startActivity(marketIntent);
    }


    public void openAdobeReaderFor(Uri aPDFUri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(aPDFUri, APPLICATION_PDF);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        this.anActivityContext.startActivity(intent);
    }

    public void closeApp() {
        this.anActivityContext.finish();
    }
}
