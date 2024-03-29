package com.example.myfitnessapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.Item.NoticeItem;
import com.example.myfitnessapp.RecyclerView.NotificationsAdapter;
import com.example.myfitnessapp.RecyclerView.OnItemListener;
import com.example.myfitnessapp.RecyclerView.WorkoutListenerAdapter;
import com.example.myfitnessapp.ViewModel.ListNotificationsViewModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment implements OnItemListener{
    private NotificationsAdapter adapter;
    private ListNotificationsViewModel listNotificationsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.notifications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentActivity activity = getActivity();
        if (activity != null){
            Utilities.setUpToolbar((AppCompatActivity) activity, getString(R.string.title_notifications));
            setRecyclerView(activity);

            listNotificationsViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ListNotificationsViewModel.class);
            listNotificationsViewModel.getNotificationsList().observe(activity, new Observer<List<NoticeItem>>() {
                @Override
                public void onChanged(List<NoticeItem> cardItems) {

                    adapter.setData(cardItems);
                }
            });
        }
        else {
            Log.e("NotificationsFragment", "Activity null");
        }


    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.app_bar_calendar).setVisible(false);
    }

    private void setRecyclerView(final Activity activity){
        RecyclerView recyclerView = activity.findViewById(R.id.recycler_view_notifications);
        recyclerView.setHasFixedSize(true);
        final OnItemListener listener = (OnItemListener) this;
        adapter = new NotificationsAdapter(listener, activity);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Activity activity = getActivity();
        if (activity != null){
            Utilities.insertFragment((AppCompatActivity) activity, new DetailsNoticeFragment(),
                    DetailsNoticeFragment.class.getSimpleName());

            listNotificationsViewModel.setItemSelected(adapter.getItemSelected(position));
        }
    }
}
