package com.example.projectpacto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapterPlaneTicket extends RecyclerView.Adapter<RecyclerAdapterPlaneTicket.ViewHolder> {
    ArrayList<String> jumlahPax;
    ArrayList<Integer> logoMaskapai;
    ArrayList<String>namaMaskapai;
    ArrayList<String>waktuBerangkat;
    ArrayList<String>bandaraAsal;
    ArrayList<String> durasi;
    ArrayList<String> langsungAtauTransit;
    ArrayList<String> waktuDatang;
    ArrayList<String> bandaraTujuan;
    ArrayList<Boolean> testCovid;
    ArrayList<String> harga;

    public RecyclerAdapterPlaneTicket( ArrayList<String> jumlahPax,  ArrayList<Integer> logoMaskapai, ArrayList<String> namaMaskapai, ArrayList<String> waktuBerangkat, ArrayList<String> bandaraAsal, ArrayList<String> durasi, ArrayList<String> langsungAtauTransit, ArrayList<String> waktuDatang, ArrayList<String> bandaraTujuan, ArrayList<Boolean> testCovid, ArrayList<String> harga){
        this.logoMaskapai = logoMaskapai;
        this.namaMaskapai = namaMaskapai;
        this.waktuBerangkat = waktuBerangkat;
        this.bandaraAsal = bandaraAsal;
        this.durasi = durasi;
        this.langsungAtauTransit = langsungAtauTransit;
        this.waktuDatang = waktuDatang;
        this.bandaraTujuan = bandaraTujuan;
        this.testCovid = testCovid;
        this.harga = harga;
        this.jumlahPax = jumlahPax;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.ticket_single_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.jumlahPax.setText(jumlahPax.get(position));
        holder.logoMaskapai.setImageResource(logoMaskapai.get(position));
        holder.namaMaskapai.setText(namaMaskapai.get(position));
        holder.waktuBerangkat.setText(waktuBerangkat.get(position));
        holder.bandaraAsal.setText(bandaraAsal.get(position));
        holder.durasi.setText(durasi.get(position));
        holder.langsungAtauTransit.setText(langsungAtauTransit.get(position));
        holder.waktuDatang.setText(waktuDatang.get(position));
        holder.bandaraTujuan.setText(bandaraTujuan.get(position));
        holder.harga.setText(harga.get(position));

        if (testCovid.get(position) == false) {
            holder.testCovid.setVisibility(View.INVISIBLE);
        }



    }

    @Override
    public int getItemCount() {
        return namaMaskapai.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView logoMaskapai;
        TextView namaMaskapai;
        TextView waktuBerangkat;
        TextView bandaraAsal;
        TextView jumlahPax;
        TextView durasi;
        TextView langsungAtauTransit;
        TextView waktuDatang;
        TextView bandaraTujuan;
        LinearLayout testCovid;
        TextView harga;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            jumlahPax = itemView.findViewById(R.id.jumlahPax);
            logoMaskapai = itemView.findViewById(R.id.logoMaskapai);
            namaMaskapai = itemView.findViewById(R.id.namaMaskapai);
            waktuBerangkat = itemView.findViewById(R.id.waktuBerangkat);
            bandaraAsal = itemView.findViewById(R.id.bandaraAsal);
            durasi = itemView.findViewById(R.id.durasi);
            langsungAtauTransit = itemView.findViewById(R.id.langsungAtauTransit);
            waktuDatang = itemView.findViewById(R.id.waktuDatang);
            bandaraTujuan = itemView.findViewById(R.id.bandaraTujuan);
            testCovid = itemView.findViewById(R.id.testCovid);
            harga = itemView.findViewById(R.id.harga);
        }


    }


}
