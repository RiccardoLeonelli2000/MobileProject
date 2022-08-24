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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.myfitnessapp.Item.ExerciseItem;
import com.example.myfitnessapp.Item.WorkoutItem;
import com.example.myfitnessapp.ViewModel.AddExerciseViewModel;
import com.example.myfitnessapp.ViewModel.AddWorkoutViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class AddExerciseFragment extends Fragment {


    private AddExerciseViewModel exerciseViewModel;
    private AddWorkoutViewModel workoutViewModel;
    private int workoutId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_exercise, container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Activity activity = getActivity();
        if (activity != null){
            Utilities.setUpToolbar((AppCompatActivity) activity, getString(R.string.title_addExercise));

            exerciseViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(AddExerciseViewModel.class);
            workoutId = exerciseViewModel.getLastWorkoutId();

            TextInputEditText titleExercise = view.findViewById(R.id.title_exercise_edittext);
            TextInputEditText repsExercise = view.findViewById(R.id.sets_exercise_edittext);
            TextInputEditText weightsExercise = view.findViewById(R.id.weights_exercise_edittext);
            TextInputEditText restExercise = view.findViewById(R.id.rest_exercise_edittext);
            view.findViewById(R.id.add_exercise_button2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (titleExercise.getText() != null && repsExercise.getText() != null
                            && weightsExercise.getText() != null && restExercise.getText() != null){
                        ExerciseItem newExerciseItem = new ExerciseItem(titleExercise.getText().toString(), repsExercise.getText().toString(),weightsExercise.getText().toString(),restExercise.getText().toString());
                        exerciseViewModel.addExerciseItem(newExerciseItem,workoutId);
                        ((AppCompatActivity) activity).getSupportFragmentManager().popBackStack();
                    }

                }
            });



        }
        else {
            Log.e("AddWorkoutFragment", "Activity null");
        }

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.app_bar_calendar).setVisible(false);
    }

}
