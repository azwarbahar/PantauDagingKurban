package com.example.pantaudagingkurban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplasActivity extends AppCompatActivity {

    private int waktu_loading=4500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splas);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //setelah loading maka akan langsung berpindah ke home activity
                Intent home=new Intent(SplasActivity.this, LoginActivity.class);
                startActivity(home);
                finish();

            }
        },waktu_loading);

    }
}
