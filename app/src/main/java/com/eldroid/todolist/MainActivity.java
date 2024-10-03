package com.eldroid.todolist;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<TodoData> items; // Declare the list at the class level
    private ListAdapter adapter; // Declare the adapter at the class level
    private TodoData selectedItem; // The selected item for edit or delete

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize data
        items = new ArrayList<>();
        

        // Set up the ListView and Adapter
        ListView listView = findViewById(R.id.itemList);
        adapter = new ListAdapter(this, items);
        listView.setAdapter(adapter);

        // Initialize EditText and Button
        EditText descriptionEditText = findViewById(R.id.addList);
        Button addButton = findViewById(R.id.adding);

        // Set OnClickListener for the Button
        addButton.setOnClickListener(v -> {
            // Get the text from the EditText
            String description = descriptionEditText.getText().toString().trim();

            // Check if the description is not empty
            if (!description.isEmpty()) {
                // Add the new item to the list
                items.add(new TodoData(R.drawable.img, description));

                // Notify the adapter that the data has changed
                adapter.notifyDataSetChanged();

                // Clear the EditText
                descriptionEditText.setText("");

                // Show a confirmation message
                Toast.makeText(MainActivity.this, "Item added", Toast.LENGTH_SHORT).show();
            } else {
                // Show an error message if the EditText is empty
                Toast.makeText(MainActivity.this, "Description cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
