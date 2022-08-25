package com.example.myfitnessapp.Database;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.myfitnessapp.Item.ExerciseItem;
import com.example.myfitnessapp.Item.WorkoutItem;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ExerciseItemDAO_Impl implements ExerciseItemDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ExerciseItem> __insertionAdapterOfExerciseItem;

  public ExerciseItemDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfExerciseItem = new EntityInsertionAdapter<ExerciseItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Exercise` (`exerciseId`,`workoutId`,`titleExercise`,`setsExercise`,`weightsExercise`,`restExercise`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ExerciseItem value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getWorkoutId());
        if (value.getTitle() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTitle());
        }
        if (value.getSets() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSets());
        }
        if (value.getWeights() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getWeights());
        }
        if (value.getRest() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getRest());
        }
      }
    };
  }

  @Override
  public void addExerciseItemToWorkout(final ExerciseItem exerciseItem) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfExerciseItem.insert(exerciseItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<ExerciseItem>> getExercisesInWorkout(final int my_workout_id) {
    final String _sql = "SELECT * FROM Exercise WHERE workoutId = ? ORDER BY exerciseId";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, my_workout_id);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Exercise"}, true, new Callable<List<ExerciseItem>>() {
      @Override
      public List<ExerciseItem> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "exerciseId");
            final int _cursorIndexOfWorkoutId = CursorUtil.getColumnIndexOrThrow(_cursor, "workoutId");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "titleExercise");
            final int _cursorIndexOfSets = CursorUtil.getColumnIndexOrThrow(_cursor, "setsExercise");
            final int _cursorIndexOfWeights = CursorUtil.getColumnIndexOrThrow(_cursor, "weightsExercise");
            final int _cursorIndexOfRest = CursorUtil.getColumnIndexOrThrow(_cursor, "restExercise");
            final List<ExerciseItem> _result = new ArrayList<ExerciseItem>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final ExerciseItem _item;
              final String _tmpTitle;
              if (_cursor.isNull(_cursorIndexOfTitle)) {
                _tmpTitle = null;
              } else {
                _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
              }
              final String _tmpSets;
              if (_cursor.isNull(_cursorIndexOfSets)) {
                _tmpSets = null;
              } else {
                _tmpSets = _cursor.getString(_cursorIndexOfSets);
              }
              final String _tmpWeights;
              if (_cursor.isNull(_cursorIndexOfWeights)) {
                _tmpWeights = null;
              } else {
                _tmpWeights = _cursor.getString(_cursorIndexOfWeights);
              }
              final String _tmpRest;
              if (_cursor.isNull(_cursorIndexOfRest)) {
                _tmpRest = null;
              } else {
                _tmpRest = _cursor.getString(_cursorIndexOfRest);
              }
              _item = new ExerciseItem(_tmpTitle,_tmpSets,_tmpWeights,_tmpRest);
              final int _tmpId;
              _tmpId = _cursor.getInt(_cursorIndexOfId);
              _item.setId(_tmpId);
              final int _tmpWorkoutId;
              _tmpWorkoutId = _cursor.getInt(_cursorIndexOfWorkoutId);
              _item.setWorkoutId(_tmpWorkoutId);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<WorkoutItem>> getLastWorkoutId() {
    final String _sql = "SELECT * FROM Workout ORDER BY workoutId DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Workout"}, true, new Callable<List<WorkoutItem>>() {
      @Override
      public List<WorkoutItem> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfWorkoutId = CursorUtil.getColumnIndexOrThrow(_cursor, "workoutId");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "titleWorkout");
            final List<WorkoutItem> _result = new ArrayList<WorkoutItem>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final WorkoutItem _item;
              final String _tmpTitle;
              if (_cursor.isNull(_cursorIndexOfTitle)) {
                _tmpTitle = null;
              } else {
                _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
              }
              _item = new WorkoutItem(_tmpTitle);
              final int _tmpWorkoutId;
              _tmpWorkoutId = _cursor.getInt(_cursorIndexOfWorkoutId);
              _item.setWorkoutId(_tmpWorkoutId);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
