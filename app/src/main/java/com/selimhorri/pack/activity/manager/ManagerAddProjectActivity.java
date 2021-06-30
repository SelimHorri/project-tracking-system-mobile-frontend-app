package com.selimhorri.pack.activity.manager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.selimhorri.pack.R;
import com.selimhorri.pack.activity.HomeActivity;

public class ManagerAddProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_add_project);
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
                super.startActivity(new Intent(ManagerAddProjectActivity.this, ManagerInfoActivity.class));
                return true;
            case R.id.managerTeam:
                super.startActivity(new Intent(ManagerAddProjectActivity.this, ManagerTeamActivity.class));
                return true;
            case R.id.managerAddProject:
                super.startActivity(new Intent(ManagerAddProjectActivity.this, ManagerAddProjectActivity.class));
                return true;
            case R.id.managerProjects:
                super.startActivity(new Intent(ManagerAddProjectActivity.this, ManagerIndexActivity.class));
                return true;
            case R.id.managerLogout:
                super.getSharedPreferences("mgr", MODE_PRIVATE)
                        .edit()
                        .clear()
                        .apply();
                super.startActivity(new Intent(ManagerAddProjectActivity.this, HomeActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}