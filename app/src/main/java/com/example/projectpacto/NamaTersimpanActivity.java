package com.example.projectpacto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectpacto.databinding.ActivityHotelOrder4Binding;
import com.example.projectpacto.databinding.ActivityNamaTersimpanBinding;

import java.util.ArrayList;

public class NamaTersimpanActivity extends AppCompatActivity implements RecyclerAdapterNamaTersimpan.AddPassengerDetail {

    ActivityNamaTersimpanBinding binding;

    ArrayList<String> nama_titel;
    ArrayList<String> NIKatauPaspor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNamaTersimpanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        nama_titel = new ArrayList<>();
        NIKatauPaspor = new ArrayList<>();

        nama_titel.add("Fajrul (Mr)");
        NIKatauPaspor.add("35171");
        nama_titel.add("Asad (Mr)");
        NIKatauPaspor.add("35172");
        nama_titel.add("Yoga (Mr)");
        NIKatauPaspor.add("35173");
        nama_titel.add("Rekyan (Mr)");
        NIKatauPaspor.add("35174");

        RecyclerAdapterNamaTersimpan recyclerAdapterNamaTersimpan = new RecyclerAdapterNamaTersimpan(nama_titel, NIKatauPaspor, this);
        binding.recyclerViewNamaTersimpan.setAdapter(recyclerAdapterNamaTersimpan);


        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }


    @Override
    public void addPassengerDetail(int penumpangKe_n) {

        int index = penumpangKe_n - 1;
        DataPenumpang dataPenumpang = new DataPenumpang();
        Bundle bundle = new Bundle();
//        bundle.putString("penumpangKe_n", nomorPelanggan);
//        bundle.putString("tglLahir_str", tglLahir.get(index));
//        bundle.putString("nama_str", namaPassenger.get(index));
//        bundle.putString("kewarganegaraan_str", kewarganegaraan.get(index));
//        bundle.putString("NIKatauPaspor_str", NIKatauPaspor.get(index));
//        bundle.putString("titel_str", titel.get(index));
        dataPenumpang.setArguments(bundle);
        dataPenumpang.show(getSupportFragmentManager(), dataPenumpang.getTag());
    }
}

