package com.selimhorri.pack.activity.manager;

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
import com.selimhorri.pack.pattern.adapter.manager.ManagerIndexCustomAdapter;
import com.selimhorri.pack.service.EmployeeService;
import com.selimhorri.pack.service.impl.EmployeeServiceImpl;

public class ManagerIndexActivity extends AppCompatActivity {

    private final EmployeeService employeeService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public ManagerIndexActivity() {
        this.employeeService = new EmployeeServiceImpl(ManagerIndexActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_index);

        this.recyclerView = super.findViewById(R.id.recyclerViewManagerProjectData);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(ManagerIndexActivity.this));

        final SharedPreferences sp = super.getSharedPreferences("mgr", MODE_PRIVATE);
        final String username = sp.getString("username", null);

        this.employeeService.findByUsername(
                username,
                response ->
                    this.employeeService.findAllManagerProjectDataByEmployeeId(
                            response.getEmployeeId(),
                            list ->
                                this.recyclerView.setAdapter(
                                        new ManagerIndexCustomAdapter(
                                                list.getCollection(),
                                                ManagerIndexActivity.this
                                        )
                                ),
                            errorOfManagerProjectData -> Toast.makeText(ManagerIndexActivity.this, errorOfManagerProjectData.toString(), Toast.LENGTH_SHORT).show()
                    ),
                error -> Toast.makeText(ManagerIndexActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
        );

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
                super.startActivity(new Intent(ManagerIndexActivity.this, ManagerInfoActivity.class));
                return true;
            case R.id.managerTeam:
                return true;
            case R.id.managerProjects:
                super.startActivity(new Intent(ManagerIndexActivity.this, ManagerIndexActivity.class));
                return true;
            case R.id.managerLogout:
                super.getSharedPreferences("mgr", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(ManagerIndexActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}