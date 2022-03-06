package com.example.projectpacto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.projectpacto.databinding.ActivityRealMainBinding;

public class RealMainActivity extends AppCompatActivity {

    ActivityRealMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main);
    }
}