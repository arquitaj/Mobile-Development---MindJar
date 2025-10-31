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

public class HotlineFragment extends Fragment implements View.OnClickListener {

    public HotlineFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private ImageView email1, email2, email3, phone1, phone2, phone3;
    @SuppressLint("MissingInflatedId")
    @Override
    public @NonNull View onCreateView(@NonNull LayoutInflater inflater,
                                      @Nullable ViewGroup container,
                                      @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotline, container, false);

        email1 = view.findViewById(R.id.imageEmail);
        email2 = view.findViewById(R.id.imageEmail2);
        email3 = view.findViewById(R.id.imageEmail3);
        phone1 = view.findViewById(R.id.imagePhone);
        phone2 = view.findViewById(R.id.imagePhone2);
        phone3 = view.findViewById(R.id.imagePhone3);

        email1.setOnClickListener(this);
        email2.setOnClickListener(this);
        email3.setOnClickListener(this);
        phone1.setOnClickListener(this);
        phone2.setOnClickListener(this);
        phone3.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if(id == R.id.imageEmail){
            openSMSApp("1800-1888-1553");
        }else if(id == R.id.imageEmail2){
            openSMSApp("09188784673");
        }else if(id == R.id.imageEmail3){
            openSMSApp("09228938944");
        }else if(id == R.id.imagePhone){
            openDialer("1800-1888-1553");
        }else if(id == R.id.imagePhone2){
            openDialer("09188784673");
        }else if(id == R.id.imagePhone3){
            openDialer("1800-1888-1553");
        }

    }
    private void openSMSApp(String phoneNumber) {
        try {
            // Use ACTION_SENDTO with smsto: URI (this ensures only SMS apps handle it)
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("smsto:" + phoneNumber)); // "smsto:" is required (NOT "sms:")
            intent.putExtra("sms_body", "Please help me.");

            if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(requireContext(), "No messaging app found.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Failed to open Messages: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void openDialer(String phoneNumber) {
        try {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phoneNumber)); // "tel:" is the correct scheme
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(requireContext(), "Failed to open dialer: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}