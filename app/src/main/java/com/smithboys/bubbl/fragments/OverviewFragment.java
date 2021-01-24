package com.smithboys.bubbl.fragments;

import android.app.Dialog;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.smithboys.bubbl.R;
import com.smithboys.bubbl.activities.BubbleActivity;
import com.smithboys.bubbl.adapters.OnBubbleClickListener;
import com.smithboys.bubbl.adapters.OverviewRecyclerAdapter;
import com.smithboys.bubbl.database.CurrentUser;
import com.smithboys.bubbl.database.GlobalBubbles;
import com.smithboys.bubbl.dialogs.AddDataDialog;
import com.smithboys.bubbl.dialogs.BubbleDialog;
import com.smithboys.bubbl.models.Bubble;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OverviewFragment extends Fragment {

    private List<Bubble> bubbleList;
    private RecyclerView recyclerView;
    private OverviewRecyclerAdapter adapter;

    private Button bubbleButton;
    private FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.overview_fragment, container, false);
        final Context context = getContext();

        recyclerView = root.findViewById(R.id.overview_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        Set<Integer> bubbleIDs = CurrentUser.currentUser.getBubbles();
        bubbleList = new ArrayList<>();
        for (Integer id : bubbleIDs) {
            bubbleList.add(GlobalBubbles.queryByID(id));
        }

        adapter = new OverviewRecyclerAdapter(context, bubbleList);
        recyclerView.setAdapter(adapter);

        adapter.setOnBubbleClickListener(new OnBubbleClickListener() {
            @Override
            public void onBubbleClick(Bubble bubble) {
                GlobalBubbles.setLastBubbleClicked(bubble.getId());
                startActivity(new Intent(getActivity(), BubbleActivity.class));
            }
        });

        floatingActionButton = root.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Dialog dialog = null;
                try {
                    dialog = BubbleDialog.onCreateDialog(getContext());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dialog.getWindow().setLayout(10, 500);
                dialog.show();
            }
        });

        return root;
    }
}
