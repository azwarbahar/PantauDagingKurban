package com.example.pantaudagingkurban;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pantaudagingkurban.adapter.KarcisAdapter;
import com.example.pantaudagingkurban.database.KarcisDB;
import com.example.pantaudagingkurban.model.KarcisModel;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;
import java.util.List;

public class KarcisActivity extends AppCompatActivity {

    private KarcisDB karcisDB;
    private RecyclerView rv_karcis;
    private KarcisAdapter adapter;
    private Toolbar toolbar;
    private String AddStatus = "";
    private ArrayList<KarcisModel> karcisModels;
    private TextView tv_kosong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karcis);

        tv_kosong = findViewById(R.id.tv_kosong);
        tv_kosong.setVisibility(View.VISIBLE);
        karcisDB = Room.databaseBuilder(getApplicationContext(), KarcisDB.class, "karcis_db").build();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rv_karcis = findViewById(R.id.rv_karcis);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv_karcis.setLayoutManager(layoutManager);

        karcisModels = new ArrayList<>();

        new AsyncTask<Void, Void, List>() {
            @Override
            protected List<KarcisModel> doInBackground(Void... voids) {
                List<KarcisModel> readKarcisAll = karcisDB.karcisDAO().readAllKarcis();
                return readKarcisAll;
            }

            @Override
            protected void onPostExecute(List list) {
                super.onPostExecute(list);
                adapter = new KarcisAdapter(KarcisActivity.this,list);
                rv_karcis.setAdapter(adapter);
                if (list.isEmpty()) {
                    AddStatus = "kosong";
                    tv_kosong.setVisibility(View.VISIBLE);
                } else {
                    AddStatus = "ada";
                    tv_kosong.setVisibility(View.GONE);
                }
            }
        }.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_pdf:
                Toast.makeText(this, "Cetak PDF..", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.home:
                finish();

            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        new AsyncTask<Void, Void, List>() {
            @Override
            protected List<KarcisModel> doInBackground(Void... voids) {
                List<KarcisModel> readKarcisAll = karcisDB.karcisDAO().readAllKarcis();
                return readKarcisAll;
            }

            @Override
            protected void onPostExecute(List list) {
                super.onPostExecute(list);
                adapter = new KarcisAdapter(KarcisActivity.this,list);
                rv_karcis.setAdapter(adapter);
                if (list.isEmpty()) {
                    AddStatus = "kosong";
                    tv_kosong.setVisibility(View.VISIBLE);
                } else {
                    AddStatus = "ada";
                    tv_kosong.setVisibility(View.GONE);
                }
            }
        }.execute();


    }
}
