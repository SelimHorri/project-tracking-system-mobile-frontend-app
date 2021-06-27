package com.selimhorri.pack.activity.employee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.selimhorri.pack.R;
import com.selimhorri.pack.activity.HomeActivity;
import com.selimhorri.pack.service.EmployeeService;
import com.selimhorri.pack.service.impl.dynmc.EmployeeServiceDynamicImpl;

public class EmployeeInfoActivity extends AppCompatActivity {

    private final EmployeeService employeeService;
    private TextView textView;
    private Button btnShowProjects;
    private Button btnEditCredentials;
    private Button btnIndex;
    private Button btnLogout;

    public EmployeeInfoActivity() {
        this.employeeService = new EmployeeServiceDynamicImpl(EmployeeInfoActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_info);

        this.textView = super.findViewById(R.id.textView5);
        this.btnShowProjects = super.findViewById(R.id.button10);
        this.btnEditCredentials = super.findViewById(R.id.button9);
        this.btnIndex = super.findViewById(R.id.button11);
        this.btnLogout = super.findViewById(R.id.button12);

        final SharedPreferences sp = super.getSharedPreferences("emp", MODE_PRIVATE);
        final String username = sp.getString("username", null);

        this.employeeService.findByUsername(
                username,
                response -> {
                    this.textView.setText(response.toString());
                },
                error -> Toast.makeText(EmployeeInfoActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
        );

        // logout
        this.btnLogout.setOnClickListener(v -> {
            sp.edit().clear().apply();
            super.startActivity(new Intent(EmployeeInfoActivity.this, HomeActivity.class));
        });

        // go index
        this.btnIndex.setOnClickListener(v -> {
            super.startActivity(new Intent(EmployeeInfoActivity.this, EmployeeIndexActivity.class));
        });
    }
}