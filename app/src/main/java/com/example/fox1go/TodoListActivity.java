package com.example.fox1go;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TodoListActivity extends AppCompatActivity {
    private ArrayList<String> todoList;
    private ArrayAdapter<String> adapter;
    private ListView listView;
    private EditText taskInput;
    private Button addTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        listView = findViewById(R.id.todo_list_view);
        taskInput = findViewById(R.id.task_input);
        addTaskButton = findViewById(R.id.add_task_button);

        // Pre-populate with some initial tasks
        todoList = new ArrayList<>();
        todoList.add("Attend team meeting");
        todoList.add("Complete project report");
        todoList.add("Review quarterly performance");
        todoList.add("Update project timeline");

        adapter = new ArrayAdapter<>(
                this,
                R.layout.custom_list_item, // Use your custom layout
                R.id.tv_list_item,
                todoList
        );
        listView.setAdapter(adapter);

        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = taskInput.getText().toString().trim();
                if (!task.isEmpty()) {
                    todoList.add(task);
                    adapter.notifyDataSetChanged();
                    taskInput.setText("");

                }
            }
        });
    }
}