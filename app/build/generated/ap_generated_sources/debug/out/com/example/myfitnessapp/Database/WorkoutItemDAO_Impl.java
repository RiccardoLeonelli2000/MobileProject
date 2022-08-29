package com.example.myfitnessapp.Database;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
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
public final class WorkoutItemDAO_Impl implements WorkoutItemDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<WorkoutItem> __insertionAdapterOfWorkoutItem;

  private final SharedSQLiteStatement __preparedStmtOfDeleteWorkout;

  public WorkoutItemDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWorkoutItem = new EntityInsertionAdapter<WorkoutItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Workout` (`workoutId`,`titleWorkout`) VALUES (nullif(?, 0),?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, WorkoutItem value) {
        stmt.bindLong(1, value.getWorkoutId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
      }
    };
    this.__preparedStmtOfDeleteWorkout = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Workout WHERE workoutId = ?";
        return _query;
      }
    };
  }

  @Override
  public void addWorkoutItem(final WorkoutItem workoutItem) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfWorkoutItem.insert(workoutItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteWorkout(final int my_workout_id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteWorkout.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, my_workout_id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteWorkout.release(_stmt);
    }
  }

  @Override
  public LiveData<List<WorkoutItem>> getWorkouts() {
    final String _sql = "SELECT * FROM Workout ORDER BY workoutId";
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

  @Override
  public WorkoutItem getWorkoutById(final int my_workout_id) {
    final String _sql = "SELECT * FROM Workout WHERE workoutId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, my_workout_id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfWorkoutId = CursorUtil.getColumnIndexOrThrow(_cursor, "workoutId");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "titleWorkout");
      final WorkoutItem _result;
      if(_cursor.moveToFirst()) {
        final String _tmpTitle;
        if (_cursor.isNull(_cursorIndexOfTitle)) {
          _tmpTitle = null;
        } else {
          _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        }
        _result = new WorkoutItem(_tmpTitle);
        final int _tmpWorkoutId;
        _tmpWorkoutId = _cursor.getInt(_cursorIndexOfWorkoutId);
        _result.setWorkoutId(_tmpWorkoutId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
