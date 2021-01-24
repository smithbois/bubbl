package com.smithboys.bubbl.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.smithboys.bubbl.R;
import com.smithboys.bubbl.database.CurrentUser;
import com.smithboys.bubbl.models.Bubble;

import java.util.List;

public class OverviewRecyclerAdapter extends RecyclerView.Adapter<OverviewRecyclerAdapter.CustomViewHolder>{

    private List<Bubble> bubbleList;
    private Context mContext;
    private OnBubbleClickListener onBubbleClickListener;

    public OverviewRecyclerAdapter(Context context, List<Bubble> bubbleList) {
        this.bubbleList = bubbleList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bubble_row_layout, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Bubble bubble = bubbleList.get(position);

        // Set bubble name
        holder.nameText.setText(bubble.getName());

        // Set risk icon and progress bar colors
        int riskLevel = bubble.getRiskLevel();
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
                onBubbleClickListener.onBubbleClick(bubble);
            }
        };
        holder.itemView.setOnClickListener(listener);
    }


    @Override
    public int getItemCount() {
        return (null != bubbleList ? bubbleList.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView nameText;
        protected TextView riskIcon;
        protected ProgressBar bubbleCapacity;

        public CustomViewHolder(View view) {
            super(view);
            this.nameText = view.findViewById(R.id.bubble_row_name_text);
            this.riskIcon = view.findViewById(R.id.bubble_row_risk_icon);
            this.bubbleCapacity = view.findViewById(R.id.bubble_row_capacity_bar);
        }
    }

    public OnBubbleClickListener getOnBubbleClickListener() {
        return onBubbleClickListener;
    }

    public void setOnBubbleClickListener(OnBubbleClickListener onBubbleClickListener) {
        this.onBubbleClickListener = onBubbleClickListener;
    }
}
