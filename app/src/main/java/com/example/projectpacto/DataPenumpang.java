package com.example.projectpacto;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class DataPenumpang extends BottomSheetDialogFragment {

    TextView penumpangNumber;
    AutoCompleteTextView namaAutoComplete;
    AutoCompleteTextView titelDropdown;
    TextInputLayout tglLahir;
    TextInputLayout kewarganegaraan;
    TextInputEditText NIKatauPaspor;
    ArrayList<String> titel;
    ArrayList<String> nama;
    OnDataPassenger datapasser;

    String tglLahir_str;
    String nama_str;
    String kewarganegaraan_str;
    String NIKatauPaspor_str;
    String titel_str;




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
        NIKatauPaspor = view.findViewById(R.id.NIKatauPasporAutoComplete);

        tglLahir_str = bundle.getString("tglLahir_str");
        nama_str = bundle.getString("nama_str");
        kewarganegaraan_str = bundle.getString("kewarganegaraan_str");
        NIKatauPaspor_str = bundle.getString("NIKatauPaspor_str");
        titel_str = bundle.getString("titel_str");

        if (!nama_str.split(" ")[0].matches("Penumpang")){
            namaAutoComplete.setText(nama_str);
        }
        tglLahir.getEditText().setText(tglLahir_str);
        titelDropdown.setText(titel_str);
        kewarganegaraan.getEditText().setText(kewarganegaraan_str);
        NIKatauPaspor.setText(NIKatauPaspor_str);
        Log.i("NIK/PASSOPRT RECEIVE", NIKatauPaspor.getText().toString());



        MaterialDatePicker datePicker_start = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Pilih Tanggal Keberangkatan").build();
        tglLahir.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b==true){
                    datePicker_start.show(getActivity().getSupportFragmentManager(), "tgl_keberangkatan");
                    datePicker_start.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onPositiveButtonClick(Object selection) {
                            long epoch_long = Long.parseLong(selection.toString());
                            ZonedDateTime dateTime= Instant.ofEpochMilli(epoch_long).atZone(ZoneId.of("Asia/Jakarta"));
                            String tglLahir_str = dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                            tglLahir.getEditText().setText(tglLahir_str);
                        }
                    });
                }
            }
        });
        tglLahir.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker_start.show(getActivity().getSupportFragmentManager(), "tgl_keberangkatan");
                datePicker_start.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        long epoch_long = Long.parseLong(selection.toString());
                        ZonedDateTime dateTime= Instant.ofEpochMilli(epoch_long).atZone(ZoneId.of("Asia/Jakarta"));
                        String tglLahir_str = dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        tglLahir.getEditText().setText(tglLahir_str);
                    }
                });
            }
        });


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
                        tglLahir.getEditText().setText("19/11/2001");
                        titelDropdown.setText("Tuan");
                        kewarganegaraan.getEditText().setText("Indonesia");
                        NIKatauPaspor.setText("081351");
                        break;
                    case "Fajrul":
                        tglLahir.getEditText().setText("16/06/2001");
                        titelDropdown.setText("Tuan");
                        kewarganegaraan.getEditText().setText("Indonesia");
                        NIKatauPaspor.setText("3517173209235832");
                        break;
                    case "Yoga":
                        tglLahir.getEditText().setText("16/04/2000");
                        titelDropdown.setText("Tuan");
                        kewarganegaraan.getEditText().setText("Indonesia");
                        NIKatauPaspor.setText("081336");
                        break;
                    case "Rekyan":
                        tglLahir.getEditText().setText("16/06/1991");
                        titelDropdown.setText("Nyonya");
                        kewarganegaraan.getEditText().setText("Indonesia");
                        NIKatauPaspor.setText("623336");
                        break;
                }
                Log.i("NIK/PASSOPRT INPUT", NIKatauPaspor.getText().toString());
            }
        });

        view.findViewById(R.id.actionButton_cari).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nama = namaAutoComplete.getText().toString();
                String titel = titelDropdown.getText().toString();
                String tglLahir_str = tglLahir.getEditText().getText().toString();
                String kewarganegaraan_str = kewarganegaraan.getEditText().getText().toString();
                String NIKatauPasport_str = NIKatauPaspor.getText().toString();
                int penumpangNumber_int = Integer.parseInt(penumpangNumber.getText().toString());
                datapasser.onDataPass(nama, titel ,tglLahir_str ,kewarganegaraan_str ,NIKatauPasport_str ,penumpangNumber_int );
                dismiss();
            }
        });



        return dialog;
    }

    public interface OnDataPassenger {
        void onDataPass(String nama, String titel, String tglLahir, String kewarganegaraan, String nikAtauPaspor, int penumpangKe_n);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        datapasser = (DataPenumpang.OnDataPassenger) context;
    }

}