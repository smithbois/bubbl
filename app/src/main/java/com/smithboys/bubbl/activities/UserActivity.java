package com.smithboys.bubbl.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.smithboys.bubbl.R;
import com.smithboys.bubbl.database.CurrentUser;
import com.smithboys.bubbl.database.GlobalBubbles;
import com.smithboys.bubbl.database.GlobalUsers;
import com.smithboys.bubbl.dialogs.AddDataDialog;
import com.smithboys.bubbl.models.Bubble;
import com.smithboys.bubbl.models.User;

import org.json.JSONException;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class UserActivity extends AppCompatActivity {
    private CircularImageView profileImage;
    private TextView nameText;

    private TextView infectedText;
    private TextView infectedVerifiedBadge;

    private TextView vaccinatedIcon;
    private TextView vaccinatedVerifiedBadge;

    private TextView testingStatusIcon;
    private TextView testingVerifiedBadge;

    private TextView riskLevelIcon;

    private TextView bubbleNumberText;
    private TextView contactNumberText;

    ImageButton addDataButton;
    ImageButton backButton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_layout);

        User user = GlobalUsers.queryByID(GlobalUsers.getLastUserClicked());

        profileImage = findViewById(R.id.profile_image);
        profileImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(), user.getProfilePic(), null));

        nameText = findViewById(R.id.profile_name_text);
        nameText.setText(user.getFirstName() + " " + user.getLastName());

        infectedText = findViewById(R.id.infected_text);
        System.out.println("isInfected: " + user.getInfected());
        if (user.getInfected()) {
            infectedText.setText("Infected");
        } else {
            infectedText.setText("Not Infected");
        }

        vaccinatedIcon = findViewById(R.id.vaccinated_icon);
        if (user.getVaccinated()) {
            vaccinatedIcon.setBackgroundResource(R.drawable.circle_green);
        } else {
            vaccinatedIcon.setBackgroundResource(R.drawable.circle_red);
        }

        testingStatusIcon = findViewById(R.id.testing_status_icon);
        LocalDate dateLastTested = user.getDateLastTested();
        if (dateLastTested != null) {
            int daysSinceTest = Integer.parseInt(String.valueOf(DAYS.between(dateLastTested, LocalDate.now())));
            if (daysSinceTest <= 7) {
                testingStatusIcon.setBackgroundResource(R.drawable.circle_green);
            } else if (daysSinceTest <= 14) {
                testingStatusIcon.setBackgroundResource(R.drawable.circle_yellow);
            } else {
                testingStatusIcon.setBackgroundResource(R.drawable.circle_red);
            }
        } else {
            testingStatusIcon.setBackgroundResource(R.drawable.circle_red);
        }


        riskLevelIcon = findViewById(R.id.risk_level_icon);
        switch (user.getRiskLevel()) {
            case 1:
                riskLevelIcon.setBackgroundResource(R.drawable.circle_blue);
                break;
            case 2:
                riskLevelIcon.setBackgroundResource(R.drawable.circle_green);
                break;
            case 3:
                riskLevelIcon.setBackgroundResource(R.drawable.circle_yellow);
                break;
            case 4:
                riskLevelIcon.setBackgroundResource(R.drawable.circle_orange);
                break;
            case 5:
                riskLevelIcon.setBackgroundResource(R.drawable.circle_red);
                break;
        }

        infectedVerifiedBadge = findViewById(R.id.infected_verified_badge);
        vaccinatedVerifiedBadge = findViewById(R.id.vaccinated_verfied_badge);
        testingVerifiedBadge = findViewById(R.id.testing_verfied_badge);

        if (user.getTestVerified()) {
            infectedVerifiedBadge.setVisibility(View.VISIBLE);
            vaccinatedVerifiedBadge.setVisibility(View.VISIBLE);
            testingVerifiedBadge.setVisibility(View.VISIBLE);
        } else {
            infectedVerifiedBadge.setVisibility(View.GONE);
            vaccinatedVerifiedBadge.setVisibility(View.GONE);
            testingVerifiedBadge.setVisibility(View.GONE);
        }

        bubbleNumberText = findViewById(R.id.bubble_number_text);
        int bubbleCount = user.getBubbles().size();
        bubbleNumberText.setText(String.valueOf(bubbleCount));

        contactNumberText = findViewById(R.id.contact_number_text);
        int contactCount = 0;
        for (int id : user.getBubbles()) {
            int members = GlobalBubbles.queryByID(id).getUsers().size() - 1;
            contactCount = contactCount + members;
        }
        contactNumberText.setText(String.valueOf(contactCount));

        addDataButton = findViewById(R.id.add_data_button);
        addDataButton.setOnClickListener(v -> {
            Dialog dialog = null;
            try {
                dialog = AddDataDialog.onCreateDialog(this);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            dialog.getWindow().setLayout(10, 500);
            dialog.show();
        });

        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> {
            startActivity(new Intent(UserActivity.this, BubbleActivity.class));
        });
    }
}
