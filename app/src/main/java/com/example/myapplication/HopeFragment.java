package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HopeFragment extends Fragment {

    public HopeFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private ImageView visibleImage, arrowLeft, arrowRight, heartIcon;
    private int currentIndex = 0;
    // 🖼️ Your image list in drawable
    private int[] hopeImages = {
            R.drawable.inspirational_image,   // your first image
            R.drawable.hope2,          // add your second
            R.drawable.hope3,          // add your third
            R.drawable.hope4           // add more as needed
    };

    @SuppressLint("MissingInflatedId")
    @Override
    public @NonNull View onCreateView(@NonNull LayoutInflater inflater,
                                      @Nullable ViewGroup container,
                                      @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hope, container, false);

        // Connect to your XML views
        arrowLeft = view.findViewById(R.id.arrowLeft);
        arrowRight = view.findViewById(R.id.arrowRight);
        visibleImage = view.findViewById(R.id.currentImage);
        heartIcon = view.findViewById(R.id.heart);

        // Set initial image and quote
        updateContent();

        // Next button (right arrow)
        arrowRight.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % hopeImages.length;
            updateContent();
        });

        //Previous button (left arrow)
        arrowLeft.setOnClickListener(v -> {
            currentIndex = (currentIndex - 1 + hopeImages.length) % hopeImages.length;
            updateContent();
        });

        //Click the heart Icon to like the image
        heartIcon.setOnClickListener(V ->{
            Toast.makeText(requireContext(), "You like this image.", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    // Helper to change image
    private void updateContent() {
        // Fade animation
        visibleImage.setAlpha(0f);

        visibleImage.setImageResource(hopeImages[currentIndex]);

        // Animate fade in
        visibleImage.animate().alpha(1f).setDuration(300).start();
    }
}