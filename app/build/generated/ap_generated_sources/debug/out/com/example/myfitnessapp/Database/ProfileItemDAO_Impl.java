package com.example.myfitnessapp.Database;

import androidx.room.RoomDatabase;
import java.lang.Class;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ProfileItemDAO_Impl implements ProfileItemDAO {
  private final RoomDatabase __db;

  public ProfileItemDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
