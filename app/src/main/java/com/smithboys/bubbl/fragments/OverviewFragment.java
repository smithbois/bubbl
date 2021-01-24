package com.smithboys.bubbl.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.smithboys.bubbl.R;
import com.smithboys.bubbl.activities.BubbleActivity;
import com.smithboys.bubbl.database.GlobalBubbles;

public class OverviewFragment extends Fragment {

    private TextView helloWorld;
    private Button bubbleButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.overview_fragment, container, false);
        final Context context = getContext();

        helloWorld = root.findViewById(R.id.hello_world_text);
        helloWorld.setText("Loaded");

        bubbleButton = root.findViewById(R.id.goto_bubble_button);

        Intent intent = new Intent(getActivity(), BubbleActivity.class);
        bubbleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalBubbles.setLastBubbleClicked(0);
                startActivity(intent);
            }
        });

        return root;
    }
}
