package com.example.myfitnessapp.RecyclerView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.myfitnessapp.Item.NoticeItem;

import java.util.List;

public class NoticeItemDiffCallback extends DiffUtil.Callback {

    private final List<NoticeItem> oldNotifications;
    private final List<NoticeItem> newNotifications;

    public NoticeItemDiffCallback(List<NoticeItem> oldList, List<NoticeItem> newList) {
        this.oldNotifications = oldList;
        this.newNotifications = newList;
    }

    @Override
    public int getOldListSize() {
        return oldNotifications.size();
    }

    @Override
    public int getNewListSize() {
        return newNotifications.size();
    }

    /**
     *
     * @param oldItemPosition position of the old item
     * @param newItemPosition position of the new item
     * @return true if the two items are the same
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldNotifications.get(oldItemPosition) == newNotifications.get(newItemPosition);
    }

    /**
     *
     * @param oldItemPosition position of the old item
     * @param newItemPosition position of the new item
     * @return true if the two item have the same content
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final NoticeItem oldItem = oldNotifications.get(oldItemPosition);
        final NoticeItem newItem = newNotifications.get(newItemPosition);
        return oldItem.getContent().equals(newItem.getContent());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}