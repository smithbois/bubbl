package com.smithboys.bubbl.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smithboys.bubbl.R;

import org.json.JSONException;

public class AddDataDialog {
    public static Dialog onCreateDialog(Context context) throws JSONException {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        final AlertDialog dialog = builder.create();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.data_dialog_layout, null);
        dialog.setView(dialogView);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        return dialog;
    }
}
