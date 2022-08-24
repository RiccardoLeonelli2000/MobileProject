package com.example.myfitnessapp.RecyclerView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.myfitnessapp.Item.NoticeItem;
import com.example.myfitnessapp.Item.WorkoutItem;

import java.util.List;

public class WorkoutItemDiffCallback extends DiffUtil.Callback {

    private final List<WorkoutItem> oldWorkouts;
    private final List<WorkoutItem> newWorkouts;

    public WorkoutItemDiffCallback(List<WorkoutItem> oldList, List<WorkoutItem> newList) {
        this.oldWorkouts = oldList;
        this.newWorkouts = newList;
    }

    @Override
    public int getOldListSize() {
        return oldWorkouts.size();
    }

    @Override
    public int getNewListSize() {
        return newWorkouts.size();
    }

    /**
     *
     * @param oldItemPosition position of the old item
     * @param newItemPosition position of the new item
     * @return true if the two items are the same
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldWorkouts.get(oldItemPosition) == newWorkouts.get(newItemPosition);
    }

    /**
     *
     * @param oldItemPosition position of the old item
     * @param newItemPosition position of the new item
     * @return true if the two item have the same content
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final WorkoutItem oldItem = oldWorkouts.get(oldItemPosition);
        final WorkoutItem newItem = newWorkouts.get(newItemPosition);
        return oldItem.getTitle().equals(newItem.getTitle());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}