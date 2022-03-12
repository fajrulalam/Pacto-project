package com.example.projectpacto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.projectpacto.databinding.ActivityPlaneOrder2Binding;
import com.example.projectpacto.databinding.ActivityRealMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class PlaneOrderActivity2 extends AppCompatActivity {
    ActivityPlaneOrder2Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaneOrder2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.filter:
                        return true;
                    case R.id.sort:
                        return true;
                }
                return false;



            }
        });
    }
}