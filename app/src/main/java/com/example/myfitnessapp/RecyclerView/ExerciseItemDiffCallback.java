package com.example.myfitnessapp.RecyclerView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.myfitnessapp.Item.ExerciseItem;
import com.example.myfitnessapp.Item.NoticeItem;
import com.example.myfitnessapp.Item.WorkoutItem;

import java.util.List;

public class ExerciseItemDiffCallback extends DiffUtil.Callback {

    private final List<ExerciseItem> oldExercises;
    private final List<ExerciseItem> newExercises;

    public ExerciseItemDiffCallback(List<ExerciseItem> oldList, List<ExerciseItem> newList) {
        this.oldExercises = oldList;
        this.newExercises = newList;
    }

    @Override
    public int getOldListSize() {
        return oldExercises.size();
    }

    @Override
    public int getNewListSize() {
        return newExercises.size();
    }

    /**
     *
     * @param oldItemPosition position of the old item
     * @param newItemPosition position of the new item
     * @return true if the two items are the same
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldExercises.get(oldItemPosition) == newExercises.get(newItemPosition);
    }

    /**
     *
     * @param oldItemPosition position of the old item
     * @param newItemPosition position of the new item
     * @return true if the two item have the same content
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final ExerciseItem oldItem = oldExercises.get(oldItemPosition);
        final ExerciseItem newItem = newExercises.get(newItemPosition);
        return oldItem.getTitle().equals(newItem.getTitle()) && oldItem.getSets().equals(newItem.getSets()) && oldItem.getWeights().equals(newItem.getWeights()) &&
                oldItem.getRest().equals(newItem.getRest());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}