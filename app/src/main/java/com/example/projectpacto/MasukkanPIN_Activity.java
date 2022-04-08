package com.example.projectpacto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.projectpacto.databinding.ActivityHotelOrder4Binding;
import com.example.projectpacto.databinding.ActivityMasukkanPinBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MasukkanPIN_Activity extends AppCompatActivity  {
    ActivityMasukkanPinBinding binding;
    String pin;

    Bundle extras;


    FirebaseFirestore fs;
    HashMap<String, Object> pesananHotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMasukkanPinBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        extras = this.getIntent().getBundleExtra("bundle");
        fs = FirebaseFirestore.getInstance();

        pesananHotel = new HashMap<>();



        binding.pin1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 1){
                    binding.pin2.requestFocus();
                }else if(editable.length() == 0)  {
                    Log.i("KOSONG", "PIN KOSONG");
                } else {
                    binding.pin1.setText(binding.pin3.getText().subSequence(0,0));
                }

            }
        });

        binding.pin2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 1){
                    binding.pin3.requestFocus();
                }else if(editable.length() == 0)  {
                    binding.pin1.requestFocus();
                } else {
                    binding.pin2.setText(binding.pin3.getText().subSequence(0,0));
                }


            }
        });

        binding.pin3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 1){
                    binding.pin4.requestFocus();
                }else if(editable.length() == 0)  {
                    binding.pin2.requestFocus();
                } else {
                    binding.pin3.setText(binding.pin3.getText().subSequence(0,0));
                }

            }
        });

        binding.pin4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 1){
                    Log.i("TERISIS", "PIN TERISI");
                    InputMethodManager mgr = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    mgr.hideSoftInputFromWindow(binding.pin4.getWindowToken(), 0);

                    pin = binding.pin1.getText().toString() + binding.pin2.getText().toString() + binding.pin3.getText().toString() + binding.pin4.getText().toString();
                    checkPIN(pin);


                }else if(editable.length() == 0)  {
                    binding.pin3.requestFocus();
                } else {
                    binding.pin4.setText(binding.pin3.getText().subSequence(0,0));
                }

            }
        });

    }

    public void checkPIN(String pin){
        if (pin.matches("1738")){
            pesananHotel.put("orderNumber", 1);
            pesananHotel.put("bookingCode", "18ABAN");
            pesananHotel.put("namaHotel", extras.getString("namaHotel"));

            Log.i("PIN_CORRECT", "SEND DATA TO FIRESTORE");


        }
    }


}