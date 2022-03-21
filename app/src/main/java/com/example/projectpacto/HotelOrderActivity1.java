package com.example.projectpacto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.projectpacto.databinding.ActivityHotelOrder1Binding;
import com.example.projectpacto.databinding.ActivityPlaneOrder1Binding;

public class HotelOrderActivity1 extends AppCompatActivity {

    ActivityHotelOrder1Binding binding;
    String kotaAtauHotel;
    String tglCek_in;
    String tglCek_out;
    String jumlahKamar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHotelOrder1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //KOTA ATAU HOTEL
        binding.kotaAtauHotel.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b==true) {
                    Hotel_KotaAtauAkomodasi hotel_kotaAtauAkomodasi = new Hotel_KotaAtauAkomodasi();
                    Bundle bundle = new Bundle();
                    bundle.putString("judul", "Tentukan kota atau nama akomodasi");
                    hotel_kotaAtauAkomodasi.setArguments(bundle);
                    hotel_kotaAtauAkomodasi.show(getSupportFragmentManager(), hotel_kotaAtauAkomodasi.getTag());

                }
            }
        });
        binding.kotaAtauHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hotel_KotaAtauAkomodasi hotel_kotaAtauAkomodasi = new Hotel_KotaAtauAkomodasi();
                Bundle bundle = new Bundle();
                bundle.putString("judul", "Tentukan kota atau nama akomodasi");
                hotel_kotaAtauAkomodasi.setArguments(bundle);
                hotel_kotaAtauAkomodasi.show(getSupportFragmentManager(), hotel_kotaAtauAkomodasi.getTag());
            }
        });


    }

    public void HalYangPerluDiperhatikan(View view) {
        PesawatYangPerluDiperhatikan pesawatYangPerluDiperhatikan = new PesawatYangPerluDiperhatikan();
        Bundle bundle = new Bundle();


        switch (view.getTag().toString()) {
            case "Cara Reschedule":
                bundle.putString("judul", view.getTag().toString());
                bundle.putString("tanggal", "19 Januari 2020");

                break;
            case "Cara Pembatalan":
                bundle.putString("judul", view.getTag().toString());
                bundle.putString("tanggal", "21 Januari 2020");

                break;
            case "Persyaratan Perjalanan":
                bundle.putString("judul", view.getTag().toString());
                bundle.putString("tanggal", "25 Januari 2020");
                break;
        }
        pesawatYangPerluDiperhatikan.setArguments(bundle);
        pesawatYangPerluDiperhatikan.show(getSupportFragmentManager(), pesawatYangPerluDiperhatikan.getTag());
    }
}