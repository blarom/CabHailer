package com.example.android.heremobilityehailingtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.android.heremobilityehailingtest.data.TaxiData;

public class MainActivity extends AppCompatActivity {

    public static final String ORIGIN = "origin";
    public static final String DESTINATION = "destination";
    private AutoCompleteTextView fromTV;
    private AutoCompleteTextView toTV;
    private String mOrigin;
    private String mDestination;

    //Lifecycle methods
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateLocationChoosers();

        Button query = findViewById(R.id.query_button);
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOrigin = fromTV.getText().toString();
                mDestination = toTV.getText().toString();
                showListOfAvailableCabs();
            }
        });

    }
    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ORIGIN, mOrigin);
        outState.putString(DESTINATION, mDestination);
    }
    @Override protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mOrigin = savedInstanceState.getString(ORIGIN);
        fromTV.setText(mOrigin);
        mDestination = savedInstanceState.getString(DESTINATION);
        toTV.setText(mDestination);
    }

    //Functional methods
    private void populateLocationChoosers() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, TaxiData.locations);

        fromTV = findViewById(R.id.from_chooser);
        fromTV.setAdapter(adapter);
        fromTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mOrigin = fromTV.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        fromTV.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                mOrigin = fromTV.getText().toString();
            }
        });

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, TaxiData.locations);
        toTV = findViewById(R.id.to_chooser);
        toTV.setAdapter(adapter);
        toTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mDestination = toTV.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        toTV.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                mDestination = toTV.getText().toString();
            }
        });
    }
    private void showListOfAvailableCabs() {
        Intent intent = new Intent(getApplicationContext(), TaxiListActivity.class);
        intent.putExtra(ORIGIN, mOrigin);
        intent.putExtra(DESTINATION, mDestination);
        startActivity(intent);
    }
}
