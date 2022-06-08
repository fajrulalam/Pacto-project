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

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setPeekHeight(3000);
        bottomSheetBehavior.setMaxHeight(2100);




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

        }

        @Override
        public int getItemCount() {
            return 0;
        }


        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView image;



            public ViewHolder(@NonNull View itemView) {
                super(itemView);


                image = itemView.findViewById(R.id.imageView);



            }


        }
    }
}
