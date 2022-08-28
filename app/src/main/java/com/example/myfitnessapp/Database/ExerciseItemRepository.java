package com.example.myfitnessapp.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.myfitnessapp.Item.ExerciseItem;
import com.example.myfitnessapp.Item.WorkoutItem;

import java.util.List;

public class ExerciseItemRepository {

    private ExerciseItemDAO exerciseItemDAO;




    public ExerciseItemRepository(Application application){
        MyFitnessDatabase db = MyFitnessDatabase.getDatabase(application);
        exerciseItemDAO = db.exerciseItemDAO();

    }

    public void addExerciseToWorkout(ExerciseItem exerciseItem, int my_workout_id){
        exerciseItem.setWorkoutId(my_workout_id);
        MyFitnessDatabase.executor.execute(new Runnable() {
            @Override
            public void run() {
                exerciseItemDAO.addExerciseItemToWorkout(exerciseItem);
            }
        });
    }

    public LiveData<List<ExerciseItem>> getExercisesInWorkout(int my_workout_id) {
        return exerciseItemDAO.getExercisesInWorkout(my_workout_id);
    }

    public LiveData<List<ExerciseItem>> getAllExercises(){
        return exerciseItemDAO.getAllExercises();
    }

    public LiveData<List<WorkoutItem>> getLastWorkoutId(){
        return exerciseItemDAO.getLastWorkoutId();
    }

    public void deleteExercise(int exerciseId){
        exerciseItemDAO.deleteExercise(exerciseId);
    }
}
