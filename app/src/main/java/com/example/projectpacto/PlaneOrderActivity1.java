package com.example.projectpacto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.projectpacto.databinding.ActivityPlaneOrder1Binding;
import com.example.projectpacto.databinding.FragmentPenumpangBottomSheetBinding;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class PlaneOrderActivity1 extends AppCompatActivity implements PenumpangBottomSheet.OnDataPassenger {

    ActivityPlaneOrder1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaneOrder1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.tanggalKeberangkatTextInput.getEditText().setInputType(TextView.AUTO_SIZE_TEXT_TYPE_NONE);
        binding.penumpangTextInput.getEditText().setInputType(TextView.AUTO_SIZE_TEXT_TYPE_NONE);


        //Penumpang
        binding.penumpangTextInput.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b==true) {
                    Log.i("CLICK LISTENED", "It's focused...");
                    PenumpangBottomSheet penumpangBottomSheet = new PenumpangBottomSheet();
                    penumpangBottomSheet.show(getSupportFragmentManager(), penumpangBottomSheet.getTag());

                }
            }
        });
        binding.penumpangTextInput.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("CLICK LISTENED", "It's focused...");
                PenumpangBottomSheet penumpangBottomSheet = new PenumpangBottomSheet();
                penumpangBottomSheet.show(getSupportFragmentManager(), penumpangBottomSheet.getTag());
            }
        });



        //Calendar Constraints
        long today = MaterialDatePicker.todayInUtcMilliseconds();
        CalendarConstraints.Builder constraints = new CalendarConstraints.Builder()
                .setStart(today);
        MaterialDatePicker datePicker_start = MaterialDatePicker.Builder.datePicker()
                .setSelection(today)
                .setCalendarConstraints(constraints.build())
                .setTitleText("Pilih Tanggal Keberangkatan").build();

        binding.tanggalKeberangkatTextInput.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b==true) {
                    datePicker_start.show(getSupportFragmentManager(), "tgl_keberangkatan");
                    datePicker_start.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                        @Override
                        public void onPositiveButtonClick(Object selection) {
                            binding.tanggalKeberangkatTextInput.getEditText().setText(datePicker_start.getHeaderText());

                        }
                    });

                }
            }
        });
        binding.tanggalKeberangkatTextInput.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker_start.show(getSupportFragmentManager(), "tgl_keberangkatan");
                datePicker_start.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        binding.tanggalKeberangkatTextInput.getEditText().setText(datePicker_start.getHeaderText());

                    }
                });

            }
        });

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RealMainActivity.class));
                overridePendingTransition(0, 0);


            }
        });
    }

    @Override
    public void onDataPass(int dewasa, int anak, int balita, String kelas) {
        if (anak + balita == 0) {
            binding.penumpangTextInput.getEditText().setText(dewasa + " Dewasa, " + kelas);
        } else if (anak > 0 && balita == 0) {
            binding.penumpangTextInput.getEditText().setText(dewasa + " Dewasa, " + anak + " Anak, " + kelas);
        } else if (anak == 0 && balita > 0) {
            binding.penumpangTextInput.getEditText().setText(dewasa + " Dewasa, " + balita + " Balita, " + kelas);
        } else {
            binding.penumpangTextInput.getEditText().setText(dewasa + " Dewasa, " + anak + " Anak, " + + balita + " Balita, " + kelas);

        }
    }

    public void HalYangPerluDiperhatikan(View view) {
        PesawatYangPerluDiperhatikan pesawatYangPerluDiperhatikan = new PesawatYangPerluDiperhatikan();
        Bundle bundle = new Bundle();


        switch (view.getTag().toString()) {
            case "Cara Reschedule":
                bundle.putString("judul", view.getTag().toString());
                bundle.putString("tanggal", "19 Januari 2020");

                break;
            case "Cara Pembatalan":
                bundle.putString("judul", view.getTag().toString());
                bundle.putString("tanggal", "21 Januari 2020");

                break;
            case "Persyaratan Penerbangan":
                bundle.putString("judul", view.getTag().toString());
                bundle.putString("tanggal", "25 Januari 2020");
                break;
        }
        pesawatYangPerluDiperhatikan.setArguments(bundle);
        pesawatYangPerluDiperhatikan.show(getSupportFragmentManager(), pesawatYangPerluDiperhatikan.getTag());
    }
}