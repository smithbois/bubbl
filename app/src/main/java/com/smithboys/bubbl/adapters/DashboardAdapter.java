package com.smithboys.bubbl.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.smithboys.bubbl.fragments.OverviewFragment;
import com.smithboys.bubbl.fragments.ProfileFragment;

public class DashboardAdapter extends FragmentPagerAdapter {

    private Context context;
    int totalTabs;

    public DashboardAdapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                OverviewFragment overviewFragment = new OverviewFragment();
                return overviewFragment;
            case 1:
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
