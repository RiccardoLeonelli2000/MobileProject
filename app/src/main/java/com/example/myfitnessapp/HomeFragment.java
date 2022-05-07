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

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private AllWorkoutAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Activity activity = getActivity();
        if (activity != null){
            Utilities.setUpToolbar((AppCompatActivity) activity, getString(R.string.app_name));
            setRecyclerView(getActivity());
        }
        else {
            Log.e("HomeFragment", "Activity null");
        }

    }

    private void setRecyclerView(final Activity activity){
        RecyclerView recyclerView = activity.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        List<WorkoutItem> list = new ArrayList<>();
        list.add(new WorkoutItem("card 1", "ic_baseline_android_24", "Le Tue Attività"));
        list.add(new WorkoutItem("card 2", "ic_baseline_android_24", "Calendario"));

        this.adapter = new AllWorkoutAdapter(list, activity);
        recyclerView.setAdapter(this.adapter);
    }
}
