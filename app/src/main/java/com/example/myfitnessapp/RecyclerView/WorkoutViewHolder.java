package com.example.myfitnessapp.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.R;

public class WorkoutViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView textView;

    public WorkoutViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.card_imageview);
        textView = itemView.findViewById(R.id.card_textview);
    }
}
