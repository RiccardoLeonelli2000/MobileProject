package com.example.myfitnessapp.RecyclerView;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.Item.NoticeItem;
import com.example.myfitnessapp.R;
import com.example.myfitnessapp.Item.WorkoutItem;

import java.util.ArrayList;
import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsViewHolder> {

    private List<NoticeItem> notificationsItemList = new ArrayList<>();
    Activity activity;

    public NotificationsAdapter(List<NoticeItem> list, Activity activity) {
        this.notificationsItemList = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_notice_layout, parent, false);

        return new NotificationsViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsViewHolder holder, int position) {
        NoticeItem currentNoticeItem = notificationsItemList.get(position);
        holder.contentView.setText(currentNoticeItem.getContent());
    }

    @Override
    public int getItemCount() {
        return notificationsItemList.size();
    }

    public void setNotifications(List<NoticeItem> list){
        final NoticeItemDiffCallback diffCallback = new NoticeItemDiffCallback(this.notificationsItemList, list);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

        this.notificationsItemList = new ArrayList<>(list);
        diffResult.dispatchUpdatesTo(this);
    }
}
