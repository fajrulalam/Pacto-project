package com.example.projectpacto;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class Hotel_KotaAtauAkomodasi extends BottomSheetDialogFragment {
    private LinearLayout appBarLayout;
    private LinearLayout linearLayout;
    private ImageView closeSheet;
    private TextView judul;
    private TextView kotaTextView;
    private TextView hotelTextView;

    private TextInputLayout textInputLayout;
    private TextView cariButton;
    private RecyclerView recyclerViewKota;
    private RecyclerView recyclerViewHotel;

    private ArrayList<String> nama_hotel;
    private ArrayList<String> namaKota;
    private ArrayList<String> nama_provinsiNegara;
    private ArrayList<String> nama_kotaProvinsi;

    private KeberangkatanDanKedatangan.OnDataKeberangkatanAtauKepulangan datapasser;


    public Hotel_KotaAtauAkomodasi() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_keberangkatan_dan_kedatangan, null);
        dialog.setContentView(view);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);


        appBarLayout = view.findViewById(R.id.appBarLayout);
        linearLayout = view.findViewById(R.id.linearLayout);
        closeSheet  = view.findViewById(R.id.closeSheet);
        judul = view.findViewById(R.id.KedatanganAtauKeberangkatan);
        kotaTextView = view.findViewById(R.id.kotaTextView);
        hotelTextView = view.findViewById(R.id.HotelTextView);
        textInputLayout = view.findViewById(R.id.bandaraKota_textInput);
        cariButton = view.findViewById(R.id.cariBandaraButton);
        recyclerViewKota = view.findViewById(R.id.RecycleViewKota);
        recyclerViewHotel = view.findViewById(R.id.RecycleViewHotel);



        nama_provinsiNegara = new ArrayList<>();
        nama_kotaProvinsi = new ArrayList<>();
        namaKota = new ArrayList<>();
        nama_hotel = new ArrayList<>();

        RecyclerAdapterKota recyclerAdapterKota = new RecyclerAdapterKota(namaKota, nama_provinsiNegara);
        recyclerViewKota.setAdapter(recyclerAdapterKota);

        RecyclerAdapterHotel recyclerAdapterHotel = new RecyclerAdapterHotel(nama_hotel, nama_kotaProvinsi);
        recyclerViewHotel.setAdapter(recyclerAdapterHotel);


        cariButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama_provinsiNegara.clear();
                nama_kotaProvinsi.clear();
                namaKota.clear();
                nama_kotaProvinsi.clear();
                switch (textInputLayout.getEditText().getText().toString()) {
                    case "surabaya":
                        namaKota.add("Surabaya");
                        nama_provinsiNegara.add("Jawa Timur, Indonesia");


                        nama_hotel.add("Pop! Hotel Gubeng Surabaya");
                        nama_kotaProvinsi.add("Surabaya, Jawa Timur");
                        nama_hotel.add("Zest Hotel");
                        nama_kotaProvinsi.add("Surabaya, Jawa Timur");
                        nama_hotel.add("The Life Hotel");
                        nama_kotaProvinsi.add("Surabaya, Jawa Timur");
                        nama_hotel.add("Shangri-La");
                        nama_kotaProvinsi.add("Surabaya, Jawa Timur");


                        break;
                    case "bali":
                        namaKota.add("Bali");
                        nama_provinsiNegara.add("Bali, Indonesia");

                        nama_hotel.add("ASTON Denpasar Hotel");
                        nama_kotaProvinsi.add("Denpasar, Bali");

                        nama_hotel.add("Padma Resort Ubud");
                        nama_kotaProvinsi.add("Ubud, Bali");

                        nama_hotel.add("Shangri-La");
                        nama_kotaProvinsi.add("Karangasem, Bali");


                    case "shangri la":
                        nama_hotel.add("Shangri-La Bali");
                        nama_kotaProvinsi.add("Karangasem, Bali");

                        nama_hotel.add("Shangri-La Surabaya");
                        nama_kotaProvinsi.add("Surabaya, Jawa Timur");

                        nama_hotel.add("Shangri-La Jakarta");
                        nama_kotaProvinsi.add("Jakarta Pusat, DKI Jakarta");

                        break;
                }
                recyclerAdapterKota.notifyDataSetChanged();
                recyclerAdapterHotel.notifyDataSetChanged();
            }
        });


        closeSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



        return dialog;
    }


    public static class RecyclerAdapterKota extends RecyclerView.Adapter<RecyclerAdapterKota.ViewHolder> {

        ArrayList<String> namaKota;
        ArrayList<String> nama_provinsiNegara;

        public RecyclerAdapterKota(ArrayList<String> namaKota, ArrayList<String> nama_provinsiNegara){
            this.namaKota = namaKota;
            this.nama_provinsiNegara = nama_provinsiNegara;

        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.bandara_single_view, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return  viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bandaraTextView.setText(nama_provinsiNegara.get(position));
            holder.kotaTextView.setText(String.valueOf(namaKota.get(position)));
        }



        @Override
        public int getItemCount() {
            return namaKota.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder{
            TextView bandaraTextView;
            TextView kotaTextView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                bandaraTextView = itemView.findViewById(R.id.namaBandara);
                kotaTextView = itemView.findViewById(R.id.namaKota);
            }


        }




    }


    public static class RecyclerAdapterHotel extends RecyclerView.Adapter<RecyclerAdapterHotel.ViewHolder> {

        ArrayList<String> namaHotel;
        ArrayList<String> nama_kotaProvinsi;

        public RecyclerAdapterHotel(ArrayList<String> namaHotel, ArrayList<String> nama_kotaProvinsi){
            this.namaHotel = namaHotel;
            this.nama_kotaProvinsi = nama_kotaProvinsi;

        }
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.hotel_single_view, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return  viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bandaraTextView.setText(nama_kotaProvinsi.get(position));
            holder.kotaTextView.setText(String.valueOf(namaHotel.get(position)));
        }



        @Override
        public int getItemCount() {
            return namaHotel.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder{
            TextView bandaraTextView;
            TextView kotaTextView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                bandaraTextView = itemView.findViewById(R.id.namaBandara);
                kotaTextView = itemView.findViewById(R.id.namaKota);
            }


        }




    }
}