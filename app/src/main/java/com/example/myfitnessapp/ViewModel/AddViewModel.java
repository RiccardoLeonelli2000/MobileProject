package com.example.myfitnessapp.ViewModel;

import static com.example.myfitnessapp.Utilities.drawableToBitmap;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myfitnessapp.Database.NoticeItemRepository;
import com.example.myfitnessapp.Item.NoticeItem;
import com.example.myfitnessapp.R;

public class AddViewModel extends AndroidViewModel {

    private final MutableLiveData<Bitmap> imageBitmap = new MutableLiveData<>();
    private Application application;

    private NoticeItemRepository noticeItemRepository;

    public AddViewModel(@NonNull Application application) {
        super(application);
        noticeItemRepository = new NoticeItemRepository(application);


    }

    public void setImageBitmap(Bitmap bitmap){
        imageBitmap.setValue(bitmap);
    }

    public MutableLiveData<Bitmap> getImageBitmap(){
        return this.imageBitmap;
    }

    public void initializeBitmap(){
        Drawable drawable = ResourcesCompat.getDrawable(application.getResources(), R.drawable.ic_baseline_account_box_24, application.getTheme());
        Bitmap bitmap = drawableToBitmap(drawable);

        imageBitmap.setValue(bitmap);
    }

    public void addNoticeItem(NoticeItem noticeItem){
        noticeItemRepository.addNotice(noticeItem);
    }
}
