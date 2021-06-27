package com.selimhorri.pack.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.selimhorri.pack.R;
import com.selimhorri.pack.activity.view.CustomAdapter;
import com.selimhorri.pack.service.AssignmentService;
import com.selimhorri.pack.service.CredentialService;
import com.selimhorri.pack.service.EmployeeService;
import com.selimhorri.pack.service.impl.dynmc.AssignmentServiceDynamicImpl;
import com.selimhorri.pack.service.impl.dynmc.CredentialServiceDynamicImpl;
import com.selimhorri.pack.service.impl.dynmc.EmployeeServiceDynamicImpl;

public class EmployeeIndexActivity extends AppCompatActivity {

    private final EmployeeService employeeService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public EmployeeIndexActivity() {
        this.employeeService = new EmployeeServiceDynamicImpl(EmployeeIndexActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_index);

        this.recyclerView = super.findViewById(R.id.recyclerViewEmployeeProjectData);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(EmployeeIndexActivity.this));

        final SharedPreferences sp = super.getSharedPreferences("emp", MODE_PRIVATE);
        final String username = sp.getString("username", null);

        this.employeeService.findByUsername(
                username,
                response -> {
                    this.employeeService.findByEmployeeId(
                            response.getEmployeeId(),
                            listOfEmployeeProjectData ->
                                    this.recyclerView.setAdapter(
                                            new CustomAdapter(
                                                    listOfEmployeeProjectData.getCollection(),
                                                    EmployeeIndexActivity.this
                                            )
                                    ),
                            errorOfEmployeeProjectData -> Toast.makeText(EmployeeIndexActivity.this, errorOfEmployeeProjectData.toString(), Toast.LENGTH_SHORT).show()
                    );
                },
                error -> Toast.makeText(EmployeeIndexActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.getMenuInflater()
                .inflate(R.menu.menu_employee, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.employeeAccountInfo:
                super.startActivity(new Intent(EmployeeIndexActivity.this, EmployeeInfoActivity.class));
                return true;
            case R.id.employeeTeam:
                return true;
            case R.id.employeeProjects:
                super.startActivity(new Intent(EmployeeIndexActivity.this, EmployeeIndexActivity.class));
                return true;
            case R.id.employeeLogout:
                final SharedPreferences sp = super.getSharedPreferences("emp", MODE_PRIVATE);
                sp.edit().clear().apply();
                super.startActivity(new Intent(EmployeeIndexActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}