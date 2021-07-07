package com.selimhorri.pack.activity.admin.loc;

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
import com.selimhorri.pack.model.dto.Location;
import com.selimhorri.pack.service.LocationService;
import com.selimhorri.pack.service.impl.LocationServiceImpl;

public class AdminLocationEditActivity extends AppCompatActivity {

    private final LocationService locationService;
    private EditText editTextAdr;
    private EditText editTextPostalCode;
    private EditText editTextCity;
    private Button btnEdit;

    public AdminLocationEditActivity() {
        this.locationService = new LocationServiceImpl(AdminLocationEditActivity.this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_location);

        this.initialize();

        final Bundle extras = super.getIntent().getExtras();
        final Integer locationId = extras.getInt("locationId");

        this.locationService.findById(
                locationId,
                location -> {
                    this.editTextAdr.setText(location.getAdr());
                    this.editTextPostalCode.setText(location.getPostalCode());
                    this.editTextCity.setText(location.getCity());
                },
                error -> Toast.makeText(AdminLocationEditActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
        );

        this.btnEdit.setOnClickListener(v -> {
            final String adr = this.editTextAdr.getText().toString().trim();
            final String postalCode = this.editTextPostalCode.getText().toString().trim();
            final String city = this.editTextCity.getText().toString().trim();

            if (isEmpty(adr, postalCode, city))
                Toast.makeText(AdminLocationEditActivity.this, "Field(s) is/are empty, plz check again!", Toast.LENGTH_SHORT).show();
            else
                this.locationService.update(
                        new Location(locationId, adr, postalCode, city),
                        location -> {
                            if (location == null)
                                Toast.makeText(AdminLocationEditActivity.this, "Location does not exist!", Toast.LENGTH_SHORT).show();
                            else {
                                super.startActivity(
                                        new Intent(AdminLocationEditActivity.this, AdminLocationEditActivity.class)
                                            .putExtra("locationId", location.getLocationId())
                                );
                                Toast.makeText(AdminLocationEditActivity.this, "Location updated successfully!", Toast.LENGTH_SHORT).show();
                            }
                        },
                        error -> Toast.makeText(AdminLocationEditActivity.this, error.toString(), Toast.LENGTH_SHORT).show()
                );
        });

    }

    private void initialize() {
        this.editTextAdr = super.findViewById(R.id.editTextAdminLocationEditAdr);
        this.editTextPostalCode = super.findViewById(R.id.editTextAdminLocationEditPostalCode);
        this.editTextCity = super.findViewById(R.id.editTextAdminLocationEditCity);
        this.btnEdit = super.findViewById(R.id.editTextAdminLocationEditEditLocation);
    }

    private static boolean isEmpty(final String adr, final String postalCode, final String city) {
        return adr.isEmpty() || postalCode.isEmpty() || city.isEmpty();
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
                super.startActivity(new Intent(AdminLocationEditActivity.this, AdminInfoActivity.class));
                return true;
            case R.id.adminCategories:
                super.startActivity(new Intent(this, AdminIndexActivity.class));
                return true;
            case R.id.adminNewLocation:
                super.startActivity(new Intent(AdminLocationEditActivity.this, AdminLocationAddActivity.class));
                return true;
            case R.id.adminAbout:
                // super.startActivity(new Intent(AdminLocationAddActivity.this, AdminInfoActivity.class));
                return true;
            case R.id.adminLogout:
                super.getSharedPreferences("admin", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(AdminLocationEditActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}