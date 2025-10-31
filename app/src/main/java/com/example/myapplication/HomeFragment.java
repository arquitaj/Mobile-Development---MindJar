package com.example.myapplication;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private ImageView iconHappy, iconSad, iconPressured, iconAngry;
    private Button btnSave;
    private boolean isIconSelected = false; // ✅ persistent flag
    private TextInputEditText textFeeling;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public @NonNull View onCreateView(@NonNull LayoutInflater inflater,
                                      @Nullable ViewGroup container,
                                      @Nullable Bundle savedInstanceState) {
        // Inflate the layout
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Access the icons by ID
        iconHappy = view.findViewById(R.id.happyIcon);
        iconSad = view.findViewById(R.id.sadIcon);
        iconPressured = view.findViewById(R.id.pressuredIcon);
        iconAngry = view.findViewById(R.id.angryIcon);
        btnSave = view.findViewById(R.id.button);
        textFeeling = view.findViewById(R.id.inputFeeling);

        // Set listeners
        iconHappy.setOnClickListener(this);
        iconSad.setOnClickListener(this);
        iconPressured.setOnClickListener(this);
        iconAngry.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        String userFeeling = "";
        if (textFeeling != null && textFeeling.getText() != null) {
            userFeeling = textFeeling.getText().toString().trim();
        }
        // Change background color based on clicked icon
        if (v.getId() == R.id.happyIcon || v.getId() == R.id.sadIcon || v.getId() == R.id.pressuredIcon || v.getId() == R.id.angryIcon) {
            // Reset all backgrounds first
            resetIconBackgrounds();
            v.setBackgroundColor(Color.parseColor("#A5D6A7")); // light green
            isIconSelected = true;
        }else if(v.getId() == R.id.button){
            if(!isIconSelected){
                handleSubmission("Please select an icon that corresponds to your feeling.");
            }else if (userFeeling.isEmpty()) {
                handleSubmission("Please describe your feeling before submitting.");
            }else{
                showConfirmationDialog(userFeeling);
            }



        }


    }

    private void resetIconBackgrounds() {
        iconHappy.setBackgroundColor(Color.TRANSPARENT);
        iconSad.setBackgroundColor(Color.TRANSPARENT);
        iconPressured.setBackgroundColor(Color.TRANSPARENT);
        iconAngry.setBackgroundColor(Color.TRANSPARENT);
    }


    private void showConfirmationDialog(String feelingText) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Confirm Submission")
                .setMessage("Are you sure you want to submit this feeling?\n\n" + feelingText)
                .setPositiveButton("Yes", (dialog, which) -> handleSubmission("Submitted successfully!"))
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void handleSubmission(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
        resetIconBackgrounds();
        textFeeling.setText("");
    }

}
