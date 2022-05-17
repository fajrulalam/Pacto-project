package com.example.projectpacto;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapterPlaneTicket_v2 extends RecyclerView.Adapter<RecyclerAdapterPlaneTicket_v2.ViewHolder> {
    ArrayList<ArrayList<String>> kodePenerbangan_outer;
    ArrayList<ArrayList<Integer>> logoMaskapai_outer;
    ArrayList<ArrayList<String>> namaMaskapai_outer;
    ArrayList<ArrayList<String>> waktuBerangkat_outer;
    ArrayList<ArrayList<String>> bandaraAsal_outer;
    ArrayList<ArrayList<String>> kabin_outer;
    ArrayList<ArrayList<String>> bagasi_outer;
    ArrayList<ArrayList<Boolean>> booleanMakan_outer;
    ArrayList<ArrayList<String>> keteranganMakan_outer;
    ArrayList<ArrayList<String>> modelPesawat_outer;
    ArrayList<ArrayList<String>> durasi_outer;
    ArrayList<ArrayList<String>> waktuDatang_outer;
    ArrayList<ArrayList<String>> bandaraTujuan_outer;
    ArrayList<ArrayList<String>> tanggalBerangkat_outer;
    ArrayList<ArrayList<String>> tanggalDatang_outer;
    ArrayList<Boolean> testCovid;
    ArrayList<String> harga;
    ArrayList<String> jumlahpax;

    public RecyclerAdapterPlaneTicket_v2(ArrayList<ArrayList<String>> kodePenerbangan_outer, ArrayList<ArrayList<Integer>> logoMaskapai_outer, ArrayList<ArrayList<String>> namaMaskapai_outer, ArrayList<ArrayList<String>> waktuBerangkat_outer, ArrayList<ArrayList<String>> bandaraAsal_outer, ArrayList<ArrayList<String>> kabin_outer, ArrayList<ArrayList<String>> bagasi_outer, ArrayList<ArrayList<Boolean>> booleanMakan_outer, ArrayList<ArrayList<String>> keteranganMakan_outer, ArrayList<ArrayList<String>> modelPesawat_outer, ArrayList<ArrayList<String>> durasi_outer, ArrayList<ArrayList<String>> waktuDatang_outer, ArrayList<ArrayList<String>> bandaraTujuan_outer, ArrayList<ArrayList<String>> tanggalBerangkat_outer, ArrayList<ArrayList<String>> tanggalDatang_outer, ArrayList<Boolean> testCovid, ArrayList<String> harga, ArrayList<String> jumlahpax) {
        this.kodePenerbangan_outer = kodePenerbangan_outer;
        this.logoMaskapai_outer = logoMaskapai_outer;
        this.namaMaskapai_outer = namaMaskapai_outer;
        this.waktuBerangkat_outer = waktuBerangkat_outer;
        this.bandaraAsal_outer = bandaraAsal_outer;
        this.kabin_outer = kabin_outer;
        this.bagasi_outer = bagasi_outer;
        this.booleanMakan_outer = booleanMakan_outer;
        this.keteranganMakan_outer = keteranganMakan_outer;
        this.modelPesawat_outer = modelPesawat_outer;
        this.durasi_outer = durasi_outer;
        this.waktuDatang_outer = waktuDatang_outer;
        this.bandaraTujuan_outer = bandaraTujuan_outer;
        this.tanggalBerangkat_outer = tanggalBerangkat_outer;
        this.tanggalDatang_outer = tanggalDatang_outer;
        this.testCovid = testCovid;
        this.harga = harga;
        this.jumlahpax = jumlahpax;
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

        holder.jumlahPax.setText(jumlahpax.get(position));
        holder.logoMaskapai.setImageResource( logoMaskapai_outer.get(position).get(0));
        holder.namaMaskapai.setText(namaMaskapai_outer.get(position).get(0));
        holder.waktuBerangkat.setText(waktuBerangkat_outer.get(position).get(0));
        String kodeBandaraAsal =  bandaraAsal_outer.get(position).get(0).split("\\(")[1].replace(")", "");
        holder.bandaraAsal.setText(kodeBandaraAsal);
        holder.durasi.setText(durasi_outer.get(position).get(0));
        holder.waktuDatang.setText(waktuDatang_outer.get(position).get(waktuDatang_outer.get(position).size()-1));
        String kodeBandaraTujuan =  bandaraTujuan_outer.get(position).get(0).split("\\(")[1].replace(")", "");
        holder.bandaraTujuan.setText(kodeBandaraTujuan);
        holder.harga.setText(harga.get(position));

        if (bandaraTujuan_outer.get(position).size() == 1){
            holder.langsungAtauTransit.setText("langsung");
        } else {
            int jml_transit = bandaraTujuan_outer.get(position).size() - 1;
            holder.langsungAtauTransit.setText(jml_transit +"x transit");
        }

        if (testCovid.get(position) == false) {
            holder.testCovid.setVisibility(View.INVISIBLE);
        }



    }

    @Override
    public int getItemCount() {
        return namaMaskapai_outer.size();
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
