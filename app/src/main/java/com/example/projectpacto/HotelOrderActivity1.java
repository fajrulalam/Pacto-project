package com.example.projectpacto;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.projectpacto.databinding.ActivityHotelOrder1Binding;
import com.example.projectpacto.databinding.ActivityPlaneOrder1Binding;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class HotelOrderActivity1 extends AppCompatActivity implements Hotel_KotaAtauAkomodasi.DataHotel {

    ActivityHotelOrder1Binding binding;
    String kotaAtauHotel;
    String tglCek_in;
    String tglCek_out;
    String jumlahKamar;
    String tanggal_cekIn;
    String tanggal_cekOut;
    long epoch_cekIn;
    long epoch_cekOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHotelOrder1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.kotaAtauHotel.getEditText().setInputType(TextView.AUTO_SIZE_TEXT_TYPE_NONE);
        binding.tglCekIn.getEditText().setInputType(TextView.AUTO_SIZE_TEXT_TYPE_NONE);
        binding.tglCekOut.getEditText().setInputType(TextView.AUTO_SIZE_TEXT_TYPE_NONE);





        //KOTA ATAU HOTEL
        binding.kotaAtauHotel.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b==true) {
                    Hotel_KotaAtauAkomodasi hotel_kotaAtauAkomodasi = new Hotel_KotaAtauAkomodasi();
                    Bundle bundle = new Bundle();
                    bundle.putString("judul", "Tentukan kota atau nama akomodasi");
                    hotel_kotaAtauAkomodasi.setArguments(bundle);
                    hotel_kotaAtauAkomodasi.show(getSupportFragmentManager(), hotel_kotaAtauAkomodasi.getTag());

                }
            }
        });
        binding.kotaAtauHotel.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hotel_KotaAtauAkomodasi hotel_kotaAtauAkomodasi = new Hotel_KotaAtauAkomodasi();
                Bundle bundle = new Bundle();
                bundle.putString("judul", "Tentukan kota atau nama akomodasi");
                Log.i("CLICKED", "BOTTOM SHEET SHOULD POP UP");
                hotel_kotaAtauAkomodasi.setArguments(bundle);
                hotel_kotaAtauAkomodasi.show(getSupportFragmentManager(), hotel_kotaAtauAkomodasi.getTag());
            }
        });


        //TGL CEK IN
        Locale lokal = new Locale("id", "ID");
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
                    datePicker_start.show(getSupportFragmentManager(), "tgl_keberangkatan");
                    datePicker_start.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onPositiveButtonClick(Object selection) {
                            epoch_cekIn = Long.parseLong(selection.toString());
                            ZonedDateTime dateTime= Instant.ofEpochMilli(epoch_cekIn).atZone(ZoneId.of("Asia/Jakarta"));
                            tanggal_cekIn = dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM YYYY", lokal));

                            binding.tglCekIn.getEditText().setText(tanggal_cekIn);

                        }
                    });
                }
            }
        });

        binding.tglCekIn.getEditText().setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  datePicker_start.show(getSupportFragmentManager(), "tgl_keberangkatan");
                  datePicker_start.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                      @RequiresApi(api = Build.VERSION_CODES.O)
                      @Override
                      public void onPositiveButtonClick(Object selection) {
                          epoch_cekIn = Long.parseLong(selection.toString());
                          ZonedDateTime dateTime = Instant.ofEpochMilli(epoch_cekIn).atZone(ZoneId.of("Asia/Jakarta"));
                          tanggal_cekOut = dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM YYYY", lokal));

                          binding.tglCekIn.getEditText().setText(tanggal_cekOut);
                      }
                  });
              }
          });


                //TGL CEK OUT

                CalendarConstraints.Builder constraints_end = new CalendarConstraints.Builder()
                        .setValidator(DateValidatorPointForward.from(Long.parseLong(tanggal_cekIn)))
                        .setStart(today);
                MaterialDatePicker datePicker_end = MaterialDatePicker.Builder.datePicker()
                        .setSelection(today)
                        .setCalendarConstraints(constraints_end.build())
                        .setTitleText("Pilih Tanggal Check-out").build();

                binding.tglCekOut.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if (b == true) {
                            datePicker_end.show(getSupportFragmentManager(), "tgl_keberangkatan");
                            datePicker_end.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.O)
                                @Override
                                public void onPositiveButtonClick(Object selection) {
                                    epoch_cekOut = Long.parseLong(selection.toString());
                                    ZonedDateTime dateTime = Instant.ofEpochMilli(epoch_cekOut).atZone(ZoneId.of("Asia/Jakarta"));
                                    tanggal_cekOut = dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM YYYY", lokal));

                                    binding.tglCekOut.getEditText().setText(tanggal_cekOut);

                                }
                            });
                        }
                    }
                });

                binding.tglCekOut.getEditText().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        datePicker_end.show(getSupportFragmentManager(), "tgl_keberangkatan");
                        datePicker_end.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onPositiveButtonClick(Object selection) {
                                epoch_cekOut = Long.parseLong(selection.toString());
                                ZonedDateTime dateTime = Instant.ofEpochMilli(epoch_cekOut).atZone(ZoneId.of("Asia/Jakarta"));
                                tanggal_cekOut = dateTime.format(DateTimeFormatter.ofPattern("E, dd MMM YYYY", lokal));

                                binding.tglCekOut.getEditText().setText(tanggal_cekOut);
                            }
                        });
                    }
                });



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
            case "Persyaratan Perjalanan":
                bundle.putString("judul", view.getTag().toString());
                bundle.putString("tanggal", "25 Januari 2020");
                break;
        }
        pesawatYangPerluDiperhatikan.setArguments(bundle);
        pesawatYangPerluDiperhatikan.show(getSupportFragmentManager(), pesawatYangPerluDiperhatikan.getTag());
    }


    @Override
    public void onDataPass(String namaHotel, String keterangan) {
        binding.kotaAtauHotel.getEditText().setText(namaHotel);
    }

}