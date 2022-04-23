package com.example.projectpacto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapterNamaTersimpan extends RecyclerView.Adapter<RecyclerAdapterNamaTersimpan.ViewHolder> {


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.nama_tersimpan_single_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView namaTersimpan_titel;
        TextView NIKatauPaspor;
        ImageView edit;
        ImageView delete;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            namaTersimpan_titel = itemView.findViewById(R.id.nama_titel);
            NIKatauPaspor = itemView.findViewById(R.id.NIKatauPaspor);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }


    }
}
