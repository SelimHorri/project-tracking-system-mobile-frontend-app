package com.selimhorri.pack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.selimhorri.pack.service.AuthenticationService;
import com.selimhorri.pack.service.impl.dynmc.AuthenticationServiceDynamicImpl;

public class LoginActivity extends AppCompatActivity {

    private final AuthenticationService authenticationService;

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button btnLogin;

    public LoginActivity() {
        this.authenticationService = new AuthenticationServiceDynamicImpl(LoginActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.editTextUsername = super.findViewById(R.id.idEditTextUsername);
        this.editTextPassword = super.findViewById(R.id.idEditTextPassword);
        this.btnLogin = super.findViewById(R.id.idBtnLogin);

        final Bundle extras = super.getIntent().getExtras();



    }



}










