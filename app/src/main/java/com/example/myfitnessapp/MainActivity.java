package com.example.myfitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    ActivitiesFragment activitiesFragment = new ActivitiesFragment();
    NotificationsFragment notificationsFragment = new NotificationsFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_view);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, homeFragment).commit();

        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.navigation_notifications);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, homeFragment).commit();
                        return true;
                    case R.id.navigation_dashboard:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, activitiesFragment).commit();
                        return true;
                    case R.id.navigation_notifications:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, notificationsFragment).commit();
                        return true;
                    case R.id.navigation_profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_view, profileFragment).commit();
                        return true;
                }

                return false;
            }
        });
    }
}