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
import com.selimhorri.pack.pattern.adapter.manager.ManagerShowCommitsCustomAdapter;
import com.selimhorri.pack.service.AssignmentService;
import com.selimhorri.pack.service.EmployeeService;
import com.selimhorri.pack.service.impl.AssignmentServiceImpl;
import com.selimhorri.pack.service.impl.EmployeeServiceImpl;

public class ManagerShowCommitsActivity extends AppCompatActivity {

    private final EmployeeService employeeService;
    private final AssignmentService assignmentService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public ManagerShowCommitsActivity() {
        this.employeeService = new EmployeeServiceImpl(ManagerShowCommitsActivity.this);
        this.assignmentService = new AssignmentServiceImpl(ManagerShowCommitsActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_show_commits);

        this.recyclerView = super.findViewById(R.id.recyclerViewManagerShowCommits);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(ManagerShowCommitsActivity.this));

        final Bundle extras = super.getIntent().getExtras();
        final Integer projectId = extras.getInt("projectId");

        final SharedPreferences sp = super.getSharedPreferences("mgr", MODE_PRIVATE);
        final String username = sp.getString("username", null);

        this.employeeService.findByUsername(
                username,
                response -> {
                    this.assignmentService.findByProjectId(
                            projectId,
                            list -> {
                                this.recyclerView.setAdapter(
                                        new ManagerShowCommitsCustomAdapter(
                                                ManagerShowCommitsActivity.this,
                                                list.getCollection()
                                        )
                                );
                            },
                            errorList -> Toast.makeText(ManagerShowCommitsActivity.this, errorList.toString(), Toast.LENGTH_SHORT).show()
                    );
                },
                error -> Toast.makeText(ManagerShowCommitsActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
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
                super.startActivity(new Intent(ManagerShowCommitsActivity.this, ManagerInfoActivity.class));
                return true;
            case R.id.managerTeam:
                super.startActivity(new Intent(ManagerShowCommitsActivity.this, ManagerTeamActivity.class));
                return true;
            case R.id.managerProjects:
                super.startActivity(new Intent(ManagerShowCommitsActivity.this, ManagerIndexActivity.class));
                return true;
            case R.id.managerLogout:
                super.getSharedPreferences("mgr", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(ManagerShowCommitsActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}