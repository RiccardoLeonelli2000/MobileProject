package com.example.myfitnessapp;

import static com.example.myfitnessapp.Utilities.REQUEST_IMAGE_CAPTURE;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.myfitnessapp.ViewModel.AddNotificationsViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class ModifyProfileFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.modify_profile, container,false);
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

            Utilities.setUpToolbar((AppCompatActivity) activity, getString(R.string.modify_profile));
            SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);

            view.findViewById(R.id.capture_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null){
                        activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }
                }
            });

            AddNotificationsViewModel addNotificationsViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(AddNotificationsViewModel.class);
            ImageView modifyImageView = view.findViewById(R.id.picture_displayed_imageview);
            ImageView profileImageView = view.findViewById(R.id.picture_profile); //impostare immagine profilo in profile.xml TODO

            addNotificationsViewModel.getImageBitmap().observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
                @Override
                public void onChanged(Bitmap bitmap) {
                    modifyImageView.setImageBitmap(bitmap);

                }
            });

            TextInputLayout nameInputLayout = view.findViewById(R.id.name_textinput);
            EditText nameEditText = view.findViewById(R.id.name_edittext);

            TextInputLayout surnameInputLayout = view.findViewById(R.id.surname_textinput);
            EditText surnameEditText = view.findViewById(R.id.surname_edittext);

            TextInputLayout dateInputLayout = view.findViewById(R.id.birthday_date_textinput);
            EditText dateEditText = view.findViewById(R.id.birthday_date_edittext);


            Button saveProfile = view.findViewById(R.id.add_exercise_button2);
            saveProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bitmap bitmap = addNotificationsViewModel.getImageBitmap().getValue();
                    if (bitmap != null) {
                        saveImage(bitmap, activity);
                    }
                    Utilities.insertFragment((AppCompatActivity) activity, new HomeFragment(),
                            HomeFragment.class.getSimpleName());
                }
            });




        }
        else {
            Log.e("ModifyProfile", "Activity null");
        }

    }

    private void saveImage(Bitmap bitmap, Activity activity) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ITALY).format(new Date());
        String name = "JPEG_"+timestamp+".jpg";
        ContentResolver contentResolver = activity.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");

        Uri imageURI = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        OutputStream outputStream = null;
        try {
            outputStream = contentResolver.openOutputStream(imageURI);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.app_bar_calendar).setVisible(false);
    }

}
