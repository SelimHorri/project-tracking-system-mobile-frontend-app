package com.selimhorri.pack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.selimhorri.pack.constant.AccountEnum;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Credential;
import com.selimhorri.pack.model.dto.Department;
import com.selimhorri.pack.model.dto.custom.AuthenticationRequest;
import com.selimhorri.pack.service.AuthenticationService;
import com.selimhorri.pack.service.CredentialService;
import com.selimhorri.pack.service.DepartmentService;
import com.selimhorri.pack.service.impl.dynmc.AuthenticationServiceImpl;
import com.selimhorri.pack.service.impl.dynmc.CredentialServiceDynamicImpl;
import com.selimhorri.pack.service.impl.dynmc.DepartmentServiceDynamicImpl;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class HomeActivity extends AppCompatActivity {

    private final CredentialService credentialService;
    private final AuthenticationService authenticationService;

    public HomeActivity() {
        credentialService = new CredentialServiceDynamicImpl(HomeActivity.this);
        authenticationService = new AuthenticationServiceImpl(HomeActivity.this);
    }

    private Button btnEmployee;
    private Button btnManager;
    private Button btnAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.btnEmployee = super.findViewById(R.id.idBtnEmployee);
        this.btnManager = super.findViewById(R.id.idBtnManager);
        this.btnAdmin = super.findViewById(R.id.idBtnAdmin);

        this.btnEmployee.setOnClickListener(v -> super.startActivity(new Intent(this, LoginActivity.class).putExtra("account", AccountEnum.EMPLOYEE.toString())));
        this.btnManager.setOnClickListener(v -> super.startActivity(new Intent(this, LoginActivity.class).putExtra("account", AccountEnum.MANAGER.toString())));
        this.btnAdmin.setOnClickListener(v -> super.startActivity(new Intent(this, LoginActivity.class).putExtra("account", AccountEnum.ADMIN.toString())));

        this.btnEmployee.setOnClickListener(v -> {
            authenticationService.authenticate(
                    new AuthenticationRequest("selimhorri", "0000"),
                    response -> {
                        Toast.makeText(HomeActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    },
                    error -> {
                        Toast.makeText(HomeActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
            );
        });

        this.btnManager.setOnClickListener(v -> {
            credentialService.findById(
                    5,
                    response -> {
                        Toast.makeText(HomeActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                    },
                    error -> {
                        Toast.makeText(HomeActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
            );
        });

    }



}




