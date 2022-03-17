package com.example.projectpacto;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class DataPenumpang extends BottomSheetDialogFragment {

    TextView penumpangNumber;
    AutoCompleteTextView namaAutoComplete;
    AutoCompleteTextView titelDropdown;
    TextInputLayout tglLahir;
    TextInputLayout kewarganegaraan;
    TextInputLayout NIKatauPaspor;
    ArrayList<String> titel;
    ArrayList<String> nama;




    public DataPenumpang() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_data_penumpang, null);
        dialog.setContentView(view);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);

        namaAutoComplete = view.findViewById(R.id.testNamaAutocomplete);
        titelDropdown = view.findViewById(R.id.titelDropdown);
        penumpangNumber = view.findViewById(R.id.penumpangNumber);
        kewarganegaraan = view.findViewById(R.id.kewarganegaraan);
        NIKatauPaspor = view.findViewById(R.id.NIKatauPaspor);

        nama = new ArrayList<>();
        nama.add("Asad"); nama.add("Fajrul"); nama.add("Rekyan"); nama.add("Yoga");
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, nama);
        namaAutoComplete.setAdapter(arrayAdapter1);


        titel = new ArrayList<>();
        titel.add("Tuan"); titel.add("Nyonya");

        ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, titel);
        titelDropdown.setAdapter(arrayAdapter);

        view.findViewById(R.id.closeSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });







        return dialog;
    }

}