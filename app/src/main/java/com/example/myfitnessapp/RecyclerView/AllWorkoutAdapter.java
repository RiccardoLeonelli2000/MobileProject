package com.example.myfitnessapp.RecyclerView;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.R;
import com.example.myfitnessapp.WorkoutItem;

import java.util.List;

public class AllWorkoutAdapter extends RecyclerView.Adapter<AllWorkoutViewHolder> {

    private List<WorkoutItem> workoutItemList;
    Activity activity;

    public AllWorkoutAdapter(List<WorkoutItem> list, Activity activity) {
        this.workoutItemList = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AllWorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_workout_layout, parent, false);

        return new AllWorkoutViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull AllWorkoutViewHolder holder, int position) {
        WorkoutItem currentWorkoutItem = workoutItemList.get(position);
        holder.titleView.setText(currentWorkoutItem.getTitle());
        holder.descriptionView.setText(currentWorkoutItem.getDescription());

        String image = currentWorkoutItem.getImageResource();
        if (image.contains("ic_")){
            Drawable drawable = AppCompatResources.getDrawable(activity, activity.getResources().getIdentifier(image, "drawable", activity.getPackageName()));
            holder.imageView.setImageDrawable(drawable);
        }
    }

    @Override
    public int getItemCount() {
        return workoutItemList.size();
    }
}
