package com.example.myfitnessapp.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.myfitnessapp.Item.NoticeItem;

import java.util.List;

@Dao
public interface NoticeItemDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addNoticeItem(NoticeItem noticeItem);

    @Transaction
    @Query("SELECT * FROM Notice ORDER BY noticeId DESC")
    LiveData<List<NoticeItem>> getNotifications();

    @Query("DELETE FROM Notice WHERE noticeId = :noticeId")
    void deleteNotice(int noticeId);


}
