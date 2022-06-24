package com.example.myfitnessapp.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.myfitnessapp.Item.ExerciseItem;
import com.example.myfitnessapp.Item.NoticeItem;
import com.example.myfitnessapp.Item.WorkoutItem;

import java.util.List;

@Dao
public interface ExerciseItemDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addExerciseItemToWorkout(ExerciseItem exerciseItem);

    @Transaction
    @Query("SELECT * FROM Exercise WHERE workoutId = :my_workout_id ORDER BY exercise_id")
    LiveData<List<ExerciseItem>> getExercisesInWorkout(int my_workout_id);


}
