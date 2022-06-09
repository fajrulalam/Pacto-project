package com.example.projectpacto;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectpacto.databinding.ActivityHotelOrder3Binding;
import com.example.projectpacto.databinding.ActivityPlaneOrder2Binding;
import com.example.projectpacto.databinding.ActivityPlaneOrder3Binding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HotelOrderActivity3 extends AppCompatActivity implements ModifikasiHotelBottomSheet.KirimModifikasiPesanan, JumlahKamarBottomSheet.OnDataKamar{

    ActivityHotelOrder3Binding binding;
    int gambarHotel;
    String namaHotel;
    String tambahanAlamat;
    String harga;
    int jmlBintang;

    String hotelAtauKota_srch;
    String jumlahKamar ;
    String jumlahMalam;
    String tglCek_out;
    String tglCek_in;
    String tglCek_in_withDay;
    int jumlahKamar_int;
    int jumlahTamu_int;
    Date tglCek_in_date;


    ArrayList<Integer> gambarKamar;
    ArrayList<String> namaKamar;
    ArrayList<Integer> kapasitasTamu;
    ArrayList<String> tipeKasur;
    ArrayList<String> sarapan;
    ArrayList<String> hargaKamar;

    RecycleAdapterKamarOptions recycleAdapterKamarOptions;

    Intent bundle;
    Bundle extras;




    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHotelOrder3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        bundle = this.getIntent();
        extras = bundle.getBundleExtra("bundle");
        gambarHotel = extras.getInt("gambarHotel");
        namaHotel = extras.getString("namaHotel");
        tambahanAlamat = extras.getString("tambahanAlamat");
        harga = extras.getString("harga");
        jmlBintang= extras.getInt("jmlBintang");

        hotelAtauKota_srch= extras.getString("kotaAtauHotel");
        jumlahKamar = extras.getString("jumlahKamar");
        jumlahMalam= extras.getString("jumlahMalam");
        tglCek_out= extras.getString("tglCek_out");
        tglCek_in= extras.getString("tglCek_in");
        tglCek_in_withDay= extras.getString("tglCek_in");

        Locale lokal = new Locale("id", "ID");

        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy", lokal);
        try {
            tglCek_in_date = formatter.parse(tglCek_in);

            formatter =  new SimpleDateFormat("dd MMM yyyy");
            tglCek_in = formatter.format(tglCek_in_date);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
//        ZonedDateTime dateTime= Instant.ofEpochMilli(Long.parseLong(tglCek_in.toString())).atZone(ZoneId.of("Asia/Jakarta"));
//        tglCek_in = dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM YYYY", lokal));

        Log.i("TGL CEK IN", "ACT3" +  tglCek_in);



        binding.namaHotel.setText(namaHotel);
        binding.detailTamudanKamar.setText(jumlahKamar);

        binding.tanggalCekIn.setText(tglCek_in);
        binding.jmlMalam.setText(jumlahMalam + " Malam");
        binding.jumlahKamar.setText(jumlahKamar.split(", ")[0]);
        binding.jmlTamu.setText(jumlahKamar.split(", ")[1]);

        gambarKamar= new ArrayList<>();
        namaKamar= new ArrayList<>();
        kapasitasTamu= new ArrayList<>();
        tipeKasur= new ArrayList<>();
        sarapan= new ArrayList<>();
        hargaKamar= new ArrayList<>();

        recycleAdapterKamarOptions = new RecycleAdapterKamarOptions(gambarKamar, namaKamar, kapasitasTamu, tipeKasur, sarapan, hargaKamar);


        populateArrayList();

        binding.ubahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle =  new Bundle();
                bundle.putString("tglCek_in", tglCek_in_withDay);
                bundle.putString("tglCek_out", tglCek_out);
                bundle.putString("jumlahMalam", jumlahMalam);
                bundle.putString("jumlahKamar", jumlahKamar);
                ModifikasiHotelBottomSheet modifikasiHotelBottomSheet = new ModifikasiHotelBottomSheet();
                modifikasiHotelBottomSheet.setArguments(bundle);
                modifikasiHotelBottomSheet.show(getSupportFragmentManager(), modifikasiHotelBottomSheet.getTag());
            }
        });




        //HANDLING BACK BUTTON
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HotelOrderActivity2.class);
                intent.putExtra("tglCek_in", tglCek_in);
                intent.putExtra("tglCek_out", tglCek_out);
                intent.putExtra("jumlahKamar", jumlahKamar);
                intent.putExtra("jumlahMalam", jumlahMalam);
                intent.putExtra("kotaAtauHotel", hotelAtauKota_srch);
                startActivity(intent);
            }
        });








    }

    private void populateArrayList() {

        gambarKamar.add(R.drawable.room_dummy);
        namaKamar.add("Budget Room");
        kapasitasTamu.add(2);
        tipeKasur.add("Single/Twin Bed");
        sarapan.add("Tidak termasuk sarapan");
        hargaKamar.add("IDR 550.000");

        gambarKamar.add(R.drawable.room_dummy);
        namaKamar.add("Budget Room with Breakfast");
        kapasitasTamu.add(2);
        tipeKasur.add("Single/Twin Bed");
        sarapan.add("Termasuk sarapan");
        hargaKamar.add("IDR 620.000");


        gambarKamar.add(R.drawable.room_dummy2);
        namaKamar.add("Worth-it Room");
        kapasitasTamu.add(2);
        tipeKasur.add("Queen Bed");
        sarapan.add("Tidak termasuk sarapan");
        hargaKamar.add("IDR 630.000");

        gambarKamar.add(R.drawable.room_dummy2);
        namaKamar.add("Worth-it Room with Breakfast");
        kapasitasTamu.add(2);
        tipeKasur.add("Queen Bed");
        sarapan.add("Termasuk sarapan");
        hargaKamar.add("IDR 690.000");

        gambarKamar.add(R.drawable.room_dummy2);
        namaKamar.add("King Room");
        kapasitasTamu.add(2);
        tipeKasur.add("King Bed");
        sarapan.add("Termasuk sarapan");
        hargaKamar.add("IDR 790.000");

        binding.RecycleViewKamarOptions.setAdapter(recycleAdapterKamarOptions);
    }

    @Override
    public void KirimModifikasiPesanan(String tglCekIn, String tglCekOut_str, String jumlahMalam_str, String KamardanTamu, String request) {
        //if request == Save, then it just saves the modification and close the fragment.
        //if request == Change, then it will save the modification, close the fragment, then opens the jumlahkamar dan jumlah tamu.

        tglCek_in_withDay = tglCekIn;
        tglCek_out = tglCekOut_str;
        jumlahKamar = KamardanTamu;
        jumlahKamar = jumlahMalam_str;




        Locale lokal = new Locale("id", "ID");
        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy", lokal);
        Log.i("Modifikasi Pesanan", tglCekIn);
        try {
            tglCek_in_date = formatter.parse(tglCekIn);
            formatter =  new SimpleDateFormat("dd MMM yyyy");
            tglCek_in = formatter.format(tglCek_in_date);
        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }
        binding.detailTamudanKamar.setText(jumlahKamar);
        binding.tanggalCekIn.setText(tglCek_in);
        binding.jmlMalam.setText(jumlahMalam_str + " Malam");
        binding.jumlahKamar.setText(jumlahKamar.split(", ")[0]);
        binding.jmlTamu.setText(jumlahKamar.split(", ")[1]);

        Log.i("jumlah kamar", jumlahKamar);
//        jumlahKamar_int =  Integer.parseInt(jumlahKamar.split(",")[1].split(" ")[0]);
//        jumlahTamu_int = Integer.parseInt(jumlahKamar.split(",")[0].split(" ")[0]);

        if (request.matches("Change")){
            Bundle bundle = new Bundle();
            bundle.putInt("jmlKamar", jumlahKamar_int);
            bundle.putInt("jmlTamu", jumlahTamu_int);
            JumlahKamarBottomSheet jumlahKamarBottomSheet = new JumlahKamarBottomSheet();
            jumlahKamarBottomSheet.setArguments(bundle);
            jumlahKamarBottomSheet.show(getSupportFragmentManager(), jumlahKamarBottomSheet.getTag());
        }
    }

    @Override
    public void onDataPassTamu(String jumlahTamu, String jumlahKamar_str) {
        jumlahTamu_int = Integer.parseInt(jumlahTamu);
        jumlahKamar_int = Integer.parseInt(jumlahKamar_str);
        binding.jumlahKamar.setText(jumlahKamar_str + "");
        binding.jmlTamu.setText(jumlahTamu + " Tamu");
        jumlahKamar = jumlahTamu + " Tamu, " + jumlahKamar_str +" Kamar";
        Bundle bundle =  new Bundle();
        bundle.putString("tglCek_in", tglCek_in);
        bundle.putString("tglCek_out", tglCek_out);
        bundle.putString("jumlahMalam", jumlahMalam);
        bundle.putString("jumlahKamar", jumlahKamar);
        ModifikasiHotelBottomSheet modifikasiHotelBottomSheet = new ModifikasiHotelBottomSheet();
        modifikasiHotelBottomSheet.setArguments(bundle);
        modifikasiHotelBottomSheet.show(getSupportFragmentManager(), modifikasiHotelBottomSheet.getTag());




    }


    public class RecycleAdapterKamarOptions extends RecyclerView.Adapter<RecycleAdapterKamarOptions.ViewHolder>{
        ArrayList<Integer> gambarKamar;
        ArrayList<String> namaKamar;
        ArrayList<Integer> kapasitasTamu;
        ArrayList<String> tipeKasur;
        ArrayList<String> sarapan;
        ArrayList<String> hargaKamar;

        public RecycleAdapterKamarOptions(ArrayList<Integer> gambarKamar, ArrayList<String> namaKamar, ArrayList<Integer> kapasitasTamu, ArrayList<String> tipeKasur, ArrayList<String> sarapan, ArrayList<String> hargaKamar ){
            this.gambarKamar =gambarKamar;
            this.namaKamar =namaKamar;
            this.kapasitasTamu =kapasitasTamu;
            this.tipeKasur =tipeKasur;
            this.sarapan =sarapan;
            this.hargaKamar =hargaKamar;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.room_single_view, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return  viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            int position_fix = position;
            holder.gambarKamar.setImageResource(gambarKamar.get(position));
            holder.namaKamar.setText(namaKamar.get(position));
            holder.jumlahTamu.setText(kapasitasTamu.get(position) + " Tamu");
            holder.tipeKasur.setText(tipeKasur.get(position));
            holder.sarapan.setText(sarapan.get(position));
            holder.harga.setText(hargaKamar.get(position));

            holder.pilihKamar_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int gambarKamar_int = gambarKamar.get(position_fix);
                    String namaKamar_str = namaKamar.get(position_fix);
                    int kapasitasKamar =  kapasitasTamu.get(position_fix);
                    String tipeKasur_str = tipeKasur.get(position_fix);
                    String sarapan_str = sarapan.get(position_fix);
                    String hargaKamar_str = hargaKamar.get(position_fix);

                    extras.putInt("gambarKamar", gambarKamar_int);
                    extras.putString("namaKamar", namaKamar_str);
                    extras.putInt("kapasitasKamar", kapasitasKamar);
                    extras.putString("tipeKasur", tipeKasur_str);
                    extras.putString("sarapan", sarapan_str);
                    extras.putString("hargaKamar", hargaKamar_str);

                    Intent intent = new Intent(getApplicationContext(), HotelOrderActivity4.class);
                    intent.putExtra("bundle", extras);

                    startActivity(intent);


                }
            });

        }

        @Override
        public int getItemCount() {
            return namaKamar.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            ImageView gambarKamar;
            TextView namaKamar;
            TextView jumlahTamu;
            TextView tipeKasur;
            TextView sarapan;
            TextView harga;
            Button pilihKamar_btn;






            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                gambarKamar = itemView.findViewById(R.id.gambarKamar);
                namaKamar = itemView.findViewById(R.id.namaKamar);
                jumlahTamu = itemView.findViewById(R.id.jumlahTamu);
                tipeKasur = itemView.findViewById(R.id.tipeKasur);
                sarapan = itemView.findViewById(R.id.sarapan);
                harga = itemView.findViewById(R.id.harga);
                pilihKamar_btn = itemView.findViewById(R.id.pilihKamar_btn);





            }


        }

    }




}