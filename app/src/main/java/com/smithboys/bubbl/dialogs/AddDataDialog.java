package com.smithboys.bubbl.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.RequiresApi;

import com.smithboys.bubbl.R;
import com.smithboys.bubbl.activities.DashboardActivity;
import com.smithboys.bubbl.activities.QRActivity;


import org.json.JSONException;

import static com.smithboys.bubbl.utils.UpdateDataUtils.updateUserData;

public class AddDataDialog {

    private static Button qrBtn;
    private static Button doneButton;
    private static RadioGroup dialogRadioGroup;
    private static RadioButton testButton;
    private static RadioButton diagnosisButton;
    private static RadioButton vaccineButton;
    public static int selectedButton;


    public static Dialog onCreateDialog(Context context) throws JSONException {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        final AlertDialog dialog = builder.create();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.data_dialog_layout, null);
        dialog.setView(dialogView);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        selectedButton = 0;
        dialogRadioGroup = dialogView.findViewById(R.id.dialog_radio_group);
        dialogRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.dialog_radio_test:
                        selectedButton = 0;
                        break;
                    case R.id.dialog_radio_diagnosis:
                        selectedButton = 1;
                        break;
                    case R.id.dialog_radio_vaccine:
                        selectedButton = 2;
                        break;
                }
            }
        });

        qrBtn = (Button) dialogView.findViewById(R.id.scanqr);
        qrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, QRActivity.class));
            }
        });

        doneButton = dialogView.findViewById(R.id.dialog_done_button);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                updateUserData(false);
                context.startActivity(new Intent(context, DashboardActivity.class));
            }
        });



        return dialog;
    }
}
