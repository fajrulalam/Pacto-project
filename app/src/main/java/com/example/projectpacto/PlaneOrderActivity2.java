package com.example.projectpacto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.projectpacto.databinding.ActivityPlaneOrder2Binding;
import com.example.projectpacto.databinding.ActivityRealMainBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class PlaneOrderActivity2 extends AppCompatActivity {
    ActivityPlaneOrder2Binding binding;
    ArrayList<Integer> logoMaskapai;
    ArrayList<String>namaMaskapai;
    ArrayList<String>waktuBerangkat;
    ArrayList<String>bandaraAsal;
    ArrayList<String> durasi;
    ArrayList<String> langsungAtauTransit;
    ArrayList<String> waktuDatang;
    ArrayList<String> bandaraTujuan;
    ArrayList<String> testCovid;
    ArrayList<String> harga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaneOrder2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        logoMaskapai = new ArrayList<>();
        namaMaskapai = new ArrayList<>();
        waktuBerangkat = new ArrayList<>();
        bandaraAsal = new ArrayList<>();
        durasi = new ArrayList<>();
        langsungAtauTransit = new ArrayList<>();
        waktuDatang = new ArrayList<>();
        bandaraTujuan = new ArrayList<>();
        testCovid = new ArrayList<>();
        harga = new ArrayList<>();

        logoMaskapai.add(R.drawable.ic_citilink);
        namaMaskapai.add("Lorem ipsum");
        waktuBerangkat.add("Lorem ipsum");
        bandaraAsal.add("Lorem ipsum");
        durasi.add("Lorem ipsum");
        langsungAtauTransit.add("Lorem ipsum");
        waktuDatang.add("Lorem ipsum");
        bandaraTujuan.add("Lorem ipsum");
        testCovid.add("Lorem ipsum");
        harga.add("Lorem ipsum");


//        RecyclerAdapaterBandara recyclerAdapaterBandara = new RecyclerAdapaterBandara(namaMaskapai, bandaraAsal, durasi);
        RecyclerAdapterPlaneTicket recyclerAdapterPlaneTicket = new RecyclerAdapterPlaneTicket(namaMaskapai,waktuBerangkat,bandaraAsal,durasi,langsungAtauTransit,waktuDatang, bandaraTujuan,testCovid,harga);
        binding.RecycleViewKotaBandara.setAdapter(recyclerAdapterPlaneTicket);

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