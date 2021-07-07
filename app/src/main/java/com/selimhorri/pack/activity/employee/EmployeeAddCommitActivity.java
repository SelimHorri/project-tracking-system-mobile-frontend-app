package com.selimhorri.pack.activity.employee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.selimhorri.pack.R;
import com.selimhorri.pack.activity.HomeActivity;
import com.selimhorri.pack.model.dto.custom.NotificationMsg;
import com.selimhorri.pack.model.id.AssignmentId;
import com.selimhorri.pack.pattern.builder.AssignmentBuilder;
import com.selimhorri.pack.service.AssignmentService;
import com.selimhorri.pack.service.EmployeeService;
import com.selimhorri.pack.service.NotificationService;
import com.selimhorri.pack.service.ProjectService;
import com.selimhorri.pack.service.impl.AssignmentServiceImpl;
import com.selimhorri.pack.service.impl.EmployeeServiceImpl;
import com.selimhorri.pack.service.impl.NotificationServiceImpl;
import com.selimhorri.pack.service.impl.ProjectServiceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmployeeAddCommitActivity extends AppCompatActivity {

    private final EmployeeService employeeService;
    private final ProjectService projectService;
    private final AssignmentService assignmentService;
    private final NotificationService notificationService;
    private EditText editTextUsername;
    private EditText editTextTitle;
    private EditText editTextCommitEmpDesc;
    private Button btnSubmit;

    public EmployeeAddCommitActivity() {
        this.employeeService = new EmployeeServiceImpl(EmployeeAddCommitActivity.this);
        this.projectService = new ProjectServiceImpl(EmployeeAddCommitActivity.this);
        this.assignmentService = new AssignmentServiceImpl(EmployeeAddCommitActivity.this);
        this.notificationService = new NotificationServiceImpl(EmployeeAddCommitActivity.this);
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

                                this.btnSubmit.setOnClickListener(v -> {
                                    if (isEmpty(this.editTextUsername.getText().toString(), this.editTextTitle.getText().toString(), this.editTextCommitEmpDesc.getText().toString()))
                                        Toast.makeText(EmployeeAddCommitActivity.this, "Field(s) is/are empty, try again!", Toast.LENGTH_SHORT).show();
                                    else
                                        this.assignmentService.save(
                                                new AssignmentBuilder()
                                                            .assignmentId(
                                                                    new AssignmentId(
                                                                            response.getEmployeeId(),
                                                                            project.getProjectId(),
                                                                            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyyHH:mm:ss"))
                                                                    ))
                                                            .commitEmpDesc(this.editTextCommitEmpDesc.getText().toString())
                                                            .commitMgrDesc(null)
                                                            .employee(response)
                                                            .project(project)
                                                        .build(),
                                                assignment -> {
                                                    this.editTextCommitEmpDesc.setText(null);
                                                    this.notificationService.notifyy(
                                                            new NotificationMsg(
                                                                    R.drawable.ic_launcher_foreground,
                                                                    "New Commit!",
                                                                    response.getFirstName() + ", you have successfully committed on project" + project.getTitle(),
                                                                    NotificationCompat.PRIORITY_DEFAULT
                                                            )
                                                    );
                                                    Log.d("notify!", "Send notification successfully");
                                                    Toast.makeText(EmployeeAddCommitActivity.this, "Committed successfully at => " + assignment.getCommitDate(), Toast.LENGTH_LONG).show();
                                                },
                                                error -> Toast.makeText(EmployeeAddCommitActivity.this, error.toString(), Toast.LENGTH_LONG).show()
                                        );
                                });
                            },
                            error -> Toast.makeText(EmployeeAddCommitActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
                    ),
                error -> Toast.makeText(EmployeeAddCommitActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
        );

    }

    private static boolean isEmpty(final String username, final String title, final String commitEmpDesc) {
        return username.trim().isEmpty() || title.trim().isEmpty() || commitEmpDesc.trim().isEmpty();
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