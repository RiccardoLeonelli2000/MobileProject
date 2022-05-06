package com.example.myfitnessapp.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.R;

public class AllWorkoutViewHolder extends RecyclerView.ViewHolder {

    TextView titleView;
    ImageView imageView;
    TextView descriptionView;

    public AllWorkoutViewHolder(@NonNull View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.workout_title_textview);
        imageView = itemView.findViewById(R.id.workout_imageview);
        descriptionView = itemView.findViewById(R.id.workout_description_textview);
    }
}
