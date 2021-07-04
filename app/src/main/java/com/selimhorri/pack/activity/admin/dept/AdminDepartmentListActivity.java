package com.selimhorri.pack.activity.admin.dept;

import android.content.Intent;
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
import com.selimhorri.pack.activity.admin.AdminInfoActivity;
import com.selimhorri.pack.activity.admin.emp.AdminEmployeeAddActivity;
import com.selimhorri.pack.activity.admin.loc.AdminLocationListActivity;
import com.selimhorri.pack.pattern.adapter.admin.AdminDepartmentListCustomAdapter;
import com.selimhorri.pack.service.DepartmentService;
import com.selimhorri.pack.service.LocationService;
import com.selimhorri.pack.service.impl.DepartmentServiceImpl;

public class AdminDepartmentListActivity extends AppCompatActivity {

    private final DepartmentService departmentService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public AdminDepartmentListActivity() {
        this.departmentService = new DepartmentServiceImpl(AdminDepartmentListActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list_departments);

        this.initialize();

        this.departmentService.findAll(
                response ->
                        this.recyclerView.setAdapter(
                                new AdminDepartmentListCustomAdapter(response.getCollection(), AdminDepartmentListActivity.this)
                        ),
                error -> Toast.makeText(AdminDepartmentListActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
        );

    }

    private void initialize() {
        this.recyclerView = super.findViewById(R.id.recyclerViewAdminListDepartments);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(AdminDepartmentListActivity.this));
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
                super.startActivity(new Intent(AdminDepartmentListActivity.this, AdminInfoActivity.class));
                return true;
            case R.id.adminNewDepartment:
                super.startActivity(new Intent(AdminDepartmentListActivity.this, AdminDepartmentAddActivity.class));
                return true;
            case R.id.adminAbout:
                // super.startActivity(new Intent(AdminDepartmentAddActivity.this, AdminInfoActivity.class));
                return true;
            case R.id.adminLogout:
                super.getSharedPreferences("admin", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(AdminDepartmentListActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}