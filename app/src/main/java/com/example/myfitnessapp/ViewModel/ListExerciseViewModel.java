package com.example.myfitnessapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.myfitnessapp.Database.ExerciseItemRepository;
import com.example.myfitnessapp.Database.NoticeItemRepository;
import com.example.myfitnessapp.Item.ExerciseItem;
import com.example.myfitnessapp.Item.NoticeItem;
import com.example.myfitnessapp.Item.WorkoutItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListExerciseViewModel extends AndroidViewModel {

    private LiveData<List<ExerciseItem>> exercisesInWorkoutList;
    private ExerciseItemRepository exerciseItemRepository;
    private final MutableLiveData<ExerciseItem> itemSelected = new MutableLiveData<>();



    public ListExerciseViewModel(@NotNull Application application) {
        super(application);
        exerciseItemRepository = new ExerciseItemRepository(application);

    }

    public LiveData<List<ExerciseItem>> getExercisesInWorkoutList(int workout_id) {
        return exerciseItemRepository.getExercisesInWorkout(workout_id);
    }

    public void setItemSelected(ExerciseItem itemSelected) {
        this.itemSelected.setValue(itemSelected);
    }
}