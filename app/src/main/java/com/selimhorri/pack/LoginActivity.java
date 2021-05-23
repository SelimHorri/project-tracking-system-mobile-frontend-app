package com.selimhorri.pack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.selimhorri.pack.model.dto.Credential;
import com.selimhorri.pack.service.CredentialService;
import com.selimhorri.pack.service.impl.sttc.CredentialServiceStaticImpl;

import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private final CredentialService credentialService;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button btnLogin;

    public LoginActivity() {
        this.credentialService = new CredentialServiceStaticImpl(LoginActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.editTextUsername = super.findViewById(R.id.idEditTextUsername);
        this.editTextPassword = super.findViewById(R.id.idEditTextPassword);
        this.btnLogin = super.findViewById(R.id.idBtnLogin);

        final Bundle extras = super.getIntent().getExtras();

        // this.btnLogin.setOnClickListener(v -> Toast.makeText(LoginActivity.this, "HELLO " + extras.getString("account"), Toast.LENGTH_LONG).show());
        this.btnLogin.setOnClickListener(v -> {
            final List<Credential> credentials = this.credentialService.findAll().getCollection();
            final String username = this.editTextUsername.getText().toString().trim();
            final String password = this.editTextPassword.getText().toString();
            credentials.forEach(c -> {
                if (username.equals(c.getUsername()) && BCrypt.checkpw(password, c.getPassword())) {
                    Toast.makeText(LoginActivity.this, "Welcome " + username, Toast.LENGTH_SHORT).show();
                }
                Log.e("erroooooooooorr", "USER : " + c.toString());
            });

            // Toast.makeText(LoginActivity.this, username.toUpperCase(), Toast.LENGTH_SHORT).show();
            // Toast.makeText(LoginActivity.this, credentials.toString(), Toast.LENGTH_LONG).show();
        });
    }
}