package com.example.myfitnessapp.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.myfitnessapp.Item.NoticeItem;
import com.example.myfitnessapp.Item.WorkoutItem;

import java.util.List;

@Dao
public interface WorkoutItemDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addWorkoutItem(WorkoutItem workoutItem);

    @Transaction
    @Query("SELECT * FROM Workout ORDER BY workout_id")
    LiveData<List<WorkoutItem>> getWorkouts();

    @Query("SELECT * FROM Workout WHERE workout_id = :my_workout_id")
    WorkoutItem getWorkoutById(int my_workout_id);


}
