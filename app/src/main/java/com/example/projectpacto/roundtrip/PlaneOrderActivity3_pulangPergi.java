package com.example.projectpacto.roundtrip;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectpacto.BookingActivity;
import com.example.projectpacto.DataPenumpang;
import com.example.projectpacto.PlaneOrderActivity2;
import com.example.projectpacto.R;
import com.example.projectpacto.RecyclerAdapterBagasi;
import com.example.projectpacto.RecyclerAdapterPenumpangList;
import com.example.projectpacto.TambahanBagasiBottomSheet;
import com.example.projectpacto.TrankasiActivity;
import com.example.projectpacto.databinding.ActivityPlaneOrder3Binding;
import com.example.projectpacto.databinding.ActivityPlaneOrder3PpBinding;
import com.example.projectpacto.databinding.ActivityPlaneOrderActivity3PulangPergiBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PlaneOrderActivity3_pulangPergi extends AppCompatActivity implements bagasi_bottomsheet_redesign.OnDataBagasiBottomSheetRedesign, DataPenumpang.OnDataPassenger, RecyclerAdapterPenumpangList.AddPassengerDetail, TambahanBagasiBottomSheet.OnDataBagasi {
    Bundle bundle;

    ActivityPlaneOrderActivity3PulangPergiBinding binding;


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
    int jmlPenumpang;

    String keberangkatan;
    String kedatangan;
    String tanggal;
    String penumpang;
    String bagasi_default;
    String kota_kedatangan;
    String kota_keberangkatan;
    String bandara_keberangktan_raw;
    String bandara_kedatangan_raw;
    String rincianPenumpang;

    String tanggalBerangkat_str_pulang;
    String waktuBerangkat_str_pulang;
    String bandaraAsal_str_pulang;
    int logoMaskapai_int_pulang;
    String namaMaskapai_str_pulang;
    String kelasPesawat_str_pulang;
    String tanggalDatang_str_pulang;
    String waktuDatang_str_pulang;
    String bandaraTujuan_str_pulang;
    String jmlDewasa_str_pulang;
    String jmlAnak_str_pulang;
    String jmlBalita_str_pulang;
    String kotaTujuan_str_pulang;
    String kotaAsal_str_pulang;
    String harga_str_pulang;
    int jmlPenumpang_pulang;

    String keberangkatan_pulang;
    String kedatangan_pulang;
    String tanggal_pulang;
    String penumpang_pulang;
    String kota_kedatangan_pulang;
    String kota_keberangkatan_pulang;
    String bandara_keberangktan_raw_pulang;
    String bandara_kedatangan_raw_pulang;
    String rincianPenumpang_pulang;


    ArrayList<String> namaPassenger;
    ArrayList<String> tglLahir;
    ArrayList<String> titel;
    ArrayList<String> kewarganegaraan;
    ArrayList<String> NIKatauPaspor;

    ArrayList<String> nama_namaTersimpan;
    ArrayList<String> tglLahir_namaTersimpan;
    ArrayList<String> titel_namaTersimpan;
    ArrayList<String> kewarganegaraan_namaTersimpan;
    ArrayList<String> NIKatauPaspor_namaTersimpan;


    //Keperluan Firestore -- Pergi
    ArrayList<String> tanggalBerangkat_ArrayList;
    ArrayList<String> waktuBerangkat_ArrayList;
    ArrayList<String> bandaraAsal_ArrayList;
    ArrayList<Integer> logoMaskapai_ArrayList;
    ArrayList<String> namaMaskapai_ArrayList;
    ArrayList<String> kodePenerbangan_ArrayList;
    ArrayList<String> kelasPesawat_ArrayList;
    ArrayList<String> tanggalDatang_ArrayList;
    ArrayList<String> waktuDatang_ArrayList;
    ArrayList<String> bandaraTujuan_ArrayList;
    ArrayList<String> kabin_ArrayList;
    ArrayList<String> bagasi_ArrayList;
    ArrayList<Integer> booleanMakan_ArrayList;
    ArrayList<String> keteranganMakan_ArrayList;
    ArrayList<String> modelPesawat_ArrayList;
    ArrayList<String> durasi_ArrayList;
    ArrayList<String> terminalBerangkat;
    ArrayList<String> terminalDatang;
    String tanggalPulang;

    //Keperluang Firestore -- Pulang
    ArrayList<String> tanggalBerangkat_ArrayList_pulang;
    ArrayList<String> waktuBerangkat_ArrayList_pulang;
    ArrayList<String> bandaraAsal_ArrayList_pulang;
    ArrayList<Integer> logoMaskapai_ArrayList_pulang;
    ArrayList<String> namaMaskapai_ArrayList_pulang;
    ArrayList<String> kodePenerbangan_ArrayList_pulang;
    ArrayList<String> kelasPesawat_ArrayList_pulang;
    ArrayList<String> tanggalDatang_ArrayList_pulang;
    ArrayList<String> waktuDatang_ArrayList_pulang;
    ArrayList<String> bandaraTujuan_ArrayList_pulang;
    ArrayList<String> kabin_ArrayList_pulang;
    ArrayList<String> bagasi_ArrayList_pulang;
    ArrayList<Integer> booleanMakan_ArrayList_pulang;
    ArrayList<String> keteranganMakan_ArrayList_pulang;
    ArrayList<String> modelPesawat_ArrayList_pulang;
    ArrayList<String> durasi_ArrayList_pulang;
    ArrayList<String> terminalBerangkat_pulang;
    ArrayList<String> terminalDatang_pulang;
    String tanggalPulang_pulang;




    ArrayList<String> tambahan_kg;
    ArrayList<String> harga_tambahan;
    ArrayList<Integer> selected_position_opsi_bagasi;

    //Keperluan Rincian harga
    String harga_dewasa;
    String harga_dewasa_pulang;
    String harga_balita_pulang;
    String harga_balita;
    ArrayList<String> tambahan_kg_pulang;
    ArrayList<String> harga_tambahan_pulang;
    ArrayList<Integer> selected_position_opsi_bagasi_pulang;


    List<Map<String, String>> ArrayofPenumpangMaps;
    Map<String, String> dataPenumpangMap;

    FirebaseFirestore fs;


    RecyclerAdapterPenumpangList recyclerAdapterPenumpangList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaneOrderActivity3PulangPergiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        fs = FirebaseFirestore.getInstance();
        String userID = "5E8dHyQfzYeu1wBvwjxNr8EUl7J3";



        final int[] kredit = {0};
        final int[] limit = {0};

        fs.collection("user").document(userID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Map<String, Object> map = (Map<String, Object>) documentSnapshot.getData();
                kredit[0] = Integer.parseInt(map.get("kredit").toString());
                limit[0] = Integer.parseInt(map.get("limit").toString());

                if (kredit[0] < limit[0]) {
                    binding.kreditWarning.setVisibility(View.VISIBLE);
                    binding.pesanButton.setBackgroundTintList(getColorStateList(R.color.dark_grey));
                }

            }
        });


        //FETCH Bundle -- essential for this page
        bundle = getIntent().getBundleExtra("bundle");
        if (!bundle.isEmpty()) {
            //data pergi
            harga_str = bundle.getString("harga");
            kotaAsal_str = bundle.getString("kotaAsal");
            kotaTujuan_str = bundle.getString("kotaTujuan");
            tanggalBerangkat_str = bundle.getStringArrayList("tanggalBerangkat").get(0);
            waktuBerangkat_str = bundle.getStringArrayList("waktuBerangkat").get(0);
            bandaraAsal_str = bundle.getStringArrayList("bandaraAsal").get(0);
            logoMaskapai_int = bundle.getIntegerArrayList("logoMaskapai").get(0);
            namaMaskapai_str = bundle.getStringArrayList("namaMaskapai").get(0);
            kelasPesawat_str = bundle.getString("kelasPesawat");
            tanggalDatang_str = bundle.getStringArrayList("tanggalDatang").get(0);
            waktuDatang_str = bundle.getStringArrayList("waktuDatang").get(0);
            bandaraTujuan_str = bundle.getStringArrayList("bandaraTujuan").get(0);
            jmlDewasa_str = bundle.getString("jmlDewasa");
            jmlAnak_str = bundle.getString("jmlAnak");
            jmlBalita_str = bundle.getString("jmlBalita");
            bagasi_default = bundle.getStringArrayList("bagasi").get(0);
            keberangkatan = bundle.getString("keberangkatan");
            kedatangan = bundle.getString("kedatangan");
            tanggal = bundle.getString("tanggal");
            penumpang = bundle.getString("penumpang");
            kota_keberangkatan = bundle.getString("kota_keberangkatan");
            kota_kedatangan = bundle.getString("kota_kedatangan");
            bandara_keberangktan_raw = bundle.getString("bandara_keberangkatan");
            bandara_kedatangan_raw = bundle.getString("bandara_kedatangan");
            harga_dewasa = bundle.getString("hargaDewasa");
            harga_balita = bundle.getString("hargaBalita");

            //data pulang
            tanggalBerangkat_str_pulang = bundle.getStringArrayList("tanggalBerangkat_pulang").get(0);
            waktuBerangkat_str_pulang = bundle.getStringArrayList("waktuBerangkat_pulang").get(0);
            bandaraAsal_str_pulang = bundle.getStringArrayList("bandaraAsal_pulang").get(0);
            logoMaskapai_int_pulang = bundle.getIntegerArrayList("logoMaskapai_pulang").get(0);
            namaMaskapai_str_pulang = bundle.getStringArrayList("namaMaskapai_pulang").get(0);
            kelasPesawat_str_pulang = bundle.getString("kelasPesawat_pulang");
            tanggalDatang_str_pulang = bundle.getStringArrayList("tanggalDatang_pulang").get(0);
            waktuDatang_str_pulang = bundle.getStringArrayList("waktuDatang_pulang").get(0);
            bandaraTujuan_str_pulang = bundle.getStringArrayList("bandaraTujuan_pulang").get(0);
            jmlDewasa_str_pulang = bundle.getString("jmlDewasa_pulang");
            jmlAnak_str_pulang = bundle.getString("jmlAnak_pulang");
            jmlBalita_str_pulang = bundle.getString("jmlBalita_pulang");
            kotaTujuan_str_pulang = bundle.getString("kotaTujuan_pulang");
            kotaAsal_str_pulang = bundle.getString("kotaAsal_pulang");
            harga_str_pulang = bundle.getString("harga_pulang");
            jmlPenumpang_pulang = bundle.getInt("jmlPenumpang_pulang");
            harga_dewasa_pulang = bundle.getString("hargaDewasa_pulang");
            harga_balita_pulang = bundle.getString("hargaBalita_pulang");

            keberangkatan_pulang = bundle.getString("keberangkatan_pulang");
            kedatangan_pulang = bundle.getString("kedatangan_pulang");
            tanggal_pulang = bundle.getString("tanggal_pulang");
            penumpang_pulang = bundle.getString("penumpang_pulang");
            kota_kedatangan_pulang = bundle.getString("kota_kedatangan_pulang");
            kota_keberangkatan_pulang = bundle.getString("kota_keberangkatan_pulang");
            bandara_keberangktan_raw_pulang = bundle.getString("bandara_keberangktan_raw_pulang");
            bandara_kedatangan_raw_pulang = bundle.getString("bandara_kedatangan_raw_pulang");
            rincianPenumpang_pulang = bundle.getString("rincianPenumpang_pulang");


            //Essential for Firebase -- Pergi
            harga_str =bundle.getString("harga");
            harga_dewasa = bundle.getString("hargaDewasa");
            harga_balita = bundle.getString("hargaBalita");
            kotaAsal_str = bundle.getString("kotaAsal");
            kotaTujuan_str = bundle.getString("kotaTujuan");
            tanggalBerangkat_ArrayList = bundle.getStringArrayList("tanggalBerangkat");
            Log.i("A3 tglBerangkatI", tanggalBerangkat_ArrayList + "tes");

            waktuBerangkat_ArrayList = bundle.getStringArrayList("waktuBerangkat");
            Log.i("A3 WAKTU", waktuBerangkat_ArrayList + "");
            bandaraAsal_ArrayList = bundle.getStringArrayList("bandaraAsal");
            logoMaskapai_ArrayList =bundle.getIntegerArrayList("logoMaskapai");
            namaMaskapai_ArrayList= bundle.getStringArrayList("namaMaskapai");;
            kodePenerbangan_ArrayList = bundle.getStringArrayList("kodePenerbangan");
            kabin_ArrayList = bundle.getStringArrayList("kabin");;
            bagasi_ArrayList = bundle.getStringArrayList("bagasi");;
            booleanMakan_ArrayList = bundle.getIntegerArrayList("booleanMakan");;
            Log.i("A3 MAKAN", booleanMakan_ArrayList + "makan");
            keteranganMakan_ArrayList = bundle.getStringArrayList("keteranganMakan");
            modelPesawat_ArrayList = bundle.getStringArrayList("modelPesawat");
            kelasPesawat_ArrayList= bundle.getStringArrayList("kelasPesawat");;
            tanggalDatang_ArrayList= bundle.getStringArrayList("tanggalDatang");;
            waktuDatang_ArrayList= bundle.getStringArrayList("waktuDatang");;
            bandaraTujuan_ArrayList= bundle.getStringArrayList("bandaraTujuan");
            durasi_ArrayList = bundle.getStringArrayList("durasi");
            terminalBerangkat = bundle.getStringArrayList("terminalBerangkat");
            terminalDatang = bundle.getStringArrayList("terminalDatang");


            keberangkatan = bundle.getString("keberangkatan");
            kedatangan = bundle.getString("kedatangan");
            tanggal = bundle.getString("tanggal");
            tanggalPulang = bundle.getString("tanggal_pulang");
            penumpang = bundle.getString("penumpang");
            kota_keberangkatan = bundle.getString("kota_keberangkatan");
            kota_kedatangan = bundle.getString("kota_kedatangan");
            bandara_keberangktan_raw = bundle.getString("bandara_keberangkatan");
            bandara_kedatangan_raw =  bundle.getString("bandara_kedatangan");


            //Essential for Firebase -- Pulang
            harga_str_pulang =bundle.getString("harga_pulang");
            harga_dewasa_pulang = bundle.getString("hargaDewasa_pulang");
            harga_balita_pulang = bundle.getString("hargaBalita_pulang");
            kotaAsal_str_pulang = bundle.getString("kotaAsal_pulang");
            kotaTujuan_str_pulang = bundle.getString("kotaTujuan_pulang");
            tanggalBerangkat_ArrayList_pulang = bundle.getStringArrayList("tanggalBerangkat_pulang");
            Log.i("SELECTED TICKET PULANG", tanggalBerangkat_ArrayList + "tes");

            waktuBerangkat_ArrayList_pulang = bundle.getStringArrayList("waktuBerangkat_pulang");
            Log.i("SELECTED TICKET BS", waktuBerangkat_ArrayList + "");
            bandaraAsal_ArrayList_pulang = bundle.getStringArrayList("bandaraAsal_pulang");
            logoMaskapai_ArrayList_pulang =bundle.getIntegerArrayList("logoMaskapai_pulang");
            namaMaskapai_ArrayList_pulang= bundle.getStringArrayList("namaMaskapai_pulang");;
            kodePenerbangan_ArrayList_pulang = bundle.getStringArrayList("kodePenerbangan_pulang");
            kabin_ArrayList_pulang = bundle.getStringArrayList("kabin_pulang");;
            bagasi_ArrayList_pulang = bundle.getStringArrayList("bagasi_pulang");;
            booleanMakan_ArrayList_pulang = bundle.getIntegerArrayList("booleanMakan_pulang");;
            Log.i("SELECTED TICKET MAKAN", booleanMakan_ArrayList + "makan");
            keteranganMakan_ArrayList_pulang = bundle.getStringArrayList("keteranganMakan_pulang");
            modelPesawat_ArrayList_pulang = bundle.getStringArrayList("modelPesawat_pulang");
            kelasPesawat_ArrayList_pulang= bundle.getStringArrayList("kelasPesawat_pulang");;
            tanggalDatang_ArrayList_pulang= bundle.getStringArrayList("tanggalDatang_pulang");;
            waktuDatang_ArrayList_pulang= bundle.getStringArrayList("waktuDatang_pulang");;
            bandaraTujuan_ArrayList_pulang= bundle.getStringArrayList("bandaraTujuan_pulang");
            durasi_ArrayList_pulang = bundle.getStringArrayList("durasi_pulang");
            jmlDewasa_str_pulang= bundle.getString("jmlDewasa_pulang");;
            jmlAnak_str_pulang= bundle.getString("jmlAnak_pulang"); ;
            jmlBalita_str_pulang= bundle.getString("jmlBalita_pulang");;
            terminalBerangkat_pulang = bundle.getStringArrayList("terminalBerangkat_pulang");
            terminalDatang_pulang = bundle.getStringArrayList("terminalDatang_pulang");

            keberangkatan_pulang = bundle.getString("keberangkatan_pulang");
            kedatangan_pulang = bundle.getString("kedatangan_pulang");
            tanggal_pulang = bundle.getString("tanggal");
            tanggalPulang_pulang = bundle.getString("tanggal_pulang");
            penumpang_pulang = bundle.getString("penumpang_pulang");
            kota_keberangkatan_pulang = bundle.getString("kota_keberangkatan_pulang");
            kota_kedatangan_pulang = bundle.getString("kota_kedatangan_pulang");
            bandara_keberangktan_raw_pulang = bundle.getString("bandara_keberangkatan_pulang");
            bandara_kedatangan_raw_pulang =  bundle.getString("bandara_kedatangan_pulang");
        }





        rincianPenumpang = "Dewasa ("+bundle.getString("jmlDewasa")+"x)";
        if (!bundle.getString("jmlAnak").matches("0")){
            rincianPenumpang = rincianPenumpang + ", Anak (" +bundle.getString("jmlAnak")+"x)";
        }
        if (!bundle.getString("jmlBalita").matches("0")){
            rincianPenumpang = rincianPenumpang + ", Balita ("+bundle.getString("jmlBalita")+"x)";
        }



        jmlPenumpang = Integer.parseInt(jmlDewasa_str) +  Integer.parseInt(jmlAnak_str) + Integer.parseInt(jmlBalita_str);

        namaPassenger = new ArrayList<>();

        tglLahir = new ArrayList<>();
        titel = new ArrayList<>();
        kewarganegaraan = new ArrayList<>();
        NIKatauPaspor = new ArrayList<>();
        tambahan_kg = new ArrayList<>();
        harga_tambahan = new ArrayList<>();
        selected_position_opsi_bagasi = new ArrayList<>();
        tambahan_kg_pulang = new ArrayList<>();
        harga_tambahan_pulang = new ArrayList<>();
        selected_position_opsi_bagasi_pulang = new ArrayList<>();

        nama_namaTersimpan = new ArrayList<>();
        tglLahir_namaTersimpan = new ArrayList<>();
        titel_namaTersimpan = new ArrayList<>();
        kewarganegaraan_namaTersimpan = new ArrayList<>();
        NIKatauPaspor_namaTersimpan = new ArrayList<>();

        ArrayofPenumpangMaps = new ArrayList<>();



        for (int i = 1; i < jmlPenumpang+1 ; i ++) {
            namaPassenger.add("Penumpang " + i);
            titel.add("");
            tglLahir.add("");
            NIKatauPaspor.add("");
            kewarganegaraan.add("");

            tambahan_kg.add("0kg");
            harga_tambahan.add("IDR 0");
            selected_position_opsi_bagasi.add(0);

            tambahan_kg_pulang.add("0kg");
            harga_tambahan_pulang.add("IDR 0");
            selected_position_opsi_bagasi_pulang.add(0);

            dataPenumpangMap = new HashMap<>();
            dataPenumpangMap.put("namaPenumpang", namaPassenger.get(i-1));
            dataPenumpangMap.put("titel", titel.get(i-1));
            dataPenumpangMap.put("tglLahir", tglLahir.get(i-1));
            dataPenumpangMap.put("NIKatauPaspor", NIKatauPaspor.get(i-1));
            dataPenumpangMap.put("kewarganegaraan", kewarganegaraan.get(i-1));
            dataPenumpangMap.put("tambahan_kg", tambahan_kg.get(i-1));
            dataPenumpangMap.put("harga_tambahan", harga_tambahan.get(i-1));
            dataPenumpangMap.put("tambahan_kg_pulang", tambahan_kg_pulang.get(i-1));
            dataPenumpangMap.put("harga_tambahan_pulang", harga_tambahan_pulang.get(i-1));

            ArrayofPenumpangMaps.add(dataPenumpangMap);
            Log.i("ArrayofPenumpangMaps"+i, "" + ArrayofPenumpangMaps);

        }

        //FETCH NAMA TERSIMPAN
        fs.collection("namaTersimpan").whereEqualTo("userID", userID).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                if (queryDocumentSnapshots != null) {
                    List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot snapshot : snapshotList) {
                        Map<String, Object> map = (Map<String, Object>) snapshot.getData();
//                        String id = snapshot.getId();
//                        documentID.add(id);
                        String nama_namaTersimpan_str = map.get("nama").toString();
                        String tglLahir_namaTerimpan_str = map.get("tglLahir").toString();
                        String  titel_namaTerimpan_str = map.get("titel").toString();
                        String kewarganegaraan_namaTerimpan_str = map.get("kewarganegaraan").toString();
                        String NIKatauPaspor_namaTerimpan_str = "";
                        try {
                             NIKatauPaspor_namaTerimpan_str = map.get("NIKatauPaspor").toString();
                        } catch (Exception e) {
                             NIKatauPaspor_namaTerimpan_str = map.get("nikatauPaspor").toString();

                        }

                        nama_namaTersimpan.add(nama_namaTersimpan_str);
                        tglLahir_namaTersimpan.add(tglLahir_namaTerimpan_str);
                        titel_namaTersimpan.add( titel_namaTerimpan_str);
                        kewarganegaraan_namaTersimpan.add(kewarganegaraan_namaTerimpan_str);
                        NIKatauPaspor_namaTersimpan.add(NIKatauPaspor_namaTerimpan_str);

                    }
//                    TrankasiActivity.RecyclerAdapterTransaksi recyclerAdapterTransaksi = new TrankasiActivity.RecyclerAdapterTransaksi(keterangan, tanggal, tipeTransaksi, nominalTransaksi);
//                    binding.transaksiRecyclerView.setAdapter(recyclerAdapterTransaksi);
                }


            }
        });

        //EDIT TAMBAHAN BAGASI
        binding.tambahFasilitasEkstraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("namaList", namaPassenger);
                bundle.putString("bagasi_default", bagasi_default);
                bundle.putStringArrayList("tambahan_kg", tambahan_kg);
                bundle.putStringArrayList("harga_tambahan", harga_tambahan);
                bundle.putStringArrayList("tambahan_kg_pulang", tambahan_kg_pulang);
                bundle.putStringArrayList("harga_tambahan_pulang", harga_tambahan_pulang);
                bundle.putIntegerArrayList("selected_position_opsi_bagasi", selected_position_opsi_bagasi);
                bundle.putIntegerArrayList("selected_position_opsi_bagasi_pulang", selected_position_opsi_bagasi_pulang);

                bagasi_bottomsheet_redesign bagasi_bottomsheet_redesign = new bagasi_bottomsheet_redesign();
                bagasi_bottomsheet_redesign.setArguments(bundle);
                bagasi_bottomsheet_redesign.show(getSupportFragmentManager(), bagasi_bottomsheet_redesign.getTag());


            }
        });

        //TAMBAHAN BAGASI
//        binding.tambahanBagasiRelativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                bundle.putStringArrayList("namaList", namaPassenger);
//                bundle.putStringArrayList("tambahan_kg", tambahan_kg);
//                bundle.putStringArrayList("harga_tambahan", harga_tambahan);
//                TambahanBagasiBottomSheet tambahanBagasiBottomSheet = new TambahanBagasiBottomSheet();
//                tambahanBagasiBottomSheet.setArguments(bundle);
//                tambahanBagasiBottomSheet.show(getSupportFragmentManager(), tambahanBagasiBottomSheet.getTag());
//
//            }
//        });


        //BACK BUTTON
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlaneOrderActivity2.class);
                intent.putExtra("keberangkatan", keberangkatan);
                intent.putExtra("kedatangan", kedatangan);
                intent.putExtra("tanggal", tanggal);
                intent.putExtra("penumpang", penumpang);
                intent.putExtra("bandara_kedatangan", bandara_kedatangan_raw);
                intent.putExtra("bandara_keberangkatan", bandara_keberangktan_raw);
                startActivity(intent);
                overridePendingTransition(0 , 0);
            }
        });


        recyclerAdapterPenumpangList = new RecyclerAdapterPenumpangList(namaPassenger, this);
        binding.NamaPenumpangRecycleView.setAdapter(recyclerAdapterPenumpangList);


        //DISPLAYING THE DATA
        binding.kotaAsal.setText(kotaAsal_str);

        Log.i("kotaAsal", kotaAsal_str);
        Log.i("Bagasi default", "default: " + bagasi_default);
        binding.kotaTujuan.setText(kotaTujuan_str);
        binding.tanggalBerangkat.setText(tanggalBerangkat_str);
        binding.tanggalDatang.setText(tanggalDatang_str);
        binding.logoMaskapai.setImageResource(logoMaskapai_int);
        binding.namaMaskapai.setText(namaMaskapai_str);
        rincianPenumpang = "Dewasa ("+jmlDewasa_str+"x)";
        if (!jmlAnak_str.matches("0")){
            rincianPenumpang = rincianPenumpang + ", Anak ("+jmlAnak_str+"x)";
        }
        if (!jmlBalita_str.matches("0")){
            rincianPenumpang = rincianPenumpang + ", Balita ("+jmlBalita_str+"x)";
        }
        binding.rincianPenumpang.setText(rincianPenumpang);
//        binding.harga.setText(harga_str);

        //DISPLAYING PULANG DATA
        binding.kotaAsalPulang.setText(kotaAsal_str_pulang);
        Log.i("KotaAsalPulang", kotaAsal_str_pulang);
        binding.kotaTujuanPulang.setText(kotaTujuan_str_pulang);
        binding.tanggalBerangkatPulang.setText(tanggalBerangkat_str_pulang);
        binding.tanggalDatangPulang.setText(tanggalDatang_str_pulang);
        binding.logoMaskapaiPulang.setImageResource(logoMaskapai_int_pulang);
        binding.namaMaskapaiPulang.setText(namaMaskapai_str_pulang);
        binding.rincianPenumpangPulang.setText(rincianPenumpang);


//        binding.harga.setText(harga_str);



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




        //Total

        int totalBagasi = 0;
        for (int i = 0; i < harga_tambahan.size(); i++){
            Integer harga_satuan_bagasi = Integer.parseInt(harga_tambahan.get(i).split("IDR ")[1].replace(".", ""));
            Integer harga_satuan_bagasi_pulang = Integer.parseInt(harga_tambahan_pulang.get(i).split("IDR ")[1].replace(".", ""));
            totalBagasi = totalBagasi + harga_satuan_bagasi + harga_satuan_bagasi_pulang;
        }

        int harga_int = Integer.parseInt(harga_str.split("IDR ")[1].replace(".", ""));
        int harga_int_pulang = Integer.parseInt(harga_str_pulang.split("IDR ")[1].replace(".", ""));
        int grandTotal_int = harga_int + harga_int_pulang + totalBagasi;

        String grandTotal_str = "IDR " + String.format("%,d", grandTotal_int).replace(',', '.');

        binding.harga.setText(grandTotal_str);

        binding.detailPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailHarga_BottemSheet_PulangPergi detailHarga_bottemSheet_pulangPergi = new DetailHarga_BottemSheet_PulangPergi();
                bundle.putStringArrayList("harga_tambahan", harga_tambahan);
                bundle.putStringArrayList("tambahan_kg", tambahan_kg);
                bundle.putStringArrayList("harga_tambahan_pulang", harga_tambahan_pulang);
                bundle.putStringArrayList("tambahan_kg_pulang", tambahan_kg_pulang);
                bundle.putString("grand_total", binding.harga.getText().toString());
                detailHarga_bottemSheet_pulangPergi.setArguments(bundle);
                detailHarga_bottemSheet_pulangPergi.show(getSupportFragmentManager(), detailHarga_bottemSheet_pulangPergi.getTag());
            }
        });

        binding.pesanButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                if (kredit[0] < limit[0]) {
                    Toast.makeText(getApplicationContext(), "Silakan hubungi admin Pacto untuk top-up kredit", Toast.LENGTH_LONG).show();
                    return;
                }


                Intent intent = new Intent(getApplicationContext(), BookingActivity.class);
                bundle.putString("status", "Belum Bayar");
                bundle.putString("tipePesanan", "Pesawat");
                bundle.putString("bookingCode", "AS2831");
                bundle.putString("kodePenerbangan","LA9230" );


                FieldValue timestamp = FieldValue.serverTimestamp();
                Date date = new Date();
                long epoch_now = date.getTime();
                Calendar c = Calendar.getInstance();

                c.setTimeInMillis(epoch_now);
                c.add(Calendar.MINUTE, 15);
                long expTime_long = c.getTimeInMillis();


                long waktuArsip = 0;
                long waktuArsip_pulang = 0;


                Locale lokal = new Locale("id", "ID");
                try {
                    Date date1 = new SimpleDateFormat("E, dd MMM yyyy", lokal).parse(tanggalBerangkat_str);
                    waktuArsip = date1.getTime() + 86400000;
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }

                try {
                    Date date2 = new SimpleDateFormat("E, dd MMM yyyy", lokal).parse(tanggalBerangkat_str_pulang);
                    waktuArsip_pulang = date2.getTime() + 86400000;
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }

                ArrayList<String> bookingCode_pergi = new ArrayList<>();
                ArrayList<String> bookingCode_pulang = new ArrayList<>();
                for (int i = 0; i < bandaraAsal_ArrayList.size(); i++) {
                    bookingCode_pergi.add("BookingCode_Pergi" + i);
                    bookingCode_pulang.add("BookingCode_Pulang" + i);
                }

                int grand_total = Integer.parseInt(binding.harga.getText().toString().split("IDR ")[1].replace(".", ""));


                PenumpangData penumpangData = new PenumpangData(
                        //Informasi sintaksis Firebase
                        true, //boolean pulang pergi
                        expTime_long,
                        waktuArsip,
                        waktuArsip_pulang,
                        true, //boolean on going
                        true, //boolean on going pulang
                        "Belum bayar", //status
                        "Belum bayar", //status pulang
                        userID,
                        "Pesawat", //tipePesanan
                        timestamp,

                        //Primary keys
                        bookingCode_pergi,
                        bookingCode_pulang,

                        //Data Penumpang
                        ArrayofPenumpangMaps,
                        rincianPenumpang,
                        jmlDewasa_str,
                        jmlAnak_str,
                        jmlBalita_str,

                        //Harga
                        harga_dewasa,
                        harga_balita,
                        harga_dewasa_pulang,
                        harga_balita_pulang,
                        harga_str,
                        harga_str_pulang,
                        grand_total, //grand total


                        //Data Pergi
                        tanggalBerangkat_ArrayList,
                        waktuBerangkat_ArrayList,
                        bandaraAsal_ArrayList,
                        logoMaskapai_ArrayList,
                        namaMaskapai_ArrayList,
                        kodePenerbangan_ArrayList,
                        kelasPesawat_ArrayList,
                        tanggalDatang_ArrayList,
                        waktuDatang_ArrayList,
                        bandaraTujuan_ArrayList,
                        kabin_ArrayList,
                        bagasi_ArrayList,
                        booleanMakan_ArrayList,
                        keteranganMakan_ArrayList,
                        modelPesawat_ArrayList,
                        durasi_ArrayList,
                        terminalBerangkat,
                        terminalDatang,
                        tanggalPulang,
                        kotaAsal_str,
                        kotaTujuan_str,


                        //Data Pulang
                        tanggalBerangkat_ArrayList_pulang,
                        waktuBerangkat_ArrayList_pulang,
                        bandaraAsal_ArrayList_pulang,
                        logoMaskapai_ArrayList_pulang,
                        namaMaskapai_ArrayList_pulang,
                        kodePenerbangan_ArrayList_pulang,
                        kelasPesawat_ArrayList_pulang,
                        tanggalDatang_ArrayList_pulang,
                        waktuDatang_ArrayList_pulang,
                        bandaraTujuan_ArrayList_pulang,
                        kabin_ArrayList_pulang,
                        bagasi_ArrayList_pulang,
                        booleanMakan_ArrayList_pulang,
                        keteranganMakan_ArrayList_pulang,
                        modelPesawat_ArrayList_pulang,
                        durasi_ArrayList_pulang,
                        terminalBerangkat_pulang,
                        terminalDatang_pulang,
                        tanggalPulang_pulang,
                        kotaAsal_str_pulang,
                        kotaTujuan_str_pulang
                );




                fs.collection("bookingHistory").add(penumpangData);



//                fs.collection("bookingHistoryPesawat").document("test1").set(bundle);
//                fs.collection("bookingHistoryPesawat").document("test1").set(map);
                intent.putExtra("bundle", bundle);
                startActivity(intent);





            }
        });


    }

    @Override
    public void onDataPass(String nama_str, String titel_str, String tglLahir_str, String kewarganegaraan_str, String nikAtauPaspor_str, int penumpangKe_n, String request) {


        namaPassenger.set(penumpangKe_n-1, nama_str);
        tglLahir.set(penumpangKe_n-1, tglLahir_str);
        titel.set(penumpangKe_n-1, titel_str);
        kewarganegaraan.set(penumpangKe_n-1, kewarganegaraan_str);
        NIKatauPaspor.set(penumpangKe_n-1, nikAtauPaspor_str);

        ArrayofPenumpangMaps.clear();

        for(int i = 0; i<jmlPenumpang; i++) {
            dataPenumpangMap = new HashMap<>();
            dataPenumpangMap.put("namaPenumpang", namaPassenger.get(i));
            dataPenumpangMap.put("titel", titel.get(i));
            dataPenumpangMap.put("tglLahir", tglLahir.get(i));
            dataPenumpangMap.put("NIKatauPaspor", NIKatauPaspor.get(i));
            dataPenumpangMap.put("kewarganegaraan", kewarganegaraan.get(i));
            dataPenumpangMap.put("tambahan_kg", tambahan_kg.get(i));
            dataPenumpangMap.put("harga_tambahan", harga_tambahan.get(i));

            dataPenumpangMap.put("tambahan_kg", tambahan_kg.get(i));
            dataPenumpangMap.put("harga_tambahan", harga_tambahan.get(i));
            dataPenumpangMap.put("tambahan_kg_pulang", tambahan_kg_pulang.get(i));
            dataPenumpangMap.put("harga_tambahan_pulang", harga_tambahan_pulang.get(i));



            ArrayofPenumpangMaps.add(i, dataPenumpangMap);
        }






        recyclerAdapterPenumpangList.notifyDataSetChanged();



        Log.i("NAMA", nama_str);
        Log.i("titel", titel_str);
        Log.i("tglLahir", tglLahir_str);
        Log.i("kewaganegaraan", kewarganegaraan_str);
        Log.i("NIK/Passport", nikAtauPaspor_str);

    }

    @Override
    public void addPassengerDetail(String nomorPelanggan) {
        int index = Integer.parseInt(nomorPelanggan) - 1;
        DataPenumpang dataPenumpang = new DataPenumpang();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("nama_namaTersimpan", nama_namaTersimpan);
        bundle.putStringArrayList("tglLahir_namaTersimpan", tglLahir_namaTersimpan);
        bundle.putStringArrayList("titel_namaTersimpan", titel_namaTersimpan);
        bundle.putStringArrayList("kewarganegaraan_namaTersimpan", kewarganegaraan_namaTersimpan);
        bundle.putStringArrayList("NIKatauPaspor_namaTersimpan", NIKatauPaspor_namaTersimpan);
        bundle.putString("penumpangKe_n", nomorPelanggan);
        bundle.putString("tglLahir_str", tglLahir.get(index));
        bundle.putString("nama_str", namaPassenger.get(index));
        bundle.putString("kewarganegaraan_str", kewarganegaraan.get(index));
        bundle.putString("NIKatauPaspor_str", NIKatauPaspor.get(index));
        bundle.putString("titel_str", titel.get(index));
        dataPenumpang.setArguments(bundle);
        dataPenumpang.show(getSupportFragmentManager(), dataPenumpang.getTag());

    }

    @Override
    public void OnDataBagasi(ArrayList<String> tambahan_kg, ArrayList<String> hargaTambahanBagasi) {

        ArrayofPenumpangMaps.clear();

        for(int i = 0; i<jmlPenumpang; i++) {
            dataPenumpangMap = new HashMap<>();
            dataPenumpangMap.put("namaPenumpang", namaPassenger.get(i));
            dataPenumpangMap.put("titel", titel.get(i));
            dataPenumpangMap.put("tglLahir", tglLahir.get(i));
            dataPenumpangMap.put("NIKatauPaspor", NIKatauPaspor.get(i));
            dataPenumpangMap.put("kewarganegaraan", kewarganegaraan.get(i));
            dataPenumpangMap.put("tambahan_kg", tambahan_kg.get(i));
            dataPenumpangMap.put("harga_tambahan", harga_tambahan.get(i));

            ArrayofPenumpangMaps.add(i, dataPenumpangMap);
        }

        RecyclerAdapterBagasi recyclerAdapterBagasi = new RecyclerAdapterBagasi(tambahan_kg, hargaTambahanBagasi);
        binding.BagasiRecyclerView.setAdapter(recyclerAdapterBagasi);
        binding.BagasiRecyclerView.setVisibility(View.VISIBLE);
        binding.tambahanBagasiRelativeLayout.setBackground(getResources().getDrawable(R.drawable.curved__even_less_bg));
        binding.bagasi.setVisibility(View.GONE);
        binding.bagasiTambahan.setVisibility(View.GONE);
        binding.hargaBagasiTambahan.setVisibility(View.GONE);
    }

    @Override
    public void OnDataBagasiBottomSheetRedesign(ArrayList<String> mtambahan_kg, ArrayList<String> mharga_tambahan, ArrayList<Integer> mselectIntegered_position_opsi_bagasi, ArrayList<String> mtambahan_kg_pulang, ArrayList<String> mharga_tambahan_pulang, ArrayList<Integer> mselected_position_opsi_bagasi_pulang) {
        ArrayofPenumpangMaps.clear();
        selected_position_opsi_bagasi = mselectIntegered_position_opsi_bagasi;
        selected_position_opsi_bagasi_pulang = mselected_position_opsi_bagasi_pulang;


        for (int i = 1; i < jmlPenumpang+1 ; i ++) {

            dataPenumpangMap = new HashMap<>();
            dataPenumpangMap.put("namaPenumpang", namaPassenger.get(i-1));
            dataPenumpangMap.put("titel", titel.get(i-1));
            dataPenumpangMap.put("tglLahir", tglLahir.get(i-1));
            dataPenumpangMap.put("NIKatauPaspor", NIKatauPaspor.get(i-1));
            dataPenumpangMap.put("kewarganegaraan", kewarganegaraan.get(i-1));
            dataPenumpangMap.put("tambahan_kg", mtambahan_kg.get(i-1));
            dataPenumpangMap.put("harga_tambahan", mharga_tambahan.get(i-1));
            dataPenumpangMap.put("tambahan_kg_pulang", mtambahan_kg_pulang.get(i-1));
            dataPenumpangMap.put("harga_tambahan_pulang", mharga_tambahan_pulang.get(i-1));

            ArrayofPenumpangMaps.add(dataPenumpangMap);
            Log.i("ArrayofPenumpangMaps"+i, "" + ArrayofPenumpangMaps);

        }

        int totalBagasi = 0;
        int bagasi_pergi = 0;
        int bagasi_pulang = 0;
        for (int i = 0; i < harga_tambahan.size(); i++){
            Integer harga_satuan_bagasi = Integer.parseInt(harga_tambahan.get(i).split("IDR ")[1].replace(".", ""));
            Integer harga_satuan_bagasi_pulang = Integer.parseInt(harga_tambahan_pulang.get(i).split("IDR ")[1].replace(".", ""));
            totalBagasi = totalBagasi + harga_satuan_bagasi + harga_satuan_bagasi_pulang;
            bagasi_pergi += harga_satuan_bagasi;
            bagasi_pulang += harga_satuan_bagasi_pulang;
        }

        int harga_int = Integer.parseInt(harga_str.split("IDR ")[1].replace(".", ""));
        int harga_int_pulang = Integer.parseInt(harga_str_pulang.split("IDR ")[1].replace(".", ""));
        int grandTotal_int = harga_int + harga_int_pulang + totalBagasi;

        harga_str_pulang = "IDR " + String.format("%,d", bagasi_pulang + harga_int_pulang).replace(",", ".");
        harga_str = "IDR " + String.format("%,d", bagasi_pergi + harga_int).replace(",", ".");

        String grandTotal_str = "IDR " + String.format("%,d", grandTotal_int).replace(',', '.');

        binding.harga.setText(grandTotal_str);
    }

    public class PenumpangData {

        //Informasi sintaksis Firebase
        boolean pulangPergi;
        Long expTime;
        Long waktuArsip;
        Long waktuArsip_pulang;
        private boolean ongoing;
        private boolean ongoing_pulang;
        private String status;
        private String status_pulang;
        private String userID;
        private String tipePesanan;
        private FieldValue timeStampPesanan;

        //Primary keys
        ArrayList<String> bookingCode_pergi;
        ArrayList<String> bookingCode_pulang;


        //Penumpang
        private List<Map<String, String>> penumpang;
        String rincianPenumpang;
        String jmlDewasa;
        String jmlAnak;
        String jmlBalita;


        //harga
        String harga_dewasa;
        String harga_balita;
        String harga_dewasa_pulang;
        String harga_balita_pulang;
        String harga_total_pergi;
        String harga_total_pulang;
        int grand_total;

        //Pergi
        ArrayList<String> tanggalBerangkat_ArrayList;
        ArrayList<String> waktuBerangkat_ArrayList;
        ArrayList<String> bandaraAsal_ArrayList;
        ArrayList<Integer> logoMaskapai_ArrayList;
        ArrayList<String> namaMaskapai_ArrayList;
        ArrayList<String> kodePenerbangan_ArrayList;
        ArrayList<String> kelasPesawat_ArrayList;
        ArrayList<String> tanggalDatang_ArrayList;
        ArrayList<String> waktuDatang_ArrayList;
        ArrayList<String> bandaraTujuan_ArrayList;
        ArrayList<String> kabin_ArrayList;
        ArrayList<String> bagasi_ArrayList;
        ArrayList<Integer> booleanMakan_ArrayList;
        ArrayList<String> keteranganMakan_ArrayList;
        ArrayList<String> modelPesawat_ArrayList;
        ArrayList<String> durasi_ArrayList;
        ArrayList<String> terminalBerangkat;
        ArrayList<String> terminalDatang;
        String tanggalPulang;
        String kotaAsal;
        String kotaTujuan;


        //Pergi
        ArrayList<String> tanggalBerangkat_ArrayList_pulang;
        ArrayList<String> waktuBerangkat_ArrayList_pulang;
        ArrayList<String> bandaraAsal_ArrayList_pulang;
        ArrayList<Integer> logoMaskapai_ArrayList_pulang;
        ArrayList<String> namaMaskapai_ArrayList_pulang;
        ArrayList<String> kodePenerbangan_ArrayList_pulang;
        ArrayList<String> kelasPesawat_ArrayList_pulang;
        ArrayList<String> tanggalDatang_ArrayList_pulang;
        ArrayList<String> waktuDatang_ArrayList_pulang;
        ArrayList<String> bandaraTujuan_ArrayList_pulang;
        ArrayList<String> kabin_ArrayList_pulang;
        ArrayList<String> bagasi_ArrayList_pulang;
        ArrayList<Integer> booleanMakan_ArrayList_pulang;
        ArrayList<String> keteranganMakan_ArrayList_pulang;
        ArrayList<String> modelPesawat_ArrayList_pulang;
        ArrayList<String> durasi_ArrayList_pulang;
        ArrayList<String> terminalBerangkat_pulang;
        ArrayList<String> terminalDatang_pulang;
        String tanggalPulang_pulang;
        String kotaAsal_pulang;
        String kotaTujuan_pulang;





        public PenumpangData(){

        }


        public PenumpangData(boolean pulangPergi, Long expTime, Long waktuArsip, Long waktuArsip_pulang, boolean ongoing, boolean ongoing_pulang, String status, String status_pulang, String userID, String tipePesanan, FieldValue timeStampPesanan, ArrayList<String> bookingCode_pergi, ArrayList<String> bookingCode_pulang, List<Map<String, String>> penumpang, String rincianPenumpang, String jmlDewasa, String jmlAnak, String jmlBalita, String harga_dewasa, String harga_balita, String harga_dewasa_pulang, String harga_balita_pulang, String harga_total_pergi, String harga_total_pulang, int grand_total, ArrayList<String> tanggalBerangkat_ArrayList, ArrayList<String> waktuBerangkat_ArrayList, ArrayList<String> bandaraAsal_ArrayList, ArrayList<Integer> logoMaskapai_ArrayList, ArrayList<String> namaMaskapai_ArrayList, ArrayList<String> kodePenerbangan_ArrayList, ArrayList<String> kelasPesawat_ArrayList, ArrayList<String> tanggalDatang_ArrayList, ArrayList<String> waktuDatang_ArrayList, ArrayList<String> bandaraTujuan_ArrayList, ArrayList<String> kabin_ArrayList, ArrayList<String> bagasi_ArrayList, ArrayList<Integer> booleanMakan_ArrayList, ArrayList<String> keteranganMakan_ArrayList, ArrayList<String> modelPesawat_ArrayList, ArrayList<String> durasi_ArrayList, ArrayList<String> terminalBerangkat, ArrayList<String> terminalDatang, String tanggalPulang, String kotaAsal, String kotaTujuan, ArrayList<String> tanggalBerangkat_ArrayList_pulang, ArrayList<String> waktuBerangkat_ArrayList_pulang, ArrayList<String> bandaraAsal_ArrayList_pulang, ArrayList<Integer> logoMaskapai_ArrayList_pulang, ArrayList<String> namaMaskapai_ArrayList_pulang, ArrayList<String> kodePenerbangan_ArrayList_pulang, ArrayList<String> kelasPesawat_ArrayList_pulang, ArrayList<String> tanggalDatang_ArrayList_pulang, ArrayList<String> waktuDatang_ArrayList_pulang, ArrayList<String> bandaraTujuan_ArrayList_pulang, ArrayList<String> kabin_ArrayList_pulang, ArrayList<String> bagasi_ArrayList_pulang, ArrayList<Integer> booleanMakan_ArrayList_pulang, ArrayList<String> keteranganMakan_ArrayList_pulang, ArrayList<String> modelPesawat_ArrayList_pulang, ArrayList<String> durasi_ArrayList_pulang, ArrayList<String> terminalBerangkat_pulang, ArrayList<String> terminalDatang_pulang, String tanggalPulang_pulang, String kotaAsal_pulang, String kotaTujuan_pulang) {
            this.pulangPergi = pulangPergi;
            this.expTime = expTime;
            this.waktuArsip = waktuArsip;
            this.waktuArsip_pulang = waktuArsip_pulang;
            this.ongoing = ongoing;
            this.ongoing_pulang = ongoing_pulang;
            this.status = status;
            this.status_pulang = status_pulang;
            this.userID = userID;
            this.tipePesanan = tipePesanan;
            this.timeStampPesanan = timeStampPesanan;
            this.bookingCode_pergi = bookingCode_pergi;
            this.bookingCode_pulang = bookingCode_pulang;
            this.penumpang = penumpang;
            this.rincianPenumpang = rincianPenumpang;
            this.jmlDewasa = jmlDewasa;
            this.jmlAnak = jmlAnak;
            this.jmlBalita = jmlBalita;
            this.harga_dewasa = harga_dewasa;
            this.harga_balita = harga_balita;
            this.harga_dewasa_pulang = harga_dewasa_pulang;
            this.harga_balita_pulang = harga_balita_pulang;
            this.harga_total_pergi = harga_total_pergi;
            this.harga_total_pulang = harga_total_pulang;
            this.grand_total = grand_total;
            this.tanggalBerangkat_ArrayList = tanggalBerangkat_ArrayList;
            this.waktuBerangkat_ArrayList = waktuBerangkat_ArrayList;
            this.bandaraAsal_ArrayList = bandaraAsal_ArrayList;
            this.logoMaskapai_ArrayList = logoMaskapai_ArrayList;
            this.namaMaskapai_ArrayList = namaMaskapai_ArrayList;
            this.kodePenerbangan_ArrayList = kodePenerbangan_ArrayList;
            this.kelasPesawat_ArrayList = kelasPesawat_ArrayList;
            this.tanggalDatang_ArrayList = tanggalDatang_ArrayList;
            this.waktuDatang_ArrayList = waktuDatang_ArrayList;
            this.bandaraTujuan_ArrayList = bandaraTujuan_ArrayList;
            this.kabin_ArrayList = kabin_ArrayList;
            this.bagasi_ArrayList = bagasi_ArrayList;
            this.booleanMakan_ArrayList = booleanMakan_ArrayList;
            this.keteranganMakan_ArrayList = keteranganMakan_ArrayList;
            this.modelPesawat_ArrayList = modelPesawat_ArrayList;
            this.durasi_ArrayList = durasi_ArrayList;
            this.terminalBerangkat = terminalBerangkat;
            this.terminalDatang = terminalDatang;
            this.tanggalPulang = tanggalPulang;
            this.kotaAsal = kotaAsal;
            this.kotaTujuan = kotaTujuan;
            this.tanggalBerangkat_ArrayList_pulang = tanggalBerangkat_ArrayList_pulang;
            this.waktuBerangkat_ArrayList_pulang = waktuBerangkat_ArrayList_pulang;
            this.bandaraAsal_ArrayList_pulang = bandaraAsal_ArrayList_pulang;
            this.logoMaskapai_ArrayList_pulang = logoMaskapai_ArrayList_pulang;
            this.namaMaskapai_ArrayList_pulang = namaMaskapai_ArrayList_pulang;
            this.kodePenerbangan_ArrayList_pulang = kodePenerbangan_ArrayList_pulang;
            this.kelasPesawat_ArrayList_pulang = kelasPesawat_ArrayList_pulang;
            this.tanggalDatang_ArrayList_pulang = tanggalDatang_ArrayList_pulang;
            this.waktuDatang_ArrayList_pulang = waktuDatang_ArrayList_pulang;
            this.bandaraTujuan_ArrayList_pulang = bandaraTujuan_ArrayList_pulang;
            this.kabin_ArrayList_pulang = kabin_ArrayList_pulang;
            this.bagasi_ArrayList_pulang = bagasi_ArrayList_pulang;
            this.booleanMakan_ArrayList_pulang = booleanMakan_ArrayList_pulang;
            this.keteranganMakan_ArrayList_pulang = keteranganMakan_ArrayList_pulang;
            this.modelPesawat_ArrayList_pulang = modelPesawat_ArrayList_pulang;
            this.durasi_ArrayList_pulang = durasi_ArrayList_pulang;
            this.terminalBerangkat_pulang = terminalBerangkat_pulang;
            this.terminalDatang_pulang = terminalDatang_pulang;
            this.tanggalPulang_pulang = tanggalPulang_pulang;
            this.kotaAsal_pulang = kotaAsal_pulang;
            this.kotaTujuan_pulang = kotaTujuan_pulang;
        }

        public String getJmlDewasa() {
            return jmlDewasa;
        }

        public void setJmlDewasa(String jmlDewasa) {
            this.jmlDewasa = jmlDewasa;
        }

        public String getJmlAnak() {
            return jmlAnak;
        }

        public void setJmlAnak(String jmlAnak) {
            this.jmlAnak = jmlAnak;
        }

        public String getJmlBalita() {
            return jmlBalita;
        }

        public void setJmlBalita(String jmlBalita) {
            this.jmlBalita = jmlBalita;
        }

        public ArrayList<String> getTerminalBerangkat() {
            return terminalBerangkat;
        }

        public void setTerminalBerangkat(ArrayList<String> terminalBerangkat) {
            this.terminalBerangkat = terminalBerangkat;
        }

        public ArrayList<String> getTerminalDatang() {
            return terminalDatang;
        }

        public void setTerminalDatang(ArrayList<String> terminalDatang) {
            this.terminalDatang = terminalDatang;
        }

        public ArrayList<String> getTerminalBerangkat_pulang() {
            return terminalBerangkat_pulang;
        }

        public void setTerminalBerangkat_pulang(ArrayList<String> terminalBerangkat_pulang) {
            this.terminalBerangkat_pulang = terminalBerangkat_pulang;
        }

        public ArrayList<String> getTerminalDatang_pulang() {
            return terminalDatang_pulang;
        }

        public void setTerminalDatang_pulang(ArrayList<String> terminalDatang_pulang) {
            this.terminalDatang_pulang = terminalDatang_pulang;
        }

        public String getHarga_dewasa() {
            return harga_dewasa;
        }

        public void setHarga_dewasa(String harga_dewasa) {
            this.harga_dewasa = harga_dewasa;
        }

        public String getHarga_balita() {
            return harga_balita;
        }

        public void setHarga_balita(String harga_balita) {
            this.harga_balita = harga_balita;
        }

        public String getHarga_dewasa_pulang() {
            return harga_dewasa_pulang;
        }

        public void setHarga_dewasa_pulang(String harga_dewasa_pulang) {
            this.harga_dewasa_pulang = harga_dewasa_pulang;
        }

        public String getHarga_balita_pulang() {
            return harga_balita_pulang;
        }

        public void setHarga_balita_pulang(String harga_balita_pulang) {
            this.harga_balita_pulang = harga_balita_pulang;
        }

        public String getHarga_total_pergi() {
            return harga_total_pergi;
        }

        public void setHarga_total_pergi(String harga_total_pergi) {
            this.harga_total_pergi = harga_total_pergi;
        }

        public String getHarga_total_pulang() {
            return harga_total_pulang;
        }

        public void setHarga_total_pulang(String harga_total_pulang) {
            this.harga_total_pulang = harga_total_pulang;
        }

        public int getGrand_total() {
            return grand_total;
        }

        public void setGrand_total(int grand_total) {
            this.grand_total = grand_total;
        }

        public String getRincianPenumpang() {
            return rincianPenumpang;
        }

        public void setRincianPenumpang(String rincianPenumpang) {
            this.rincianPenumpang = rincianPenumpang;
        }

        public String getKotaAsal() {
            return kotaAsal;
        }

        public void setKotaAsal(String kotaAsal) {
            this.kotaAsal = kotaAsal;
        }

        public String getKotaTujuan() {
            return kotaTujuan;
        }

        public void setKotaTujuan(String kotaTujuan) {
            this.kotaTujuan = kotaTujuan;
        }

        public String getKotaAsal_pulang() {
            return kotaAsal_pulang;
        }

        public void setKotaAsal_pulang(String kotaAsal_pulang) {
            this.kotaAsal_pulang = kotaAsal_pulang;
        }

        public String getKotaTujuan_pulang() {
            return kotaTujuan_pulang;
        }

        public void setKotaTujuan_pulang(String kotaTujuan_pulang) {
            this.kotaTujuan_pulang = kotaTujuan_pulang;
        }

        public boolean isPulangPergi() {
            return pulangPergi;
        }

        public void setPulangPergi(boolean pulangPergi) {
            this.pulangPergi = pulangPergi;
        }

        public Long getExpTime() {
            return expTime;
        }

        public void setExpTime(Long expTime) {
            this.expTime = expTime;
        }

        public Long getWaktuArsip() {
            return waktuArsip;
        }

        public void setWaktuArsip(Long waktuArsip) {
            this.waktuArsip = waktuArsip;
        }

        public Long getWaktuArsip_pulang() {
            return waktuArsip_pulang;
        }

        public void setWaktuArsip_pulang(Long waktuArsip_pulang) {
            this.waktuArsip_pulang = waktuArsip_pulang;
        }

        public boolean isOngoing() {
            return ongoing;
        }

        public void setOngoing(boolean ongoing) {
            this.ongoing = ongoing;
        }

        public boolean isOngoing_pulang() {
            return ongoing_pulang;
        }

        public void setOngoing_pulang(boolean ongoing_pulang) {
            this.ongoing_pulang = ongoing_pulang;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatus_pulang() {
            return status_pulang;
        }

        public void setStatus_pulang(String status_pulang) {
            this.status_pulang = status_pulang;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getTipePesanan() {
            return tipePesanan;
        }

        public void setTipePesanan(String tipePesanan) {
            this.tipePesanan = tipePesanan;
        }

        public FieldValue getTimeStampPesanan() {
            return timeStampPesanan;
        }

        public void setTimeStampPesanan(FieldValue timeStampPesanan) {
            this.timeStampPesanan = timeStampPesanan;
        }

        public ArrayList<String> getBookingCode_pergi() {
            return bookingCode_pergi;
        }

        public void setBookingCode_pergi(ArrayList<String> bookingCode_pergi) {
            this.bookingCode_pergi = bookingCode_pergi;
        }

        public ArrayList<String> getBookingCode_pulang() {
            return bookingCode_pulang;
        }

        public void setBookingCode_pulang(ArrayList<String> bookingCode_pulang) {
            this.bookingCode_pulang = bookingCode_pulang;
        }

        public List<Map<String, String>> getPenumpang() {
            return penumpang;
        }

        public void setPenumpang(List<Map<String, String>> penumpang) {
            this.penumpang = penumpang;
        }

        public ArrayList<String> getTanggalBerangkat_ArrayList() {
            return tanggalBerangkat_ArrayList;
        }

        public void setTanggalBerangkat_ArrayList(ArrayList<String> tanggalBerangkat_ArrayList) {
            this.tanggalBerangkat_ArrayList = tanggalBerangkat_ArrayList;
        }

        public ArrayList<String> getWaktuBerangkat_ArrayList() {
            return waktuBerangkat_ArrayList;
        }

        public void setWaktuBerangkat_ArrayList(ArrayList<String> waktuBerangkat_ArrayList) {
            this.waktuBerangkat_ArrayList = waktuBerangkat_ArrayList;
        }

        public ArrayList<String> getBandaraAsal_ArrayList() {
            return bandaraAsal_ArrayList;
        }

        public void setBandaraAsal_ArrayList(ArrayList<String> bandaraAsal_ArrayList) {
            this.bandaraAsal_ArrayList = bandaraAsal_ArrayList;
        }

        public ArrayList<Integer> getLogoMaskapai_ArrayList() {
            return logoMaskapai_ArrayList;
        }

        public void setLogoMaskapai_ArrayList(ArrayList<Integer> logoMaskapai_ArrayList) {
            this.logoMaskapai_ArrayList = logoMaskapai_ArrayList;
        }

        public ArrayList<String> getNamaMaskapai_ArrayList() {
            return namaMaskapai_ArrayList;
        }

        public void setNamaMaskapai_ArrayList(ArrayList<String> namaMaskapai_ArrayList) {
            this.namaMaskapai_ArrayList = namaMaskapai_ArrayList;
        }

        public ArrayList<String> getKodePenerbangan_ArrayList() {
            return kodePenerbangan_ArrayList;
        }

        public void setKodePenerbangan_ArrayList(ArrayList<String> kodePenerbangan_ArrayList) {
            this.kodePenerbangan_ArrayList = kodePenerbangan_ArrayList;
        }

        public ArrayList<String> getKelasPesawat_ArrayList() {
            return kelasPesawat_ArrayList;
        }

        public void setKelasPesawat_ArrayList(ArrayList<String> kelasPesawat_ArrayList) {
            this.kelasPesawat_ArrayList = kelasPesawat_ArrayList;
        }

        public ArrayList<String> getTanggalDatang_ArrayList() {
            return tanggalDatang_ArrayList;
        }

        public void setTanggalDatang_ArrayList(ArrayList<String> tanggalDatang_ArrayList) {
            this.tanggalDatang_ArrayList = tanggalDatang_ArrayList;
        }

        public ArrayList<String> getWaktuDatang_ArrayList() {
            return waktuDatang_ArrayList;
        }

        public void setWaktuDatang_ArrayList(ArrayList<String> waktuDatang_ArrayList) {
            this.waktuDatang_ArrayList = waktuDatang_ArrayList;
        }

        public ArrayList<String> getBandaraTujuan_ArrayList() {
            return bandaraTujuan_ArrayList;
        }

        public void setBandaraTujuan_ArrayList(ArrayList<String> bandaraTujuan_ArrayList) {
            this.bandaraTujuan_ArrayList = bandaraTujuan_ArrayList;
        }

        public ArrayList<String> getKabin_ArrayList() {
            return kabin_ArrayList;
        }

        public void setKabin_ArrayList(ArrayList<String> kabin_ArrayList) {
            this.kabin_ArrayList = kabin_ArrayList;
        }

        public ArrayList<String> getBagasi_ArrayList() {
            return bagasi_ArrayList;
        }

        public void setBagasi_ArrayList(ArrayList<String> bagasi_ArrayList) {
            this.bagasi_ArrayList = bagasi_ArrayList;
        }

        public ArrayList<Integer> getBooleanMakan_ArrayList() {
            return booleanMakan_ArrayList;
        }

        public void setBooleanMakan_ArrayList(ArrayList<Integer> booleanMakan_ArrayList) {
            this.booleanMakan_ArrayList = booleanMakan_ArrayList;
        }

        public ArrayList<String> getKeteranganMakan_ArrayList() {
            return keteranganMakan_ArrayList;
        }

        public void setKeteranganMakan_ArrayList(ArrayList<String> keteranganMakan_ArrayList) {
            this.keteranganMakan_ArrayList = keteranganMakan_ArrayList;
        }

        public ArrayList<String> getModelPesawat_ArrayList() {
            return modelPesawat_ArrayList;
        }

        public void setModelPesawat_ArrayList(ArrayList<String> modelPesawat_ArrayList) {
            this.modelPesawat_ArrayList = modelPesawat_ArrayList;
        }

        public ArrayList<String> getDurasi_ArrayList() {
            return durasi_ArrayList;
        }

        public void setDurasi_ArrayList(ArrayList<String> durasi_ArrayList) {
            this.durasi_ArrayList = durasi_ArrayList;
        }

        public String getTanggalPulang() {
            return tanggalPulang;
        }

        public void setTanggalPulang(String tanggalPulang) {
            this.tanggalPulang = tanggalPulang;
        }

        public ArrayList<String> getTanggalBerangkat_ArrayList_pulang() {
            return tanggalBerangkat_ArrayList_pulang;
        }

        public void setTanggalBerangkat_ArrayList_pulang(ArrayList<String> tanggalBerangkat_ArrayList_pulang) {
            this.tanggalBerangkat_ArrayList_pulang = tanggalBerangkat_ArrayList_pulang;
        }

        public ArrayList<String> getWaktuBerangkat_ArrayList_pulang() {
            return waktuBerangkat_ArrayList_pulang;
        }

        public void setWaktuBerangkat_ArrayList_pulang(ArrayList<String> waktuBerangkat_ArrayList_pulang) {
            this.waktuBerangkat_ArrayList_pulang = waktuBerangkat_ArrayList_pulang;
        }

        public ArrayList<String> getBandaraAsal_ArrayList_pulang() {
            return bandaraAsal_ArrayList_pulang;
        }

        public void setBandaraAsal_ArrayList_pulang(ArrayList<String> bandaraAsal_ArrayList_pulang) {
            this.bandaraAsal_ArrayList_pulang = bandaraAsal_ArrayList_pulang;
        }

        public ArrayList<Integer> getLogoMaskapai_ArrayList_pulang() {
            return logoMaskapai_ArrayList_pulang;
        }

        public void setLogoMaskapai_ArrayList_pulang(ArrayList<Integer> logoMaskapai_ArrayList_pulang) {
            this.logoMaskapai_ArrayList_pulang = logoMaskapai_ArrayList_pulang;
        }

        public ArrayList<String> getNamaMaskapai_ArrayList_pulang() {
            return namaMaskapai_ArrayList_pulang;
        }

        public void setNamaMaskapai_ArrayList_pulang(ArrayList<String> namaMaskapai_ArrayList_pulang) {
            this.namaMaskapai_ArrayList_pulang = namaMaskapai_ArrayList_pulang;
        }

        public ArrayList<String> getKodePenerbangan_ArrayList_pulang() {
            return kodePenerbangan_ArrayList_pulang;
        }

        public void setKodePenerbangan_ArrayList_pulang(ArrayList<String> kodePenerbangan_ArrayList_pulang) {
            this.kodePenerbangan_ArrayList_pulang = kodePenerbangan_ArrayList_pulang;
        }

        public ArrayList<String> getKelasPesawat_ArrayList_pulang() {
            return kelasPesawat_ArrayList_pulang;
        }

        public void setKelasPesawat_ArrayList_pulang(ArrayList<String> kelasPesawat_ArrayList_pulang) {
            this.kelasPesawat_ArrayList_pulang = kelasPesawat_ArrayList_pulang;
        }

        public ArrayList<String> getTanggalDatang_ArrayList_pulang() {
            return tanggalDatang_ArrayList_pulang;
        }

        public void setTanggalDatang_ArrayList_pulang(ArrayList<String> tanggalDatang_ArrayList_pulang) {
            this.tanggalDatang_ArrayList_pulang = tanggalDatang_ArrayList_pulang;
        }

        public ArrayList<String> getWaktuDatang_ArrayList_pulang() {
            return waktuDatang_ArrayList_pulang;
        }

        public void setWaktuDatang_ArrayList_pulang(ArrayList<String> waktuDatang_ArrayList_pulang) {
            this.waktuDatang_ArrayList_pulang = waktuDatang_ArrayList_pulang;
        }

        public ArrayList<String> getBandaraTujuan_ArrayList_pulang() {
            return bandaraTujuan_ArrayList_pulang;
        }

        public void setBandaraTujuan_ArrayList_pulang(ArrayList<String> bandaraTujuan_ArrayList_pulang) {
            this.bandaraTujuan_ArrayList_pulang = bandaraTujuan_ArrayList_pulang;
        }

        public ArrayList<String> getKabin_ArrayList_pulang() {
            return kabin_ArrayList_pulang;
        }

        public void setKabin_ArrayList_pulang(ArrayList<String> kabin_ArrayList_pulang) {
            this.kabin_ArrayList_pulang = kabin_ArrayList_pulang;
        }

        public ArrayList<String> getBagasi_ArrayList_pulang() {
            return bagasi_ArrayList_pulang;
        }

        public void setBagasi_ArrayList_pulang(ArrayList<String> bagasi_ArrayList_pulang) {
            this.bagasi_ArrayList_pulang = bagasi_ArrayList_pulang;
        }

        public ArrayList<Integer> getBooleanMakan_ArrayList_pulang() {
            return booleanMakan_ArrayList_pulang;
        }

        public void setBooleanMakan_ArrayList_pulang(ArrayList<Integer> booleanMakan_ArrayList_pulang) {
            this.booleanMakan_ArrayList_pulang = booleanMakan_ArrayList_pulang;
        }

        public ArrayList<String> getKeteranganMakan_ArrayList_pulang() {
            return keteranganMakan_ArrayList_pulang;
        }

        public void setKeteranganMakan_ArrayList_pulang(ArrayList<String> keteranganMakan_ArrayList_pulang) {
            this.keteranganMakan_ArrayList_pulang = keteranganMakan_ArrayList_pulang;
        }

        public ArrayList<String> getModelPesawat_ArrayList_pulang() {
            return modelPesawat_ArrayList_pulang;
        }

        public void setModelPesawat_ArrayList_pulang(ArrayList<String> modelPesawat_ArrayList_pulang) {
            this.modelPesawat_ArrayList_pulang = modelPesawat_ArrayList_pulang;
        }

        public ArrayList<String> getDurasi_ArrayList_pulang() {
            return durasi_ArrayList_pulang;
        }

        public void setDurasi_ArrayList_pulang(ArrayList<String> durasi_ArrayList_pulang) {
            this.durasi_ArrayList_pulang = durasi_ArrayList_pulang;
        }

        public String getTanggalPulang_pulang() {
            return tanggalPulang_pulang;
        }

        public void setTanggalPulang_pulang(String tanggalPulang_pulang) {
            this.tanggalPulang_pulang = tanggalPulang_pulang;
        }
    }
}