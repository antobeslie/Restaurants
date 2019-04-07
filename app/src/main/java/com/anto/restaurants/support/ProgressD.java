package com.anto.restaurants.support;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.anto.restaurants.R;

/**
 * Created by Anto on 03-05-2018.
 */

public class ProgressD {

    Context context;
    AlertDialog dialog;

    public ProgressD(Context context, String text) {

        this.context = context;
        createProgressDialog(text);
    }

    public void createProgressDialog(String text) {

        AlertDialog b;
        AlertDialog.Builder dialogBuilder;
        dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.progress_dialog_layout, null);
        TextView textView = dialogView.findViewById(R.id.textView9);
        textView.setText(text);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);
        dialog = dialogBuilder.create();


    }

    public void showProgressDialog() {

        dialog.show();
    }

    public void HideProgressDialog() {

        if (dialog.isShowing())
            dialog.dismiss();
    }

}
