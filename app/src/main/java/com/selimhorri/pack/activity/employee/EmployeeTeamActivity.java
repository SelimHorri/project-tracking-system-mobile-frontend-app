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
import com.selimhorri.pack.pattern.adapter.EmployeeTeamCustomAdapter;
import com.selimhorri.pack.service.EmployeeService;
import com.selimhorri.pack.service.impl.dynmc.EmployeeServiceDynamicImpl;

public class EmployeeTeamActivity extends AppCompatActivity {

    private final EmployeeService employeeService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public EmployeeTeamActivity() {
        this.employeeService = new EmployeeServiceDynamicImpl(EmployeeTeamActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_team);

        this.recyclerView = super.findViewById(R.id.recyclerViewEmployeeTeam);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(EmployeeTeamActivity.this));

        final SharedPreferences sp = super.getSharedPreferences("emp", MODE_PRIVATE);
        final String username = sp.getString("username", null);

        this.employeeService.findByUsername(
                username,
                response -> {
                    this.employeeService.findByDepartmentId(
                            response.getDepartment().getDepartmentId(),
                            list -> {
                                this.recyclerView.setAdapter(
                                        new EmployeeTeamCustomAdapter(list.getCollection(), EmployeeTeamActivity.this)
                                );
                            },
                            errorList -> Toast.makeText(EmployeeTeamActivity.this, errorList.toString(), Toast.LENGTH_SHORT).show()
                    );
                },
                error -> Toast.makeText(EmployeeTeamActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
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
                super.startActivity(new Intent(EmployeeTeamActivity.this, EmployeeInfoActivity.class));
                return true;
            case R.id.employeeTeam:
                super.startActivity(new Intent(EmployeeTeamActivity.this, EmployeeTeamActivity.class));
                return true;
            case R.id.employeeProjects:
                super.startActivity(new Intent(EmployeeTeamActivity.this, EmployeeIndexActivity.class));
                return true;
            case R.id.employeeLogout:
                super.getSharedPreferences("emp", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(EmployeeTeamActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}