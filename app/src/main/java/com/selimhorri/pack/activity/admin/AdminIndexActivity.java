package com.selimhorri.pack.activity.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.selimhorri.pack.R;
import com.selimhorri.pack.activity.HomeActivity;

public class AdminIndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_index);
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
                // super.startActivity(new Intent(AdminIndexActivity.this, AdminInfoActivity.class));
                return true;
            case R.id.adminLogout:
                super.getSharedPreferences("admin", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(AdminIndexActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}