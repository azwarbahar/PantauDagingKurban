package com.example.pantaudagingkurban;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pantaudagingkurban.adapter.TempatAdapter;
import com.example.pantaudagingkurban.database.KarcisDB;
import com.example.pantaudagingkurban.database.TempatDB;
import com.example.pantaudagingkurban.model.TempatModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TempatActivity extends AppCompatActivity {

    private TempatDB tempatDB;
    private RecyclerView rv_tempat;
    private TempatAdapter adapter;
    private ArrayList<TempatModel> tempatModels;
    private FloatingActionButton fb_tambah;
    private String AddStatus = "";
    private TextView tv_kosong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tempat);
        tv_kosong = findViewById(R.id.tv_kosong);
        tv_kosong.setVisibility(View.VISIBLE);
        tempatDB = Room.databaseBuilder(getApplicationContext(), TempatDB.class, "tempat_db").build();
        rv_tempat = findViewById(R.id.rv_tempat);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv_tempat.setLayoutManager(layoutManager);

        fb_tambah = findViewById(R.id.fb_tambah);

        fb_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AddStatus.equals("kosong")){
                    startActivity(new Intent(TempatActivity.this,FormKarcisActivity.class));
                } else if (AddStatus.equals("ada")){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(TempatActivity.this);
                    dialog.setMessage("Untuk Sementara anda hanya bisa membuat karcis untuk satu tempat saja..");
                    dialog.setTitle("Mohon Maaf");
                    dialog.setCancelable(true);
                    dialog.show();
                }
            }
        });

        tempatModels = new ArrayList<>();

        new AsyncTask<Void, Void, List>() {
            @Override
            protected List doInBackground(Void... voids) {
                List<TempatModel> readtempat = tempatDB.tempatDAO().readAllTempat();
                return readtempat;
            }

            @Override
            protected void onPostExecute(List list) {
                super.onPostExecute(list);

                adapter = new TempatAdapter(TempatActivity.this,list);
                rv_tempat.setAdapter(adapter);
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
    protected void onResume() {
        super.onResume();

        new AsyncTask<Void, Void, List>() {
            @Override
            protected List doInBackground(Void... voids) {
                List<TempatModel> readtempat = tempatDB.tempatDAO().readAllTempat();
                return readtempat;
            }

            @Override
            protected void onPostExecute(List list) {
                super.onPostExecute(list);

                adapter = new TempatAdapter(TempatActivity.this,list);
                rv_tempat.setAdapter(adapter);
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
