package com.example.projectpacto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.projectpacto.databinding.ActivityHotelOrder4Binding;
import com.example.projectpacto.databinding.ActivityPlaneOrder3Binding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HotelOrderActivity4 extends AppCompatActivity implements RecyclerAdapterPenumpangList.AddPassengerDetail, DataTamu_BottomSheet.OnDataTamu {

    ActivityHotelOrder4Binding binding;

    int gambarKamar;
    String namaKamar;
    int kapasitasKamar;
    String tipeKasur;
    String sarapan;
    String hargaKamar;

    int gambarHotel;
    String namaHotel;
    String tambahanAlamat;
    String hargaMulaiHotel;
    int jmlBintang;

    String kotaAtauHotel;
    String jumlahKamar;
    String jumlahMalam;
    String tglCek_in;
    String tglCek_out;
    Date tglCek_in_date;
    Date tglCek_out_date;

    ArrayList<String> namaPassenger;
    ArrayList<String> titel;

    RecyclerAdapterPenumpangList recyclerAdapterPenumpangList;

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHotelOrder4Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        extras = getIntent().getBundleExtra("bundle");
        gambarKamar= extras.getInt("gambarKamar");
        namaKamar= extras.getString("namaKamar");
        kapasitasKamar= extras.getInt("kapasitasKamar");
        tipeKasur= extras.getString("tipeKasur");
        sarapan= extras.getString("sarapan");
        hargaKamar= extras.getString("hargaKamar");

        gambarHotel= extras.getInt("gambarHotel");
        namaHotel= extras.getString("namaHotel");
        tambahanAlamat= extras.getString("tambahanAlamat");
        hargaMulaiHotel= extras.getString("harga");
        jmlBintang= extras.getInt("jmlBintang");

        kotaAtauHotel= extras.getString("kotaAtauHotel");
        jumlahKamar= extras.getString("jumlahKamar");
        jumlahMalam= extras.getString("jumlahMalam");
        tglCek_in= extras.getString("tglCek_out");
        tglCek_out= extras.getString("tglCek_in");


        Locale lokal = new Locale("id", "ID");

//        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy", lokal);
//        try {
//            tglCek_in_date = formatter.parse(tglCek_in);
//            tglCek_out_date  = formatter.parse(tglCek_out);
//
//            formatter =  new SimpleDateFormat("dd MMM yyyy");
//            tglCek_in = formatter.format(tglCek_in_date);
//        } catch (ParseException parseException) {
//            parseException.printStackTrace();
//        }

        binding.namaHotelTxt.setText(namaHotel);
        binding.alamatTambahan.setText(tambahanAlamat);
        binding.tglCekIn.setText(tglCek_in);
        binding.tglCekOut.setText(tglCek_out);
        binding.detailTamu.setText(jumlahKamar.split(", ")[0]);
        binding.jmldanPilihanKamar.setText(jumlahKamar.split(", ")[1]+ " ("+namaKamar+")");

        namaPassenger = new ArrayList<>();
        titel = new ArrayList<>();



        Log.i("NAMAHOTEL", ""+namaHotel);
        Log.i("NAMAKAMAR", ""+namaKamar);
        Log.i("CEKIN", ""+tglCek_in);
        Log.i("CEKOUT", ""+tglCek_out);
        Log.i("JUMLAH KAMAR", ""+jumlahKamar);
        Log.i("KAPASITAS KAMAR", ""+kapasitasKamar);
        Log.i("TIPE KASIR", ""+tipeKasur);

        int jumlahTamu_int = Integer.parseInt(jumlahKamar.split(" ")[0]);
        for (int i = 1; i < jumlahTamu_int+1 ; i ++) {
            namaPassenger.add("Tamu " + i);
            titel.add("");

        }

        recyclerAdapterPenumpangList = new RecyclerAdapterPenumpangList(namaPassenger, this);
        binding.NamaPenumpangRecycleView.setAdapter(recyclerAdapterPenumpangList);


    }


    @Override
    public void addPassengerDetail(String nomorPelanggan) {
        DataTamu_BottomSheet dataTamu_bottomSheet = new DataTamu_BottomSheet();
        int index = Integer.parseInt(nomorPelanggan) - 1;
        Bundle bundle = new Bundle();
        bundle.putString("penumpangKe_n", nomorPelanggan);
        bundle.putString("nama_str", namaPassenger.get(index));
        bundle.putString("titel_str", titel.get(index));
        dataTamu_bottomSheet.setArguments(bundle);
        dataTamu_bottomSheet.show(getSupportFragmentManager(), dataTamu_bottomSheet.getTag());

    }

    @Override
    public void onDataTamuPass(String nama, String titel_str, int penumpangKe_n) {
        namaPassenger.set(penumpangKe_n-1, nama);
        titel.set(penumpangKe_n-1, titel_str);
        recyclerAdapterPenumpangList.notifyDataSetChanged();




    }
}


