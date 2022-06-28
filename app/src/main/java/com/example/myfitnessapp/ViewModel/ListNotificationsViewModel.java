package com.example.myfitnessapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.myfitnessapp.Database.NoticeItemRepository;
import com.example.myfitnessapp.Item.NoticeItem;
import com.example.myfitnessapp.Item.WorkoutItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListNotificationsViewModel extends AndroidViewModel {

    private LiveData<List<NoticeItem>> notificationsList;



    public ListNotificationsViewModel(@NotNull Application application) {
        super(application);
        NoticeItemRepository noticeItemRepository = new NoticeItemRepository(application);
        this.notificationsList = noticeItemRepository.getNotificationsList();
    }

    public LiveData<List<NoticeItem>> getNotificationsList() {
        return this.notificationsList;
    }

}
