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
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Declaring the variables so we can reference them later on in the code.
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button add_button, delete_button, confirm_button;
    EditText input_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);
        input_text = findViewById(R.id.input_text);

        dataList = new ArrayList<>();

        cityAdapter = new ArrayAdapter<String>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // When add button is pressed, you can see the input text box, and the confirm button
        add_button = findViewById(R.id.add_button);
        confirm_button = findViewById(R.id.confirm_button);
        add_button.setOnClickListener(v -> {
                    input_text.setVisibility(View.VISIBLE);
                    confirm_button.setVisibility(View.VISIBLE);
        });

        // Adds city name to list once pressing confirm.
        confirm_button.setOnClickListener(v -> {
            String new_city_name = input_text.getText().toString().trim();
            if (input_text.length() > 0) {
                dataList.add(new_city_name);
                cityAdapter.notifyDataSetChanged();
                input_text.setText("");
                input_text.setVisibility(View.GONE);
                confirm_button.setVisibility(View.GONE);
            }
        });

        // Delete button, takes the item pressed at its given position in the list and deletes it.
        delete_button = findViewById(R.id.delete_button);
        delete_button.setOnClickListener(v -> {
            int pos = cityList.getCheckedItemPosition();
            if (pos != ListView.INVALID_POSITION) {
                dataList.remove(pos);
                cityAdapter.notifyDataSetChanged();
                cityList.clearChoices();
            }
        });

    }
}