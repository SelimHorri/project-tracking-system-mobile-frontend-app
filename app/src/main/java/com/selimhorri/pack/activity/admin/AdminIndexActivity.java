package com.selimhorri.pack.activity.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.selimhorri.pack.R;
import com.selimhorri.pack.activity.HomeActivity;
import com.selimhorri.pack.activity.admin.dept.AdminDepartmentListActivity;
import com.selimhorri.pack.activity.admin.emp.AdminEmployeeListActivity;
import com.selimhorri.pack.activity.admin.loc.AdminLocationListActivity;

public class AdminIndexActivity extends AppCompatActivity {

    private Button btnManageEmployees;
    private Button btnManageDepartments;
    private Button btnManageLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_index);

        this.initialize();

        this.btnManageEmployees.setOnClickListener(v -> super.startActivity(new Intent(AdminIndexActivity.this, AdminEmployeeListActivity.class)));
        this.btnManageDepartments.setOnClickListener(v -> super.startActivity(new Intent(AdminIndexActivity.this, AdminDepartmentListActivity.class)));
        this.btnManageLocations.setOnClickListener(v -> super.startActivity(new Intent(AdminIndexActivity.this, AdminLocationListActivity.class)));

    }

    private void initialize() {
        this.btnManageEmployees = super.findViewById(R.id.buttonAdminIndexManageEmployees);
        this.btnManageDepartments = super.findViewById(R.id.buttonAdminIndexManageDepartments);
        this.btnManageLocations = super.findViewById(R.id.buttonAdminIndexManageLocations);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.getMenuInflater()
                .inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.adminAccountInfo:
                super.startActivity(new Intent(AdminIndexActivity.this, AdminInfoActivity.class));
                return true;
            case R.id.adminLogout:
                super.getSharedPreferences("admin", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(AdminIndexActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}