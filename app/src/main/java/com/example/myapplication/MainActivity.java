package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private EditText userName;
    private EditText userPassword;
    private CheckBox checkPassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.button);
        userName = findViewById(R.id.editUserName);
        userPassword = findViewById(R.id.editPassword);
        checkPassword = findViewById(R.id.checkBox);

        btnLogin.setOnClickListener(this);

        checkPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // ✅ Show Password
                    userPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // 🔒 Hide Password
                    userPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                // Move cursor to end
                userPassword.setSelection(userPassword.getText().length());
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            toLogin(v);
        }
    }

    public void toLogin(View v) {
        String email = String.valueOf(userName.getText());
        String password = String.valueOf(userPassword.getText());

        /* ---------------- Do not remove ------------------ */
         if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
             Toast.makeText(MainActivity.this, "Email and/or Password is empty!", Toast.LENGTH_LONG).show();
         } else if (email.equals("user") && password.equals("user")) {
             Intent toDashboardPage = new Intent(MainActivity.this, Dashboard.class);
             startActivity(toDashboardPage);
         } else {
             Toast.makeText(MainActivity.this, "Incorrect Credentials!", Toast.LENGTH_LONG).show();
         }
    }
}