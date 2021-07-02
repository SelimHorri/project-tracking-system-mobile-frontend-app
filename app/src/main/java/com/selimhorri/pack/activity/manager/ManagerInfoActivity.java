package com.selimhorri.pack.activity.manager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.selimhorri.pack.R;
import com.selimhorri.pack.activity.HomeActivity;
import com.selimhorri.pack.activity.employee.EmployeeEditCredentialsActivity;
import com.selimhorri.pack.service.EmployeeService;
import com.selimhorri.pack.service.impl.EmployeeServiceImpl;

public class ManagerInfoActivity extends AppCompatActivity {

    private final EmployeeService employeeService;
    private TextView textViewFullName;
    private TextView textViewJob;
    private TextView textViewUsername;
    private TextView textViewIsActive;
    private TextView textViewEmail;
    private TextView textViewDepartment;
    private TextView textViewPhone;
    private TextView textViewHiredate;
    private TextView textViewSalary;
    private Button btnEditCredentials;

    public ManagerInfoActivity() {
        this.employeeService = new EmployeeServiceImpl(ManagerInfoActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_info);

        this.textViewFullName = super.findViewById(R.id.textViewManagerInfoFullName);
        this.textViewJob = super.findViewById(R.id.textViewManagerInfoJob);
        this.textViewUsername = super.findViewById(R.id.textViewManagerInfoUsername);
        this.textViewIsActive = super.findViewById(R.id.textViewManagerInfoIsActive);
        this.textViewEmail = super.findViewById(R.id.textViewManagerInfoEmail);
        this.textViewDepartment = super.findViewById(R.id.textViewManagerInfoDepartment);
        this.textViewPhone = super.findViewById(R.id.textViewManagerInfoPhone);
        this.textViewHiredate = super.findViewById(R.id.textViewManagerInfoHiredate);
        this.textViewSalary = super.findViewById(R.id.textViewManagerInfoSalary);
        this.btnEditCredentials = super.findViewById(R.id.buttonManagerInfoEditCredentials);

        final SharedPreferences sp = super.getSharedPreferences("mgr", MODE_PRIVATE);
        final String username = sp.getString("username", null);

        this.employeeService.findByUsername(
                username,
                employee -> {
                    this.textViewFullName.setText(employee.getFirstName() + " " + employee.getLastName());
                    this.textViewJob.setText(employee.getJob());
                    this.textViewUsername.setText(employee.getCredential().getUsername());
                    this.textViewIsActive.setText(String.valueOf(employee.getCredential().getEnabled()));
                    this.textViewEmail.setText(employee.getEmail());
                    this.textViewDepartment.setText(employee.getDepartment().getDepartmentName());
                    this.textViewPhone.setText(employee.getPhone());
                    this.textViewHiredate.setText(employee.getHiredate());
                    this.textViewSalary.setText(String.valueOf(employee.getSalary()));
                },
                error -> Toast.makeText(ManagerInfoActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
        );

        this.btnEditCredentials.setOnClickListener(v -> {
            super.startActivity(new Intent(ManagerInfoActivity.this, EmployeeEditCredentialsActivity.class));
        });

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
                super.startActivity(new Intent(ManagerInfoActivity.this, ManagerInfoActivity.class));
                return true;
            case R.id.managerTeam:
                super.startActivity(new Intent(ManagerInfoActivity.this, ManagerTeamActivity.class));
                return true;
            case R.id.managerAddProject:
                super.startActivity(new Intent(ManagerInfoActivity.this, ManagerAddProjectActivity.class));
                return true;
            case R.id.managerProjects:
                super.startActivity(new Intent(ManagerInfoActivity.this, ManagerIndexActivity.class));
                return true;
            case R.id.managerLogout:
                super.getSharedPreferences("mgr", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(ManagerInfoActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}