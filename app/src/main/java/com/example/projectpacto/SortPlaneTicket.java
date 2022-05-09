package com.example.projectpacto;


import android.app.Dialog;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class SortPlaneTicket extends BottomSheetDialogFragment {

    Button tampilkanButton;

    public SortPlaneTicket() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.activity_sort_plane_ticket, null);
        dialog.setContentView(view);


        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);

        tampilkanButton = view.findViewById(R.id.tampilkanButton);


        tampilkanButton.setVisibility(View.INVISIBLE);
        view.findViewById(R.id.closeSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



        for (int i = 1; i < 7; i++){
            int idView = getResources().getIdentifier("planeSort" + i, "id", getContext().getPackageName());
            View eventView = view.findViewById(idView);
            eventView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickSort(view);
                }
            });
        }






        return dialog;
    }

    public void onClickSort(View view){
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
            case R.id.planeSort5:
                //do something...
                Toast.makeText(getContext(), "Plane Sort 5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.planeSort6:
                //do something...
                Toast.makeText(getContext(), "Plane Sort 6", Toast.LENGTH_SHORT).show();
                break;

        }



    }

    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }

    private void hideView(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = 0;
        view.setLayoutParams(params);
    }

    private void showView(View view, int size) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = size;
        view.setLayoutParams(params);
    }

    private int getActionBarSize(){
        final TypedArray typedArray =getContext().getTheme().obtainStyledAttributes(new int[]{
                android.R.attr.actionBarSize
        });

        return (int) typedArray.getDimension(0, 0);
    }
}


