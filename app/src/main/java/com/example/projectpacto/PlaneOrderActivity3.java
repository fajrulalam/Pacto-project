package com.example.projectpacto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class PlaneOrderActivity3 extends AppCompatActivity {
    Bundle bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plane_order3);

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



        Log.i("ACTIVITY3", "HARGA: "+ harga_str);
        Log.i("ACTIVITY3", "KOTAASAL: "+ kotaAsal_str);
        Log.i("ACTIVITY3", "KOTATUJUAN: "+ kotaTujuan_str);
        Log.i("ACTIVITY3", "INTLOGO: "+ logoMaskapai_int);
        Log.i("ACTIVITY3", "TGLBERANGKAT: "+ tanggalBerangkat_str);
        Log.i("ACTIVITY3", "TGLBERANGKAT: "+ waktuBerangkat_str);
        Log.i("ACTIVITY3", "DEWASA: "+ jmlDewasa_str);
        Log.i("ACTIVITY3", "ANAK: "+ jmlAnak_str);
        Log.i("ACTIVITY3", "BALITA: "+ jmlBalita_str);
    }
}