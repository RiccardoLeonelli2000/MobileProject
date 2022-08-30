package com.example.myfitnessapp.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.R;

public class NotificationsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    TextView contentView;

    private final OnItemListener itemListener;

    public NotificationsViewHolder(@NonNull View itemView, OnItemListener listener) {
        super(itemView);
        contentView = itemView.findViewById(R.id.notice_content);
        itemListener = listener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemListener.onItemClick(getAdapterPosition());
    }
}