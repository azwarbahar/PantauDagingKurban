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

public class LoginActivity extends AppCompatActivity {

    private EditText tv_email, tv_password;
    private Button btn_login;
    private TextView register;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tv_email = findViewById(R.id.tv_emil);
        tv_password = findViewById(R.id.tv_password);
        btn_login = findViewById(R.id.btn_login);
        register = findViewById(R.id.register);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            finish();
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = tv_email.getText().toString().trim();
                String pass = tv_password.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    tv_email.setError("Tidak Boleh Kosong");
                }

                if (TextUtils.isEmpty(pass)){
                    tv_password.setError("Tidak Boleh Kosong");
                }

                if (pass.length() < 5){
                    tv_password.setError("Minimal 6 Karakter");
                }

                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Username dan Password Tidak Sesuai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

    }
}
