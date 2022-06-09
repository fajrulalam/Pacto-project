package com.example.projectpacto;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;


public class FasilitasLainnyaBottomSheet extends BottomSheetDialogFragment {

    public FasilitasLainnyaBottomSheet() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_fasilitas_lainnya_bottom_sheet, null);
        dialog.setContentView(view);

        ArrayList<String> fasilitas = getArguments().getStringArrayList("fasilitas");
        RecyclerView recyclerView = view.findViewById(R.id.RecyclerViewFasilitas);
        RecyclerAdapterFasilitasHotel recyclerAdapterFasilitasHotel =  new RecyclerAdapterFasilitasHotel(fasilitas);
        recyclerView.setAdapter(recyclerAdapterFasilitasHotel);



        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setPeekHeight(3000);
        bottomSheetBehavior.setMaxHeight(2100);


        view.findViewById(R.id.closeSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });




        return  dialog;
    }


    public class RecyclerAdapterFasilitasHotel extends RecyclerView.Adapter<RecyclerAdapterFasilitasHotel.ViewHolder>{
        ArrayList<String> fasilitas;

        public RecyclerAdapterFasilitasHotel(ArrayList<String> fasilitas) {
            this.fasilitas = fasilitas;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.fasilitas_single_view, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return  viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


            holder.fasilitas.setText(fasilitas.get(position));

            switch (fasilitas.get(position)) {
                case "Restoran":
                    holder.icon.setImageResource(R.drawable.ic_restoranred);
                    break;
                case "Wi-Fi":
                    holder.icon.setImageResource(R.drawable.ic_wifired);
                    break;
                case "Air hangat":
                    holder.icon.setImageResource(R.drawable.ic_hairhangatred);
                    break;
                case "Laundry":
                    holder.icon.setImageResource(R.drawable.ic_laundryred);
                    break;
                case "Elevator":
                    holder.icon.setImageResource(R.drawable.ic_elevator);
                    break;
                case "Transportasi umum":
                    holder.icon.setImageResource(R.drawable.ic_transportasiumum);
                    break;
                case "Pusat kebugaran":
                    holder.icon.setImageResource(R.drawable.ic_pusatkebugaran);
                    break;
                case "Kolam renang":
                    holder.icon.setImageResource(R.drawable.ic_kolamrenang);
                    break;
                case "Hewan peliharaan diperbolehkan":
                    holder.icon.setImageResource(R.drawable.ic_petallowed);
                    break;
                case "Call center 24 jam":
                    holder.icon.setImageResource(R.drawable.ic_calcenter);
                    break;
                case "Bathtub":
                    holder.icon.setImageResource(R.drawable.ic_bathtub);
                    break;
                case "Spa":
                    holder.icon.setImageResource(R.drawable.ic_spa);
                    break;
                case "Hiburan":
                    holder.icon.setImageResource(R.drawable.ic_hiburan);
                    break;


            }
        }

        @Override
        public int getItemCount() {
            return fasilitas.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView icon;
            TextView fasilitas;



            public ViewHolder(@NonNull View itemView) {
                super(itemView);


                icon = itemView.findViewById(R.id.iconFasilitas);
                fasilitas = itemView.findViewById(R.id.namafasilitas);



            }


        }
    }
}
