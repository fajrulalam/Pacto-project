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
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
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

    String decrypt;

    boolean pulangPergi;

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

    String pinFirebase;



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

    String userID;




    RecyclerAdapterPenumpangList recyclerAdapterPenumpangList;
    FirebaseFirestore fs;

    int jumlahTamu_int;

    List<Map<String, String>> ArrayofPenumpangMaps;
    Map<String, String> dataPenumpangMap;


    //Informasi sintaksis Firebase
//    boolean pulangPergi;
    Long expTime;
//    Long waktuArsip;
    Long waktuArsip_pulang;
//    private boolean ongoing;
    private boolean ongoing_pulang;
    private String status;
    private String status_pulang;
//    private String userID;
    private String tipePesanan;
//    private String timeStampPesanan;

    //Primary keys
    ArrayList<String> bookingCode_pergi;
    ArrayList<String> bookingCode_pulang;


    //Penumpang
    private List<Map<String, String>> penumpang;
    String rincianPenumpang;
    String jmlDewasa;
    String jmlAnak;
    String jmlBalita;


    //harga
    String harga_dewasa;
    String harga_balita;
    String harga_dewasa_pulang;
    String harga_balita_pulang;
    String harga_total_pergi;
    String harga_total_pulang;
    String grand_total;

    //Pergi
    ArrayList<String> tanggalBerangkat_ArrayList;
    ArrayList<String> waktuBerangkat_ArrayList;
    ArrayList<String> bandaraAsal_ArrayList;
    ArrayList<Integer> logoMaskapai_ArrayList;
    ArrayList<String> namaMaskapai_ArrayList;
    ArrayList<String> kodePenerbangan_ArrayList;
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
    ArrayList<String> terminalBerangkat;
    ArrayList<String> terminalDatang;
    String tanggalPulang;
    String kotaAsal;
    String kotaTujuan;


    //Pulang
    ArrayList<String> tanggalBerangkat_ArrayList_pulang;
    ArrayList<String> waktuBerangkat_ArrayList_pulang;
    ArrayList<String> bandaraAsal_ArrayList_pulang;
    ArrayList<Integer> logoMaskapai_ArrayList_pulang;
    ArrayList<String> namaMaskapai_ArrayList_pulang;
    ArrayList<String> kodePenerbangan_ArrayList_pulang;
    ArrayList<String> kelasPesawat_ArrayList_pulang;
    ArrayList<String> tanggalDatang_ArrayList_pulang;
    ArrayList<String> waktuDatang_ArrayList_pulang;
    ArrayList<String> bandaraTujuan_ArrayList_pulang;
    ArrayList<String> kabin_ArrayList_pulang;
    ArrayList<String> bagasi_ArrayList_pulang;
    ArrayList<Integer> booleanMakan_ArrayList_pulang;
    ArrayList<String> keteranganMakan_ArrayList_pulang;
    ArrayList<String> modelPesawat_ArrayList_pulang;
    ArrayList<String> durasi_ArrayList_pulang;
    ArrayList<String> terminalBerangkat_pulang;
    ArrayList<String> terminalDatang_pulang;
    String tanggalPulang_pulang;
    String kotaAsal_pulang;
    String kotaTujuan_pulang;





    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMasukkanPinBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        tipePesanan = this.getIntent().getStringExtra("tipePesanan");
        fs = FirebaseFirestore.getInstance();
        binding.pin1.requestFocus();

        userID = "5E8dHyQfzYeu1wBvwjxNr8EUl7J3";

        fs.collection("user").document(userID).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Map<String, Object> map = (Map<String, Object>) documentSnapshot.getData();
                pinFirebase = map.get("pin").toString();

            }
        });



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
                    hargaPesawat = map.get("grand_total").toString();
                    namaHotel = ((ArrayList<String>) map.get("kodePenerbangan_ArrayList")).get(0);
                    pulangPergi = (boolean) map.get("pulangPergi");
                    if (hargaPesawat.matches("")){
                        hargaPesawat = map.get("harga").toString();
                    }

                    if (pulangPergi){
                        ongoing_pulang = (boolean) map.get("ongoing_pulang");
                        status_pulang = (String) map.get("status_pulang");
                        bookingCode_pulang = (ArrayList<String>) map.get("bookingCode_pulang");

                        //harga pulang
                        harga_dewasa_pulang =(String)  map.get("harga_dewasa_pulang");
                        harga_balita_pulang = (String) map.get("harga_balita_pulang");
                        harga_total_pulang = (String) map.get("harga_total_pulang");


                        //Pulang
                        tanggalBerangkat_ArrayList_pulang = (ArrayList<String>) map.get("tanggalBerangkat_ArrayList_pulang");
                        waktuBerangkat_ArrayList_pulang = (ArrayList<String>) map.get("waktuBerangkat_ArrayList_pulang");
                        bandaraAsal_ArrayList_pulang = (ArrayList<String>) map.get("bandaraAsal_ArrayList_pulang");
                        logoMaskapai_ArrayList_pulang =  (ArrayList<Integer>) map.get("logoMaskapai_ArrayList_pulang");
                        namaMaskapai_ArrayList_pulang = (ArrayList<String>) map.get("namaMaskapai_ArrayList_pulang");
                        kodePenerbangan_ArrayList_pulang =  (ArrayList<String>) map.get("kodePenerbangan_ArrayList_pulang");
                        kelasPesawat_ArrayList_pulang =  (ArrayList<String>) map.get("kelasPesawat_ArrayList_pulang");
                        tanggalDatang_ArrayList_pulang = (ArrayList<String>) map.get("tanggalDatang_ArrayList_pulang");
                        waktuDatang_ArrayList_pulang =  (ArrayList<String>) map.get("waktuDatang_ArrayList_pulang");
                        bandaraTujuan_ArrayList_pulang =  (ArrayList<String>) map.get("bandaraTujuan_ArrayList_pulang");
                        kabin_ArrayList_pulang = (ArrayList<String>) map.get("kabin_ArrayList_pulang");
                        bagasi_ArrayList_pulang = (ArrayList<String>) map.get("bagasi_ArrayList_pulang");
                        booleanMakan_ArrayList_pulang =  (ArrayList<Integer>) map.get("booleanMakan_ArrayList_pulang");
                        keteranganMakan_ArrayList_pulang =  (ArrayList<String>) map.get("keteranganMakan_ArrayList_pulang");
                        modelPesawat_ArrayList_pulang =  (ArrayList<String>) map.get("modelPesawat_ArrayList_pulang");
                        durasi_ArrayList_pulang =  (ArrayList<String>) map.get("durasi_ArrayList_pulang");
                        terminalBerangkat_pulang = (ArrayList<String>) map.get("terminalBerangkat_pulang");
                        terminalDatang_pulang = (ArrayList<String>) map.get("terminalDatang_pulang");
                        tanggalPulang_pulang = (String) map.get("tanggalPulang_pulang");
                        kotaAsal_pulang = (String) map.get("kotaAsal_pulang");
                        kotaTujuan_pulang = (String) map.get("kotaTujuan_pulang");

                    }
                    //Get all
                    //Informasi sintaksis Firebase
                    pulangPergi = (boolean) map.get("pulangPergi");
                    expTime = (Long) map.get("expTime");
                    waktuArsip = (long) map.get("waktuArsip");
                    waktuArsip_pulang = (Long) map.get("waktuArsip_pulang");
                    ongoing = (boolean) map.get("ongoing");
                    status = (String) map.get("status");
                    userID = (String) map.get("userID");
//                    tipePesanan = (String) map.get("tipePesanan");
//                    timeStampPesanan = (String) map.get("timeStampPesanan");

                    //Primary keys
                    bookingCode_pergi = (ArrayList<String>) map.get("bookingCode_pergi");


                    //Penumpang
                    penumpang = (List<Map<String, String>>) map.get("penumpang");
                    rincianPenumpang = (String) map.get("rincianPenumpang");
                    jmlDewasa =(String) map.get("jmlDewasa");
                    jmlAnak =(String) map.get("jmlAnak");
                    jmlBalita =(String) map.get("jmlBalita");


                    //harga
                    harga_dewasa = (String) map.get("harga_dewasa");
                    harga_balita = (String) map.get("harga_balita");
                    harga_total_pergi =(String)  map.get("harga_total_pergi");
                    int grand_total_int =  (Integer.parseInt(map.get("grand_total").toString()) );
                    grand_total = "IDR " + String.format("%,d", grand_total_int).replace(",", ".");

                    //Pergi
                    tanggalBerangkat_ArrayList = (ArrayList<String>) map.get("tanggalBerangkat_ArrayList");
                    waktuBerangkat_ArrayList = (ArrayList<String>) map.get("waktuBerangkat_ArrayList");
                    bandaraAsal_ArrayList = (ArrayList<String>) map.get("bandaraAsal_ArrayList");
                    logoMaskapai_ArrayList = (ArrayList<Integer>) map.get("logoMaskapai_ArrayList");
                    namaMaskapai_ArrayList = (ArrayList<String>) map.get("namaMaskapai_ArrayList");
                    kodePenerbangan_ArrayList = (ArrayList<String>) map.get("kodePenerbangan_ArrayList");
                    kelasPesawat_ArrayList = (ArrayList<String>) map.get("kelasPesawat_ArrayList");
                    tanggalDatang_ArrayList = (ArrayList<String>) map.get("tanggalDatang_ArrayList");
                    waktuDatang_ArrayList = (ArrayList<String>) map.get("waktuDatang_ArrayList");
                    bandaraTujuan_ArrayList = (ArrayList<String>) map.get("bandaraTujuan_ArrayList");
                    kabin_ArrayList = (ArrayList<String>) map.get("kabin_ArrayList");
                    bagasi_ArrayList = (ArrayList<String>) map.get("bagasi_ArrayList");
                    booleanMakan_ArrayList = (ArrayList<Integer>) map.get("booleanMakan_ArrayList");
                    keteranganMakan_ArrayList = (ArrayList<String>) map.get("keteranganMakan_ArrayList");
                    modelPesawat_ArrayList = (ArrayList<String>) map.get("modelPesawat_ArrayList");
                    durasi_ArrayList =(ArrayList<String>) map.get("durasi_ArrayList");
                    terminalBerangkat = (ArrayList<String>) map.get("terminalBerangkat");
                    terminalDatang = (ArrayList<String>) map.get("terminalDatang");
                    tanggalPulang = (String) map.get("tanggalPulang");
                    kotaAsal = (String) map.get("kotaAsal");
                    kotaTujuan = (String) map.get("kotaTujuan");





                }
            });


        }




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

                    pin = binding.pin1.getText().toString() + binding.pin2.getText().toString() + binding.pin3.getText().toString() + binding.pin4.getText().toString();
                    decrypt(pin, tipePesanan);


                }else if(editable.length() == 0)  {
                    binding.pin3.requestFocus();
                } else {
                    binding.pin4.setText(binding.pin3.getText().subSequence(0,0));
                }

            }
        });

    }

    public void decrypt(String pin, String tipePesanan){
        try {
            decrypt = AESCrypt.decrypt(pin, pinFirebase);
            if (tipePesanan.matches("Hotel")) {
                HotelcheckPIN();
            } else if (tipePesanan.matches("Pesawat")){
                PesawatcheckPIN();
            }

        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "PIN Anda Salah", Toast.LENGTH_SHORT).show();
            binding.pin1.setText("");
            binding.pin2.setText("");
            binding.pin3.setText("");
            binding.pin4.setText("");
            binding.pin1.requestFocus();
        }
    }

    public void HotelcheckPIN(){


            Log.i("PIN_CORRECT", "SEND DATA TO FIRESTORE");
            String namaPemesan = "Asad Albalad";
            String bookingCode = "B00K1N9C0D3";
            String status = "Issued";
            String tipePesanan = "Hotel";
            String userID = "5E8dHyQfzYeu1wBvwjxNr8EUl7J3";
            String JumlahKamar = jumlahKamar.split(", ")[1];
            int hargaTotal = 670000;

            int hargaPesawat_int = hargaTotal *-1;
            Log.i("harga hotel", ""+hargaPesawat_int);

            FieldValue timeStamp = FieldValue.serverTimestamp();
            BookingPesawat bookingPesawat = new BookingPesawat(
                    ongoing,
                    waktuArsip,
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
                    hargaTotal,
                    timeStamp
            );
            String keterangan = "Pemesanan hotel " + namaHotel;
            Date date = new Date();
            String date_str = new SimpleDateFormat("dd/MM/yyyy").format(date);
            String tipeTransaksi = "minus";
            String nominalTransaksi = "- IDR" + String.format("%,d", hargaTotal).replace(",", ".");
            Date dateNow = new Date();
            Long epochNow = dateNow.getTime();

            TransactionDetail transactionDetail = new TransactionDetail(epochNow, userID, keterangan, date_str, tipeTransaksi, nominalTransaksi);



            fs.collection("credit").add(transactionDetail).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    fs.collection("bookingHistory").add(bookingPesawat).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReferebnce) {
                            Toast.makeText(getApplicationContext(), "Update to Firestore successful!", Toast.LENGTH_SHORT).show();
                            fs.collection("user").document(userID).update("kredit", FieldValue.increment(hargaPesawat_int));
                            Intent intent = new Intent(getApplicationContext(), BookingActivity.class);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                        }
                    });

                }
            });







    }

    public void PesawatcheckPIN(){

            String keterangan = "Pemesanan tiket pesawat " + namaHotel;
            String userID = "5E8dHyQfzYeu1wBvwjxNr8EUl7J3";
            Date date = new Date();
            String date_str = new SimpleDateFormat("dd/MM/yyyy").format(date);
            String tipeTransaksi = "minus";
            String nominalTransaksi = "- IDR " + String.format("%,d", Integer.parseInt(hargaPesawat)).replace(",", ".");

//            String hargaPesawat_cleanstr = hargaPesawat.split(" ")[1].replace(".", "");
            int hargaPesawat_int = Integer.parseInt(hargaPesawat) *-1;
            Log.i("harga hotel", ""+hargaPesawat_int);


            Date dateNow = new Date();
            Long epochNow = dateNow.getTime();

            TransactionDetail transactionDetail = new TransactionDetail(epochNow, userID, keterangan, date_str, tipeTransaksi, nominalTransaksi);
            fs.collection("credit").add(transactionDetail).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    fs.collection("user").document(userID).update("kredit", FieldValue.increment(hargaPesawat_int));


                    if (pulangPergi == false){
                        fs.collection("bookingHistory").document(documentID).update("status", "Issued");
                        Intent intent = new Intent(getApplicationContext(), BookingActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    } else {

                        //add two new records, one for pergi and one for pulang
                        addPulangPergiRecords();


                    }
//
                }
            });


    }


    public void addPulangPergiRecords(){
        FieldValue timestamp = FieldValue.serverTimestamp();


        //Pergi
        CreateSingleBookingData createSingleBookingData_pergi = new CreateSingleBookingData(
                //Informasi Sintaksis
                false, //sudah tidak pulang pergi
                expTime, waktuArsip, ongoing,
                "Issued",
                userID, tipePesanan, timestamp,

                //Primary Keys
                bookingCode_pergi,

                //Penumpang
                penumpang, rincianPenumpang, jmlDewasa, jmlAnak, jmlBalita,

                //harga
                harga_dewasa, harga_balita, Integer.parseInt(harga_total_pergi.split("IDR ")[1].replace(".", "")) ,

                //Arraylistss
                tanggalBerangkat_ArrayList,
                waktuBerangkat_ArrayList,
                bandaraAsal_ArrayList,
                logoMaskapai_ArrayList,
                namaMaskapai_ArrayList,
                kodePenerbangan_ArrayList,
                kelasPesawat_ArrayList,
                tanggalDatang_ArrayList,
                waktuDatang_ArrayList,
                bandaraTujuan_ArrayList,
                kabin_ArrayList,
                bagasi_ArrayList,
                booleanMakan_ArrayList,
                keteranganMakan_ArrayList,
                modelPesawat_ArrayList,
                durasi_ArrayList,
                terminalBerangkat,
                terminalDatang,
                kotaAsal,
                kotaTujuan
        );

        if (pulangPergi) {
            CreateSingleBookingData createSingleBookingData_pulang = new CreateSingleBookingData(

                    //Informasi Sintaksis
                    false,  //sudah tidak pulang pergi
                    expTime, waktuArsip_pulang, ongoing,
                    "Issued",
                    userID, tipePesanan, timestamp,

                    //Primary Keys
                    bookingCode_pulang,

                    //Penumpang
                    penumpang, rincianPenumpang, jmlDewasa, jmlAnak, jmlBalita,

                    //harga
                    harga_dewasa_pulang, harga_balita_pulang, Integer.parseInt(harga_total_pulang.split("IDR ")[1].replace(".", "")),

                    //Arraylistss
                    tanggalBerangkat_ArrayList_pulang,
                    waktuBerangkat_ArrayList_pulang,
                    bandaraAsal_ArrayList_pulang,
                    logoMaskapai_ArrayList_pulang,
                    namaMaskapai_ArrayList_pulang,
                    kodePenerbangan_ArrayList_pulang,
                    kelasPesawat_ArrayList_pulang,
                    tanggalDatang_ArrayList_pulang,
                    waktuDatang_ArrayList_pulang,
                    bandaraTujuan_ArrayList_pulang,
                    kabin_ArrayList_pulang,
                    bagasi_ArrayList_pulang,
                    booleanMakan_ArrayList_pulang,
                    keteranganMakan_ArrayList_pulang,
                    modelPesawat_ArrayList_pulang,
                    durasi_ArrayList_pulang,
                    terminalBerangkat_pulang,
                    terminalDatang_pulang,
                    kotaAsal_pulang,
                    kotaTujuan_pulang

            );

            fs.collection("bookingHistory").add(createSingleBookingData_pergi).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    fs.collection("bookingHistory").add(createSingleBookingData_pulang).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            fs.collection("bookingHistory").document(documentID).delete();
                            Intent intent = new Intent(getApplicationContext(), BookingActivity.class);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                        }
                    });
                }
            });
        }

        if (!pulangPergi) {
            fs.collection("bookingHistory").add(createSingleBookingData_pergi).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    fs.collection("bookingHistory").document(documentID).delete();
                    Intent intent = new Intent(getApplicationContext(), BookingActivity.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);


                }
            });
        }





    }


    public class CreateSingleBookingData {

        //Informasi sintaksis Firebase
        boolean pulangPergi;
        Long expTime;
        Long waktuArsip;
        private boolean ongoing;
        private String status;
        private String userID;
        private String tipePesanan;
        private FieldValue timeStampPesanan;

        //Primary keys
        ArrayList<String> bookingCode_pergi;


        //Penumpang
        private List<Map<String, String>> penumpang;
        String rincianPenumpang;
        String jmlDewasa;
        String jmlAnak;
        String jmlBalita;


        //harga
        String harga_dewasa;
        String harga_balita;
        int grand_total;

        //Pergi
        ArrayList<String> tanggalBerangkat_ArrayList;
        ArrayList<String> waktuBerangkat_ArrayList;
        ArrayList<String> bandaraAsal_ArrayList;
        ArrayList<Integer> logoMaskapai_ArrayList;
        ArrayList<String> namaMaskapai_ArrayList;
        ArrayList<String> kodePenerbangan_ArrayList;
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
        ArrayList<String> terminalBerangkat;
        ArrayList<String> terminalDatang;
        String kotaAsal;
        String kotaTujuan;

        public CreateSingleBookingData(boolean pulangPergi, Long expTime, Long waktuArsip, boolean ongoing, String status, String userID, String tipePesanan, FieldValue timeStampPesanan, ArrayList<String> bookingCode_pergi, List<Map<String, String>> penumpang, String rincianPenumpang, String jmlDewasa, String jmlAnak, String jmlBalita, String harga_dewasa, String harga_balita, int grand_total, ArrayList<String> tanggalBerangkat_ArrayList, ArrayList<String> waktuBerangkat_ArrayList, ArrayList<String> bandaraAsal_ArrayList, ArrayList<Integer> logoMaskapai_ArrayList, ArrayList<String> namaMaskapai_ArrayList, ArrayList<String> kodePenerbangan_ArrayList, ArrayList<String> kelasPesawat_ArrayList, ArrayList<String> tanggalDatang_ArrayList, ArrayList<String> waktuDatang_ArrayList, ArrayList<String> bandaraTujuan_ArrayList, ArrayList<String> kabin_ArrayList, ArrayList<String> bagasi_ArrayList, ArrayList<Integer> booleanMakan_ArrayList, ArrayList<String> keteranganMakan_ArrayList, ArrayList<String> modelPesawat_ArrayList, ArrayList<String> durasi_ArrayList, ArrayList<String> terminalBerangkat, ArrayList<String> terminalDatang, String kotaAsal, String kotaTujuan) {
            this.pulangPergi = pulangPergi;
            this.expTime = expTime;
            this.waktuArsip = waktuArsip;
            this.ongoing = ongoing;
            this.status = status;
            this.userID = userID;
            this.tipePesanan = tipePesanan;
            this.timeStampPesanan = timeStampPesanan;
            this.bookingCode_pergi = bookingCode_pergi;
            this.penumpang = penumpang;
            this.rincianPenumpang = rincianPenumpang;
            this.jmlDewasa = jmlDewasa;
            this.jmlAnak = jmlAnak;
            this.jmlBalita = jmlBalita;
            this.harga_dewasa = harga_dewasa;
            this.harga_balita = harga_balita;
            this.grand_total = grand_total;
            this.tanggalBerangkat_ArrayList = tanggalBerangkat_ArrayList;
            this.waktuBerangkat_ArrayList = waktuBerangkat_ArrayList;
            this.bandaraAsal_ArrayList = bandaraAsal_ArrayList;
            this.logoMaskapai_ArrayList = logoMaskapai_ArrayList;
            this.namaMaskapai_ArrayList = namaMaskapai_ArrayList;
            this.kodePenerbangan_ArrayList = kodePenerbangan_ArrayList;
            this.kelasPesawat_ArrayList = kelasPesawat_ArrayList;
            this.tanggalDatang_ArrayList = tanggalDatang_ArrayList;
            this.waktuDatang_ArrayList = waktuDatang_ArrayList;
            this.bandaraTujuan_ArrayList = bandaraTujuan_ArrayList;
            this.kabin_ArrayList = kabin_ArrayList;
            this.bagasi_ArrayList = bagasi_ArrayList;
            this.booleanMakan_ArrayList = booleanMakan_ArrayList;
            this.keteranganMakan_ArrayList = keteranganMakan_ArrayList;
            this.modelPesawat_ArrayList = modelPesawat_ArrayList;
            this.durasi_ArrayList = durasi_ArrayList;
            this.terminalBerangkat = terminalBerangkat;
            this.terminalDatang = terminalDatang;
            this.kotaAsal = kotaAsal;
            this.kotaTujuan = kotaTujuan;
        }

        public boolean isPulangPergi() {
            return pulangPergi;
        }

        public void setPulangPergi(boolean pulangPergi) {
            this.pulangPergi = pulangPergi;
        }

        public Long getExpTime() {
            return expTime;
        }

        public void setExpTime(Long expTime) {
            this.expTime = expTime;
        }

        public Long getWaktuArsip() {
            return waktuArsip;
        }

        public void setWaktuArsip(Long waktuArsip) {
            this.waktuArsip = waktuArsip;
        }

        public boolean isOngoing() {
            return ongoing;
        }

        public void setOngoing(boolean ongoing) {
            this.ongoing = ongoing;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getTipePesanan() {
            return tipePesanan;
        }

        public void setTipePesanan(String tipePesanan) {
            this.tipePesanan = tipePesanan;
        }

        public FieldValue getTimeStampPesanan() {
            return timeStampPesanan;
        }

        public void setTimeStampPesanan(FieldValue timeStampPesanan) {
            this.timeStampPesanan = timeStampPesanan;
        }

        public ArrayList<String> getBookingCode_pergi() {
            return bookingCode_pergi;
        }

        public void setBookingCode_pergi(ArrayList<String> bookingCode_pergi) {
            this.bookingCode_pergi = bookingCode_pergi;
        }

        public List<Map<String, String>> getPenumpang() {
            return penumpang;
        }

        public void setPenumpang(List<Map<String, String>> penumpang) {
            this.penumpang = penumpang;
        }

        public String getRincianPenumpang() {
            return rincianPenumpang;
        }

        public void setRincianPenumpang(String rincianPenumpang) {
            this.rincianPenumpang = rincianPenumpang;
        }

        public String getJmlDewasa() {
            return jmlDewasa;
        }

        public void setJmlDewasa(String jmlDewasa) {
            this.jmlDewasa = jmlDewasa;
        }

        public String getJmlAnak() {
            return jmlAnak;
        }

        public void setJmlAnak(String jmlAnak) {
            this.jmlAnak = jmlAnak;
        }

        public String getJmlBalita() {
            return jmlBalita;
        }

        public void setJmlBalita(String jmlBalita) {
            this.jmlBalita = jmlBalita;
        }

        public String getHarga_dewasa() {
            return harga_dewasa;
        }

        public void setHarga_dewasa(String harga_dewasa) {
            this.harga_dewasa = harga_dewasa;
        }

        public String getHarga_balita() {
            return harga_balita;
        }

        public void setHarga_balita(String harga_balita) {
            this.harga_balita = harga_balita;
        }

        public int getGrand_total() {
            return grand_total;
        }

        public void setGrand_total(int grand_total) {
            this.grand_total = grand_total;
        }

        public ArrayList<String> getTanggalBerangkat_ArrayList() {
            return tanggalBerangkat_ArrayList;
        }

        public void setTanggalBerangkat_ArrayList(ArrayList<String> tanggalBerangkat_ArrayList) {
            this.tanggalBerangkat_ArrayList = tanggalBerangkat_ArrayList;
        }

        public ArrayList<String> getWaktuBerangkat_ArrayList() {
            return waktuBerangkat_ArrayList;
        }

        public void setWaktuBerangkat_ArrayList(ArrayList<String> waktuBerangkat_ArrayList) {
            this.waktuBerangkat_ArrayList = waktuBerangkat_ArrayList;
        }

        public ArrayList<String> getBandaraAsal_ArrayList() {
            return bandaraAsal_ArrayList;
        }

        public void setBandaraAsal_ArrayList(ArrayList<String> bandaraAsal_ArrayList) {
            this.bandaraAsal_ArrayList = bandaraAsal_ArrayList;
        }

        public ArrayList<Integer> getLogoMaskapai_ArrayList() {
            return logoMaskapai_ArrayList;
        }

        public void setLogoMaskapai_ArrayList(ArrayList<Integer> logoMaskapai_ArrayList) {
            this.logoMaskapai_ArrayList = logoMaskapai_ArrayList;
        }

        public ArrayList<String> getNamaMaskapai_ArrayList() {
            return namaMaskapai_ArrayList;
        }

        public void setNamaMaskapai_ArrayList(ArrayList<String> namaMaskapai_ArrayList) {
            this.namaMaskapai_ArrayList = namaMaskapai_ArrayList;
        }

        public ArrayList<String> getKodePenerbangan_ArrayList() {
            return kodePenerbangan_ArrayList;
        }

        public void setKodePenerbangan_ArrayList(ArrayList<String> kodePenerbangan_ArrayList) {
            this.kodePenerbangan_ArrayList = kodePenerbangan_ArrayList;
        }

        public ArrayList<String> getKelasPesawat_ArrayList() {
            return kelasPesawat_ArrayList;
        }

        public void setKelasPesawat_ArrayList(ArrayList<String> kelasPesawat_ArrayList) {
            this.kelasPesawat_ArrayList = kelasPesawat_ArrayList;
        }

        public ArrayList<String> getTanggalDatang_ArrayList() {
            return tanggalDatang_ArrayList;
        }

        public void setTanggalDatang_ArrayList(ArrayList<String> tanggalDatang_ArrayList) {
            this.tanggalDatang_ArrayList = tanggalDatang_ArrayList;
        }

        public ArrayList<String> getWaktuDatang_ArrayList() {
            return waktuDatang_ArrayList;
        }

        public void setWaktuDatang_ArrayList(ArrayList<String> waktuDatang_ArrayList) {
            this.waktuDatang_ArrayList = waktuDatang_ArrayList;
        }

        public ArrayList<String> getBandaraTujuan_ArrayList() {
            return bandaraTujuan_ArrayList;
        }

        public void setBandaraTujuan_ArrayList(ArrayList<String> bandaraTujuan_ArrayList) {
            this.bandaraTujuan_ArrayList = bandaraTujuan_ArrayList;
        }

        public ArrayList<String> getKabin_ArrayList() {
            return kabin_ArrayList;
        }

        public void setKabin_ArrayList(ArrayList<String> kabin_ArrayList) {
            this.kabin_ArrayList = kabin_ArrayList;
        }

        public ArrayList<String> getBagasi_ArrayList() {
            return bagasi_ArrayList;
        }

        public void setBagasi_ArrayList(ArrayList<String> bagasi_ArrayList) {
            this.bagasi_ArrayList = bagasi_ArrayList;
        }

        public ArrayList<Integer> getBooleanMakan_ArrayList() {
            return booleanMakan_ArrayList;
        }

        public void setBooleanMakan_ArrayList(ArrayList<Integer> booleanMakan_ArrayList) {
            this.booleanMakan_ArrayList = booleanMakan_ArrayList;
        }

        public ArrayList<String> getKeteranganMakan_ArrayList() {
            return keteranganMakan_ArrayList;
        }

        public void setKeteranganMakan_ArrayList(ArrayList<String> keteranganMakan_ArrayList) {
            this.keteranganMakan_ArrayList = keteranganMakan_ArrayList;
        }

        public ArrayList<String> getModelPesawat_ArrayList() {
            return modelPesawat_ArrayList;
        }

        public void setModelPesawat_ArrayList(ArrayList<String> modelPesawat_ArrayList) {
            this.modelPesawat_ArrayList = modelPesawat_ArrayList;
        }

        public ArrayList<String> getDurasi_ArrayList() {
            return durasi_ArrayList;
        }

        public void setDurasi_ArrayList(ArrayList<String> durasi_ArrayList) {
            this.durasi_ArrayList = durasi_ArrayList;
        }

        public ArrayList<String> getTerminalBerangkat() {
            return terminalBerangkat;
        }

        public void setTerminalBerangkat(ArrayList<String> terminalBerangkat) {
            this.terminalBerangkat = terminalBerangkat;
        }

        public ArrayList<String> getTerminalDatang() {
            return terminalDatang;
        }

        public void setTerminalDatang(ArrayList<String> terminalDatang) {
            this.terminalDatang = terminalDatang;
        }

        public String getKotaAsal() {
            return kotaAsal;
        }

        public void setKotaAsal(String kotaAsal) {
            this.kotaAsal = kotaAsal;
        }

        public String getKotaTujuan() {
            return kotaTujuan;
        }

        public void setKotaTujuan(String kotaTujuan) {
            this.kotaTujuan = kotaTujuan;
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
        private int hargaTotal;
        FieldValue pesananTimeStamp;

        public BookingPesawat() {
        }

        public BookingPesawat(boolean ongoing, long waktuArsip, String tglCek_in, String tglCek_out, String jumlahMalam, List<Map<String, String>> dataTamu, String namaPemesan, String bookingCode, String namaHotel, String status, String tipePesanan, String userID, String jumlahKamar, String namaKamar, String permintaanKhusus, String tambahanAlamat, int hargaTotal, FieldValue pesananTimeStamp) {
            this.ongoing = ongoing;
            this.waktuArsip = waktuArsip;
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

        public int getHargaTotal() {
            return hargaTotal;
        }

        public void setHargaTotal(int hargaTotal) {
            this.hargaTotal = hargaTotal;
        }
    }

    public class TransactionDetail{
        Long timeStampEpoch;
        String keterangan;
        String tanggal;
        String tipeTransaksi;
        String nominalTransaksi;
        String userID;


        public TransactionDetail() {
        }

        public TransactionDetail(Long timeStampEpoch, String userID, String keterangan, String tanggal, String tipeTransaksi, String nominalTransaksi) {
            this.keterangan = keterangan;
            this.tanggal = tanggal;
            this.tipeTransaksi = tipeTransaksi;
            this.nominalTransaksi = nominalTransaksi;
            this.userID = userID;
            this.timeStampEpoch = timeStampEpoch;
        }


        public Long getTimeStampEpoch() {
            return timeStampEpoch;
        }

        public void setTimeStampEpoch(Long timeStampEpoch) {
            this.timeStampEpoch = timeStampEpoch;
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