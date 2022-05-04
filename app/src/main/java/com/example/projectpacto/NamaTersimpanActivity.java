package com.example.projectpacto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectpacto.databinding.ActivityHotelOrder4Binding;
import com.example.projectpacto.databinding.ActivityNamaTersimpanBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class NamaTersimpanActivity extends AppCompatActivity implements RecyclerAdapterNamaTersimpan.AddPassengerDetail, DataPenumpang.OnDataPassenger {

    ActivityNamaTersimpanBinding binding;

    ArrayList<String> nama_titel;
    ArrayList<String> NIKatauPaspor;
    ArrayList<String> nama;
    ArrayList<String> titel;
    ArrayList<String> tglLahir;
    ArrayList<String> kewarganegaraan;
    RecyclerAdapterNamaTersimpan recyclerAdapterNamaTersimpan;



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

        recyclerAdapterNamaTersimpan = new RecyclerAdapterNamaTersimpan(nama_titel, NIKatauPaspor, this);
        binding.recyclerViewNamaTersimpan.setAdapter(recyclerAdapterNamaTersimpan);


        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }


    @Override
    public void addPassengerDetail(int penumpangKe_n, String query) {
        int index = penumpangKe_n - 1;
        Log.i("index battle", "tgl Lahir: " + tglLahir.size() + " || index: " + index);

        if (query.matches("edit")) {
            DataPenumpang dataPenumpang = new DataPenumpang();
            Bundle bundle = new Bundle();
            bundle.putString("penumpangKe_n", "");
            bundle.putInt("index", index);
            bundle.putString("tglLahir_str", tglLahir.get(index));
            bundle.putString("nama_str", nama.get(index));
            bundle.putString("kewarganegaraan_str", kewarganegaraan.get(index));
            bundle.putString("NIKatauPaspor_str", NIKatauPaspor.get(index));
            bundle.putString("titel_str", titel.get(index));
            dataPenumpang.setArguments(bundle);
            dataPenumpang.show(getSupportFragmentManager(), dataPenumpang.getTag());
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Yakin hapus nama " + nama.get(index)+"?")
                    .setMessage("\n")
                    .setPositiveButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                        }
                    })
                    .setNegativeButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            NIKatauPaspor.remove(index);
                            nama.remove(index);
                            titel.remove(index);
                            tglLahir.remove(index);
                            kewarganegaraan.remove(index);
                            nama_titel.remove(index);
                            recyclerAdapterNamaTersimpan = new RecyclerAdapterNamaTersimpan(nama_titel, NIKatauPaspor, NamaTersimpanActivity.this::addPassengerDetail);
                            binding.recyclerViewNamaTersimpan.setAdapter(recyclerAdapterNamaTersimpan);
                        }
                    }).show();


        }
    }

    @Override
    public void onDataPass(String nama_str, String titel_str, String tglLahir_str, String kewarganegaraan_str, String nikAtauPaspor_str, int penumpangKe_n) {

        NIKatauPaspor.set(penumpangKe_n,nikAtauPaspor_str );
        nama.set(penumpangKe_n,nama_str );
        titel.set(penumpangKe_n,titel_str );
        tglLahir.set(penumpangKe_n,tglLahir_str );
        kewarganegaraan.set(penumpangKe_n,kewarganegaraan_str );

        nama_titel.set(penumpangKe_n, nama.get(penumpangKe_n) + " (" + titel.get(penumpangKe_n) +")");

        recyclerAdapterNamaTersimpan = new RecyclerAdapterNamaTersimpan(nama_titel, NIKatauPaspor, this);
        binding.recyclerViewNamaTersimpan.setAdapter(recyclerAdapterNamaTersimpan);



        Log.i("Data should be saved", "SAVED");
    }
}

