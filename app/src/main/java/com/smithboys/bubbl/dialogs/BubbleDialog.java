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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.RequiresApi;

import com.smithboys.bubbl.R;
import com.smithboys.bubbl.activities.DashboardActivity;
import com.smithboys.bubbl.activities.QRActivity;
import com.smithboys.bubbl.database.CurrentUser;
import com.smithboys.bubbl.utils.RiskAlgo;

import org.json.JSONException;

import static com.smithboys.bubbl.utils.UpdateDataUtils.updateUserData;

public class BubbleDialog {

    private static Button joinGroupButton;
    private static LinearLayout joinGroupLayout;
    private static LinearLayout newGroupLayout;
    private static LinearLayout joinLayout;
    private static EditText groupCodeEditText;
    private static Button joinButton;


    public static Dialog onCreateDialog(Context context) throws JSONException {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        final AlertDialog dialog = builder.create();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.bubble_dialog_layout, null);
        dialog.setView(dialogView);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        joinGroupButton = dialogView.findViewById(R.id.join_group_button);
        joinGroupLayout = dialogView.findViewById(R.id.join_group_layout);
        newGroupLayout = dialogView.findViewById(R.id.new_group_layout);
        joinLayout = dialogView.findViewById(R.id.join_layout);
        groupCodeEditText = dialogView.findViewById(R.id.group_code_edit_text);
        joinButton = dialogView.findViewById(R.id.join_button);

        joinGroupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                joinGroupLayout.setVisibility(View.GONE);
                newGroupLayout.setVisibility(View.GONE);
                joinLayout.setVisibility(View.VISIBLE);
            }
        });

        Intent intent = new Intent(context, DashboardActivity.class);

        joinButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                CurrentUser.currentUser.joinBubble(1);
                context.startActivity(intent);
                RiskAlgo.updateBubbleRisks();
            }
        });



        return dialog;
    }
}
