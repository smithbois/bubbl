package com.smithboys.bubbl.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.smithboys.bubbl.R;
import com.smithboys.bubbl.database.CurrentUser;

public class DashboardActivity extends AppCompatActivity {

    private TextView helloWorld;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);
        helloWorld = findViewById(R.id.dashboard_user_email);
        helloWorld.setText(CurrentUser.currentUser.getEmail());
        System.out.println("Email: " + CurrentUser.currentUser.getEmail());
    }
}
