package com.example.projectpacto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectpacto.databinding.ActivityPlaneOrder1Binding;
import com.example.projectpacto.databinding.FragmentPenumpangBottomSheetBinding;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.util.ArrayList;

public class PlaneOrderActivity1 extends AppCompatActivity implements PenumpangBottomSheet.OnDataPassenger, KeberangkatanDanKedatangan.OnDataKeberangkatanAtauKepulangan {

    ActivityPlaneOrder1Binding binding;
    String kota_keberangkatan;
    String kota_kedatangan;
    String bandara_kedatangan;
    String bandara_keberangkatan;
    String keberangkatan;
    String kedatangan;
    String tanggal;
    String penumpang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaneOrder1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.tanggalKeberangkatTextInput.getEditText().setInputType(TextView.AUTO_SIZE_TEXT_TYPE_NONE);
        binding.penumpangTextInput.getEditText().setInputType(TextView.AUTO_SIZE_TEXT_TYPE_NONE);
        binding.kedatanganTextInput.getEditText().setInputType(TextView.AUTO_SIZE_TEXT_TYPE_NONE);
        binding.keberangkatTextInput.getEditText().setInputType(TextView.AUTO_SIZE_TEXT_TYPE_NONE);

        keberangkatan = "";
        kedatangan = "";
        tanggal = "";
        penumpang = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            keberangkatan = extras.getString("keberangkatan");
            kedatangan = extras.getString("kedatangan");
            tanggal = extras.getString("tanggal");
            penumpang = extras.getString("penumpang");
            bandara_keberangkatan = extras.getString("bandara_keberangkatan").split(" \\(")[0];
            bandara_kedatangan = extras.getString("bandara_kedatangan").split(" \\(")[0];
            Log.i("Keberangkatan1", "hello" + keberangkatan);
            binding.keberangkatTextInput.getEditText().setText(keberangkatan);
            binding.kedatanganTextInput.getEditText().setText(kedatangan);
            binding.tanggalKeberangkatTextInput.getEditText().setText(tanggal);
            binding.penumpangTextInput.getEditText().setText(penumpang);
        }


        //Action Button
        binding.cariButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keberangkatan = binding.keberangkatTextInput.getEditText().getText().toString();
                kedatangan = binding.kedatanganTextInput.getEditText().getText().toString();
                tanggal = binding.tanggalKeberangkatTextInput.getEditText().getText().toString();
                penumpang = binding.penumpangTextInput.getEditText().getText().toString();
//                if (!keberangkatan.matches("") && !kedatangan.matches("") && !tanggal.matches("") && !penumpang.matches("")){
                    Intent intent = new Intent(getApplicationContext(), PlaneOrderActivity2.class);
                    intent.putExtra("keberangkatan", keberangkatan);
                    intent.putExtra("kedatangan", kedatangan);
                    intent.putExtra("kota_keberangkatan", kota_keberangkatan);
                    intent.putExtra("kota_kedatangan", kota_kedatangan);
                    intent.putExtra("bandara_kedatangan", bandara_kedatangan);
                    intent.putExtra("bandara_keberangkatan", bandara_keberangkatan);
                    intent.putExtra("tanggal", tanggal);
                    intent.putExtra("penumpang", penumpang);
                    startActivity(intent);
                    overridePendingTransition(0 , 0);
//                } else {
//                    Toast.makeText(getApplicationContext(), "Mohon lengkapi data terlebih dahulu", Toast.LENGTH_SHORT).show();
//                }

            }
        });



        //Tombol Tukar
        binding.tukar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keberangkatan = binding.keberangkatTextInput.getEditText().getText().toString();
                String kedatangan = binding.kedatanganTextInput.getEditText().getText().toString();
                if (!keberangkatan.matches("") || !kedatangan.matches("")) {
                    binding.keberangkatTextInput.getEditText().setText(kedatangan);
                    binding.kedatanganTextInput.getEditText().setText(keberangkatan);
                    String temp = bandara_kedatangan;
                    String temp2 = bandara_keberangkatan;
                    bandara_keberangkatan = temp;
                    bandara_kedatangan = temp2;
                } else {
                    Toast.makeText(getApplicationContext(), "Mohon destinasi diisi terlebih dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Keberangkatan
        binding.keberangkatTextInput.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b==true) {
                    KeberangkatanDanKedatangan keberangkatanDanKedatangan = new KeberangkatanDanKedatangan();
                    Bundle bundle = new Bundle();
                    bundle.putString("judul", "Tentukan bandara keberangkatan");
                    keberangkatanDanKedatangan.setArguments(bundle);
                    keberangkatanDanKedatangan.show(getSupportFragmentManager(), keberangkatanDanKedatangan.getTag());

                }
            }
        });
        binding.keberangkatTextInput.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KeberangkatanDanKedatangan keberangkatanDanKedatangan = new KeberangkatanDanKedatangan();
                Bundle bundle = new Bundle();
                bundle.putString("judul", "Tentukan bandara keberangkatan");
                keberangkatanDanKedatangan.setArguments(bundle);
                keberangkatanDanKedatangan.show(getSupportFragmentManager(), keberangkatanDanKedatangan.getTag());
            }
        });

        //Kedatangan
        binding.kedatanganTextInput.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b==true){
                    KeberangkatanDanKedatangan keberangkatanDanKedatangan = new KeberangkatanDanKedatangan();
                    Bundle bundle = new Bundle();
                    bundle.putString("judul", "Tentukan bandara kedatangan");
                    keberangkatanDanKedatangan.setArguments(bundle);
                    keberangkatanDanKedatangan.show(getSupportFragmentManager(), keberangkatanDanKedatangan.getTag());
                }
            }
        });
        binding.kedatanganTextInput.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KeberangkatanDanKedatangan keberangkatanDanKedatangan = new KeberangkatanDanKedatangan();
                Bundle bundle = new Bundle();
                bundle.putString("judul", "Tentukan bandara kedatangan");
                keberangkatanDanKedatangan.setArguments(bundle);
                keberangkatanDanKedatangan.show(getSupportFragmentManager(), keberangkatanDanKedatangan.getTag());
            }
        });


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

    @Override
    public void onDataPass(String pulangAtauPergi, String bandara, String kota, String kodeBandara) {
        if (pulangAtauPergi.matches("Tentukan bandara kedatangan")) {
            binding.kedatanganTextInput.getEditText().setText(kota + " (" + kodeBandara + ")");
            bandara_kedatangan = bandara;
            kota_kedatangan = kota;
        } else {
            binding.keberangkatTextInput.getEditText().setText(kota + " (" + kodeBandara + ")");
            bandara_keberangkatan= bandara;
            Log.i("BANDARA_MASUK", bandara);
            kota_keberangkatan = kota;
        }
    }
}