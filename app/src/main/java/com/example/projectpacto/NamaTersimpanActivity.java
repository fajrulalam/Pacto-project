package com.example.projectpacto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.projectpacto.databinding.ActivityHotelOrder4Binding;
import com.example.projectpacto.databinding.ActivityNamaTersimpanBinding;

public class NamaTersimpanActivity extends AppCompatActivity {

    ActivityNamaTersimpanBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNamaTersimpanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


    }
}