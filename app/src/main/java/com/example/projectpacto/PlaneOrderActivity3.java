package com.example.projectpacto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.projectpacto.databinding.ActivityPlaneOrder2Binding;
import com.example.projectpacto.databinding.ActivityPlaneOrder3Binding;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class PlaneOrderActivity3 extends AppCompatActivity implements DataPenumpang.OnDataPassenger, RecyclerAdapterPenumpangList.AddPassengerDetail, TambahanBagasiBottomSheet.OnDataBagasi {
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
    int jmlPenumpang;

    String keberangkatan;
    String kedatangan;
    String tanggal;
    String penumpang;
    String kota_kedatangan;
    String kota_keberangkatan;
    String bandara_keberangktan_raw;
    String bandara_kedatangan_raw;

    ArrayList<String> namaPassenger;
    ArrayList<String> tglLahir;
    ArrayList<String> titel;
    ArrayList<String> kewarganegaraan;
    ArrayList<String> NIKatauPaspor;

    ArrayList<String> tambahan_kg;
    ArrayList<String> harga_tambahan;

    List<Map<String, String>> ArrayofPenumpangMaps;
    Map<String, String> dataPenumpangMap;

    FirebaseFirestore fs;


    RecyclerAdapterPenumpangList recyclerAdapterPenumpangList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaneOrder3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        fs = FirebaseFirestore.getInstance();


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

        keberangkatan = bundle.getString("keberangkatan");
        kedatangan = bundle.getString("kedatangan");
        tanggal = bundle.getString("tanggal");
        penumpang = bundle.getString("penumpang");
        kota_keberangkatan = bundle.getString("kota_keberangkatan");
        kota_kedatangan = bundle.getString("kota_kedatangan");
        bandara_keberangktan_raw = bundle.getString("bandara_keberangkatan");
        bandara_kedatangan_raw =  bundle.getString("bandara_kedatangan");


        jmlPenumpang = Integer.parseInt(jmlDewasa_str) +  Integer.parseInt(jmlAnak_str) + Integer.parseInt(jmlBalita_str);

        namaPassenger = new ArrayList<>();

        tglLahir = new ArrayList<>();
        titel = new ArrayList<>();
        kewarganegaraan = new ArrayList<>();
        NIKatauPaspor = new ArrayList<>();
        tambahan_kg = new ArrayList<>();
        harga_tambahan = new ArrayList<>();

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
        binding.editFasilitasEkstra.setOnClickListener(new View.OnClickListener() {
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
        binding.kotaTujuan.setText(kotaTujuan_str);
        binding.tanggalBerangkat.setText(tanggalBerangkat_str);
        binding.tanggalDatang.setText(tanggalDatang_str);
        binding.logoMaskapai.setImageResource(logoMaskapai_int);
        binding.namaMaskapai.setText(namaMaskapai_str);

        String rincianPenumpang = "Dewasa ("+jmlDewasa_str+"x)";
        if (!jmlAnak_str.matches("0")){
            rincianPenumpang = rincianPenumpang + ", Anak ("+jmlAnak_str+"x)";
        }


        if (!jmlBalita_str.matches("0")){
            rincianPenumpang = rincianPenumpang + ", Balita ("+jmlBalita_str+"x)";
        }


        binding.rincianPenumpang.setText(rincianPenumpang);
        binding.harga.setText(harga_str);

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
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BookingActivity.class);
                bundle.putString("status", "Belum Bayar");
                bundle.putString("tipePesanan", "Pesawat");
                bundle.putString("bookingCode", "AS2831");
                bundle.putString("kodePenerbangan","LA9230" );


                FieldValue timestamp = FieldValue.serverTimestamp();

                PenumpangData penumpangData = new PenumpangData("pesawat", timestamp, bandaraAsal_str, bandaraTujuan_str, "B0OK1NGC0D3", "K0D3P3N3RB4N94N", namaMaskapai_str, "As'ad AlBalad", "17381738", ArrayofPenumpangMaps, "081317381738", "Belum bayar", "5E8dHyQfzYeu1wBvwjxNr8EUl7J3", waktuBerangkat_str, waktuDatang_str);
                fs.collection("bookingHistoryPesawat").add(penumpangData);



                fs.collection("bookingHistoryPesawat").document("test1").set(bundle);
//                fs.collection("bookingHistoryPesawat").document("test1").set(map);
                intent.putExtra("bundle", bundle);
                startActivity(intent);





            }
        });


    }

    @Override
    public void onDataPass(String nama_str, String titel_str, String tglLahir_str, String kewarganegaraan_str, String nikAtauPaspor_str, int penumpangKe_n) {


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

        public PenumpangData(String tipePesanan, FieldValue timeStampPesanan, String bandaraAsal, String bandara_kedatangan, String bookingCode, String kodePenerbangan, String namaMaskapai, String namaPemesan, String orderNumber, List<Map<String, String>> penumpang, String phoneNumber, String status, String userID, String waktuBerangkat, String waktuDatang) {
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