package com.example.projectpacto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.projectpacto.databinding.ActivityHotelOrder4Binding;
import com.example.projectpacto.databinding.ActivityPlaneOrder3Binding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HotelOrderActivity4 extends AppCompatActivity {

    ActivityHotelOrder4Binding binding;

    int gambarKamar;
    String namaKamar;
    int kapasitasKamar;
    String tipeKasur;
    String sarapan;
    String hargaKamar;

    int gambarHotel;
    String namaHotel;
    String tambahanAlamat;
    String hargaMulaiHotel;
    int jmlBintang;

    String kotaAtauHotel;
    String jumlahKamar;
    String jumlahMalam;
    String tglCek_in;
    String tglCek_out;
    Date tglCek_in_date;
    Date tglCek_out_date;

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHotelOrder4Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        extras = getIntent().getBundleExtra("bundle");
        gambarKamar= extras.getInt("gambarKamar");
        namaKamar= extras.getString("");
        kapasitasKamar= extras.getInt("kapasitasKamar");
        tipeKasur= extras.getString("tipeKasur");
        sarapan= extras.getString("sarapan");
        hargaKamar= extras.getString("hargaKamar");

        gambarHotel= extras.getInt("gambarHotel");
        namaHotel= extras.getString("namaHotel");
        tambahanAlamat= extras.getString("tambahanAlamat");
        hargaMulaiHotel= extras.getString("harga");
        jmlBintang= extras.getInt("jmlBintang");

        kotaAtauHotel= extras.getString("kotaAtauHotel");
        jumlahKamar= extras.getString("jumlahKamar");
        jumlahMalam= extras.getString("jumlahMalam");
        tglCek_in= extras.getString("tglCek_out");
        tglCek_out= extras.getString("tglCek_in");

        Locale lokal = new Locale("id", "ID");

//        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy", lokal);
//        try {
//            tglCek_in_date = formatter.parse(tglCek_in);
//            tglCek_out_date  = formatter.parse(tglCek_out);
//
//            formatter =  new SimpleDateFormat("dd MMM yyyy");
//            tglCek_in = formatter.format(tglCek_in_date);
//        } catch (ParseException parseException) {
//            parseException.printStackTrace();
//        }

        Log.i("NAMAHOTEL", ""+namaHotel);
        Log.i("NAMAKAMAR", ""+namaKamar);
        Log.i("CEKIN", ""+tglCek_in);
        Log.i("CEKOUT", ""+tglCek_out);
        Log.i("JUMLAH KAMAR", ""+jumlahKamar);
        Log.i("KAPASITAS KAMAR", ""+kapasitasKamar);
        Log.i("TIPE KASIR", ""+tipeKasur);






    }
}