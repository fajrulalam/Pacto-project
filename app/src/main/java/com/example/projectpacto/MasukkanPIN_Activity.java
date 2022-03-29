package com.example.projectpacto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;

import com.example.projectpacto.databinding.ActivityHotelOrder4Binding;
import com.example.projectpacto.databinding.ActivityMasukkanPinBinding;

public class MasukkanPIN_Activity extends AppCompatActivity implements TextWatcher {
    ActivityMasukkanPinBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMasukkanPinBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.pin1.addTextChangedListener( this);

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}