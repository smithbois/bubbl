package com.smithboys.bubbl.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.smithboys.bubbl.R;
import com.smithboys.bubbl.models.Bubble;
import com.smithboys.bubbl.models.User;

import java.util.List;

public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.CustomViewHolder>{
    private List<User> UserList;
    private Context mContext;
    private OnUserClickListener onUserClickListener;

    public UserRecyclerViewAdapter(Context context, List<User> UserList) {
        this.UserList = UserList;
        this.mContext = context;
    }

    @Override
    public UserRecyclerViewAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_layout, null);
        UserRecyclerViewAdapter.CustomViewHolder viewHolder = new UserRecyclerViewAdapter.CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserRecyclerViewAdapter.CustomViewHolder holder, int position) {
        User user = UserList.get(position);

        // Set bubble name
        holder.nameText.setText(user.getFirstName() + " " + user.getLastName());

        // Set risk icon and progress bar colors
        int riskLevel = user.getRiskLevel();
        switch (riskLevel) {
            case 1:
                holder.riskIcon.setBackgroundResource(R.drawable.circle_blue);
                DrawableCompat.setTint(holder.bubbleCapacity.getProgressDrawable(), ContextCompat.getColor(mContext, R.color.uiBlue));
                break;
            case 2:
                holder.riskIcon.setBackgroundResource(R.drawable.circle_green);
                DrawableCompat.setTint(holder.bubbleCapacity.getProgressDrawable(), ContextCompat.getColor(mContext, R.color.uiGreen));
                break;
            case 3:
                holder.riskIcon.setBackgroundResource(R.drawable.circle_yellow);
                DrawableCompat.setTint(holder.bubbleCapacity.getProgressDrawable(), ContextCompat.getColor(mContext, R.color.uiYellow));
                break;
            case 4:
                holder.riskIcon.setBackgroundResource(R.drawable.circle_orange);
                DrawableCompat.setTint(holder.bubbleCapacity.getProgressDrawable(), ContextCompat.getColor(mContext, R.color.uiOrange));
                break;
            case 5:
                holder.riskIcon.setBackgroundResource(R.drawable.circle_red);
                DrawableCompat.setTint(holder.bubbleCapacity.getProgressDrawable(), ContextCompat.getColor(mContext, R.color.uiRed));
                break;
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserClickListener.onUserClick(user);
            }
        };
        holder.itemView.setOnClickListener(listener);
    }


    @Override
    public int getItemCount() {
        return (null != UserList ? UserList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView nameText;
        protected TextView riskIcon;
        protected ProgressBar bubbleCapacity;

        public CustomViewHolder(View view) {
            super(view);
            this.nameText = view.findViewById(R.id.user_row_name_text);
            this.riskIcon = view.findViewById(R.id.user_row_risk_icon);
            this.bubbleCapacity = view.findViewById(R.id.user_row_capacity_bar);
        }
    }

    public OnUserClickListener getOnUserClickListener() {
        return onUserClickListener;
    }

    public void setOnUserClickListener(OnUserClickListener onUserClickListener) {
        this.onUserClickListener = onUserClickListener;
    }
}
