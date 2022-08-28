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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.myfitnessapp.Item.ExerciseItem;
import com.example.myfitnessapp.Item.WorkoutItem;
import com.example.myfitnessapp.ViewModel.ListExerciseViewModel;
import com.example.myfitnessapp.ViewModel.ListWorkoutViewModel;

public class DetailsExerciseFragment extends Fragment {

    private TextView titleView;
    private TextView setsView;
    private TextView weightsView;
    private TextView repsView;
    private int exercise_id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.exercise_details, container, false);
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
            Utilities.setUpToolbar((AppCompatActivity) activity, getString(R.string.title_detailsExercise) );

            titleView = view.findViewById(R.id.title_esDetails);
            setsView = view.findViewById(R.id.reps_details);
            weightsView = view.findViewById(R.id.weights_details);
            repsView = view.findViewById(R.id.rest_details);

            ListExerciseViewModel listExerciseViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ListExerciseViewModel.class);
            listExerciseViewModel.getItemSelected().observe(getViewLifecycleOwner(), new Observer<ExerciseItem>() {
                @Override
                public void onChanged(ExerciseItem exerciseItem) {
                    exercise_id = exerciseItem.getId();

                    titleView.setText(exerciseItem.getTitle());
                    setsView.setText(exerciseItem.getSets());
                    weightsView.setText(exerciseItem.getWeights());
                    repsView.setText(exerciseItem.getRest());
                }
            });

            Button deleteExercise = view.findViewById(R.id.button_deleteEx);
            deleteExercise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listExerciseViewModel.deleteExercise(exercise_id);
                    ((AppCompatActivity) activity).getSupportFragmentManager().popBackStack();
                }
            });


        }
        else {
            Log.e("DetailsExerciseFragment", "Activity null");
        }

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.app_bar_calendar).setVisible(false);
    }
}
