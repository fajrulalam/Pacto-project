package com.example.projectpacto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapterOpsiBagasi_Pergi extends RecyclerView.Adapter<RecyclerAdapterOpsiBagasi_Pergi.ViewHolder> {


    int position_int;
    ArrayList<Integer> selected_position;
    ArrayList<String> tambahan_kg_option;
    ArrayList<String> hargaTambahan_kg_option;
    OnDataBagasi onDataBagasi;

    public interface OnDataBagasi {
        void OnDataBagasi(int position_int);
    }

    public RecyclerAdapterOpsiBagasi_Pergi(OnDataBagasi onDataBagasi, int position_int, ArrayList<Integer> selected_position, ArrayList<String> tambahan_kg_option, ArrayList<String> hargaTambahan_kg_option) {
        this.position_int = position_int;
        this.selected_position = selected_position;
        this.tambahan_kg_option = tambahan_kg_option;
        this.hargaTambahan_kg_option = hargaTambahan_kg_option;
        this.onDataBagasi = onDataBagasi;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.opsi_bagas_single_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, onDataBagasi);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int position_selected = position;
        holder.tambahan_kilo.setText("+ " + tambahan_kg_option.get(position));
        holder.tambahan_harga.setText(hargaTambahan_kg_option.get(position));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_position.set(position_int, position_selected);
            }
        });

        if (position == selected_position.get(position_int)) {
            holder.tambahan_harga.setTextColor(holder.itemView.getResources().getColor(R.color.primary));
            holder.tambahan_kilo.setTextColor(holder.itemView.getResources().getColor(R.color.primary));
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDataBagasi.OnDataBagasi(position_selected);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tambahan_kg_option.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tambahan_kilo;
        TextView tambahan_harga;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView, OnDataBagasi onDataBagasi) {
            super(itemView);

            tambahan_kilo = itemView.findViewById(R.id.tambahan_kilo);
            tambahan_harga = itemView.findViewById(R.id.tambahan_harga);
            linearLayout = itemView.findViewById(R.id.linearLayout);

        }


    }
}