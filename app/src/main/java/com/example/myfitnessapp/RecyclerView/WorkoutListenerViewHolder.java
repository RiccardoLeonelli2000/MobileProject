package com.example.myfitnessapp.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.R;

public class WorkoutListenerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    TextView titleView;
    ImageView imageView;

    private final OnItemListener itemListener;

    public WorkoutListenerViewHolder(@NonNull View itemView, OnItemListener listener) {
        super(itemView);
        titleView = itemView.findViewById(R.id.workout_title_textview);
        imageView = itemView.findViewById(R.id.workout_imageview);
        itemListener = listener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemListener.onItemClick(getAdapterPosition());
    }
}