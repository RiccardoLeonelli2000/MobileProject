package com.example.myfitnessapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myfitnessapp.Item.ExerciseItem;
import com.example.myfitnessapp.Item.NoticeItem;
import com.example.myfitnessapp.Item.ProfileItem;
import com.example.myfitnessapp.Item.WorkoutItem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {NoticeItem.class, WorkoutItem.class, ExerciseItem.class, ProfileItem.class}, version = 1)
public abstract class MyFitnessDatabase extends RoomDatabase {

    public abstract NoticeItemDAO noticeItemDAO();
    public abstract WorkoutItemDAO workoutItemDAO();
    public abstract ExerciseItemDAO exerciseItemDAO();
    public abstract ProfileItemDAO profileItemDAO();

    private static volatile MyFitnessDatabase INSTANCE;

    static final ExecutorService executor = Executors.newFixedThreadPool(4);

    static MyFitnessDatabase getDatabase(final Context context) {
        if (INSTANCE == null){
            synchronized (MyFitnessDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MyFitnessDatabase.class, "MyFitnessDB").build();
                    prepopulateDB();
                }
            }
        }

        return INSTANCE;
    }

    private static void prepopulateDB() {
        executor.execute(() -> {
            WorkoutItemDAO wDao = INSTANCE.workoutItemDAO();
            NoticeItemDAO nDao = INSTANCE.noticeItemDAO();
            ExerciseItemDAO eDao = INSTANCE.exerciseItemDAO();

            wDao.addWorkoutItem(new WorkoutItem("Workout 1"));
            wDao.addWorkoutItem(new WorkoutItem("Workout 2"));
            wDao.addWorkoutItem(new WorkoutItem("Workout 3"));

            ExerciseItem e1 = new ExerciseItem("Exercise 1","10/12/10","100/110/100", "1/2");
            e1.setWorkoutId(1);
            eDao.addExerciseItemToWorkout(e1);
            ExerciseItem e2 = new ExerciseItem("Exercise 2","10x4","90", "1");
            e2.setWorkoutId(1);
            eDao.addExerciseItemToWorkout(e2);
            ExerciseItem e3 = new ExerciseItem("Exercise 3","10/12/10/12","100/90/100/90", "3/4/4/3");
            e3.setWorkoutId(1);
            eDao.addExerciseItemToWorkout(e3);

            e1 = new ExerciseItem("Exercise 1","12/12/12","22", "1/2");
            e1.setWorkoutId(2);
            eDao.addExerciseItemToWorkout(e1);
             e2 = new ExerciseItem("Exercise 2","8x4","150/150/100/90", "3 stripping");
            e2.setWorkoutId(2);
            eDao.addExerciseItemToWorkout(e2);


            e1 = new ExerciseItem("Run","15 min","level 18", "split");
            e1.setWorkoutId(3);
            eDao.addExerciseItemToWorkout(e1);

            nDao.addNoticeItem(new NoticeItem("Start Gym card \n \n On date: 2023-01-23 "));


        });
    }
}
