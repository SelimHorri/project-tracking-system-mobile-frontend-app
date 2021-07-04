package com.selimhorri.pack.activity.manager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.selimhorri.pack.R;
import com.selimhorri.pack.activity.HomeActivity;
import com.selimhorri.pack.model.dto.Project;
import com.selimhorri.pack.pattern.builder.ProjectBuilder;
import com.selimhorri.pack.service.EmployeeService;
import com.selimhorri.pack.service.ProjectService;
import com.selimhorri.pack.service.impl.EmployeeServiceImpl;
import com.selimhorri.pack.service.impl.ProjectServiceImpl;

public class ManagerEditProjectActivity extends AppCompatActivity {

    private final EmployeeService employeeService;
    private final ProjectService projectService;
    private EditText editTextTitle;
    private EditText editTextStartDate;
    private EditText editTextEndDate;
    private EditText editTextStatus;
    private Button btnEditProject;

    public ManagerEditProjectActivity() {
        this.employeeService = new EmployeeServiceImpl(ManagerEditProjectActivity.this);
        this.projectService = new ProjectServiceImpl(ManagerEditProjectActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_edit_project);

        this.initialize();

        final Bundle extras = super.getIntent().getExtras();
        final Integer projectId = extras.getInt("projectId");

        final SharedPreferences sp = super.getSharedPreferences("mgr", MODE_PRIVATE);
        final String username = sp.getString("username", null);

        this.projectService.findById(
                projectId,
                project -> {
                    this.editTextTitle.setText(project.getTitle());
                    this.editTextStartDate.setText(project.getStartDate());
                    this.editTextEndDate.setText(project.getEndDate());
                    this.editTextStatus.setText(project.getStatus());
                },
                error -> Toast.makeText(ManagerEditProjectActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
        );

        this.btnEditProject.setOnClickListener(v -> {

            final String title = this.editTextTitle.getText().toString().trim();
            final String startDate = this.editTextStartDate.getText().toString().trim();
            final String endDate = this.editTextEndDate.getText().toString().trim();
            final String status = this.editTextStatus.getText().toString().trim();

            if (isEmpty(title, startDate, endDate, status))
                Toast.makeText(ManagerEditProjectActivity.this, "field(s) is/are empty, try again!", Toast.LENGTH_SHORT).show();
            else {
                this.projectService.update(
                        new ProjectBuilder(new Project())
                                .projectId(projectId)
                                .title(title)
                                .startDate(startDate)
                                .endDate(endDate)
                                .status(status)
                                .build(),
                        project -> Toast.makeText(ManagerEditProjectActivity.this, String.format("Project [%s] updated successfully!", project.getTitle()), Toast.LENGTH_SHORT).show(),
                        error -> Toast.makeText(ManagerEditProjectActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
                );
            }

        });

    }

    private void initialize() {
        this.editTextTitle = super.findViewById(R.id.editTextManagerEditProjectTitle);
        this.editTextStartDate = super.findViewById(R.id.editTextManagerEditProjectStartDate);
        this.editTextEndDate = super.findViewById(R.id.editTextManagerEditProjectEndDate);
        this.editTextStatus = super.findViewById(R.id.editTextManagerEditProjectStatus);
        this.btnEditProject = super.findViewById(R.id.editTextManagerEditProjectEditProject);
    }

    private static boolean isEmpty(final String title, final String startDate, final String endDate, final String status) {
        return title.isEmpty() || startDate.isEmpty() || endDate.isEmpty() || status.isEmpty();
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
                super.startActivity(new Intent(ManagerEditProjectActivity.this, ManagerInfoActivity.class));
                return true;
            case R.id.managerTeam:
                super.startActivity(new Intent(ManagerEditProjectActivity.this, ManagerTeamActivity.class));
                return true;
            case R.id.managerAddProject:
                super.startActivity(new Intent(ManagerEditProjectActivity.this, ManagerAddProjectActivity.class));
                return true;
            case R.id.managerProjects:
                super.startActivity(new Intent(ManagerEditProjectActivity.this, ManagerIndexActivity.class));
                return true;
            case R.id.managerLogout:
                super.getSharedPreferences("mgr", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(ManagerEditProjectActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}