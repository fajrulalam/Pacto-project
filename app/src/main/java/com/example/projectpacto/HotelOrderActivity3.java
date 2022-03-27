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

public class HotelOrderActivity3 extends AppCompatActivity {

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
    Date tglCek_in_date;


    ArrayList<Integer> gambarKamar;
    ArrayList<String> namaKamar;
    ArrayList<Integer> kapasitasTamu;
    ArrayList<String> tipeKasur;
    ArrayList<String> sarapan;
    ArrayList<String> hargaKamar;

    RecycleAdapterKamarOptions recycleAdapterKamarOptions;




    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHotelOrder3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent bundle = this.getIntent();
        Bundle extras = bundle.getBundleExtra("bundle");
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
            holder.gambarKamar.setImageResource(gambarKamar.get(position));
            holder.namaKamar.setText(namaKamar.get(position));
            holder.jumlahTamu.setText(kapasitasTamu.get(position) + " Tamu");
            holder.tipeKasur.setText(tipeKasur.get(position));
            holder.sarapan.setText(sarapan.get(position));
            holder.harga.setText(hargaKamar.get(position));

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