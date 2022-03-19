package com.example.projectpacto;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;


public class TambahanBagasiBottomSheet extends BottomSheetDialogFragment {

    RecyclerView recyclerView;
    ArrayList<String> nama;
    ArrayList<String> tambahan_kg;
    ArrayList<String> hargaTambahanBagasi;

    public TambahanBagasiBottomSheet() {
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_tambahan_bagasi_bottom_sheet, null);
        dialog.setContentView(view);
        Bundle bundle = this.getArguments();



        nama = bundle.getStringArrayList("namaList");
        tambahan_kg = bundle.getStringArrayList("tambahan_kg");
        hargaTambahanBagasi = bundle.getStringArrayList("harga_tambahan");

        Log.i("TAMBAHAN_KG", tambahan_kg.toString());

        recyclerView = view.findViewById(R.id.RecyclerViewTambahanBagasi);
        TambahanBagasiRecyclerAdapter tambahanBagasiRecyclerAdapter = new TambahanBagasiRecyclerAdapter(nama, tambahan_kg, hargaTambahanBagasi);
        recyclerView.setAdapter(tambahanBagasiRecyclerAdapter);



        return dialog;
    }


    public class TambahanBagasiRecyclerAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<TambahanBagasiRecyclerAdapter.ViewHolder> {
        ArrayList<String> nama;
        ArrayList<String> tambahan_kg;
        ArrayList<String> hargaTambahanBagasi;

        public TambahanBagasiRecyclerAdapter( ArrayList<String> nama,ArrayList<String> tambahan_kg, ArrayList<String> hargaTambahanBagasi ){
            this.hargaTambahanBagasi = hargaTambahanBagasi;
            this.tambahan_kg = tambahan_kg;
            this.nama = nama;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.tambahan_opsi_bagasi_single_view, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            int nomorPenumpang =  position + 1;
            int position_int = position;
            holder.nomorPenumpang.setText(nomorPenumpang + ". ");
            holder.namaPenumpang.setText(nama.get(position));

            holder.radioButton1.setBackground(getResources().getDrawable(R.drawable.curved__even_less_colorized_bg));
            holder.default_harga.setTextColor(getResources().getColor(R.color.primary));
            holder.default_weight.setTextColor(getResources().getColor(R.color.primary));



            holder.radioButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    holder.radioButton1.setBackground(getResources().getDrawable(R.drawable.curved_less_bg_outline));
                    holder.radioButton2.setBackground(getResources().getDrawable(R.drawable.curved_less_bg_outline));
                    holder.radioButton3.setBackground(getResources().getDrawable(R.drawable.curved_less_bg_outline));
//                    holder.default_harga.setTextColor(getResources().getColor(R.color.black));
//                    holder.default_weight.setTextColor(getResources().getColor(R.color.black));
                    holder.hargaTambahan_1.setTextColor(getResources().getColor(R.color.black));
                    holder.tambahanKG_1.setTextColor(getResources().getColor(R.color.black));
                    holder.tambahanKG_2.setTextColor(getResources().getColor(R.color.black));
                    holder.hargaTambahan_2.setTextColor(getResources().getColor(R.color.black));

                    holder.radioButton1.setBackground(getResources().getDrawable(R.drawable.curved__even_less_colorized_bg));
                    holder.default_harga.setTextColor(getResources().getColor(R.color.primary));
                    holder.default_weight.setTextColor(getResources().getColor(R.color.primary));

                    tambahan_kg.set(position_int, holder.default_weight.getText().toString());
                    hargaTambahanBagasi.set(position_int, holder.default_harga.getText().toString());

                }
            });

            holder.radioButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.radioButton1.setBackground(getResources().getDrawable(R.drawable.curved_less_bg_outline));
//                    holder.radioButton2.setBackground(getResources().getDrawable(R.drawable.curved_less_bg_outline));
                    holder.radioButton3.setBackground(getResources().getDrawable(R.drawable.curved_less_bg_outline));
                    holder.default_harga.setTextColor(getResources().getColor(R.color.black));
                    holder.default_weight.setTextColor(getResources().getColor(R.color.black));
//                    holder.hargaTambahan_1.setTextColor(getResources().getColor(R.color.black));
//                    holder.tambahanKG_1.setTextColor(getResources().getColor(R.color.black));
                    holder.tambahanKG_2.setTextColor(getResources().getColor(R.color.black));
                    holder.hargaTambahan_2.setTextColor(getResources().getColor(R.color.black));


                    holder.radioButton2.setBackground(getResources().getDrawable(R.drawable.curved__even_less_colorized_bg));
                    holder.tambahanKG_1.setTextColor(getResources().getColor(R.color.primary));
                    holder.hargaTambahan_1.setTextColor(getResources().getColor(R.color.primary));

                    tambahan_kg.set(position_int, holder.tambahanKG_1.getText().toString());
                    hargaTambahanBagasi.set(position_int, holder.hargaTambahan_1.getText().toString());
                }
            });

            holder.radioButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.radioButton1.setBackground(getResources().getDrawable(R.drawable.curved_less_bg_outline));
                    holder.radioButton2.setBackground(getResources().getDrawable(R.drawable.curved_less_bg_outline));
//                    holder.radioButton3.setBackground(getResources().getDrawable(R.drawable.curved_less_bg_outline));
                    holder.default_harga.setTextColor(getResources().getColor(R.color.black));
                    holder.default_weight.setTextColor(getResources().getColor(R.color.black));
                    holder.hargaTambahan_1.setTextColor(getResources().getColor(R.color.black));
                    holder.tambahanKG_1.setTextColor(getResources().getColor(R.color.black));
//                    holder.tambahanKG_2.setTextColor(getResources().getColor(R.color.black));
//                    holder.hargaTambahan_2.setTextColor(getResources().getColor(R.color.black));


                    holder.radioButton3.setBackground(getResources().getDrawable(R.drawable.curved__even_less_colorized_bg));
                    holder.tambahanKG_2.setTextColor(getResources().getColor(R.color.primary));
                    holder.hargaTambahan_2.setTextColor(getResources().getColor(R.color.primary));

                    tambahan_kg.set(position_int, holder.tambahanKG_2.getText().toString());
                    hargaTambahanBagasi.set(position_int, holder.hargaTambahan_2.getText().toString());
                }
            });






        }



        @Override
        public int getItemCount() {
            return nama.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView nomorPenumpang;
            TextView namaPenumpang;
            TextView default_weight;
            TextView default_harga;
            TextView tambahanKG_1;
            TextView hargaTambahan_1;
            TextView tambahanKG_2;
            TextView hargaTambahan_2;
            LinearLayout radioButton1;
            LinearLayout radioButton2;
            LinearLayout radioButton3;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                nomorPenumpang = itemView.findViewById(R.id.nomorPenumpang);
                namaPenumpang = itemView.findViewById(R.id.namaPenumpang);
                default_harga = itemView.findViewById(R.id.default_harga);
                default_weight = itemView.findViewById(R.id.default_weight);
                tambahanKG_1 = itemView.findViewById(R.id.tambahanKG_1);
                hargaTambahan_1 = itemView.findViewById(R.id.hargaTambahan_1);
                tambahanKG_2 = itemView.findViewById(R.id.tambahanKG_2);
                hargaTambahan_2 = itemView.findViewById(R.id.hargaTambahan_2);
                radioButton1 =  itemView.findViewById(R.id.radioButton1);
                radioButton2 =  itemView.findViewById(R.id.radioButton2);
                radioButton3 =  itemView.findViewById(R.id.radioButton3);
            }
        }
    }
}