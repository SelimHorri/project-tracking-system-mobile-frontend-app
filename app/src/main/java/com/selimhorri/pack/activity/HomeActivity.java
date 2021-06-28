package com.selimhorri.pack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.selimhorri.pack.R;
import com.selimhorri.pack.constant.RoleEnum;

public class HomeActivity extends AppCompatActivity {

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

        this.btnEmployee.setOnClickListener(v -> super.startActivity(new Intent(this, LoginActivity.class).putExtra("role", RoleEnum.ROLE_EMP.toString())));
        this.btnManager.setOnClickListener(v -> super.startActivity(new Intent(this, LoginActivity.class).putExtra("role", RoleEnum.ROLE_MGR.toString())));
        this.btnAdmin.setOnClickListener(v -> super.startActivity(new Intent(this, LoginActivity.class).putExtra("role", RoleEnum.ROLE_ADMIN.toString())));

    }



}




