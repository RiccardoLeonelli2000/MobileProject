package com.example.myfitnessapp.ViewModel;

import static com.example.myfitnessapp.Utilities.drawableToBitmap;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myfitnessapp.Database.ExerciseItemRepository;
import com.example.myfitnessapp.Database.NoticeItemRepository;
import com.example.myfitnessapp.Item.ExerciseItem;
import com.example.myfitnessapp.Item.NoticeItem;
import com.example.myfitnessapp.R;

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

    public int getLastWorkoutId(){
        return exerciseItemRepository.getLastWorkoutId();
    }
}