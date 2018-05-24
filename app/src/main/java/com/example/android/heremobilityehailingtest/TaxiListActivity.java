package com.example.android.heremobilityehailingtest;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.android.heremobilityehailingtest.data.TaxiData;
import com.example.android.heremobilityehailingtest.ui.AvailableTaxisRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TaxiListActivity extends AppCompatActivity {

    private RecyclerView mAvailableCabsRV;
    private AvailableTaxisRecyclerViewAdapter mAvailableCabsRVAdapter;
    public List<Taxi> taxis;
    final Handler myHandler = new Handler();

    //Lifecycle methods
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taxi_list);

        updateTextViews();
        populateTaxiList();
        sortTaxisByEta();
        setupListOfAvailableCabs();
        setupListRefresher();
    }
    @Override protected void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacks(myRunnable); //Prevents memory leaks
    }

    //Functional methods
    private void populateTaxiList() {

        taxis = new ArrayList<>();
        for (int i = 0; i < TaxiData.numberTaxis; i++) {
            Taxi taxi = new Taxi();
            taxi.setName("taxi_" + i);

            //Choosing a random station for each cab
            Random rand = new Random();
            int stationIndex = rand.nextInt(TaxiData.stations.length);
            if (stationIndex==TaxiData.stations.length) stationIndex--;
            taxi.setStation(TaxiData.stations[stationIndex]);

            //Setting the corresponding icon path for each cab
            int icon = TaxiData.recipeImagesMap.get(TaxiData.stations[stationIndex]);
            taxi.setIconResource(icon);

            //Setting the etas
            int eta = getRandomEtaMinutes();
            taxi.setEtaMinutes(eta);

            taxis.add(taxi);
        }
    }
    private void updateTextViews() {
        TextView origin = findViewById(R.id.originTV);
        TextView destination = findViewById(R.id.destinationTV);

        Intent intent = getIntent();
        if (getIntent().hasExtra(MainActivity.ORIGIN)) {
            String originString = intent.getStringExtra(MainActivity.ORIGIN);
            if (TextUtils.isEmpty(originString)) origin.setText(R.string.origin);
            else origin.setText(originString);
        }
        if (getIntent().hasExtra(MainActivity.DESTINATION)) {
            String destinationString = intent.getStringExtra(MainActivity.DESTINATION);
            if (TextUtils.isEmpty(destinationString)) destination.setText(R.string.destination);
            else destination.setText(destinationString);
        }
    }
    public int getRandomEtaMinutes() {
        Random rand = new Random();
        int maxMinutes = 4*60;
        int eta = rand.nextInt(maxMinutes);
        return eta;
    }
    private void sortTaxisByEta() {
        Collections.sort(taxis, new Comparator<Taxi>(){
            @Override
            public int compare(Taxi taxi1, Taxi taxi2) {
                return Integer.valueOf(taxi1.getEtaMinutes()).compareTo(taxi2.getEtaMinutes());
            }
        });
    }
    private void setupListOfAvailableCabs() {
        mAvailableCabsRV = findViewById(R.id.taxis_list);
        mAvailableCabsRV.setLayoutManager(new LinearLayoutManager(this));
        mAvailableCabsRVAdapter = new AvailableTaxisRecyclerViewAdapter(this);
        mAvailableCabsRVAdapter.setCabsList(taxis);
        mAvailableCabsRV.setAdapter(mAvailableCabsRVAdapter);
    }
    private void setupListRefresher() {
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                myHandler.post(myRunnable);
            }
        }, 0, 5000);
    }
    final Runnable myRunnable = new Runnable() {
        public void run() {
            refreshList();
        }
    };
    private void refreshList() {
        for (int i=0; i<taxis.size(); i++) {
            int currentEta = taxis.get(i).getEtaMinutes();

            Random rand = new Random();
            int deviation = (int) Math.floor(currentEta*0.2);
            if (deviation==0) deviation++; //Prevents rand(0)
            int newEta = currentEta + deviation - rand.nextInt(2*deviation); //Models taxi delays/advancement
            taxis.get(i).setEtaMinutes(newEta);
        }
        sortTaxisByEta();
        mAvailableCabsRVAdapter.setCabsList(taxis);
    }

}
