package com.example.myfitnessapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.myfitnessapp.Database.NoticeItemRepository;
import com.example.myfitnessapp.Database.WorkoutItemRepository;
import com.example.myfitnessapp.Item.NoticeItem;
import com.example.myfitnessapp.Item.WorkoutItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListWorkoutViewModel extends AndroidViewModel {

    private LiveData<List<WorkoutItem>> workoutsList;
    private final MutableLiveData<WorkoutItem> itemSelected = new MutableLiveData<>();

    private WorkoutItemRepository workoutItemRepository;


    public ListWorkoutViewModel(@NotNull Application application) {
        super(application);
        workoutItemRepository = new WorkoutItemRepository(application);
        this.workoutsList = workoutItemRepository.getWorkouts();
    }

    public LiveData<List<WorkoutItem>> getWorkoutsList() {
        return this.workoutsList;
    }

    public MutableLiveData<WorkoutItem> getItemSelected() {
        return itemSelected;
    }

    public void setItemSelected(WorkoutItem itemSelected) {
        this.itemSelected.setValue(itemSelected);
    }

    public void deleteWorkout(int workoutId){
        workoutItemRepository.deleteWorkout(workoutId);
    }



}