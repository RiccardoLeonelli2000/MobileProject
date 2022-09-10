package com.example.myfitnessapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.myfitnessapp.Global.GlobalClass;
import com.example.myfitnessapp.Item.ExerciseItem;
import com.example.myfitnessapp.ViewModel.AddExerciseViewModel;
import com.example.myfitnessapp.ViewModel.ListExerciseViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ModifyExerciseFragment extends Fragment {

    private AddExerciseViewModel addExerciseViewModel;
    private TextView titleView;
    private ExerciseItem currentExerciseItem;
    private int workoutId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.modify_exercise, container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Activity activity = getActivity();

        if (activity != null) {
            GlobalClass globalClass = (GlobalClass) activity.getApplicationContext();
            int exerciseId = globalClass.getExerciseId();

            titleView = view.findViewById(R.id.title_esDetails_edit);
            TextInputEditText repsExercise = view.findViewById(R.id.sets_exercise_edittext_edit);
            TextInputEditText weightsExercise = view.findViewById(R.id.weights_exercise_edittext_edit);
            TextInputEditText restExercise = view.findViewById(R.id.rest_exercise_edittext_edit);

            ListExerciseViewModel listExerciseViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ListExerciseViewModel.class);
            listExerciseViewModel.getAllExercises().observe(getViewLifecycleOwner(), new Observer<List<ExerciseItem>>() {
                @Override
                public void onChanged(List<ExerciseItem> exerciseItems) {

                    for (ExerciseItem exerciseItem: exerciseItems ) {
                        if (exerciseItem.getId() == exerciseId) {
                            currentExerciseItem = exerciseItem;
                            workoutId = currentExerciseItem.getWorkoutId();
                            titleView.setText(currentExerciseItem.getTitle());
                            repsExercise.setHint("Reps for Sets: "+currentExerciseItem.getSets());
                            weightsExercise.setHint("Weights for Sets (Kg): "+currentExerciseItem.getWeights());
                            restExercise.setHint("Rest for Sets (min): "+currentExerciseItem.getRest());
                        }
                    }
                }
            });




            addExerciseViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(AddExerciseViewModel.class);
            view.findViewById(R.id.button_modifyEx_edit).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (repsExercise.getText() != null && weightsExercise.getText() != null && restExercise.getText() != null){

                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                listExerciseViewModel.deleteExercise(exerciseId);
                                ExerciseItem newExerciseItem = new ExerciseItem(titleView.getText().toString(), repsExercise.getText().toString(),weightsExercise.getText().toString(),restExercise.getText().toString());
                                addExerciseViewModel.addExerciseItem(newExerciseItem, workoutId);
                            }
                        });
                        thread.start();
                        thread.interrupt();
                        Utilities.insertFragment((AppCompatActivity) activity, new DetailsWorkoutFragment(),
                                DetailsWorkoutFragment.class.getSimpleName());                    }

                }
            });



            Button deleteExercise = view.findViewById(R.id.button_deleteEx_edit);
            deleteExercise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
                    alertDialog.setTitle(R.string.titleAlertDeleteEx);
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE,"DISMISS", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "DELETE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    listExerciseViewModel.deleteExercise(exerciseId);
                                }
                            });
                            thread.start();
                            thread.interrupt();
                            Utilities.insertFragment((AppCompatActivity) activity, new DetailsWorkoutFragment(),
                                    DetailsWorkoutFragment.class.getSimpleName());
                            dialog.dismiss();
                        }
                    });
                    alertDialog.show();


                }
            });


        } else {
            Log.e("ModifyExercise Fragment", "Activity null");
        }
    }
}
