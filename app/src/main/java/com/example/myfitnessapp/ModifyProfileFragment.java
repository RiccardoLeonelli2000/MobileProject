package com.example.myfitnessapp;

import static com.example.myfitnessapp.Utilities.REQUEST_IMAGE_CAPTURE;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
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
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.myfitnessapp.PopUp.NewImagePopUp;
import com.example.myfitnessapp.ViewModel.AddNotificationsViewModel;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
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
            AddNotificationsViewModel addNotificationsViewModel = new ViewModelProvider((ViewModelStoreOwner) activity).get(AddNotificationsViewModel.class);


            view.findViewById(R.id.capture_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //PopUp page

                    Intent intent = new Intent(activity, NewImagePopUp.class);
                    startActivity(intent);


                }
            });

            ImageView modifyImageView = view.findViewById(R.id.picture_displayed_imageview);
            ImageView profileImageView = view.findViewById(R.id.picture_profile); //impostare immagine profilo in profile.xml TODO

            addNotificationsViewModel.getImageBitmap().observe(getViewLifecycleOwner(), new Observer<Bitmap>() {
                @Override
                public void onChanged(Bitmap bitmap) {
                    modifyImageView.setImageBitmap(bitmap);
                }
            });


            EditText nameEditText = view.findViewById(R.id.name_edittext);
            EditText surnameEditText = view.findViewById(R.id.surname_edittext);
            EditText dateEditText = view.findViewById(R.id.birthday_date_edittext);


            SharedPreferences sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);


            String image_path = sharedPreferences.getString(getString(R.string.imageProfile), "ic_baseline_account_circle_24");
            if (image_path.contains("ic_")){
                Drawable drawable = ResourcesCompat.getDrawable(activity.getResources(),
                        R.drawable.ic_baseline_account_circle_24, activity.getTheme());
                modifyImageView.setImageDrawable(drawable);
            } else {
                Bitmap bitmap = Utilities.getImageBitmap(activity, Uri.parse(image_path));
                if (bitmap != null){
                    modifyImageView.setImageBitmap(bitmap);
                    modifyImageView.setBackgroundColor(Color.WHITE);
                }
            }


            Button saveProfile = view.findViewById(R.id.add_exercise_button2);
            saveProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Bitmap bitmap = addNotificationsViewModel.getImageBitmap().getValue();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    String imageUriString = null;
                    try {
                        if (bitmap != null) {
                            try {
                                imageUriString = String.valueOf(saveImage(bitmap, activity));
                            } catch (FileNotFoundException ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            imageUriString = "ic_baseline_account_circle_24";
                        }
                        if (nameEditText.getText() != null && !nameEditText.getText().toString().equals("")) {
                            editor.putString(getString(R.string.nameProfile), nameEditText.getText().toString());
                        }
                        if (surnameEditText.getText() != null && !surnameEditText.getText().toString().equals("")) {
                            editor.putString(getString(R.string.surnameProfile), surnameEditText.getText().toString());
                        }
                        if (dateEditText.getText() != null  && !dateEditText.getText().toString().equals("")) {
                            editor.putString(getString(R.string.dateBirthayProfile), dateEditText.getText().toString());
                        }
                        if (imageUriString != null) {
                            editor.putString(getString(R.string.imageProfile), imageUriString);
                        }
                        editor.apply();
                        ((AppCompatActivity) activity).getSupportFragmentManager().popBackStack();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }



            });




        }
        else {
            Log.e("ModifyProfile", "Activity null");
        }

    }

    private Uri saveImage(Bitmap bitmap, Activity activity) throws FileNotFoundException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ITALY)
                .format(new Date());
        String name = "JPEG_" + timestamp + ".jpg";

        ContentResolver contentResolver = activity.getContentResolver();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg");

        Uri imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues);

        Log.d("ModifyProfile", String.valueOf(imageUri));

        OutputStream outputStream = contentResolver.openOutputStream(imageUri);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageUri;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.app_bar_calendar).setVisible(false);
    }

}
