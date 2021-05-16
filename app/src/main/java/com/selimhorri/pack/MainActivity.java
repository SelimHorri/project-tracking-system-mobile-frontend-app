package com.selimhorri.pack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.selimhorri.pack.service.DepartmentService;
import com.selimhorri.pack.service.impl.dynmc.DepartmentServiceDynamicImpl;
import com.selimhorri.pack.service.impl.sttc.DepartmentServiceStaticImpl;

public class MainActivity extends AppCompatActivity {

    private DepartmentService departmentService;

    private TextView textView;
    private Button btnDepartments;
    private Button btnDepartmentById;

    public MainActivity() {
        departmentService = new DepartmentServiceStaticImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.textView = super.findViewById(R.id.textView);
        this.btnDepartments = findViewById(R.id.btnGetDepartmentsId);
        this.btnDepartmentById = findViewById(R.id.btnGetDepartmentById);

        this.btnDepartments.setOnClickListener(v -> this.textView.setText(this.departmentService.findAll().getCollection().toString()));
        this.btnDepartmentById.setOnClickListener(v -> this.textView.setText(this.departmentService.findById(1).toString()));

    }



}