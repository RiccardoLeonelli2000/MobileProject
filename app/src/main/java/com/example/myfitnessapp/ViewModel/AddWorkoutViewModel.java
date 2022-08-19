package com.example.myfitnessapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.myfitnessapp.Database.ExerciseItemRepository;
import com.example.myfitnessapp.Database.WorkoutItemRepository;
import com.example.myfitnessapp.Item.ExerciseItem;
import com.example.myfitnessapp.Item.WorkoutItem;

public class AddWorkoutViewModel extends AndroidViewModel {


    private Application application;

    private WorkoutItemRepository workoutItemRepository;

    public AddWorkoutViewModel(@NonNull Application application) {
        super(application);
        workoutItemRepository = new WorkoutItemRepository(application);


    }

    public void addWorkoutItem(WorkoutItem workoutItem){
        workoutItemRepository.addWorkout(workoutItem);
    }

    public int getLastWorkoutId(){
        return workoutItemRepository.getLastWorkoutId();
    }

}
