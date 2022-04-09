package com.example.projectpacto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookingActivity extends AppCompatActivity {
    @Override
    public void onBackPressed() {
    }

    private static final String TAG = "MainActivity";
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
    ArrayList<String> jumlahMalam;

    FirebaseFirestore fs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        fs= FirebaseFirestore.getInstance();



        kotaAsal_atau_namaHotel = new ArrayList<>(); //Semua keisi, sesuaikan saja
        kotaTujuan = new ArrayList<>(); //khusus pesawat, kalau hotel tinggal diisi string kosong saja
        statusPesanan = new ArrayList<>(); //semua clear
        tglBerangkat_atau_alamat = new ArrayList<>(); //sesuaikan
        tglCek_in = new ArrayList<>(); //khusus hotel, pesawat diisi string kosong saja
        namaMaskapai = new ArrayList<>(); //khusus pesawat, hotel diisi string kosong saja
        kodePenerbangan = new ArrayList<>(); // khusus pesawat, hotel diisi string kosong saja
        rincianPenumpang = new ArrayList<>(); //sesuaikan
        jumlahKamar = new ArrayList<>(); //khusus hotel, pesawat diisi string kosong saja
        logoMaskapai = new ArrayList<>(); //khusus pesawat, hotel diisi string kosong saja
        tipePesanan = new ArrayList<>(); //khusus pesawat, hotel diisi string kosong saja
        jumlahMalam = new ArrayList<>(); //khusus hotel, pesawat diisi string kosong saja


        fs.collection("bookingHistory").whereEqualTo("userID", "5E8dHyQfzYeu1wBvwjxNr8EUl7J3").addSnapshotListener(new EventListener<QuerySnapshot>() {
           @Override
           public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
               if (error != null) {
                   Log.e(TAG, "onEvent", error);
                   return;
               }
               if (value != null) {
                   List<DocumentSnapshot> snapshotList = value.getDocuments();
                   for (DocumentSnapshot snapshot : snapshotList) {
                       Map<String, Object> map = (Map<String, Object>) snapshot.getData();
                       String tipePesanan_str = map.get("tipePesanan").toString();

                       //If it's a hotel
                       if (tipePesanan_str.matches("Hotel")) {

                           String namaHotel_str = map.get("namaHotel").toString();
                           String alamatTambahan_str = map.get("tambahanAlamat").toString();
                           String tglCek_in_str = map.get("tglCek_in").toString();
                           String tglCek_out_str = map.get("tglCek_out").toString();
                           String jumlahKamar_str = map.get("jumlahKamar").toString();
                           String jumlahMalam_str = "(" + map.get("jumlahMalam").toString() + " Malam)";
                           ArrayList<String> arrayTamu = (ArrayList<String>) map.get("dataTamu");
                           String jumlahTamu_str = arrayTamu.size() + " Tamu";
                           String status = map.get("status").toString();

                           kotaAsal_atau_namaHotel.add(namaHotel_str);
                           kotaTujuan.add("");
                           statusPesanan.add(status);
                           tglBerangkat_atau_alamat.add(alamatTambahan_str);
                           tglCek_in.add(tglCek_in_str);
                           namaMaskapai.add(tglCek_out_str);
                           kodePenerbangan.add("");
                           rincianPenumpang.add(jumlahTamu_str);
                           jumlahKamar.add(jumlahKamar_str);
                           logoMaskapai.add(null);
                           tipePesanan.add(tipePesanan_str);
                           jumlahMalam.add(jumlahMalam_str);


                       } if (tipePesanan_str.matches("Pesawat")){



                       }
                   }
               }
           }
       });






//        Bundle bundle = getIntent().getBundleExtra("bundle");
//        if (bundle !=null){
//
//            //CEK APAKAH PESAWAT ATAU HOTEL
//
//            tglCek_in.add("");
//            jumlahKamar.add("");
//            kotaAsal_atau_namaHotel.add(bundle.getString("kotaAsal"));
//            kotaTujuan.add(bundle.getString("kotaTujuan"));
//            tglBerangkat_atau_alamat.add(bundle.getString("tanggalBerangkat") + " - " + bundle.getString("waktuBerangkat"));
//            logoMaskapai.add(bundle.getInt("logoMaskapai"));
//            namaMaskapai.add(bundle.getString("namaMaskapai"));
//            kodePenerbangan.add("IQK290");
//
//            String rincianPenumpang_str = "Dewasa ("+bundle.getString("jmlDewasa")+"x)";
//            if (!bundle.getString("jmlAnak").matches("0")){
//                rincianPenumpang_str = rincianPenumpang_str + ", Anak (" +bundle.getString("jmlAnak")+"x)";
//            }
//
//
//            if (!bundle.getString("jmlBalita").matches("0")){
//                rincianPenumpang_str = rincianPenumpang_str + ", Balita ("+bundle.getString("jmlBalita")+"x)";
//            }
//
//            rincianPenumpang.add(rincianPenumpang_str);
//            statusPesanan.add(bundle.getString("status"));
//            tipePesanan.add(bundle.getString("tipePesanan"));

//            BookingStatusRecyclerAdapter bookingStatusRecyclerAdapter = new BookingStatusRecyclerAdapter(jumlahMalam, kotaAsal_atau_namaHotel,kotaTujuan, statusPesanan,
//                    tglBerangkat_atau_alamat,  tglCek_in,  namaMaskapai,   kodePenerbangan,
//                     rincianPenumpang,  jumlahKamar, logoMaskapai,  tipePesanan);
//            binding.RecyclerViewPesanan.setAdapter(bookingStatusRecyclerAdapter);



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
        ArrayList<String> jumlahMalam;

        public BookingStatusRecyclerAdapter(ArrayList<String> jumlahMalam, ArrayList<String> kotaAsal_atau_namaHotel, ArrayList<String> kotaTujuan, ArrayList<String> statusPesanan,
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
            this.jumlahMalam = jumlahMalam;
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