package com.example.myfitnessapp.Item;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Workout")
public class WorkoutItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "workout_id")
    private int workoutId;

    @ColumnInfo(name = "titleWorkout")
    private String title;

    @ColumnInfo(name = "image")
    private String imageResource;


    public WorkoutItem(String title, String imageResource){
        this.title = title;
        this.imageResource = imageResource;

    }

    public String getImageResource() {
        return imageResource;
    }

    public  String getTitle() { return title; }


    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

}
