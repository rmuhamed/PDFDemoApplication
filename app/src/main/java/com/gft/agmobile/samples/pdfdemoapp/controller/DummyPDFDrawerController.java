package com.gft.agmobile.samples.pdfdemoapp.controller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.gft.agmobile.samples.pdfdemoapp.R;
import com.gft.agmobile.samples.pdfdemoapp.canvas.GenericPaint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by romh on 20/04/2017.
 */

public class DummyPDFDrawerController extends AbstractPDFDrawerController {

    public static final String PRINTING_DATE_PATTERN = "dd MMMM yyyy hh:mm";

    public DummyPDFDrawerController(Context aContext, String fileName, int displayWidth, int displayHeight, PDFDrawerControllerResultListener listener) {
        super(aContext, fileName, displayWidth, displayHeight, listener);
    }

    @Override
    public void make(@NonNull List<GenericPaint> genericPaintList) {
        // crate a page description
        PdfDocument.Page page = this.document.startPage(this.pageInfo);
        Canvas c = page.getCanvas();

        this.paintTitle(c);
        this.printCustomerInformation(c);
        this.paintSubtitle(c);
        this.paintPrintInformation(c);
        this.paintTableHeader(c);
        this.paintTableSum(c);
        this.paintFooter(c);

        this.document.finishPage(page);

        this.write();
    }

    private void printCustomerInformation(Canvas canvas) {
        Paint p = new Paint();
        p.setTextSize(this.context.getResources().getDimension(R.dimen.text_size));
        p.setTypeface(Typeface.DEFAULT_BOLD);
        p.setColor(ContextCompat.getColor(this.context, R.color.black));

        float marginTop = this.context.getResources().getDimension(R.dimen.customer_section_margin_top);
        float marginLeft = this.marginRight - (this.marginRight / 4);

        canvas.drawText(this.context.getString(R.string.txdetails_print_account_holder), marginLeft, marginTop, p);

        marginTop = marginTop
                + this.context.getResources().getDimension(R.dimen.text_size)
                + this.context.getResources().getDimension(R.dimen.customer_section_margin_top_between_items);

        canvas.drawText(this.context.getString(R.string.global_iban), marginLeft, marginTop, p);

        marginTop = marginTop
                + this.context.getResources().getDimension(R.dimen.text_size)
                + this.context.getResources().getDimension(R.dimen.customer_section_margin_top_between_items);

        canvas.drawText(this.context.getString(R.string.global_bic), marginLeft, marginTop, p);
    }

    private void paintTitle(Canvas canvas) {
        Paint p = new Paint();
        p.setTextSize(this.context.getResources().getDimension(R.dimen.title_text_size));
        p.setTypeface(Typeface.DEFAULT_BOLD);
        p.setColor(ContextCompat.getColor(this.context, R.color.main));

        float titleMarginTop = this.context.getResources().getDimension(R.dimen.document_title_margin_top);
        float center = this.marginRight / 2 - (p.measureText(this.context.getString(R.string.document_title)) / 2);

        canvas.drawText(this.context.getString(R.string.document_title), center, titleMarginTop, p);
    }

    private void paintPrintInformation(Canvas canvas) {
        Paint aPaint = new Paint();
        aPaint.setTextSize(this.context.getResources().getDimension(R.dimen.text_size));
        aPaint.setColor(ContextCompat.getColor(this.context, R.color.black));

        float titleX = this.context.getResources().getDimension(R.dimen.document_left_margin);
        float titleY = this.context.getResources().getDimension(R.dimen.txdetails_print_info_title_margin_top);

        canvas.drawText(this.context.getString(R.string.txdetails_print_pdf_timestamp,
                new SimpleDateFormat(PRINTING_DATE_PATTERN).format(new Date())),
                titleX,
                titleY,
                aPaint);
    }

    private void paintSubtitle(Canvas canvas) {
        Paint aPaint = new Paint();
        aPaint.setTextSize(this.context.getResources().getDimension(R.dimen.text_size));
        aPaint.setTypeface(Typeface.DEFAULT_BOLD);
        aPaint.setColor(ContextCompat.getColor(this.context, R.color.black));

        float titleX = this.context.getResources().getDimension(R.dimen.document_left_margin);
        float titleY = this.context.getResources().getDimension(R.dimen.txdetails_print_title_margin_top);

        canvas.drawText(this.context.getString(R.string.txdetails_print_title), titleX, titleY, aPaint);
    }

    private void paintTableHeader(Canvas canvas) {
        Paint firstColumnPaint = new Paint();
        firstColumnPaint.setTextSize(this.context.getResources().getDimension(R.dimen.table_header_column_text_size));
        firstColumnPaint.setColor(ContextCompat.getColor(this.context, R.color.black));

        Paint rectPaint = new Paint();
        rectPaint.setColor(ContextCompat.getColor(this.context, R.color.mediumGrey));

        float tableHeaderMarginTop = this.context.getResources().getDimension(R.dimen.table_header_margin_top);
        float tableHeaderHeight = tableHeaderMarginTop + this.context.getResources().getDimension(R.dimen.table_header_height);

        canvas.drawRect(this.marginLeft, tableHeaderMarginTop, this.marginRight, tableHeaderHeight, rectPaint);

        float firstColumnX =  this.marginLeft + this.context.getResources().getDimension(R.dimen.table_header_column_margin_left);

        canvas.drawText(this.context.getString(R.string.txdetails_print_table_timestamp),
                firstColumnX,
                tableHeaderHeight - this.context.getResources().getDimension(R.dimen.table_header_medium_gravity_text_column),
                firstColumnPaint);

        Paint secondColumnPaint = new Paint();
        secondColumnPaint.setTextSize(this.context.getResources().getDimension(R.dimen.table_header_column_text_size));
        secondColumnPaint.setColor(ContextCompat.getColor(this.context, R.color.black));

        float secondColumnX =  firstColumnX
                + firstColumnPaint.measureText(this.context.getString(R.string.txdetails_print_table_timestamp))
                + this.context.getResources().getDimension(R.dimen.table_header_column_margin_left);

        canvas.drawText(this.context.getString(R.string.txdetails_print_table_value_date),
                secondColumnX,
                tableHeaderHeight - this.context.getResources().getDimension(R.dimen.table_header_medium_gravity_text_column),
                secondColumnPaint);

        Paint thirdColumnPaint = new Paint();
        thirdColumnPaint.setTextSize(this.context.getResources().getDimension(R.dimen.table_header_column_text_size));
        thirdColumnPaint.setColor(ContextCompat.getColor(this.context, R.color.black));

        float thirdColumnX =  secondColumnX
                + secondColumnPaint.measureText(this.context.getString(R.string.txdetails_print_table_value_date))
                + this.context.getResources().getDimension(R.dimen.table_header_column_margin_left);

        canvas.drawText(this.context.getString(R.string.txdetails_print_table_type),
                thirdColumnX,
                tableHeaderHeight - this.context.getResources().getDimension(R.dimen.table_header_medium_gravity_text_column),
                thirdColumnPaint);


        Paint fourColumnPaint = new Paint();
        fourColumnPaint.setTextSize(this.context.getResources().getDimension(R.dimen.table_header_column_text_size));
        fourColumnPaint.setColor(ContextCompat.getColor(this.context, R.color.black));

        float fourColumnX =  thirdColumnX
                + secondColumnPaint.measureText(this.context.getString(R.string.txdetails_print_table_type))
                + this.context.getResources().getDimension(R.dimen.table_header_column_margin_left);

        canvas.drawText(this.context.getString(R.string.txdetails_print_table_summary),
                fourColumnX,
                tableHeaderHeight - this.context.getResources().getDimension(R.dimen.table_header_medium_gravity_text_column),
                fourColumnPaint);


        Paint fiveColumnPaint = new Paint();
        fourColumnPaint.setTextSize(this.context.getResources().getDimension(R.dimen.table_header_column_text_size));
        fourColumnPaint.setColor(ContextCompat.getColor(this.context, R.color.black));

        float fiveColumnX =  fourColumnX
                + secondColumnPaint.measureText(this.context.getString(R.string.txdetails_print_table_summary))
                + this.context.getResources().getDimension(R.dimen.table_header_column_margin_left);

        canvas.drawText(this.context.getString(R.string.txdetails_print_table_amount),
                fiveColumnX,
                tableHeaderHeight - this.context.getResources().getDimension(R.dimen.table_header_medium_gravity_text_column),
                fiveColumnPaint);
    }

    private void paintTableSum(Canvas canvas) {
        Paint rectPaint = new Paint();
        rectPaint.setColor(ContextCompat.getColor(this.context, R.color.main));

        float marginTop = this.context.getResources().getDimension(R.dimen.table_sum_margin_top);
        float tableHeaderHeight = marginTop + this.context.getResources().getDimension(R.dimen.table_header_height);

        float sumCellMarginLeft = this.marginRight / 2;

        canvas.drawRect(sumCellMarginLeft, marginTop, this.marginRight, tableHeaderHeight, rectPaint);

        Paint sumValuePaint = new Paint();
        sumValuePaint.setTextSize(this.context.getResources().getDimension(R.dimen.table_header_column_text_size));
        sumValuePaint.setColor(ContextCompat.getColor(this.context, R.color.white));

        float sumValueX = this.marginRight -
                sumValuePaint.measureText("\"100 €\"") -
                this.marginLeft;

        canvas.drawText("\"100 €\"",
                sumValueX,
                tableHeaderHeight - this.context.getResources().getDimension(R.dimen.table_header_medium_gravity_text_column),
                sumValuePaint);


        Paint sumLabelPaint = new Paint();
        sumLabelPaint.setTextSize(this.context.getResources().getDimension(R.dimen.table_header_column_text_size));
        sumLabelPaint.setColor(ContextCompat.getColor(this.context, R.color.white));

        float sumLabelX = sumCellMarginLeft + this.marginLeft;

        canvas.drawText(this.context.getString(R.string.txdetails_print_table_sum),
                sumLabelX,
                tableHeaderHeight - this.context.getResources().getDimension(R.dimen.table_header_medium_gravity_text_column),
                sumLabelPaint);


    }

    private void paintFooter(Canvas canvas) {
        String footer = this.context.getString(R.string.txdetails_print_footer_imprint);

        Paint aPaint = new Paint();
        aPaint.setTextSize(this.context.getResources().getDimension(R.dimen.footer_text_size));
        aPaint.setColor(ContextCompat.getColor(this.context, R.color.black));

        canvas.drawText(footer, this.marginLeft, this.marginBottom, aPaint);
    }
}
