package com.example.fox1go;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private TextView punchInTimestamp;
    private TextView punchOutTimestamp;
    private Button punchInButton;
    private FusedLocationProviderClient fusedLocationClient;
    private BottomNavigationView bottomNavigation;
    private ImageButton profileIcon;
    private ImageButton notificationBell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize location services
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Initialize views
        punchInTimestamp = findViewById(R.id.punch_in_timestamp);
        punchOutTimestamp = findViewById(R.id.punch_out_timestamp);
        punchInButton = findViewById(R.id.punch_in_button);
        profileIcon = findViewById(R.id.profile_icon);
        notificationBell = findViewById(R.id.notification_bell);
        bottomNavigation = findViewById(R.id.bottom_navigation);

        // Setup punch in functionality
        punchInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                punchIn();
            }
        });

        // Setup bottom navigation
        setupBottomNavigation();

        // Setup app bar buttons
        setupAppBarButtons();

        // Setup grid buttons
        setupGridButtons();
    }

    private void punchIn() {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String currentTime = sdf.format(new Date());
        punchInTimestamp.setText(currentTime);
        punchInButton.setEnabled(false);
    }

    private void setupBottomNavigation() {
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_tasks) {
                    // Open To-Do List Activity
                    Intent intent = new Intent(MainActivity.this, TodoListActivity.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.nav_team_chat) {
                    // Open Team Chatbox Activity
                    Intent intent = new Intent(MainActivity.this, TeamChatboxActivity.class);
                    startActivity(intent);
                    return true;
                }

                return false;
            }
        });
    }

    private void setupAppBarButtons() {
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Profile Activity
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        notificationBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open Notifications Activity
                Intent intent = new Intent(MainActivity.this, NotificationsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupGridButtons() {
        // Apply Leave Button
        findViewById(R.id.btn_apply_leave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUnderDevelopmentDialog("Leave Application");
            }
        });

        // Individual Performance Button
        findViewById(R.id.btn_individual_performance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PerformanceGraphActivity.class);
                startActivity(intent);
            }
        });

        // Team Chatbox Button
        findViewById(R.id.btn_team_chatbox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUnderDevelopmentDialog("Team Chatbox");
            }
        });

        findViewById(R.id.btn_payroll_management).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUnderDevelopmentDialog("Payroll Management");
            }
        });

        findViewById(R.id.btn_peer_rating).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUnderDevelopmentDialog("Peer Rating");
            }
        });

        // Stimulation Button
        findViewById(R.id.btn_stimulation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StimulationActivity.class);
                startActivity(intent);
            }
        });

        // Current News Button
        findViewById(R.id.btn_current_news).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CurrentNewsActivity.class);
                startActivity(intent);
            }
        });

        // Team Performance Button
        findViewById(R.id.btn_team_performance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUnderDevelopmentDialog("Team Performance");
            }
        });

        // Location Button
        findViewById(R.id.btn_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLocationPermissionAndGetLocation();
            }
        });

        // Timesheet Button
        findViewById(R.id.btn_timesheet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TimesheetActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkLocationPermissionAndGetLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Request permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            // Permission already granted, get location
            getLastLocation();
        }
    }

    private void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            // Open Location Activity with location details
                            Intent intent = new Intent(MainActivity.this, LocationActivity.class);
                            intent.putExtra("latitude", location.getLatitude());
                            intent.putExtra("longitude", location.getLongitude());
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this,
                                    "Unable to retrieve location",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void showUnderDevelopmentDialog(String feature) {
        new AlertDialog.Builder(this)
                .setTitle("Coming Soon")
                .setMessage(feature + " feature is currently under development.")
                .setPositiveButton("OK", null)
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                Toast.makeText(this,
                        "Location permission denied",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}