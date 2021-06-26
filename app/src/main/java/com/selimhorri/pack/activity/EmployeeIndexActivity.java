package com.selimhorri.pack.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.selimhorri.pack.R;
import com.selimhorri.pack.activity.view.CustomAdapter;
import com.selimhorri.pack.service.AssignmentService;
import com.selimhorri.pack.service.CredentialService;
import com.selimhorri.pack.service.EmployeeService;
import com.selimhorri.pack.service.impl.dynmc.AssignmentServiceDynamicImpl;
import com.selimhorri.pack.service.impl.dynmc.CredentialServiceDynamicImpl;
import com.selimhorri.pack.service.impl.dynmc.EmployeeServiceDynamicImpl;

public class EmployeeIndexActivity extends AppCompatActivity {

    private final EmployeeService employeeService;
    private TextView textView;
    private Button btnIndex;
    private Button btnLogout;
    private Button btnAccountInfo;
    private Button btnTeamMembers;
    private Button btnMyCommits;
    private Button btnAllCommits;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    public EmployeeIndexActivity() {
        this.employeeService = new EmployeeServiceDynamicImpl(EmployeeIndexActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_index);

        this.recyclerView = super.findViewById(R.id.recyclerViewEmployeeProjectData);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(EmployeeIndexActivity.this));

        final SharedPreferences sp = super.getSharedPreferences("emp", MODE_PRIVATE);
        final String username = sp.getString("username", null);

        this.employeeService.findByUsername(
                username,
                response -> {
                    this.employeeService.findByEmployeeId(
                            response.getEmployeeId(),
                            listOfEmployeeProjectData ->
                                    this.recyclerView.setAdapter(
                                            new CustomAdapter(
                                                    listOfEmployeeProjectData.getCollection(),
                                                    R.layout.activity_employee_project_data_item,
                                                    EmployeeIndexActivity.this
                                            )
                                    ),
                            errorOfEmployeeProjectData -> Toast.makeText(EmployeeIndexActivity.this, errorOfEmployeeProjectData.toString(), Toast.LENGTH_SHORT).show()
                    );
                },
                error -> Toast.makeText(EmployeeIndexActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
        );

        /*
        this.textView = super.findViewById(R.id.textView4);
        this.btnIndex = super.findViewById(R.id.button6);
        this.btnLogout = super.findViewById(R.id.button5);
        this.btnAccountInfo = super.findViewById(R.id.button);
        this.btnTeamMembers = super.findViewById(R.id.button4);
        this.btnMyCommits = super.findViewById(R.id.button8);
        this.btnAllCommits = super.findViewById(R.id.button7);
         */

        /*
        // get data
        this.employeeService.findByUsername(
                username,
                response -> {
                    this.employeeService.findByEmployeeId(
                            response.getEmployeeId(),
                            listOfEmployeeProjectData -> {
                                final StringBuilder sb = new StringBuilder();
                                listOfEmployeeProjectData.getCollection().forEach(sb::append);
                                this.textView.setText(sb.toString());
                            },
                            errorOfEmployeeProjectData -> Toast.makeText(EmployeeIndexActivity.this, errorOfEmployeeProjectData.toString(), Toast.LENGTH_SHORT).show()
                    );
                },
                error -> Toast.makeText(EmployeeIndexActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
        );

        // logout
        this.btnLogout.setOnClickListener(v -> {
            sp.edit().clear().apply();
            super.startActivity(new Intent(EmployeeIndexActivity.this, HomeActivity.class));
        });

        // go index
        this.btnIndex.setOnClickListener(v -> {
            super.startActivity(new Intent(EmployeeIndexActivity.this, EmployeeIndexActivity.class));
        });

        // go account_info
        this.btnAccountInfo.setOnClickListener(v -> {
            super.startActivity(new Intent(EmployeeIndexActivity.this, EmployeeInfoActivity.class));
        });

        // go team members
        this.btnTeamMembers.setOnClickListener(v -> {
            this.employeeService.findByUsername(
                    username,
                    response -> {

                    },
                    error -> Toast.makeText(EmployeeIndexActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
            );
        });

        // go my commits
        this.btnMyCommits.setOnClickListener(v -> {
            this.employeeService.findByUsername(
                username,
                response -> {

                },
                error -> Toast.makeText(EmployeeIndexActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
            );
        });

        // go all commits
        this.btnAllCommits.setOnClickListener(v -> {
            this.employeeService.findByUsername(
                    username,
                    response -> {

                    },
                    error -> Toast.makeText(EmployeeIndexActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
            );
        });

         */

    }



}