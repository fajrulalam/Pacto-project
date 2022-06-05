package com.example.projectpacto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.projectpacto.databinding.ActivityUbahPin1Binding;
import com.example.projectpacto.databinding.ActivityUbahPin2Binding;

public class UbahPinActivity2 extends AppCompatActivity {

    ActivityUbahPin2Binding binding;
    String userID;
    String pinLama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUbahPin2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        userID = this.getIntent().getStringExtra("userID");
        pinLama = this.getIntent().getStringExtra("pin_lama");

        binding.pin1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i == KeyEvent.KEYCODE_DEL){
                    Toast.makeText(getApplicationContext(), "Masukkan PIN", Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });

        binding.pin2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i == KeyEvent.KEYCODE_DEL){
                    binding.pin1.requestFocus();
                }

                return false;
            }
        });

        binding.pin3.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i == KeyEvent.KEYCODE_DEL){
                    binding.pin2.requestFocus();
                }

                return false;
            }
        });


        binding.pin4.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(i == KeyEvent.KEYCODE_DEL){
                    binding.pin3.requestFocus();
                }

                return false;
            }
        });



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

                    String pin = binding.pin1.getText().toString() + binding.pin2.getText().toString() + binding.pin3.getText().toString() + binding.pin4.getText().toString();

                    if (!pin.matches(pinLama)) {
                        CheckPin(pin);
                    } else {
                        Toast.makeText(getApplicationContext(), "Pastikan PIN berbeda dengan sebelumnya", Toast.LENGTH_SHORT).show();
                        binding.pin1.setText("");
                        binding.pin2.setText("");
                        binding.pin3.setText("");
                        binding.pin4.setText("");
                        binding.pin1.requestFocus();
                    }


                }else if(editable.length() == 0)  {
                    binding.pin3.requestFocus();
                } else {
                    binding.pin4.setText(binding.pin3.getText().subSequence(0,0));
                }

            }
        });
    }

    public void CheckPin(String pin){

        Intent intent = new Intent(getApplicationContext(), UbahPinActivity3.class);
        intent.putExtra("userID", userID);
        intent.putExtra("newPIN", pin);
        startActivity(intent);



    }
}