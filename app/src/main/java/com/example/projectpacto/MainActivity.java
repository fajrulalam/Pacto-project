package com.example.projectpacto;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectpacto.databinding.ActivityMainBinding;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

//    TextInputEditText emailEditText;
//    TextInputEditText passwordEditText;
//    TextView lupaPassword;
//    Button loginButton;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);




        binding.masukButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = String.valueOf(binding.emailEditText.getText());
                String password = binding.passwordEditText.getText().toString();
                if (email.matches("admin") && password.matches("password123")){
                    Toast.makeText(getApplicationContext(), "Login Succeessful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), RealMainActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();

                }
                Log.i(TAG, "onClick: Email is " + email);
                Log.i(TAG, "onClick: Password is " + password);
            }
        });




    }
}