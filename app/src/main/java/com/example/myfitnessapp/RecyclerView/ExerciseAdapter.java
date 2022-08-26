package com.example.myfitnessapp.RecyclerView;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.Item.ExerciseItem;
import com.example.myfitnessapp.Item.NoticeItem;
import com.example.myfitnessapp.R;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseViewHolder> {

    private List<ExerciseItem> exerciseItemList;
    Activity activity;

    public ExerciseAdapter(List<ExerciseItem> list, Activity activity) {
        this.exerciseItemList = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_exercise_layout, parent, false);

        return new ExerciseViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
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

    public void setExerciseItemList(List<ExerciseItem> list){
        final ExerciseItemDiffCallback diffCallback = new ExerciseItemDiffCallback(this.exerciseItemList, list);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.exerciseItemList = new ArrayList<>(list);
        diffResult.dispatchUpdatesTo(this);
    }
}
