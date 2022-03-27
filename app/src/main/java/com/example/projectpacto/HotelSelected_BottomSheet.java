package com.example.projectpacto;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class HotelSelected_BottomSheet extends BottomSheetDialogFragment {
    int gambarHotel;
    String namaHotel;
    String tambahanAlamat;
    String harga;
    int jmlBintang;

    TextView namaHotel_txt;
    TextView alamatTambahan_txt;


    public HotelSelected_BottomSheet() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_hotel_selected__bottom_sheet, null);
        dialog.setContentView(view);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setPeekHeight(3000);



        Bundle extras = this.getArguments();
        gambarHotel = extras.getInt("gambarHotel");
        namaHotel = extras.getString("namaHotel");
        tambahanAlamat = extras.getString("tambahanAlamat");
        harga = extras.getString("harga");
        jmlBintang= extras.getInt("jmlBintang");



        namaHotel_txt = view.findViewById(R.id.namaHotel_txt);
        namaHotel_txt.setText(namaHotel);
        alamatTambahan_txt = view.findViewById(R.id.alamatTambahan);
        alamatTambahan_txt.setText(tambahanAlamat);



        //BINTANG
        switch (jmlBintang){
            case 1:
                view.findViewById(R.id.star1).setVisibility(View.VISIBLE);
                view.findViewById(R.id.star2).setVisibility(View.INVISIBLE);
                view.findViewById(R.id.star3).setVisibility(View.INVISIBLE);
                view.findViewById(R.id.star4).setVisibility(View.INVISIBLE);
                view.findViewById(R.id.star5).setVisibility(View.INVISIBLE);
                break;

            case 2:
                view.findViewById(R.id.star1).setVisibility(View.VISIBLE);
                view.findViewById(R.id.star2).setVisibility(View.VISIBLE);
                view.findViewById(R.id.star3).setVisibility(View.INVISIBLE);
                view.findViewById(R.id.star4).setVisibility(View.INVISIBLE);
                view.findViewById(R.id.star5).setVisibility(View.INVISIBLE);
                break;


            case 3:
                view.findViewById(R.id.star1).setVisibility(View.VISIBLE);
                view.findViewById(R.id.star2).setVisibility(View.VISIBLE);
                view.findViewById(R.id.star3).setVisibility(View.VISIBLE);
                view.findViewById(R.id.star4).setVisibility(View.INVISIBLE);
                view.findViewById(R.id.star5).setVisibility(View.INVISIBLE);
                break;

            case 4:
                view.findViewById(R.id.star1).setVisibility(View.VISIBLE);
                view.findViewById(R.id.star2).setVisibility(View.VISIBLE);
                view.findViewById(R.id.star3).setVisibility(View.VISIBLE);
                view.findViewById(R.id.star4).setVisibility(View.VISIBLE);
                view.findViewById(R.id.star5).setVisibility(View.INVISIBLE);
                break;

            case 5:
                view.findViewById(R.id.star1).setVisibility(View.VISIBLE);
                view.findViewById(R.id.star2).setVisibility(View.VISIBLE);
                view.findViewById(R.id.star3).setVisibility(View.VISIBLE);
                view.findViewById(R.id.star4).setVisibility(View.VISIBLE);
                view.findViewById(R.id.star5).setVisibility(View.VISIBLE);
                break;


        }





        view.findViewById(R.id.closeSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return dialog;
    }
}