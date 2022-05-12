package com.example.projectpacto.roundtrip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.projectpacto.databinding.ActivityPlaneOrderActivity2PergiBinding;

public class PlaneOrderActivity2_Pergi extends AppCompatActivity {

    ActivityPlaneOrderActivity2PergiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaneOrderActivity2PergiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


//        setContentView(com.example.projectpacto.R.layout.activity_plane_order_activity2_pergi);
    }
}