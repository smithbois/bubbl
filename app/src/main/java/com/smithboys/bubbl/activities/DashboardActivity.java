package com.smithboys.bubbl.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.BlendMode;
import android.graphics.BlendModeColorFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.smithboys.bubbl.R;
import com.smithboys.bubbl.adapters.DashboardAdapter;
import com.smithboys.bubbl.database.CurrentUser;
import com.smithboys.bubbl.dialogs.AddDataDialog;

import org.json.JSONException;

public class DashboardActivity extends AppCompatActivity {

    TabLayout dashboardTabs;
    ViewPager dashboardPager;

    ImageButton addDataButton;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);

        System.out.println("Email: " + CurrentUser.currentUser.getEmail());

        dashboardTabs = findViewById(R.id.dashboard_tab_layout);
        dashboardPager = findViewById(R.id.dashboard_pager);

        dashboardTabs.addTab(dashboardTabs.newTab().setText("My Bubbles"));
        dashboardTabs.addTab(dashboardTabs.newTab().setText("My Profile"));

        dashboardTabs.getTabAt(0).setIcon(R.drawable.ic_bubble_icon);
        dashboardTabs.getTabAt(0).getIcon().setTint(getResources().getColor(R.color.colorPrimary, getTheme()));
        dashboardTabs.getTabAt(1).setIcon(CurrentUser.currentUser.getProfilePic());

        final DashboardAdapter adapter = new DashboardAdapter(getSupportFragmentManager(), this, dashboardTabs.getTabCount());
        dashboardPager.setAdapter(adapter);
        dashboardPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(dashboardTabs));
        dashboardTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                dashboardPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0) {
                    tab.getIcon().setTint(getResources().getColor(R.color.colorPrimary, getTheme()));
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0) {
                    tab.getIcon().setTint(getResources().getColor(R.color.secondaryText, getTheme()));
                }

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

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
    }
}
