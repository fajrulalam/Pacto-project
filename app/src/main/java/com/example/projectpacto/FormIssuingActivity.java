package com.example.projectpacto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.projectpacto.databinding.ActivityFormIssuingBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Map;


public class FormIssuingActivity extends AppCompatActivity {

    ActivityFormIssuingBinding binding;
    FirebaseFirestore fs;


    String tipePesanan;
    String status;

    String bandaraAsal;
    String bandara_kedatangan;
    String kotaAsal;
    String kotaTujuan;
    String tanggalBerangkat;
    String waktuBerangkat;
    String namaMaskapai;
    String kodePenerbangan;
    String rincianPenumpang;
    String namaPemesan;
    String phoneNumber;
    List<Map<String, String>> penumpang;
    String hargaTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormIssuingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        fs = FirebaseFirestore.getInstance();

        String documentID = getIntent().getStringExtra("documentID");

        Log.i("DocumentID FormIssuing", documentID);

        fs.collection("bookingHistory").document(documentID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot != null) {
                    Map<String, Object> map = (Map<String, Object>) documentSnapshot.getData();
                    tipePesanan = map.get("tipePesanan").toString();

                    //adjust the color according to the status
                    status = map.get("status").toString();


                    if (tipePesanan.matches("Hotel")) {
                        Log.i("Tipe Pesanan", "Hotel");


                    } else if (tipePesanan.matches("Pesawat")) {
                        bandaraAsal  = map.get("bandaraAsal").toString();
                        bandara_kedatangan = map.get("bandara_kedatangan").toString();
                        kotaAsal = map.get("kotaAsal").toString();
                        kotaTujuan = map.get("kotaTujuan").toString();
                        tanggalBerangkat = map.get("tanggalBerangkat").toString();
                        waktuBerangkat = map.get("waktuBerangkat").toString();
                        namaMaskapai = map.get("namaMaskapai").toString();
                        kodePenerbangan = map.get("kodePenerbangan").toString();
                        rincianPenumpang = map.get("rincianPenumpang").toString();
                        namaPemesan = map.get("namaPemesan").toString();
                        phoneNumber = map.get("phoneNumber").toString();
                        penumpang = (List<Map<String, String>>) map.get("penumpang");
                        hargaTotal = map.get("hargaTotal").toString();

                        String kota_dan_bandaraAsal = kotaAsal + " (" + bandaraAsal.split("\\(")[1];
                        String kota_dan_bandaraTujuan = kotaTujuan + " (" + bandara_kedatangan.split("\\(")[1];

                        Log.i("kota_dan_bandaraAsal", kota_dan_bandaraAsal);
                        Log.i("kota_dan_bandaraTujuan", kota_dan_bandaraTujuan);


                    }


                }
            }
        });




    }
}