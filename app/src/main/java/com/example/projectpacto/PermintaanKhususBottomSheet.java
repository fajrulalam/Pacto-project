package com.example.projectpacto;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class PermintaanKhususBottomSheet extends BottomSheetDialogFragment {

    int bebasAsapRokok;
    int kamarTersambung;
    String waktuCekin_req;
    String waktuCekOut_req;
    String tipeRanjang_req;
    String catatanLainnya_req;

   public  PermintaanKhususBottomSheet(){

   }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_permintaan_khusus_bottom_sheet, null);
        dialog.setContentView(view);
        Bundle bundle = this.getArguments();

        bebasAsapRokok = bundle.getInt("bebasAsapRokok");
        kamarTersambung = bundle.getInt("kamarTersambung");
        waktuCekin_req = bundle.getString("waktuCekin_req");
        waktuCekOut_req = bundle.getString("waktuCekOut_req");
        tipeRanjang_req = bundle.getString("tipeRanjang_req");
        catatanLainnya_req = bundle.getString("catatanLainnya_req");

        if (bebasAsapRokok == 1){

        }



        return dialog;
    }
}