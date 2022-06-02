package com.example.projectpacto;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectpacto.Hotel_KotaAtauAkomodasi;
import com.example.projectpacto.ItemClickSupport;
import com.example.projectpacto.ItemClickSupport2;
import com.example.projectpacto.PenumpangBottomSheet;
import com.example.projectpacto.R;
import com.example.projectpacto.RecyclerAdapterOpsiBagasi_Pergi;
import com.example.projectpacto.RecyclerItemClickListener;
import com.example.projectpacto.TambahanBagasiBottomSheet;
import com.example.projectpacto.databinding.FragmentBagasiBottomsheetRedesignBinding;
import com.example.projectpacto.databinding.FragmentBagasiRedesignPergiBinding;
import com.example.projectpacto.roundtrip.RecyclerAdapterOpsiBagasi_Pulang;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;


public class BagasiRedesign_Pergi extends BottomSheetDialogFragment implements RecyclerAdapterOpsiBagasi_Pergi.OnDataBagasi, RecyclerAdapterOpsiBagasi_Pulang.OnDataBagasi_pulang {

    FragmentBagasiRedesignPergiBinding holder;
    OnDataBagasiBottomSheetRedesign datapasser;
    RecyclerView recyclerViewTambahanBagasi;
    RecyclerView recyclerViewTambahanBagasiOpsi_Pergi;
    RecyclerView recyclerViewTambahanBagasiOpsi_Pulang;

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        datapasser.OnDataBagasiBottomSheetRedesign(tambahan_kg, harga_tambahan, selected_position_opsibagasi,
                tambahan_kg_pulang, harga_tambahan_pulang, selected_position_opsibagasi_pulang);

    }

    ArrayList<String> namaPassenger;
    ArrayList<String> tambahan_kg;
    String bagasi;
    ArrayList<String> harga_tambahan;
    ArrayList<String> tambahan_kg_pulang;
    ArrayList<String> harga_tambahan_pulang;
    int position_int; //penumpang

    ArrayList<Integer> selected_position_opsibagasi;
    ArrayList<Integer> selected_position_opsibagasi_pulang;
    ArrayList<String> tambahan_kg_option;
    ArrayList<String> hargaTambahan_kg_option;


    public BagasiRedesign_Pergi() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        holder = FragmentBagasiRedesignPergiBinding.inflate(inflater, container, false);
        View view = holder.getRoot();

        namaPassenger = new ArrayList<>();
        selected_position_opsibagasi = new ArrayList<>();
        selected_position_opsibagasi_pulang = new ArrayList<>();
        tambahan_kg = new ArrayList<>();
        harga_tambahan = new ArrayList<>();
        tambahan_kg_pulang = new ArrayList<>();
        harga_tambahan_pulang = new ArrayList<>();
        tambahan_kg_option = new ArrayList<>();
        hargaTambahan_kg_option = new ArrayList<>();


        Bundle bundle = this.getArguments();

        namaPassenger = bundle.getStringArrayList("namaList");
        bagasi = bundle.getString("bagasi_default");
        selected_position_opsibagasi = bundle.getIntegerArrayList("selected_position_opsi_bagasi");
        selected_position_opsibagasi_pulang = bundle.getIntegerArrayList("selected_position_opsi_bagasi_pulang");
        tambahan_kg = bundle.getStringArrayList("tambahan_kg");
        harga_tambahan = bundle.getStringArrayList("harga_tambahan");
        tambahan_kg_pulang = bundle.getStringArrayList("tambahan_kg_pulang");
        harga_tambahan_pulang = bundle.getStringArrayList("harga_tambahan_pulang");




        //Nanti ngambil dari API
        tambahan_kg_option.add("0kg");
        hargaTambahan_kg_option.add("IDR 0");
        tambahan_kg_option.add("5kg");
        hargaTambahan_kg_option.add("IDR 115.000");
        tambahan_kg_option.add("10kg");
        hargaTambahan_kg_option.add("IDR 215.000");
        tambahan_kg_option.add("15kg");
        hargaTambahan_kg_option.add("IDR 315.000");
        tambahan_kg_option.add("20kg");
        hargaTambahan_kg_option.add("IDR 515.000");
        tambahan_kg_option.add("25kg");
        hargaTambahan_kg_option.add("IDR 615.000");




        recyclerViewTambahanBagasi = view.findViewById(R.id.RecyclerViewTambahanBagasi);
        recyclerViewTambahanBagasiOpsi_Pergi = view.findViewById(R.id.RecyclerViewOpsiBagasi_Pergi);
        recyclerViewTambahanBagasiOpsi_Pulang = view.findViewById(R.id.RecyclerViewOpsiBagasi_Pulang);


        position_int = 0;


        RecyclerAdapterBagasiHorizontal recyclerAdapterBagasiHorizontal = new RecyclerAdapterBagasiHorizontal(position_int, bagasi, namaPassenger, tambahan_kg, harga_tambahan, tambahan_kg_pulang, harga_tambahan_pulang);
        holder.RecyclerViewTambahanBagasi.setAdapter(recyclerAdapterBagasiHorizontal);

        RecyclerAdapterOpsiBagasi_Pergi recyclerAdapterOpsiBagasi_pergi = new RecyclerAdapterOpsiBagasi_Pergi(this, position_int, selected_position_opsibagasi, tambahan_kg_option, hargaTambahan_kg_option);
        holder.RecyclerViewOpsiBagasiPergi.setAdapter(recyclerAdapterOpsiBagasi_pergi);

        RecyclerAdapterOpsiBagasi_Pulang recyclerAdapterOpsiBagasi_pulang = new RecyclerAdapterOpsiBagasi_Pulang(this, position_int, selected_position_opsibagasi_pulang, tambahan_kg_option, hargaTambahan_kg_option);


        ItemClickSupport2.addTo(holder.RecyclerViewTambahanBagasi).setOnItemClickListener(new ItemClickSupport2.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                position_int = position;
                int scrollStateX = recyclerView.computeHorizontalScrollOffset();
                int scrollStateY = recyclerView.computeVerticalScrollOffset();
                int scrollStateY_pergi = holder.RecyclerViewOpsiBagasiPergi.computeVerticalScrollOffset();
                int scrollStateX_pergi = holder.RecyclerViewOpsiBagasiPergi.computeHorizontalScrollOffset();
                RecyclerAdapterBagasiHorizontal recyclerAdapterBagasiHorizontal = new RecyclerAdapterBagasiHorizontal(position_int, bagasi, namaPassenger, tambahan_kg, harga_tambahan, tambahan_kg_pulang, harga_tambahan_pulang);
                holder.RecyclerViewTambahanBagasi.setAdapter(recyclerAdapterBagasiHorizontal);

                RecyclerAdapterOpsiBagasi_Pergi recyclerAdapterOpsiBagasi_pergi = new RecyclerAdapterOpsiBagasi_Pergi(BagasiRedesign_Pergi.this, position_int, selected_position_opsibagasi, tambahan_kg_option, hargaTambahan_kg_option);
                holder.RecyclerViewOpsiBagasiPergi.setAdapter(recyclerAdapterOpsiBagasi_pergi);

                RecyclerAdapterOpsiBagasi_Pulang recyclerAdapterOpsiBagasi_pulang = new RecyclerAdapterOpsiBagasi_Pulang(BagasiRedesign_Pergi.this, position_int, selected_position_opsibagasi_pulang, tambahan_kg_option, hargaTambahan_kg_option);

                holder.RecyclerViewOpsiBagasiPergi.scrollBy(scrollStateX_pergi, scrollStateY_pergi);
//
//                holder.RecyclerViewOpsiBagasiPergi.scrollToPosition(selected_position_opsibagasi.get(position));
//                holder.RecyclerViewOpsiBagasiPulang.scrollToPosition(selected_position_opsibagasi_pulang.get(position));

//                recyclerView.scrollTo(scrollStateX, scrollStateY);
                recyclerView.scrollBy(scrollStateX, scrollStateY);
                Log.i("SCROLL TO POS", "it executed the message");
                Log.i("Change position", "posisi " + selected_position_opsibagasi);

//                recyclerView.setBackground(getResources().getDrawable(R.drawable.curved__even_less_colorized_bg));
//                v.setBackground(getResources().getDrawable(R.drawable.curved__even_less_colorized_bg));
            }
        });



        //CLOSE BOTTOM SHEET
        holder.closeSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datapasser.OnDataBagasiBottomSheetRedesign(tambahan_kg, harga_tambahan, selected_position_opsibagasi,
                        tambahan_kg_pulang, harga_tambahan_pulang, selected_position_opsibagasi_pulang);
                dismiss();
            }
        });

        //ACTION BUTTON
        holder.actionButtonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datapasser.OnDataBagasiBottomSheetRedesign(tambahan_kg, harga_tambahan, selected_position_opsibagasi,
                        tambahan_kg_pulang, harga_tambahan_pulang, selected_position_opsibagasi_pulang);
                dismiss();
            }
        });





        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        final View view = View.inflate(getContext(), R.layout.fragment_bagasi_redesign__pergi, null);
        dialog.setContentView(view);

        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setPeekHeight(3000);

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                datapasser.OnDataBagasiBottomSheetRedesign(tambahan_kg, harga_tambahan, selected_position_opsibagasi,
                        tambahan_kg_pulang, harga_tambahan_pulang, selected_position_opsibagasi_pulang);
            }
        });
        //BOTTOM SHEET BEHAVIOR
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                datapasser.OnDataBagasiBottomSheetRedesign(tambahan_kg, harga_tambahan, selected_position_opsibagasi,
                        tambahan_kg_pulang, harga_tambahan_pulang, selected_position_opsibagasi_pulang);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//                datapasser.OnDataBagasiBottomSheetRedesign(tambahan_kg, harga_tambahan, selected_position_opsibagasi,
//                        tambahan_kg_pulang, harga_tambahan_pulang, selected_position_opsibagasi_pulang);
            }
        });


        recyclerViewTambahanBagasi = view.findViewById(R.id.RecyclerViewTambahanBagasi);
        recyclerViewTambahanBagasiOpsi_Pergi = view.findViewById(R.id.RecyclerViewOpsiBagasi_Pergi);
        recyclerViewTambahanBagasiOpsi_Pulang = view.findViewById(R.id.RecyclerViewOpsiBagasi_Pulang);



        return dialog;
    }

    @Override
    public void OnDataBagasi(int bagasiOptionPosition_int) {
        int pcc = bagasiOptionPosition_int;
        Toast.makeText(getContext(), "pcc " + pcc, Toast.LENGTH_SHORT).show();
        Log.i("Change position", "posisi " + selected_position_opsibagasi);
        selected_position_opsibagasi.set(position_int, pcc);
        tambahan_kg.set(position_int, tambahan_kg_option.get(pcc));
        harga_tambahan.set(position_int, hargaTambahan_kg_option.get(pcc));
        int scrollStateX = recyclerViewTambahanBagasiOpsi_Pergi.computeHorizontalScrollOffset();
        int scrollStateY = recyclerViewTambahanBagasiOpsi_Pergi.computeVerticalScrollOffset();
        int scrollStateX_penumpang = holder.RecyclerViewTambahanBagasi.computeHorizontalScrollOffset();
        int scrollStateY_penumpang = holder.RecyclerViewTambahanBagasi.computeVerticalScrollOffset();

        Log.i("tambahankg", "kg " + tambahan_kg);
        Log.i("tambahankg harga", "harga " + tambahan_kg);
        RecyclerAdapterOpsiBagasi_Pergi recyclerAdapterOpsiBagasi_pergi = new RecyclerAdapterOpsiBagasi_Pergi(BagasiRedesign_Pergi.this, position_int, selected_position_opsibagasi, tambahan_kg_option, hargaTambahan_kg_option);
        recyclerViewTambahanBagasiOpsi_Pergi.setAdapter(recyclerAdapterOpsiBagasi_pergi);
        RecyclerAdapterBagasiHorizontal recyclerAdapterBagasiHorizontal = new RecyclerAdapterBagasiHorizontal(position_int, bagasi, namaPassenger, tambahan_kg, harga_tambahan, tambahan_kg_pulang, harga_tambahan_pulang);
        recyclerViewTambahanBagasi.setAdapter(recyclerAdapterBagasiHorizontal);


        recyclerViewTambahanBagasiOpsi_Pergi.scrollBy(scrollStateX, scrollStateY);
        recyclerViewTambahanBagasi.scrollBy(scrollStateX_penumpang, scrollStateY_penumpang);
    }

    @Override
    public void OnDataBagasi_pulang(int bagasiOptionPosition_int) {
        int pcc = bagasiOptionPosition_int;
        Toast.makeText(getContext(), "pcc " + pcc, Toast.LENGTH_SHORT).show();
        Log.i("Change position", "posisi " + selected_position_opsibagasi_pulang);
        selected_position_opsibagasi_pulang.set(position_int, pcc);
        tambahan_kg_pulang.set(position_int, tambahan_kg_option.get(pcc));
        harga_tambahan_pulang.set(position_int, hargaTambahan_kg_option.get(pcc));
        int scrollStateX = recyclerViewTambahanBagasiOpsi_Pulang.computeHorizontalScrollOffset();
        int scrollStateY = recyclerViewTambahanBagasiOpsi_Pulang.computeVerticalScrollOffset();
        int scrollStateX_penumpang = holder.RecyclerViewTambahanBagasi.computeHorizontalScrollOffset();
        int scrollStateY_penumpang = holder.RecyclerViewTambahanBagasi.computeVerticalScrollOffset();

        Log.i("tambahankg_pulang", "kg pulang" + tambahan_kg_pulang);
        Log.i("tambahankg harga pulang", "harga pulang " + harga_tambahan_pulang);
        RecyclerAdapterOpsiBagasi_Pulang recyclerAdapterOpsiBagasi_pulang = new RecyclerAdapterOpsiBagasi_Pulang(this, position_int, selected_position_opsibagasi_pulang, tambahan_kg_option, hargaTambahan_kg_option);
        RecyclerAdapterBagasiHorizontal recyclerAdapterBagasiHorizontal = new RecyclerAdapterBagasiHorizontal(position_int, bagasi, namaPassenger, tambahan_kg, harga_tambahan, tambahan_kg_pulang, harga_tambahan_pulang);
        recyclerViewTambahanBagasi.setAdapter(recyclerAdapterBagasiHorizontal);


        recyclerViewTambahanBagasiOpsi_Pulang.scrollBy(scrollStateX, scrollStateY);
        recyclerViewTambahanBagasi.scrollBy(scrollStateX_penumpang, scrollStateY_penumpang);
    }


    public static class RecyclerAdapterBagasiHorizontal extends RecyclerView.Adapter<RecyclerAdapterBagasiHorizontal.ViewHolder> {
        ArrayList<String> namaPassenger;
        ArrayList<String> tambahan_kg;
        ArrayList<String> harga_tambahan;
        ArrayList<String> tambahan_kg_pulang;
        ArrayList<String> harga_tambahan_pulang;
        String bagasi_default;
        int position_int;

        public RecyclerAdapterBagasiHorizontal(int position_int, String bagasi_default, ArrayList<String> namaPassenger, ArrayList<String> tambahan_kg, ArrayList<String> harga_tambahan, ArrayList<String> tambahan_kg_pulang, ArrayList<String> harga_tambahan_pulang) {
            this.namaPassenger = namaPassenger;
            this.tambahan_kg = tambahan_kg;
            this.harga_tambahan = harga_tambahan;
            this.tambahan_kg_pulang = tambahan_kg_pulang;
            this.harga_tambahan_pulang = harga_tambahan_pulang;
            this.bagasi_default = bagasi_default;
            this.position_int = position_int;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.bagasi_penumpang_horizontal_pergi_single_view, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            holder.nomordanNamaPassenger.setText(position + 1 + ". " + namaPassenger.get(position));
            holder.defaultbagasiPergi.setText(bagasi_default);
            holder.defaultbagasiPulang.setText(bagasi_default);

            holder.plus.setVisibility(View.GONE);
            holder.plus1.setVisibility(View.GONE);
            holder.tambahanbagasiPulang.setVisibility(View.GONE);
            holder.tambahanbagasiPergi.setVisibility(View.GONE);

            if (position == position_int) {
                holder.linearLayout.setBackground(holder.itemView.getResources().getDrawable(R.drawable.curved__even_less_fillcolorized_bg));
                holder.linearLayout.setPadding(42, 32, 22, 32);
            } else {
                holder.linearLayout.setBackground(holder.itemView.getResources().getDrawable(R.drawable.curved__even_less_colorized_bg));
                holder.linearLayout.setPadding(42, 32, 22, 32);

            }


            if (!tambahan_kg.get(position).matches("0kg")) {
                holder.plus.setVisibility(View.VISIBLE);
                holder.tambahanbagasiPergi.setVisibility(View.VISIBLE);

                holder.tambahanbagasiPergi.setText(tambahan_kg.get(position));
            }

            if (!tambahan_kg_pulang.get(position).matches("0kg")) {
                holder.plus1.setVisibility(View.VISIBLE);
                holder.tambahanbagasiPulang.setVisibility(View.VISIBLE);

                holder.tambahanbagasiPulang.setText(tambahan_kg_pulang.get(position));
            }

        }

        @Override
        public int getItemCount() {
            return namaPassenger.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView nomordanNamaPassenger;
            TextView defaultbagasiPergi;
            TextView defaultbagasiPulang;
            TextView tambahanbagasiPergi;
            TextView tambahanbagasiPulang;
            TextView plus;
            TextView plus1;
            LinearLayout linearLayout;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                linearLayout = itemView.findViewById(R.id.linearLayout);
                nomordanNamaPassenger = itemView.findViewById(R.id.nomordanNamaPassenger);
                defaultbagasiPergi = itemView.findViewById(R.id.defaultbagasiPergi);
                defaultbagasiPulang = itemView.findViewById(R.id.defaultbagasiPulang);
                tambahanbagasiPergi = itemView.findViewById(R.id.tambahanbagasi_pergi);
                tambahanbagasiPulang = itemView.findViewById(R.id.tambahanbagasi_pulang);

                plus = itemView.findViewById(R.id.plus);
                plus1 = itemView.findViewById(R.id.plus1);
            }


        }


    }

    public interface OnDataBagasiBottomSheetRedesign {
        void OnDataBagasiBottomSheetRedesign(ArrayList<String> mtambahan_kg,
                                             ArrayList<String> mharga_tambahan,
                                             ArrayList<Integer> mselectIntegered_position_opsi_bagasi,
                                             ArrayList<String> mtambahan_kg_pulang,
                                             ArrayList<String> mharga_tambahan_pulang,
                                             ArrayList<Integer> mselected_position_opsi_bagasi_pulang
        );


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        datapasser = (BagasiRedesign_Pergi.OnDataBagasiBottomSheetRedesign) context;
    }



}