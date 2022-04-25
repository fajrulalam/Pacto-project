package com.example.projectpacto;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import javax.security.auth.login.LoginException;

public class RecyclerAdapterNamaTersimpan extends RecyclerView.Adapter<RecyclerAdapterNamaTersimpan.ViewHolder> {

    ArrayList<String> nama_titel;
    ArrayList<String> NIKatauPaspor;
    AddPassengerDetail addPassengerDetail;


    public RecyclerAdapterNamaTersimpan(ArrayList<String> nama_titel, ArrayList<String> NIKatauPaspor, AddPassengerDetail addPassengerDetail) {
        this.nama_titel = nama_titel;
        this.NIKatauPaspor = NIKatauPaspor;
        this.addPassengerDetail = addPassengerDetail;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.nama_tersimpan_single_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, addPassengerDetail);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int position_ = position+1;
        holder.namaTersimpan_titel.setText(nama_titel.get(position));
        holder.NIKatauPaspor.setText(NIKatauPaspor.get(position));

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("NIK atau Paspor", holder.NIKatauPaspor.getText().toString());
                addPassengerDetail.addPassengerDetail(position_);
            }
        });

    }

    @Override
    public int getItemCount() {
        return nama_titel.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView namaTersimpan_titel;
        TextView NIKatauPaspor;
        ImageView edit;
        ImageView delete;


        public ViewHolder(@NonNull View itemView, AddPassengerDetail addPassengerDetail) {
            super(itemView);


            namaTersimpan_titel = itemView.findViewById(R.id.nama_titel);
            NIKatauPaspor = itemView.findViewById(R.id.NIKatauPaspor);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }


    }

    public interface AddPassengerDetail {
        void addPassengerDetail(int nomorPelanggan);





    }
}
