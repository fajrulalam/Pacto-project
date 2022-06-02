package com.example.projectpacto.roundtrip;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projectpacto.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;


public class DetailHarga_BottemSheet_PulangPergi extends BottomSheetDialogFragment {

    //Views
    ImageView dotpointBalita;
    ImageView dotpointBalita_pulang;
    TextView jmlDewasa;
    TextView jmlBalita;
    TextView jmlDewasa_pulang;
    TextView jmlBalita_pulang;
    TextView kotaAsal_pulang;
    TextView kotaAsal;
    TextView kotaTujuan_pulang;
    TextView kotaTujuan;
    TextView grandtotal;
    TextView subtotalDewasa;
    TextView subtotalDewasa_pulang;
    TextView subtotalBalita;
    TextView subtotalBalita_pulang;
    RecyclerView bagasiPulang;
    RecyclerView bagasiPergi;




    //Variables
    String harga;
    String harga_pulang;
    String kotaAsal_str;
    String kotaTujuan_str;
    String kotaAsal_str_pulang;
    String kotaTujuan_str_pulang;
    String jmlDewasa_str;
    String jmlAnak_str;
    String jmlBalita_str;
    ArrayList<String> tambahan_kg;
    ArrayList<String> harga_tambahan;
    ArrayList<String>  tambahan_kg_pulang;
    ArrayList<String> harga_tambahan_pulang;
    String harga_dewasa;
    String harga_dewasa_pulang;
    String harga_balita;
    String harga_balita_pulang;



    public DetailHarga_BottemSheet_PulangPergi() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_detail_harga__bottem_sheet__pulang_pergi, null);
        dialog.setContentView(view);
        Bundle bundle = this.getArguments();

        //Textview binding
        jmlDewasa = view.findViewById(R.id.jmlDewasa);
        jmlBalita = view.findViewById(R.id.jmlBalita);
        jmlDewasa_pulang = view.findViewById(R.id.jmlDewasa_pulang);
        jmlBalita_pulang = view.findViewById(R.id.jmlBalita_pulang);
        kotaAsal_pulang = view.findViewById(R.id.kotaAsal_pulang);
        kotaAsal = view.findViewById(R.id.kotaAsal);
        kotaTujuan_pulang = view.findViewById(R.id.kotaTujuan_pulang);
        kotaTujuan = view.findViewById(R.id.kotaTujuan);
        subtotalDewasa = view.findViewById(R.id.subtotalDewasa);
        subtotalDewasa_pulang = view.findViewById(R.id.subtotalDewasa_pulang);
        subtotalBalita = view.findViewById(R.id.subtotalBalita);
        subtotalBalita_pulang = view.findViewById(R.id.subtotalBalita_pulang);
        grandtotal = view.findViewById(R.id.grandtotal);
        dotpointBalita = view.findViewById(R.id.dotpoinBalita);
        dotpointBalita_pulang = view.findViewById(R.id.dotpoinBalita_pulang);

        //RecyclerView Binding
        bagasiPulang = view.findViewById(R.id.fasilitasEkstra_Pulang_RecyclerView);
        bagasiPergi = view.findViewById(R.id.fasilitasEkstra_Pergi_RecyclerView);


        kotaAsal_str = bundle.getString("kotaAsal");
        kotaTujuan_str = bundle.getString("kotaTujuan");
        kotaAsal_str_pulang = bundle.getString("kotaAsal_pulang");
        kotaTujuan_str_pulang = bundle.getString("kotaTujuan_pulang");
        jmlDewasa_str = bundle.getString("jmlDewasa");
        jmlAnak_str = bundle.getString("jmlAnak");
        jmlBalita_str = bundle.getString("jmlBalita");
        harga_tambahan = bundle.getStringArrayList("harga_tambahan");
        tambahan_kg = bundle.getStringArrayList("tambahan_kg");
        harga_tambahan_pulang = bundle.getStringArrayList("harga_tambahan_pulang");
        tambahan_kg_pulang= bundle.getStringArrayList("tambahan_kg_pulang");
        harga_dewasa = bundle.getString("hargaDewasa");
        harga_balita = bundle.getString("hargaBalita");
        harga_dewasa_pulang = bundle.getString("hargaDewasa_pulang");
        harga_balita_pulang = bundle.getString("hargaBalita_pulang");
        harga = bundle.getString("harga");
        harga_pulang = bundle.getString("harga_pulang");


        kotaAsal_pulang.setText(kotaAsal_str_pulang);
        kotaAsal.setText(kotaAsal_str);
        kotaTujuan_pulang.setText(kotaTujuan_str_pulang);
        kotaTujuan.setText(kotaTujuan_str);


        int jumlDewasa_anak = Integer.parseInt(jmlDewasa_str) + Integer.parseInt(jmlAnak_str);
        jmlDewasa.setText("Dewasa/Anak (x" + jumlDewasa_anak + ")");
        jmlDewasa_pulang.setText("Dewasa/Anak (x" + jumlDewasa_anak + ")");
        subtotalDewasa.setText(harga_dewasa);
        subtotalDewasa_pulang.setText(harga_dewasa_pulang);


//        grandtotal.setText(grandtotal_str);

        Log.i("hargaArraylist", "arraylist harga  " + harga_tambahan );

        jmlBalita_pulang.setVisibility(View.GONE);
        jmlBalita.setVisibility(View.GONE);
        subtotalBalita.setVisibility(View.GONE);
        dotpointBalita.setVisibility(View.GONE);
        dotpointBalita_pulang.setVisibility(View.GONE);
        subtotalBalita_pulang.setVisibility(View.GONE);
        if (!jmlBalita_str.matches("0")){
            dotpointBalita.setVisibility(View.VISIBLE);
            dotpointBalita_pulang.setVisibility(View.VISIBLE);
            jmlBalita_pulang.setVisibility(View.VISIBLE);
            jmlBalita.setVisibility(View.VISIBLE);
            subtotalBalita.setVisibility(View.VISIBLE);
            subtotalBalita_pulang.setVisibility(View.VISIBLE);
            subtotalBalita.setText(harga_balita);
            subtotalBalita_pulang.setText(harga_balita_pulang);

            jmlBalita.setText("Balita (x"+jmlBalita_str+")");
            jmlBalita_pulang.setText("Balita (x"+jmlBalita_str+")");

        }





        //RecyclerAdapater
        RincianHargaBagasiRecyclerAdapater rincianHargaBagasiRecyclerAdapater = new RincianHargaBagasiRecyclerAdapater(tambahan_kg, harga_tambahan);
        bagasiPergi.setAdapter(rincianHargaBagasiRecyclerAdapater);

        RincianHargaBagasiRecyclerAdapater rincianHargaBagasiRecyclerAdapater_pulang = new RincianHargaBagasiRecyclerAdapater(tambahan_kg_pulang, harga_tambahan_pulang);
        bagasiPulang.setAdapter(rincianHargaBagasiRecyclerAdapater_pulang);

        //Hitung Grand total
        int totalBagasi = 0;
        for (int i = 0; i < harga_tambahan.size(); i++){
            Integer harga_satuan_bagasi = Integer.parseInt(harga_tambahan.get(i).split("IDR ")[1].replace(".", ""));
            Integer harga_satuan_bagasi_pulang = Integer.parseInt(harga_tambahan_pulang.get(i).split("IDR ")[1].replace(".", ""));
            totalBagasi = totalBagasi + harga_satuan_bagasi + harga_satuan_bagasi_pulang;
        }
        Log.i("total bagasi pergi", "IDR " + totalBagasi );
        int harga_int = Integer.parseInt(harga.split("IDR ")[1].replace(".", ""));
//        int harga_int_pulang = Integer.parseInt(harga_pulang.split("IDR ")[1].replace(".", ""));
        int grandTotal_int = harga_int  + totalBagasi;

        String grandTotal_str = "IDR " + String.format("%,d", grandTotal_int).replace(',', '.');

        grandtotal.setText(grandTotal_str);

        view.findViewById(R.id.closeSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });




        return dialog;


    }

    public static class RincianHargaBagasiRecyclerAdapater extends RecyclerView.Adapter<RincianHargaBagasiRecyclerAdapater.ViewHolder>{
        ArrayList<String> mtambahan_kg;
        ArrayList<String> mharga_tambahanKG;

        public RincianHargaBagasiRecyclerAdapater(ArrayList<String> mtambahan_kg, ArrayList<String> mharga_tambahanKG) {
            this.mtambahan_kg = mtambahan_kg;
            this.mharga_tambahanKG = mharga_tambahanKG;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.detail_harga_single_view, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


            if (!mharga_tambahanKG.get(position).matches("IDR 0")){
                holder.row.setVisibility(View.VISIBLE);
                holder.tambahan_kg.setText("Bagasi (+" +mtambahan_kg.get(position)+")");
                holder.harga_tambahanKG.setText(mharga_tambahanKG.get(position));
            }

        }

        @Override
        public int getItemCount() {
            return mharga_tambahanKG.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ConstraintLayout row;
            TextView tambahan_kg;
            TextView harga_tambahanKG;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                row = itemView.findViewById(R.id.row);
                tambahan_kg = itemView.findViewById(R.id.tambahan_kg);
                harga_tambahanKG = itemView.findViewById(R.id.harga_tambahanKG);


            }
        }
    }




}

