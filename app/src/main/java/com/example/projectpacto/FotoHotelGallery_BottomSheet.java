package com.example.projectpacto;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class FotoHotelGallery_BottomSheet extends BottomSheetDialogFragment {

    RecyclerView fotoRecyclerView;


    public FotoHotelGallery_BottomSheet() {
    // required empty constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_foto_hotel_gallery__bottom_sheet, null);
        dialog.setContentView(view);

        Bundle bundle = getArguments();
        ArrayList<Integer> gambar = new ArrayList<>();
        gambar = bundle.getIntegerArrayList("gambar");


        fotoRecyclerView = view.findViewById(R.id.RecycleViewHotelFotoLainnya);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4, RecyclerView.VERTICAL, false);
        fotoRecyclerView.setLayoutManager(gridLayoutManager);

//        int spanCount = 3; // 3 columns
//        int spacing = 50; // 50px
//        boolean includeEdge = true;
//        GridSpacingItemDecoration gridSpacingItemDecoration = new GridSpacingItemDecoration(4, 8, true);
//        fotoRecyclerView.addItemDecoration(gridSpacingItemDecoration);

        RecyclerAdapterFotoLainnya recyclerAdapterFotoLainnya = new RecyclerAdapterFotoLainnya(gambar);
        fotoRecyclerView.setAdapter(recyclerAdapterFotoLainnya);







        view.findViewById(R.id.closeSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });





        return dialog;
    }


    public class RecyclerAdapterFotoLainnya extends RecyclerView.Adapter<RecyclerAdapterFotoLainnya.ViewHolder>{
        ArrayList<Integer> gambar;

        public RecyclerAdapterFotoLainnya(ArrayList<Integer> gambar) {
            this.gambar = gambar;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.grid_foto_hotel, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return  viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.image.setImageResource(gambar.get(position));
        }

        @Override
        public int getItemCount() {
            return gambar.size();
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