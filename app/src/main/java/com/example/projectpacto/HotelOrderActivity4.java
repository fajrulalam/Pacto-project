package com.example.projectpacto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.projectpacto.databinding.ActivityHotelOrder4Binding;
import com.example.projectpacto.databinding.ActivityPlaneOrder3Binding;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.utils.URIBuilder;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HotelOrderActivity4 extends AppCompatActivity implements RecyclerAdapterPenumpangList.AddPassengerDetail, DataTamu_BottomSheet.OnDataTamu, PermintaanKhususBottomSheet.OnDataSpecialRequest {

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

    String permintaanKhusus;

    ArrayList<String> namaPassenger;
    ArrayList<String> titel;

    //Permintaan Khusus
    int bebasAsapRokok;
    int kamarTersambung;
    String waktuCekin_req;
    String waktuCekOut_req;
    String tipeRanjang_req;
    String catatanLainnya_req;

    RecyclerAdapterPenumpangList recyclerAdapterPenumpangList;
    FirebaseFirestore fs;

    int jumlahTamu_int;

    List<Map<String, String>> ArrayofPenumpangMaps;
    Map<String, String> dataPenumpangMap;



    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHotelOrder4Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        permintaanKhusus = "";
        extras = getIntent().getBundleExtra("bundle");
        gambarKamar= extras.getInt("gambarKamar");
        namaKamar= extras.getString("namaKamar");
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
        tglCek_in= extras.getString("tglCek_in");
        tglCek_out= extras.getString("tglCek_out");

        bebasAsapRokok = 0;
        kamarTersambung = 0;
//        waktuCekin_req = "";
//        waktuCekOut_req = "";
        tipeRanjang_req = "";
        catatanLainnya_req = "";

        String MSISDN =  "08180700672";
        String iccid = "8962115937878589405";
        URIBuilder uriBuilder = new URIBuilder()
                .setScheme("[INSERT_SCHEME")
                .setHost("[INSER_HOST")
                .setPath("subscriber/v1/params?iscancel=true&msisdn=")
                .setParameter("mssisdn", MSISDN)
                .setParameter("ICCID", iccid);
        String URL = uriBuilder.toString();


        Locale lokal = new Locale("id", "ID");

        fs = FirebaseFirestore.getInstance();

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

        binding.namaHotelTxt.setText(namaHotel);
        binding.alamatTambahan.setText(tambahanAlamat);
        binding.tglCekIn.setText(tglCek_in);
        binding.tglCekOut.setText(tglCek_out);
        binding.detailTamu.setText(jumlahKamar.split(", ")[0]);
        binding.jmldanPilihanKamar.setText(jumlahKamar.split(", ")[1]+ " ("+namaKamar+")");

        namaPassenger = new ArrayList<>();
        titel = new ArrayList<>();



        Log.i("NAMAHOTEL", ""+namaHotel);
        Log.i("NAMAKAMAR", ""+namaKamar);
        Log.i("CEKIN", ""+tglCek_in);
        Log.i("CEKOUT", ""+tglCek_out);
        Log.i("JUMLAH KAMAR", ""+jumlahKamar);
        Log.i("KAPASITAS KAMAR", ""+kapasitasKamar);
        Log.i("TIPE KASIR", ""+tipeKasur);

        jumlahTamu_int = Integer.parseInt(jumlahKamar.split(" ")[0]);

        ArrayofPenumpangMaps = new ArrayList<>();
        for (int i = 1; i < jumlahTamu_int+1 ; i ++) {
            namaPassenger.add("Tamu " + i);
            titel.add("");

            dataPenumpangMap = new HashMap<>();
            dataPenumpangMap.put("namaPenumpang", namaPassenger.get(i-1));
            dataPenumpangMap.put("titel", titel.get(i-1));
            ArrayofPenumpangMaps.add(dataPenumpangMap);
            Log.i("ArrayofPenumpangMaps"+i, "" + ArrayofPenumpangMaps);

        }

        recyclerAdapterPenumpangList = new RecyclerAdapterPenumpangList(namaPassenger, this);
        binding.NamaPenumpangRecycleView.setAdapter(recyclerAdapterPenumpangList);

        binding.tambahanBagasiRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("bebasAsapRokok", bebasAsapRokok);
                bundle.putInt("kamarTersambung", kamarTersambung);
//                bundle.putString("waktuCekin_req", waktuCekin_req);
//                bundle.putString("waktuCekOut_req", waktuCekOut_req);
                bundle.putString("tipeRanjang_req", tipeRanjang_req);
                bundle.putString("catatanLainnya_req", catatanLainnya_req);

                PermintaanKhususBottomSheet permintaanKhususBottomSheet = new PermintaanKhususBottomSheet();
                permintaanKhususBottomSheet.setArguments(bundle);
                permintaanKhususBottomSheet.show(getSupportFragmentManager(), permintaanKhususBottomSheet.getTag());



            }
        });

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HotelOrderActivity3.class);
                intent.putExtra("bundle", extras);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        binding.pesanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extras.putStringArrayList("namaTamu", namaPassenger);
                extras.putStringArrayList("titel", titel);
                extras.putString("permintaanKhusus", permintaanKhusus);

                Intent intent = new Intent(getApplicationContext(), MasukkanPIN_Activity.class);
               intent.putExtra("bundle", extras);
               startActivity(intent);
               overridePendingTransition(0, 0);

            }
        });


    }


    @Override
    public void addPassengerDetail(String nomorPelanggan) {
        DataTamu_BottomSheet dataTamu_bottomSheet = new DataTamu_BottomSheet();
        int index = Integer.parseInt(nomorPelanggan) - 1;
        Bundle bundle = new Bundle();
        bundle.putString("penumpangKe_n", nomorPelanggan);
        bundle.putString("nama_str", namaPassenger.get(index));
        bundle.putString("titel_str", titel.get(index));
        dataTamu_bottomSheet.setArguments(bundle);
        dataTamu_bottomSheet.show(getSupportFragmentManager(), dataTamu_bottomSheet.getTag());

    }

    @Override
    public void onDataTamuPass(String nama, String titel_str, int penumpangKe_n) {
        namaPassenger.set(penumpangKe_n-1, nama);
        titel.set(penumpangKe_n-1, titel_str);

        ArrayofPenumpangMaps.clear();

        for(int i = 0; i<jumlahTamu_int; i++) {
            dataPenumpangMap = new HashMap<>();
            dataPenumpangMap.put("namaPenumpang", namaPassenger.get(i));
            dataPenumpangMap.put("titel", titel.get(i));



            ArrayofPenumpangMaps.add(i, dataPenumpangMap);
        }
        recyclerAdapterPenumpangList.notifyDataSetChanged();




    }

    @Override
    public void OnDataSpecialRequest(int bebasAsapRokok_req, int kamarTersambung_req, String waktuCekin, String waktuCekout, String tipeRanjang, String catatanLainnya) {
        bebasAsapRokok = bebasAsapRokok_req;
        kamarTersambung = kamarTersambung_req;
//        waktuCekin_req = waktuCekin;
//        waktuCekOut_req = waktuCekout;
        tipeRanjang_req = tipeRanjang;
        catatanLainnya_req = catatanLainnya;



        if (bebasAsapRokok_req == 1){
            permintaanKhusus += "Bebas asap rokok";
        }

        if (kamarTersambung_req == 1){
            permintaanKhusus += ", Kamar tersambung";
        }

        if (!tipeRanjang.matches("")) {
            permintaanKhusus += "Tipe ranjang: " + tipeRanjang_req;
        }

        if (!catatanLainnya.matches("")){
            permintaanKhusus += " || Catatan lainnya: " + catatanLainnya;
        }

    }
}


