package com.selimhorri.pack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.selimhorri.pack.model.dto.Department;
import com.selimhorri.pack.model.dto.Location;
import com.selimhorri.pack.service.DepartmentService;
import com.selimhorri.pack.service.impl.dynmc.DepartmentServiceDynamicImpl;
import com.selimhorri.pack.service.impl.sttc.DepartmentServiceStaticImpl;

public class MainActivity extends AppCompatActivity {

    private DepartmentService departmentService;

    public MainActivity() {
        departmentService = new DepartmentServiceStaticImpl(MainActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }



}