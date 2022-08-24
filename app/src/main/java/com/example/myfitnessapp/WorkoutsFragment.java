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

import com.example.myfitnessapp.Item.WorkoutItem;
import com.example.myfitnessapp.RecyclerView.AllWorkoutAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class WorkoutsFragment extends Fragment {

    private AllWorkoutAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.workouts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Activity activity = getActivity();

        if (activity != null){

            setRecyclerView(getActivity());
            Utilities.setUpToolbar((AppCompatActivity) activity, getString(R.string.title_workouts));

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
            Log.e("WorkoutsFragment", "Activity null");
        }
    }


    private void setRecyclerView(final Activity activity){
        RecyclerView recyclerView = activity.findViewById(R.id.recycler_view_workouts);
        recyclerView.setHasFixedSize(true);

        List<WorkoutItem> list = new ArrayList<>();
        list.add(new WorkoutItem("Workout 1"));
        list.add(new WorkoutItem("Workout 2"));
        list.add(new WorkoutItem("Workout 3"));

        this.adapter = new AllWorkoutAdapter(list, activity);
        recyclerView.setAdapter(this.adapter);
    }
}
