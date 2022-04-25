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

public class NamaTersimpanActivity extends AppCompatActivity implements RecyclerAdapterNamaTersimpan.AddPassengerDetail, DataPenumpang.OnDataPassenger {

    ActivityNamaTersimpanBinding binding;

    ArrayList<String> nama_titel;
    ArrayList<String> NIKatauPaspor;
    ArrayList<String> nama;
    ArrayList<String> titel;
    ArrayList<String> tglLahir;
    ArrayList<String> kewarganegaraan;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNamaTersimpanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        nama_titel = new ArrayList<>();
        NIKatauPaspor = new ArrayList<>();
        nama = new ArrayList<>();
        titel = new ArrayList<>();
        tglLahir = new ArrayList<>();
        kewarganegaraan = new ArrayList<>();


        NIKatauPaspor.add("35171");
        nama.add("Fajrul");
        titel.add("Mr");
        tglLahir.add("27 Januari 2003");
        kewarganegaraan.add("Indonesia");


        NIKatauPaspor.add("35172");
        nama.add("Yoga");
        titel.add("Mr");
        tglLahir.add("27 Januari 1987");
        kewarganegaraan.add("Indonesia");


        NIKatauPaspor.add("35173");
        nama.add("Asad");
        titel.add("Ms");
        tglLahir.add("27 Januari 2003");
        kewarganegaraan.add("Australia");


        NIKatauPaspor.add("35174");
        nama.add("Rekyan");
        titel.add("Mr");
        tglLahir.add("27 Januari 1967");
        kewarganegaraan.add("Indonesia");

        for (int i = 0; i<nama.size(); i++){
            String nama_titel_str = nama.get(i) + " (" + titel.get(i) +")";
            nama_titel.add(nama_titel_str);
        }

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
        Log.i("index battle", "tgl Lahir: " + tglLahir.size() +" || index: " + index);
        DataPenumpang dataPenumpang = new DataPenumpang();
        Bundle bundle = new Bundle();
        bundle.putString("penumpangKe_n", "");
        bundle.putString("tglLahir_str", tglLahir.get(index));
        bundle.putString("nama_str", nama.get(index));
        bundle.putString("kewarganegaraan_str", kewarganegaraan.get(index));
        bundle.putString("NIKatauPaspor_str", NIKatauPaspor.get(index));
        bundle.putString("titel_str", titel.get(index));
        dataPenumpang.setArguments(bundle);
        dataPenumpang.show(getSupportFragmentManager(), dataPenumpang.getTag());
    }

    @Override
    public void onDataPass(String nama, String titel, String tglLahir, String kewarganegaraan, String nikAtauPaspor, int penumpangKe_n) {
        Log.i("Data should be saved", "SAVED");
    }
}

