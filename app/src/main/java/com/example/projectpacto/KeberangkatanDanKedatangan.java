package com.example.projectpacto;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class KeberangkatanDanKedatangan extends BottomSheetDialogFragment {


    private String mParam1;
    private String mParam2;

    public KeberangkatanDanKedatangan() {
        // Required empty public constructor
    }

    private LinearLayout appBarLayout;
    private LinearLayout linearLayout;
    private ImageView closeSheet;
    private TextView judul;
    private TextInputLayout bandaraTextInput;
    private TextView cariButton;
    private RecyclerView recyclerView;
    private ArrayList<String> namaBandara;
    private ArrayList<String> namaKota;
    private OnDataKeberangkatanAtauKepulangan datapasser;



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_keberangkatan_dan_kedatangan, null);
        dialog.setContentView(view);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);

        appBarLayout = view.findViewById(R.id.appBarLayout);
        linearLayout = view.findViewById(R.id.linearLayout);
        closeSheet  = view.findViewById(R.id.closeSheet);
        judul = view.findViewById(R.id.KedatanganAtauKeberangkatan);
        bandaraTextInput = view.findViewById(R.id.bandaraKota_textInput);
        cariButton = view.findViewById(R.id.cariBandaraButton);
        recyclerView = view.findViewById(R.id.RecycleViewKota_Bandara);
        namaBandara = new ArrayList<>();
        namaKota = new ArrayList<>();

        Bundle bundle = this.getArguments();
        judul.setText(bundle.getString("judul"));
        RecyclerAdapaterBandara recyclerAdapaterBandara = new RecyclerAdapaterBandara(namaKota, namaBandara);
        recyclerView.setAdapter(recyclerAdapaterBandara);



        cariButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namaBandara.clear();
                namaKota.clear();
                switch (bandaraTextInput.getEditText().getText().toString()) {
                    case "surabaya":
                        namaBandara.add("Juanda International Airport");
                        namaKota.add("Surabaya");
                        recyclerAdapaterBandara.notifyDataSetChanged();

                        break;
                    case "jakarta":
                        namaKota.add("Jakarta");
                        namaKota.add("Jakarta");
                        namaBandara.add("Halim Perdanakusuma International Airport");
                        namaBandara.add("Soekarno-Hatta International Airport");
                        recyclerAdapaterBandara.notifyDataSetChanged();

                        break;
                }


            }
        });

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                String bandara = namaBandara.get(position);
                String heading = judul.getText().toString();
                String kota = namaKota.get(position);
                datapasser.onDataPass(heading, bandara, kota);
                dismiss();
            }
        });


        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (BottomSheetBehavior.STATE_EXPANDED == newState) {
//                    hideView(appBarLayout);
                    showView(linearLayout, getActionBarSize());
                }

                if (BottomSheetBehavior.STATE_COLLAPSED == newState)   {
//                    hideView(appBarLayout);
                    showView(linearLayout, getActionBarSize());

                }

                if (BottomSheetBehavior.STATE_HIDDEN == newState) {
                    dismiss();
                }

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        closeSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



        return dialog;
    }

    public interface OnDataKeberangkatanAtauKepulangan {
        void onDataPass(String pulangAtauPergi, String bandara, String kota);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        datapasser = (OnDataKeberangkatanAtauKepulangan) context;
    }

    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;    }


    private void hideView(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = 0;
        view.setLayoutParams(params);
    }

    private void showView(View view, int size) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = size;
        view.setLayoutParams(params);
    }

    private int getActionBarSize(){
        final TypedArray typedArray =getContext().getTheme().obtainStyledAttributes(new int[]{
                android.R.attr.actionBarSize
        });

        return (int) typedArray.getDimension(0, 0);
    }
}