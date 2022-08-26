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
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.Item.ExerciseItem;
import com.example.myfitnessapp.Item.NoticeItem;
import com.example.myfitnessapp.Item.WorkoutItem;
import com.example.myfitnessapp.RecyclerView.AllWorkoutAdapter;
import com.example.myfitnessapp.RecyclerView.ExerciseAdapter;
import com.example.myfitnessapp.ViewModel.AddWorkoutViewModel;
import com.example.myfitnessapp.ViewModel.ListExerciseViewModel;
import com.example.myfitnessapp.ViewModel.ListNotificationsViewModel;
import com.example.myfitnessapp.ViewModel.ListWorkoutViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class AddWorkoutFragment extends Fragment {

    private ExerciseAdapter adapter;
    private AllWorkoutAdapter workoutAdapter;
    private AddWorkoutViewModel workoutViewModel;
    private int workout_id;

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



            List<WorkoutItem> list = new ArrayList<>();
            this.workoutAdapter = new AllWorkoutAdapter(list, activity);
            ListWorkoutViewModel listWorkoutViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ListWorkoutViewModel.class);
            listWorkoutViewModel.getWorkoutsList().observe((LifecycleOwner) activity, new Observer<List<WorkoutItem>>() {
                @Override
                public void onChanged(List<WorkoutItem> cardItems) {
                    workoutAdapter.setWorkoutList(cardItems);

                    workout_id = workoutAdapter.getItemSelected(workoutAdapter.getItemCount()-1).getWorkoutId()+1;
                }
            });

            setRecyclerView(activity);
            ListExerciseViewModel exerciseViewModel = new ViewModelProvider((ViewModelStoreOwner) activity)
                    .get(ListExerciseViewModel.class);
            exerciseViewModel.getExercisesInWorkoutList(this.workout_id).observe((LifecycleOwner) activity, new Observer<List<ExerciseItem>>() {
                @Override
                public void onChanged(List<ExerciseItem> cardItems) {
                    adapter.setExerciseItemList(cardItems);
                }
            });

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
        this.adapter = new ExerciseAdapter(list, activity);
        recyclerView.setAdapter(this.adapter);
    }


}
