package com.example.projectpacto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapaterBandara extends RecyclerView.Adapter<RecyclerAdapaterBandara.ViewHolder> {

    ArrayList<String> namaKota;
    ArrayList<String> namaBandara;

    public RecyclerAdapaterBandara(ArrayList<String> namaKota, ArrayList<String> namaBandara){
        this.namaKota = namaKota;
        this.namaBandara = namaBandara;

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
        holder.bandaraTextView.setText(String.valueOf(namaBandara.get(position)));
        holder.kotaTextView.setText(String.valueOf(namaKota.get(position)));

    }

    @Override
    public int getItemCount() {
        return namaKota.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView bandaraTextView;
        TextView kotaTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bandaraTextView = itemView.findViewById(R.id.namaBandara);
            kotaTextView = itemView.findViewById(R.id.namaKota);
        }


    }
}
