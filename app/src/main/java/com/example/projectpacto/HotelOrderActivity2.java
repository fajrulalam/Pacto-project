package com.example.projectpacto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projectpacto.databinding.ActivityHotelOrder2Binding;
import com.example.projectpacto.databinding.ActivityPlaneOrder2Binding;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class HotelOrderActivity2 extends AppCompatActivity {

    ActivityHotelOrder2Binding binding;

    ArrayList<String> nama_hotel;
    ArrayList<String> tambahanAlamat;
    ArrayList<String> harga;
    ArrayList<Integer> jmlBintang;
    ArrayList<Integer> gambarHotel;

    String hotelAtauKota_srch;
    String jumlahKamar ;
    String jumlahMalam;
    String tglCek_out;
    String tglCek_in;
    RecycleAdapterHotelOptions recycleAdapterHotelOptions;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHotelOrder2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        nama_hotel= new ArrayList<String>();
        tambahanAlamat= new ArrayList<String>();
        harga= new ArrayList<String>();
        jmlBintang= new ArrayList<Integer>();
        gambarHotel= new ArrayList<Integer>();


        recycleAdapterHotelOptions = new RecycleAdapterHotelOptions(nama_hotel, tambahanAlamat, harga, jmlBintang, gambarHotel);
        binding.RecycleViewHotel.setAdapter(recycleAdapterHotelOptions);


        Bundle extras = getIntent().getExtras();
        if (extras != null){
            hotelAtauKota_srch= extras.getString("kotaAtauHotel");
            jumlahKamar = extras.getString("jumlahKamar");
            jumlahMalam= extras.getString("jumlahMalam");
            tglCek_out= extras.getString("tglCek_out");
            tglCek_in= extras.getString("tglCek_in");

            Log.i("TGL CEK IN", "ACT2" + tglCek_in);

            //DISPLAYING INTENTS
            binding.kotaAtauHotelTextView.setText(hotelAtauKota_srch);
            binding.detailTamudanKamar.setText(jumlahKamar);

            populateList(hotelAtauKota_srch);

        }

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HotelOrderActivity1.class);
                intent.putExtra("tglCek_in", tglCek_in);
                intent.putExtra("tglCek_out", tglCek_out);
                intent.putExtra("jumlahKamar", jumlahKamar);
                intent.putExtra("jumlahMalam", jumlahMalam);
                intent.putExtra("kotaAtauHotel", hotelAtauKota_srch);
                startActivity(intent);
            }
        });

        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.filter:
                        Log.i("Test", "Filter");
                        FilterHotelFragment filterFragment = new FilterHotelFragment();
                        filterFragment.show(getSupportFragmentManager(), filterFragment.getTag());
                        return true;
                    case R.id.sort:
                        SortHotelFragment sortPlaneTicket = new SortHotelFragment();
                        sortPlaneTicket.show(getSupportFragmentManager(), sortPlaneTicket.getTag());
                        return true;
                }
                return false;



            }
        });

        RecyclerView recyclerView = binding.RecycleViewHotel;
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("gambarHotel", gambarHotel.get(position));
                bundle.putString("namaHotel", nama_hotel.get(position));
                bundle.putString("tambahanAlamat", tambahanAlamat.get(position));
                bundle.putString("harga", harga.get(position));
                bundle.putInt("jmlBintang", jmlBintang.get(position));
                bundle.putString("tglCek_in", tglCek_in);
                bundle.putString("tglCek_out", tglCek_out);
                bundle.putString("jumlahKamar", jumlahKamar);
                bundle.putString("jumlahMalam", jumlahMalam);
                bundle.putString("kotaAtauHotel", hotelAtauKota_srch);

                HotelSelected_BottomSheet hotelSelected_bottomSheet = new HotelSelected_BottomSheet();
                hotelSelected_bottomSheet.setArguments(bundle);
                hotelSelected_bottomSheet.show(getSupportFragmentManager(), hotelSelected_bottomSheet.getTag());


            }
        });









    }

    public void populateList(String hotelAtauKota_srch){
        nama_hotel.clear();
        tambahanAlamat.clear();
        harga.clear();
        jmlBintang.clear();
        gambarHotel.clear();

        switch (hotelAtauKota_srch){
            case "Surabaya":

                nama_hotel.add("Pop! Hotel Gubeng Surabaya");
                tambahanAlamat.add("Wonokromo, Surabaya");
                harga.add("IDR 350.000");
                jmlBintang.add(3);
                gambarHotel.add(R.drawable.pop_hotel);


                nama_hotel.add("Zest Hotel");
                tambahanAlamat.add("Kertajaya, Surabaya");
                harga.add("IDR 410.000");
                jmlBintang.add(3);
                gambarHotel.add(R.drawable.zest2_hotel);


                nama_hotel.add("The Life Hotel");
                tambahanAlamat.add("Menanggal, Surabaya");
                harga.add("IDR 470.000");
                jmlBintang.add(3);
                gambarHotel.add(R.drawable.thelife2_hotel);


                nama_hotel.add("Shangri-La");
                tambahanAlamat.add("Tunjungan, Surabaya");
                harga.add("IDR 780.000");
                jmlBintang.add(4);
                gambarHotel.add(R.drawable.pop_hotel);

                nama_hotel.add("Grand Mercure Surabaya");
                tambahanAlamat.add("Gubeng, Surabaya");
                harga.add("IDR 1.280.000");
                jmlBintang.add(5);
                gambarHotel.add(R.drawable.grandmercure_hotel);

                break;

            case "Bali":
                nama_hotel.add("ASTON Denpasar Hotel");
                tambahanAlamat.add("Denpasar, Bali");
                jmlBintang.add(3);
                harga.add("IDR 350.000");
                gambarHotel.add(R.drawable.pop_hotel);

                nama_hotel.add("Padma Resort Ubud");
                tambahanAlamat.add("Ubud, Bali");
                jmlBintang.add(4);
                harga.add("IDR 350.000");
                gambarHotel.add(R.drawable.pop_hotel);

                nama_hotel.add("Shangri-La");
                tambahanAlamat.add("Karangasem, Bali");
                jmlBintang.add(5);
                harga.add("IDR 350.000");
                gambarHotel.add(R.drawable.pop_hotel);
                break;

        }

        recycleAdapterHotelOptions.notifyDataSetChanged();



    }

    public class RecycleAdapterHotelOptions extends RecyclerView.Adapter<RecycleAdapterHotelOptions.ViewHolder>{
        ArrayList<String> nama_hotel;
        ArrayList<String> tambahanAlamat;
        ArrayList<String> harga;
        ArrayList<Integer> jmlBintang;
        ArrayList<Integer> gambarHotel;

        public RecycleAdapterHotelOptions(ArrayList<String> nama_hotel, ArrayList<String> tambahanAlamat, ArrayList<String> harga, ArrayList<Integer> jmlBintang, ArrayList<Integer> gambarHotel){
            this.nama_hotel = nama_hotel;
            this.tambahanAlamat = tambahanAlamat;
            this.harga = harga;
            this.jmlBintang= jmlBintang;
            this.gambarHotel= gambarHotel;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.hotel_options_single_view, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return  viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.namaHotel_txt.setText(nama_hotel.get(position));
            holder.alamat_txt.setText(tambahanAlamat.get(position));
            holder.harga_txt.setText(harga.get(position));
            holder.gambar.setImageResource(gambarHotel.get(position));

            switch (jmlBintang.get(position)){
                case 1:
                    holder.star1.setVisibility(View.VISIBLE);
                    holder.star2.setVisibility(View.INVISIBLE);
                    holder.star3.setVisibility(View.INVISIBLE);
                    holder.star4.setVisibility(View.INVISIBLE);
                    holder.star5.setVisibility(View.INVISIBLE);
                    break;

                case 2:
                    holder.star1.setVisibility(View.VISIBLE);
                    holder.star2.setVisibility(View.VISIBLE);
                    holder.star3.setVisibility(View.INVISIBLE);
                    holder.star4.setVisibility(View.INVISIBLE);
                    holder.star5.setVisibility(View.INVISIBLE);
                    break;


                case 3:
                    holder.star1.setVisibility(View.VISIBLE);
                    holder.star2.setVisibility(View.VISIBLE);
                    holder.star3.setVisibility(View.VISIBLE);
                    holder.star4.setVisibility(View.INVISIBLE);
                    holder.star5.setVisibility(View.INVISIBLE);
                    break;

                case 4:
                    holder.star1.setVisibility(View.VISIBLE);
                    holder.star2.setVisibility(View.VISIBLE);
                    holder.star3.setVisibility(View.VISIBLE);
                    holder.star4.setVisibility(View.VISIBLE);
                    holder.star5.setVisibility(View.INVISIBLE);
                    break;

                case 5:
                    holder.star1.setVisibility(View.VISIBLE);
                    holder.star2.setVisibility(View.VISIBLE);
                    holder.star3.setVisibility(View.VISIBLE);
                    holder.star4.setVisibility(View.VISIBLE);
                    holder.star5.setVisibility(View.VISIBLE);
                    break;


            }

        }

        @Override
        public int getItemCount() {
            return nama_hotel.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder{
            TextView namaHotel_txt;
            ImageView star1;
            ImageView star2;
            ImageView star3;
            ImageView star4;
            ImageView star5;
            ImageView gambar;
            TextView alamat_txt;
            TextView harga_txt;






            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                namaHotel_txt = itemView.findViewById(R.id.namaHotel_txt) ;
                star1 = itemView.findViewById(R.id.star1);
                star2 = itemView.findViewById(R.id.star2);
                star3 = itemView.findViewById(R.id.star3);
                star4 = itemView.findViewById(R.id.star4);
                star5 = itemView.findViewById(R.id.star5);
                gambar = itemView.findViewById(R.id.hotelImage);
                alamat_txt = itemView.findViewById(R.id.alamat_txt);
                harga_txt = itemView.findViewById(R.id.harga_txt);



            }


        }
    }



}