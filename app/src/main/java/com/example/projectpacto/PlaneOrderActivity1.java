package com.example.projectpacto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.projectpacto.databinding.ActivityPlaneOrder1Binding;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class PlaneOrderActivity1 extends AppCompatActivity {

    ActivityPlaneOrder1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlaneOrder1Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Calendar Constraints
        long today = MaterialDatePicker.todayInUtcMilliseconds();
        CalendarConstraints.Builder constraints = new CalendarConstraints.Builder()
                .setStart(today);
        MaterialDatePicker datePicker_start = MaterialDatePicker.Builder.datePicker()
                .setSelection(today)
                .setCalendarConstraints(constraints.build())
                .setTitleText("Pilih Tanggal Keberangkatan").build();

        binding.tanggalKeberangkatTextInput.getEditText().setInputType(TextView.AUTO_SIZE_TEXT_TYPE_NONE);


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
    }
}