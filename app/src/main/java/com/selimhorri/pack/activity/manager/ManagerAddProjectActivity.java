package com.selimhorri.pack.activity.manager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.selimhorri.pack.R;
import com.selimhorri.pack.activity.HomeActivity;
import com.selimhorri.pack.activity.employee.EmployeeTeamActivity;
import com.selimhorri.pack.pattern.adapter.manager.ManagerAddProjectCustomAdapter;
import com.selimhorri.pack.service.EmployeeService;
import com.selimhorri.pack.service.impl.EmployeeServiceImpl;

import java.util.List;

public class ManagerAddProjectActivity extends AppCompatActivity {

    private final EmployeeService employeeService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private EditText editTextTitle;
    private EditText editTextStartDate;
    private EditText editTextEndDate;
    private EditText editTextStatus;
    private Button btnAddProject;

    public ManagerAddProjectActivity() {
        this.employeeService = new EmployeeServiceImpl(ManagerAddProjectActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_add_project);

        this.initialize();

        final SharedPreferences sp = super.getSharedPreferences("mgr", MODE_PRIVATE);
        final String username = sp.getString("username", null);

        this.employeeService.findByUsername(
                username,
                response -> {
                    this.employeeService.findByDepartmentId(
                            response.getDepartment().getDepartmentId(),
                            employees -> {
                                new ManagerAddProjectCustomAdapter(
                                        employees.getCollection(),
                                        ManagerAddProjectActivity.this
                                );
                            },
                            error -> Toast.makeText(ManagerAddProjectActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
                    );
                },
                error -> Toast.makeText(ManagerAddProjectActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
        );

    }

    private void initialize() {
        this.recyclerView = super.findViewById(R.id.recyclerViewManagerAddProjectAssignTo);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(ManagerAddProjectActivity.this));
        this.editTextTitle = super.findViewById(R.id.editTextManagerAddProjectTitle);
        this.editTextStartDate = super.findViewById(R.id.editTextManagerAddProjectStartDate);
        this.editTextEndDate = super.findViewById(R.id.editTextManagerAddProjectEndDate);
        this.editTextStatus = super.findViewById(R.id.editTextManagerAddProjectStatus);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.getMenuInflater()
                .inflate(R.menu.menu_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.managerAccountInfo:
                super.startActivity(new Intent(ManagerAddProjectActivity.this, ManagerInfoActivity.class));
                return true;
            case R.id.managerTeam:
                super.startActivity(new Intent(ManagerAddProjectActivity.this, ManagerTeamActivity.class));
                return true;
            case R.id.managerAddProject:
                super.startActivity(new Intent(ManagerAddProjectActivity.this, ManagerAddProjectActivity.class));
                return true;
            case R.id.managerProjects:
                super.startActivity(new Intent(ManagerAddProjectActivity.this, ManagerIndexActivity.class));
                return true;
            case R.id.managerLogout:
                super.getSharedPreferences("mgr", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(ManagerAddProjectActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}