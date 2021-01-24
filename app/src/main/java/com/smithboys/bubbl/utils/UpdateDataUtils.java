package com.smithboys.bubbl.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.smithboys.bubbl.database.CurrentUser;
import com.smithboys.bubbl.dialogs.AddDataDialog;

import java.time.LocalDate;

public class UpdateDataUtils {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void updateUserData(Boolean isVerified) {
        int selectedButton = AddDataDialog.selectedButton;
        if (isVerified) {
            CurrentUser.currentUser.setTestVerified(true);
        }
        switch (selectedButton) {
            case 0:
                // Test
                CurrentUser.currentUser.setDateLastTested(LocalDate.now());
                break;
            case 1:
                // Diagnosis
                CurrentUser.currentUser.setDateInfected(LocalDate.now());
                break;
            case 2:
                // Vaccine
                CurrentUser.currentUser.setDateVaccinated(LocalDate.now());
        }
    }
}
