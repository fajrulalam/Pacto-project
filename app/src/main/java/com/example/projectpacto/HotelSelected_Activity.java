package com.example.projectpacto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class HotelSelected_Activity extends AppCompatActivity {

    int gambarHotel;
    String namaHotel;
    String tambahanAlamat;
    String harga;
    int jmlBintang;

    String hotelAtauKota_srch;
    String jumlahKamar;
    String jumlahMalam;
    String tglCek_out;
    String tglCek_in;

    TextView namaHotel_txt;
    TextView alamatTambahan_txt;
    RelativeLayout fotoGaleri;
    TextView jumlahFotoLain;
    LinearLayout fasilitasLainnyaButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_selected);


        Bundle extras = this.getIntent().getBundleExtra("bundle");
        gambarHotel = extras.getInt("gambarHotel");
        namaHotel = extras.getString("namaHotel");
        tambahanAlamat = extras.getString("tambahanAlamat");
        harga = extras.getString("harga");
        jmlBintang= extras.getInt("jmlBintang");
        hotelAtauKota_srch= extras.getString("kotaAtauHotel");
        jumlahKamar = extras.getString("jumlahKamar");
        jumlahMalam= extras.getString("jumlahMalam");
        tglCek_out= extras.getString("tglCek_out");
        tglCek_in= extras.getString("tglCek_in");


        namaHotel_txt = findViewById(R.id.namaHotel_txt);
        namaHotel_txt.setText(namaHotel);
        alamatTambahan_txt = findViewById(R.id.alamatTambahan);
        alamatTambahan_txt.setText(tambahanAlamat);
        jumlahFotoLain = findViewById(R.id.jumlahfotolain);
        fasilitasLainnyaButton = findViewById(R.id.lihatlainnya_btn);

        fotoGaleri = findViewById(R.id.fotogallery);
        ArrayList<Integer> gambar = new ArrayList<>();
        gambar.add(R.drawable.hotel_fasilitas1);
        gambar.add(R.drawable.hotel_fasilitas2);
        gambar.add(R.drawable.hotel_fasilitas3);
        gambar.add(R.drawable.hotel_fasilitas4);
        gambar.add(R.drawable.hotel_fasilitas1);
        gambar.add(R.drawable.hotel_fasilitas2);
        gambar.add(R.drawable.hotel_fasilitas3);
        gambar.add(R.drawable.hotel_fasilitas4);


        jumlahFotoLain.setText("+ " + gambar.size() + " Lainnya");



        fotoGaleri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putIntegerArrayList("gambar", gambar);
                FotoHotelGallery_BottomSheet galeriFotoHotelFragment = new FotoHotelGallery_BottomSheet();
                galeriFotoHotelFragment.setArguments(bundle);
                galeriFotoHotelFragment.show(getSupportFragmentManager(), galeriFotoHotelFragment.getTag());
            }
        });


        ArrayList<String> fasilitas = new ArrayList<>();
        fasilitas.add("Restoran");
        fasilitas.add("Wi-Fi");
        fasilitas.add("Air hangat");
        fasilitas.add("Laundry");
        fasilitas.add("Elevator");
        fasilitas.add("Transportasi umum");
        fasilitas.add("Pusat kebugaran");
        fasilitas.add("Kolam renang");
        fasilitas.add("Hewan peliharaan diperbolehkan");
        fasilitas.add("Call center 24 jam");
        fasilitas.add("Bathtub");
        fasilitas.add("Spa");
        fasilitas.add("Hiburan");

        fasilitasLainnyaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("fasilitas", fasilitas);
                FasilitasLainnyaBottomSheet fasilitasLainnyaBottomSheet = new FasilitasLainnyaBottomSheet();
                fasilitasLainnyaBottomSheet.setArguments(bundle);
                fasilitasLainnyaBottomSheet.show(getSupportFragmentManager(), fasilitasLainnyaBottomSheet.getTag());


            }
        });



        //BINTANG
        switch (jmlBintang){
            case 1:
                findViewById(R.id.star1).setVisibility(View.VISIBLE);
                findViewById(R.id.star2).setVisibility(View.INVISIBLE);
                findViewById(R.id.star3).setVisibility(View.INVISIBLE);
                findViewById(R.id.star4).setVisibility(View.INVISIBLE);
                findViewById(R.id.star5).setVisibility(View.INVISIBLE);
                break;

            case 2:
                findViewById(R.id.star1).setVisibility(View.VISIBLE);
                findViewById(R.id.star2).setVisibility(View.VISIBLE);
                findViewById(R.id.star3).setVisibility(View.INVISIBLE);
                findViewById(R.id.star4).setVisibility(View.INVISIBLE);
                findViewById(R.id.star5).setVisibility(View.INVISIBLE);
                break;


            case 3:
                findViewById(R.id.star1).setVisibility(View.VISIBLE);
                findViewById(R.id.star2).setVisibility(View.VISIBLE);
                findViewById(R.id.star3).setVisibility(View.VISIBLE);
                findViewById(R.id.star4).setVisibility(View.INVISIBLE);
                findViewById(R.id.star5).setVisibility(View.INVISIBLE);
                break;

            case 4:
                findViewById(R.id.star1).setVisibility(View.VISIBLE);
                findViewById(R.id.star2).setVisibility(View.VISIBLE);
                findViewById(R.id.star3).setVisibility(View.VISIBLE);
                findViewById(R.id.star4).setVisibility(View.VISIBLE);
                findViewById(R.id.star5).setVisibility(View.INVISIBLE);
                break;

            case 5:
                findViewById(R.id.star1).setVisibility(View.VISIBLE);
                findViewById(R.id.star2).setVisibility(View.VISIBLE);
                findViewById(R.id.star3).setVisibility(View.VISIBLE);
                findViewById(R.id.star4).setVisibility(View.VISIBLE);
                findViewById(R.id.star5).setVisibility(View.VISIBLE);
                break;


        }

        findViewById(R.id.lihatkamar_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HotelOrderActivity3.class);
                intent.putExtra("bundle", extras);
                startActivity(intent);
            }
        });


        findViewById(R.id.closeSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.closeSheet).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), HotelOrderActivity2.class);
                        intent.putExtra("tglCek_in", tglCek_in);
                        intent.putExtra("tglCek_out", tglCek_out);
                        intent.putExtra("jumlahKamar", jumlahKamar);
                        intent.putExtra("jumlahMalam", jumlahMalam);
                        intent.putExtra("kotaAtauHotel", hotelAtauKota_srch);
                        startActivity(intent);
                    }
                });
            }
        });


    }
}