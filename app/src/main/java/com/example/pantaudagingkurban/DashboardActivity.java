package com.example.pantaudagingkurban;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity {

    private CardView cv_logout;
    private CardView cv_buat;
    private CardView cv_list;
    private ImageView img_qr_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        cv_logout = findViewById(R.id.cv_logout);
        cv_buat = findViewById(R.id.cv_buat);
        cv_list = findViewById(R.id.cv_list);
        img_qr_btn = findViewById(R.id.img_qr_btn);

        cv_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, KarcisActivity.class));
            }
        });

        img_qr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, ScanQRCodeActivity.class));

            }
        });

        cv_buat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, TempatActivity.class));
            }
        });

        cv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}
