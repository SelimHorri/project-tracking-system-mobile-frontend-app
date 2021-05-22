package com.selimhorri.pack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.btnLogin = super.findViewById(R.id.idBtnLogin);

        final Bundle extras = super.getIntent().getExtras();

        this.btnLogin.setOnClickListener(v -> Toast.makeText(LoginActivity.this, "HELLO " + extras.getString("account"), Toast.LENGTH_LONG).show());

    }
}