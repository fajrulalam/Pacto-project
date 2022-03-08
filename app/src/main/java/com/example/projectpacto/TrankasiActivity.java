package com.example.projectpacto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.projectpacto.databinding.ActivityTrankasiBinding;
import com.google.android.material.navigation.NavigationBarView;

public class TrankasiActivity extends AppCompatActivity {

    ActivityTrankasiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrankasiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.bottomNav.setSelectedItemId(R.id.transaksi);


        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.beranda:
                        startActivity(new Intent(getApplicationContext(), RealMainActivity.class));
                        overridePendingTransition(0 , 0);
                        return true;

                    case R.id.transaksi:

                        return true;

                    case R.id.booking:
                        startActivity(new Intent(getApplicationContext(), BookingActivity.class));
                        overridePendingTransition(0 , 0);
                        return true;

                    case R.id.user:
                        startActivity(new Intent(getApplicationContext(), UserActivity.class));
                        overridePendingTransition(0 , 0);
                        return true;
                }


                return false;
            }
        });
    }
}