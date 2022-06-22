package com.example.projectpacto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectpacto.databinding.ActivityHotelOrder3Binding;
import com.example.projectpacto.databinding.ActivityNotificationsBinding;

import java.util.ArrayList;

public class NotificationsActivity extends AppCompatActivity {

    ActivityNotificationsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



    }

    public class RecyclerAdapterNotifications extends RecyclerView.Adapter<RecyclerAdapterNotifications.ViewHolder> {

        ArrayList<String> logoNotif; //informasi, peringatan, approved
        ArrayList<String> waktuNotif;
        ArrayList<String> judulNotif;
        ArrayList<String> keteranganNotif;


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.notification_single_view, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return  viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            switch (judulNotif.get(position)){
                case "informasi":
                    holder.logoNotif.setImageResource(R.drawable.ic_notif_information);
                    break;
                case "peringatan":
                    holder.logoNotif.setImageResource(R.drawable.ic_notif_warning);
                    break;
                case "approved":
                    holder.logoNotif.setImageResource(R.drawable.ic_notif_acc);
                    break;
            }

            holder.waktuNotif.setText(waktuNotif.get(position));
            holder.judulNotif.setText(judulNotif.get(position));
            holder.keteranganNotif.setText(keteranganNotif.get(position));


        }

        @Override
        public int getItemCount() {
            return logoNotif.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            ImageView logoNotif;
            TextView judulNotif;
            TextView waktuNotif;
            TextView keteranganNotif;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                logoNotif = itemView.findViewById(R.id.logoNotif);
                judulNotif = itemView.findViewById(R.id.judulNotif);
                waktuNotif = itemView.findViewById(R.id.waktuNotif);
                keteranganNotif = itemView.findViewById(R.id.keteranganNotif);




            }


        }
    }



}