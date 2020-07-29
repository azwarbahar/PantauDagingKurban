package com.example.pantaudagingkurban;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText tv_email, tv_password, tv_nama;
    private Button btn_regist;
    private TextView tv_login;


    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        tv_nama = findViewById(R.id.tv_nama);
        tv_email = findViewById(R.id.tv_emil);
        tv_password = findViewById(R.id.tv_password);
        btn_regist = findViewById(R.id.btn_regist);
        tv_login = findViewById(R.id.tv_login);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        }

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nama = tv_nama.getText().toString().trim();
                String email = tv_email.getText().toString().trim();
                String pass = tv_password.getText().toString().trim();

                if (TextUtils.isEmpty(nama)){
                    tv_nama.setError("Tidak Boleh Kosong");
                }

                if (TextUtils.isEmpty(email)){
                    tv_email.setError("Tidak Boleh Kosong");
                }

                if (TextUtils.isEmpty(pass)){
                    tv_password.setError("Tidak Boleh Kosong");
                }

                if (pass.length() < 5){
                    tv_password.setError("Minimal 6 Karakter");
                }

                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(RegisterActivity.this, DashboardActivity.class));
                            finish();
                        } else {
                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });


    }
}
