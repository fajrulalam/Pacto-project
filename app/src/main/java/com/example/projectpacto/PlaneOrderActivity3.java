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
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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

        ArrayofPenumpangMaps = new List<Map<String, String>>() {
            @Override
            public int size() {
                return jmlPenumpang;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(@Nullable Object o) {
                return false;
            }

            @NonNull
            @Override
            public Iterator<Map<String, String>> iterator() {
                return null;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(@NonNull T[] ts) {
                return null;
            }

            @Override
            public boolean add(Map<String, String> stringStringMap) {
                return false;
            }

            @Override
            public boolean remove(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean addAll(@NonNull Collection<? extends Map<String, String>> collection) {
                return false;
            }

            @Override
            public boolean addAll(int i, @NonNull Collection<? extends Map<String, String>> collection) {
                return false;
            }

            @Override
            public boolean removeAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(@NonNull Collection<?> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public Map<String, String> get(int i) {
                return null;
            }

            @Override
            public Map<String, String> set(int i, Map<String, String> stringStringMap) {
                return null;
            }

            @Override
            public void add(int i, Map<String, String> stringStringMap) {

            }

            @Override
            public Map<String, String> remove(int i) {
                return null;
            }

            @Override
            public int indexOf(@Nullable Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(@Nullable Object o) {
                return 0;
            }

            @NonNull
            @Override
            public ListIterator<Map<String, String>> listIterator() {
                return null;
            }

            @NonNull
            @Override
            public ListIterator<Map<String, String>> listIterator(int i) {
                return null;
            }

            @NonNull
            @Override
            public List<Map<String, String>> subList(int i, int i1) {
                return null;
            }
        };
        dataPenumpangMap = new Map<String, String>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean containsKey(@Nullable Object o) {
                return false;
            }

            @Override
            public boolean containsValue(@Nullable Object o) {
                return false;
            }

            @Nullable
            @Override
            public String get(@Nullable Object o) {
                return null;
            }

            @Nullable
            @Override
            public String put(String s, String s2) {
                return null;
            }

            @Nullable
            @Override
            public String remove(@Nullable Object o) {
                return null;
            }

            @Override
            public void putAll(@NonNull Map<? extends String, ? extends String> map) {

            }

            @Override
            public void clear() {

            }

            @NonNull
            @Override
            public Set<String> keySet() {
                return null;
            }

            @NonNull
            @Override
            public Collection<String> values() {
                return null;
            }

            @NonNull
            @Override
            public Set<Entry<String, String>> entrySet() {
                return null;
            }
        };

        for (int i = 1; i < jmlPenumpang+1 ; i ++) {
            namaPassenger.add("Penumpang " + i);
            titel.add("");
            tglLahir.add("");
            NIKatauPaspor.add("");
            kewarganegaraan.add("");
            tambahan_kg.add("Bagasi +0 kg");
            harga_tambahan.add("IDR 0");


            dataPenumpangMap.put("namaPenumpang", namaPassenger.get(i-1));
            dataPenumpangMap.put("titel", titel.get(i-1));
            dataPenumpangMap.put("tglLahir", tglLahir.get(i-1));
            dataPenumpangMap.put("NIKatauPaspor", NIKatauPaspor.get(i-1));
            dataPenumpangMap.put("kewarganegaraan", kewarganegaraan.get(i-1));
            dataPenumpangMap.put("tambahan_kg", tambahan_kg.get(i-1));
            dataPenumpangMap.put("harga_tambahan", harga_tambahan.get(i-1));

            ArrayofPenumpangMaps.set(i-1, dataPenumpangMap);
            Log.i("ArrayofPenumpangMaps"+i, "" + dataPenumpangMap);

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

                Map map = new Map() {
                    @Override
                    public int size() {
                        return 0;
                    }

                    @Override
                    public boolean isEmpty() {
                        return false;
                    }

                    @Override
                    public boolean containsKey(@Nullable Object o) {
                        return false;
                    }

                    @Override
                    public boolean containsValue(@Nullable Object o) {
                        return false;
                    }

                    @Nullable
                    @Override
                    public Object get(@Nullable Object o) {
                        return null;
                    }

                    @Nullable
                    @Override
                    public Object put(Object o, Object o2) {
                        return null;
                    }

                    @Nullable
                    @Override
                    public Object remove(@Nullable Object o) {
                        return null;
                    }

                    @Override
                    public void putAll(@NonNull Map map) {

                    }

                    @Override
                    public void clear() {

                    }

                    @NonNull
                    @Override
                    public Set keySet() {
                        return null;
                    }

                    @NonNull
                    @Override
                    public Collection values() {
                        return null;
                    }

                    @NonNull
                    @Override
                    public Set<Entry> entrySet() {
                        return null;
                    }
                };
                map.put("penumpangData", ArrayofPenumpangMaps);



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


        dataPenumpangMap.put("namaPenumpang", namaPassenger.get(penumpangKe_n-1));
        dataPenumpangMap.put("titel", titel.get(penumpangKe_n-1));
        dataPenumpangMap.put("tglLahir", tglLahir.get(penumpangKe_n-1));
        dataPenumpangMap.put("NIKatauPaspor", NIKatauPaspor.get(penumpangKe_n-1));
        dataPenumpangMap.put("kewarganegaraan", kewarganegaraan.get(penumpangKe_n-1));


        ArrayofPenumpangMaps.set(penumpangKe_n-1, dataPenumpangMap);



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

        for (int i = 0; i < tambahan_kg.size(); i++){

            dataPenumpangMap.put("tambahan_kg", tambahan_kg.get(i));
            dataPenumpangMap.put("harga_tambahan", hargaTambahanBagasi.get(i));

            ArrayofPenumpangMaps.set(i, dataPenumpangMap);

        }

        RecyclerAdapterBagasi recyclerAdapterBagasi = new RecyclerAdapterBagasi(tambahan_kg, hargaTambahanBagasi);
        binding.BagasiRecyclerView.setAdapter(recyclerAdapterBagasi);
        binding.BagasiRecyclerView.setVisibility(View.VISIBLE);
        binding.tambahanBagasiRelativeLayout.setBackground(getResources().getDrawable(R.drawable.curved__even_less_bg));
        binding.bagasi.setVisibility(View.GONE);
        binding.bagasiTambahan.setVisibility(View.GONE);
        binding.hargaBagasiTambahan.setVisibility(View.GONE);
    }
}