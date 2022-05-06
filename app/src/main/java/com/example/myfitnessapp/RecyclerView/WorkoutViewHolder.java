package com.example.myfitnessapp.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.R;

public class WorkoutViewHolder extends RecyclerView.ViewHolder {

    TextView titleView;

    TextView setsView;
    TextView repsView;

    public WorkoutViewHolder(@NonNull View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.exercise_name);
        setsView = itemView.findViewById(R.id.exercise_sets);
        repsView = itemView.findViewById(R.id.exercise_reps);
    }
}
