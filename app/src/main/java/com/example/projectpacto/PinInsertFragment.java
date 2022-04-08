package com.example.projectpacto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectpacto.databinding.FragmentPinInsertBinding;


public class PinInsertFragment extends Fragment {
    FragmentPinInsertBinding binding;




    public PinInsertFragment() {
        // Required empty public constructor
    }

    public static PinInsertFragment newInstance(String param1, String param2) {
        PinInsertFragment fragment = new PinInsertFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pin_insert, container, false);
    }
}