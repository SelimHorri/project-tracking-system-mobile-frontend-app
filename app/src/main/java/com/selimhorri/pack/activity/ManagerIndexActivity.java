package com.selimhorri.pack.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.selimhorri.pack.R;

public class ManagerIndexActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_index);

        this.textView = super.findViewById(R.id.textView2);
        this.button = super.findViewById(R.id.button2);

        this.button.setOnClickListener(v -> {
            SharedPreferences.Editor editor = super.getSharedPreferences("mgr", Context.MODE_PRIVATE).edit();
            editor.clear();
            editor.apply();
            super.startActivity(new Intent(ManagerIndexActivity.this, HomeActivity.class));
        });

    }
}