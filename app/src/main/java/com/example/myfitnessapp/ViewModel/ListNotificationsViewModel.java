package com.example.myfitnessapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.myfitnessapp.Database.NoticeItemRepository;
import com.example.myfitnessapp.Item.ExerciseItem;
import com.example.myfitnessapp.Item.NoticeItem;
import com.example.myfitnessapp.Item.WorkoutItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListNotificationsViewModel extends AndroidViewModel {

    private LiveData<List<NoticeItem>> notificationsList;
    private final MutableLiveData<NoticeItem> itemSelected = new MutableLiveData<>();
    private NoticeItemRepository noticeItemRepository;

    public ListNotificationsViewModel(@NotNull Application application) {
        super(application);
        noticeItemRepository = new NoticeItemRepository(application);
        this.notificationsList = noticeItemRepository.getNotificationsList();
    }

    public LiveData<List<NoticeItem>> getNotificationsList() {
        return this.notificationsList;
    }

    public void setItemSelected(NoticeItem itemSelected) {
        this.itemSelected.setValue(itemSelected);
    }

    public LiveData<NoticeItem> getItemSelected() {
        return itemSelected;
    }
    public void deleteNotice(int noticeId){
        noticeItemRepository.deleteNotice(noticeId);
    }
}
