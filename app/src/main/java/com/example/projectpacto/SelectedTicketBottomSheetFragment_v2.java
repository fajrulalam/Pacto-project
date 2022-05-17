package com.example.projectpacto;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;


public class SelectedTicketBottomSheetFragment_v2 extends BottomSheetDialogFragment {

    TextView tanggalBerangkat;
    TextView waktuBerangkat;
    TextView kotaTujuan;
    TextView kotaAsal;
    TextView bandaraAsal;
    ImageView logoMaskapai;
    TextView kodePenerbangan;
    TextView namaMaskapai;
    TextView kabin;
    TextView bagasi;
    TextView keteranganKabin;
    TextView makanAtauTidak;
    TextView keteranganMakanan;
    TextView modelPesawat;
    TextView kelasPesawat;
    TextView tanggalDatang;
    TextView waktuDatang;
    TextView bandaraTujuan;
    TextView jmlPenumpangDewasa;
    TextView subtotalDewasa;
    LinearLayout penumpangAnakLinearLayout;
    TextView jmlPenumpangAnak;
    TextView subtotalAnak;
    LinearLayout penumpangBalitaLinearLayout;
    TextView jmlPenumpangBalita;
    TextView subtotalBalita;
    LinearLayout pajakLinearLayout;
    TextView pajakTextView;
    TextView subtotalPajak;
    TextView total;
    TextView total_bottomcorner;
    Button tampilkanButton;

    ArrayList<String> tanggalBerangkat_ArrayList;
    ArrayList<String> waktuBerangkat_ArrayList;
    ArrayList<String> bandaraAsal_ArrayList;
    ArrayList<Integer> logoMaskapai_ArrayList;
    ArrayList<String> namaMaskapai_ArrayList;
    ArrayList<String> kelasPesawat_ArrayList;
    ArrayList<String> tanggalDatang_ArrayList;
    ArrayList<String> waktuDatang_ArrayList;
    ArrayList<String> bandaraTujuan_ArrayList;
     ArrayList<String> kabin_ArrayList;
    ArrayList<String> bagasi_ArrayList;
    ArrayList<Integer> booleanMakan_ArrayList;
    ArrayList<String> keteranganMakan_ArrayList;
    ArrayList<String> modelPesawat_ArrayList;
    ArrayList<String> durasi_ArrayList;

    String jmlDewasa_str;
    String jmlAnak_str;
    String jmlBalita_str;
    String kotaTujuan_str;
    String kotaAsal_str;
    String harga_str;

    String keberangkatan;
    String kedatangan;
    String tanggal;
    String penumpang;
    String kota_kedatangan;
    String kota_keberangkatan;
    String bandara_keberangktan_raw;
    String bandara_kedatangan_raw;





    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SelectedTicketBottomSheetFragment_v2() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_selected_ticket_bottom_sheet, null);
        dialog.setContentView(view);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setPeekHeight(3000);

        tampilkanButton = view.findViewById(R.id.tampilkanButton);



        kotaAsal = view.findViewById(R.id.kotaAsal);
        kotaTujuan = view.findViewById(R.id.kotaTujuan);
        pajakLinearLayout =  view.findViewById(R.id.pajakDetail);
        penumpangAnakLinearLayout = view.findViewById(R.id.penumpangAnakDetail);
        penumpangBalitaLinearLayout =view.findViewById(R.id.penumpangBalitaDetail);
        tanggalBerangkat = view.findViewById(R.id.tanggalBerangkat);
        waktuBerangkat = view.findViewById(R.id.waktuBerangkat);
        bandaraAsal=view.findViewById(R.id.bandaraAsal);
        logoMaskapai = view.findViewById(R.id.logoMaskapai);
        kodePenerbangan = view.findViewById(R.id.kodePenerbangan);
        namaMaskapai = view.findViewById(R.id.namaMaskapai);
        kabin = view.findViewById(R.id.kabin);
        bagasi = view.findViewById(R.id.bagasi);
        keteranganKabin = view.findViewById(R.id.keteranganKabin);
        makanAtauTidak = view.findViewById(R.id.makanAtauTidak);
        keteranganMakanan = view.findViewById(R.id.keteranganMakanan);
        modelPesawat = view.findViewById(R.id.modelPesawat);
        kelasPesawat = view.findViewById(R.id.kelasPesawat);
        tanggalDatang = view.findViewById(R.id.tanggalDatang);
        waktuDatang = view.findViewById(R.id.waktuDatang);
        bandaraTujuan = view.findViewById(R.id.bandaraTujuan);
        jmlPenumpangDewasa = view.findViewById(R.id.jumlahPenumpangDewasa);
        subtotalDewasa = view.findViewById(R.id.subtotalPenumpangDewasa);
        jmlPenumpangAnak = view.findViewById(R.id.jumlahPenumpangAnak);
        subtotalAnak = view.findViewById(R.id.subtotalPenumpangAnak);
        jmlPenumpangBalita =  view.findViewById(R.id.jumlahPenumpangBalita);
        subtotalBalita = view.findViewById(R.id.subtotalPenumpangBalita);
        pajakTextView = view.findViewById(R.id.pajakTextView);
        subtotalPajak = view.findViewById(R.id.pajakSubtotal);
        total = view.findViewById(R.id.total);
        total_bottomcorner = view.findViewById(R.id.total_bottomCorner);

        Bundle bundle = this.getArguments();
        harga_str =bundle.getString("harga");
        kotaAsal_str = bundle.getString("kotaAsal");
        kotaTujuan_str = bundle.getString("kotaTujuan");
        tanggalBerangkat_ArrayList = bundle.getStringArrayList("tanggalBerangkat");
        waktuBerangkat_ArrayList = bundle.getStringArrayList("waktuBerangkat");
        Log.i("SELECTED TICKET BS", waktuBerangkat_ArrayList + "");
        bandaraAsal_ArrayList = bundle.getStringArrayList("bandaraAsal");
        logoMaskapai_ArrayList =bundle.getIntegerArrayList("logoMaskapai");
        namaMaskapai_ArrayList= bundle.getStringArrayList("namaMaskapai");;
        kabin_ArrayList = bundle.getStringArrayList("kabin");;
        bagasi_ArrayList = bundle.getStringArrayList("bagasi");;
        booleanMakan_ArrayList = bundle.getIntegerArrayList("booleanMakan");;
        Log.i("SELECTED TICKET MAKAN", booleanMakan_ArrayList + "makan");
        keteranganMakan_ArrayList = bundle.getStringArrayList("keteranganMakan");
        modelPesawat_ArrayList = bundle.getStringArrayList("modelPesawat");
        kelasPesawat_ArrayList= bundle.getStringArrayList("kelasPesawat");;
        tanggalDatang_ArrayList= bundle.getStringArrayList("tanggalDatang");;
        waktuDatang_ArrayList= bundle.getStringArrayList("waktuDatang");;
        bandaraTujuan_ArrayList= bundle.getStringArrayList("bandaraTujuan");
        durasi_ArrayList = bundle.getStringArrayList("durasi");
        jmlDewasa_str= bundle.getString("jmlDewasa");;
        jmlAnak_str= bundle.getString("jmlAnak"); ;
        jmlBalita_str= bundle.getString("jmlBalita");;

        keberangkatan = bundle.getString("keberangkatan");
        kedatangan = bundle.getString("kedatangan");
        tanggal = bundle.getString("tanggal");
        penumpang = bundle.getString("penumpang");
        kota_keberangkatan = bundle.getString("kota_keberangkatan");
        kota_kedatangan = bundle.getString("kota_kedatangan");
        bandara_keberangktan_raw = bundle.getString("bandara_keberangkatan");
        bandara_kedatangan_raw =  bundle.getString("bandara_kedatangan");



        total.setText(harga_str);
        total_bottomcorner.setText(harga_str);
        kotaAsal.setText(kotaAsal_str);
        kotaTujuan.setText(kotaTujuan_str);
//        tanggalBerangkat.setText(tanggalBerangkat_str);
//        waktuBerangkat.setText(waktuBerangkat_str);
//        bandaraAsal.setText(bandaraAsal_str);
//        logoMaskapai.setImageResource(logoMaskapai_int);
//        namaMaskapai.setText(namaMaskapai_str);
//        kelasPesawat.setText(kelasPesawat_str);
//        tanggalDatang.setText(tanggalDatang_str);
//        waktuDatang.setText(waktuDatang_str);
//        bandaraTujuan.setText(bandaraTujuan_str);
        jmlPenumpangDewasa.setText("Dewasa (x"+jmlDewasa_str +")");


        if (!jmlAnak_str.matches("0")){
            jmlPenumpangAnak.setText("Anak-Anak (x"+jmlAnak_str+")");
            penumpangAnakLinearLayout.setVisibility(View.VISIBLE);
        }


        if (!jmlBalita_str.matches("0")){
            jmlPenumpangBalita.setText("Balita (x"+jmlBalita_str+")");
            penumpangBalitaLinearLayout.setVisibility(View.VISIBLE);
        }

        view.findViewById(R.id.closeSheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        tampilkanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(getActivity(), PlaneOrderActivity3.class);
                intent.putExtra("bundle", bundle);

                startActivity(intent);



            }
        });


        return dialog;
    }

    public class RecycleAdapterHotelOptions extends RecyclerView.Adapter<ViewHolder>{
        private ArrayList<String> bandaraAsal_ArrayList ;
        private ArrayList<Integer> logoMaskapai_ArrayList ;
        private ArrayList<String> namaMaskapai_ArrayList;
        private ArrayList<String> kabin_ArrayList;
        private ArrayList<String> bagasi_ArrayList;
        private ArrayList<String> booleanMakan_ArrayList;
        private ArrayList<String> keteranganMakan_ArrayList;
        private ArrayList<String> modelPesawat_ArrayList ;
        private ArrayList<String> kelasPesawat_ArrayList;
        private ArrayList<String> tanggalDatang_ArrayList;
        private ArrayList<String> waktuDatang_ArrayList;
        private ArrayList<String> bandaraTujuan_ArrayList;

        public RecycleAdapterHotelOptions(ArrayList<String> bandaraAsal_ArrayList, ArrayList<Integer> logoMaskapai_ArrayList, ArrayList<String> namaMaskapai_ArrayList, ArrayList<String> kabin_ArrayList, ArrayList<String> bagasi_ArrayList, ArrayList<String> booleanMakan_ArrayList, ArrayList<String> keteranganMakan_ArrayList, ArrayList<String> modelPesawat_ArrayList, ArrayList<String> kelasPesawat_ArrayList, ArrayList<String> tanggalDatang_ArrayList, ArrayList<String> waktuDatang_ArrayList, ArrayList<String> bandaraTujuan_ArrayList) {
            this.bandaraAsal_ArrayList = bandaraAsal_ArrayList;
            this.logoMaskapai_ArrayList = logoMaskapai_ArrayList;
            this.namaMaskapai_ArrayList = namaMaskapai_ArrayList;
            this.kabin_ArrayList = kabin_ArrayList;
            this.bagasi_ArrayList = bagasi_ArrayList;
            this.booleanMakan_ArrayList = booleanMakan_ArrayList;
            this.keteranganMakan_ArrayList = keteranganMakan_ArrayList;
            this.modelPesawat_ArrayList = modelPesawat_ArrayList;
            this.kelasPesawat_ArrayList = kelasPesawat_ArrayList;
            this.tanggalDatang_ArrayList = tanggalDatang_ArrayList;
            this.waktuDatang_ArrayList = waktuDatang_ArrayList;
            this.bandaraTujuan_ArrayList = bandaraTujuan_ArrayList;
        }

        @NonNull
        @Override
        public com.example.projectpacto.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.hotel_options_single_view, parent, false);
            com.example.projectpacto.ViewHolder viewHolder = new com.example.projectpacto.ViewHolder(view);
            return  viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.projectpacto.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return bandaraAsal_ArrayList.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder{


            public ViewHolder(@NonNull View itemView) {
                super(itemView);

            }


        }
    }
}