package com.example.projectpacto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Class ini akan digunakan di Activity Plane Order 3 untuk menampilkan ringkasan penambahan bagasi.


public class RecyclerAdapterBagasi extends RecyclerView.Adapter<ViewHolder> {

    ArrayList<String> tambahanKG;
    ArrayList<String> hargaTambahan;

    public RecyclerAdapterBagasi( ArrayList<String> tambahanKG, ArrayList<String> hargaTambahan){
        this.hargaTambahan = hargaTambahan;
        this.tambahanKG = tambahanKG;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.bagasi_single_view, parent, false);
        ViewHolder  viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.hargaTambahan_txt.setText(hargaTambahan.get(position));
        holder.tambahanKG_txt.setText(tambahanKG.get(position));

    }

    @Override
    public int getItemCount() {
        return tambahanKG.size();
    }
}

class ViewHolder extends RecyclerView.ViewHolder{
    TextView hargaTambahan_txt;
    TextView tambahanKG_txt;




    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        hargaTambahan_txt = itemView.findViewById(R.id.bagasiTambahan);
        tambahanKG_txt = itemView.findViewById(R.id.hargaBagasiTambahan);


    }


}
