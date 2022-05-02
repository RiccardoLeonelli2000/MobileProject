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
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.RecyclerView.WorkoutAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ActivitiesFragment extends Fragment {

    private WorkoutAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activities, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Activity activity = getActivity();
        if (activity != null){
            setRecyclerView(getActivity());

            FloatingActionButton floatingActionButton = view.findViewById(R.id.fab_add);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utilities.insertFragment((AppCompatActivity) activity, new AddWorkoutFragment(),
                            AddWorkoutFragment.class.getSimpleName());
                }
            });
        }
        else {
            Log.e("ActivitiesFragment", "Activity null");
        }
    }


    private void setRecyclerView(final Activity activity){
        RecyclerView recyclerView = activity.findViewById(R.id.recycler_view_activities);
        recyclerView.setHasFixedSize(true);

        List<WorkoutItem> list = new ArrayList<>();
        list.add(new WorkoutItem("ic_baseline_fitness_center_24", "Workout 1"));
        list.add(new WorkoutItem("ic_baseline_fitness_center_24", "Workout 2"));
        list.add(new WorkoutItem("ic_baseline_fitness_center_24", "Workout 3"));

        this.adapter = new WorkoutAdapter(list, activity);
        recyclerView.setAdapter(this.adapter);
    }
}
