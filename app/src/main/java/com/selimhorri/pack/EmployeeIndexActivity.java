package com.selimhorri.pack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class EmployeeIndexActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_index);

        this.textView = super.findViewById(R.id.textView);
        this.button = super.findViewById(R.id.button);

        this.button.setOnClickListener(v -> {
            SharedPreferences.Editor editor = super.getSharedPreferences("emp_shared_preferences", Context.MODE_PRIVATE).edit();
            editor.clear();
            editor.apply();
            super.startActivity(new Intent(EmployeeIndexActivity.this, HomeActivity.class));
        });

    }
}