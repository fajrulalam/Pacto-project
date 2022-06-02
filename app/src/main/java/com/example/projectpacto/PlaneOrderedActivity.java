package com.example.projectpacto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.projectpacto.databinding.ActivityPlaneIssuingBinding;
import com.example.projectpacto.databinding.ActivityPlaneOrderedBinding;
import com.example.projectpacto.roundtrip.DetailHarga_BottemSheet_PulangPergi;
import com.example.projectpacto.roundtrip.PlaneIssuingActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PlaneOrderedActivity extends AppCompatActivity {

    ActivityPlaneOrderedBinding binding;
    String documentID;
    FirebaseFirestore fs;
    String status;

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
    String grand_total;

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
    ArrayList<String> bookingCode_pergi;
    String tanggalPulang;
    String kotaAsal;
    String kotaTujuan;


    ArrayList NIKatauPaspor;
    ArrayList harga_tambahan;
    ArrayList harga_tambahan_pulang;
    ArrayList kewarganegaraan;
    ArrayList namaPenumpang;
    ArrayList tambahan_kg;
    ArrayList tambahan_kg_pulang;
    ArrayList tglLahir;
    ArrayList titel;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaneOrderedBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        NIKatauPaspor = new ArrayList();
        harga_tambahan = new ArrayList();
        harga_tambahan_pulang = new ArrayList();
        kewarganegaraan = new ArrayList();
        namaPenumpang = new ArrayList();
        tambahan_kg = new ArrayList();
        tambahan_kg_pulang = new ArrayList();
        tglLahir = new ArrayList();
        titel = new ArrayList();


        documentID = getIntent().getStringExtra("documentID");
        fs = FirebaseFirestore.getInstance();
        Log.i("DocumentID FormIssuing", documentID);


        fs.collection("bookingHistory").document(documentID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot != null) {
                    Map<String, Object> map = (Map<String, Object>) documentSnapshot.getData();
                    //adjust the color according to the status
                    status = map.get("status").toString();
                    adjustStatus(status);


                    //Primary keys
                    bookingCode_pergi = (ArrayList<String>) map.get("bookingCode_pergi");


                    //Penumpang
                    penumpang = (List<Map<String, String>>) map.get("penumpang");
                    Log.i("PENUMPANG", penumpang +"penumpang");
                    rincianPenumpang = (String) map.get("rincianPenumpang");
                    jmlDewasa = (String) map.get("jmlDewasa");
                    jmlAnak = (String) map.get("jmlAnak");
                    jmlBalita = (String) map.get("jmlBalita");


                    //harga
                    harga_dewasa = (String) map.get("harga_dewasa");
                    harga_balita = (String) map.get("harga_balita");
                    harga_dewasa_pulang = (String) map.get("harga_dewasa_pulang");
                    harga_balita_pulang = (String) map.get("harga_balita_pulang");
                    harga_total_pergi = (String) map.get("harga_total_pergi");
                    harga_total_pulang = (String) map.get("harga_total_pulang");
                    grand_total = (String) map.get("grand_total");

                    //Pergi
                    tanggalBerangkat_ArrayList = (ArrayList<String>) map.get("tanggalBerangkat_ArrayList");
                    waktuBerangkat_ArrayList = (ArrayList<String>) map.get("waktuBerangkat_ArrayList");
                    bandaraAsal_ArrayList = (ArrayList<String>) map.get("bandaraAsal_ArrayList");
                    logoMaskapai_ArrayList = (ArrayList<Integer>) map.get("logoMaskapai_ArrayList");
                    namaMaskapai_ArrayList = (ArrayList<String>) map.get("namaMaskapai_ArrayList");
                    kodePenerbangan_ArrayList = (ArrayList<String>) map.get("kodePenerbangan_ArrayList");
                    kelasPesawat_ArrayList = (ArrayList<String>) map.get("kelasPesawat_ArrayList");
                    tanggalDatang_ArrayList = (ArrayList<String>) map.get("tanggalDatang_ArrayList");
                    waktuDatang_ArrayList = (ArrayList<String>) map.get("waktuDatang_ArrayList");
                    bandaraTujuan_ArrayList = (ArrayList<String>) map.get("bandaraTujuan_ArrayList");
                    kabin_ArrayList = (ArrayList<String>) map.get("kabin_ArrayList");
                    bagasi_ArrayList = (ArrayList<String>) map.get("bagasi_ArrayList");
                    booleanMakan_ArrayList = (ArrayList<Integer>) map.get("booleanMakan_ArrayList");
                    keteranganMakan_ArrayList = (ArrayList<String>) map.get("keteranganMakan_ArrayList");
                    modelPesawat_ArrayList = (ArrayList<String>) map.get("modelPesawat_ArrayList");
                    durasi_ArrayList = (ArrayList<String>) map.get("durasi_ArrayList");
                    terminalBerangkat = (ArrayList<String>) map.get("terminalBerangkat");
                    terminalDatang = (ArrayList<String>) map.get("terminalDatang");
                    tanggalPulang = (String) map.get("tanggalPulang");
                    kotaAsal = (String) map.get("kotaAsal");
                    kotaTujuan = (String) map.get("kotaTujuan");
                    SelectedTicketBottomSheetFragment_v2.RecyclerAdapterFlightRoute recyclerAdapterFlightRoute = new SelectedTicketBottomSheetFragment_v2.RecyclerAdapterFlightRoute(tanggalBerangkat_ArrayList, waktuBerangkat_ArrayList,  bandaraAsal_ArrayList, logoMaskapai_ArrayList,  namaMaskapai_ArrayList,  kodePenerbangan_ArrayList,  kabin_ArrayList,  bagasi_ArrayList,  booleanMakan_ArrayList, keteranganMakan_ArrayList, modelPesawat_ArrayList, kelasPesawat_ArrayList,  tanggalDatang_ArrayList, waktuDatang_ArrayList, bandaraTujuan_ArrayList);
                    binding.DetailRuteRecyclerView.setAdapter(recyclerAdapterFlightRoute);

                    for (int i = 0; i < penumpang.size(); i++) {
                        Map<String, String> penumpangMap = penumpang.get(i);
                        NIKatauPaspor.add(penumpangMap.get("NIKatauPaspor"));
                        harga_tambahan.add(penumpangMap.get("harga_tambahan"));
                        harga_tambahan_pulang.add(penumpangMap.get("harga_tambahan_pulang"));
                        kewarganegaraan.add(penumpangMap.get("kewarganegaraan"));
                        namaPenumpang.add(penumpangMap.get("namaPenumpang"));
                        tambahan_kg.add(penumpangMap.get("tambahan_kg"));
                        tambahan_kg_pulang.add(penumpangMap.get("tambahan_kg_pulang"));
                        tglLahir.add(penumpangMap.get("tglLahir"));
                        titel.add(penumpangMap.get("titel"));

                    }
                    binding.harga.setText(grand_total);
                    ArrayList<String> bagasiDefault_pulang = new ArrayList<>();
                    bagasiDefault_pulang.add("kosong");
                    Log.i("NamaPenumpang", namaPenumpang.toString());
                    String kodeBandaraBerangkat = bandaraAsal_ArrayList.get(0).split("\\(")[1].replace(")", "");
                    String kodeBandaraDatang = bandaraTujuan_ArrayList.get(bandaraTujuan_ArrayList.size() -1).split("\\(")[1].replace(")", "");
                    PlaneIssuingActivity.DetailPenumpangRecyclerAdapter detailPenumpangRecyclerAdapter = new PlaneIssuingActivity.DetailPenumpangRecyclerAdapter(status, false, kodeBandaraBerangkat, kodeBandaraDatang, bagasi_ArrayList.get(0), bagasiDefault_pulang.get(0), titel, namaPenumpang, tambahan_kg, tambahan_kg_pulang);
                    binding.DetailPenumpangRecyclerView.setAdapter(detailPenumpangRecyclerAdapter);





                }
            }
        });


        binding.detailHarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("kotaAsal", kotaAsal);
                bundle.putString("kotaTujuan", kotaTujuan);
                bundle.putString("jmlDewasa", jmlDewasa);
                bundle.putString("jmlAnak", jmlAnak);
                bundle.putString("jmlBalita", jmlBalita);
                bundle.putStringArrayList("harga_tambahan", harga_tambahan);
                bundle.putStringArrayList("tambahan_kg", tambahan_kg);
                bundle.putString("hargaDewasa", harga_dewasa);
                bundle.putString("hargaBalita", harga_balita);
                bundle.putString("harga", grand_total );
                DetailHargaBottomSheet_Pergi detailHargaBottomSheet_pergi = new DetailHargaBottomSheet_Pergi();
                detailHargaBottomSheet_pergi.setArguments(bundle);
                detailHargaBottomSheet_pergi.show(getSupportFragmentManager(), detailHargaBottomSheet_pergi.getTag());
            }
        });

    }

    public void adjustStatus (String status) {
        binding.statusPesanan.setText(status);
        switch (status) {
            case "Belum bayar":
                binding.statusPesanan.setBackgroundTintList(getResources().getColorStateList(R.color.yellow));
                break;
            case "Selesai":
                binding.statusPesanan.setBackgroundTintList(getResources().getColorStateList(R.color.green_success));
                break;
            case "Dibatalkan":
                binding.statusPesanan.setBackgroundTintList(getResources().getColorStateList(R.color.fail));
                break;
            case "Issued":
                binding.statusPesanan.setBackgroundTintList(getResources().getColorStateList(R.color.primary));
                break;

        }
    }
}


