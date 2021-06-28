package com.selimhorri.pack.activity.employee;

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
import com.selimhorri.pack.pattern.builder.AssignmentBuilder;
import com.selimhorri.pack.service.AssignmentService;
import com.selimhorri.pack.service.EmployeeService;
import com.selimhorri.pack.service.ProjectService;
import com.selimhorri.pack.service.impl.AssignmentServiceImpl;
import com.selimhorri.pack.service.impl.EmployeeServiceImpl;
import com.selimhorri.pack.service.impl.ProjectServiceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmployeeAddCommitActivity extends AppCompatActivity {

    private final EmployeeService employeeService;
    private final ProjectService projectService;
    private final AssignmentService assignmentService;
    private EditText editTextUsername;
    private EditText editTextTitle;
    private EditText editTextCommitEmpDesc;
    private Button btnSubmit;

    public EmployeeAddCommitActivity() {
        this.employeeService = new EmployeeServiceImpl(EmployeeAddCommitActivity.this);
        this.projectService = new ProjectServiceImpl(EmployeeAddCommitActivity.this);
        this.assignmentService = new AssignmentServiceImpl(EmployeeAddCommitActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_add_commit);

        this.editTextUsername = super.findViewById(R.id.editTextEmployeeAddCommitUsername);
        this.editTextTitle = super.findViewById(R.id.editTextEmployeeAddCommitIsTitle);
        this.editTextCommitEmpDesc = super.findViewById(R.id.editTextEmployeeAddCommitCommitEmpDesc);
        this.btnSubmit = super.findViewById(R.id.buttonEmployeeAddCommitSubmit);

        final SharedPreferences sp = super.getSharedPreferences("emp", MODE_PRIVATE);
        final String username = sp.getString("username", null);

        final Bundle extras = super.getIntent().getExtras();
        final Integer projectId = extras.getInt("projectId");

        this.employeeService.findByUsername(
                username,
                response ->
                    this.projectService.findById(
                            projectId,
                            project -> {
                                this.editTextUsername.setText(response.getCredential().getUsername());
                                this.editTextTitle.setText(project.getTitle());

                                /*
                                this.btnSubmit.setOnClickListener(v -> {
                                    this.assignmentService.save(
                                            new AssignmentBuilder()
                                                .assignmentId(
                                                        new AssignmentId(
                                                                response.getEmployeeId(),
                                                                project.getProjectId(),
                                                                // LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyyHH:mm:ss")))
                                                                LocalDateTime.now()
                                                        ))
                                                .commitEmpDesc(this.editTextCommitEmpDesc.getText().toString())
                                                .commitMgrDesc("init")
                                                .build(),
                                            assignment -> Toast.makeText(EmployeeAddCommitActivity.this, assignment.toString(), Toast.LENGTH_LONG).show(),
                                            error -> Toast.makeText(EmployeeAddCommitActivity.this, error.toString(), Toast.LENGTH_LONG).show()
                                    );
                                });
                                */

                                this.btnSubmit.setOnClickListener(v -> {
                                    this.assignmentService.save(
                                            new AssignmentBuilder()
                                                    .assignmentId(
                                                            new AssignmentId(
                                                                    response.getEmployeeId(),
                                                                    project.getProjectId(),
                                                                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyyHH:mm:ss"))
                                                            ))
                                                    .commitEmpDesc(this.editTextCommitEmpDesc.getText().toString())
                                                    .commitMgrDesc("init")
                                                    .build(),
                                            assignment -> Toast.makeText(EmployeeAddCommitActivity.this, assignment.toString(), Toast.LENGTH_LONG).show(),
                                            error -> Toast.makeText(EmployeeAddCommitActivity.this, error.toString(), Toast.LENGTH_LONG).show()
                                    );
                                });

                            },
                            error -> Toast.makeText(EmployeeAddCommitActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
                    ),
                error -> Toast.makeText(EmployeeAddCommitActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
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
                super.startActivity(new Intent(EmployeeAddCommitActivity.this, EmployeeInfoActivity.class));
                return true;
            case R.id.employeeTeam:
                super.startActivity(new Intent(EmployeeAddCommitActivity.this, EmployeeTeamActivity.class));
                return true;
            case R.id.employeeProjects:
                super.startActivity(new Intent(EmployeeAddCommitActivity.this, EmployeeIndexActivity.class));
                return true;
            case R.id.employeeLogout:
                super.getSharedPreferences("emp", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(EmployeeAddCommitActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}