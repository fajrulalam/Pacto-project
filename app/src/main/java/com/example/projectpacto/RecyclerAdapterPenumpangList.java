package com.example.projectpacto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapterPenumpangList extends RecyclerView.Adapter<RecyclerAdapterPenumpangList.ViewHolder> {

    ArrayList<String> namaPassenger;


    public RecyclerAdapterPenumpangList(ArrayList<String> namaPassenger){
        this.namaPassenger = namaPassenger;


    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.passenger_list_single_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.namaPassenger_textView.setText(namaPassenger.get(position));


    }

    @Override
    public int getItemCount() {
        return namaPassenger.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView namaPassenger_textView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            namaPassenger_textView = itemView.findViewById(R.id.namaPassenger);
        }


    }
}
