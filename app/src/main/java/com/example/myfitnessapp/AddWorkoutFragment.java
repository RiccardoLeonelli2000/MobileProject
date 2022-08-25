package com.example.myfitnessapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.Item.ExerciseItem;
import com.example.myfitnessapp.Item.WorkoutItem;
import com.example.myfitnessapp.RecyclerView.ExerciseAdapter;
import com.example.myfitnessapp.ViewModel.AddWorkoutViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AddWorkoutFragment extends Fragment {

    private ExerciseAdapter adapter;
    private AddWorkoutViewModel workoutViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_workout, container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Activity activity = getActivity();
        if (activity != null){
            Utilities.setUpToolbar((AppCompatActivity) activity, getString(R.string.title_addWorkout));
            setRecyclerView(activity);

            Button button = view.findViewById(R.id.add_exercise_button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utilities.insertFragment((AppCompatActivity) activity, new AddExerciseFragment(),
                            AddExerciseFragment.class.getSimpleName());
                }
            });



            workoutViewModel = new ViewModelProvider((ViewModelStoreOwner) activity)
                    .get(AddWorkoutViewModel.class);
            TextInputEditText titleWorkout = view.findViewById(R.id.title_workout_edittext);

            FloatingActionButton floatingActionButton = view.findViewById(R.id.fab_add);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (titleWorkout!=null) {

                        workoutViewModel.addWorkoutItem(new WorkoutItem(titleWorkout.getText().toString()));

                        Utilities.insertFragment((AppCompatActivity) activity, new WorkoutsFragment(),
                                WorkoutsFragment.class.getSimpleName());

                    }


                }
            });



        }
        else {
            Log.e("AddWorkoutFragment", "Activity null");
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    /* CON QUESTO METODO E ONCREATE GESTICO GLI ELEMENTI DELLA TOPAPPBAR  */
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.app_bar_calendar).setVisible(false);
    }

    private void setRecyclerView(final Activity activity){
        RecyclerView recyclerView = activity.findViewById(R.id.recycler_view_exercises);
        recyclerView.setHasFixedSize(true);

        List<ExerciseItem> list = new ArrayList<>();
        list.add(new ExerciseItem("Panca Piana", " 5x5x5x5x5", "100Kg", "1 Min"));
        list.add(new ExerciseItem("Chest Press","10x10x8x8", "80Kgx80Kgx60Kgx60Kg", "1 Min"));
        list.add(new ExerciseItem("Bicipiti con Bilancere","10x10x10", "30Kg", "1 30 Min"));

        this.adapter = new ExerciseAdapter(list, activity);
        recyclerView.setAdapter(this.adapter);
    }


}
