package com.example.myfitnessapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.example.myfitnessapp.Global.GlobalClass;
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

            GlobalClass globalClass = (GlobalClass) activity.getApplicationContext();

            titleView = view.findViewById(R.id.title_esDetails);
            setsView = view.findViewById(R.id.reps_details);
            weightsView = view.findViewById(R.id.weights_details);
            repsView = view.findViewById(R.id.rest_details);

            ListExerciseViewModel listExerciseViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ListExerciseViewModel.class);
            listExerciseViewModel.getItemSelected().observe(getViewLifecycleOwner(), new Observer<ExerciseItem>() {
                @Override
                public void onChanged(ExerciseItem exerciseItem) {
                    exercise_id = exerciseItem.getId();

                    globalClass.setExerciseId(exercise_id);

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
                                    listExerciseViewModel.deleteExercise(exercise_id);
                                }
                            });
                            thread.start();
                            thread.interrupt();
                            ((AppCompatActivity) activity).getSupportFragmentManager().popBackStack();
                            dialog.dismiss();
                        }
                    });
                    alertDialog.show();


                }
            });

            Button modifyExercise = view.findViewById(R.id.button_modifyEx);
            modifyExercise.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utilities.insertFragment((AppCompatActivity) activity, new ModifyExerciseFragment(),
                            ModifyExerciseFragment.class.getSimpleName());
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
