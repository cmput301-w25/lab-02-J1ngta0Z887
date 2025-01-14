package com.example.listycity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
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

    //Declare variables for later use
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String []cities = {"Cold Lake", "Haida Gwaii", "Thunder Bay" ,"Resolute", "Deadhorse", "Iqaluit", "Alert", "Kugluktuk", "Anchorage", "Hakodate", "Niigata"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Set a click listener for the ListView to track the selected city
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCity = dataList.get(position);
            Toast.makeText(MainActivity.this, "Selected: " + selectedCity, Toast.LENGTH_SHORT).show();
        });

        // Add city functionality
        addCityButton.setOnClickListener(v -> {
            String cityName = cityNameInput.getText().toString().trim();
            if (!cityName.isEmpty() && !dataList.contains(cityName)) {
                dataList.add(cityName); // Add city to the list
                cityAdapter.notifyDataSetChanged(); // Update the ListView
                cityNameInput.setText(""); // Clear the input
                Toast.makeText(MainActivity.this, cityName + " added!", Toast.LENGTH_SHORT).show();
            } else if (dataList.contains(cityName)) {
                Toast.makeText(MainActivity.this, "City already exists!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Enter a valid city name!", Toast.LENGTH_SHORT).show();
            }
        });

        // Delete city functionality
        deleteCityButton.setOnClickListener(v -> {
            if (selectedCity != null) {
                dataList.remove(selectedCity); // Remove the selected city
                cityAdapter.notifyDataSetChanged(); // Update the ListView
                selectedCity = null; // Reset the selection
                Toast.makeText(MainActivity.this, "City deleted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Select a city to delete!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}