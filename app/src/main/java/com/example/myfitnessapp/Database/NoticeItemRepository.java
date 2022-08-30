package com.example.myfitnessapp.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.myfitnessapp.Item.ExerciseItem;
import com.example.myfitnessapp.Item.NoticeItem;
import com.example.myfitnessapp.Item.WorkoutItem;

import java.util.List;

public class NoticeItemRepository {

    private NoticeItemDAO noticeItemDAO;
    private LiveData<List<NoticeItem>> notificationsList;



    public NoticeItemRepository(Application application){
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

    public void deleteNotice(int noticeId){
        noticeItemDAO.deleteNotice(noticeId);
    }




}
