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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.projectpacto.databinding.ActivityBookingBinding;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class BookingActivity extends AppCompatActivity {

    ActivityBookingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        binding.bottomNav.setSelectedItemId(R.id.booking);

        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.beranda:
                        startActivity(new Intent(getApplicationContext(), RealMainActivity.class));
                        overridePendingTransition(0 , 0);
                        return true;

                    case R.id.transaksi:
                        startActivity(new Intent(getApplicationContext(), TrankasiActivity.class));
                        overridePendingTransition(0 , 0);
                        return true;

                    case R.id.booking:
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


    public class BookingStatusRecyclerAdapter extends RecyclerView.Adapter<BookingStatusRecyclerAdapter.ViewHolder> {
        ArrayList<String> kotaAsal_atau_namaHotel;
        ArrayList<String> kotaTujuan;
        ArrayList<String> statusPesanan;
        ArrayList<String> tglBerangkat_atau_alamat;
        ArrayList<String> tglCek_in;
        ArrayList<String> namaMaskapai;
        ArrayList<String> kodePenerbangan;
        ArrayList<String> rincianPenumpang;
        ArrayList<String> jumlahKamar;
        ArrayList<Integer> logoMaskapai;
        ArrayList<String> tipePesanan;

        public BookingStatusRecyclerAdapter(ArrayList<String> kotaAsal_atau_namaHotel, ArrayList<String> kotaTujuan, ArrayList<String> statusPesanan,
                                            ArrayList<String> tglBerangkat_atau_alamat, ArrayList<String> tglCek_in,  ArrayList<String> namaMaskapai,  ArrayList<String> kodePenerbangan,
                                            ArrayList<String> rincianPenumpang, ArrayList<String> jumlahKamar, ArrayList<Integer> logoMaskapai, ArrayList<String> tipePesanan){

            this.kotaAsal_atau_namaHotel = kotaAsal_atau_namaHotel;
            this.kotaTujuan = kotaTujuan;
            this.statusPesanan =statusPesanan;
            this.tglBerangkat_atau_alamat = tglBerangkat_atau_alamat;
            this.tglCek_in = tglCek_in;
            this.namaMaskapai = namaMaskapai;
            this.kodePenerbangan = kodePenerbangan;
            this.rincianPenumpang = rincianPenumpang;
            this.jumlahKamar = jumlahKamar;
            this.logoMaskapai = logoMaskapai;
            this.tipePesanan = tipePesanan;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.tambahan_opsi_bagasi_single_view, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            String tipePesanan_str = tipePesanan.get(position);



            holder.kotaAsal_atau_namaHotel.setText(kotaAsal_atau_namaHotel.get(position));
            holder.kotaTujuan.setText(kotaTujuan.get(position));
            holder.statusPesanan.setText(statusPesanan.get(position));
            holder.tglBerangkat_atau_alamat.setText(tglBerangkat_atau_alamat.get(position));
            holder.tglCek_in.setText(tglCek_in.get(position));
            holder.namaMaskapai.setText(namaMaskapai.get(position));
            holder.kodePenerbangan.setText(kodePenerbangan.get(position));
            holder.rincianPenumpang.setText(rincianPenumpang.get(position));
            holder.jumlahKamar.setText(jumlahKamar.get(position));
            holder.logoMaskapai.setImageResource(logoMaskapai.get(position));

            switch (tipePesanan_str){
                case "Pesawat":
                    holder.tglCek_in.setVisibility(View.GONE);
                    holder.jumlahKamarLayout.setVisibility(View.GONE);
                    break;

                case "Hotel":
                    holder.arrow.setVisibility(View.GONE);
                    holder.kotaTujuan.setVisibility(View.GONE);
                    holder.logoMaskapaiLayout.setVisibility(View.GONE);
                    break;
            }


        }

        @Override
        public int getItemCount() {
            return tipePesanan.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView kotaAsal_atau_namaHotel;
            ImageView arrow;
            TextView kotaTujuan;
            TextView statusPesanan;
            TextView tglBerangkat_atau_alamat;
            TextView tglCek_in;
            RelativeLayout logoMaskapaiLayout;
            ImageView logoMaskapai;
            TextView namaMaskapai;
            TextView kodePenerbangan;
            LinearLayout rincianPenumpangLayout;
            TextView rincianPenumpang;
            LinearLayout jumlahKamarLayout;
            TextView jumlahKamar;



            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                kotaAsal_atau_namaHotel = itemView.findViewById(R.id.kotaAsal_atau_namaHotel);
                arrow = itemView.findViewById(R.id.arrow);
                kotaTujuan = itemView.findViewById(R.id.kotaTujuan);
                statusPesanan = itemView.findViewById(R.id.statusPesanan);
                tglBerangkat_atau_alamat = itemView.findViewById(R.id.tglBerangkat_atau_alamat);
                tglCek_in = itemView.findViewById(R.id.tglCek_in);
                logoMaskapaiLayout = itemView.findViewById(R.id.logoMaskapaiLayout);
                logoMaskapai = itemView.findViewById(R.id.logoMaskapai);
                namaMaskapai = itemView.findViewById(R.id.namaMaskapai);
                kodePenerbangan = itemView.findViewById(R.id.kodePenerbangan);
                rincianPenumpangLayout = itemView.findViewById(R.id.rincianPenumpangLayout);
                rincianPenumpang = itemView.findViewById(R.id.rincianPenumpang);
                jumlahKamarLayout = itemView.findViewById(R.id.jumlahKamarLayout);
                jumlahKamar = itemView.findViewById(R.id.jumlahKamar);
            }
        }






    }
}