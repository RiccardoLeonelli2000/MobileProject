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
                }
            }
        }

        return INSTANCE;
    }
}
