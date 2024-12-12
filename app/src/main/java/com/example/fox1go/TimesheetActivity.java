package com.example.fox1go;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TimesheetActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timesheet);

        ListView eventListView = findViewById(R.id.events_list);

        // Sample upcoming events
        ArrayList<String> events = new ArrayList<>();
        events.add("Team Meeting | 10:00 AM | Conference Room");
        events.add("Performance Review | 2:00 PM | HR Office");
        events.add("Project Presentation | 4:30 PM | Main Hall");
        events.add("Training Session | 11:00 AM | Training Room");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.custom_list_item, // Use your custom layout
                R.id.tv_list_item,
                events
        );
        eventListView.setAdapter(adapter);
    }
}