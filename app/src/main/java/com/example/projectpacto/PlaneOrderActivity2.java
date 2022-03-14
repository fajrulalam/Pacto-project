package com.example.projectpacto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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
    ArrayList<Boolean> testCovid;
    ArrayList<String> harga;
    String keberangkatan;
    String kedatangan;
    String tanggal;
    String penumpang;

    String jmlDewasa;
    String jmlAnak;
    String jmlBalita;
    String kelasPesawat;
    int jumlPenumpang;

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


        //Recycle View on Click

        ItemClickSupport.addTo(binding.RecycleViewTicket).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                String waktuDatang =  ((TextView) v.findViewById(R.id.waktuDatang)).getText().toString();
                Log.i("WAKTU DATANG", waktuDatang);
                SelectedTicketBottomSheetFragment selectedTicketBottomSheetFragment = new SelectedTicketBottomSheetFragment();
                selectedTicketBottomSheetFragment.show(getSupportFragmentManager(), selectedTicketBottomSheetFragment.getTag());
            }
        });


        //Setting the top Bar
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            keberangkatan = extras.getString("keberangkatan");
            kedatangan = extras.getString("kedatangan");
            tanggal = extras.getString("tanggal");
            penumpang = extras.getString("penumpang");
            String[] passengerRaw = penumpang.split(", ");
            if (passengerRaw.length == 2) {
                jmlDewasa = passengerRaw[0].split(" ")[0];
                jmlAnak = "0";
                jmlBalita = "0";
            } else if (passengerRaw.length == 3) {
                jmlDewasa = passengerRaw[0].split(" ")[0];
                switch (passengerRaw[1].split(" ")[1]) {
                    case "Anak":
                        jmlAnak = passengerRaw[1].split(" ")[0];
                        jmlBalita = "0";
                        break;
                    case "Balita":
                        jmlBalita = passengerRaw[1].split(" ")[0];
                        jmlAnak= "0";
                        break;
                }
            } else {
                jmlDewasa = passengerRaw[0].split(" ")[0];
                jmlAnak = passengerRaw[1].split(" ")[0];
                jmlBalita = passengerRaw[2].split(" ")[0];
            }
            jumlPenumpang = Integer.parseInt(jmlDewasa) +  Integer.parseInt(jmlAnak) + Integer.parseInt(jmlBalita);
            kelasPesawat = passengerRaw[passengerRaw.length -1];



            Log.i("Dewasa",  "DEWASA "+  jmlDewasa);
            Log.i("Anak",  "ANAK "+ jmlAnak);
            Log.i("Balita",  "BALITA "+ jmlBalita);
            Log.i("TotalPenumpang",  "" + jumlPenumpang);
            Log.i("KelasPesawat",  "" + kelasPesawat);

            binding.kotaAsal.setText(keberangkatan.split(" ")[0]);
            binding.kotaTujuan.setText(kedatangan.split(" ")[0]);
            binding.detailPassenger.setText(jumlPenumpang + " Penumpang");
        } else {
            Log.i("Keberangkatan2", "gamasuk");
        }





        //Back Button
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlaneOrderActivity1.class);
                intent.putExtra("keberangkatan", keberangkatan);
                intent.putExtra("kedatangan", kedatangan);
                intent.putExtra("tanggal", tanggal);
                intent.putExtra("penumpang", penumpang);
                startActivity(intent);
                overridePendingTransition(0 , 0);
            }
        });
        //Ubah Button
        binding.ubahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlaneOrderActivity1.class);
                intent.putExtra("keberangkatan", keberangkatan);
                intent.putExtra("kedatangan", kedatangan);
                intent.putExtra("tanggal", tanggal);
                intent.putExtra("penumpang", penumpang);
                startActivity(intent);
                overridePendingTransition(0 , 0);
            }
        });


        //Displaying the Tickets.
        populateArrayLists();
        RecyclerAdapterPlaneTicket recyclerAdapterPlaneTicket = new RecyclerAdapterPlaneTicket(logoMaskapai, namaMaskapai,waktuBerangkat,bandaraAsal,durasi,langsungAtauTransit,waktuDatang, bandaraTujuan,testCovid,harga);
        binding.RecycleViewTicket.setAdapter(recyclerAdapterPlaneTicket);

        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.filter:
                        Log.i("Test", "Filter");
                        FilterFragment filterFragment = new FilterFragment();
                        filterFragment.show(getSupportFragmentManager(), filterFragment.getTag());
                        return true;
                    case R.id.sort:
                        SortPlaneTicket sortPlaneTicket = new SortPlaneTicket();
                        sortPlaneTicket.show(getSupportFragmentManager(), sortPlaneTicket.getTag());
                        return true;
                }
                return false;



            }
        });
    }

    public void populateArrayLists(){
        logoMaskapai.add(R.drawable.ic_citilink);
        namaMaskapai.add("Citilink");
        waktuBerangkat.add("06:30");
        bandaraAsal.add("SUB");
        durasi.add("50 menit");
        langsungAtauTransit.add("langsung");
        waktuDatang.add("07:20");
        bandaraTujuan.add("HLP");
        testCovid.add(true);
        harga.add("IDR 560.000");

        logoMaskapai.add(R.drawable.ic_citilink);
        namaMaskapai.add("Citilink");
        waktuBerangkat.add("10:30");
        bandaraAsal.add("SUB");
        durasi.add("50 menit");
        langsungAtauTransit.add("langsung");
        waktuDatang.add("11:20");
        bandaraTujuan.add("HLP");
        testCovid.add(false);
        harga.add("IDR 580.000");

        logoMaskapai.add(R.drawable.ic_citilink);
        namaMaskapai.add("Citilink");
        waktuBerangkat.add("15:10");
        bandaraAsal.add("SUB");
        durasi.add("50 menit");
        langsungAtauTransit.add("langsung");
        waktuDatang.add("16:00");
        bandaraTujuan.add("HLP");
        testCovid.add(true);
        harga.add("IDR 520.000");

        logoMaskapai.add(R.drawable.ic_lionair);
        namaMaskapai.add("Lion Air");
        waktuBerangkat.add("08:20");
        bandaraAsal.add("SUB");
        durasi.add("45 menit");
        langsungAtauTransit.add("langsung");
        waktuDatang.add("09:05");
        bandaraTujuan.add("HLP");
        testCovid.add(true);
        harga.add("IDR 570.000");

        logoMaskapai.add(R.drawable.ic_lionair);
        namaMaskapai.add("Lion Air");
        waktuBerangkat.add("11:05");
        bandaraAsal.add("SUB");
        durasi.add("45 menit");
        langsungAtauTransit.add("langsung");
        waktuDatang.add("12:50");
        bandaraTujuan.add("HLP");
        testCovid.add(true);
        harga.add("IDR 550.000");
    }
}