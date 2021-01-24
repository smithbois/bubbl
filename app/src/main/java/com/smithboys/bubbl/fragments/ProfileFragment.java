package com.smithboys.bubbl.fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.smithboys.bubbl.R;
import com.smithboys.bubbl.database.CurrentUser;
import com.smithboys.bubbl.database.GlobalBubbles;

import java.time.LocalDate;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

public class ProfileFragment extends Fragment {

    private ImageView profileImage;
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


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.profile_fragment, container, false);
        final Context context = getContext();

        profileImage = root.findViewById(R.id.profile_image);
        //profileImage.setBackgroundResource(CurrentUser.currentUser.getProfilePic());

        nameText = root.findViewById(R.id.profile_name_text);
        nameText.setText(CurrentUser.currentUser.getFirstName() + " " + CurrentUser.currentUser.getLastName());

        infectedText = root.findViewById(R.id.infected_text);
        if (CurrentUser.currentUser.getInfected()) {
            infectedText.setText("Infected");
        } else {
            infectedText.setText("Not Infected");
        }

        vaccinatedIcon = root.findViewById(R.id.vaccinated_icon);
        if (CurrentUser.currentUser.getVaccinated()) {
            vaccinatedIcon.setBackgroundResource(R.drawable.circle_green);
        } else {
            vaccinatedIcon.setBackgroundResource(R.drawable.circle_red);
        }

        testingStatusIcon = root.findViewById(R.id.testing_status_icon);
        LocalDate dateLastTested = CurrentUser.currentUser.getDateLastTested();
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


        riskLevelIcon = root.findViewById(R.id.risk_level_icon);
        switch (CurrentUser.currentUser.getRiskLevel()) {
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

        infectedVerifiedBadge = root.findViewById(R.id.infected_verified_badge);
        vaccinatedVerifiedBadge = root.findViewById(R.id.vaccinated_verfied_badge);
        testingVerifiedBadge = root.findViewById(R.id.testing_verfied_badge);

        if (CurrentUser.currentUser.getTestVerified()) {
            infectedVerifiedBadge.setVisibility(View.VISIBLE);
            vaccinatedVerifiedBadge.setVisibility(View.VISIBLE);
            testingVerifiedBadge.setVisibility(View.VISIBLE);
        } else {
            infectedVerifiedBadge.setVisibility(View.GONE);
            vaccinatedVerifiedBadge.setVisibility(View.GONE);
            testingVerifiedBadge.setVisibility(View.GONE);
        }

        bubbleNumberText = root.findViewById(R.id.bubble_number_text);
        int bubbleCount = CurrentUser.currentUser.getBubbles().size();
        bubbleNumberText.setText(String.valueOf(bubbleCount));

        contactNumberText = root.findViewById(R.id.contact_number_text);
        int contactCount = 0;
        for (int id : CurrentUser.currentUser.getBubbles()) {
            int members = GlobalBubbles.queryByID(id).getUsers().size() - 1;
            contactCount = contactCount + members;
        }
        contactNumberText.setText(String.valueOf(contactCount));





        return root;
    }
}
