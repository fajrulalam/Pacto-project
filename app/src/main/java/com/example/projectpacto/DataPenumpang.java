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
import android.widget.AdapterView;
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
    OnDataPassenger datapasser;




    public DataPenumpang() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_data_penumpang, null);
        dialog.setContentView(view);
        Bundle bundle = this.getArguments();

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);

        namaAutoComplete = view.findViewById(R.id.testNamaAutocomplete);
        titelDropdown = view.findViewById(R.id.titelDropdown);
        penumpangNumber = view.findViewById(R.id.penumpangNumber);
        kewarganegaraan = view.findViewById(R.id.kewarganegaraan);
        tglLahir = view.findViewById(R.id.tglLahir);
        NIKatauPaspor = view.findViewById(R.id.NIKatauPaspor);


        penumpangNumber.setText(bundle.getString("penumpangKe_n"));


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

        namaAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (String.valueOf(adapterView.getItemAtPosition(position))){
                    case "Asad":
                        tglLahir.getEditText().setText("19-11-2001");
                        titelDropdown.setText("Tuan");
                        kewarganegaraan.getEditText().setText("Indonesia");
                        NIKatauPaspor.getEditText().setText("081351");
                        break;
                    case "Fajrul":
                        tglLahir.getEditText().setText("16-06-2001");
                        titelDropdown.setText("Tuan");
                        kewarganegaraan.getEditText().setText("Indonesia");
                        NIKatauPaspor.getEditText().setText("3517173209235832");
                        break;
                    case "Yoga":
                        tglLahir.getEditText().setText("16-04-2000");
                        titelDropdown.setText("Tuan");
                        kewarganegaraan.getEditText().setText("Indonesia");
                        NIKatauPaspor.getEditText().setText("081336");
                        break;
                    case "Rekyan":
                        tglLahir.getEditText().setText("16-06-1991");
                        titelDropdown.setText("Nyonya");
                        kewarganegaraan.getEditText().setText("Indonesia");
                        NIKatauPaspor.getEditText().setText("623336");
                        break;
                }
            }
        });

        view.findViewById(R.id.actionButton_cari).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datapasser.onDataPass(namaAutoComplete.getText().toString(), titelDropdown.getText().toString(), tglLahir.getEditText().getText().toString(), kewarganegaraan.getEditText().getText().toString(), NIKatauPaspor.getEditText().toString());
                dismiss();
            }
        });



        return dialog;
    }

    public interface OnDataPassenger {
        void onDataPass(String nama, String titel, String tglLahir, String kewarganegaraan, String nikAtauPaspor);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        datapasser = (DataPenumpang.OnDataPassenger) context;
    }

}