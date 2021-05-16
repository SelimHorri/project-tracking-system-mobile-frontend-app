package com.selimhorri.pack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.selimhorri.pack.service.DepartmentService;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button btnDepartments;
    private Button btnDepartmentById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



}