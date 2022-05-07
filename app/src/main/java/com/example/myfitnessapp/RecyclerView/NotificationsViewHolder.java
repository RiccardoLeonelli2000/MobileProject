package com.example.myfitnessapp.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.R;

public class NotificationsViewHolder extends RecyclerView.ViewHolder {

TextView contentView;

    public NotificationsViewHolder(@NonNull View itemView) {
        super(itemView);
        contentView = itemView.findViewById(R.id.notice_content);
    }
}
