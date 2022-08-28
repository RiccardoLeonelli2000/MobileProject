package com.example.myfitnessapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.Item.NoticeItem;
import com.example.myfitnessapp.Item.WorkoutItem;
import com.example.myfitnessapp.RecyclerView.AllWorkoutAdapter;
import com.example.myfitnessapp.RecyclerView.OnItemListener;
import com.example.myfitnessapp.RecyclerView.WorkoutListenerAdapter;
import com.example.myfitnessapp.ViewModel.ListNotificationsViewModel;
import com.example.myfitnessapp.ViewModel.ListWorkoutViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class WorkoutsFragment extends Fragment implements OnItemListener {

    private WorkoutListenerAdapter workoutListenerAdapter;
    private ListWorkoutViewModel listWorkoutViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.workouts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final FragmentActivity activity = getActivity();

        if (activity != null){


            Utilities.setUpToolbar((AppCompatActivity) activity, getString(R.string.title_workouts));

            FloatingActionButton floatingActionButton = view.findViewById(R.id.fab_add);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utilities.insertFragment((AppCompatActivity) activity, new AddWorkoutFragment(),
                            AddWorkoutFragment.class.getSimpleName());
                }
            });

            setRecyclerView(activity);
            listWorkoutViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ListWorkoutViewModel.class);
            listWorkoutViewModel.getWorkoutsList().observe((LifecycleOwner) activity, new Observer<List<WorkoutItem>>() {
                @Override
                public void onChanged(List<WorkoutItem> cardItems) {
                    workoutListenerAdapter.setData(cardItems);
                }
            });
        }
        else {
            Log.e("WorkoutsFragment", "Activity null");
        }
    }


    private void setRecyclerView(final Activity activity) {
        RecyclerView recyclerView = activity.findViewById(R.id.recycler_view_workouts);
        recyclerView.setHasFixedSize(true);
        final OnItemListener listener = (OnItemListener) this;
        workoutListenerAdapter = new WorkoutListenerAdapter(listener, activity);
        recyclerView.setAdapter(workoutListenerAdapter);
    }


    public void onItemClick(int position) {
        Activity activity = getActivity();
        if (activity != null){
            Utilities.insertFragment((AppCompatActivity) activity, new DetailsWorkoutFragment(),
                    DetailsWorkoutFragment.class.getSimpleName());

            listWorkoutViewModel.setItemSelected(workoutListenerAdapter.getItemSelected(position));
        }
    }

}
