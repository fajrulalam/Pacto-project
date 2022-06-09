package com.example.projectpacto;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class JumlahKamarBottomSheet extends BottomSheetDialogFragment {

    ImageView plusTamu;
    ImageView minusTamu;
    TextView tamuCount;

    ImageView minusKamar;
    ImageView plusKamar;
    TextView kamarCount;
    OnDataKamar datapasser;



    JumlahKamarBottomSheet(){
        //Constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_jumlah_kamar_bottom_sheet, null);
        dialog.setContentView(view);

        plusTamu  = view.findViewById(R.id.plusTamu);
        minusTamu = view.findViewById(R.id.minusTamu);
        tamuCount = view.findViewById(R.id.tamuCount);

        minusKamar = view.findViewById(R.id.minusKamar);
        plusKamar = view.findViewById(R.id.plusKamar);
        kamarCount = view.findViewById(R.id.kamarCount);

        Bundle bundle = this.getArguments();
        int jmlTamu_int = bundle.getInt("jmlTamu");
        int jmlKamar_int = bundle.getInt("jmlKamar");

        if (jmlKamar_int > 0 ){
            tamuCount.setText(jmlTamu_int+"");
        }
        if (jmlTamu_int > 0 ){
            kamarCount.setText(jmlKamar_int+"");
        }

        //TAMU COUNT
        plusTamu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tamuCount.setText(Integer.parseInt(tamuCount.getText().toString()) + 1 +"");
            }
        });
        minusTamu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(tamuCount.getText().toString())!= 1) {
                    tamuCount.setText(Integer.parseInt(tamuCount.getText().toString()) - 1 + "");
                }
            }
        });

        //KAMAR COUNT
        plusKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kamarCount.setText(Integer.parseInt(kamarCount.getText().toString()) + 1 +"");
            }
        });
        minusKamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(kamarCount.getText().toString())!= 1) {
                    kamarCount.setText(Integer.parseInt(kamarCount.getText().toString()) - 1 + "");
                }
            }
        });

        //ACTION BUTTON
        view.findViewById(R.id.actionButton_cari).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datapasser.onDataPassTamu(tamuCount.getText().toString(), kamarCount.getText().toString());
                dismiss();
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

    public interface OnDataKamar{
        void onDataPassTamu(String jumlahTamu, String jumlahKamar);
    }



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        datapasser = (OnDataKamar) context;
    }
}