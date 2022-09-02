package com.example.myfitnessapp.Global;

import android.app.Application;

import com.example.myfitnessapp.ViewModel.AddNotificationsViewModel;

public class GlobalClass extends Application {
    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    private int workoutId = 1;
    private int workoutIdSelected = 0;
    private AddNotificationsViewModel addNotificationsViewModel;

    public int getWorkoutIdSelected() {
        return workoutIdSelected;
    }

    public void setWorkoutIdSelected(int workoutIdSelected) {
        this.workoutIdSelected = workoutIdSelected;
    }

    public AddNotificationsViewModel getAddNotificationsViewModel() {
        return addNotificationsViewModel;
    }

    public void setAddNotificationsViewModel(AddNotificationsViewModel addNotificationsViewModel) {
        this.addNotificationsViewModel = addNotificationsViewModel;
    }
}
