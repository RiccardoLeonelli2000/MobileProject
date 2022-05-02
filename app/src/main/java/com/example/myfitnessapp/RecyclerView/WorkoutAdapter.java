package com.example.myfitnessapp.RecyclerView;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.WorkoutItem;
import com.example.myfitnessapp.R;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutViewHolder> {

    private List<WorkoutItem> workoutItemList;
    Activity activity;

    public WorkoutAdapter(List<WorkoutItem> list, Activity activity) {
        this.workoutItemList = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public WorkoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_workout_layout, parent, false);

        return new WorkoutViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutViewHolder holder, int position) {
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
