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
    @Query("SELECT * FROM Workout ORDER BY workoutId")
    LiveData<List<WorkoutItem>> getWorkouts();

    @Query("SELECT * FROM Workout WHERE workoutId = :my_workout_id")
    WorkoutItem getWorkoutById(int my_workout_id);

    @Query("DELETE FROM Workout WHERE workoutId = :my_workout_id")
    void deleteWorkout(int my_workout_id);



}
