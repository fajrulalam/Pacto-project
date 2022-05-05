package com.example.projectpacto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectpacto.databinding.ActivityHotelOrder4Binding;
import com.example.projectpacto.databinding.ActivityNamaTersimpanBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NamaTersimpanActivity extends AppCompatActivity implements RecyclerAdapterNamaTersimpan.AddPassengerDetail, DataPenumpang.OnDataPassenger {

    ActivityNamaTersimpanBinding binding;

    ArrayList<String> nama_titel;
    ArrayList<String> NIKatauPaspor;
    ArrayList<String> nama;
    ArrayList<String> titel;
    ArrayList<String> tglLahir;
    ArrayList<String> kewarganegaraan;
    ArrayList<String> documentID;
    RecyclerAdapterNamaTersimpan recyclerAdapterNamaTersimpan;
    FirebaseFirestore fs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNamaTersimpanBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        fs = FirebaseFirestore.getInstance();

        nama_titel = new ArrayList<>();
        NIKatauPaspor = new ArrayList<>();
        nama = new ArrayList<>();
        titel = new ArrayList<>();
        tglLahir = new ArrayList<>();
        kewarganegaraan = new ArrayList<>();
        documentID = new ArrayList<>();




        fs.collection("namaTersimpan").whereEqualTo("userID", "5E8dHyQfzYeu1wBvwjxNr8EUl7J3").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots != null) {
                    List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot snapshot : snapshotList) {
                        Map<String, Object> map = (Map<String, Object>) snapshot.getData();
                        String id = snapshot.getId();
                        documentID.add(id);
                        String NIKatauPaspor_str = map.get("NIKatauPaspor").toString();
                        String nama_str = map.get("nama").toString();
                        String titel_str = map.get("titel").toString();
                        String nama_titel_str = nama_str + " (" + titel_str +")";
                        String tglLahir_str = map.get("tglLahir").toString();
                        String kewarganegaraan_str = map.get("kewarganegaraan").toString();

                        nama_titel.add(nama_titel_str);
                        NIKatauPaspor.add(NIKatauPaspor_str);
                        nama.add(nama_str);
                        titel.add(titel_str);
                        tglLahir.add(tglLahir_str);
                        kewarganegaraan.add(kewarganegaraan_str);
                    }
                    recyclerAdapterNamaTersimpan = new RecyclerAdapterNamaTersimpan(nama_titel, NIKatauPaspor, NamaTersimpanActivity.this::addPassengerDetail);
                    binding.recyclerViewNamaTersimpan.setAdapter(recyclerAdapterNamaTersimpan);
                }
            }
        });



        binding.tambahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataPenumpang dataPenumpang = new DataPenumpang();
                Bundle bundle = new Bundle();
                bundle.putString("penumpangKe_n", "");
                bundle.putString("documentID", "");
//                bundle.putInt("index", null);
                bundle.putString("tglLahir_str", "");
                bundle.putString("request", "Create");
                bundle.putString("nama_str", "");
                bundle.putString("kewarganegaraan_str", "");
                bundle.putString("NIKatauPaspor_str", "");
                bundle.putString("titel_str", "");
                dataPenumpang.setArguments(bundle);
                dataPenumpang.show(getSupportFragmentManager(), dataPenumpang.getTag());


            }
        });


        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


    }


    @Override
    public void addPassengerDetail(int penumpangKe_n, String query) {
        int index = penumpangKe_n - 1;
        Log.i("index battle", "tgl Lahir: " + tglLahir.size() + " || index: " + index);

        if (query.matches("edit")) {
            DataPenumpang dataPenumpang = new DataPenumpang();
            Bundle bundle = new Bundle();
            bundle.putString("penumpangKe_n", "");
            bundle.putString("documentID", documentID.get(index));
            bundle.putInt("index", index);
            bundle.putString("tglLahir_str", tglLahir.get(index));
            bundle.putString("request", "Update");
            bundle.putString("nama_str", nama.get(index));
            bundle.putString("kewarganegaraan_str", kewarganegaraan.get(index));
            bundle.putString("NIKatauPaspor_str", NIKatauPaspor.get(index));
            bundle.putString("titel_str", titel.get(index));
            dataPenumpang.setArguments(bundle);
            dataPenumpang.show(getSupportFragmentManager(), dataPenumpang.getTag());
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Yakin hapus nama " + nama.get(index)+"?")
                    .setMessage("\n")
                    .setPositiveButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {


                        }
                    })
                    .setNegativeButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            NIKatauPaspor.remove(index);
                            nama.remove(index);
                            titel.remove(index);
                            tglLahir.remove(index);
                            kewarganegaraan.remove(index);
                            nama_titel.remove(index);
                            recyclerAdapterNamaTersimpan = new RecyclerAdapterNamaTersimpan(nama_titel, NIKatauPaspor, NamaTersimpanActivity.this::addPassengerDetail);
                            binding.recyclerViewNamaTersimpan.setAdapter(recyclerAdapterNamaTersimpan);
                        }
                    }).show();


        }
    }

    @Override
    public void onDataPass(String nama_str, String titel_str, String tglLahir_str, String kewarganegaraan_str, String nikAtauPaspor_str, int penumpangKe_n, String request) {

        NIKatauPaspor.set(penumpangKe_n,nikAtauPaspor_str );
        nama.set(penumpangKe_n,nama_str );
        titel.set(penumpangKe_n,titel_str );
        tglLahir.set(penumpangKe_n,tglLahir_str );
        kewarganegaraan.set(penumpangKe_n,kewarganegaraan_str );

        nama_titel.set(penumpangKe_n, nama.get(penumpangKe_n) + " (" + titel.get(penumpangKe_n) +")");

        recyclerAdapterNamaTersimpan = new RecyclerAdapterNamaTersimpan(nama_titel, NIKatauPaspor, this);
        binding.recyclerViewNamaTersimpan.setAdapter(recyclerAdapterNamaTersimpan);

        if (request.matches("Update")) {
            fs.collection("namaTersimpan").document(documentID.get(penumpangKe_n)).update(
                    "NIKatauPaspor", nikAtauPaspor_str,
                    "titel", titel_str,
                    "nama", nama_str,
                    "tglLahir", tglLahir_str,
                    "kewarganegaraan", kewarganegaraan_str);
        } else if (request.matches("Create")){

        }

        Log.i("Data should be saved", "SAVED");
    }


    public class SimpanNamaBaru {
        String nama_str;
        String titel_str;
        String tglLahir_str;
        String kewarganegaraan_str;
        String nikAtauPaspor_str;
        String userID;

        public SimpanNamaBaru() {
            //Constructor
        }

        public SimpanNamaBaru(String nama_str, String titel_str, String tglLahir_str, String kewarganegaraan_str, String nikAtauPaspor_str, String userID) {
            this.nama_str = nama_str;
            this.titel_str = titel_str;
            this.tglLahir_str = tglLahir_str;
            this.kewarganegaraan_str = kewarganegaraan_str;
            this.nikAtauPaspor_str = nikAtauPaspor_str;
            this.userID = userID;
        }

        public String getNama_str() {
            return nama_str;
        }

        public void setNama_str(String nama_str) {
            this.nama_str = nama_str;
        }

        public String getTitel_str() {
            return titel_str;
        }

        public void setTitel_str(String titel_str) {
            this.titel_str = titel_str;
        }

        public String getTglLahir_str() {
            return tglLahir_str;
        }

        public void setTglLahir_str(String tglLahir_str) {
            this.tglLahir_str = tglLahir_str;
        }

        public String getKewarganegaraan_str() {
            return kewarganegaraan_str;
        }

        public void setKewarganegaraan_str(String kewarganegaraan_str) {
            this.kewarganegaraan_str = kewarganegaraan_str;
        }

        public String getNikAtauPaspor_str() {
            return nikAtauPaspor_str;
        }

        public void setNikAtauPaspor_str(String nikAtauPaspor_str) {
            this.nikAtauPaspor_str = nikAtauPaspor_str;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }
    }
}

