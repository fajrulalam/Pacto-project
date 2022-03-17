package com.example.projectpacto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.projectpacto.databinding.ActivityPlaneOrder2Binding;
import com.example.projectpacto.databinding.ActivityPlaneOrder3Binding;

import java.util.ArrayList;

public class PlaneOrderActivity3 extends AppCompatActivity implements DataPenumpang.OnDataPassenger {
    Bundle bundle;

    ActivityPlaneOrder3Binding binding;


    String tanggalBerangkat_str;
    String waktuBerangkat_str;
    String bandaraAsal_str;
    int logoMaskapai_int;
    String namaMaskapai_str;
    String kelasPesawat_str;
    String tanggalDatang_str;
    String waktuDatang_str;
    String bandaraTujuan_str;
    String jmlDewasa_str;
    String jmlAnak_str;
    String jmlBalita_str;
    String kotaTujuan_str;
    String kotaAsal_str;
    String harga_str;

    ArrayList<String> namaPassenger;
    RecyclerAdapterPenumpangList recyclerAdapterPenumpangList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaneOrder3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        bundle = getIntent().getBundleExtra("bundle");
        harga_str =bundle.getString("harga");
        kotaAsal_str = bundle.getString("kotaAsal");
        kotaTujuan_str = bundle.getString("kotaTujuan");
        tanggalBerangkat_str = bundle.getString("tanggalBerangkat");
        waktuBerangkat_str= bundle.getString("waktuBerangkat");
        bandaraAsal_str= bundle.getString("bandaraAsal");
        logoMaskapai_int =bundle.getInt("logoMaskapai");
        namaMaskapai_str= bundle.getString("namaMaskapai");;
        kelasPesawat_str= bundle.getString("kelasPesawat");;
        tanggalDatang_str= bundle.getString("tanggalDatang");;
        waktuDatang_str= bundle.getString("waktuDatang");;
        bandaraTujuan_str= bundle.getString("bandaraTujuan");
        jmlDewasa_str= bundle.getString("jmlDewasa");;
        jmlAnak_str= bundle.getString("jmlAnak"); ;
        jmlBalita_str= bundle.getString("jmlBalita");;

        namaPassenger = new ArrayList<>();

        recyclerAdapterPenumpangList = new RecyclerAdapterPenumpangList(namaPassenger);
        binding.NamaPenumpangRecycleView.setAdapter(recyclerAdapterPenumpangList);



        Log.i("ACTIVITY3", "HARGA: "+ harga_str);
        Log.i("ACTIVITY3", "KOTAASAL: "+ kotaAsal_str);
        Log.i("ACTIVITY3", "KOTATUJUAN: "+ kotaTujuan_str);
        Log.i("ACTIVITY3", "INTLOGO: "+ logoMaskapai_int);
        Log.i("ACTIVITY3", "TGLBERANGKAT: "+ tanggalBerangkat_str);
        Log.i("ACTIVITY3", "TGLBERANGKAT: "+ waktuBerangkat_str);
        Log.i("ACTIVITY3", "DEWASA: "+ jmlDewasa_str);
        Log.i("ACTIVITY3", "ANAK: "+ jmlAnak_str);
        Log.i("ACTIVITY3", "BALITA: "+ jmlBalita_str);

        binding.editPassengerNamesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataPenumpang dataPenumpang = new DataPenumpang();
                dataPenumpang.show(getSupportFragmentManager(), dataPenumpang.getTag());
            }
        });
    }

    @Override
    public void onDataPass(String nama, String titel, String tglLahir, String kewarganegaraan, String nikAtauPaspor) {
        namaPassenger.add(nama);
        recyclerAdapterPenumpangList.notifyDataSetChanged();
        binding.penumpangKosong.setVisibility(View.GONE);

        Log.i("NAMA", nama);
        Log.i("titel", titel);
        Log.i("tglLahir", tglLahir);
        Log.i("kewaganegaraan", kewarganegaraan);
        Log.i("NIK/Passport", nikAtauPaspor);

    }
}