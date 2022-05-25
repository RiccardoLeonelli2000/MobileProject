package com.example.myfitnessapp.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.myfitnessapp.Item.NoticeItem;

import java.util.List;

public class Repository {

    private NoticeItemDAO noticeItemDAO;
    private LiveData<List<NoticeItem>> notificationsList;

    public Repository(Application application){
        MyFitnessDatabase db = MyFitnessDatabase.getDatabase(application);
        noticeItemDAO = db.noticeItemDAO();
        notificationsList = noticeItemDAO.getNotifications();
    }

    public void addNotice(NoticeItem noticeItem){
        MyFitnessDatabase.executor.execute(new Runnable() {
            @Override
            public void run() {
                noticeItemDAO.addNoticeItem(noticeItem);
            }
        });
    }

    public LiveData<List<NoticeItem>> getNotificationsList() {
        return notificationsList;
    }



}
