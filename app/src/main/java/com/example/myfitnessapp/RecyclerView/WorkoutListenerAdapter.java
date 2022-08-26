package com.example.myfitnessapp.RecyclerView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


import com.example.myfitnessapp.Item.WorkoutItem;
import com.example.myfitnessapp.R;

public class WorkoutListenerAdapter extends RecyclerView.Adapter<WorkoutListenerViewHolder> {
//public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> implements Filterable {

    private List<WorkoutItem> workoutItemList = new ArrayList<>();

    private final Activity activity;

    private final OnItemListener listener;

    private List<WorkoutItem> cardItemListNotFiltered = new ArrayList<>();

    public WorkoutListenerAdapter(OnItemListener listener, Activity activity) {
        this.listener = listener;
        this.activity = activity;
    }

    /**
     *
     * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
     *
     * @param parent ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public WorkoutListenerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_workout_layout,
                parent,false);
        return new WorkoutListenerViewHolder(layoutView, listener);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method should update the contents of the RecyclerView.ViewHolder.itemView to reflect
     * the item at the given position.
     *
     * @param holder ViewHolder which should be updated to represent the contents of the item at
     *               the given position in the data set.
     * @param position position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull WorkoutListenerViewHolder holder, int position) {
        WorkoutItem currentWorkoutItem = workoutItemList.get(position);
        holder.titleView.setText(currentWorkoutItem.getTitle());

        String image = "ic_baseline_fitness_center_24";
        if (image.contains("ic_")){
            Drawable drawable = AppCompatResources.getDrawable(activity, activity.getResources().getIdentifier(image, "drawable", activity.getPackageName()));
            holder.imageView.setImageDrawable(drawable);
        }
    }

    @Override
    public int getItemCount() {
        return workoutItemList.size();
    }

    public void updateCardListItems(List<WorkoutItem> filteredList) {
        final WorkoutItemDiffCallback diffCallback =
                new WorkoutItemDiffCallback(this.workoutItemList, filteredList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.workoutItemList = new ArrayList<>(filteredList);
        diffResult.dispatchUpdatesTo(this);
    }

    /**
     * Method that set the list in the Home
     * @param list the list to display in the home
     */
    public void setData(List<WorkoutItem> list){
        final WorkoutItemDiffCallback diffCallback =
                new WorkoutItemDiffCallback(this.workoutItemList, list);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.workoutItemList = new ArrayList<>(list);
        this.cardItemListNotFiltered = new ArrayList<>(list);

        diffResult.dispatchUpdatesTo(this);
    }

    /**
     *
     * @param position the position of the item selected in the list displayed
     * @return the item selected
     */
    public WorkoutItem getItemSelected(int position) {
        return workoutItemList.get(position);
    }
}
