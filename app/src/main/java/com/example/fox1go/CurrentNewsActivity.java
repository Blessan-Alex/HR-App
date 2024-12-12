package com.example.fox1go;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CurrentNewsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_news);

        ListView newsListView = findViewById(R.id.news_list);

        // Sample news items
        ArrayList<String> newsList = new ArrayList<>();
        newsList.add("Company launches new innovation initiative");
        newsList.add("Annual team building event announced");
        newsList.add("New product development update");
        newsList.add("Industry trends in digital transformation");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.custom_list_item, // Use your custom layout
                R.id.tv_list_item,
                newsList
        );
        newsListView.setAdapter(adapter);
    }
}