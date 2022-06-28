package com.example.myfitnessapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.myfitnessapp.Database.ExerciseItemRepository;
import com.example.myfitnessapp.Database.NoticeItemRepository;
import com.example.myfitnessapp.Item.ExerciseItem;
import com.example.myfitnessapp.Item.NoticeItem;
import com.example.myfitnessapp.Item.WorkoutItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListExerciseViewModel extends AndroidViewModel {

    private LiveData<List<ExerciseItem>> exercisesInWorkoutList;



    public ListExerciseViewModel(@NotNull Application application, int workout_id) {
        super(application);
        ExerciseItemRepository exerciseItemRepository = new ExerciseItemRepository(application);
        this.exercisesInWorkoutList = exerciseItemRepository.getExercisesInWorkout(workout_id);
    }

    public LiveData<List<ExerciseItem>> getExercisesInWorkoutList() {
        return this.exercisesInWorkoutList;
    }

}