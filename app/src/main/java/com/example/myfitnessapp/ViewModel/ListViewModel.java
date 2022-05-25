package com.example.myfitnessapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.myfitnessapp.Database.Repository;
import com.example.myfitnessapp.Item.NoticeItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ListViewModel extends AndroidViewModel {

    private LiveData<List<NoticeItem>> notificationsList;

    public ListViewModel(@NotNull Application application) {
        super(application);
        Repository repository = new Repository(application);
        this.notificationsList = repository.getNotificationsList();
    }

    public LiveData<List<NoticeItem>> getNotificationsList() {
        return this.notificationsList;
    }

}
