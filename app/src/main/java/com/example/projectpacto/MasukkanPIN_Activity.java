package com.example.projectpacto;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.projectpacto.databinding.ActivityHotelOrder4Binding;
import com.example.projectpacto.databinding.ActivityMasukkanPinBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.security.auth.login.LoginException;

public class MasukkanPIN_Activity extends AppCompatActivity  {
    ActivityMasukkanPinBinding binding;
    String pin;

    String hargaPesawat;

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


    String permintaanKhusus;

    ArrayList<String> namaPassenger;
    ArrayList<String> titel;

    //Permintaan Khusus
    int bebasAsapRokok;
    int kamarTersambung;
    String waktuCekin_req;
    String waktuCekOut_req;
    String tipeRanjang_req;
    String catatanLainnya_req;
    String documentID;
    long waktuArsip;
    boolean ongoing;

    RecyclerAdapterPenumpangList recyclerAdapterPenumpangList;
    FirebaseFirestore fs;

    int jumlahTamu_int;

    List<Map<String, String>> ArrayofPenumpangMaps;
    Map<String, String> dataPenumpangMap;




    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMasukkanPinBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String tipePesanan = this.getIntent().getStringExtra("tipePesanan");
        fs = FirebaseFirestore.getInstance();
        binding.pin1.requestFocus();


        if (tipePesanan.matches("Hotel")) {
            extras = this.getIntent().getBundleExtra("bundle");

            gambarKamar = extras.getInt("gambarKamar");
            namaKamar = extras.getString("namaKamar");
            kapasitasKamar = extras.getInt("kapasitasKamar");
            tipeKasur = extras.getString("tipeKasur");
            sarapan = extras.getString("sarapan");
            hargaKamar = extras.getString("hargaKamar");

            gambarHotel = extras.getInt("gambarHotel");
            namaHotel = extras.getString("namaHotel");
            tambahanAlamat = extras.getString("tambahanAlamat");
            hargaMulaiHotel = extras.getString("harga");
            jmlBintang = extras.getInt("jmlBintang");

            kotaAtauHotel = extras.getString("kotaAtauHotel");
            jumlahKamar = extras.getString("jumlahKamar");
            jumlahMalam = extras.getString("jumlahMalam");
            tglCek_in = extras.getString("tglCek_in");
            tglCek_out = extras.getString("tglCek_out");

            ongoing = true;
            Locale lokal = new Locale("id", "ID");
            try {
                Date date = new SimpleDateFormat("E, dd MMM yyyy", lokal).parse(tglCek_out);
                waktuArsip = date.getTime() + 86400000;
            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }

            permintaanKhusus = extras.getString("permintaanKhusus");

            namaPassenger = extras.getStringArrayList("namaTamu");
            titel = extras.getStringArrayList("titel");

            Log.i(TAG, "onCreate: Nama Tamu " + namaPassenger);
            Log.i(TAG, "onCreate: Titel " + titel);

            ArrayofPenumpangMaps = new ArrayList<>();

            for (int i = 0; i < namaPassenger.size(); i++) {
                dataPenumpangMap = new HashMap<>();
                dataPenumpangMap.put("namaPenumpang", namaPassenger.get(i));
                dataPenumpangMap.put("titel", titel.get(i));


                ArrayofPenumpangMaps.add(i, dataPenumpangMap);
            }

            Log.i(TAG, "onCreate: ArrayofPenumpangMaps " + ArrayofPenumpangMaps);
        }

        if (tipePesanan.matches("Pesawat")){
            documentID = this.getIntent().getStringExtra("documentID");
            fs.collection("bookingHistory").document(documentID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Map<String, Object> map = documentSnapshot.getData();
                    hargaPesawat = map.get("hargaTotal").toString();
                    namaHotel = map.get("kodePenerbangan").toString();
                    if (hargaPesawat.matches("")){
                        hargaPesawat = map.get("harga").toString();
                    }

                }
            });


        }








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
                    if (tipePesanan.matches("Hotel")) {
                        HotelcheckPIN(pin);
                    } else if (tipePesanan.matches("Pesawat")){
                        PesawatcheckPIN(pin);
                    }


                }else if(editable.length() == 0)  {
                    binding.pin3.requestFocus();
                } else {
                    binding.pin4.setText(binding.pin3.getText().subSequence(0,0));
                }

            }
        });

    }

    public void HotelcheckPIN(String pin){
        if (pin.matches("1738")){


            Log.i("PIN_CORRECT", "SEND DATA TO FIRESTORE");
            String namaPemesan = "Asad Albalad";
            String bookingCode = "B00K1N9C0D3";
            String status = "Issued";
            String tipePesanan = "Hotel";
            String userID = "5E8dHyQfzYeu1wBvwjxNr8EUl7J3";
            String JumlahKamar = jumlahKamar.split(", ")[1];
            String hargaTotal = "IDR 670.000";
            FieldValue timeStamp = FieldValue.serverTimestamp();
            BookingPesawat bookingPesawat = new BookingPesawat(
                    ongoing,
                    waktuArsip,
                    timeStamp,
                    tglCek_in,
                    tglCek_out,
                    jumlahMalam,
                    ArrayofPenumpangMaps,
                    namaPemesan,
                    bookingCode,
                    namaHotel,
                    status,
                    tipePesanan,
                    userID,
                    JumlahKamar,
                    namaKamar,
                    permintaanKhusus,
                    tambahanAlamat,
                    hargaTotal
            );
            String keterangan = "Pemesanan hotel " + namaHotel;
            Date date = new Date();
            String date_str = new SimpleDateFormat("dd/MM/yyyy").format(date);
            String tipeTransaksi = "minus";
            String nominalTransaksi = "- " + hargaTotal;

            TransactionDetail transactionDetail = new TransactionDetail(userID, keterangan, date_str, tipeTransaksi, nominalTransaksi);


            fs.collection("credit").add(transactionDetail).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    fs.collection("bookingHistory").add(bookingPesawat).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReferebnce) {
                            Toast.makeText(getApplicationContext(), "Update to Firestore successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), BookingActivity.class);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                        }
                    });

                }
            });






        }
    }

    public void PesawatcheckPIN(String pin){
        if (pin.matches("1738")){
            String keterangan = "Pemesanan tiket pesawat " + namaHotel;
            String userID = "5E8dHyQfzYeu1wBvwjxNr8EUl7J3";
            Date date = new Date();
            String date_str = new SimpleDateFormat("dd/MM/yyyy").format(date);
            String tipeTransaksi = "minus";
            String nominalTransaksi = "- " + hargaPesawat;

            TransactionDetail transactionDetail = new TransactionDetail(userID, keterangan, date_str, tipeTransaksi, nominalTransaksi);
            fs.collection("credit").add(transactionDetail).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    fs.collection("bookingHistory").document(documentID).update("status", "Issued");
                    Intent intent = new Intent(getApplicationContext(), BookingActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                }
            });

        }
    }

    public class BookingPesawat {

        private  boolean ongoing;
        private long waktuArsip;
        private String tglCek_in;
        private String tglCek_out;
        private String jumlahMalam;
        private List<Map<String, String>> dataTamu;
        private String namaPemesan;
        private String bookingCode;
        private String namaHotel;
        private String status;
        private String tipePesanan;
        private String userID;
        private String jumlahKamar;
        private String namaKamar;
        private String permintaanKhusus;
        private String tambahanAlamat;
        private String hargaTotal;
        FieldValue pesananTimeStamp;

        public BookingPesawat() {
        }

        public BookingPesawat(boolean ongoing, long waktuArsip, FieldValue pesananTimeStamp, String tglCek_in, String tglCek_out, String jumlahMalam, List<Map<String, String>> dataTamu, String namaPemesan, String bookingCode, String namaHotel, String status, String tipePesanan, String userID, String jumlahKamar, String namaKamar, String permintaanKhusus, String tambahanAlamat, String hargaTotal) {
            this.tglCek_in = tglCek_in;
            this.tglCek_out = tglCek_out;
            this.jumlahMalam = jumlahMalam;
            this.dataTamu = dataTamu;
            this.namaPemesan = namaPemesan;
            this.bookingCode = bookingCode;
            this.namaHotel = namaHotel;
            this.status = status;
            this.tipePesanan = tipePesanan;
            this.userID = userID;
            this.jumlahKamar = jumlahKamar;
            this.namaKamar = namaKamar;
            this.permintaanKhusus = permintaanKhusus;
            this.tambahanAlamat = tambahanAlamat;
            this.hargaTotal = hargaTotal;
            this.pesananTimeStamp = pesananTimeStamp;
            this.ongoing = ongoing;
            this.waktuArsip = waktuArsip;
        }

        public boolean isOngoing() {
            return ongoing;
        }

        public void setOngoing(boolean ongoing) {
            this.ongoing = ongoing;
        }

        public long getWaktuArsip() {
            return waktuArsip;
        }

        public void setWaktuArsip(long waktuArsip) {
            this.waktuArsip = waktuArsip;
        }

        public FieldValue getPesananTimeStamp() {
            return pesananTimeStamp;
        }

        public void setPesananTimeStamp(FieldValue pesananTimeStamp) {
            this.pesananTimeStamp = pesananTimeStamp;
        }

        public String getTglCek_in() {
            return tglCek_in;
        }

        public void setTglCek_in(String tglCek_in) {
            this.tglCek_in = tglCek_in;
        }

        public String getTglCek_out() {
            return tglCek_out;
        }

        public void setTglCek_out(String tglCek_out) {
            this.tglCek_out = tglCek_out;
        }

        public String getJumlahMalam() {
            return jumlahMalam;
        }

        public void setJumlahMalam(String jumlahMalam) {
            this.jumlahMalam = jumlahMalam;
        }

        public List<Map<String, String>> getDataTamu() {
            return dataTamu;
        }

        public void setDataTamu(List<Map<String, String>> dataTamu) {
            this.dataTamu = dataTamu;
        }

        public String getNamaPemesan() {
            return namaPemesan;
        }

        public void setNamaPemesan(String namaPemesan) {
            this.namaPemesan = namaPemesan;
        }

        public String getBookingCode() {
            return bookingCode;
        }

        public void setBookingCode(String bookingCode) {
            this.bookingCode = bookingCode;
        }

        public String getNamaHotel() {
            return namaHotel;
        }

        public void setNamaHotel(String namaHotel) {
            this.namaHotel = namaHotel;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTipePesanan() {
            return tipePesanan;
        }

        public void setTipePesanan(String tipePesanan) {
            this.tipePesanan = tipePesanan;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getJumlahKamar() {
            return jumlahKamar;
        }

        public void setJumlahKamar(String jumlahKamar) {
            this.jumlahKamar = jumlahKamar;
        }

        public String getNamaKamar() {
            return namaKamar;
        }

        public void setNamaKamar(String namaKamar) {
            this.namaKamar = namaKamar;
        }

        public String getPermintaanKhusus() {
            return permintaanKhusus;
        }

        public void setPermintaanKhusus(String permintaanKhusus) {
            this.permintaanKhusus = permintaanKhusus;
        }

        public String getTambahanAlamat() {
            return tambahanAlamat;
        }

        public void setTambahanAlamat(String tambahanAlamat) {
            this.tambahanAlamat = tambahanAlamat;
        }

        public String getHargaTotal() {
            return hargaTotal;
        }

        public void setHargaTotal(String hargaTotal) {
            this.hargaTotal = hargaTotal;
        }
    }

    public class TransactionDetail{
        String keterangan;
        String tanggal;
        String tipeTransaksi;
        String nominalTransaksi;
        String userID;


        public TransactionDetail() {
        }

        public TransactionDetail(String userID, String keterangan, String tanggal, String tipeTransaksi, String nominalTransaksi) {
            this.keterangan = keterangan;
            this.tanggal = tanggal;
            this.tipeTransaksi = tipeTransaksi;
            this.nominalTransaksi = nominalTransaksi;
            this.userID = userID;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getKeterangan() {
            return keterangan;
        }

        public void setKeterangan(String keterangan) {
            this.keterangan = keterangan;
        }

        public String getTanggal() {
            return tanggal;
        }

        public void setTanggal(String tanggal) {
            this.tanggal = tanggal;
        }

        public String getTipeTransaksi() {
            return tipeTransaksi;
        }

        public void setTipeTransaksi(String tipeTransaksi) {
            this.tipeTransaksi = tipeTransaksi;
        }

        public String getNominalTransaksi() {
            return nominalTransaksi;
        }

        public void setNominalTransaksi(String nominalTransaksi) {
            this.nominalTransaksi = nominalTransaksi;
        }
    }




}