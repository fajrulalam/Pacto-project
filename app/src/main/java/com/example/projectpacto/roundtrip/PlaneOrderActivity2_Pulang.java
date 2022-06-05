package com.example.projectpacto.roundtrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectpacto.FilterFragment;
import com.example.projectpacto.ItemClickSupport;
import com.example.projectpacto.PlaneOrderActivity1;
import com.example.projectpacto.R;
import com.example.projectpacto.RecyclerAdapterPlaneTicket_v2;
import com.example.projectpacto.SelectedTicketBottomSheetFragment_v2;
import com.example.projectpacto.SortPlaneTicket;
import com.example.projectpacto.databinding.ActivityPlaneOrderActivity2PergiBinding;
import com.example.projectpacto.databinding.ActivityPlaneOrderActivity2PulangBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class PlaneOrderActivity2_Pulang extends AppCompatActivity {

    ActivityPlaneOrderActivity2PulangBinding binding;
    ArrayList<ArrayList<Integer>> logoMaskapai_outer;
    ArrayList<ArrayList<String>> namaMaskapai_outer;
    ArrayList<ArrayList<String>> waktuBerangkat_outer;
    ArrayList<ArrayList<String>> bandaraAsal_outer;
    ArrayList<ArrayList<String>> durasi_outer;
    ArrayList<String> langsungAtauTransit;
    ArrayList<ArrayList<String>> terminalBerangkat_outer;
    ArrayList<ArrayList<String>> terminalDatang_outer;
    ArrayList<ArrayList<String>> waktuDatang_outer;
    ArrayList<ArrayList<String>> kodePenerbangan_outer;
    ArrayList<ArrayList<String>> bandaraTujuan_outer;
    ArrayList<ArrayList<String>> tanggalBerangkat_outer;
    ArrayList<ArrayList<String>> tanggalDatang_outer;
    ArrayList<ArrayList<String>> kabin_outer;
    ArrayList<ArrayList<String>> bagasi_outer;
    ArrayList<ArrayList<Integer>> booleanMakan_outer;
    ArrayList<ArrayList<String>> keteranganMakan_outer;
    ArrayList<ArrayList<String>> modelPesawat_outer;
    ArrayList<Boolean> testCovid;
    ArrayList<String> harga;
    ArrayList<String> jumlahpax;
    ArrayList<Integer> logoMaskapai_inner;
    ArrayList<String> namaMaskapai_inner;
    ArrayList<String> waktuBerangkat_inner;
    ArrayList<String> terminalDatang_inner;
    ArrayList<String> terminalBerangkat_inner;
    ArrayList<String> bandaraAsal_inner;
    ArrayList<String> durasi_inner;
    ArrayList<String> waktuDatang_inner;
    ArrayList<String> kodePenerbangan_inner;
    ArrayList<String> bandaraTujuan_inner;
    ArrayList<String> tanggalBerangkat_inner;
    ArrayList<String> tanggalDatang_inner;
    ArrayList<String> kabin_inner;
    ArrayList<String> bagasi_inner;
    ArrayList<Integer> booleanMakan_inner;
    ArrayList<String> keteranganMakan_inner;
    ArrayList<String> modelPesawat_inner;
    ArrayList<String> harga_dewasa;
    ArrayList<String> harga_balita;
    ArrayList<String> kelas_outer;
    String keberangkatan;
    String kedatangan;
    String tanggal;
    String tanggalPulang;
    String penumpang;

    Bundle extras;

    //Display Tiket Berangkat
    int logoPergi;
    String maskapaiPergi;
    String tanggalPergi;
    String waktuPergi;
    String hargaPergi;

    String jmlDewasa;
    String jmlAnak;
    String jmlBalita;
    String kelasPesawat;
    String bandara_kedatangan;
    String bandara_keberangkatan;
    String kota_kedatangan;
    String kota_keberangkatan;
    String bandara_keberangktan_raw;
    String bandara_kedatangan_raw;
    int jumlPenumpang_dewasa_anak;
    int juml_balita;
    int jumlPenumpang_total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaneOrderActivity2PulangBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        kodePenerbangan_outer = new ArrayList<>();
        logoMaskapai_outer = new ArrayList<>();
        namaMaskapai_outer = new ArrayList<>();
        waktuBerangkat_outer = new ArrayList<>();
        bandaraAsal_outer = new ArrayList<>();
        kabin_outer = new ArrayList<>();
        bagasi_outer = new ArrayList<>();
        booleanMakan_outer = new ArrayList<>();
        keteranganMakan_outer = new ArrayList<>();
        modelPesawat_outer = new ArrayList<>();
        durasi_outer = new ArrayList<>();
        langsungAtauTransit = new ArrayList<>();
        waktuDatang_outer = new ArrayList<>();
        bandaraTujuan_outer = new ArrayList<>();
        tanggalBerangkat_outer = new ArrayList<>();
        tanggalDatang_outer = new ArrayList<>();
        testCovid = new ArrayList<>();
        harga = new ArrayList<>();
        harga_dewasa = new ArrayList<>();
        harga_balita = new ArrayList<>();
        jumlahpax = new ArrayList<>();
        kodePenerbangan_inner = new ArrayList();
        logoMaskapai_inner = new ArrayList<>();
        namaMaskapai_inner = new ArrayList();
        waktuBerangkat_inner = new ArrayList();
        bandaraAsal_inner = new ArrayList();
        durasi_inner = new ArrayList();
        waktuDatang_inner = new ArrayList();
        bandaraTujuan_inner = new ArrayList();
        tanggalBerangkat_inner = new ArrayList();
        tanggalDatang_inner = new ArrayList();
        kabin_inner = new ArrayList<>();
        bagasi_inner = new ArrayList<>();
        booleanMakan_inner = new ArrayList<>();
        keteranganMakan_inner = new ArrayList<>();
        modelPesawat_inner = new ArrayList<>();
        kelas_outer = new ArrayList<>();
        terminalBerangkat_outer=new ArrayList<>();
        terminalBerangkat_inner =new ArrayList<>();
        terminalDatang_inner = new ArrayList<>();
        terminalDatang_outer =new ArrayList<>();


        //Recycle View on Click
        ItemClickSupport.addTo(binding.RecycleViewTicket).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                String waktuDatang = ((TextView) v.findViewById(R.id.waktuDatang)).getText().toString();
                String waktuBerangkat = ((TextView) v.findViewById(R.id.waktuBerangkat)).getText().toString();
                String harga = ((TextView) v.findViewById(R.id.harga)).getText().toString();

                for (int i = 0; i < namaMaskapai_outer.get(position).size(); i++) {
//                    jumlahpax.add("/" + jumlPenumpang_total + " pax");

                    kelas_outer.add(kelasPesawat);

                }

//                Bundle bundle = new Bundle();
                extras.putString("type", "pp_pulang");
                extras.putString("harga_pulang", harga);
                extras.putString("kotaAsal_pulang", kota_keberangkatan);
                extras.putString("kotaTujuan_pulang", kota_kedatangan);
                extras.putStringArrayList("tanggalBerangkat_pulang", tanggalBerangkat_outer.get(position));
                extras.putStringArrayList("waktuBerangkat_pulang", waktuBerangkat_outer.get(position));
                extras.putStringArrayList("bandaraAsal_pulang", bandaraAsal_outer.get(position));
                extras.putStringArrayList("terminalBerangkat_pulang", terminalBerangkat_outer.get(position));
                extras.putIntegerArrayList("logoMaskapai_pulang", logoMaskapai_outer.get(position));
                extras.putStringArrayList("kodePenerbangan_pulang", kodePenerbangan_outer.get(position));
                extras.putStringArrayList("namaMaskapai_pulang", namaMaskapai_outer.get(position));
                extras.putStringArrayList("kabin_pulang", kabin_outer.get(position));
                extras.putStringArrayList("bagasi_pulang", bagasi_outer.get(position));
                extras.putIntegerArrayList("booleanMakan_pulang", booleanMakan_outer.get(position));
                extras.putStringArrayList("keteranganMakan_pulang", keteranganMakan_outer.get(position));
                extras.putStringArrayList("modelPesawat_pulang", modelPesawat_outer.get(position));
                extras.putStringArrayList("kelasPesawat_pulang", kelas_outer);
                extras.putStringArrayList("tanggalDatang_pulang", tanggalDatang_outer.get(position));
                extras.putStringArrayList("waktuDatang_pulang", waktuDatang_outer.get(position));
                extras.putStringArrayList("bandaraTujuan_pulang", bandaraTujuan_outer.get(position));
                extras.putStringArrayList("terminalDatang_pulang", terminalDatang_outer.get(position));
                extras.putStringArrayList("durasi_pulang", durasi_outer.get(position));
                extras.putString("hargaBalita_pulang", harga_balita.get(position));
                extras.putString("hargaDewasa_pulang", harga_dewasa.get(position));
                extras.putString("jmlDewasa_pulang", jmlDewasa);
                extras.putString("jmlAnak_pulang", jmlAnak);
                extras.putString("jmlBalita_pulang", jmlBalita);
                extras.putString("keberangkatan_pulang", keberangkatan);
                extras.putString("kedatangan_pulang", kedatangan);
                extras.putString("tanggal", tanggal);
                extras.putString("tanggal_pulang", tanggalPulang);
                extras.putString("penumpang_pulang", penumpang);
                extras.putString("kota_keberangkatan_pulang", kota_keberangkatan);
                extras.putString("kota_kedatangan_pulang", kota_kedatangan);
                extras.putString("bandara_keberangkatan_pulang", bandara_keberangktan_raw);
                extras.putString("bandara_kedatangan_pulang", bandara_kedatangan_raw);


                SelectedTicketBottomSheetFragment_v2 selectedTicketBottomSheetFragment = new SelectedTicketBottomSheetFragment_v2();
                selectedTicketBottomSheetFragment.setArguments(extras);
                selectedTicketBottomSheetFragment.show(getSupportFragmentManager(), selectedTicketBottomSheetFragment.getTag());
            }
        });



        //Setting the top Bar
        extras = getIntent().getBundleExtra("bundle");
        if (extras != null) {
            keberangkatan = extras.getString("kedatangan"); //kedatangan dan keberangkatan di balik
            kedatangan = extras.getString("keberangkatan");
            tanggal = extras.getString("tanggal");
            tanggalPulang = extras.getString("tanggal_pulang");
            Log.i("Str tanggal_pulang", tanggalPulang +"tes");
            Log.i("TANGGAL PULANG", tanggalPulang + "tgl Pulang");
            penumpang = extras.getString("penumpang");
            kota_keberangkatan = extras.getString("kota_kedatangan"); //kota kedatangan dan keberangkatan di balik
            kota_kedatangan = extras.getString("kota_keberangkatan");
            bandara_keberangktan_raw = extras.getString("bandara_kedatangan"); //bandara kedatangan dan keberangkatan di balik
            bandara_kedatangan_raw = extras.getString("bandara_keberangkatan");
            bandara_keberangkatan = extras.getString("bandara_kedatangann") + " " + keberangkatan.split(" ")[1].replace(" ", "");
            bandara_kedatangan = extras.getString("bandara_keberangkatan") + " " + kedatangan.split(" ")[1];
            logoPergi = extras.getIntegerArrayList("logoMaskapai").get(0);
            tanggalPergi = extras.getStringArrayList("tanggalBerangkat").get(0);
            waktuPergi = extras.getStringArrayList("waktuBerangkat").get(0);
            hargaPergi = extras.getString("harga");
            maskapaiPergi = extras.getStringArrayList("namaMaskapai").get(0);



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
                        jmlAnak = "0";
                        break;
                }
            } else {
                jmlDewasa = passengerRaw[0].split(" ")[0];
                jmlAnak = passengerRaw[1].split(" ")[0];
                jmlBalita = passengerRaw[2].split(" ")[0];
            }
            jumlPenumpang_dewasa_anak = Integer.parseInt(jmlDewasa) + Integer.parseInt(jmlAnak);
            juml_balita = Integer.parseInt(jmlBalita);
            jumlPenumpang_total = jumlPenumpang_dewasa_anak + juml_balita;
            kelasPesawat = passengerRaw[passengerRaw.length - 1];
            kota_keberangkatan = keberangkatan.split(" ")[0];
            kota_kedatangan = kedatangan.split(" ")[0];




            binding.kotaAsal.setText(kota_keberangkatan);
            binding.kotaTujuan.setText(kota_kedatangan);
            binding.detailPassenger.setText(jumlPenumpang_total + " Penumpang");
        } else {
            Log.i("Keberangkatan2", "gamasuk");
        }

        //Setting the Pergi details
        binding.logoMaskapaiPergi.setImageResource(logoPergi);
        binding.namaMaskapaiPergi.setText(maskapaiPergi);
        binding.tanggalPergi.setText(tanggalPergi);
        binding.waktuPergi.setText(waktuPergi);
        binding.hargaPergi.setText(hargaPergi);
        binding.jumlahPenumpangPax.setText("/" +jumlPenumpang_total+ " pax");


        //Back Button
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlaneOrderActivity1.class);
                intent.putExtra("keberangkatan", keberangkatan);
                intent.putExtra("kedatangan", kedatangan);
                intent.putExtra("tanggal", tanggal);
                intent.putExtra("tanggal_pulang", tanggalPulang);
                intent.putExtra("penumpang", penumpang);
                intent.putExtra("bandara_kedatangan", bandara_kedatangan);
                intent.putExtra("bandara_keberangkatan", bandara_keberangkatan);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        //Tidak ditemukan penerbangan button
        binding.tombolKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlaneOrderActivity1.class);
                intent.putExtra("keberangkatan", keberangkatan);
                intent.putExtra("kedatangan", kedatangan);
                intent.putExtra("tanggal", tanggal);
                intent.putExtra("tanggal_pulang", tanggalPulang);
                intent.putExtra("penumpang", penumpang);
                intent.putExtra("bandara_kedatangan", bandara_kedatangan);
                intent.putExtra("bandara_keberangkatan", bandara_keberangkatan);
                startActivity(intent);
                overridePendingTransition(0, 0);
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
                intent.putExtra("tanggal_pulang", tanggalPulang);
                intent.putExtra("penumpang", penumpang);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });



        String kodeBandara_datang = bandara_kedatangan.split("\\(")[1].replace(")", "");
        String kodeBandara_berangkat= bandara_keberangkatan.split("\\(")[1].replace(")", "");

        Log.i("kedatangan", kodeBandara_datang);
        Log.i("keberangkatan", kodeBandara_berangkat);

        //Displaying the Tickets.
        if (kodeBandara_berangkat.matches("SUB") && kodeBandara_datang.matches("HLP")){
        }
        populateArrayLists(kodeBandara_berangkat, kodeBandara_datang);
        for (int i = 0; i <= namaMaskapai_outer.size(); i++) {
            jumlahpax.add("/" + jumlPenumpang_total + " pax");
//            kelas_outer.add(kelasPesawat);

        }

//        RecyclerAdapterPlaneTicket_v2 recyclerAdapterPlaneTicket_v2 = new RecyclerAdapterPlaneTicket_v2(kodePenerbangan_outer, logoMaskapai_outer,  namaMaskapai_outer,  waktuBerangkat_outer,  bandaraAsal_outer,  kabin_outer,  bagasi_outer, booleanMakan_outer, keteranganMakan_outer,  modelPesawat_outer, durasi_outer,  waktuDatang_outer, bandaraTujuan_outer,  tanggalBerangkat_outer, tanggalDatang_outer, testCovid,  harga,  jumlahpax);
//        binding.RecycleViewTicket.setAdapter(recyclerAdapterPlaneTicket_v2);

//        RecyclerAdapterPlaneTicket recyclerAdapterPlaneTicket = new RecyclerAdapterPlaneTicket(jumlahpax, logoMaskapai, namaMaskapai, waktuBerangkat, bandaraAsal, durasi, langsungAtauTransit, waktuDatang, bandaraTujuan, testCovid, harga);
//        binding.RecycleViewTicket.setAdapter(recyclerAdapterPlaneTicket);

        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

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

    public void populateArrayLists(String kodeBandara_berangkat, String kodeBandara_kedatangan) {
        int hargasatuan_dewasa_anak;
        int hargasatuan_balita;
        String hargapax;
//        String kodeBandara_berangkat=  bandara_keberangkatan.split("\\(")[1].replace(" ", "");
//        String kodeBandara_kedatangan=  bandara_kedatangan.split("\\(")[1].replace(" ", "");

        Log.i("KODEBANDARA_BERANGKAT",kodeBandara_berangkat );
        Log.i("KODEBANDARA_TUJUAN",kodeBandara_kedatangan );

        if (kodeBandara_berangkat.matches("SUB") && kodeBandara_kedatangan.matches("HLP")){
            binding.tidakAdaPenerbangan.setVisibility(View.GONE);

            //Data dummy 1
            tanggalBerangkat_inner = new ArrayList<>();
            tanggalBerangkat_inner.add(tanggalPulang);
            tanggalBerangkat_outer.add(tanggalBerangkat_inner);

            terminalBerangkat_inner = new ArrayList<>();
            terminalBerangkat_inner.add("Terminal 2");
            terminalBerangkat_outer.add(terminalBerangkat_inner);

            terminalDatang_inner = new ArrayList<>();
            terminalDatang_inner.add("Terminal 1");
            terminalDatang_outer.add(terminalDatang_inner);

            waktuBerangkat_inner = new ArrayList<>();
            waktuBerangkat_inner.add("6:30");
            waktuBerangkat_outer.add(waktuBerangkat_inner);
//            waktuBerangkat_inner.clear(); //ngga di clear dulu karena mau calculate flight duration

            bandaraAsal_inner = new ArrayList<>();
            bandaraAsal_inner.add("Juanda International Airport (SUB)");
            bandaraAsal_outer.add(bandaraAsal_inner);

            logoMaskapai_inner = new ArrayList<>();
            logoMaskapai_inner.add(R.drawable.ic_citilink);
            logoMaskapai_outer.add(logoMaskapai_inner);

            namaMaskapai_inner = new ArrayList<>();
            namaMaskapai_inner.add("Citilink");
            namaMaskapai_outer.add(namaMaskapai_inner);

            kodePenerbangan_inner = new ArrayList<>();
            kodePenerbangan_inner.add("IQC107");
            kodePenerbangan_outer.add(kodePenerbangan_inner);


            kabin_inner = new ArrayList<>();
            kabin_inner.add("7kg");
            kabin_outer.add(kabin_inner);


            bagasi_inner = new ArrayList<>();
            bagasi_inner.add("20kg");
            bagasi_outer.add(bagasi_inner);


            booleanMakan_inner = new ArrayList<>();
            booleanMakan_inner.add(1);
            booleanMakan_outer.add(booleanMakan_inner);


            keteranganMakan_inner = new ArrayList<>();
            keteranganMakan_inner.add("Makanan ringan 1x");
            keteranganMakan_outer.add(keteranganMakan_inner);


            modelPesawat_inner = new ArrayList<>();
            modelPesawat_inner.add("Airbus");
            modelPesawat_outer.add(modelPesawat_inner);


            tanggalDatang_inner = new ArrayList<>();
            tanggalDatang_inner.add(tanggalPulang);
            tanggalDatang_outer.add(tanggalDatang_inner);


            waktuDatang_inner = new ArrayList<>();
            waktuDatang_inner.add("9:40");
            waktuDatang_outer.add(waktuDatang_inner);
//            waktuDatang_inner.clear(); //ngga di clear dulu karena mau calculate flight duration

            calculateDuration(waktuBerangkat_inner, waktuDatang_inner);             //ini sudah memasukkan ke inner dan outer


            bandaraTujuan_inner = new ArrayList<>();
            bandaraTujuan_inner.add("Halim Perdana Kusuma International Airport (HLP)");
            bandaraTujuan_outer.add(bandaraTujuan_inner);



            testCovid.add(true);

            hargasatuan_dewasa_anak = 550000;
            hargasatuan_balita = 420000;
            harga_dewasa.add("IDR " + String.format("%,d", hargasatuan_dewasa_anak*jumlPenumpang_dewasa_anak ).replace(',', '.'));
            harga_balita.add("IDR " +  String.format("%,d", hargasatuan_balita*juml_balita ).replace(',', '.'));
            hargapax = String.format("%,d", (hargasatuan_dewasa_anak * jumlPenumpang_dewasa_anak) + (hargasatuan_balita*juml_balita)).replace(',', '.');
            harga.add("IDR " + hargapax);

//-----------------------------------

            //Data dummy 2

            tanggalBerangkat_inner= new ArrayList<>();
            tanggalBerangkat_inner.add(tanggalPulang);
            tanggalBerangkat_outer.add(tanggalBerangkat_inner);

            terminalBerangkat_inner = new ArrayList<>();
            terminalBerangkat_inner.add("Terminal 2");
            terminalBerangkat_outer.add(terminalBerangkat_inner);

            terminalDatang_inner = new ArrayList<>();
            terminalDatang_inner.add("Terminal 1");
            terminalDatang_outer.add(terminalDatang_inner);

            waktuBerangkat_inner = new ArrayList<>();
            waktuBerangkat_inner.add("7:30");
            waktuBerangkat_outer.add(waktuBerangkat_inner);
//            waktuBerangkat_inner.clear(); //ngga di clear dulu karena mau calculate flight duration

            bandaraAsal_inner = new ArrayList<>();
            bandaraAsal_inner.add("Juanda International Airport (SUB)");
            bandaraAsal_outer.add(bandaraAsal_inner);

            logoMaskapai_inner = new ArrayList<>();
            logoMaskapai_inner.add(R.drawable.ic_lionair);
            logoMaskapai_outer.add(logoMaskapai_inner);
//            logoMaskapai_inner.clear();

            namaMaskapai_inner= new ArrayList<>();
            namaMaskapai_inner.add("Lion Air");
            namaMaskapai_outer.add(namaMaskapai_inner);

            kodePenerbangan_inner= new ArrayList<>();
            kodePenerbangan_inner.add("IQC108");
            kodePenerbangan_outer.add(kodePenerbangan_inner);

            kabin_inner= new ArrayList<>();
            kabin_inner.add("7kg");
            kabin_outer.add(kabin_inner);

            bagasi_inner= new ArrayList<>();
            bagasi_inner.add("25kg");
            bagasi_outer.add(bagasi_inner);

            booleanMakan_inner= new ArrayList<>();
            booleanMakan_inner.add(0);
            booleanMakan_outer.add(booleanMakan_inner);

            keteranganMakan_inner= new ArrayList<>();
            keteranganMakan_inner.add("Tidak termasuk makan");
            keteranganMakan_outer.add(keteranganMakan_inner);

            modelPesawat_inner= new ArrayList<>();
            modelPesawat_inner.add("Boeing 737");
            modelPesawat_outer.add(modelPesawat_inner);

            tanggalDatang_inner= new ArrayList<>();
            tanggalDatang_inner.add(tanggalPulang);
            tanggalDatang_outer.add(tanggalDatang_inner);

            waktuDatang_inner = new ArrayList<>();
            waktuDatang_inner.add("10:20");
            waktuDatang_outer.add(waktuDatang_inner);
//            waktuDatang_inner.clear(); //ngga di clear dulu karena mau calculate flight duration

            calculateDuration(waktuBerangkat_inner, waktuDatang_inner);             //ini sudah memasukkan ke inner dan outer

            bandaraTujuan_inner = new ArrayList<>();
            bandaraTujuan_inner.add("Halim Perdana Kusuma International Airport (HLP)");
            bandaraTujuan_outer.add(bandaraTujuan_inner);

            testCovid.add(true);

            hargasatuan_dewasa_anak = 450000;
            hargasatuan_balita = 320000;
            harga_dewasa.add("IDR " + String.format("%,d", hargasatuan_dewasa_anak*jumlPenumpang_dewasa_anak ).replace(',', '.'));
            harga_balita.add("IDR " +  String.format("%,d", hargasatuan_balita*juml_balita ).replace(',', '.'));
            hargapax = String.format("%,d", (hargasatuan_dewasa_anak * jumlPenumpang_dewasa_anak) + (hargasatuan_balita*juml_balita)).replace(',', '.');
            harga.add("IDR " + hargapax);
            Log.i("POPULATE DONE", "INSIDE");




        }

        if (kodeBandara_berangkat.matches("HLP") && kodeBandara_kedatangan.matches("SUB")){
            binding.tidakAdaPenerbangan.setVisibility(View.GONE);


            //Data dummy 1
            tanggalBerangkat_inner = new ArrayList<>();
            tanggalBerangkat_inner.add(tanggalPulang);
            tanggalBerangkat_outer.add(tanggalBerangkat_inner);

            terminalBerangkat_inner = new ArrayList<>();
            terminalBerangkat_inner.add("Terminal 2");
            terminalBerangkat_outer.add(terminalBerangkat_inner);

            terminalDatang_inner = new ArrayList<>();
            terminalDatang_inner.add("Terminal 1");
            terminalDatang_outer.add(terminalDatang_inner);

            waktuBerangkat_inner = new ArrayList<>();
            waktuBerangkat_inner.add("6:30");
            waktuBerangkat_outer.add(waktuBerangkat_inner);
//            waktuBerangkat_inner.clear(); //ngga di clear dulu karena mau calculate flight duration

            bandaraAsal_inner = new ArrayList<>();
            bandaraAsal_inner.add("Halim Perdana Kusuma International Airport (HLP) ");
            bandaraAsal_outer.add(bandaraAsal_inner);

            logoMaskapai_inner = new ArrayList<>();
            logoMaskapai_inner.add(R.drawable.ic_citilink);
            logoMaskapai_outer.add(logoMaskapai_inner);

            namaMaskapai_inner = new ArrayList<>();
            namaMaskapai_inner.add("Citilink");
            namaMaskapai_outer.add(namaMaskapai_inner);

            kodePenerbangan_inner = new ArrayList<>();
            kodePenerbangan_inner.add("IQC107");
            kodePenerbangan_outer.add(kodePenerbangan_inner);


            kabin_inner = new ArrayList<>();
            kabin_inner.add("7kg");
            kabin_outer.add(kabin_inner);


            bagasi_inner = new ArrayList<>();
            bagasi_inner.add("20kg");
            bagasi_outer.add(bagasi_inner);


            booleanMakan_inner = new ArrayList<>();
            booleanMakan_inner.add(0);
            booleanMakan_outer.add(booleanMakan_inner);


            keteranganMakan_inner = new ArrayList<>();
            keteranganMakan_inner.add("Tidak termasuk makan");
            keteranganMakan_outer.add(keteranganMakan_inner);


            modelPesawat_inner = new ArrayList<>();
            modelPesawat_inner.add("Airbus");
            modelPesawat_outer.add(modelPesawat_inner);


            tanggalDatang_inner = new ArrayList<>();
            tanggalDatang_inner.add(tanggalPulang);
            tanggalDatang_outer.add(tanggalDatang_inner);


            waktuDatang_inner = new ArrayList<>();
            waktuDatang_inner.add("9:40");
            waktuDatang_outer.add(waktuDatang_inner);
//            waktuDatang_inner.clear(); //ngga di clear dulu karena mau calculate flight duration

            calculateDuration(waktuBerangkat_inner, waktuDatang_inner);             //ini sudah memasukkan ke inner dan outer


            bandaraTujuan_inner = new ArrayList<>();
            bandaraTujuan_inner.add("Juanda International Airport (SUB)");
            bandaraTujuan_outer.add(bandaraTujuan_inner);



            testCovid.add(true);

            hargasatuan_dewasa_anak = 550000;
            hargasatuan_balita = 420000;
            harga_dewasa.add("IDR " + String.format("%,d", hargasatuan_dewasa_anak*jumlPenumpang_dewasa_anak ).replace(',', '.'));
            harga_balita.add("IDR " +  String.format("%,d", hargasatuan_balita*juml_balita ).replace(',', '.'));
            hargapax = String.format("%,d", (hargasatuan_dewasa_anak * jumlPenumpang_dewasa_anak) + (hargasatuan_balita*juml_balita)).replace(',', '.');
            harga.add("IDR " + hargapax);

//-----------------------------------

            //Data dummy 2

            tanggalBerangkat_inner= new ArrayList<>();
            tanggalBerangkat_inner.add(tanggalPulang);
            tanggalBerangkat_outer.add(tanggalBerangkat_inner);

            terminalBerangkat_inner = new ArrayList<>();
            terminalBerangkat_inner.add("Terminal 2");
            terminalBerangkat_outer.add(terminalBerangkat_inner);

            terminalDatang_inner = new ArrayList<>();
            terminalDatang_inner.add("Terminal 1");
            terminalDatang_outer.add(terminalDatang_inner);

            waktuBerangkat_inner = new ArrayList<>();
            waktuBerangkat_inner.add("11:30");
            waktuBerangkat_outer.add(waktuBerangkat_inner);
//            waktuBerangkat_inner.clear(); //ngga di clear dulu karena mau calculate flight duration

            bandaraAsal_inner = new ArrayList<>();
            bandaraAsal_inner.add("Halim Perdana Kusuma International Airport (HLP)");
            bandaraAsal_outer.add(bandaraAsal_inner);

            logoMaskapai_inner = new ArrayList<>();
            logoMaskapai_inner.add(R.drawable.ic_lionair);
            logoMaskapai_outer.add(logoMaskapai_inner);
//            logoMaskapai_inner.clear();

            namaMaskapai_inner= new ArrayList<>();
            namaMaskapai_inner.add("Lion Air");
            namaMaskapai_outer.add(namaMaskapai_inner);

            kodePenerbangan_inner= new ArrayList<>();
            kodePenerbangan_inner.add("IQC108");
            kodePenerbangan_outer.add(kodePenerbangan_inner);

            kabin_inner= new ArrayList<>();
            kabin_inner.add("7kg");
            kabin_outer.add(kabin_inner);

            bagasi_inner= new ArrayList<>();
            bagasi_inner.add("25kg");
            bagasi_outer.add(bagasi_inner);

            booleanMakan_inner= new ArrayList<>();
            booleanMakan_inner.add(0);
            booleanMakan_outer.add(booleanMakan_inner);

            keteranganMakan_inner= new ArrayList<>();
            keteranganMakan_inner.add("Tidak termasuk makan");
            keteranganMakan_outer.add(keteranganMakan_inner);

            modelPesawat_inner= new ArrayList<>();
            modelPesawat_inner.add("Boeing 737");
            modelPesawat_outer.add(modelPesawat_inner);

            tanggalDatang_inner= new ArrayList<>();
            tanggalDatang_inner.add(tanggalPulang);
            tanggalDatang_outer.add(tanggalDatang_inner);

            waktuDatang_inner = new ArrayList<>();
            waktuDatang_inner.add("13:40");
            waktuDatang_outer.add(waktuDatang_inner);
//            waktuDatang_inner.clear(); //ngga di clear dulu karena mau calculate flight duration

            calculateDuration(waktuBerangkat_inner, waktuDatang_inner);             //ini sudah memasukkan ke inner dan outer

            bandaraTujuan_inner = new ArrayList<>();
            bandaraTujuan_inner.add("Juanda International Airport (SUB)");
            bandaraTujuan_outer.add(bandaraTujuan_inner);

            testCovid.add(true);

            hargasatuan_dewasa_anak = 450000;
            hargasatuan_balita = 320000;
            harga_dewasa.add("IDR " + String.format("%,d", hargasatuan_dewasa_anak*jumlPenumpang_dewasa_anak ).replace(',', '.'));
            harga_balita.add("IDR " +  String.format("%,d", hargasatuan_balita*juml_balita ).replace(',', '.'));
            hargapax = String.format("%,d", (hargasatuan_dewasa_anak * jumlPenumpang_dewasa_anak) + (hargasatuan_balita*juml_balita)).replace(',', '.');
            harga.add("IDR " + hargapax);
            Log.i("POPULATE DONE", "INSIDE");


        }

        if (kodeBandara_berangkat.matches("CGK") && kodeBandara_kedatangan.matches("AMS")){
            binding.tidakAdaPenerbangan.setVisibility(View.GONE);

            //Data dummy 1
            tanggalBerangkat_inner= new ArrayList<>();
            tanggalBerangkat_inner.add(tanggalPulang);
            tanggalBerangkat_inner.add(tanggalPulang);
            tanggalBerangkat_outer.add(tanggalBerangkat_inner);

            terminalBerangkat_inner = new ArrayList<>();
            terminalBerangkat_inner.add("Terminal 2");
            terminalBerangkat_inner.add("Terminal 3");
            terminalBerangkat_outer.add(terminalBerangkat_inner);

            terminalDatang_inner = new ArrayList<>();
            terminalDatang_inner.add("Terminal 1");
            terminalDatang_inner.add("Terminal 2");
            terminalDatang_outer.add(terminalDatang_inner);


            waktuBerangkat_inner = new ArrayList<>();
            waktuBerangkat_inner.add("12:30");
            waktuBerangkat_inner.add("13:30");
            waktuBerangkat_outer.add(waktuBerangkat_inner);
//            waktuBerangkat_inner.clear(); //ngga di clear dulu karena mau calculate flight duration

            bandaraAsal_inner = new ArrayList<>();
            bandaraAsal_inner.add("Soekarno Hatta International Airport (CGK)");
            bandaraAsal_inner.add("Doha International Airport (DIA)");
            bandaraAsal_outer.add(bandaraAsal_inner);

            logoMaskapai_inner = new ArrayList<>();
            logoMaskapai_inner.add(R.drawable.ic_citilink);
            logoMaskapai_inner.add(R.drawable.ic_citilink);
            logoMaskapai_outer.add(logoMaskapai_inner);
//            logoMaskapai_inner.clear();

            namaMaskapai_inner= new ArrayList<>();
            namaMaskapai_inner.add("Citilink");
            namaMaskapai_inner.add("Citilink");
            namaMaskapai_outer.add(namaMaskapai_inner);

            kodePenerbangan_inner= new ArrayList<>();
            kodePenerbangan_inner.add("IQC119");
            kodePenerbangan_inner.add("IQC188");
            kodePenerbangan_outer.add(kodePenerbangan_inner);

            kabin_inner= new ArrayList<>();
            kabin_inner.add("7kg");
            kabin_inner.add("7kg");
            kabin_outer.add(kabin_inner);

            bagasi_inner= new ArrayList<>();
            bagasi_inner.add("25kg");
            bagasi_inner.add("20kg");
            bagasi_outer.add(bagasi_inner);

            booleanMakan_inner= new ArrayList<>();
            booleanMakan_inner.add(1);
            booleanMakan_inner.add(0);
            booleanMakan_outer.add(booleanMakan_inner);

            keteranganMakan_inner= new ArrayList<>();
            keteranganMakan_inner.add("2x Makanan berat, 1x Makanan ringan");
            keteranganMakan_inner.add("Penerbangan ini tidak termasuk makan");
            keteranganMakan_outer.add(keteranganMakan_inner);

            modelPesawat_inner= new ArrayList<>();
            modelPesawat_inner.add("Airbus Neo");
            modelPesawat_inner.add("Airbus Neo 2");
            modelPesawat_outer.add(modelPesawat_inner);

            tanggalDatang_inner= new ArrayList<>();
            tanggalDatang_inner.add(tanggalPulang);
            tanggalDatang_inner.add(tanggalPulang);
            tanggalDatang_outer.add(tanggalDatang_inner);

            waktuDatang_inner = new ArrayList<>();
            waktuDatang_inner.add("18:30");
            waktuDatang_inner.add("08:25");
            waktuDatang_outer.add(waktuDatang_inner);
//            waktuDatang_inner.clear(); //ngga di clear dulu karena mau calculate flight duration

            calculateDuration(waktuBerangkat_inner, waktuDatang_inner);             //ini sudah memasukkan ke inner dan outer

            bandaraTujuan_inner = new ArrayList<>();
            bandaraTujuan_inner.add("Doha International Airport (DIA)");
            bandaraTujuan_inner.add("Amsterdam Airport Schiphol (AMS)");
            bandaraTujuan_outer.add(bandaraTujuan_inner);

            testCovid.add(true);

            hargasatuan_dewasa_anak = 2950000;
            hargasatuan_balita = 2825000;
            harga_dewasa.add("IDR " + String.format("%,d", hargasatuan_dewasa_anak*jumlPenumpang_dewasa_anak ).replace(',', '.'));
            harga_balita.add("IDR " +  String.format("%,d", hargasatuan_balita*juml_balita ).replace(',', '.'));
            hargapax = String.format("%,d", (hargasatuan_dewasa_anak * jumlPenumpang_dewasa_anak) + (hargasatuan_balita*juml_balita)).replace(',', '.');
            harga.add("IDR " + hargapax);
            Log.i("POPULATE DONE", "INSIDE");

//-----------------------------------

            //Data dummy 2

            tanggalBerangkat_inner= new ArrayList<>();
            tanggalBerangkat_inner.add(tanggalPulang);
            tanggalBerangkat_inner.add(tanggalPulang);
            tanggalBerangkat_outer.add(tanggalBerangkat_inner);

            terminalBerangkat_inner = new ArrayList<>();
            terminalBerangkat_inner.add("Terminal 2");
            terminalBerangkat_inner.add("Terminal 3");
            terminalBerangkat_outer.add(terminalBerangkat_inner);

            terminalDatang_inner = new ArrayList<>();
            terminalDatang_inner.add("Terminal 1");
            terminalDatang_inner.add("Terminal 2");
            terminalDatang_outer.add(terminalDatang_inner);

            waktuBerangkat_inner = new ArrayList<>();
            waktuBerangkat_inner.add("11:30");
            waktuBerangkat_inner.add("19:30");
            waktuBerangkat_outer.add(waktuBerangkat_inner);
//            waktuBerangkat_inner.clear(); //ngga di clear dulu karena mau calculate flight duration

            bandaraAsal_inner = new ArrayList<>();
            bandaraAsal_inner.add("Soekarno Hatta International Airport (CGK)");
            bandaraAsal_inner.add("Doha International Airport (DIA)");
            bandaraAsal_outer.add(bandaraAsal_inner);

            logoMaskapai_inner = new ArrayList<>();
            logoMaskapai_inner.add(R.drawable.ic_lionair);
            logoMaskapai_inner.add(R.drawable.ic_lionair);
            logoMaskapai_outer.add(logoMaskapai_inner);
//            logoMaskapai_inner.clear();

            namaMaskapai_inner= new ArrayList<>();
            namaMaskapai_inner.add("Lion Air");
            namaMaskapai_inner.add("Lion Air");
            namaMaskapai_outer.add(namaMaskapai_inner);

            kodePenerbangan_inner= new ArrayList<>();
            kodePenerbangan_inner.add("IQC108");
            kodePenerbangan_inner.add("IQC118");
            kodePenerbangan_outer.add(kodePenerbangan_inner);

            kabin_inner= new ArrayList<>();
            kabin_inner.add("7kg");
            kabin_inner.add("7kg");
            kabin_outer.add(kabin_inner);

            bagasi_inner= new ArrayList<>();
            bagasi_inner.add("25kg");
            bagasi_inner.add("20kg");
            bagasi_outer.add(bagasi_inner);

            booleanMakan_inner= new ArrayList<>();
            booleanMakan_inner.add(1);
            booleanMakan_inner.add(0);
            booleanMakan_outer.add(booleanMakan_inner);

            keteranganMakan_inner= new ArrayList<>();
            keteranganMakan_inner.add("2x Makanan berat, 1x Makanan ringan");
            keteranganMakan_inner.add("Penerbangan ini tidak termasuk makan");
            keteranganMakan_outer.add(keteranganMakan_inner);

            modelPesawat_inner= new ArrayList<>();
            modelPesawat_inner.add("Boeing 737");
            modelPesawat_inner.add("Boeing 737 Max");
            modelPesawat_outer.add(modelPesawat_inner);

            tanggalDatang_inner= new ArrayList<>();
            tanggalDatang_inner.add(tanggalPulang);
            tanggalDatang_inner.add(tanggalPulang);
            tanggalDatang_outer.add(tanggalDatang_inner);

            waktuDatang_inner = new ArrayList<>();
            waktuDatang_inner.add("17:30");
            waktuDatang_inner.add("07:25");
            waktuDatang_outer.add(waktuDatang_inner);
//            waktuDatang_inner.clear(); //ngga di clear dulu karena mau calculate flight duration

            calculateDuration(waktuBerangkat_inner, waktuDatang_inner);             //ini sudah memasukkan ke inner dan outer

            bandaraTujuan_inner = new ArrayList<>();
            bandaraTujuan_inner.add("Doha International Airport (DIA)");
            bandaraTujuan_inner.add("Amsterdam Airport Schiphol (AMS)");
            bandaraTujuan_outer.add(bandaraTujuan_inner);

            testCovid.add(true);

            hargasatuan_dewasa_anak = 2750000;
            hargasatuan_balita = 2320000;
            harga_dewasa.add("IDR " + String.format("%,d", hargasatuan_dewasa_anak*jumlPenumpang_dewasa_anak ).replace(',', '.'));
            harga_balita.add("IDR " +  String.format("%,d", hargasatuan_balita*juml_balita ).replace(',', '.'));
            hargapax = String.format("%,d", (hargasatuan_dewasa_anak * jumlPenumpang_dewasa_anak) + (hargasatuan_balita*juml_balita)).replace(',', '.');
            harga.add("IDR " + hargapax);
            Log.i("POPULATE DONE", "INSIDE");


        }

        if (kodeBandara_berangkat.matches("AMS") && kodeBandara_kedatangan.matches("CGK")){
            binding.tidakAdaPenerbangan.setVisibility(View.GONE);

            //Data dummy 1
            tanggalBerangkat_inner= new ArrayList<>();
            tanggalBerangkat_inner.add(tanggalPulang);
            tanggalBerangkat_inner.add(tanggalPulang);
            tanggalBerangkat_outer.add(tanggalBerangkat_inner);

            terminalBerangkat_inner = new ArrayList<>();
            terminalBerangkat_inner.add("Terminal 2");
            terminalBerangkat_inner.add("Terminal 3");
            terminalBerangkat_outer.add(terminalBerangkat_inner);

            terminalDatang_inner = new ArrayList<>();
            terminalDatang_inner.add("Terminal 1");
            terminalDatang_inner.add("Terminal 2");
            terminalDatang_outer.add(terminalDatang_inner);

            waktuBerangkat_inner = new ArrayList<>();
            waktuBerangkat_inner.add("22:30");
            waktuBerangkat_inner.add("23:30");
            waktuBerangkat_outer.add(waktuBerangkat_inner);
//            waktuBerangkat_inner.clear(); //ngga di clear dulu karena mau calculate flight duration

            bandaraAsal_inner = new ArrayList<>();
            bandaraAsal_inner.add("Amsterdam Airport Schiphol (AMS)");
            bandaraAsal_inner.add("Doha International Airport (DIA)");
            bandaraAsal_outer.add(bandaraAsal_inner);

            logoMaskapai_inner = new ArrayList<>();
            logoMaskapai_inner.add(R.drawable.ic_citilink);
            logoMaskapai_inner.add(R.drawable.ic_citilink);
            logoMaskapai_outer.add(logoMaskapai_inner);
//            logoMaskapai_inner.clear();

            namaMaskapai_inner= new ArrayList<>();
            namaMaskapai_inner.add("Citilink");
            namaMaskapai_inner.add("Citilink");
            namaMaskapai_outer.add(namaMaskapai_inner);

            kodePenerbangan_inner= new ArrayList<>();
            kodePenerbangan_inner.add("IQC119");
            kodePenerbangan_inner.add("IQC188");
            kodePenerbangan_outer.add(kodePenerbangan_inner);

            kabin_inner= new ArrayList<>();
            kabin_inner.add("7kg");
            kabin_inner.add("7kg");
            kabin_outer.add(kabin_inner);

            bagasi_inner= new ArrayList<>();
            bagasi_inner.add("25kg");
            bagasi_inner.add("20kg");
            bagasi_outer.add(bagasi_inner);

            booleanMakan_inner= new ArrayList<>();
            booleanMakan_inner.add(1);
            booleanMakan_inner.add(0);
            booleanMakan_outer.add(booleanMakan_inner);

            keteranganMakan_inner= new ArrayList<>();
            keteranganMakan_inner.add("2x Makanan berat, 1x Makanan ringan");
            keteranganMakan_inner.add("Penerbangan ini tidak termasuk makan");
            keteranganMakan_outer.add(keteranganMakan_inner);

            modelPesawat_inner= new ArrayList<>();
            modelPesawat_inner.add("Airbus Neo");
            modelPesawat_inner.add("Airbus Neo 2");
            modelPesawat_outer.add(modelPesawat_inner);

            tanggalDatang_inner= new ArrayList<>();
            tanggalDatang_inner.add(tanggalPulang);
            tanggalDatang_inner.add(tanggalPulang);
            tanggalDatang_outer.add(tanggalDatang_inner);

            waktuDatang_inner = new ArrayList<>();
            waktuDatang_inner.add("09:50");
            waktuDatang_inner.add("10:25");
            waktuDatang_outer.add(waktuDatang_inner);
//            waktuDatang_inner.clear(); //ngga di clear dulu karena mau calculate flight duration

            calculateDuration(waktuBerangkat_inner, waktuDatang_inner);             //ini sudah memasukkan ke inner dan outer

            bandaraTujuan_inner = new ArrayList<>();
            bandaraTujuan_inner.add("Doha International Airport (DIA)");
            bandaraTujuan_inner.add("Soekarno Hatta International Airport (CGK)");
            bandaraTujuan_outer.add(bandaraTujuan_inner);

            testCovid.add(true);

            hargasatuan_dewasa_anak = 2650000;
            hargasatuan_balita = 2525000;
            harga_dewasa.add("IDR " + String.format("%,d", hargasatuan_dewasa_anak*jumlPenumpang_dewasa_anak ).replace(',', '.'));
            harga_balita.add("IDR " +  String.format("%,d", hargasatuan_balita*juml_balita ).replace(',', '.'));
            hargapax = String.format("%,d", (hargasatuan_dewasa_anak * jumlPenumpang_dewasa_anak) + (hargasatuan_balita*juml_balita)).replace(',', '.');
            harga.add("IDR " + hargapax);
            Log.i("POPULATE DONE", "INSIDE");

//-----------------------------------

            //Data dummy 2

            //Data dummy 1
            tanggalBerangkat_inner= new ArrayList<>();
            tanggalBerangkat_inner.add(tanggalPulang);
            tanggalBerangkat_inner.add(tanggalPulang);
            tanggalBerangkat_outer.add(tanggalBerangkat_inner);

            terminalBerangkat_inner = new ArrayList<>();
            terminalBerangkat_inner.add("Terminal 2");
            terminalBerangkat_inner.add("Terminal 3");
            terminalBerangkat_outer.add(terminalBerangkat_inner);

            terminalDatang_inner = new ArrayList<>();
            terminalDatang_inner.add("Terminal 1");
            terminalDatang_inner.add("Terminal 2");
            terminalDatang_outer.add(terminalDatang_inner);

            waktuBerangkat_inner = new ArrayList<>();
            waktuBerangkat_inner.add("22:30");
            waktuBerangkat_inner.add("23:30");
            waktuBerangkat_outer.add(waktuBerangkat_inner);
//            waktuBerangkat_inner.clear(); //ngga di clear dulu karena mau calculate flight duration

            bandaraAsal_inner = new ArrayList<>();
            bandaraAsal_inner.add("Amsterdam Airport Schiphol (AMS)");
            bandaraAsal_inner.add("Doha International Airport (DIA)");
            bandaraAsal_outer.add(bandaraAsal_inner);

            logoMaskapai_inner = new ArrayList<>();
            logoMaskapai_inner.add(R.drawable.ic_lionair);
            logoMaskapai_inner.add(R.drawable.ic_lionair);
            logoMaskapai_outer.add(logoMaskapai_inner);
//            logoMaskapai_inner.clear();

            namaMaskapai_inner= new ArrayList<>();
            namaMaskapai_inner.add("Lion Air");
            namaMaskapai_inner.add("Lion Air");
            namaMaskapai_outer.add(namaMaskapai_inner);

            kodePenerbangan_inner= new ArrayList<>();
            kodePenerbangan_inner.add("IQC119");
            kodePenerbangan_inner.add("IQC188");
            kodePenerbangan_outer.add(kodePenerbangan_inner);

            kabin_inner= new ArrayList<>();
            kabin_inner.add("7kg");
            kabin_inner.add("7kg");
            kabin_outer.add(kabin_inner);

            bagasi_inner= new ArrayList<>();
            bagasi_inner.add("25kg");
            bagasi_inner.add("20kg");
            bagasi_outer.add(bagasi_inner);

            booleanMakan_inner= new ArrayList<>();
            booleanMakan_inner.add(1);
            booleanMakan_inner.add(0);
            booleanMakan_outer.add(booleanMakan_inner);

            keteranganMakan_inner= new ArrayList<>();
            keteranganMakan_inner.add("2x Makanan berat, 1x Makanan ringan");
            keteranganMakan_inner.add("Penerbangan ini tidak termasuk makan");
            keteranganMakan_outer.add(keteranganMakan_inner);

            modelPesawat_inner= new ArrayList<>();
            modelPesawat_inner.add("Airbus Neo");
            modelPesawat_inner.add("Airbus Neo 2");
            modelPesawat_outer.add(modelPesawat_inner);

            tanggalDatang_inner= new ArrayList<>();
            tanggalDatang_inner.add(tanggalPulang);
            tanggalDatang_inner.add(tanggalPulang);
            tanggalDatang_outer.add(tanggalDatang_inner);

            waktuDatang_inner = new ArrayList<>();
            waktuDatang_inner.add("08:30");
            waktuDatang_inner.add("09:25");
            waktuDatang_outer.add(waktuDatang_inner);
//            waktuDatang_inner.clear(); //ngga di clear dulu karena mau calculate flight duration

            calculateDuration(waktuBerangkat_inner, waktuDatang_inner);             //ini sudah memasukkan ke inner dan outer

            bandaraTujuan_inner = new ArrayList<>();
            bandaraTujuan_inner.add("Doha International Airport (DIA)");
            bandaraTujuan_inner.add("Soekarno Hatta International Airport (CGK)");
            bandaraTujuan_outer.add(bandaraTujuan_inner);

            testCovid.add(true);

            hargasatuan_dewasa_anak = 2950000;
            hargasatuan_balita = 2825000;
            harga_dewasa.add("IDR " + String.format("%,d", hargasatuan_dewasa_anak*jumlPenumpang_dewasa_anak ).replace(',', '.'));
            harga_balita.add("IDR " +  String.format("%,d", hargasatuan_balita*juml_balita ).replace(',', '.'));
            hargapax = String.format("%,d", (hargasatuan_dewasa_anak * jumlPenumpang_dewasa_anak) + (hargasatuan_balita*juml_balita)).replace(',', '.');
            harga.add("IDR " + hargapax);
            Log.i("POPULATE DONE", "INSIDE");        }

        Log.i("POPULATE DONE", "OUTSIDE");
        Log.i("POPULATE HARGA", harga.toString());
        RecyclerAdapterPlaneTicket_v2 recyclerAdapterPlaneTicket_v2 = new RecyclerAdapterPlaneTicket_v2(kodePenerbangan_outer, logoMaskapai_outer,  namaMaskapai_outer,  waktuBerangkat_outer,  bandaraAsal_outer,  kabin_outer,  bagasi_outer, booleanMakan_outer, keteranganMakan_outer,  modelPesawat_outer, durasi_outer,  waktuDatang_outer, bandaraTujuan_outer,  tanggalBerangkat_outer, tanggalDatang_outer, testCovid,  harga,  jumlahpax);
        binding.RecycleViewTicket.setAdapter(recyclerAdapterPlaneTicket_v2);




    }

    public void  calculateDuration(ArrayList<String> waktuBerangkat_inner, ArrayList<String> waktuDatang_inner) {
        for (int i = 0; i < waktuDatang_inner.size(); i++) {
            int jam_berangkat = Integer.parseInt(waktuBerangkat_inner.get(i).split(":")[0]);
            int menit_berangkat = Integer.parseInt(waktuBerangkat_inner.get(i).split(":")[1]);
            int jam_datang = Integer.parseInt(waktuDatang_inner.get(i).split(":")[0]);
            int menit_datang = Integer.parseInt(waktuDatang_inner.get(i).split(":")[1]);

            int durasi_menit = menit_datang - menit_berangkat;
            int durasi_jam = jam_datang - jam_berangkat;
            if (durasi_menit < 0) {
                durasi_menit = 60 + durasi_menit;
                durasi_jam = durasi_jam - 1;
            }
            if (durasi_jam < 0) {
                durasi_jam = 24 + durasi_jam;
            }

            durasi_inner = new ArrayList<>();
            durasi_inner.add(durasi_jam + "j " + durasi_menit + "m");

        }
        durasi_outer.add(durasi_inner);
    }
}