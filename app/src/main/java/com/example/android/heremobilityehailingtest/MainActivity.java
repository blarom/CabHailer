package com.example.android.heremobilityehailingtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.android.heremobilityehailingtest.data.TaxiData;

public class MainActivity extends AppCompatActivity {

    public static final String ORIGIN = "origin";
    public static final String DESTINATION = "destination";
    private AutoCompleteTextView fromTV;
    private AutoCompleteTextView toTV;

    //Lifecycle methods
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateLocationChoosers();

        Button query = findViewById(R.id.query_button);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showListOfAvailableCabs();
            }
        });

    }

    //Functional methods
    private void populateLocationChoosers() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, TaxiData.locations);

        fromTV = findViewById(R.id.from_chooser);
        fromTV.setAdapter(adapter);

        toTV = findViewById(R.id.to_chooser);
        toTV.setAdapter(adapter);
    }
    private void showListOfAvailableCabs() {
        Intent intent = new Intent(getApplicationContext(), TaxiListActivity.class);
        intent.putExtra(ORIGIN, fromTV.getText().toString());
        intent.putExtra(DESTINATION, toTV.getText().toString());
        startActivity(intent);
    }
}
