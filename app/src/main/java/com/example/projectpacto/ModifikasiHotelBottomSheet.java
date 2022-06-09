package com.example.projectpacto;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;

import com.example.projectpacto.databinding.FragmentBagasiBottomsheetRedesignBinding;
import com.example.projectpacto.databinding.FragmentModifikasiHotelBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;


public class ModifikasiHotelBottomSheet extends BottomSheetDialogFragment {

    FragmentModifikasiHotelBottomSheetBinding binding;

    String kotaAtauHotel;
    String tglCek_in;
    String tglCek_out;
    String jumlahMalam;
    String jumlahKamar;
    String tanggal_cekIn;
    String tanggal_cekOut;
    long epoch_cekIn;
    long epoch_cekOut;
    int jumlahKamar_int;
    int jumlahTamu_int;

    CalendarConstraints.Builder constraints_end;
    MaterialDatePicker datePicker_end;
    KirimModifikasiPesanan datapasser;
    KirimModifikasiPesanan datapasserKamar;

    public ModifikasiHotelBottomSheet() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentModifikasiHotelBottomSheetBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        tglCek_in = getArguments().getString("tglCek_in");
        tglCek_out = getArguments().getString("tglCek_out");
        jumlahMalam = getArguments().getString("jumlahMalam");
        jumlahKamar = getArguments().getString("jumlahKamar");

        binding.tglCekIn.getEditText().setText(tglCek_in);
        binding.tglCekOut.getEditText().setText(tglCek_out);
        binding.jumlahMalam.getEditText().setText(jumlahMalam);
        binding.jumlahKamar.getEditText().setText(jumlahKamar);

        Locale lokal = new Locale("id", "ID");

        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy", lokal);
        try {
            Date tglCek_in_date = formatter.parse(tglCek_in);
            epoch_cekIn = tglCek_in_date.getTime();

        } catch (ParseException parseException) {
            parseException.printStackTrace();
        }

        constraints_end = new CalendarConstraints.Builder()
                .setValidator(DateValidatorPointForward.from(Long.parseLong(""+epoch_cekIn)))
                .setStart(Long.parseLong(""+epoch_cekIn));
        datePicker_end = MaterialDatePicker.Builder.datePicker()
                .setSelection(Long.parseLong(""+epoch_cekIn))
                .setCalendarConstraints(constraints_end.build())
                .setTitleText("Pilih Tanggal Check-out").build();




        //Close Sheet
        binding.closeSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });




        //TGL CEK IN
        long today = MaterialDatePicker.todayInUtcMilliseconds();
        CalendarConstraints.Builder constraints = new CalendarConstraints.Builder()
                .setValidator(DateValidatorPointForward.now())
                .setStart(today);
        MaterialDatePicker datePicker_start = MaterialDatePicker.Builder.datePicker()
                .setSelection(today)
                .setCalendarConstraints(constraints.build())
                .setTitleText("Pilih Tanggal Check-in").build();

        binding.tglCekIn.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b==true){
                    datePicker_start.show(getActivity().getSupportFragmentManager(), "tgl_keberangkatan");
                    datePicker_start.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onPositiveButtonClick(Object selection) {
                            epoch_cekIn = Long.parseLong(selection.toString());

                            ZonedDateTime dateTime= Instant.ofEpochMilli(Long.parseLong(selection.toString())).atZone(ZoneId.of("Asia/Jakarta"));
                            tanggal_cekIn = dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM YYYY", lokal));
                            Log.i("TGL CEK IN", ""+ epoch_cekIn);

                            binding.tglCekIn.getEditText().setText(tanggal_cekIn);

                            binding.tglCekOut.getEditText().setText("");
                            binding.jumlahMalam.getEditText().setText("");

                            constraints_end = new CalendarConstraints.Builder()
                                    .setValidator(DateValidatorPointForward.from(Long.parseLong(""+epoch_cekIn)))
                                    .setStart(Long.parseLong(""+epoch_cekIn));
                            datePicker_end = MaterialDatePicker.Builder.datePicker()
                                    .setSelection(Long.parseLong(""+epoch_cekIn))
                                    .setCalendarConstraints(constraints_end.build())
                                    .setTitleText("Pilih Tanggal Check-out").build();

                        }
                    });
                }
            }
        });

        binding.tglCekIn.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker_start.show(getActivity().getSupportFragmentManager(), "tgl_keberangkatan");
                datePicker_start.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        epoch_cekIn = Long.parseLong(selection.toString());
                        ZonedDateTime dateTime = Instant.ofEpochMilli(epoch_cekIn).atZone(ZoneId.of("Asia/Jakarta"));
                        tanggal_cekIn = dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM YYYY", lokal));


                        binding.tglCekIn.getEditText().setText(tanggal_cekIn);
                        binding.tglCekOut.getEditText().setText("");
                        binding.jumlahMalam.getEditText().setText("");

                        constraints_end = new CalendarConstraints.Builder()
                                .setValidator(DateValidatorPointForward.from(Long.parseLong(""+epoch_cekIn)))
                                .setStart(Long.parseLong(""+epoch_cekIn));
                        datePicker_end = MaterialDatePicker.Builder.datePicker()
                                .setSelection(Long.parseLong(""+epoch_cekIn))
                                .setCalendarConstraints(constraints_end.build())
                                .setTitleText("Pilih Tanggal Check-out").build();
                    }
                });
            }
        });




        //TGL CEK OUT

        binding.tglCekOut.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b == true) {
                    if (!binding.tglCekIn.getEditText().getText().toString().matches("")) {
                        datePicker_end.show(getActivity().getSupportFragmentManager(), "tgl_keberangkatan");
                        datePicker_end.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onPositiveButtonClick(Object selection) {
                                epoch_cekOut = Long.parseLong(selection.toString());
                                ZonedDateTime dateTime = Instant.ofEpochMilli(epoch_cekOut).atZone(ZoneId.of("Asia/Jakarta"));
                                tanggal_cekOut = dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM YYYY", lokal));

                                binding.tglCekOut.getEditText().setText(tanggal_cekOut);

                                long jmlMalam = ((epoch_cekOut - epoch_cekIn) / 86400000) +1;
                                binding.jumlahMalam.getEditText().setText(""+jmlMalam);

                            }
                        });
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Tentukan dulu tanggal cek in", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        binding.tglCekOut.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.tglCekIn.getEditText().getText().toString().matches("")) {
                    datePicker_end.show(getActivity().getSupportFragmentManager(), "tgl_keberangkatan");
                    datePicker_end.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onPositiveButtonClick(Object selection) {
                            epoch_cekOut = Long.parseLong(selection.toString());
                            ZonedDateTime dateTime = Instant.ofEpochMilli(epoch_cekOut).atZone(ZoneId.of("Asia/Jakarta"));
                            tanggal_cekOut = dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM YYYY", lokal));

                            binding.tglCekOut.getEditText().setText(tanggal_cekOut);
                            long jmlMalam = ((epoch_cekOut - epoch_cekIn) / 86400000) + 1;
                            binding.jumlahMalam.getEditText().setText("" + jmlMalam);

                        }
                    });
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Tentukan dulu tanggal cek in", Toast.LENGTH_SHORT).show();

                }
            }
        });

        //JUMLAH TAMU DAN KAMAR
        binding.jumlahKamar.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                tglCek_in = binding.tglCekIn.getEditText().getText().toString();
                tglCek_out = binding.tglCekOut.getEditText().getText().toString();
                jumlahMalam = binding.jumlahMalam.getEditText().getText().toString();
                jumlahKamar = binding.jumlahKamar.getEditText().getText().toString();
                datapasser.KirimModifikasiPesanan(tglCek_in, tglCek_out, jumlahMalam, jumlahKamar, "Change");
                dismiss();

//                JumlahKamarBottomSheet jumlahKamarBottomSheet = new JumlahKamarBottomSheet();
//                Bundle bundle = new Bundle();
//                bundle.putInt("jmlKamar", jumlahKamar_int);
//                bundle.putInt("jmlTamu", jumlahTamu_int);
//                jumlahKamarBottomSheet.setArguments(bundle);

//                Bundle bundle = new Bundle();
//                bundle.putInt("jmlKamar", jumlahKamar_int);
//                bundle.putInt("jmlTamu", jumlahTamu_int);
//                JumlahKamarBottomSheet jumlahKamarBottomSheet = new JumlahKamarBottomSheet();
//                jumlahKamarBottomSheet.setArguments(bundle);
//                jumlahKamarBottomSheet.show(getActivity().getSupportFragmentManager(), jumlahKamarBottomSheet.getTag());
            }
        });

        binding.jumlahKamar.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tglCek_in = binding.tglCekIn.getEditText().getText().toString();
                tglCek_out = binding.tglCekOut.getEditText().getText().toString();
                jumlahMalam = binding.jumlahMalam.getEditText().getText().toString();
                jumlahKamar = binding.jumlahKamar.getEditText().getText().toString();
                datapasser.KirimModifikasiPesanan(tglCek_in, tglCek_out, jumlahMalam, jumlahKamar, "Change");
                dismiss();

//                JumlahKamarBottomSheet jumlahKamarBottomSheet = new JumlahKamarBottomSheet();
//                Bundle bundle = new Bundle();
//                bundle.putInt("jmlKamar", jumlahKamar_int);
//                bundle.putInt("jmlTamu", jumlahTamu_int);
//                jumlahKamarBottomSheet.setArguments(bundle);

//                Bundle bundle = new Bundle();
//                bundle.putInt("jmlKamar", jumlahKamar_int);
//                bundle.putInt("jmlTamu", jumlahTamu_int);
//                JumlahKamarBottomSheet jumlahKamarBottomSheet = new JumlahKamarBottomSheet();
//                jumlahKamarBottomSheet.setArguments(bundle);
//                jumlahKamarBottomSheet.show(getActivity().getSupportFragmentManager(), jumlahKamarBottomSheet.getTag());
            }
        });



        //CARI BUTTON
        binding.cariButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tglCek_in = binding.tglCekIn.getEditText().getText().toString();
                tglCek_out = binding.tglCekOut.getEditText().getText().toString();
                jumlahMalam = binding.jumlahMalam.getEditText().getText().toString();
                jumlahKamar = binding.jumlahKamar.getEditText().getText().toString();
                datapasser.KirimModifikasiPesanan(tglCek_in, tglCek_out, jumlahMalam, jumlahKamar, "Save");
                dismiss();
            }
        });


        return view;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_modifikasi_hotel_bottom_sheet, null);
        dialog.setContentView(view);





        return dialog;
    }

//    @Override
//    public void onDataPassTamu(String jumlahTamu, String jumlahKamar) {
//        jumlahTamu_int = Integer.parseInt(jumlahTamu);
//        jumlahKamar_int = Integer.parseInt(jumlahKamar);
//        binding.jumlahKamar.getEditText().setText(jumlahTamu + " Tamu, "+jumlahKamar+" Kamar" );
//    }

    public interface KirimModifikasiPesanan{
        void KirimModifikasiPesanan(String tglCekIn, String tglCekOut, String jumlahMalam_str, String KamardanTamu, String request);

        //if request == Save, then it just saves the modification and close the fragment.
        //if request == Change, then it will save the modification, close the fragment, then opens the jumlahkamar dan jumlah tamu.
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        datapasser = (ModifikasiHotelBottomSheet.KirimModifikasiPesanan) context;

    }
}