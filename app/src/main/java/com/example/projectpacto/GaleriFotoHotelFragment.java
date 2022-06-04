package com.example.projectpacto;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;


public class GaleriFotoHotelFragment extends AppCompatDialogFragment {

    ImageButton next;
    ImageButton prev;
    ImageView gambar;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_galeri_foto_hotel, null);
        builder.setView(view);
        next = view.findViewById(R.id.next);
        prev = view.findViewById(R.id.prev);
        gambar = view.findViewById(R.id.image);

        ArrayList<Integer> gambarIntegerArrayList = getArguments().getIntegerArrayList("gambar");
        int pcc = getArguments().getInt("pcc");

        gambar.setImageResource(gambarIntegerArrayList.get(pcc));
        final int[] current = {pcc};

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (current[0] != gambarIntegerArrayList.size()-1) {
                    current[0] = current[0] + 1;
                    gambar.setImageResource(gambarIntegerArrayList.get(current[0]));
                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (current[0] != 0) {
                    current[0] = current[0] - 1;
                    gambar.setImageResource(gambarIntegerArrayList.get(current[0]));
                }
            }
        });




        return builder.create();
    }
}