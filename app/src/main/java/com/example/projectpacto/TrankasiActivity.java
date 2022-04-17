package com.example.projectpacto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectpacto.databinding.ActivityTrankasiBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrankasiActivity extends AppCompatActivity {

    ActivityTrankasiBinding binding;

    String tglMulai;
    String tglBerakhir;
    String pemasukanAtauPengeluaran;

    ArrayList<String> keterangan;
    ArrayList<String> tanggal;
    ArrayList<String> tipeTransaksi;
    ArrayList<String> nominalTransaksi;
    ArrayList<String> documentID;


    FirebaseFirestore fs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrankasiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        fs = FirebaseFirestore.getInstance();

        documentID = new ArrayList<>();
        keterangan = new ArrayList<>();
        tanggal = new ArrayList<>();
        tipeTransaksi = new ArrayList<>();
        nominalTransaksi = new ArrayList<>();

        binding.bottomNav.setSelectedItemId(R.id.transaksi);

        String userID = "5E8dHyQfzYeu1wBvwjxNr8EUl7J3";

        tglMulai = "";
        tglBerakhir = "";
        pemasukanAtauPengeluaran = "2"; //1a = pengeluaran saja. 1b = pemasukan saja. 2 = dua2nya


        binding.filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("tglMulai", tglMulai);
                bundle.putString("tglBerakhir", tglBerakhir);
                bundle.putString("pemasukanAtauPengeluaran", pemasukanAtauPengeluaran);
                TransaksiFilterButtonSheet transaksiFilterButtonSheet = new TransaksiFilterButtonSheet();
                transaksiFilterButtonSheet.setArguments(bundle);
                transaksiFilterButtonSheet.show(getSupportFragmentManager(), transaksiFilterButtonSheet.getTag());
            }
        });

        fs.collection("credit").whereEqualTo("userID", userID).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (queryDocumentSnapshots != null) {
                    List<DocumentSnapshot> snapshotList = queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot snapshot : snapshotList) {
                        Map<String, Object> map = (Map<String, Object>) snapshot.getData();
                        String id = snapshot.getId();
                        documentID.add(id);
                        String keterangan_str = map.get("keterangan").toString();
                        String tanggal_str = map.get("tanggal").toString();
                        String tipeTransakasi_str = map.get("tipeTransaksi").toString();
                        String nominalTransaksi_str = map.get("nominalTransaksi").toString();

                        keterangan.add(keterangan_str);
                        tanggal.add(tanggal_str);
                        tipeTransaksi.add(tipeTransakasi_str);
                        nominalTransaksi.add(nominalTransaksi_str);

                    }
                    RecyclerAdapterTransaksi recyclerAdapterTransaksi = new RecyclerAdapterTransaksi(keterangan, tanggal, tipeTransaksi, nominalTransaksi);
                    binding.transaksiRecyclerView.setAdapter(recyclerAdapterTransaksi);
                }
            }
        });

//        keterangan.add("Penambahan kredit bertambah");
//        tanggal.add("27/02/2022");
//        tipeTransaksi.add("plus");
//        nominalTransaksi.add("+ IDR 20.000.00");
//
//        keterangan.add("Penambahan kredit berkurang lorem ipsum sit dolar amet");
//        tanggal.add("27/02/2022");
//        tipeTransaksi.add("minus");
//        nominalTransaksi.add("- IDR 2.000.00");






        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.beranda:
                        startActivity(new Intent(getApplicationContext(), RealMainActivity.class));
                        overridePendingTransition(0 , 0);
                        return true;

                    case R.id.transaksi:

                        return true;

                    case R.id.booking:
                        startActivity(new Intent(getApplicationContext(), BookingActivity.class));
                        overridePendingTransition(0 , 0);
                        return true;

                    case R.id.user:
                        startActivity(new Intent(getApplicationContext(), UserActivity.class));
                        overridePendingTransition(0 , 0);
                        return true;
                }


                return false;
            }
        });
    }


    public class RecyclerAdapterTransaksi extends RecyclerView.Adapter<ViewHolder> {

        private ArrayList<String> keterangan;
        private ArrayList<String> tanggal;
        private ArrayList<String> tipeTransaksi;
        private ArrayList<String> nominalTransaksi;

        public RecyclerAdapterTransaksi(ArrayList<String> keterangan, ArrayList<String> tanggal, ArrayList<String> tipeTransaksi, ArrayList<String> nominalTransaksi) {
            this.keterangan = keterangan;
            this.tanggal = tanggal;
            this.tipeTransaksi = tipeTransaksi;
            this.nominalTransaksi = nominalTransaksi;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.transaction_single_view, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return  viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if (tipeTransaksi.get(position).matches("minus")){
                holder.simbolPlusOrMinus.setImageResource(R.drawable.transaction_minus);
                holder.nominalTransaction.setTextColor(getResources().getColor(R.color.fail));
            }
            holder.keteranganTransaksi.setText(keterangan.get(position));
            holder.tanggal.setText(tanggal.get(position));
            holder.nominalTransaction.setText(nominalTransaksi.get(position));


        }

        @Override
        public int getItemCount() {
            return tipeTransaksi.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView simbolPlusOrMinus;
        TextView keteranganTransaksi;
        TextView nominalTransaction;
        TextView tanggal;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            simbolPlusOrMinus = itemView.findViewById(R.id.simbolPlusOrMinus);
            keteranganTransaksi = itemView.findViewById(R.id.keteranganTransaksi);
            nominalTransaction = itemView.findViewById(R.id.nominalTransaction);
            tanggal = itemView.findViewById(R.id.tanggalTransaksi);




        }


    }
}