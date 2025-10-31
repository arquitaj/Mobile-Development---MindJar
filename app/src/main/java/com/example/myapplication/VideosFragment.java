package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class VideosFragment extends Fragment implements View.OnClickListener {

    public VideosFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private ImageView youtube1, youtube2, youtube3;
    @SuppressLint("MissingInflatedId")
    @Override
    public @NonNull View onCreateView(@NonNull LayoutInflater inflater,
                                      @Nullable ViewGroup container,
                                      @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_videos, container, false);

        // Connect to your XML views
        youtube1 = view.findViewById(R.id.youtubeIcon1);
        youtube2 = view.findViewById(R.id.youtubeIcon2);
        youtube3 = view.findViewById(R.id.youtubeIcon3);

        youtube1.setOnClickListener(this);
        youtube2.setOnClickListener(this);
        youtube3.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.youtubeIcon1) {
            openYouTubeLink("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        } else if (id == R.id.youtubeIcon2) {
            openYouTubeLink("https://www.youtube.com/watch?v=ysz5S6PUM-U");
        } else if (id == R.id.youtubeIcon3) {
            openYouTubeLink("https://www.youtube.com/watch?v=3fumBcKC6RE");
        } else {
            Toast.makeText(requireContext(), "Unknown icon clicked", Toast.LENGTH_SHORT).show();
        }

    }
    private void openYouTubeLink(String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setPackage("com.google.android.youtube"); // Try to open in YouTube app
            startActivity(intent);
        } catch (Exception e) {
            // If YouTube app not available, open in browser
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        }
    }


}