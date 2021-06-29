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
import com.selimhorri.pack.service.CredentialService;
import com.selimhorri.pack.service.EmployeeService;
import com.selimhorri.pack.service.impl.CredentialServiceImpl;
import com.selimhorri.pack.service.impl.EmployeeServiceImpl;

public class EmployeeEditCredentialsActivity extends AppCompatActivity {

    private final EmployeeService employeeService;
    private final CredentialService credentialService;
    private EditText editTextUsername;
    private EditText editTextPassword1;
    private EditText editTextPassword2;
    private Button btnUpdate;

    public EmployeeEditCredentialsActivity() {
        this.employeeService = new EmployeeServiceImpl(EmployeeEditCredentialsActivity.this);
        this.credentialService = new CredentialServiceImpl(EmployeeEditCredentialsActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_edit_credentials);

        this.editTextUsername = super.findViewById(R.id.editTextUsername);
        this.editTextPassword1 = super.findViewById(R.id.editTextTextPassword1);
        this.editTextPassword2 = super.findViewById(R.id.editTextTextPassword2);
        this.btnUpdate = super.findViewById(R.id.buttonEmployeeEditCredentialsUpdate);

        final SharedPreferences sp = super.getSharedPreferences("emp", MODE_PRIVATE);
        final String username = sp.getString("username", null);

        this.employeeService.findByUsername(
                username,
                response -> {
                    this.editTextUsername.setText(response.getCredential().getUsername());
                },
                error -> Toast.makeText(EmployeeEditCredentialsActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
        );

        this.btnUpdate.setOnClickListener(v -> {
            final String textUsername = this.editTextUsername.getText().toString().toLowerCase().trim();
            final String password1 = this.editTextPassword1.getText().toString();
            final String password2 = this.editTextPassword2.getText().toString();

            if (isEmpty(textUsername, password1, password2))
                Toast.makeText(EmployeeEditCredentialsActivity.this, "Field(s) is/are empty!", Toast.LENGTH_SHORT).show();
            else {
                if (!isIdentical(password1, password2))
                    Toast.makeText(EmployeeEditCredentialsActivity.this, "password not matched!", Toast.LENGTH_SHORT).show();
                else {



                }
            }
        });

    }

    private static boolean isEmpty(final String textUsername, final String password1, final String password2) {
        return textUsername.isEmpty() || password1.isEmpty() || password2.isEmpty();
    }

    private static boolean isIdentical(final String password1, final String password2) {
        return password1.equals(password2);
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
                super.startActivity(new Intent(EmployeeEditCredentialsActivity.this, EmployeeInfoActivity.class));
                return true;
            case R.id.employeeTeam:
                super.startActivity(new Intent(EmployeeEditCredentialsActivity.this, EmployeeTeamActivity.class));
                return true;
            case R.id.employeeProjects:
                super.startActivity(new Intent(EmployeeEditCredentialsActivity.this, EmployeeIndexActivity.class));
                return true;
            case R.id.employeeLogout:
                super.getSharedPreferences("emp", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(EmployeeEditCredentialsActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}