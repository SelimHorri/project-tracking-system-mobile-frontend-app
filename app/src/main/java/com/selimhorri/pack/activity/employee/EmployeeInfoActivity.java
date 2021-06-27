package com.selimhorri.pack.activity.employee;

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
import com.selimhorri.pack.service.EmployeeService;
import com.selimhorri.pack.service.impl.EmployeeServiceImpl;

public class EmployeeInfoActivity extends AppCompatActivity {

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

    public EmployeeInfoActivity() {
        this.employeeService = new EmployeeServiceImpl(EmployeeInfoActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_info);

        this.textViewFullName = super.findViewById(R.id.textViewEmployeeAccountInfoFullName);
        this.textViewJob = super.findViewById(R.id.textViewEmployeeAccountInfoJob);
        this.textViewUsername = super.findViewById(R.id.textViewEmployeeAccountInfoUsername);
        this.textViewIsActive = super.findViewById(R.id.textViewEmployeeAccountInfoIsActive);
        this.textViewEmail = super.findViewById(R.id.textViewEmployeeAccountInfoEmail);
        this.textViewDepartment = super.findViewById(R.id.textViewEmployeeAccountInfoDepartment);
        this.textViewPhone = super.findViewById(R.id.textViewEmployeeAccountInfoPhone);
        this.textViewHiredate = super.findViewById(R.id.textViewEmployeeAccountInfoHiredate);
        this.textViewSalary = super.findViewById(R.id.textViewEmployeeAccountInfoSalary);
        this.btnEditCredentials = super.findViewById(R.id.buttonEmployeeAccountInfoEditCredentials);

        final SharedPreferences sp = super.getSharedPreferences("emp", MODE_PRIVATE);
        final String username = sp.getString("username", null);

        this.employeeService.findByUsername(
                username,
                response -> {
                    this.textViewFullName.setText(response.getFirstName() + " " + response.getLastName());
                    this.textViewJob.setText(response.getJob());
                    this.textViewUsername.setText(response.getCredential().getUsername());
                    this.textViewIsActive.setText(String.valueOf(response.getCredential().getEnabled()));
                    this.textViewEmail.setText(response.getEmail());
                    this.textViewDepartment.setText(response.getDepartment().getDepartmentName());
                    this.textViewPhone.setText(response.getPhone());
                    this.textViewHiredate.setText(response.getHiredate().toString());
                    this.textViewSalary.setText(String.valueOf(response.getSalary()));
                },
                error -> Toast.makeText(EmployeeInfoActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
        );

        this.btnEditCredentials.setOnClickListener(v -> {
            super.startActivity(new Intent(EmployeeInfoActivity.this, EmployeeIndexActivity.class));
        });

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
                super.startActivity(new Intent(EmployeeInfoActivity.this, EmployeeInfoActivity.class));
                return true;
            case R.id.employeeTeam:
                super.startActivity(new Intent(EmployeeInfoActivity.this, EmployeeTeamActivity.class));
                return true;
            case R.id.employeeProjects:
                super.startActivity(new Intent(EmployeeInfoActivity.this, EmployeeIndexActivity.class));
                return true;
            case R.id.employeeLogout:
                super.getSharedPreferences("emp", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(EmployeeInfoActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}