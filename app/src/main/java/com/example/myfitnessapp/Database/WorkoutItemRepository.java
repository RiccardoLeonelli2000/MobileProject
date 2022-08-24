package com.example.myfitnessapp.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.myfitnessapp.Item.NoticeItem;
import com.example.myfitnessapp.Item.WorkoutItem;

import java.util.List;

public class WorkoutItemRepository {

    private WorkoutItemDAO workoutItemDAO;
    private LiveData<List<WorkoutItem>> workouts;



    public WorkoutItemRepository(Application application){
        MyFitnessDatabase db = MyFitnessDatabase.getDatabase(application);
        workoutItemDAO = db.workoutItemDAO();
        workouts = workoutItemDAO.getWorkouts();


    }

    public void addWorkout(WorkoutItem workoutItem){
        MyFitnessDatabase.executor.execute(new Runnable() {
            @Override
            public void run() {
                workoutItemDAO.addWorkoutItem(workoutItem);
            }
        });
    }

    public LiveData<List<WorkoutItem>> getWorkouts() {
        return this.workouts;
    }



}
