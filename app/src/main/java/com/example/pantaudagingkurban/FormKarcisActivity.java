package com.example.pantaudagingkurban;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.pantaudagingkurban.database.KarcisDB;
import com.example.pantaudagingkurban.database.TempatDB;
import com.example.pantaudagingkurban.model.KarcisModel;
import com.example.pantaudagingkurban.model.TempatModel;
import com.google.android.material.textfield.TextInputEditText;


import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormKarcisActivity extends AppCompatActivity {

    private KarcisDB karcisDB;
    private TempatDB tempatDB;

    private ProgressDialog pd;

    //Kode Karcis
    private String kode_awal = "A";
    private String kode_tengah = "KRCS";
    private String kode_nol = "";
    private String kode_akhir = "";
    private String kode_final = "";


    private Button enter, btn_kurang, btn_tambah;
    private TextInputEditText tv_waktu, tv_jam, tv_jumlah, tv_nama, tv_tempat, tv_telpon, tv_alamat;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pd = new ProgressDialog(this);

        enter       = findViewById(R.id.enter);
        tv_waktu    = findViewById(R.id.tv_waktu);
        tv_nama     = findViewById(R.id.tv_nama);
        tv_tempat   = findViewById(R.id.tv_tempat);
        tv_telpon   = findViewById(R.id.tv_telpon);
        tv_alamat   = findViewById(R.id.tv_alamat);
        tv_jam      = findViewById(R.id.tv_jam);
        tv_jumlah   = findViewById(R.id.tv_jumlah);
        btn_kurang  = findViewById(R.id.btn_kurang);
        btn_tambah  = findViewById(R.id.btn_tambah);

        karcisDB = Room.databaseBuilder(getApplicationContext(), KarcisDB.class, "karcis_db").build();
        tempatDB = Room.databaseBuilder(getApplicationContext(), TempatDB.class, "tempat_db").build();

        btn_kurang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String jumlah_awal = tv_jumlah.getText().toString();

                if (!jumlah_awal.isEmpty() && !jumlah_awal.equals("0")) {
                    int jumlah = Integer.parseInt(jumlah_awal) - 1;
                    tv_jumlah.setText(String.valueOf(jumlah));
                }
            }
        });

        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jumlah_awal = tv_jumlah.getText().toString();
                if (jumlah_awal.isEmpty()) {
                    tv_jumlah.setText("1");
                } else {
                    int jumlah = Integer.parseInt(jumlah_awal) + 1;
                    tv_jumlah.setText(String.valueOf(jumlah));
                }

            }
        });


        tv_jam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogJam();
            }
        });

        tv_waktu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogKelender();
            }
        });


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pd.setMessage("Proses ... ");
                pd.setCancelable(false);
                pd.show();

                if (tv_jumlah.getText().toString().isEmpty() ||
                        tv_nama.getText().toString().isEmpty() ||
                        tv_tempat.getText().toString().isEmpty() ||
                        tv_telpon.getText().toString().isEmpty() ||
                        tv_waktu.getText().toString().isEmpty() ||
                        tv_alamat.getText().toString().isEmpty() ||
                        tv_jumlah.getText().toString().isEmpty() ||
                        tv_jumlah.getText().toString().equals("0") ||
                        tv_jam.getText().toString().isEmpty()) {
                    Toast.makeText(FormKarcisActivity.this, "Lengkapi Semua Isian Diatas!", Toast.LENGTH_SHORT).show();
                } else {
//                    Toast.makeText(FormKarcisActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();

                    String nama     = tv_nama.getText().toString();
                    String tempat   = tv_tempat.getText().toString();
                    String telpon   = tv_telpon.getText().toString();
                    String tgl      = tv_waktu.getText().toString();
                    String jam      = tv_jam.getText().toString();
                    String alamat   = tv_alamat.getText().toString();
                    String jumlah   = tv_jumlah.getText().toString();
                    String status   = "Belum";

                    tambahTempat();

                    int jumlah_int = Integer.parseInt(jumlah);

                    for (int a = 1; a <= jumlah_int; a++) {

                        final KarcisModel karcisModel = new KarcisModel();
                        karcisModel.setNama_pj(nama);
                        karcisModel.setTempat(tempat);
                        karcisModel.setNomor_hp(telpon);
                        karcisModel.setTanggal(tgl);
                        karcisModel.setJam(jam);
                        karcisModel.setAlamat(alamat);
                        karcisModel.setStatus(status);

                        //penyeleksian kode jumlah Nol
                        kode_akhir = String.valueOf(a);
                        if (kode_akhir.length() == 1) {
                            kode_nol = "000";
                        } else if (kode_akhir.length() == 2) {
                            kode_nol = "00";
                        } else if (kode_akhir.length() == 3) {
                            kode_nol = "0";
                        } else if (kode_akhir.length() == 4) {
                            kode_nol = "";
                        }

                        //perumusan kode karcis
                        kode_final = kode_awal + kode_tengah + kode_nol + kode_akhir;
                        karcisModel.setKode_karcis(kode_final);

                        new AsyncTask<Void, Void, Long>() {
                            @Override
                            protected Long doInBackground(Void... voids) {

                                long add = karcisDB.karcisDAO().insertKarcis(karcisModel);

                                return add;
                            }

                            @Override
                            protected void onPostExecute(Long aLong) {
                                finish();
                                pd.hide();
                                startActivity(new Intent(FormKarcisActivity.this, TempatActivity.class));

                            }
                        }.execute();

                    }


                }

            }
        });


    }

    private void tambahTempat() {
        String tempat   = tv_tempat.getText().toString();
        String tgl      = tv_waktu.getText().toString();

        final TempatModel tempatModel = new TempatModel();
        tempatModel.setTempat(tempat);
        tempatModel.setTanggal(tgl);

        new AsyncTask<Void, Void, Long>() {

            @Override
            protected Long doInBackground(Void... voids) {
                long add = tempatDB.tempatDAO().insetrTempat(tempatModel);
                return add;
            }

            @Override
            protected void onPostExecute(Long aLong) {

            }
        }.execute();

    }

    private void dialogJam() {
        Calendar calendar = Calendar.getInstance();

        final DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                String jam = String.valueOf(hourOfDay);
                String menit = String.valueOf(minute);
                String final_jam = "";
                String final_menit = "";
                if (jam.length() == 1) {
                    final_jam = "0" + jam;
                } else {
                    final_jam = jam;
                }

                if (menit.length() == 1) {
                    final_menit = "0" + menit;
                } else {
                    final_menit = menit;
                }

                tv_jam.setText(final_jam + ":" + final_menit);
            }
        },
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), android.text.format.DateFormat.is24HourFormat(this)

        );
        timePickerDialog.show();
    }


    private void dialogKelender() {
        final DateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);

                tv_waktu.setText(dateFormat.format(newDate.getTime()));

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();

    }


//    private void buatQrCode(){
//        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
//        try {
//            BitMatrix bitMatrix = multiFormatWriter.encode(et_qrcode.getText().toString(), BarcodeFormat.QR_CODE,300,300);
//            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
//            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
//            img_qrcode.setImageBitmap(bitmap);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
