package com.example.projectpacto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.projectpacto.BookingActivity;
import com.example.projectpacto.MasukkanPIN_Activity;
import com.example.projectpacto.R;
import com.example.projectpacto.RecyclerAdapterBagasi;
import com.example.projectpacto.RecyclerAdapterPenumpangList_IssueForm;
import com.example.projectpacto.databinding.ActivityFormIssuingBinding;
import com.example.projectpacto.databinding.ActivityPlaneIssuingBinding;
import com.example.projectpacto.databinding.ActivityPlaneIssuingPergiBinding;
import com.example.projectpacto.roundtrip.DetailHarga_BottemSheet_PulangPergi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.LazyStringArrayList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PlaneIssuingActivityPergi extends AppCompatActivity {



    ActivityPlaneIssuingPergiBinding binding;
    FirebaseFirestore fs;

    CountDownTimer timer;
    List<Map<String, String>> penumpang;
    String grand_total;
    String documentID;

    //Keperluan Activity -- Pergi
    String kotaAsal;
    String kotaTujuan;
    String status;
    String expTime;
    ArrayList<String> namaMaskapai_ArrayList;
    ArrayList<Integer> logoMasakapai_ArrayList;
    ArrayList<String> kodePenerbangan_ArrayList;
    ArrayList<String> bandaraAsal_ArrayList;
    ArrayList<String> terminalBerangkat_ArrayList;
    ArrayList<String> tanggalBerangkat_ArrayList;
    ArrayList<String> waktuBerangkat_ArrayList;
    ArrayList<String> durasi_ArrayList;
    ArrayList<String> bandaraTujuan_ArrayList;
    ArrayList<String> terminalDatang_ArrayList;
    ArrayList<String> tanggalDatang_ArrayList;
    ArrayList<String> tambahan_kg_pulang;
    ArrayList<String> waktuDatangArrayList;
    ArrayList<String> bagasiDefault;
    String harga_balita;
    String harga_dewasa;
    String jmlDewasa;
    String jmlAnak;
    String jmlBalita;
    String harga_total_pergi;






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
        binding = ActivityPlaneIssuingPergiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        NIKatauPaspor = new ArrayList();
        harga_tambahan = new ArrayList();
        kewarganegaraan = new ArrayList();
        namaPenumpang = new ArrayList();
        tambahan_kg = new ArrayList();
        tambahan_kg_pulang = new ArrayList();
        tglLahir = new ArrayList();
        titel = new ArrayList();

        fs = FirebaseFirestore.getInstance();

        documentID = getIntent().getStringExtra("documentID");
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
                    //adjust the color according to the status
                    status = map.get("status").toString();
                    adjustStatus(status);


                    //Setting the timer
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

                    penumpang = (List<Map<String, String>>) map.get("penumpang");

                    //Getting the variables  -- Pergi
                    kotaAsal = map.get("kotaAsal").toString();
                    kotaTujuan = map.get("kotaTujuan").toString();
                    namaMaskapai_ArrayList = ((ArrayList<String>) map.get("namaMaskapai_ArrayList"));
//                    logoMasakapai_ArrayList = ((ArrayList<Integer>) map.get("logoMaskapai_ArrayList"));
                    ArrayList logoMaskapai_obj = (ArrayList)  map.get("logoMaskapai_ArrayList");
                    Long logoMaskapai_long = Long.parseLong(logoMaskapai_obj.get(0).toString());
                    kodePenerbangan_ArrayList = ((ArrayList<String>) map.get("kodePenerbangan_ArrayList"));
                    bandaraAsal_ArrayList = ((ArrayList<String>) map.get("bandaraAsal_ArrayList"));
                    terminalBerangkat_ArrayList = ((ArrayList<String>) map.get("terminalBerangkat"));
                    tanggalBerangkat_ArrayList = ((ArrayList<String>) map.get("tanggalBerangkat_ArrayList"));
                    waktuBerangkat_ArrayList = ((ArrayList<String>) map.get("waktuBerangkat_ArrayList"));
                    durasi_ArrayList = ((ArrayList<String>) map.get("durasi_ArrayList"));
                    bandaraTujuan_ArrayList = ((ArrayList<String>) map.get("bandaraTujuan_ArrayList"));
                    terminalDatang_ArrayList = ((ArrayList<String>) map.get("terminalDatang"));
                    tanggalDatang_ArrayList = ((ArrayList<String>) map.get("tanggalDatang_ArrayList"));
                    waktuDatangArrayList = ((ArrayList<String>) map.get("waktuDatang_ArrayList"));
                    bagasiDefault = ((ArrayList<String>) map.get("bagasi_ArrayList"));
                    harga_balita = (String) map.get("harga_balita");
                    harga_dewasa = (String) map.get("harga_dewasa");
                    jmlDewasa = (String) map.get("jmlDewasa");
                    jmlAnak = (String) map.get("jmlAnak");
                    jmlBalita = (String) map.get("jmlBalita");
                    harga_total_pergi = (String) map.get("harga_total_pergi");
                    int grand_total_int =  (Integer.parseInt(map.get("grand_total").toString()) );
                    grand_total = "IDR " + String.format("%,d", grand_total_int).replace(",", ".");


                    //Getting the variables  -- Pulang
                    kotaAsal = map.get("kotaAsal").toString();
                    kotaTujuan = map.get("kotaTujuan").toString();



                    //Obtaining Penumpang Data
                    for (int i = 0; i < penumpang.size(); i++) {
                        Map<String, String> penumpangMap = penumpang.get(i);
                        NIKatauPaspor.add(penumpangMap.get("NIKatauPaspor"));
                        harga_tambahan.add(penumpangMap.get("harga_tambahan"));
                        kewarganegaraan.add(penumpangMap.get("kewarganegaraan"));
                        namaPenumpang.add(penumpangMap.get("namaPenumpang"));
                        tambahan_kg.add(penumpangMap.get("tambahan_kg"));
                        tambahan_kg_pulang.add("kosong");
                        tglLahir.add(penumpangMap.get("tglLahir"));
                        titel.add(penumpangMap.get("titel"));

                    }
                    Log.i("NamaPenumpang", namaPenumpang.toString());


                    //obtaining transit info
                    int transits = bandaraTujuan_ArrayList.size() - 1;
                    String airportTransitCodes = "";
                    String airportTransitCodes_pulang = "";
                    for (int i = 0; i < bandaraTujuan_ArrayList.size() - 1; i++){
                        String singleAiportCode = bandaraTujuan_ArrayList.get(i).split("\\(")[1].replace(")", "");
                        if (i+1 != bandaraAsal_ArrayList.size() -1){
                            airportTransitCodes = airportTransitCodes + singleAiportCode + ",";
                        } else {
                            airportTransitCodes = airportTransitCodes + singleAiportCode;
                        }
                    }



                    //Binding General Textviews
                    binding.kotaAsal.setText(kotaAsal);
                    binding.kotaTujuan.setText(kotaTujuan);
                    binding.harga.setText(grand_total);

                    //Binding --pergi
                    binding.namaMaskapai.setText(namaMaskapai_ArrayList.get(0));
                    binding.kodePenerbangan.setText(kodePenerbangan_ArrayList.get(0));
                    binding.logoMaskapai.setImageResource(logoMaskapai_long.intValue());
                    binding.bandaraTerminalBerangkat.setText(bandaraAsal_ArrayList.get(0).replace("International Airport", "") + " " + terminalBerangkat_ArrayList.get(0));
                    binding.tanggalWaktuBerangkat.setText(tanggalBerangkat_ArrayList.get(0) + " - " + waktuBerangkat_ArrayList.get(0));
                    binding.durasiJmlTransitTransits.setText(durasi_ArrayList.get(0) + " - " + transits + " transits " + airportTransitCodes);
                    binding.bandaraTerminalDatang.setText(bandaraTujuan_ArrayList.get(bandaraTujuan_ArrayList.size()-1).replace("International Airport", "") + " " + terminalDatang_ArrayList.get(terminalDatang_ArrayList.size() -1));
                    binding.tanggalWaktuDatang.setText(tanggalDatang_ArrayList.get(tanggalDatang_ArrayList.size()-1) + " - " + waktuDatangArrayList.get(waktuDatangArrayList.size()-1));

                    ArrayList<String> bagasiDefault_pulang = new ArrayList<>();
                    bagasiDefault_pulang.add("kosong");
                    bagasiDefault_pulang.add("kosong");
                    String kodeBandaraBerangkat = bandaraAsal_ArrayList.get(0).split("\\(")[1].replace(")", "");
                    String kodeBandaraDatang = bandaraTujuan_ArrayList.get(bandaraTujuan_ArrayList.size() -1).split("\\(")[1].replace(")", "");
                    DetailPenumpangRecyclerAdapter detailPenumpangRecyclerAdapter = new DetailPenumpangRecyclerAdapter(status, true,  kodeBandaraBerangkat, kodeBandaraDatang, bagasiDefault.get(0), bagasiDefault_pulang.get(0), titel, namaPenumpang, tambahan_kg, tambahan_kg_pulang);
                    binding.DetailPenumpangRecyclerView.setAdapter(detailPenumpangRecyclerAdapter);

                }


            }

        });

        //Detail Harga
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
                DetailHargaBottomSheet_Pergi detailHarga_bottemSheet_pulangPergi = new DetailHargaBottomSheet_Pergi();
                detailHarga_bottemSheet_pulangPergi.setArguments(bundle);
                detailHarga_bottemSheet_pulangPergi.show(getSupportFragmentManager(), detailHarga_bottemSheet_pulangPergi.getTag());
            }
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


    public static class DetailPenumpangRecyclerAdapter extends RecyclerView.Adapter<DetailPenumpangRecyclerAdapter.ViewHolder>{

        String status;
        boolean pulangPergi;
        String kode_bandaraAsal;
        String kode_bandaraDatang;
        String bagasi;
        String bagasi_pulang;
        ArrayList<String> titel;
        ArrayList<String> nama;
        ArrayList<String> tambahan_kg;
        ArrayList<String> tambahan_kg_pulang;

        public DetailPenumpangRecyclerAdapter(String status, boolean pulangPergi, String kode_bandaraAsal, String kode_bandaraDatang, String bagasi, String bagasi_pulang, ArrayList<String> titel, ArrayList<String> nama, ArrayList<String> tambahan_kg, ArrayList<String> tambahan_kg_pulang) {
            this.status = status;
            this.pulangPergi = pulangPergi;
            this.kode_bandaraAsal = kode_bandaraAsal;
            this.kode_bandaraDatang = kode_bandaraDatang;
            this.bagasi = bagasi;
            this.bagasi_pulang = bagasi_pulang;
            this.titel = titel;
            this.nama = nama;
            this.tambahan_kg = tambahan_kg;
            this.tambahan_kg_pulang = tambahan_kg_pulang;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.detail_penumpang_fasilitas_pp_singleview, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


            int pcc = position +1;
            String nomor_titel_namaLengkap = pcc + ". " + titel.get(position) + " " + nama.get(position);
            holder.nomor_titel_namaLengkap.setText(nomor_titel_namaLengkap);

            holder.kode_bandaraAsal.setText(kode_bandaraAsal);
            holder.kode_bandaraTujuan.setText(kode_bandaraDatang);

            //balik arah shg bandara asal dan tujuan dibalik
            holder.kode_bandaraAsal_pulang.setText(kode_bandaraDatang);

            holder.bagasiDefault.setText(bagasi);

            holder.bagasiTambahan_kg.setText("+" + tambahan_kg.get(position));


            //Pulang
            if (pulangPergi==true) {
                holder.pulangPergi.setVisibility(View.VISIBLE);
                holder.bagasiDefault_pulang.setText(bagasi_pulang);
                holder.kode_bandaraTujuan_pulang.setText(kode_bandaraAsal);
                holder.bagasiTambahan_kg_pulang.setText("+" + tambahan_kg_pulang.get(position));
            }

            if (status.matches("Issued")){
                holder.nomorKursiLayout.setVisibility(View.VISIBLE);
                holder.nomorKursiLayout_pulang.setVisibility(View.VISIBLE);
                //insert nomor kursi code here....


                //
            }


        }

        @Override
        public int getItemCount() {
            return nama.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView nomor_titel_namaLengkap;
            TextView kode_bandaraAsal;
            TextView kode_bandaraTujuan;
            TextView bagasiDefault;
            TextView bagasiTambahan_kg;
            TextView nomorKursi;
            LinearLayout nomorKursiLayout;
            LinearLayout pulangPergi;

            TextView kode_bandaraAsal_pulang;
            TextView kode_bandaraTujuan_pulang;
            TextView bagasiDefault_pulang;
            TextView bagasiTambahan_kg_pulang;
            TextView nomorKursi_pulang;
            LinearLayout nomorKursiLayout_pulang;



            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                nomor_titel_namaLengkap =  itemView.findViewById(R.id.nomor_titel_namaLengkap);
                pulangPergi = itemView.findViewById(R.id.pulangPergi);

                kode_bandaraAsal =  itemView.findViewById(R.id.kodeBandaraAsal);
                kode_bandaraTujuan =  itemView.findViewById(R.id.kodeBandaraTujuan);
                bagasiDefault =  itemView.findViewById(R.id.bagasiDefault);
                bagasiTambahan_kg =  itemView.findViewById(R.id.bagasiTambahan);
                nomorKursi =  itemView.findViewById(R.id.nomorKursi);
                nomorKursiLayout =  itemView.findViewById(R.id.nomorKursi_layout);

                kode_bandaraAsal_pulang =  itemView.findViewById(R.id.kodeBandaraAsal_pulang);
                kode_bandaraTujuan_pulang =  itemView.findViewById(R.id.kodeBandaraTujuan_pulang);
                bagasiDefault_pulang =  itemView.findViewById(R.id.bagasiDefault_pulang);
                bagasiTambahan_kg_pulang =  itemView.findViewById(R.id.bagasiTambahan_pulang);
                nomorKursi_pulang =  itemView.findViewById(R.id.nomorKursi_pulang);
                nomorKursiLayout_pulang =  itemView.findViewById(R.id.nomorKursi_layout_pulang);


            }
        }
    }






}