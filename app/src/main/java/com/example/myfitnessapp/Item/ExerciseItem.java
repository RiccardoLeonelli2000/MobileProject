package com.example.myfitnessapp.Item;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Exercise")
public class ExerciseItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "exerciseId")
    private int id;

    @ColumnInfo
    private int workoutId;

    @ColumnInfo(name = "titleExercise")
    private String title;

    @ColumnInfo(name = "setsExercise")
    private String sets;

    @ColumnInfo(name = "weightsExercise")
    private String weights;

    @ColumnInfo(name = "restExercise")
    private String rest;

    public ExerciseItem(String title, String sets, String weights, String rest){
        this.title = title;
        this.sets = sets;
        this.weights = weights;
        this.rest = rest;
    }


    public String getTitle() {
        return title;
    }

    public String getSets() {
        return sets;
    }

    public String getWeights() {
        return weights;
    }

    public String getRest() {
        return rest;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }
}
