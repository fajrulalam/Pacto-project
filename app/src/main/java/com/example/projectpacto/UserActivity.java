package com.example.projectpacto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.projectpacto.databinding.ActivityUserBinding;
import com.google.android.material.navigation.NavigationBarView;

public class UserActivity extends AppCompatActivity {

    ActivityUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.bottomNav.setSelectedItemId(R.id.user);

        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.beranda:
                        startActivity(new Intent(getApplicationContext(), RealMainActivity.class));
                        overridePendingTransition(0 , 0);
                        return true;

                    case R.id.transaksi:
                        startActivity(new Intent(getApplicationContext(), TrankasiActivity.class));
                        overridePendingTransition(0 , 0);
                        return true;

                    case R.id.booking:
                        startActivity(new Intent(getApplicationContext(), BookingActivity.class));
                        overridePendingTransition(0 , 0);
                        return true;

                    case R.id.user:

                        return true;
                }


                return false;
            }
        });

        binding.bottomNav.setSelectedItemId(R.id.user);
    }
}