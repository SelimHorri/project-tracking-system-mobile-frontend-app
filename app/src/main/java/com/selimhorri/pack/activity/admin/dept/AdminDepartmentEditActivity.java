package com.selimhorri.pack.activity.admin.dept;

import android.content.Intent;
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
import com.selimhorri.pack.activity.admin.AdminIndexActivity;
import com.selimhorri.pack.activity.admin.AdminInfoActivity;
import com.selimhorri.pack.activity.admin.loc.AdminLocationEditActivity;
import com.selimhorri.pack.pattern.builder.DepartmentBuilder;
import com.selimhorri.pack.service.DepartmentService;
import com.selimhorri.pack.service.LocationService;
import com.selimhorri.pack.service.impl.DepartmentServiceImpl;
import com.selimhorri.pack.service.impl.LocationServiceImpl;

public class AdminDepartmentEditActivity extends AppCompatActivity {

    private final DepartmentService departmentService;
    private final LocationService locationService;
    private EditText editTextDepartmentName;
    private EditText editTextLocation;
    private Button btnEditDepartment;

    public AdminDepartmentEditActivity() {
        this.departmentService = new DepartmentServiceImpl(AdminDepartmentEditActivity.this);
        this.locationService = new LocationServiceImpl(AdminDepartmentEditActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_department);

        this.initialize();

        final Bundle extras = super.getIntent().getExtras();
        final Integer departmentId = extras.getInt("departmentId");

        this.departmentService.findById(
                departmentId,
                department -> {
                    this.editTextDepartmentName.setText(department.getDepartmentName());
                    this.editTextLocation.setText(String.valueOf(department.getLocation().getLocationId()));
                },
                error -> Toast.makeText(AdminDepartmentEditActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
        );

        this.btnEditDepartment.setOnClickListener(v -> {
            final String departmentName = this.editTextDepartmentName.getText().toString().trim();
            final String locationIdString = this.editTextLocation.getText().toString().trim();

            if (isEmpty(departmentName, locationIdString))
                Toast.makeText(AdminDepartmentEditActivity.this, "Field(s) is/are empty, plz try again!", Toast.LENGTH_SHORT).show();
            else
                this.locationService.findById(
                        Integer.parseInt(locationIdString),
                        location ->
                                this.departmentService.update(
                                        new DepartmentBuilder()
                                            .departmentId(departmentId)
                                            .departmentName(departmentName)
                                            .location(location)
                                            .build(),
                                        department -> {
                                            if (department == null || department.getLocation() == null)
                                                Toast.makeText(AdminDepartmentEditActivity.this, "Department does not exist!", Toast.LENGTH_SHORT).show();
                                            else {
                                                super.startActivity(
                                                        new Intent(AdminDepartmentEditActivity.this, AdminLocationEditActivity.class)
                                                                .putExtra("departmentId", department.getDepartmentId())
                                                );
                                                Toast.makeText(AdminDepartmentEditActivity.this, "Department updated successfully!", Toast.LENGTH_SHORT).show();
                                            }
                                        },
                                        error -> Toast.makeText(AdminDepartmentEditActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
                                ),
                        error -> Toast.makeText(AdminDepartmentEditActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
                );

        });

    }

    private void initialize() {
        this.editTextDepartmentName = super.findViewById(R.id.editTextAdminDepartmentEditDepartmentName);
        this.editTextLocation = super.findViewById(R.id.editTextAdminDepartmentEditLocationId);
        this.btnEditDepartment = super.findViewById(R.id.buttonAdminDepartmentEditEditDepartment);
    }

    private static boolean isEmpty(final String departmentName, final String locationIdString) {
        return departmentName.isEmpty() || locationIdString.isEmpty();
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
                super.startActivity(new Intent(AdminDepartmentEditActivity.this, AdminInfoActivity.class));
                return true;
            case R.id.adminCategories:
                super.startActivity(new Intent(this, AdminIndexActivity.class));
                return true;
            case R.id.adminNewDepartment:
                super.startActivity(new Intent(AdminDepartmentEditActivity.this, AdminDepartmentAddActivity.class));
                return true;
            case R.id.adminAbout:
                // super.startActivity(new Intent(AdminDepartmentAddActivity.this, AdminInfoActivity.class));
                return true;
            case R.id.adminLogout:
                super.getSharedPreferences("admin", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(AdminDepartmentEditActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}