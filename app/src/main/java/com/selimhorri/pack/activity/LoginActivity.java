package com.selimhorri.pack.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.selimhorri.pack.R;
import com.selimhorri.pack.model.dto.custom.AuthenticationRequest;
import com.selimhorri.pack.service.AuthenticationService;
import com.selimhorri.pack.service.CredentialService;
import com.selimhorri.pack.service.impl.dynmc.AuthenticationServiceDynamicImpl;
import com.selimhorri.pack.service.impl.dynmc.CredentialServiceDynamicImpl;

public class LoginActivity extends AppCompatActivity {

    private final AuthenticationService authenticationService;
    private final CredentialService credentialService;

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button btnLogin;
    public LoginActivity() {
        this.authenticationService = new AuthenticationServiceDynamicImpl(LoginActivity.this);
        this.credentialService = new CredentialServiceDynamicImpl(LoginActivity.this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.editTextUsername = super.findViewById(R.id.idEditTextUsername);
        this.editTextPassword = super.findViewById(R.id.idEditTextPassword);
        this.btnLogin = super.findViewById(R.id.idBtnLogin);


        final Bundle extras = super.getIntent().getExtras();


        TextView tv1 = (TextView)findViewById(R.id.topText);
        String message = extras.getString("role");
        if(message.equals("ROLE_EMP")){
            tv1.setText(R.string.hiemp);
        }
        else if (message.equals("ROLE_MGR")) {
            tv1.setText(R.string.himan);
        }
        else {
            tv1.setText(R.string.hiadm);
        }

        this.btnLogin.setOnClickListener(v -> {
            final String username = this.editTextUsername.getText().toString().trim();
            final String password = this.editTextPassword.getText().toString();

            if (isEmpty(username, password)) {
                Toast.makeText(LoginActivity.this, "username/password is/are empty!", Toast.LENGTH_SHORT).show();
            }
            else {
                // TODO: NOT correctly implemented
                this.authenticationService.authenticate(
                        new AuthenticationRequest(username, password),
                        response -> {

                            if (!response.getEligible())
                                Toast.makeText(LoginActivity.this, "User is Not enabled !", Toast.LENGTH_SHORT).show();
                            else {
                                this.credentialService.findByUsername(
                                        response.getUsername(),
                                        respCredential -> {
                                            // Toast.makeText(LoginActivity.this, respCredential.toString(), Toast.LENGTH_SHORT).show();

                                            SharedPreferences.Editor editor;
                                            switch (respCredential.getRole()) {
                                                case "ROLE_EMP":
                                                    //  TODO: activate sharedpreferences & go to employee-index
                                                    editor = super.getSharedPreferences("emp", Context.MODE_PRIVATE)
                                                            .edit()
                                                            .putString("username", respCredential.getUsername());
                                                    editor.apply();
                                                    super.startActivity(new Intent(LoginActivity.this, EmployeeIndexActivity.class));

                                                    break;
                                                case "ROLE_MGR":
                                                    // TODO: activate sharedpreferences & go to manager-index
                                                    editor = super.getSharedPreferences("mgr", Context.MODE_PRIVATE)
                                                            .edit()
                                                            .putString("username", respCredential.getUsername());
                                                    editor.apply();
                                                    super.startActivity(new Intent(LoginActivity.this, ManagerIndexActivity.class));
                                                    break;
                                                case "ROLE_ADMIN":
                                                    // TODO: activate sharedpreferences & go to admin-index
                                                    editor = super.getSharedPreferences("admin", Context.MODE_PRIVATE)
                                                            .edit()
                                                            .putString("username", respCredential.getUsername());
                                                    editor.apply();
                                                    super.startActivity(new Intent(LoginActivity.this, AdminIndexActivity.class));
                                                    break;
                                                default:
                                                    throw new IllegalStateException("#### ILLEGAL ROLE FOR USER! PROBLEM HAPPENED ####");
                                            }

                                        },
                                        errCredential -> {
                                            Toast.makeText(LoginActivity.this, errCredential.toString(), Toast.LENGTH_SHORT).show();
                                        }
                                );
                            }
                        },
                        error -> {
                            Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                );
            }

        });

    }

    private static boolean isEmpty(final String username, final String password) {
        return username.isEmpty() || password.isEmpty();
    }



}










