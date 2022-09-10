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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfitnessapp.Global.GlobalClass;
import com.example.myfitnessapp.Item.ExerciseItem;
import com.example.myfitnessapp.Item.WorkoutItem;
import com.example.myfitnessapp.RecyclerView.ExerciseListenerAdapter;
import com.example.myfitnessapp.RecyclerView.OnItemListener;
import com.example.myfitnessapp.RecyclerView.WorkoutListenerAdapter;
import com.example.myfitnessapp.ViewModel.ListExerciseViewModel;
import com.example.myfitnessapp.ViewModel.ListWorkoutViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailsWorkoutFragment extends Fragment implements OnItemListener {

    private int workoutId;
    private TextView titleView;
    private ExerciseListenerAdapter adapter;
    private ListExerciseViewModel exerciseViewModel;
    private List<ExerciseItem> exerciseItemList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.workout_details, container,false);
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
            Utilities.setUpToolbar((AppCompatActivity) activity, getString(R.string.title_detailsWorkout));

            titleView = view.findViewById(R.id.title_workout_textView);

            GlobalClass globalClass = (GlobalClass) activity.getApplicationContext();


            ListWorkoutViewModel listWorkoutViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ListWorkoutViewModel.class);
            listWorkoutViewModel.getItemSelected().observe(getViewLifecycleOwner(), new Observer<WorkoutItem>() {
                @Override
                public void onChanged(WorkoutItem workoutItem) {

                    titleView.setText(workoutItem.getTitle());
                    workoutId = workoutItem.getWorkoutId();
                    globalClass.setWorkoutIdSelected(workoutId);

                }
            });


            setRecyclerView(activity);
            exerciseViewModel = new ViewModelProvider((ViewModelStoreOwner) activity)
                    .get(ListExerciseViewModel.class);

            exerciseViewModel.getAllExercises().observe((LifecycleOwner) activity,new Observer<List<ExerciseItem>>() {
                @Override
                public void onChanged(List<ExerciseItem> cardItems) {
                    exerciseItemList = new ArrayList<>();
                    for (ExerciseItem exerciseItem: cardItems ) {
                        if (exerciseItem.getWorkoutId() == workoutId) {
                            exerciseItemList.add(exerciseItem);
                        }
                    }
                    adapter.setData(exerciseItemList);
                }
            });


            view.findViewById(R.id.button_deleteW).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
                    alertDialog.setTitle(R.string.titleAlertDeleteW);
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
                                    listWorkoutViewModel.deleteWorkout(workoutId);
                                    exerciseViewModel.deleteExercisesInWorkout(workoutId);
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

            view.findViewById(R.id.addOtherExercise).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utilities.insertFragment((AppCompatActivity) activity, new AddExerciseInWorkoutFragment(),
                            AddExerciseInWorkoutFragment.class.getSimpleName());
                }
            });



        }
        else {
            Log.e("DetailsWorkoutFragment", "Activity null");
        }

    }

    private void setRecyclerView(final Activity activity) {
        RecyclerView recyclerView = activity.findViewById(R.id.recyclerView_WorkoutDetails);
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        final OnItemListener listener = (OnItemListener) this;
        adapter = new ExerciseListenerAdapter(listener, activity);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.app_bar_calendar).setVisible(false);
    }

    @Override
    public void onItemClick(int position) {
        Activity activity = getActivity();
        if (activity != null){
            exerciseViewModel.setItemSelected(adapter.getItemSelected(position));

            Utilities.insertFragment((AppCompatActivity) activity, new DetailsExerciseFragment(),
                    DetailsExerciseFragment.class.getSimpleName());
        }

    }
}
