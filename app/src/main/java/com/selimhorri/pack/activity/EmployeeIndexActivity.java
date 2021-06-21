package com.selimhorri.pack.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.selimhorri.pack.R;
import com.selimhorri.pack.service.AssignmentService;
import com.selimhorri.pack.service.CredentialService;
import com.selimhorri.pack.service.EmployeeService;
import com.selimhorri.pack.service.impl.dynmc.AssignmentServiceDynamicImpl;
import com.selimhorri.pack.service.impl.dynmc.CredentialServiceDynamicImpl;
import com.selimhorri.pack.service.impl.dynmc.EmployeeServiceDynamicImpl;

public class EmployeeIndexActivity extends AppCompatActivity {

    private final EmployeeService employeeService;
    private TextView textView;

    public EmployeeIndexActivity() {
        this.employeeService = new EmployeeServiceDynamicImpl(EmployeeIndexActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_index);

        this.textView = super.findViewById(R.id.textView4);

        final SharedPreferences sp = super.getSharedPreferences("emp", MODE_PRIVATE);
        this.employeeService.findByUsername(
                sp.getString("username", null),
                response -> {

                    this.employeeService.findByEmployeeId(
                            response.getEmployeeId(),
                            listOfEmployeeProjectData -> {

                                final StringBuilder sb = new StringBuilder("");
                                listOfEmployeeProjectData.getCollection().forEach(sb::append);
                                this.textView.setText(sb.toString());

                            },
                            errorOfEmployeeProjectData -> Toast.makeText(EmployeeIndexActivity.this, errorOfEmployeeProjectData.toString(), Toast.LENGTH_SHORT).show()
                    );

                },
                error -> Toast.makeText(EmployeeIndexActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
        );

    }
}