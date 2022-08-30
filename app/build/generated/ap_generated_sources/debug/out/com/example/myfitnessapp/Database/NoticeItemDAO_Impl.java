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
import com.example.myfitnessapp.Item.NoticeItem;
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
public final class NoticeItemDAO_Impl implements NoticeItemDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<NoticeItem> __insertionAdapterOfNoticeItem;

  private final SharedSQLiteStatement __preparedStmtOfDeleteNotice;

  public NoticeItemDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNoticeItem = new EntityInsertionAdapter<NoticeItem>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Notice` (`noticeId`,`content`) VALUES (nullif(?, 0),?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NoticeItem value) {
        stmt.bindLong(1, value.getId());
        if (value.getContent() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getContent());
        }
      }
    };
    this.__preparedStmtOfDeleteNotice = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Notice WHERE noticeId = ?";
        return _query;
      }
    };
  }

  @Override
  public void addNoticeItem(final NoticeItem noticeItem) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfNoticeItem.insert(noticeItem);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteNotice(final int noticeId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteNotice.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, noticeId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteNotice.release(_stmt);
    }
  }

  @Override
  public LiveData<List<NoticeItem>> getNotifications() {
    final String _sql = "SELECT * FROM Notice ORDER BY noticeId DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"Notice"}, true, new Callable<List<NoticeItem>>() {
      @Override
      public List<NoticeItem> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "noticeId");
            final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
            final List<NoticeItem> _result = new ArrayList<NoticeItem>(_cursor.getCount());
            while(_cursor.moveToNext()) {
              final NoticeItem _item;
              final String _tmpContent;
              if (_cursor.isNull(_cursorIndexOfContent)) {
                _tmpContent = null;
              } else {
                _tmpContent = _cursor.getString(_cursorIndexOfContent);
              }
              _item = new NoticeItem(_tmpContent);
              final int _tmpId;
              _tmpId = _cursor.getInt(_cursorIndexOfId);
              _item.setId(_tmpId);
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
