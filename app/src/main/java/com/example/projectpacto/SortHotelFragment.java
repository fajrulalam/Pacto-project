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
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class SortHotelFragment extends BottomSheetDialogFragment {

    Button tampilkanButton;
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

        tampilkanButton = view.findViewById(R.id.tampilkanButton);

        //A click will make it the action button visible

        for (int i = 1; i < 5; i++){
            int idView = getResources().getIdentifier("planeSort" + i, "id", getContext().getPackageName());
            View eventView = view.findViewById(idView);
            eventView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickSort(view);
                }
            });
        }



        view.findViewById(R.id.closeSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });




        return dialog;

    }

    public void onClickSort(View view) {
        tampilkanButton.setVisibility(View.VISIBLE);
        switch (view.getId()){
            case R.id.planeSort1:
                //do something...
                Toast.makeText(getContext(), "Plane Sort 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.planeSort2:
                //do something...
                Toast.makeText(getContext(), "Plane Sort 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.planeSort3:
                //do something...
                Toast.makeText(getContext(), "Plane Sort 3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.planeSort4:
                //do something...
                Toast.makeText(getContext(), "Plane Sort 4", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }
}