package com.example.projectpacto;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectpacto.roundtrip.DetailHarga_BottemSheet_PulangPergi;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;


public class DetailHargaBottomSheet_Pergi extends BottomSheetDialogFragment {


    //Views
    TextView jmlDewasa;
    TextView jmlBalita;

    TextView kotaAsal;
    TextView kotaTujuan;
    TextView grandtotal;
    TextView subtotalDewasa;
    TextView subtotalBalita;
    RecyclerView bagasiPergi;
    ImageView dotpointBalita;


    //Variables
    String harga;
    String kotaAsal_str;
    String kotaTujuan_str;
    String jmlDewasa_str;
    String jmlAnak_str;
    String jmlBalita_str;
    ArrayList<String> tambahan_kg;
    ArrayList<String> harga_tambahan;
    String harga_dewasa;
    String harga_balita;

    public DetailHargaBottomSheet_Pergi() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_detail_harga_bottom_sheet__pergi, null);
        dialog.setContentView(view);
        Bundle bundle = this.getArguments();



        //Textview binding
        jmlDewasa = view.findViewById(R.id.jmlDewasa);
        jmlBalita = view.findViewById(R.id.jmlBalita);
        kotaAsal = view.findViewById(R.id.kotaAsal);
        kotaTujuan = view.findViewById(R.id.kotaTujuan);
        subtotalDewasa = view.findViewById(R.id.subtotalDewasa);
        subtotalBalita = view.findViewById(R.id.subtotalBalita);
        grandtotal = view.findViewById(R.id.grandtotal);
        dotpointBalita = view.findViewById(R.id.dotpoinBalita);

        //RecyclerView Binding
        bagasiPergi = view.findViewById(R.id.fasilitasEkstra_Pergi_RecyclerView);


        kotaAsal_str = bundle.getString("kotaAsal");
        kotaTujuan_str = bundle.getString("kotaTujuan");
        jmlDewasa_str = bundle.getString("jmlDewasa");
        jmlAnak_str = bundle.getString("jmlAnak");
        jmlBalita_str = bundle.getString("jmlBalita");
        harga_tambahan = bundle.getStringArrayList("harga_tambahan");
        tambahan_kg = bundle.getStringArrayList("tambahan_kg");
        harga_dewasa = bundle.getString("hargaDewasa");
        harga_balita = bundle.getString("hargaBalita");
        harga = bundle.getString("harga");


        kotaAsal.setText(kotaAsal_str);
        kotaTujuan.setText(kotaTujuan_str);
        grandtotal.setText(harga);


        int jumlDewasa_anak = Integer.parseInt(jmlDewasa_str) + Integer.parseInt(jmlAnak_str);
        jmlDewasa.setText("Dewasa/Anak (x" + jumlDewasa_anak + ")");
        subtotalDewasa.setText(harga_dewasa);

        jmlBalita.setVisibility(View.GONE);
        subtotalBalita.setVisibility(View.GONE);
        dotpointBalita.setVisibility(View.GONE);
        if (!jmlBalita_str.matches("0")){
            dotpointBalita.setVisibility(View.VISIBLE);
            jmlBalita.setVisibility(View.VISIBLE);
            subtotalBalita.setVisibility(View.VISIBLE);
            subtotalBalita.setText(harga_balita);

            jmlBalita.setText("Balita (x"+jmlBalita_str+")");

        }

        DetailHarga_BottemSheet_PulangPergi.RincianHargaBagasiRecyclerAdapater rincianHargaBagasiRecyclerAdapater = new DetailHarga_BottemSheet_PulangPergi.RincianHargaBagasiRecyclerAdapater(tambahan_kg, harga_tambahan);
        bagasiPergi.setAdapter(rincianHargaBagasiRecyclerAdapater);




        return dialog;
    }
}