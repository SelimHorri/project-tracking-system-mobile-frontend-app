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
import com.selimhorri.pack.service.EmployeeService;
import com.selimhorri.pack.service.impl.dynmc.EmployeeServiceDynamicImpl;

public class EmployeeIndexActivity extends AppCompatActivity {

    private final EmployeeService employeeService;

    public EmployeeIndexActivity() {
        this.employeeService = new EmployeeServiceDynamicImpl(EmployeeIndexActivity.this);
    }

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_index);

        this.textView = super.findViewById(R.id.textView);
        this.button = super.findViewById(R.id.button);

        this.employeeService.findByEmployeeId(
                1,
                response -> {
                    this.textView.setText(response.getCollection().toString());
                },
                error -> {
                    Toast.makeText(EmployeeIndexActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
        );

        this.button.setOnClickListener(v -> {
            SharedPreferences.Editor editor = super.getSharedPreferences("emp", Context.MODE_PRIVATE).edit();
            editor.clear();
            editor.apply();
            super.startActivity(new Intent(EmployeeIndexActivity.this, HomeActivity.class));
        });

    }
}