package com.example.fox1go;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NotificationsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        ListView notificationListView = findViewById(R.id.notifications_list);

        // Sample notifications
        ArrayList<String> notifications = new ArrayList<>();
        notifications.add("New task assigned: Q3 Report");
        notifications.add("Performance review scheduled");
        notifications.add("Team meeting tomorrow at 10 AM");
        notifications.add("Upcoming training session");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.custom_list_item, // Use your custom layout
                R.id.tv_list_item,
                notifications
        );
        notificationListView.setAdapter(adapter);
    }
}