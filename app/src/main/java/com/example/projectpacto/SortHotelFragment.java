package com.example.projectpacto;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class SortHotelFragment extends BottomSheetDialogFragment {


    public SortHotelFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (com.google.android.material.bottomsheet.BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_sort_hotel, null);
        dialog.setContentView(view);


        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);

        view.findViewById(R.id.tampilkanButton).setVisibility(View.INVISIBLE);



        //A click will make it the action button visible
        view.findViewById(R.id.planeSort1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.findViewById(R.id.tampilkanButton).setVisibility(View.VISIBLE);

            }
        });
        view.findViewById(R.id.planeSort2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.findViewById(R.id.tampilkanButton).setVisibility(View.VISIBLE);

            }
        });
        view.findViewById(R.id.planeSort3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.findViewById(R.id.tampilkanButton).setVisibility(View.VISIBLE);

            }
        });
        view.findViewById(R.id.planeSort4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.findViewById(R.id.tampilkanButton).setVisibility(View.VISIBLE);

            }
        });


        view.findViewById(R.id.closeSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });




        return dialog;

    }

    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }
}