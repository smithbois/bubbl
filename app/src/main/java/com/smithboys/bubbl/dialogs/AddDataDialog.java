package com.smithboys.bubbl.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.smithboys.bubbl.R;
import com.smithboys.bubbl.activities.DashboardActivity;
import com.smithboys.bubbl.activities.QRActivity;


import org.json.JSONException;

public class AddDataDialog {
    private static Button qrBtn;

    public static Dialog onCreateDialog(Context context) throws JSONException {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        final AlertDialog dialog = builder.create();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.data_dialog_layout, null);
        dialog.setView(dialogView);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        qrBtn = (Button) dialogView.findViewById(R.id.scanqr);
        qrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, QRActivity.class));
            }
        });

        return dialog;
    }
}
