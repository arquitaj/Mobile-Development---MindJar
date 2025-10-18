package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Dashboard extends AppCompatActivity implements View.OnClickListener {

    private static final String KEY_SELECTED_ICON_ID = "selected_icon_id";

    private ImageView homeIcon, realizationIcon, hopeIcon, videosIcon, hotlineIcon;
    private int selectedIconId = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        // find views
        homeIcon = findViewById(R.id.homeIcon);
        realizationIcon = findViewById(R.id.realizationIcon);
        hopeIcon = findViewById(R.id.hopeIcon);
        videosIcon = findViewById(R.id.videosIcon);
        hotlineIcon = findViewById(R.id.hotlineIcon);

        homeIcon.setOnClickListener(this);
        realizationIcon.setOnClickListener(this);
        hopeIcon.setOnClickListener(this);
        videosIcon.setOnClickListener(this);
        hotlineIcon.setOnClickListener(this);

        if (savedInstanceState == null) {
            selectedIconId = R.id.homeIcon;
            accessFragments(new HomeFragment());
            tintSelection(homeIcon);
        } else {
            selectedIconId = savedInstanceState.getInt(KEY_SELECTED_ICON_ID, R.id.homeIcon);
            View v = findViewById(selectedIconId);
            if (v != null) changeColorIcon(v);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SELECTED_ICON_ID, selectedIconId);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.homeIcon || id == R.id.realizationIcon || id == R.id.hopeIcon
                || id == R.id.videosIcon || id == R.id.hotlineIcon) {
            changeColorIcon(v);
        }
    }

    private void changeColorIcon(View v) {
        // reset all to black
        int black = ContextCompat.getColor(this, R.color.black);
        homeIcon.setColorFilter(black);
        realizationIcon.setColorFilter(black);
        hopeIcon.setColorFilter(black);
        videosIcon.setColorFilter(black);
        hotlineIcon.setColorFilter(black);

        int active = Color.parseColor("#73903F");
        selectedIconId = v.getId();

        if (v.getId() == R.id.homeIcon) {
            accessFragments(new HomeFragment());
            homeIcon.setColorFilter(active);
        } else if (v.getId() == R.id.realizationIcon) {
            accessFragments(new RealizationFragment());
            realizationIcon.setColorFilter(active);
        } else if (v.getId() == R.id.hopeIcon) {
            accessFragments(new HopeFragment());
            hopeIcon.setColorFilter(active);
        } else if (v.getId() == R.id.videosIcon) {
            accessFragments(new VideosFragment());
            videosIcon.setColorFilter(active);
        } else if (v.getId() == R.id.hotlineIcon) {
            accessFragments(new HotlineFragment());
            hotlineIcon.setColorFilter(active);
        }
    }

    private void tintSelection(ImageView iv) {
        int black = ContextCompat.getColor(this, R.color.black);
        homeIcon.setColorFilter(black);
        realizationIcon.setColorFilter(black);
        hopeIcon.setColorFilter(black);
        videosIcon.setColorFilter(black);
        hotlineIcon.setColorFilter(black);
        iv.setColorFilter(Color.parseColor("#73903F"));
    }

    private void accessFragments(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.replace(R.id.fragmentContainerView2, fragment);
        tx.commit();
    }
}