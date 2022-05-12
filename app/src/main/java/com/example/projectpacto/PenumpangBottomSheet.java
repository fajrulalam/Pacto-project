package com.example.projectpacto;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.

 */
public class PenumpangBottomSheet extends BottomSheetDialogFragment {

    TextView dewasaTextView;
    TextView anak2TextView;
    TextView balitaTextview;
    ImageView minusDewasa;
    ImageView plusDewasa;
    ImageView minusAnak;
    ImageView plusAnak;
    ImageView minusBalita;
    ImageView plusBalita;
    RadioGroup radioGroup;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioButton radioButton4;
    Button actionButton;
    OnDataPassenger datapasser;

    public PenumpangBottomSheet() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_penumpang_bottom_sheet, null);
        dialog.setContentView(view);




        dewasaTextView = view.findViewById(R.id.passengerCount_dewasa);
        anak2TextView = view.findViewById(R.id.passengerCount_anak);
        balitaTextview = view.findViewById(R.id.passengerCount_Balita);
        minusDewasa = view.findViewById(R.id.minusDewasa);
        plusDewasa = view.findViewById(R.id.plusDewasa);
        minusAnak = view.findViewById(R.id.minusAnak);
        plusAnak = view.findViewById(R.id.plusAnak);
        minusBalita = view.findViewById(R.id.minusBalita);
        plusBalita = view.findViewById(R.id.plusBalita);
        radioGroup = view.findViewById(R.id.radioGroup);
        radioButton1 = view.findViewById(R.id.radioButton1);
        radioButton2 = view.findViewById(R.id.radioButton2);
        radioButton3 = view.findViewById(R.id.radioButton3);
        radioButton4 = view.findViewById(R.id.radioButton4);
        actionButton = view.findViewById(R.id.actionButton_cari);


        Bundle bundle = this.getArguments();
        String kelas_str = bundle.get("kelas").toString();
        int dewasa = bundle.getInt("dewasa");
        int balita = bundle.getInt("balita");
        int anak = bundle.getInt("anak");

//        Log.i("KELAS FRAG", "hello yolo"+ kelas_str);
        Log.i("DEWASA FRAG", dewasa+"");


        if (dewasa > 0){
            dewasaTextView.setText(""+dewasa);
        }
        if (anak > 0){
            anak2TextView.setText(""+anak);
        }
        if (balita > 0){
            balitaTextview.setText(""+balita);
        }


        //BOTTOM SHEET BEHAVIOR
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (BottomSheetBehavior.STATE_EXPANDED == newState) {
//                    showView(appBarLayout, getActionBarSize());
                    hideView(view.findViewById(R.id.linearLayout));
                }

                if (BottomSheetBehavior.STATE_COLLAPSED == newState)   {
//                    hideView(appBarLayout);
                    showView(view.findViewById(R.id.linearLayout), getActionBarSize());
                }

                if (BottomSheetBehavior.STATE_HIDDEN == newState) {
                    dismiss();
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        view.findViewById(R.id.closeSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        //DEWASA COUNT
        plusDewasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dewasaTextView.setText(Integer.parseInt(dewasaTextView.getText().toString()) + 1 +"");
            }
        });
        minusDewasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(dewasaTextView.getText().toString())!= 1) {
                    dewasaTextView.setText(Integer.parseInt(dewasaTextView.getText().toString()) - 1 + "");
                }
            }
        });

        //ANAK-ANAK COUNT
        plusAnak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anak2TextView.setText(Integer.parseInt(anak2TextView.getText().toString()) + 1 +"");
            }
        });
        minusAnak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(anak2TextView.getText().toString())!= 0) {
                    anak2TextView.setText(Integer.parseInt(anak2TextView.getText().toString()) - 1 + "");
                }

            }
        });

        //BALITA COUNT
        plusBalita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                balitaTextview.setText(Integer.parseInt(balitaTextview.getText().toString()) + 1 +"");

            }
        });
        minusBalita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(balitaTextview.getText().toString())!= 0) {
                    balitaTextview.setText(Integer.parseInt(balitaTextview.getText().toString()) - 1 + "");
                }

            }
        });


        final String[] kelas = {""};

        //Default is ekonomi
        if (kelas_str.matches("")){
            kelas[0] = "Ekonomi";
            radioButton1.setBackground(getResources().getDrawable(R.drawable.curved__even_less_fillcolorized_bg));
        } else {
            kelas[0] = kelas_str;
        }
        if (kelas_str.matches("Ekonomi")){
            radioButton1.setBackground(getResources().getDrawable(R.drawable.curved__even_less_fillcolorized_bg));
        }
        if (kelas_str.matches("Bisnis")){
            radioButton3.setBackground(getResources().getDrawable(R.drawable.curved__even_less_fillcolorized_bg));
        }
        if (kelas_str.matches("Premium Ekonomi")){
            radioButton3.setBackground(getResources().getDrawable(R.drawable.curved__even_less_fillcolorized_bg));
        }
        if (kelas_str.matches("First Class")){
            radioButton4.setBackground(getResources().getDrawable(R.drawable.curved__even_less_fillcolorized_bg));
        }

        radioButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetDialog();
                radioButton4.setBackground(getResources().getDrawable(R.drawable.curved__even_less_fillcolorized_bg));
                kelas[0] = radioButton4.getText().toString();
            }
        });
        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetDialog();
                radioButton3.setBackground(getResources().getDrawable(R.drawable.curved__even_less_fillcolorized_bg));
                kelas[0] = radioButton3.getText().toString();
            }
        });
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetDialog();
                radioButton2.setBackground(getResources().getDrawable(R.drawable.curved__even_less_fillcolorized_bg));
                kelas[0] = radioButton2.getText().toString();
            }
        });
        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetDialog();
                radioButton1.setBackground(getResources().getDrawable(R.drawable.curved__even_less_fillcolorized_bg));
                kelas[0] = radioButton1.getText().toString();

            }
        });

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    int dewasa = Integer.parseInt(dewasaTextView.getText().toString());
                    int anak = Integer.parseInt(anak2TextView.getText().toString());
                    int balita = Integer.parseInt(balitaTextview.getText().toString());

                    if (dewasa + anak + balita <= 9) {
                        datapasser.onDataPass(dewasa, anak, balita, kelas[0]);
                        dismiss();
                    } else {
                        Toast.makeText(getContext(), "Untuk pemesanan lebih dari 9 penumpang, silakan hubungi Contact Person Pacto", Toast.LENGTH_LONG).show();
                    }

            }
        });






    return dialog;

    }

    public interface OnDataPassenger {
         void onDataPass(int dewasa, int anak, int balita, String kelas);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        datapasser = (OnDataPassenger) context;
    }



    public void resetDialog(){
        radioButton1.setBackground(getResources().getDrawable(R.drawable.curved__even_less_colorized_bg));
        radioButton2.setBackground(getResources().getDrawable(R.drawable.curved__even_less_colorized_bg));
        radioButton3.setBackground(getResources().getDrawable(R.drawable.curved__even_less_colorized_bg));
        radioButton4.setBackground(getResources().getDrawable(R.drawable.curved__even_less_colorized_bg));

    }



    @Override
    public int getTheme() {
        return R.style.CustomBottomSheetDialog;
    }

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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;





//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        binding = FragmentPenumpangBottomSheetBinding.inflate(())
////        return inflater.inflate(R.layout.fragment_penumpang_bottom_sheet, container, false);
//    }
}