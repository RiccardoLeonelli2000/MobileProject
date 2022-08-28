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


import com.example.myfitnessapp.Item.ExerciseItem;
import com.example.myfitnessapp.Item.WorkoutItem;
import com.example.myfitnessapp.R;

public class ExerciseListenerAdapter extends RecyclerView.Adapter<ExerciseListenerViewHolder> {
//public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> implements Filterable {

    private List<ExerciseItem> exerciseItemList = new ArrayList<>();

    private final Activity activity;

    private final OnItemListener listener;

    private List<ExerciseItem> cardItemListNotFiltered = new ArrayList<>();

    public ExerciseListenerAdapter(OnItemListener listener, Activity activity) {
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
    public ExerciseListenerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_exercise_layout,
                parent,false);
        return new ExerciseListenerViewHolder(layoutView, listener);
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
    public void onBindViewHolder(@NonNull ExerciseListenerViewHolder holder, int position) {
        ExerciseItem currentExerciseItem = exerciseItemList.get(position);
        holder.titleView.setText(currentExerciseItem.getTitle());
        holder.setsView.setText(currentExerciseItem.getSets());
        holder.weightsView.setText(currentExerciseItem.getWeights());
        holder.restView.setText(currentExerciseItem.getRest());

    }

    @Override
    public int getItemCount() {
        return exerciseItemList.size();
    }

    public void updateCardListItems(List<ExerciseItem> filteredList) {
        final ExerciseItemDiffCallback diffCallback =
                new ExerciseItemDiffCallback(this.exerciseItemList, filteredList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.exerciseItemList = new ArrayList<>(filteredList);
        diffResult.dispatchUpdatesTo(this);
    }

    /**
     * Method that set the list in the Home
     * @param list the list to display in the home
     */
    public void setData(List<ExerciseItem> list){
        final ExerciseItemDiffCallback diffCallback =
                new ExerciseItemDiffCallback(this.exerciseItemList, list);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.exerciseItemList = new ArrayList<>(list);
        this.cardItemListNotFiltered = new ArrayList<>(list);

        diffResult.dispatchUpdatesTo(this);
    }

    public void setExerciseItemList(List<ExerciseItem> list){
        this.exerciseItemList = new ArrayList<>(list);
        this.cardItemListNotFiltered = new ArrayList<>(list);
    }

    /**
     *
     * @param position the position of the item selected in the list displayed
     * @return the item selected
     */
    public ExerciseItem getItemSelected(int position) {
        return exerciseItemList.get(position);
    }
}
