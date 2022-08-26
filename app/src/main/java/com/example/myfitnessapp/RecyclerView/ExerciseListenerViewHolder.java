package com.example.myfitnessapp.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.R;


        import android.view.View;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.myfitnessapp.R;

public class ExerciseListenerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView titleView;
    TextView setsView;
    TextView weightsView;
    TextView restView;

    private final OnItemListener itemListener;

    public ExerciseListenerViewHolder(@NonNull View itemView, OnItemListener listener) {
        super(itemView);
        titleView = itemView.findViewById(R.id.exercise_name);
        setsView = itemView.findViewById(R.id.exercise_sets);
        weightsView = itemView.findViewById(R.id.exercise_weights);
        restView = itemView.findViewById((R.id.exercise_rest));
        itemListener = listener;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemListener.onItemClick(getAdapterPosition());
    }
}