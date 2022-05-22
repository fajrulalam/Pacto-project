package com.example.projectpacto.roundtrip;

import android.app.Dialog;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.projectpacto.Hotel_KotaAtauAkomodasi;
import com.example.projectpacto.ItemClickSupport;
import com.example.projectpacto.R;
import com.example.projectpacto.databinding.FragmentBagasiBottomsheetRedesignBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;


public class bagasi_bottomsheet_redesign extends BottomSheetDialogFragment {

    FragmentBagasiBottomsheetRedesignBinding holder;
    RecyclerView recyclerViewTambahanBagasi;
    ArrayList<String> namaPassenger;
    ArrayList<String> tambahan_kg;
    String bagasi;
    ArrayList<String> harga_tambahan;
    ArrayList<String> tambahan_kg_pulang;
    ArrayList<String> harga_tambahan_pulang;
    int position_int;




    public bagasi_bottomsheet_redesign() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        holder = FragmentBagasiBottomsheetRedesignBinding.inflate(inflater, container, false);
        View view = holder.getRoot();

        namaPassenger = new ArrayList<>();
        tambahan_kg = new ArrayList<>();
        harga_tambahan = new ArrayList<>();
        tambahan_kg_pulang = new ArrayList<>();
        harga_tambahan_pulang = new ArrayList<>();
        bagasi = "20kg";


        namaPassenger.add("tes bruh");
        tambahan_kg.add("0kg");
        harga_tambahan.add("tes bruh");
        tambahan_kg_pulang.add("0kg");
        harga_tambahan_pulang.add("0kg");
        namaPassenger.add("tes bruh");
        tambahan_kg.add("0kg");
        harga_tambahan.add("tes bruh");
        tambahan_kg_pulang.add("0kg");
        harga_tambahan_pulang.add("tes bruh");
        namaPassenger.add("tes bruh");
        tambahan_kg.add("0kg");
        harga_tambahan.add("tes bruh");
        tambahan_kg_pulang.add("0kg");
        harga_tambahan_pulang.add("tes bruh");


        position_int = 0;


        RecyclerAdapterBagasiHorizontal recyclerAdapterBagasiHorizontal = new RecyclerAdapterBagasiHorizontal(position_int, bagasi, namaPassenger, tambahan_kg, harga_tambahan, tambahan_kg_pulang, harga_tambahan_pulang);
        holder.RecyclerViewTambahanBagasi.setAdapter(recyclerAdapterBagasiHorizontal);



        ItemClickSupport.addTo(holder.RecyclerViewTambahanBagasi).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
               position_int = position;
               int scrollStateX = recyclerView.computeHorizontalScrollOffset();
               int scrollStateY = recyclerView.computeVerticalScrollOffset();
                RecyclerAdapterBagasiHorizontal recyclerAdapterBagasiHorizontal = new RecyclerAdapterBagasiHorizontal(position_int, bagasi, namaPassenger, tambahan_kg, harga_tambahan, tambahan_kg_pulang, harga_tambahan_pulang);
                holder.RecyclerViewTambahanBagasi.setAdapter(recyclerAdapterBagasiHorizontal);


//                recyclerView.scrollTo(scrollStateX, scrollStateY);
                recyclerView.scrollBy(scrollStateX, scrollStateY);
                Log.i("SCROLL TO POS", "it executed the message");
//                recyclerView.setBackground(getResources().getDrawable(R.drawable.curved__even_less_colorized_bg));
//                v.setBackground(getResources().getDrawable(R.drawable.curved__even_less_colorized_bg));



            }
        });

        holder.radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    holder.radioButton1.setBackground(getResources().getDrawable(R.drawable.curved_less_bg_outline));
                holder.radioButton2.setBackground(getResources().getDrawable(R.drawable.curved_less_bg_outline));
                holder.radioButton3.setBackground(getResources().getDrawable(R.drawable.curved_less_bg_outline));
//                    holder.default_harga.setTextColor(getResources().getColor(R.color.black));
//                    holder.default_weight.setTextColor(getResources().getColor(R.color.black));
                holder.hargaTambahan1.setTextColor(getResources().getColor(R.color.black));
                holder.tambahanKG1.setTextColor(getResources().getColor(R.color.black));
                holder.tambahanKG2.setTextColor(getResources().getColor(R.color.black));
                holder.hargaTambahan2.setTextColor(getResources().getColor(R.color.black));

                holder.radioButton1.setBackground(getResources().getDrawable(R.drawable.curved__even_less_colorized_bg));
                holder.defaultHarga.setTextColor(getResources().getColor(R.color.primary));
                holder.defaultWeight.setTextColor(getResources().getColor(R.color.primary));

                tambahan_kg.set(position_int, "0kg");
                harga_tambahan.set(position_int, "IDR 0");

                RecyclerAdapterBagasiHorizontal recyclerAdapterBagasiHorizontal = new RecyclerAdapterBagasiHorizontal(position_int, bagasi, namaPassenger, tambahan_kg, harga_tambahan, tambahan_kg_pulang, harga_tambahan_pulang);
                holder.RecyclerViewTambahanBagasi.setAdapter(recyclerAdapterBagasiHorizontal);

            }
        });

        holder.radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.radioButton1.setBackground(getResources().getDrawable(R.drawable.curved_less_bg_outline));
//                    holder.radioButton2.setBackground(getResources().getDrawable(R.drawable.curved_less_bg_outline));
                holder.radioButton3.setBackground(getResources().getDrawable(R.drawable.curved_less_bg_outline));
                holder.defaultHarga.setTextColor(getResources().getColor(R.color.black));
                holder.defaultWeight.setTextColor(getResources().getColor(R.color.black));
//                    holder.hargaTambahan_1.setTextColor(getResources().getColor(R.color.black));
//                    holder.tambahanKG_1.setTextColor(getResources().getColor(R.color.black));
                holder.tambahanKG2.setTextColor(getResources().getColor(R.color.black));
                holder.hargaTambahan2.setTextColor(getResources().getColor(R.color.black));


                holder.radioButton2.setBackground(getResources().getDrawable(R.drawable.curved__even_less_colorized_bg));
                holder.tambahanKG1.setTextColor(getResources().getColor(R.color.primary));
                holder.hargaTambahan1.setTextColor(getResources().getColor(R.color.primary));

                tambahan_kg.set(position_int, holder.tambahanKG1.getText().toString().replace("+ ", ""));
                harga_tambahan.set(position_int, holder.hargaTambahan1.getText().toString());

                RecyclerAdapterBagasiHorizontal recyclerAdapterBagasiHorizontal = new RecyclerAdapterBagasiHorizontal(position_int, bagasi, namaPassenger, tambahan_kg, harga_tambahan, tambahan_kg_pulang, harga_tambahan_pulang);
                holder.RecyclerViewTambahanBagasi.setAdapter(recyclerAdapterBagasiHorizontal);
            }
        });

        holder.radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.radioButton1.setBackground(getResources().getDrawable(R.drawable.curved_less_bg_outline));
                holder.radioButton2.setBackground(getResources().getDrawable(R.drawable.curved_less_bg_outline));
//                    holder.radioButton3.setBackground(getResources().getDrawable(R.drawable.curved_less_bg_outline));
                   holder.defaultHarga.setTextColor(getResources().getColor(R.color.black));
                holder.defaultWeight.setTextColor(getResources().getColor(R.color.black));
                holder.tambahanKG1.setTextColor(getResources().getColor(R.color.black));
                holder.hargaTambahan1.setTextColor(getResources().getColor(R.color.black));
//                    holder.tambahanKG_2.setTextColor(getResources().getColor(R.color.black));
//                    holder.hargaTambahan_2.setTextColor(getResources().getColor(R.color.black));


                holder.radioButton3.setBackground(getResources().getDrawable(R.drawable.curved__even_less_colorized_bg));
                holder.tambahanKG2.setTextColor(getResources().getColor(R.color.primary));
                holder.hargaTambahan2.setTextColor(getResources().getColor(R.color.primary));

                tambahan_kg.set(position_int, holder.tambahanKG2.getText().toString().replace("+ ", ""));
                harga_tambahan.set(position_int, holder.hargaTambahan2.getText().toString());

                RecyclerAdapterBagasiHorizontal recyclerAdapterBagasiHorizontal = new RecyclerAdapterBagasiHorizontal(position_int, bagasi, namaPassenger, tambahan_kg, harga_tambahan, tambahan_kg_pulang, harga_tambahan_pulang);
                holder.RecyclerViewTambahanBagasi.setAdapter(recyclerAdapterBagasiHorizontal);
            }
        });

        holder.closeSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_bagasi_bottomsheet_redesign, null);
        dialog.setContentView(view);

        recyclerViewTambahanBagasi = view.findViewById(R.id.RecyclerViewTambahanBagasi);



        return dialog;
    }

    public static class RecyclerAdapterBagasiHorizontal extends RecyclerView.Adapter<RecyclerAdapterBagasiHorizontal.ViewHolder> {
        ArrayList<String> namaPassenger;
        ArrayList<String> tambahan_kg;
        ArrayList<String> harga_tambahan;
        ArrayList<String> tambahan_kg_pulang;
        ArrayList<String> harga_tambahan_pulang;
        String bagasi_default;
        int position_int;

        public RecyclerAdapterBagasiHorizontal(int position_int, String bagasi_default, ArrayList<String> namaPassenger, ArrayList<String> tambahan_kg, ArrayList<String> harga_tambahan, ArrayList<String> tambahan_kg_pulang, ArrayList<String> harga_tambahan_pulang) {
            this.namaPassenger = namaPassenger;
            this.tambahan_kg = tambahan_kg;
            this.harga_tambahan = harga_tambahan;
            this.tambahan_kg_pulang = tambahan_kg_pulang;
            this.harga_tambahan_pulang = harga_tambahan_pulang;
            this.bagasi_default = bagasi_default;
            this.position_int = position_int;
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
            holder.defaultbagasiPergi.setText(bagasi_default);
            holder.defaultbagasiPulang.setText(bagasi_default);

            holder.plus.setVisibility(View.GONE);
            holder.plus1.setVisibility(View.GONE);
            holder.tambahanbagasiPulang.setVisibility(View.GONE);
            holder.tambahanbagasiPergi.setVisibility(View.GONE);

            if (position == position_int){
                holder.linearLayout.setBackground(holder.itemView.getResources().getDrawable(R.drawable.curved__even_less_fillcolorized_bg));
                holder.linearLayout.setPadding(12, 12, 12, 12);
            } else {
                holder.linearLayout.setBackground(holder.itemView.getResources().getDrawable(R.drawable.curved__even_less_colorized_bg));
                holder.linearLayout.setPadding(12, 12, 12, 12);

            }


            if (!tambahan_kg.get(position).matches("0kg")){
                holder.plus.setVisibility(View.VISIBLE);
                holder.tambahanbagasiPergi.setVisibility(View.VISIBLE);

                holder.tambahanbagasiPergi.setText(tambahan_kg.get(position));
            }

            if (!tambahan_kg_pulang.get(position).matches("0kg")) {
                holder.plus1.setVisibility(View.VISIBLE);
                holder.tambahanbagasiPulang.setVisibility(View.VISIBLE);

                holder.tambahanbagasiPulang.setText(tambahan_kg.get(position));
            }

        }

        @Override
        public int getItemCount() {
            return namaPassenger.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder{
            TextView nomordanNamaPassenger;
            TextView defaultbagasiPergi;
            TextView defaultbagasiPulang;
            TextView tambahanbagasiPergi;
            TextView tambahanbagasiPulang;
            TextView plus;
            TextView plus1;
            LinearLayout linearLayout;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                linearLayout = itemView.findViewById(R.id.linearLayout);
                nomordanNamaPassenger = itemView.findViewById(R.id.nomordanNamaPassenger);
                defaultbagasiPergi = itemView.findViewById(R.id.defaultbagasiPergi);
                defaultbagasiPulang = itemView.findViewById(R.id.defaultbagasiPulang);
                tambahanbagasiPergi = itemView.findViewById(R.id.tambahanbagasi_pergi);
                tambahanbagasiPulang = itemView.findViewById(R.id.tambahanbagasi_pulang);

                plus = itemView.findViewById(R.id.plus);
                plus1 = itemView.findViewById(R.id.plus1);
            }


        }




    }
}