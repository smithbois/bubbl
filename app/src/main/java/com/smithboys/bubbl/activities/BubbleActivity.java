package com.smithboys.bubbl.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.smithboys.bubbl.R;
import com.smithboys.bubbl.adapters.OverviewRecyclerAdapter;
import com.smithboys.bubbl.adapters.UserRecyclerViewAdapter;
import com.smithboys.bubbl.database.CurrentUser;
import com.smithboys.bubbl.database.GlobalBubbles;
import com.smithboys.bubbl.database.GlobalUsers;
import com.smithboys.bubbl.models.Bubble;
import com.smithboys.bubbl.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BubbleActivity extends AppCompatActivity {
    private TextView nameText;
    private TextView creatorText;
    private TextView riskText;

    private List<User> userList;
    private RecyclerView recyclerView;
    private UserRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bubble_layout);

        Bubble bubble = GlobalBubbles.queryByID(GlobalBubbles.getLastBubbleClicked());

        // Pull data from bubble
        String name = bubble.getName();
        User creator = GlobalUsers.queryByID(bubble.getCreator());
        int riskLevel = bubble.getRiskLevel();

        nameText = findViewById(R.id.BbNameText);
        nameText.setText(name);

        creatorText = findViewById(R.id.BbCreatorText);
        creatorText.setText(creator.getFirstName() + " " + creator.getLastName());

        riskText = findViewById(R.id.BbRiskText);
        riskText.setText(riskLevel + "");

        recyclerView = findViewById(R.id.Bb_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Set<Integer> userIDs = bubble.getUsers();
        userList = new ArrayList<>();
        for (Integer id : userIDs) {
            userList.add(GlobalUsers.queryByID(id));
        }

        adapter = new UserRecyclerViewAdapter(this, userList);
        recyclerView.setAdapter(adapter);
    }
}
