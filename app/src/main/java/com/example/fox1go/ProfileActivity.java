package com.example.fox1go;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView nameText = findViewById(R.id.profile_name);
        TextView emailText = findViewById(R.id.profile_email);
        TextView departmentText = findViewById(R.id.profile_department);
        TextView employeeIdText = findViewById(R.id.profile_employee_id);

        // Sample profile data
        nameText.setText("John Doe");
        emailText.setText("john.doe@company.com");
        departmentText.setText("Software Development");
        employeeIdText.setText("EMP-12345");
    }
}