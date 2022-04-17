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
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class TrankasiActivity extends AppCompatActivity {

    ActivityTrankasiBinding binding;

    ArrayList<String> keterangan;
    ArrayList<String> tanggal;
    ArrayList<String> tipeTransaksi;
    ArrayList<String> nominalTransaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrankasiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        keterangan = new ArrayList<>();
        tanggal = new ArrayList<>();
        tipeTransaksi = new ArrayList<>();
        nominalTransaksi = new ArrayList<>();

        binding.bottomNav.setSelectedItemId(R.id.transaksi);

        keterangan.add("Penambahan kredit bertambah");
        tanggal.add("27/02/2022");
        tipeTransaksi.add("plus");
        nominalTransaksi.add("+ IDR 20.000.00");

        keterangan.add("Penambahan kredit berkurang lorem ipsum sit dolar amet");
        tanggal.add("27/02/2022");
        tipeTransaksi.add("minus");
        nominalTransaksi.add("- IDR 2.000.00");

        RecyclerAdapterTransaksi recyclerAdapterTransaksi = new RecyclerAdapterTransaksi(keterangan, tanggal, tipeTransaksi, nominalTransaksi);
        binding.transaksiRecyclerView.setAdapter(recyclerAdapterTransaksi);




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