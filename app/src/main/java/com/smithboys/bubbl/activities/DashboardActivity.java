package com.smithboys.bubbl.activities;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.smithboys.bubbl.R;
import com.smithboys.bubbl.adapters.DashboardAdapter;
import com.smithboys.bubbl.database.CurrentUser;

public class DashboardActivity extends AppCompatActivity {

    TabLayout dashboardTabs;
    ViewPager dashboardPager;

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
        dashboardTabs.getTabAt(1).setIcon(CurrentUser.currentUser.getProfilePic());

        final DashboardAdapter adapter = new DashboardAdapter(getSupportFragmentManager(), this, dashboardTabs.getTabCount());
        dashboardPager.setAdapter(adapter);
        dashboardPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(dashboardTabs));
        dashboardTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                dashboardPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}
