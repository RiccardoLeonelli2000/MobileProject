package com.example.myfitnessapp.Database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class MyFitnessDatabase_Impl extends MyFitnessDatabase {
  private volatile NoticeItemDAO _noticeItemDAO;

  private volatile WorkoutItemDAO _workoutItemDAO;

  private volatile ExerciseItemDAO _exerciseItemDAO;

  private volatile ProfileItemDAO _profileItemDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Notice` (`noticeId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `content` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Workout` (`workoutId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `titleWorkout` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Exercise` (`exerciseId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `workoutId` INTEGER NOT NULL, `titleExercise` TEXT, `setsExercise` TEXT, `weightsExercise` TEXT, `restExercise` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Profile` (`profileId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `surname` TEXT, `birthday` TEXT, `imageProfile` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '1ee4e5f43fefddaa727f4ddbc3496dc3')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Notice`");
        _db.execSQL("DROP TABLE IF EXISTS `Workout`");
        _db.execSQL("DROP TABLE IF EXISTS `Exercise`");
        _db.execSQL("DROP TABLE IF EXISTS `Profile`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsNotice = new HashMap<String, TableInfo.Column>(2);
        _columnsNotice.put("noticeId", new TableInfo.Column("noticeId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsNotice.put("content", new TableInfo.Column("content", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysNotice = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesNotice = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoNotice = new TableInfo("Notice", _columnsNotice, _foreignKeysNotice, _indicesNotice);
        final TableInfo _existingNotice = TableInfo.read(_db, "Notice");
        if (! _infoNotice.equals(_existingNotice)) {
          return new RoomOpenHelper.ValidationResult(false, "Notice(com.example.myfitnessapp.Item.NoticeItem).\n"
                  + " Expected:\n" + _infoNotice + "\n"
                  + " Found:\n" + _existingNotice);
        }
        final HashMap<String, TableInfo.Column> _columnsWorkout = new HashMap<String, TableInfo.Column>(2);
        _columnsWorkout.put("workoutId", new TableInfo.Column("workoutId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWorkout.put("titleWorkout", new TableInfo.Column("titleWorkout", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWorkout = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWorkout = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWorkout = new TableInfo("Workout", _columnsWorkout, _foreignKeysWorkout, _indicesWorkout);
        final TableInfo _existingWorkout = TableInfo.read(_db, "Workout");
        if (! _infoWorkout.equals(_existingWorkout)) {
          return new RoomOpenHelper.ValidationResult(false, "Workout(com.example.myfitnessapp.Item.WorkoutItem).\n"
                  + " Expected:\n" + _infoWorkout + "\n"
                  + " Found:\n" + _existingWorkout);
        }
        final HashMap<String, TableInfo.Column> _columnsExercise = new HashMap<String, TableInfo.Column>(6);
        _columnsExercise.put("exerciseId", new TableInfo.Column("exerciseId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercise.put("workoutId", new TableInfo.Column("workoutId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercise.put("titleExercise", new TableInfo.Column("titleExercise", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercise.put("setsExercise", new TableInfo.Column("setsExercise", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercise.put("weightsExercise", new TableInfo.Column("weightsExercise", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExercise.put("restExercise", new TableInfo.Column("restExercise", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExercise = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesExercise = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoExercise = new TableInfo("Exercise", _columnsExercise, _foreignKeysExercise, _indicesExercise);
        final TableInfo _existingExercise = TableInfo.read(_db, "Exercise");
        if (! _infoExercise.equals(_existingExercise)) {
          return new RoomOpenHelper.ValidationResult(false, "Exercise(com.example.myfitnessapp.Item.ExerciseItem).\n"
                  + " Expected:\n" + _infoExercise + "\n"
                  + " Found:\n" + _existingExercise);
        }
        final HashMap<String, TableInfo.Column> _columnsProfile = new HashMap<String, TableInfo.Column>(5);
        _columnsProfile.put("profileId", new TableInfo.Column("profileId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfile.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfile.put("surname", new TableInfo.Column("surname", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfile.put("birthday", new TableInfo.Column("birthday", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProfile.put("imageProfile", new TableInfo.Column("imageProfile", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProfile = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProfile = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProfile = new TableInfo("Profile", _columnsProfile, _foreignKeysProfile, _indicesProfile);
        final TableInfo _existingProfile = TableInfo.read(_db, "Profile");
        if (! _infoProfile.equals(_existingProfile)) {
          return new RoomOpenHelper.ValidationResult(false, "Profile(com.example.myfitnessapp.Item.ProfileItem).\n"
                  + " Expected:\n" + _infoProfile + "\n"
                  + " Found:\n" + _existingProfile);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "1ee4e5f43fefddaa727f4ddbc3496dc3", "75e91a9d26e4e734c069f7220a8bd0bb");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Notice","Workout","Exercise","Profile");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Notice`");
      _db.execSQL("DELETE FROM `Workout`");
      _db.execSQL("DELETE FROM `Exercise`");
      _db.execSQL("DELETE FROM `Profile`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(NoticeItemDAO.class, NoticeItemDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(WorkoutItemDAO.class, WorkoutItemDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(ExerciseItemDAO.class, ExerciseItemDAO_Impl.getRequiredConverters());
    _typeConvertersMap.put(ProfileItemDAO.class, ProfileItemDAO_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public NoticeItemDAO noticeItemDAO() {
    if (_noticeItemDAO != null) {
      return _noticeItemDAO;
    } else {
      synchronized(this) {
        if(_noticeItemDAO == null) {
          _noticeItemDAO = new NoticeItemDAO_Impl(this);
        }
        return _noticeItemDAO;
      }
    }
  }

  @Override
  public WorkoutItemDAO workoutItemDAO() {
    if (_workoutItemDAO != null) {
      return _workoutItemDAO;
    } else {
      synchronized(this) {
        if(_workoutItemDAO == null) {
          _workoutItemDAO = new WorkoutItemDAO_Impl(this);
        }
        return _workoutItemDAO;
      }
    }
  }

  @Override
  public ExerciseItemDAO exerciseItemDAO() {
    if (_exerciseItemDAO != null) {
      return _exerciseItemDAO;
    } else {
      synchronized(this) {
        if(_exerciseItemDAO == null) {
          _exerciseItemDAO = new ExerciseItemDAO_Impl(this);
        }
        return _exerciseItemDAO;
      }
    }
  }

  @Override
  public ProfileItemDAO profileItemDAO() {
    if (_profileItemDAO != null) {
      return _profileItemDAO;
    } else {
      synchronized(this) {
        if(_profileItemDAO == null) {
          _profileItemDAO = new ProfileItemDAO_Impl(this);
        }
        return _profileItemDAO;
      }
    }
  }
}
