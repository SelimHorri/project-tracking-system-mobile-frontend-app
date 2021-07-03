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
import com.selimhorri.pack.model.id.AssignmentId;
import com.selimhorri.pack.service.AssignmentService;
import com.selimhorri.pack.service.EmployeeService;
import com.selimhorri.pack.service.ProjectService;
import com.selimhorri.pack.service.impl.AssignmentServiceImpl;
import com.selimhorri.pack.service.impl.EmployeeServiceImpl;
import com.selimhorri.pack.service.impl.ProjectServiceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ManagerDescribeCommitActivity extends AppCompatActivity {

    private final EmployeeService employeeService;
    private final AssignmentService assignmentService;
    private final ProjectService projectService;
    private EditText editTextUsername;
    private EditText editTextTitle;
    private EditText editTextCommitDate;
    private EditText editTextCommitEmpDesc;
    private EditText editTextCommitMgrOldDesc;
    private EditText editTextCommitMgrNewDesc;
    private Button btnDescribe;

    public ManagerDescribeCommitActivity() {
        this.employeeService = new EmployeeServiceImpl(ManagerDescribeCommitActivity.this);
        this.assignmentService = new AssignmentServiceImpl(ManagerDescribeCommitActivity.this);
        this.projectService = new ProjectServiceImpl(ManagerDescribeCommitActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_describe_commit);

        this.initialize();

        final Bundle extras = super.getIntent().getExtras();
        final int employeeId = extras.getInt("employeeId");
        final int projectId = extras.getInt("projectId");
        final String commitDate = LocalDateTime.parse(extras.getString("commitDate")).format(DateTimeFormatter.ofPattern("dd-MM-yyyyHH:mm:ss"));

        final SharedPreferences sp = super.getSharedPreferences("mgr", MODE_PRIVATE);
        final String username = sp.getString("username", null);

        this.employeeService.findByUsername(
                username,
                response ->
                    this.employeeService.findById(
                            employeeId,
                            employee -> this.projectService.findById(
                                    projectId,
                                    project ->
                                            this.assignmentService.findProjectCommitByEmployeeIdAndProjectIdAndCommitDate(
                                                    new AssignmentId(employee.getEmployeeId(), project.getProjectId(), commitDate),
                                                    projectCommit -> {
                                                        this.editTextUsername.setText(employee.getFirstName() + " " + employee.getLastName());
                                                        this.editTextTitle.setText(project.getTitle());
                                                        this.editTextCommitDate.setText(commitDate);
                                                        this.editTextCommitEmpDesc.setText(projectCommit.getCommitEmpDesc());
                                                        this.editTextCommitMgrOldDesc.setText(projectCommit.getCommitMgrDesc());
                                                    },
                                                    error -> Toast.makeText(ManagerDescribeCommitActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
                                            ),
                                    error -> Toast.makeText(ManagerDescribeCommitActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
                            ),
                            error -> Toast.makeText(ManagerDescribeCommitActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
                    ),
                error -> Toast.makeText(ManagerDescribeCommitActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
        );

    }

    private void initialize() {
        this.editTextUsername = super.findViewById(R.id.editTextManagerDescribeCommitUsername);
        this.editTextTitle = super.findViewById(R.id.editTextManagerDescribeCommitTitle);
        this.editTextCommitDate = super.findViewById(R.id.editTextManagerDescribeCommitCommitDate);
        this.editTextCommitEmpDesc = super.findViewById(R.id.editTextManagerDescribeCommitCommitEmpDesc);
        this.editTextCommitMgrOldDesc = super.findViewById(R.id.editTextManagerDescribeCommitCommitMgrOldDesc);
        this.editTextCommitMgrNewDesc = super.findViewById(R.id.editTextManagerDescribeCommitCommitMgrNewDesc);
        this.btnDescribe = super.findViewById(R.id.buttonManagerDescribeCommitDescribe);
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
                super.startActivity(new Intent(ManagerDescribeCommitActivity.this, ManagerInfoActivity.class));
                return true;
            case R.id.managerTeam:
                super.startActivity(new Intent(ManagerDescribeCommitActivity.this, ManagerTeamActivity.class));
                return true;
            case R.id.managerAddProject:
                super.startActivity(new Intent(ManagerDescribeCommitActivity.this, ManagerAddProjectActivity.class));
                return true;
            case R.id.managerProjects:
                super.startActivity(new Intent(ManagerDescribeCommitActivity.this, ManagerIndexActivity.class));
                return true;
            case R.id.managerLogout:
                super.getSharedPreferences("mgr", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(ManagerDescribeCommitActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}