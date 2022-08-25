package com.example.myfitnessapp.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myfitnessapp.Database.ExerciseItemRepository;
import com.example.myfitnessapp.Item.ExerciseItem;
import com.example.myfitnessapp.Item.WorkoutItem;

import java.util.List;

public class AddExerciseViewModel extends AndroidViewModel {


    private Application application;

    private ExerciseItemRepository exerciseItemRepository;

    public AddExerciseViewModel(@NonNull Application application) {
        super(application);
        exerciseItemRepository = new ExerciseItemRepository(application);


    }

    public void addExerciseItem(ExerciseItem exerciseItem, int workout_id){
        exerciseItemRepository.addExerciseToWorkout(exerciseItem, workout_id);
    }

    public LiveData<List<WorkoutItem>> getLastWorkoutId(){
        return exerciseItemRepository.getLastWorkoutId();
    }
}