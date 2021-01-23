package com.smithboys.bubbl.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.smithboys.bubbl.R;

public class OverviewFragment extends Fragment {

    private TextView helloWorld;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.overview_fragment, container, false);
        final Context context = getContext();

        helloWorld = root.findViewById(R.id.hello_world_text);
        helloWorld.setText("Loaded");

        return root;
    }
}
