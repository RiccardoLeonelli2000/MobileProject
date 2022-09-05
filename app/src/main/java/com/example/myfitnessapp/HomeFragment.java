package com.example.myfitnessapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.example.myfitnessapp.Calendar.CalendarActivity;

import java.text.MessageFormat;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Activity activity = getActivity();
        if (activity != null){
            Utilities.setUpToolbar((AppCompatActivity) activity, getString(R.string.app_name));

            SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);


            ImageView imageProfile = view.findViewById(R.id.picture_profile);
            TextView username = view.findViewById(R.id.usernameTextView);
            TextView birthday = view.findViewById(R.id.birthdayTextView);
            username.setText(MessageFormat.format("{0} {1}", sharedPreferences.getString(getString(R.string.nameProfile), "Name"), sharedPreferences.getString(getString(R.string.surnameProfile), "Surname")));
            birthday.setText(sharedPreferences.getString(getString(R.string.dateBirthayProfile), "Birthday date"));
            String image_path = sharedPreferences.getString(getString(R.string.imageProfile), "face");
            if (image_path.contains("ic_")){
                Drawable drawable = ResourcesCompat.getDrawable(activity.getResources(),
                        R.drawable.ic_baseline_account_circle_24, activity.getTheme());
                imageProfile.setImageDrawable(drawable);
            } else {
                Bitmap bitmap = Utilities.getImageBitmap(activity, Uri.parse(image_path));
                if (bitmap != null){
                    imageProfile.setImageBitmap(bitmap);
                    imageProfile.setBackgroundColor(Color.WHITE);
                }
            }

            view.findViewById(R.id.modify_profile_card).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utilities.insertFragment((AppCompatActivity) activity, new ModifyProfileFragment(),
                            ModifyProfileFragment.class.getSimpleName());
                }
            });


            view.findViewById(R.id.workouts_card).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utilities.insertFragment((AppCompatActivity) activity, new WorkoutsFragment(),
                            WorkoutsFragment.class.getSimpleName());
                }
            });

            view.findViewById(R.id.calendar_card).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, CalendarActivity.class);
                    startActivity(intent);
                }
            });

            CardView gymCard = view.findViewById(R.id.notifications_card);
            gymCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utilities.insertFragment((AppCompatActivity) activity, new NotificationsFragment(),
                            NotificationsFragment.class.getSimpleName());
                }
            });




        }
        else {
            Log.e("ProfileFragment", "Activity null");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.app_bar_calendar).setVisible(false);
    }
}
