package com.example.projectpacto;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class TransaksiFilterButtonSheet extends BottomSheetDialogFragment {


    TextInputLayout tglMulai;
    TextInputLayout tglBerakhir;
    LinearLayout pemasukanFilter;
    LinearLayout pengeluaranFilter;
    Long epoch_tglMulai;

    MaterialDatePicker datePicker_end;

    CalendarConstraints.Builder constraints_end;

    public TransaksiFilterButtonSheet() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_transaksi_filter_button_sheet, null);
        dialog.setContentView(view);


        //Calendar Constraints
        Locale lokal = new Locale("id", "ID");
        long today = MaterialDatePicker.todayInUtcMilliseconds();
        MaterialDatePicker datePicker_start = MaterialDatePicker.Builder.datePicker()
                .setSelection(today)
                .setTitleText("Pilih Batas Tanggal Mulai").build();

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);



        tglMulai = view.findViewById(R.id.tanggalMulai_txtInput);
        tglBerakhir = view.findViewById(R.id.tanggalBerakhir_txtInput);
        pemasukanFilter = view.findViewById(R.id.filterPemasukan);
        pengeluaranFilter = view.findViewById(R.id.filterPengeluaran);

        tglMulai.getEditText().setInputType(TextView.AUTO_SIZE_TEXT_TYPE_NONE);
        tglBerakhir.getEditText().setInputType(TextView.AUTO_SIZE_TEXT_TYPE_NONE);

        //TANGGAL MULAI
        tglMulai.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b==true) {
                    datePicker_start.show(getActivity().getSupportFragmentManager(), "tgl_keberangkatan");
                    datePicker_start.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onPositiveButtonClick(Object selection) {
                            long epoch_long = Long.parseLong(selection.toString());
                            ZonedDateTime dateTime= Instant.ofEpochMilli(epoch_long).atZone(ZoneId.of("Asia/Jakarta"));
                            String tanggal = dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM YYYY", lokal));
                            tglMulai.getEditText().setText(tanggal);

                            constraints_end = new CalendarConstraints.Builder()
                                    .setValidator(DateValidatorPointForward.from(Long.parseLong(""+epoch_long)))
                                    .setStart(Long.parseLong(""+epoch_long));
                            datePicker_end = MaterialDatePicker.Builder.datePicker()
                                    .setSelection(Long.parseLong(""+epoch_long))
                                    .setCalendarConstraints(constraints_end.build())
                                    .setTitleText("Pilih Tanggal Check-out").build();



                        }
                    });

                }
            }
        });
        tglMulai.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker_start.show(getActivity().getSupportFragmentManager(), "tgl_keberangkatan");
                datePicker_start.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        long epoch_long = Long.parseLong(selection.toString());
                        ZonedDateTime dateTime= Instant.ofEpochMilli(epoch_long).atZone(ZoneId.of("Asia/Jakarta"));
                        String tanggal = dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM YYYY", lokal));
                        tglMulai.getEditText().setText(tanggal);
                        constraints_end = new CalendarConstraints.Builder()
                                .setValidator(DateValidatorPointForward.from(Long.parseLong(""+epoch_long)))
                                .setStart(Long.parseLong(""+epoch_long));
                        datePicker_end = MaterialDatePicker.Builder.datePicker()
                                .setSelection(Long.parseLong(""+epoch_long))
                                .setCalendarConstraints(constraints_end.build())
                                .setTitleText("Pilih Tanggal Check-out").build();

                    }
                });
            }
        });

        //TANGGAL BERAKHIR
        tglBerakhir.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b==true){
                if (tglMulai.getEditText().getText().toString().matches("")) {
                        MaterialDatePicker datePicker_akhir = MaterialDatePicker.Builder.datePicker()
                                .setSelection(today)
                                .setTitleText("Pilih Batas Tanggal Akhir").build();
                        datePicker_akhir.show(getActivity().getSupportFragmentManager(), "tgl_keberangkatan");
                        datePicker_akhir.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onPositiveButtonClick(Object selection) {
                                long epoch_long = Long.parseLong(selection.toString());
                                ZonedDateTime dateTime= Instant.ofEpochMilli(epoch_long).atZone(ZoneId.of("Asia/Jakarta"));
                                String tanggal = dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM YYYY", lokal));
                                tglBerakhir.getEditText().setText(tanggal);
                            }
                        });
                    } else {
                    datePicker_end.show(getActivity().getSupportFragmentManager(), "tgl_keberangkatan");
                    datePicker_end.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onPositiveButtonClick(Object selection) {
                            long epoch_long = Long.parseLong(selection.toString());
                            ZonedDateTime dateTime= Instant.ofEpochMilli(epoch_long).atZone(ZoneId.of("Asia/Jakarta"));
                            String tanggal = dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM YYYY", lokal));
                            tglBerakhir.getEditText().setText(tanggal);
                        }
                    });
                }

                }
            }
        });
        tglBerakhir.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tglMulai.getEditText().getText().toString().matches("")) {
                    MaterialDatePicker datePicker_akhir = MaterialDatePicker.Builder.datePicker()
                            .setSelection(today)
                            .setTitleText("Pilih Batas Tanggal Akhir").build();
                    datePicker_akhir.show(getActivity().getSupportFragmentManager(), "tgl_keberangkatan");
                    datePicker_akhir.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onPositiveButtonClick(Object selection) {
                            long epoch_long = Long.parseLong(selection.toString());
                            ZonedDateTime dateTime= Instant.ofEpochMilli(epoch_long).atZone(ZoneId.of("Asia/Jakarta"));
                            String tanggal = dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM YYYY", lokal));
                            tglBerakhir.getEditText().setText(tanggal);
                        }
                    });
                } else {
                    datePicker_end.show(getActivity().getSupportFragmentManager(), "tgl_keberangkatan");
                    datePicker_end.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onPositiveButtonClick(Object selection) {
                            long epoch_long = Long.parseLong(selection.toString());
                            ZonedDateTime dateTime= Instant.ofEpochMilli(epoch_long).atZone(ZoneId.of("Asia/Jakarta"));
                            String tanggal = dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM YYYY", lokal));
                            tglBerakhir.getEditText().setText(tanggal);
                        }
                    });
                }
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





}