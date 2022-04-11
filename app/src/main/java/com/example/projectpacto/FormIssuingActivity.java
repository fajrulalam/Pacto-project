package com.example.projectpacto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.projectpacto.databinding.ActivityFormIssuingBinding;
import com.google.firebase.firestore.FirebaseFirestore;

public class FormIssuingActivity extends AppCompatActivity {

    ActivityFormIssuingBinding binding;

    FirebaseFirestore fs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormIssuingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        fs = FirebaseFirestore.getInstance();

        String documentID = getIntent().getStringExtra("documentID");

        Log.i("DocumentID FormIssuing", documentID);

//        fs.collection("bookingHistory").document(documentID);




    }
}