package com.example.projectpacto.roundtrip;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectpacto.BookingActivity;
import com.example.projectpacto.DataPenumpang;
import com.example.projectpacto.PlaneOrderActivity2;
import com.example.projectpacto.R;
import com.example.projectpacto.RecyclerAdapterBagasi;
import com.example.projectpacto.RecyclerAdapterPenumpangList;
import com.example.projectpacto.TambahanBagasiBottomSheet;
import com.example.projectpacto.databinding.ActivityPlaneOrder3Binding;
import com.example.projectpacto.databinding.ActivityPlaneOrder3PpBinding;
import com.example.projectpacto.databinding.ActivityPlaneOrderActivity3PulangPergiBinding;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PlaneOrderActivity3_pulangPergi extends AppCompatActivity implements DataPenumpang.OnDataPassenger, RecyclerAdapterPenumpangList.AddPassengerDetail, TambahanBagasiBottomSheet.OnDataBagasi {
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

    ArrayList<String> tambahan_kg;
    ArrayList<String> harga_tambahan;
    ArrayList<String> tambahan_kg_pulang;
    ArrayList<String> harga_tambahan_pulang;

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


        bundle = getIntent().getBundleExtra("bundle");
        //data pergi
        harga_str =bundle.getString("harga");
        kotaAsal_str = bundle.getString("kotaAsal");
        kotaTujuan_str = bundle.getString("kotaTujuan");
        tanggalBerangkat_str = bundle.getStringArrayList("tanggalBerangkat").get(0);
        waktuBerangkat_str= bundle.getStringArrayList("waktuBerangkat").get(0);
        bandaraAsal_str= bundle.getStringArrayList("bandaraAsal").get(0);
        logoMaskapai_int =bundle.getIntegerArrayList("logoMaskapai").get(0);
        namaMaskapai_str= bundle.getStringArrayList("namaMaskapai").get(0);
        kelasPesawat_str= bundle.getString("kelasPesawat");;
        tanggalDatang_str= bundle.getStringArrayList("tanggalDatang").get(0);
        waktuDatang_str= bundle.getStringArrayList("waktuDatang").get(0);
        bandaraTujuan_str= bundle.getStringArrayList("bandaraTujuan").get(0);
        jmlDewasa_str= bundle.getString("jmlDewasa");;
        jmlAnak_str= bundle.getString("jmlAnak"); ;
        jmlBalita_str= bundle.getString("jmlBalita");;
        keberangkatan = bundle.getString("keberangkatan");
        kedatangan = bundle.getString("kedatangan");
        tanggal = bundle.getString("tanggal");
        penumpang = bundle.getString("penumpang");
        kota_keberangkatan = bundle.getString("kota_keberangkatan");
        kota_kedatangan = bundle.getString("kota_kedatangan");
        bandara_keberangktan_raw = bundle.getString("bandara_keberangkatan");
        bandara_kedatangan_raw =  bundle.getString("bandara_kedatangan");

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

        keberangkatan_pulang = bundle.getString("keberangkatan_pulang");
        kedatangan_pulang = bundle.getString("kedatangan_pulang");
        tanggal_pulang = bundle.getString("tanggal_pulang");
        penumpang_pulang = bundle.getString("penumpang_pulang");
        kota_kedatangan_pulang = bundle.getString("kota_kedatangan_pulang");
        kota_keberangkatan_pulang = bundle.getString("kota_keberangkatan_pulang");
        bandara_keberangktan_raw_pulang = bundle.getString("bandara_keberangktan_raw_pulang");
        bandara_kedatangan_raw_pulang = bundle.getString("bandara_kedatangan_raw_pulang");
        rincianPenumpang_pulang = bundle.getString("rincianPenumpang_pulang");


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
        tambahan_kg_pulang = new ArrayList<>();
        harga_tambahan_pulang = new ArrayList<>();

        ArrayofPenumpangMaps = new ArrayList<>();



        for (int i = 1; i < jmlPenumpang+1 ; i ++) {
            namaPassenger.add("Penumpang " + i);
            titel.add("");
            tglLahir.add("");
            NIKatauPaspor.add("");
            kewarganegaraan.add("");
            tambahan_kg.add("Bagasi +0 kg");
            harga_tambahan.add("IDR 0");

            dataPenumpangMap = new HashMap<>();
            dataPenumpangMap.put("namaPenumpang", namaPassenger.get(i-1));
            dataPenumpangMap.put("titel", titel.get(i-1));
            dataPenumpangMap.put("tglLahir", tglLahir.get(i-1));
            dataPenumpangMap.put("NIKatauPaspor", NIKatauPaspor.get(i-1));
            dataPenumpangMap.put("kewarganegaraan", kewarganegaraan.get(i-1));
            dataPenumpangMap.put("tambahan_kg", tambahan_kg.get(i-1));
            dataPenumpangMap.put("harga_tambahan", harga_tambahan.get(i-1));

            ArrayofPenumpangMaps.add(dataPenumpangMap);
            Log.i("ArrayofPenumpangMaps"+i, "" + ArrayofPenumpangMaps);

        }

        //EDIT TAMBAHAN BAGASI
        binding.tambahFasilitasEkstraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                bundle.putStringArrayList("namaList", namaPassenger);
//                bundle.putStringArrayList("tambahan_kg", tambahan_kg);
//                bundle.putStringArrayList("harga_tambahan", harga_tambahan);
//                TambahanBagasiBottomSheet tambahanBagasiBottomSheet = new TambahanBagasiBottomSheet();
//                tambahanBagasiBottomSheet.setArguments(bundle);
//                tambahanBagasiBottomSheet.show(getSupportFragmentManager(), tambahanBagasiBottomSheet.getTag());
                bagasi_bottomsheet_redesign bagasi_bottomsheet_redesign = new bagasi_bottomsheet_redesign();
                bagasi_bottomsheet_redesign.show(getSupportFragmentManager(), bagasi_bottomsheet_redesign.getTag());


            }
        });

        //TAMBAHAN BAGASI
        binding.tambahanBagasiRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("namaList", namaPassenger);
                bundle.putStringArrayList("tambahan_kg", tambahan_kg);
                bundle.putStringArrayList("harga_tambahan", harga_tambahan);
                TambahanBagasiBottomSheet tambahanBagasiBottomSheet = new TambahanBagasiBottomSheet();
                tambahanBagasiBottomSheet.setArguments(bundle);
                tambahanBagasiBottomSheet.show(getSupportFragmentManager(), tambahanBagasiBottomSheet.getTag());

            }
        });


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

        binding.pesanButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
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


                Locale lokal = new Locale("id", "ID");
                try {
                    Date date1 = new SimpleDateFormat("E, dd MMM yyyy", lokal).parse(tanggalBerangkat_str);
                    waktuArsip = date1.getTime() + 86400000;
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }


                PenumpangData penumpangData = new PenumpangData(false, true, waktuArsip, expTime_long, harga_str, logoMaskapai_int, tanggalBerangkat_str, rincianPenumpang, kotaAsal_str, kotaTujuan_str, "Pesawat", timestamp, bandaraAsal_str, bandaraTujuan_str, "B0OK1NGC0D3", "K0D3P3N3RB4N94N", namaMaskapai_str, "As'ad AlBalad", "17381738", ArrayofPenumpangMaps, "081317381738", "Belum bayar", "5E8dHyQfzYeu1wBvwjxNr8EUl7J3", waktuBerangkat_str, waktuDatang_str);
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

    public class PenumpangData {

        boolean pulangPergi;
        String kotaAsal;
        String kotaTujuan;
        String rincianPenumpang;
        String tanggalBerangkat;
        int logoMaskapai;
        Long expTime;
        Long waktuArsip;

        private boolean ongoing;
        private String hargaTotal;
        private String bandaraAsal;
        private String bandara_kedatangan;
        private String bookingCode;
        private String kodePenerbangan;
        private String namaMaskapai;
        private String namaPemesan;
        private String orderNumber;
        private List<Map<String, String>> penumpang;
        private String phoneNumber;
        private String status;
        private String userID;
        private String waktuBerangkat;
        private String waktuDatang;
        private String tipePesanan;
        private FieldValue timeStampPesanan;

        public PenumpangData(){

        }

        public PenumpangData(boolean pulangPergi, boolean ongoing, Long waktuArsip, Long expTime, String hargaTotal, int logoMaskapai, String tanggalBerangkat, String rincianPenumpang, String kotaAsal, String kotaTujuan, String tipePesanan, FieldValue timeStampPesanan, String bandaraAsal, String bandara_kedatangan, String bookingCode, String kodePenerbangan, String namaMaskapai, String namaPemesan, String orderNumber, List<Map<String, String>> penumpang, String phoneNumber, String status, String userID, String waktuBerangkat, String waktuDatang) {
            this.bandaraAsal = bandaraAsal;
            this.bandara_kedatangan = bandara_kedatangan;
            this.bookingCode = bookingCode;
            this.kodePenerbangan = kodePenerbangan;
            this.namaMaskapai = namaMaskapai;
            this.namaPemesan = namaPemesan;
            this.orderNumber = orderNumber;
            this.penumpang = penumpang;
            this.phoneNumber = phoneNumber;
            this.status = status;
            this.userID = userID;
            this.waktuBerangkat = waktuBerangkat;
            this.waktuDatang = waktuDatang;
            this.tipePesanan = tipePesanan;
            this.timeStampPesanan = timeStampPesanan;
            this.kotaAsal = kotaAsal;
            this.kotaTujuan = kotaTujuan;
            this.rincianPenumpang = rincianPenumpang;
            this.tanggalBerangkat = tanggalBerangkat;
            this.logoMaskapai = logoMaskapai;
            this.hargaTotal = hargaTotal;
            this.expTime = expTime;
            this.waktuArsip = waktuArsip;
            this.ongoing = ongoing;
            this.pulangPergi = pulangPergi;
        }

        public boolean isPulangPergi() {
            return pulangPergi;
        }

        public void setPulangPergi(boolean pulangPergi) {
            this.pulangPergi = pulangPergi;
        }

        public boolean isOngoing() {
            return ongoing;
        }

        public void setOngoing(boolean ongoing) {
            this.ongoing = ongoing;
        }

        public Long getWaktuArsip() {
            return waktuArsip;
        }

        public void setWaktuArsip(Long waktuArsip) {
            this.waktuArsip = waktuArsip;
        }

        public Long getExpTime() {
            return expTime;
        }

        public void setExpTime(Long expTime) {
            this.expTime = expTime;
        }

        public String getHargaTotal() {
            return hargaTotal;
        }

        public void setHargaTotal(String hargaTotal) {
            this.hargaTotal = hargaTotal;
        }

        public int getLogoMaskapai() {
            return logoMaskapai;
        }

        public void setLogoMaskapai(int logoMaskapai) {
            this.logoMaskapai = logoMaskapai;
        }

        public String getTanggalBerangkat() {
            return tanggalBerangkat;
        }

        public void setTanggalBerangkat(String tanggalBerangkat) {
            this.tanggalBerangkat = tanggalBerangkat;
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

        public String getBandaraAsal() {
            return bandaraAsal;
        }

        public void setBandaraAsal(String bandaraAsal) {
            this.bandaraAsal = bandaraAsal;
        }

        public String getBandara_kedatangan() {
            return bandara_kedatangan;
        }

        public void setBandara_kedatangan(String bandara_kedatangan) {
            this.bandara_kedatangan = bandara_kedatangan;
        }

        public String getBookingCode() {
            return bookingCode;
        }

        public void setBookingCode(String bookingCode) {
            this.bookingCode = bookingCode;
        }

        public String getKodePenerbangan() {
            return kodePenerbangan;
        }

        public void setKodePenerbangan(String kodePenerbangan) {
            this.kodePenerbangan = kodePenerbangan;
        }

        public String getNamaMaskapai() {
            return namaMaskapai;
        }

        public void setNamaMaskapai(String namaMaskapai) {
            this.namaMaskapai = namaMaskapai;
        }

        public String getNamaPemesan() {
            return namaPemesan;
        }

        public void setNamaPemesan(String namaPemesan) {
            this.namaPemesan = namaPemesan;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public List<Map<String, String>> getPenumpang() {
            return penumpang;
        }

        public void setPenumpang(List<Map<String, String>> penumpang) {
            this.penumpang = penumpang;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getWaktuBerangkat() {
            return waktuBerangkat;
        }

        public void setWaktuBerangkat(String waktuBerangkat) {
            this.waktuBerangkat = waktuBerangkat;
        }

        public String getWaktuDatang() {
            return waktuDatang;
        }

        public void setWaktuDatang(String waktuDatang) {
            this.waktuDatang = waktuDatang;
        }
    }
}