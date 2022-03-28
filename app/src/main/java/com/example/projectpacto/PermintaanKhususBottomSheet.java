package com.example.projectpacto;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class PermintaanKhususBottomSheet extends BottomSheetDialogFragment {

    int bebasAsapRokok;
    int kamarTersambung;
    String waktuCekin_req;
    String waktuCekOut_req;
    String tipeRanjang_req;
    String catatanLainnya_req;

    CheckBox bebasAsapRokok_cekBox;
    CheckBox kamarTersammbung_cekBox;
    TextInputLayout waktuCekin;
    TextInputLayout waktuCekOut;
    TextInputLayout catatanLainnya;
    AutoCompleteTextView tipeRanjang_autoComplete;

    ArrayList<String> tipeRanjang;

    OnDataSpecialRequest datapasser;



   public  PermintaanKhususBottomSheet(){

   }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_permintaan_khusus_bottom_sheet, null);
        dialog.setContentView(view);
        Bundle bundle = this.getArguments();

        bebasAsapRokok = bundle.getInt("bebasAsapRokok");
        kamarTersambung = bundle.getInt("kamarTersambung");
        waktuCekin_req = bundle.getString("waktuCekin_req");
        waktuCekOut_req = bundle.getString("waktuCekOut_req");
        tipeRanjang_req = bundle.getString("tipeRanjang_req");
        catatanLainnya_req = bundle.getString("catatanLainnya_req");




        bebasAsapRokok_cekBox = view.findViewById(R.id.bebasAsapRokok_cekBox);
        kamarTersammbung_cekBox = view.findViewById(R.id.kamarTersambung_cekBox);


        //HANDLING CHECK BOXES
        if (bebasAsapRokok == 1){
            bebasAsapRokok_cekBox.setChecked(true);
        }
        if (kamarTersambung == 1 ){
            kamarTersammbung_cekBox.setChecked(true);
        }


        bebasAsapRokok_cekBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                bebasAsapRokok = 0;
                if (b==true) {
                    bebasAsapRokok = 1;
                }
            }
        });

        kamarTersammbung_cekBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                kamarTersambung = 0;
                if (b==true) {
                    kamarTersambung = 1;
                }
            }
        });


        //HANDLING THE TEXT VIEWS
        waktuCekin = view.findViewById(R.id.waktuCekInTextInput);
        waktuCekOut = view.findViewById(R.id.waktuCekOutTextInput);
        catatanLainnya = view.findViewById(R.id.catatanLainnyaTextInput);

        waktuCekin.getEditText().setText(waktuCekin_req);
        waktuCekOut.getEditText().setText(waktuCekOut_req);
        catatanLainnya.getEditText().setText(catatanLainnya_req);



        //Handling the Drop Down
        tipeRanjang_autoComplete = view.findViewById(R.id.tipeRanjangDropdown);
        tipeRanjang_autoComplete.setInputType(TextView.AUTO_SIZE_TEXT_TYPE_NONE);
        tipeRanjang_autoComplete.setText(tipeRanjang_req);
        tipeRanjang = new ArrayList<>();
        tipeRanjang.add("Single"); tipeRanjang.add("Double"); tipeRanjang.add("Queen"); tipeRanjang.add("King");
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, tipeRanjang);
        tipeRanjang_autoComplete.setAdapter(arrayAdapter1);


        //Handling the Action Button
        view.findViewById(R.id.actionButton_cari).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                waktuCekin_req = waktuCekin.getEditText().getText().toString();
                waktuCekOut_req = waktuCekOut.getEditText().getText().toString();
                tipeRanjang_req = tipeRanjang_autoComplete.getText().toString();
                catatanLainnya_req = catatanLainnya.getEditText().getText().toString();
                datapasser.OnDataSpecialRequest(bebasAsapRokok, kamarTersambung, waktuCekin_req, waktuCekOut_req, tipeRanjang_req, catatanLainnya_req);
                dismiss();
            }
        });

        view.findViewById(R.id.closeSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



        return dialog;
    }

    public interface OnDataSpecialRequest {
        void OnDataSpecialRequest(int bebasAsapRokok, int kamarTersambung, String waktuCekin, String waktuCekout, String tipeRanjang, String CatatanLainnya);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        datapasser = (OnDataSpecialRequest) context;
    }


}