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
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;


public class NamaTersimpanBaru_BottomSheet extends BottomSheetDialogFragment {

    TextView headingFragment;
    TextView penumpangNumber;
    TextInputEditText namaAutoComplete;
    AutoCompleteTextView titelDropdown;
    TextInputLayout tglLahir;
    TextInputLayout kewarganegaraan;
    TextInputEditText NIKatauPaspor;
    ArrayList<String>  titel;
    ArrayList<String> nama_namaTersimpan;
    ArrayList<String> tglLahir_namaTersimpan;
    ArrayList<String> titel_namaTersimpan;
    ArrayList<String> kewarganegaraan_namaTersimpan;
    ArrayList<String> NIKatauPaspor_namaTersimpan;
    OnDataPassenger datapasser;
    int index;

    String tglLahir_str;
    String nama_str;
    String kewarganegaraan_str;
    String NIKatauPaspor_str;
    String titel_str;
    String documentID;
    String request; //can be Create, Update, or Add temporarily.



    FirebaseFirestore fs;




    public NamaTersimpanBaru_BottomSheet() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_nama_tersimpan_baru__bottom_sheet, null);
        dialog.setContentView(view);
        Bundle bundle = this.getArguments();

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);

        headingFragment = view.findViewById(R.id.headingFragment);
        namaAutoComplete = view.findViewById(R.id.testNamaAutocompletee);
        titelDropdown = view.findViewById(R.id.titelDropdown);
        penumpangNumber = view.findViewById(R.id.penumpangNumber);
        kewarganegaraan = view.findViewById(R.id.kewarganegaraan);
        tglLahir = view.findViewById(R.id.tglLahir);
        NIKatauPaspor = view.findViewById(R.id.NIKatauPasporAutoComplete);

        nama_namaTersimpan = new ArrayList<>();
        tglLahir_namaTersimpan = new ArrayList<>();
        titel_namaTersimpan = new ArrayList<>();
        kewarganegaraan_namaTersimpan = new ArrayList<>();
        NIKatauPaspor_namaTersimpan = new ArrayList<>();



//        nama_namaTersimpan = bundle.getStringArrayList("nama_namaTersimpan");
//        tglLahir_namaTersimpan = bundle.getStringArrayList("tglLahir_namaTersimpan");
//        titel_namaTersimpan = bundle.getStringArrayList("titel_namaTersimpan");
//        kewarganegaraan_namaTersimpan = bundle.getStringArrayList("kewarganegaraan_namaTersimpan");
//        NIKatauPaspor_namaTersimpan = bundle.getStringArrayList("NIKatauPaspor_namaTersimpan");
        tglLahir_str = bundle.getString("tglLahir_str");
        nama_str = bundle.getString("nama_str");
        kewarganegaraan_str = bundle.getString("kewarganegaraan_str");
        NIKatauPaspor_str = bundle.getString("NIKatauPaspor_str");
        titel_str = bundle.getString("titel_str");
        documentID = bundle.getString("documentID");
        request = bundle.getString("request");

        fs = FirebaseFirestore.getInstance();

        if (!nama_str.split(" ")[0].matches("Penumpang")){
            namaAutoComplete.setText(nama_str);
        }
        tglLahir.getEditText().setText(tglLahir_str);
        titelDropdown.setText(titel_str);
        kewarganegaraan.getEditText().setText(kewarganegaraan_str);
        NIKatauPaspor.setText(NIKatauPaspor_str);
        Log.i("NIK/PASSOPRT RECEIVE", NIKatauPaspor.getText().toString());

        Locale lokale = new Locale("id", "ID");

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
                            String tglLahir_str = dateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", lokale));
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
                        String tglLahir_str = dateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy", lokale));
                        tglLahir.getEditText().setText(tglLahir_str);
                    }
                });
            }
        });

        if (bundle.getString("penumpangKe_n").matches("")){
            headingFragment.setText("Sunting nama tersimpan");
            index = bundle.getInt("index");
            penumpangNumber.setVisibility(View.INVISIBLE);

        } else {
            penumpangNumber.setText(bundle.getString("penumpangKe_n"));
        }


        titel = new ArrayList<>();
        titel.add("Tuan"); titel.add("Nyonya"); titel.add("Nona");


        ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, titel);
        titelDropdown.setAdapter(arrayAdapter);



        view.findViewById(R.id.closeSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
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
                String userID = "5E8dHyQfzYeu1wBvwjxNr8EUl7J3";

                //Cek semua sudah terisi
                if (nama.matches("") || titel.matches("")|| tglLahir_str.matches("")|| kewarganegaraan_str.matches("") ||
                        NIKatauPasport_str.matches("")){
                    Toast.makeText(getContext(), "Pastikan semua data sudaht terisi", Toast.LENGTH_SHORT).show();
                    return;
                }

                //Cek NIKatauPaspor terisi dengan benar
                try {
                    long NIKataupaspor = Long.parseLong(NIKatauPasport_str);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Pastikan 'NIK atau No. Paspor' sudah benar", Toast.LENGTH_SHORT).show();
                    return;
                }



                try {
                    int penumpangNumber_int = Integer.parseInt(penumpangNumber.getText().toString());
                    datapasser.onDataPass(nama, titel ,tglLahir_str ,kewarganegaraan_str ,NIKatauPasport_str ,penumpangNumber_int, request );
                } catch (Exception e){
                    datapasser.onDataPass(nama, titel ,tglLahir_str ,kewarganegaraan_str ,NIKatauPasport_str ,index, request);

                }
                dismiss();
            }
        });



        return dialog;
    }

    public interface OnDataPassenger {
        void onDataPass(String nama, String titel, String tglLahir, String kewarganegaraan, String nikAtauPaspor, int penumpangKe_n, String request);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        datapasser = (NamaTersimpanBaru_BottomSheet.OnDataPassenger) context;
    }




}

