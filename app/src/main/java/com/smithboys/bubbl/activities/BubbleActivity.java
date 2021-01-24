package com.smithboys.bubbl.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.smithboys.bubbl.R;
import com.smithboys.bubbl.database.GlobalBubbles;
import com.smithboys.bubbl.models.Bubble;

public class BubbleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bubble_layout);

        Bubble bubble = GlobalBubbles.queryByID(GlobalBubbles.getLastBubbleClicked());

        // Pull data from bubble

    }
}
