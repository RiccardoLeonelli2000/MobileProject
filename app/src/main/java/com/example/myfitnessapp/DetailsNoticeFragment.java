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

import com.example.myfitnessapp.Item.ExerciseItem;
import com.example.myfitnessapp.Item.NoticeItem;
import com.example.myfitnessapp.ViewModel.ListExerciseViewModel;
import com.example.myfitnessapp.ViewModel.ListNotificationsViewModel;

public class DetailsNoticeFragment extends Fragment {

        private TextView contentWiew;
        private int noticeId;

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.notice_details, container, false);
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

                contentWiew = view.findViewById(R.id.content_noticeDetails);

                ListNotificationsViewModel listNotificationsViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(ListNotificationsViewModel.class);
                listNotificationsViewModel.getItemSelected().observe(getViewLifecycleOwner(), new Observer<NoticeItem>() {
                    @Override
                    public void onChanged(NoticeItem noticeItem) {
                        noticeId = noticeItem.getId();
                        System.out.println(noticeItem.getContent());
                        contentWiew.setText(noticeItem.getContent());
                    }
                });

                Button deleteNotice = view.findViewById(R.id.button_deleteN);
                deleteNotice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
                        alertDialog.setTitle(R.string.titleAlertDeleteN);
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
                                        listNotificationsViewModel.deleteNotice(noticeId);
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


