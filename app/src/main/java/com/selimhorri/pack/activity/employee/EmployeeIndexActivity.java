package com.selimhorri.pack.activity.employee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.selimhorri.pack.R;
import com.selimhorri.pack.activity.HomeActivity;
import com.selimhorri.pack.pattern.adapter.EmployeeIndexCustomAdapter;
import com.selimhorri.pack.service.EmployeeService;
import com.selimhorri.pack.service.impl.EmployeeServiceImpl;

public class EmployeeIndexActivity extends AppCompatActivity {

    private final EmployeeService employeeService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public EmployeeIndexActivity() {
        this.employeeService = new EmployeeServiceImpl(EmployeeIndexActivity.this);
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
                                            new EmployeeIndexCustomAdapter(
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
                super.startActivity(new Intent(EmployeeIndexActivity.this, EmployeeTeamActivity.class));
                return true;
            case R.id.employeeProjects:
                super.startActivity(new Intent(EmployeeIndexActivity.this, EmployeeIndexActivity.class));
                return true;
            case R.id.employeeLogout:
                super.getSharedPreferences("emp", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(EmployeeIndexActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}