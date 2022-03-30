package com.example.projectpacto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
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
    SharedPreferences pref;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        kotaAsal_atau_namaHotel = new ArrayList<>();
        kotaTujuan = new ArrayList<>();
        statusPesanan = new ArrayList<>();
        tglBerangkat_atau_alamat = new ArrayList<>();
        tglCek_in = new ArrayList<>();
        namaMaskapai = new ArrayList<>();
        kodePenerbangan = new ArrayList<>();
        rincianPenumpang = new ArrayList<>();
        jumlahKamar = new ArrayList<>();
        logoMaskapai = new ArrayList<>();
        tipePesanan = new ArrayList<>();


        Bundle bundle = getIntent().getBundleExtra("bundle");
        if (bundle !=null){

            //CEK APAKAH PESAWAT ATAU HOTEL

            tglCek_in.add("");
            jumlahKamar.add("");
            kotaAsal_atau_namaHotel.add(bundle.getString("kotaAsal"));
            kotaTujuan.add(bundle.getString("kotaTujuan"));
            tglBerangkat_atau_alamat.add(bundle.getString("tanggalBerangkat") + " - " + bundle.getString("waktuBerangkat"));
            logoMaskapai.add(bundle.getInt("logoMaskapai"));
            namaMaskapai.add(bundle.getString("namaMaskapai"));
            kodePenerbangan.add("IQK290");

            String rincianPenumpang_str = "Dewasa ("+bundle.getString("jmlDewasa")+"x)";
            if (!bundle.getString("jmlAnak").matches("0")){
                rincianPenumpang_str = rincianPenumpang_str + ", Anak (" +bundle.getString("jmlAnak")+"x)";
            }


            if (!bundle.getString("jmlBalita").matches("0")){
                rincianPenumpang_str = rincianPenumpang_str + ", Balita ("+bundle.getString("jmlBalita")+"x)";
            }

            rincianPenumpang.add(rincianPenumpang_str);
            statusPesanan.add(bundle.getString("status"));
            tipePesanan.add(bundle.getString("tipePesanan"));

            BookingStatusRecyclerAdapter bookingStatusRecyclerAdapter = new BookingStatusRecyclerAdapter(kotaAsal_atau_namaHotel,kotaTujuan, statusPesanan,
                    tglBerangkat_atau_alamat,  tglCek_in,  namaMaskapai,   kodePenerbangan,
                     rincianPenumpang,  jumlahKamar, logoMaskapai,  tipePesanan);
            binding.RecyclerViewPesanan.setAdapter(bookingStatusRecyclerAdapter);



//            bandaraAsal_str= bundle.getString("bandaraAsal");
//            logoMaskapai_int =bundle.getInt("logoMaskapai");
//            namaMaskapai_str= bundle.getString("namaMaskapai");;
//            kelasPesawat_str= bundle.getString("kelasPesawat");;
//            tanggalDatang_str= bundle.getString("tanggalDatang");;
//            waktuDatang_str= bundle.getString("waktuDatang");;
//            bandaraTujuan_str= bundle.getString("bandaraTujuan");
//            jmlDewasa_str= ;;
//            jmlAnak_str=  ;
//            jmlBalita_str= ;;
//
//            keberangkatan = bundle.getString("keberangkatan");
//            kedatangan = bundle.getString("kedatangan");
//            tanggal = bundle.getString("tanggal");
//            penumpang = bundle.getString("penumpang");
//            kota_keberangkatan = bundle.getString("kota_keberangkatan");
//            kota_kedatangan = bundle.getString("kota_kedatangan");
//            bandara_keberangktan_raw = bundle.getString("bandara_keberangkatan");
//            bandara_kedatangan_raw =  bundle.getString("bandara_kedatangan");


        }





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
            View view = layoutInflater.inflate(R.layout.pesanan_tiket_hotel_single_view, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            String tipePesanan_str = tipePesanan.get(position);
            String status_str = statusPesanan.get(position);



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
            holder.statusPesanan.setText(statusPesanan.get(position));

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

            switch (status_str) {
                case "Belum Bayar":
                    holder.statusPesanan.setBackgroundTintList(getResources().getColorStateList(R.color.yellow));
                    break;
                case "Selesai":
                    holder.statusPesanan.setBackgroundTintList(getResources().getColorStateList(R.color.green_success));
                    break;
                case "Dibatalkan":
                    holder.statusPesanan.setBackgroundTintList(getResources().getColorStateList(R.color.fail));
                    break;
                case "Issued":
                    holder.statusPesanan.setBackgroundTintList(getResources().getColorStateList(R.color.primary));
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