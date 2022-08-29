package com.example.myfitnessapp.Global;

import android.app.Application;

public class GlobalClass extends Application {
    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    private int workoutId = 1;
    private int workoutIdSelected = 0;

    public int getWorkoutIdSelected() {
        return workoutIdSelected;
    }

    public void setWorkoutIdSelected(int workoutIdSelected) {
        this.workoutIdSelected = workoutIdSelected;
    }
}
