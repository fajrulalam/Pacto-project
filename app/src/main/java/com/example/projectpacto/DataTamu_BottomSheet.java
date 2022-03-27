package com.example.projectpacto;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
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

import java.util.ArrayList;


public class DataTamu_BottomSheet extends BottomSheetDialogFragment {
    TextView penumpangNumber;
    AutoCompleteTextView namaAutoComplete;
    AutoCompleteTextView titelDropdown;

    ArrayList<String> titel;
    ArrayList<String> nama;

    String nama_str;
    String titel_str;

    OnDataTamu datapasser;


    public DataTamu_BottomSheet(){
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_data_tamu__bottom_sheet, null);
        dialog.setContentView(view);
        Bundle bundle = this.getArguments();

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);

        penumpangNumber = view.findViewById(R.id.penumpangNumber);
        namaAutoComplete = view.findViewById(R.id.testNamaAutocomplete);
        titelDropdown = view.findViewById(R.id.titelDropdown);

        nama_str = bundle.getString("nama_str");
        titel_str = bundle.getString("titel_str");

        if (!nama_str.split(" ")[0].matches("Tamu")){
            namaAutoComplete.setText(nama_str);
        }
        titelDropdown.setText(titel_str);
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
                        titelDropdown.setText("Tuan");
                        break;
                    case "Fajrul":
                        titelDropdown.setText("Tuan");
                        break;
                    case "Yoga":
                        titelDropdown.setText("Tuan");
                        break;
                    case "Rekyan":
                        titelDropdown.setText("Nyonya");
                        break;
                }
            }
        });



        view.findViewById(R.id.actionButton_cari).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datapasser.onDataTamuPass(namaAutoComplete.getText().toString(), titelDropdown.getText().toString(), Integer.parseInt(penumpangNumber.getText().toString()));
                dismiss();
            }
        });



        return dialog;
    }

    public interface OnDataTamu {
        void onDataTamuPass(String nama, String titel, int penumpangKe_n);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        datapasser = (DataTamu_BottomSheet.OnDataTamu) context;
    }
}