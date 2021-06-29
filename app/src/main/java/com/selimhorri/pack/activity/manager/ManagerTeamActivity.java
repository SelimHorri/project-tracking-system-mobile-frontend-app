package com.selimhorri.pack.activity.manager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.selimhorri.pack.R;
import com.selimhorri.pack.activity.HomeActivity;
import com.selimhorri.pack.pattern.adapter.manager.ManagerTeamCustomAdapter;
import com.selimhorri.pack.service.EmployeeService;
import com.selimhorri.pack.service.impl.EmployeeServiceImpl;

public class ManagerTeamActivity extends AppCompatActivity {

    private final EmployeeService employeeService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public ManagerTeamActivity() {
        this.employeeService = new EmployeeServiceImpl(ManagerTeamActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_team);

        this.recyclerView = super.findViewById(R.id.recyclerViewManagerTeam);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(ManagerTeamActivity.this));

        final SharedPreferences sp = super.getSharedPreferences("mgr", MODE_PRIVATE);
        final String username = sp.getString("username", null);

        this.employeeService.findByUsername(
                username,
                response ->
                    this.employeeService.findByDepartmentId(
                            response.getDepartment().getDepartmentId(),
                            list -> {
                                this.recyclerView.setAdapter(
                                        new ManagerTeamCustomAdapter(list.getCollection(), ManagerTeamActivity.this)
                                );
                            },
                            errorList -> Toast.makeText(ManagerTeamActivity.this, errorList.toString(), Toast.LENGTH_SHORT).show()
                    ),
                error -> Toast.makeText(ManagerTeamActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
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
                super.startActivity(new Intent(ManagerTeamActivity.this, ManagerInfoActivity.class));
                return true;
            case R.id.managerTeam:
                super.startActivity(new Intent(ManagerTeamActivity.this, ManagerTeamActivity.class));
                return true;
            case R.id.managerProjects:
                super.startActivity(new Intent(ManagerTeamActivity.this, ManagerIndexActivity.class));
                return true;
            case R.id.managerLogout:
                super.getSharedPreferences("mgr", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(ManagerTeamActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}