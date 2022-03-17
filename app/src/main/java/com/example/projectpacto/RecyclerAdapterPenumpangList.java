package com.example.projectpacto;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapterPenumpangList extends RecyclerView.Adapter<RecyclerAdapterPenumpangList.ViewHolder> {

    ArrayList<String> namaPassenger;
    AddPassengerDetail addPassengerDetail;


    public RecyclerAdapterPenumpangList(ArrayList<String> namaPassenger, AddPassengerDetail addPassengerDetail){
        this.namaPassenger = namaPassenger;
        this.addPassengerDetail = addPassengerDetail;


    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.passenger_list_single_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, addPassengerDetail);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int penumpangKe_n = position;
        holder.namaPassenger_textView.setText(namaPassenger.get(position));




        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addPassengerDetail.addPassengerDetail(String.valueOf(position));


            }
        });


    }

    @Override
    public int getItemCount() {
        return namaPassenger.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView namaPassenger_textView;
        ImageView edit;
        AddPassengerDetail addPassengerDetail;


        public ViewHolder(@NonNull View itemView, AddPassengerDetail addPassengerDetail) {
            super(itemView);
            this.addPassengerDetail = addPassengerDetail;

            namaPassenger_textView = itemView.findViewById(R.id.namaPassenger);
            edit = itemView.findViewById(R.id.editNamaPassenger);
        }


    }

    public interface AddPassengerDetail {
        void addPassengerDetail(String nomorPelanggan);
    }
}
