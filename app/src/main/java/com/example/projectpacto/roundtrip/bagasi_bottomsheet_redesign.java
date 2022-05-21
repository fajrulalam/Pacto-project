package com.example.projectpacto.roundtrip;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectpacto.Hotel_KotaAtauAkomodasi;
import com.example.projectpacto.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;


public class bagasi_bottomsheet_redesign extends BottomSheetDialogFragment {

    RecyclerView recyclerViewTambahanBagasi;
    ArrayList<String> namaPassenger;
    ArrayList<String> tambahan_kg;
    ArrayList<String> harga_tambahan;
    ArrayList<String> tambahan_kg_pulang;
    ArrayList<String> harga_tambahan_pulang;




    public bagasi_bottomsheet_redesign() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_bagasi_bottomsheet_redesign, null);
        dialog.setContentView(view);

        recyclerViewTambahanBagasi = view.findViewById(R.id.RecyclerViewTambahanBagasi);


        namaPassenger = new ArrayList<>();
        tambahan_kg = new ArrayList<>();
        harga_tambahan = new ArrayList<>();
        tambahan_kg_pulang = new ArrayList<>();
        harga_tambahan_pulang = new ArrayList<>();

        //tes aja sih
        namaPassenger.add("tes bruh");
        tambahan_kg.add("tes bruh");
        harga_tambahan.add("tes bruh");
        tambahan_kg_pulang.add("tes bruh");
        harga_tambahan_pulang.add("tes bruh");
        namaPassenger.add("tes bruh");
        tambahan_kg.add("tes bruh");
        harga_tambahan.add("tes bruh");
        tambahan_kg_pulang.add("tes bruh");
        harga_tambahan_pulang.add("tes bruh");
        namaPassenger.add("tes bruh");
        tambahan_kg.add("tes bruh");
        harga_tambahan.add("tes bruh");
        tambahan_kg_pulang.add("tes bruh");
        harga_tambahan_pulang.add("tes bruh");


        RecyclerAdapterBagasiHorizontal recyclerAdapterBagasiHorizontal = new RecyclerAdapterBagasiHorizontal(namaPassenger, tambahan_kg, harga_tambahan, tambahan_kg_pulang, harga_tambahan_pulang);
        recyclerViewTambahanBagasi.setAdapter(recyclerAdapterBagasiHorizontal);


        view.findViewById(R.id.closeSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });






        return dialog;
    }

    public static class RecyclerAdapterBagasiHorizontal extends RecyclerView.Adapter<RecyclerAdapterBagasiHorizontal.ViewHolder> {
        ArrayList<String> namaPassenger;
        ArrayList<String> tambahan_kg;
        ArrayList<String> harga_tambahan;
        ArrayList<String> tambahan_kg_pulang;
        ArrayList<String> harga_tambahan_pulang;

        public RecyclerAdapterBagasiHorizontal(ArrayList<String> namaPassenger, ArrayList<String> tambahan_kg, ArrayList<String> harga_tambahan, ArrayList<String> tambahan_kg_pulang, ArrayList<String> harga_tambahan_pulang) {
            this.namaPassenger = namaPassenger;
            this.tambahan_kg = tambahan_kg;
            this.harga_tambahan = harga_tambahan;
            this.tambahan_kg_pulang = tambahan_kg_pulang;
            this.harga_tambahan_pulang = harga_tambahan_pulang;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.bagasi_penumpang_horizontal_single_view, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return  viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            holder.nomordanNamaPassenger.setText(position+1 + ". " + namaPassenger.get(position));
            holder.bagasiPergi.setText(tambahan_kg.get(position));
            holder.bagasiPulang.setText(tambahan_kg_pulang.get(position));

        }

        @Override
        public int getItemCount() {
            return namaPassenger.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder{
            TextView nomordanNamaPassenger;
            TextView bagasiPergi;
            TextView bagasiPulang;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                nomordanNamaPassenger = itemView.findViewById(R.id.nomordanNamaPassenger);
                bagasiPergi = itemView.findViewById(R.id.bagasiPergi);
                bagasiPulang = itemView.findViewById(R.id.bagasiPulang);
            }


        }




    }
}