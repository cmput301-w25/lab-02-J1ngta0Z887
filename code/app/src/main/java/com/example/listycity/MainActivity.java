package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // Declare variables for UI components and data
    private ListView cityList;
    private EditText cityInput;
    private Button addCityButton, deleteCityButton;

    private ArrayList<String> dataList; // Data source for ListView
    private ArrayAdapter<String> cityAdapter; // Adapter for ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        cityList = findViewById(R.id.city_list);
        cityInput = findViewById(R.id.cityInput);
        addCityButton = findViewById(R.id.addCityButton);
        deleteCityButton = findViewById(R.id.deleteCityButton);

        // Initialize the list of cities
        String[] cities = {"Cold Lake", "Haida Gwaii", "Thunder Bay", "Resolute", "Deadhorse", "Iqaluit", "Alert", "Kugluktuk", "Anchorage", "Hakodate", "Niigata"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        // Set up the ArrayAdapter
        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, dataList);
        cityList.setAdapter(cityAdapter);

        // Enable single selection mode in ListView
        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Add City Button: Add a city to the list
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCity = cityInput.getText().toString().trim(); // Get input from EditText
                if (!newCity.isEmpty()) {
                    dataList.add(newCity); // Add city to data list
                    cityAdapter.notifyDataSetChanged(); // Notify adapter of data change
                    cityInput.setText(""); // Clear the input field
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a city name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Delete City Button: Remove a city from the list
        deleteCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedPosition = cityList.getCheckedItemPosition(); // Get the selected position
                if (selectedPosition != ListView.INVALID_POSITION) {
                    dataList.remove(selectedPosition); // Remove the selected city from data list
                    cityAdapter.notifyDataSetChanged(); // Notify adapter of data change
                    cityList.clearChoices(); // Clear the ListView selection
                } else {
                    Toast.makeText(MainActivity.this, "Please select a city to delete", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Handle window insets for proper padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
