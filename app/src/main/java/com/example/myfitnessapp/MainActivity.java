package com.example.myfitnessapp;

import static com.example.myfitnessapp.Utilities.REQUEST_IMAGE_CAPTURE;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.Manifest;
import android.annotation.SuppressLint;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.myfitnessapp.Global.GlobalClass;
import com.example.myfitnessapp.Item.NoticeItem;
import com.example.myfitnessapp.Item.WorkoutItem;
import com.example.myfitnessapp.PopUp.NewImagePopUp;
import com.example.myfitnessapp.ViewModel.AddNotificationsViewModel;
import com.example.myfitnessapp.ViewModel.ListNotificationsViewModel;
import com.example.myfitnessapp.ViewModel.ListWorkoutViewModel;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;


public class MainActivity extends AppCompatActivity{

    private int STORAGE_PERMISSION_CODE = 1;

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    WorkoutsFragment workoutsFragment = new WorkoutsFragment();
    NotificationsFragment notificationsFragment = new NotificationsFragment();
    HomeFragment profileFragment = new HomeFragment();

    private AddNotificationsViewModel addNotificationsViewModel;
    private ListNotificationsViewModel listNotificationsViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_view);

        Utilities.insertFragment(this, new HomeFragment(), HomeFragment.class.getSimpleName());

        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.navigation_notifications);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

        }
        else{
            this.requestStoragePermission();
        }


        listNotificationsViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(ListNotificationsViewModel.class);
        listNotificationsViewModel.getNotificationsList().observe(this, new Observer<List<NoticeItem>>() {
            @Override
            public void onChanged(List<NoticeItem> cardItems) {
                if (cardItems.size()!=0){
                    badgeDrawable.setVisible(true);
                    badgeDrawable.setNumber(cardItems.size());
                }
                else{
                    badgeDrawable.setVisible(false);
                }

            }
        });

        GlobalClass globalClass = (GlobalClass) getApplicationContext();
        ListWorkoutViewModel listWorkoutViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(ListWorkoutViewModel.class);
        listWorkoutViewModel.getWorkoutsList().observe(this, new Observer<List<WorkoutItem>>() {
            @Override
            public void onChanged(List<WorkoutItem> workoutItems) {
                if (workoutItems.size()>0){
                    globalClass.setWorkoutId(workoutItems.get(workoutItems.size()-1).getWorkoutId()+1);
                }
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.anim_left_to_right, R.anim.exit_left_to_right)
                            .setReorderingAllowed(true).replace(R.id.fragment_container_view, homeFragment).commit();
                        return true;
                    case R.id.navigation_workouts:
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.anim_left_to_right, R.anim.exit_left_to_right)
                                .setReorderingAllowed(true).replace(R.id.fragment_container_view, workoutsFragment).commit();
                        return true;
                    case R.id.navigation_notifications:
                        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.anim_left_to_right, R.anim.exit_left_to_right)
                                .setReorderingAllowed(true).replace(R.id.fragment_container_view, notificationsFragment).commit();
                        return true;
                }

                return false;
            }
        });

        addNotificationsViewModel = new ViewModelProvider(this).get(AddNotificationsViewModel.class);
        globalClass.setAddNotificationsViewModel(addNotificationsViewModel);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.notifications_card){
            Intent intent = new Intent(this, MapActivity.class);
            this.startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            if (bundle != null){
                Bitmap imageBitmap = (Bitmap) bundle.get("data");
                addNotificationsViewModel.setImageBitmap(imageBitmap);
            }
        }
    }

    public void requestStoragePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(this).setTitle("Permission Needed").setMessage("This permission is needed").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);

                }
            }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }
        else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANDED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}