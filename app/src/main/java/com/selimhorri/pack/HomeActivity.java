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
import com.selimhorri.pack.model.dto.Department;
import com.selimhorri.pack.service.DepartmentService;
import com.selimhorri.pack.service.impl.dynmc.DepartmentServiceDynamicImpl;

import java.util.concurrent.atomic.AtomicReference;

public class HomeActivity extends AppCompatActivity {

    private final DepartmentService departmentService;

    public HomeActivity() {
        departmentService = new DepartmentServiceDynamicImpl(HomeActivity.this);
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

    }



}




