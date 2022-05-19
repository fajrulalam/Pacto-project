package com.example.projectpacto;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectpacto.databinding.ActivityPlaneOrder1Binding;
import com.example.projectpacto.databinding.FragmentPenumpangBottomSheetBinding;
import com.example.projectpacto.roundtrip.PlaneOrderActivity2_Pergi;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PlaneOrderActivity1 extends AppCompatActivity implements PenumpangBottomSheet.OnDataPassenger, KeberangkatanDanKedatangan.OnDataKeberangkatanAtauKepulangan {

    ActivityPlaneOrder1Binding binding;
    String kota_keberangkatan;
    String kota_kedatangan;
    String bandara_kedatangan;
    String bandara_keberangkatan;
    String keberangkatan;
    String kedatangan;
    String tanggalBerangkat;
    String tanggalPulang;
    String penumpang;
    CalendarConstraints.Builder constraints_end;
    MaterialDatePicker datePicker_end;


    int dewasa_int;
    int anak_int;
    int balita_int;
    String kelas_str;

    @RequiresApi(api = Build.VERSION_CODES.O)
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

        dewasa_int = -1;
        anak_int = -1;
        balita_int = -1;
        kelas_str = "";

        keberangkatan = "";
        kedatangan = "";
        tanggalBerangkat = "";
        penumpang = "";
        tanggalPulang = "";
        Locale lokal = new Locale("id", "ID");


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            keberangkatan = extras.getString("keberangkatan");
            kedatangan = extras.getString("kedatangan");
            tanggalBerangkat = extras.getString("tanggal");
            tanggalPulang = extras.getString("tanggal_pulang");
            Log.i("TanggalPulang", tanggalPulang +"tes");
            try {
                if (!tanggalPulang.matches("")) {
                    binding.ppSwitch.setChecked(true);
                    binding.tanggalKepulanganTextInput.setVisibility(View.VISIBLE);
                    binding.tanggalKepulanganTextInput.getEditText().setText(tanggalPulang);
                }
            } catch (Exception e){
                Log.i("Exception", "Ini adalah trip one way");
            }
            try {
                Date date1=new SimpleDateFormat("E, dd MMM yyyy", lokal).parse(tanggalBerangkat);
                Long epoch_long= date1.getTime();
                ZonedDateTime dateTime= Instant.ofEpochMilli(epoch_long).atZone(ZoneId.of("Asia/Jakarta"));
                tanggalBerangkat = dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM YYYY", lokal));
                tanggalPulang = "";
//                binding.tanggalKepulanganTextInput.getEditText().setText("");

                binding.tanggalKeberangkatTextInput.getEditText().setText(tanggalBerangkat);

                constraints_end = new CalendarConstraints.Builder()
                        .setValidator(DateValidatorPointForward.from(Long.parseLong(""+epoch_long)))
                        .setStart(Long.parseLong(""+epoch_long));
                datePicker_end = MaterialDatePicker.Builder.datePicker()
                        .setSelection(Long.parseLong(""+epoch_long))
                        .setCalendarConstraints(constraints_end.build())
                        .setTitleText("Pilih Tanggal Kepulangan").build();
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }

            penumpang = extras.getString("penumpang");
            bandara_keberangkatan = extras.getString("bandara_keberangkatan").split(" \\(")[0];
            bandara_kedatangan = extras.getString("bandara_kedatangan").split(" \\(")[0];
            Log.i("Keberangkatan1", "hello" + keberangkatan);
            binding.keberangkatTextInput.getEditText().setText(keberangkatan);
            binding.kedatanganTextInput.getEditText().setText(kedatangan);
            binding.tanggalKeberangkatTextInput.getEditText().setText(tanggalBerangkat);
            binding.penumpangTextInput.getEditText().setText(penumpang);
        }


        //Action Button
        binding.cariButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keberangkatan = binding.keberangkatTextInput.getEditText().getText().toString();
                kedatangan = binding.kedatanganTextInput.getEditText().getText().toString();
//                tanggalBerangkat = binding.tanggalKeberangkatTextInput.getEditText().getText().toString();
                penumpang = binding.penumpangTextInput.getEditText().getText().toString();

                if(binding.ppSwitch.isChecked()){
                    Intent intent=  new Intent(getApplicationContext(), PlaneOrderActivity2_Pergi.class);

                    //for testing purpose
//                    keberangkatan = "Min, 16 Mei 2022";
//                    kedatangan = "Min, 16 Mei 2022";
//                    kota_keberangkatan = "Surabaya";
//                    kota_kedatangan = "Jakarta";
//                    bandara_kedatangan = "Juanda International Airport (SUB)";
//                    bandara_kedatangan = "Halim Perdana Kusuma International Airport (HLP)";
//                    tanggalBerangkat = "Min, 16 Mei 2022";
//                    penumpang = "1 Dewasa, Ekonomi";

                    intent.putExtra("keberangkatan", keberangkatan);
                    intent.putExtra("kedatangan", kedatangan);
                    intent.putExtra("kota_keberangkatan", kota_keberangkatan);
                    intent.putExtra("kota_kedatangan", kota_kedatangan);
                    intent.putExtra("bandara_kedatangan", bandara_kedatangan);
                    intent.putExtra("bandara_keberangkatan", bandara_keberangkatan);
                    intent.putExtra("tanggal", tanggalBerangkat);
                    intent.putExtra("tanggal_pulang", tanggalPulang);
                    intent.putExtra("penumpang", penumpang);
                    startActivity(intent);
                    overridePendingTransition(0,0);


                } else {
//                    if (!keberangkatan.matches("") && !kedatangan.matches("") && !tanggal.matches("") && !penumpang.matches("")){
                    Intent intent = new Intent(getApplicationContext(), PlaneOrderActivity2.class);
                    intent.putExtra("keberangkatan", keberangkatan);
                    intent.putExtra("kedatangan", kedatangan);
                    intent.putExtra("kota_keberangkatan", kota_keberangkatan);
                    intent.putExtra("kota_kedatangan", kota_kedatangan);
                    intent.putExtra("bandara_kedatangan", bandara_kedatangan);
                    intent.putExtra("bandara_keberangkatan", bandara_keberangkatan);
                    intent.putExtra("tanggal", tanggalBerangkat);
                    intent.putExtra("penumpang", penumpang);
                    startActivity(intent);
                    overridePendingTransition(0 , 0);
//                } else {
//                    Toast.makeText(getApplicationContext(), "Mohon lengkapi data terlebih dahulu", Toast.LENGTH_SHORT).show();
//                }
                }


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
                    Bundle bundle = new Bundle();
                    bundle.putString("kelas", kelas_str);
                    bundle.putInt("dewasa", dewasa_int);
                    bundle.putInt("anak", anak_int);
                    bundle.putInt("balita", balita_int);
                    PenumpangBottomSheet penumpangBottomSheet = new PenumpangBottomSheet();
                    penumpangBottomSheet.setArguments(bundle);
                    penumpangBottomSheet.show(getSupportFragmentManager(), penumpangBottomSheet.getTag());

                }
            }
        });
        binding.penumpangTextInput.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("CLICK LISTENED", "It's focused...");
                Bundle bundle = new Bundle();
                bundle.putString("kelas", kelas_str);
                bundle.putInt("dewasa", dewasa_int);
                bundle.putInt("anak", anak_int);
                bundle.putInt("balita", balita_int);
                PenumpangBottomSheet penumpangBottomSheet = new PenumpangBottomSheet();
                penumpangBottomSheet.setArguments(bundle);
                penumpangBottomSheet.show(getSupportFragmentManager(), penumpangBottomSheet.getTag());
            }
        });



        //Calendar Constraints
        long today = MaterialDatePicker.todayInUtcMilliseconds();
        CalendarConstraints.Builder constraints = new CalendarConstraints.Builder()
                .setValidator(DateValidatorPointForward.now())
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
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onPositiveButtonClick(Object selection) {
                            long epoch_long = Long.parseLong(selection.toString());
                            ZonedDateTime dateTime= Instant.ofEpochMilli(epoch_long).atZone(ZoneId.of("Asia/Jakarta"));
                            tanggalBerangkat = dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM YYYY", lokal));
                            
                            tanggalPulang = "";
                            binding.tanggalKepulanganTextInput.getEditText().setText("");

                            binding.tanggalKeberangkatTextInput.getEditText().setText(tanggalBerangkat);

                            constraints_end = new CalendarConstraints.Builder()
                                    .setValidator(DateValidatorPointForward.from(Long.parseLong(""+epoch_long)))
                                    .setStart(Long.parseLong(""+epoch_long));
                            datePicker_end = MaterialDatePicker.Builder.datePicker()
                                    .setSelection(Long.parseLong(""+epoch_long))
                                    .setCalendarConstraints(constraints_end.build())
                                    .setTitleText("Pilih Tanggal Kepulangan").build();

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
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        long epoch_long = Long.parseLong(selection.toString());
                        ZonedDateTime dateTime= Instant.ofEpochMilli(epoch_long).atZone(ZoneId.of("Asia/Jakarta"));
                        tanggalBerangkat = dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM YYYY", new Locale("id", "ID")));

                        tanggalPulang = "";
                        binding.tanggalKepulanganTextInput.getEditText().setText("");

                        binding.tanggalKeberangkatTextInput.getEditText().setText(tanggalBerangkat);

                        constraints_end = new CalendarConstraints.Builder()
                                .setValidator(DateValidatorPointForward.from(Long.parseLong(""+epoch_long)))
                                .setStart(Long.parseLong(""+epoch_long));
                        datePicker_end = MaterialDatePicker.Builder.datePicker()
                                .setSelection(Long.parseLong(""+epoch_long))
                                .setCalendarConstraints(constraints_end.build())
                                .setTitleText("Pilih Tanggal Kepulangan").build();

                    }
                });

            }
        });

        //Handling PP Switch
        binding.ppSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean true_) {
                if (true_){
                    binding.tanggalKepulanganTextInput.setVisibility(View.VISIBLE);
                    binding.tanggalKepulanganTextInput.getEditText().setText("");

                }
                if (!true_){
                    binding.tanggalKepulanganTextInput.setVisibility(View.GONE);
                    binding.tanggalKeberangkatTextInput.getEditText().setText(tanggalBerangkat);
                }
            }
        });


        binding.tanggalKepulanganTextInput.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b == true) {
                    if (!binding.tanggalKeberangkatTextInput.getEditText().getText().toString().matches("")) {
                        datePicker_end.show(getSupportFragmentManager(), "tgl_keberangkatan");
                        datePicker_end.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onPositiveButtonClick(Object selection) {
                                long epoch_cekOut = Long.parseLong(selection.toString());
                                ZonedDateTime dateTime = Instant.ofEpochMilli(epoch_cekOut).atZone(ZoneId.of("Asia/Jakarta"));
                                tanggalPulang = dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM YYYY", lokal));

                                String yearBerangkat = tanggalBerangkat.substring(tanggalBerangkat.length()-4);
                                String yearPulang = tanggalPulang.substring(tanggalPulang.length()-4);
                                if(yearBerangkat.matches(yearPulang)){
                                    binding.tanggalKepulanganTextInput.getEditText().setText(tanggalPulang.substring(0, tanggalPulang.length()-4));
                                    binding.tanggalKeberangkatTextInput.getEditText().setText(tanggalBerangkat.substring(0, tanggalBerangkat.length()-4));


                                } else {
                                    binding.tanggalKepulanganTextInput.getEditText().setText(tanggalPulang);
                                }

                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "Tentukan dulu tanggal cek in", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.tanggalKepulanganTextInput.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.tanggalKeberangkatTextInput.getEditText().getText().toString().matches("")) {
                    datePicker_end.show(getSupportFragmentManager(), "tgl_keberangkatan");
                    datePicker_end.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onPositiveButtonClick(Object selection) {
                            long epoch_cekOut = Long.parseLong(selection.toString());
                            ZonedDateTime dateTime = Instant.ofEpochMilli(epoch_cekOut).atZone(ZoneId.of("Asia/Jakarta"));
                            tanggalPulang = dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM YYYY", lokal));

                            String yearBerangkat = tanggalBerangkat.substring(tanggalBerangkat.length()-4);
                            String yearPulang = tanggalPulang.substring(tanggalPulang.length()-4);
                            if(yearBerangkat.matches(yearPulang)){
                                binding.tanggalKepulanganTextInput.getEditText().setText(tanggalPulang.substring(0, tanggalPulang.length()-4));
                                binding.tanggalKeberangkatTextInput.getEditText().setText(tanggalBerangkat.substring(0, tanggalBerangkat.length()-4));


                            } else {
                                binding.tanggalKepulanganTextInput.getEditText().setText(tanggalPulang);
                            }



                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Tentukan dulu tanggal cek in", Toast.LENGTH_SHORT).show();
                }
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
        balita_int = balita;
        anak_int = anak;
        dewasa_int = dewasa;
        kelas_str = kelas;
        Log.i("KELAS", kelas_str);
        if (anak + balita == 0) {
            binding.penumpangTextInput.getEditText().setText(dewasa + " Dewasa, " + kelas);
        } else if (anak > 0 && balita == 0) {
            binding.penumpangTextInput.getEditText().setText(dewasa + " Dewasa, " + anak + " Anak, " + kelas);
        } else if (anak == 0 && balita > 0) {
            binding.penumpangTextInput.getEditText().setText(dewasa + " Dewasa, " + balita + " Balita, " + kelas);
        } else {
            binding.penumpangTextInput.getEditText().setText(dewasa + " Dewasa, " + anak + " Anak, " +  balita + " Balita, " + kelas);

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