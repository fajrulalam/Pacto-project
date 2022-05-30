package com.example.projectpacto.roundtrip;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import com.example.projectpacto.BookingActivity;
import com.example.projectpacto.MasukkanPIN_Activity;
import com.example.projectpacto.R;
import com.example.projectpacto.RecyclerAdapterBagasi;
import com.example.projectpacto.RecyclerAdapterPenumpangList_IssueForm;
import com.example.projectpacto.databinding.ActivityFormIssuingBinding;
import com.example.projectpacto.databinding.ActivityPlaneIssuingBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PlaneIssuingActivity extends AppCompatActivity {



    ActivityPlaneIssuingBinding binding;
    FirebaseFirestore fs;

    CountDownTimer timer;


    String tipePesanan;
    String status;

    String documentID;

    //Pesawat
    String bandaraAsal;
    Date timeStampPesanan;
    Date expTime;
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
    int logoMaskapai;

    //Hotel
    String namaHotel;
    String tambahanAlamat;
    String tglCek_in;
    String tglCek_out;
    String jumlahMalam;
    List<Map<String, String>> dataTamu;
    String jumlahKamar;
    String namaKamar;
    String fasilitasExtra;

    ArrayList NIKatauPaspor;
    ArrayList harga_tambahan;
    ArrayList kewarganegaraan;
    ArrayList namaPenumpang;
    ArrayList tambahan_kg;
    ArrayList tglLahir;
    ArrayList titel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaneIssuingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        NIKatauPaspor = new ArrayList();
        harga_tambahan = new ArrayList();
        kewarganegaraan = new ArrayList();
        namaPenumpang = new ArrayList();
        tambahan_kg = new ArrayList();
        tglLahir = new ArrayList();
        titel = new ArrayList();

        fs = FirebaseFirestore.getInstance();

        documentID = getIntent().getStringExtra("documentID");
        String tipePesanan_ = getIntent().getStringExtra("tipePesanan");
//        if (tipePesanan_.matches("Hotel")){
//            binding.timer.setVisibility(View.GONE);
//            binding.bagasiTambahan.setText("Permintaan khusus");
//            binding.hargaBagasiTambahan.setText("Permintaan khusus Anda akan tertulis di sini jika ada.");
//        } else if (tipePesanan_.matches("Pesawat")){
//            binding.hotelLinearLayout.setVisibility(View.GONE);
//        }





        Log.i("DocumentID FormIssuing", documentID);

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BookingActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        binding.pesanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MasukkanPIN_Activity.class);
                intent.putExtra("tipePesanan", "Pesawat");
                intent.putExtra("documentID", documentID);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

        fs.collection("bookingHistory").document(documentID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot != null) {
                    Map<String, Object> map = (Map<String, Object>) documentSnapshot.getData();
                    tipePesanan = map.get("tipePesanan").toString();
                    //adjust the color according to the status
                    status = map.get("status").toString();
                    adjustStatus(status);



//                     else if (tipePesanan.matches("Pesawat")) {
                        Date date = new Date();
                        long timeNow = date.getTime();


                        Long expTime_str = Long.parseLong(map.get("expTime").toString());




                        long duration =  (expTime_str  - timeNow);
                        if (duration > 0) {
                            startTimer(duration);
                        } else {
                            binding.minuteTens.setText("0");
                            binding.minuteOnes.setText("0");
                            binding.secondTens.setText("0");
                            binding.secondOnes.setText("0");
                            timeUp();

                        }

                        bandaraAsal = map.get("bandaraAsal").toString();
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
                        try {
                            hargaTotal = map.get("hargaTotal").toString();
                        } catch (Exception e) {
                            hargaTotal = map.get("harga").toString();
                        }
                        logoMaskapai = Integer.parseInt(map.get("logoMaskapai").toString());


                        String kota_dan_bandaraAsal = kotaAsal + " (" + bandaraAsal.split("\\(")[1];
                        String kota_dan_bandaraTujuan = kotaTujuan + " (" + bandara_kedatangan.split("\\(")[1];

                        Log.i("kota_dan_bandaraAsal", kota_dan_bandaraAsal);
                        Log.i("kota_dan_bandaraTujuan", kota_dan_bandaraTujuan);

                        for (int i = 0; i < penumpang.size(); i++) {
                            Map<String, String> penumpangMap = penumpang.get(i);
                            NIKatauPaspor.add(penumpangMap.get("NIKatauPaspor"));
                            harga_tambahan.add(penumpangMap.get("harga_tambahan"));
                            kewarganegaraan.add(penumpangMap.get("kewarganegaraan"));
                            namaPenumpang.add(penumpangMap.get("namaPenumpang"));
                            tambahan_kg.add(penumpangMap.get("tambahan_kg"));
                            tglLahir.add(penumpangMap.get("tglLahir"));
                            titel.add(penumpangMap.get("titel"));

                        }
                        Log.i("NamaPenumpang", namaPenumpang.toString());

//                        binding.kotaAsal.setText(kota_dan_bandaraAsal);
//                        binding.kotaTujuan.setText(kota_dan_bandaraTujuan);
//                        binding.tanggalBerangkat.setText(tanggalBerangkat);
//                        binding.tanggalDatang.setText(waktuBerangkat);
//                        binding.logoMaskapai.setImageResource(logoMaskapai);
//                        binding.namaMaskapai.setText(namaMaskapai);
//                        binding.kodePenerbangan.setText(kodePenerbangan);
//                        binding.rincianPenumpang.setText(rincianPenumpang);
//                        binding.namaPemesan.setText(namaPemesan);
//                        binding.nomorPemesan.setText(phoneNumber);

                        RecyclerAdapterPenumpangList_IssueForm recyclerAdapterPenumpangList = new RecyclerAdapterPenumpangList_IssueForm(namaPenumpang);
                        binding.NamaPenumpangRecycleView.setAdapter(recyclerAdapterPenumpangList);

                        RecyclerAdapterBagasi recyclerAdapterBagasi = new RecyclerAdapterBagasi(tambahan_kg, harga_tambahan);
                        binding.BagasiRecyclerView.setAdapter(recyclerAdapterBagasi);
                        binding.BagasiRecyclerView.setVisibility(View.VISIBLE);
                        binding.tambahanBagasiRelativeLayout.setBackground(getResources().getDrawable(R.drawable.curved__even_less_bg));
                        binding.bagasi.setVisibility(View.GONE);
                        binding.bagasiTambahan.setVisibility(View.GONE);
                        binding.hargaBagasiTambahan.setVisibility(View.GONE);


                    }


                }

//            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        binding.batalkanPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("Yakin batalkan pesanan?")
                        .setMessage("\n")
                        .setPositiveButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                fs.collection("bookingHistory").document(documentID).update("ongoing", false);
                                fs.collection("bookingHistory").document(
                                        documentID).update("status", "Dibatalkan").addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Intent intent = new Intent(getApplicationContext(), BookingActivity.class);
                                        startActivity(intent);
                                        overridePendingTransition(0, 0);
                                        Log.i("Success", "The data was successfully sent");

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.i("Fail", "The data was not sent");
                                    }
                                });
                            }
                        }).show();

            }
        });




    }

    public void adjustStatus(String status){
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
                binding.batalkanPesanan.setVisibility(View.GONE);
                binding.pesanButton.setVisibility(View.GONE);
                break;
            case "Issued":
                binding.statusPesanan.setBackgroundTintList(getResources().getColorStateList(R.color.primary));
                binding.batalkanPesanan.setVisibility(View.GONE);
                binding.pesanButton.setVisibility(View.GONE);
                break;

        }
    }

    public void startTimer(long duration){
        timer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long l) {
                int minuteLeft = (int) l / 1000 / 60;
                int secondsLeft = (int) l / 1000 % 60;

                String timeleftFormatted = String.format("%02d:%02d", minuteLeft, secondsLeft);




                String minuteTens = timeleftFormatted.substring(0,1);
                String minuteOnes = timeleftFormatted.substring(1,2);
                String secondsTens = timeleftFormatted.substring(3,4);
                String secondsOnes = timeleftFormatted.substring(4);

                binding.minuteTens.setText(minuteTens);
                binding.minuteOnes.setText(minuteOnes);
                binding.secondTens.setText(secondsTens);
                binding.secondOnes.setText(secondsOnes);
            }

            @Override
            public void onFinish() {
                timeUp();





            }
        };
        timer.start();
    }

    public void timeUp(){
        fs.collection("bookingHistory").document(documentID).update("status", "Dibatalkan");
        fs.collection("bookingHistory").document(documentID).update("ongoing", false);
        binding.statusPesanan.setBackgroundTintList(getResources().getColorStateList(R.color.fail));
        binding.statusPesanan.setText("Dibatalkan");

        binding.batalkanPesanan.setVisibility(View.GONE);
        binding.pesanButton.setVisibility(View.GONE);

        binding.minuteTens.setText("0");
        binding.minuteOnes.setText("0");
        binding.secondTens.setText("0");
        binding.secondOnes.setText("0");
    }






}