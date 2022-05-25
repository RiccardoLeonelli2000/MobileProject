package com.example.myfitnessapp.Item;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notice")
public class NoticeItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "noticeId")
    private  int id;

    @ColumnInfo(name = "content")
    private String content;

    public NoticeItem(String content){
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
