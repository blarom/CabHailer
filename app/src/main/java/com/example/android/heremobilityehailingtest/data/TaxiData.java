package com.example.android.heremobilityehailingtest.data;

import com.example.android.heremobilityehailingtest.R;

import java.util.HashMap;
import java.util.Map;

public class TaxiData {
    public static final int numberTaxis = 20;
    public static final String[] locations = new String[] {
            "Namal","Azrieli","Ra'anana","Ashdod","Tel Aviv","Rishon LeZion","Givataim","Jerusalem","Haifa"
    };
    public static final Map<String, Integer> recipeImagesMap = createStationImagesMap();
    private static Map<String, Integer> createStationImagesMap() {
        Map<String,Integer> myMap = new HashMap<>();
        myMap.put("Castle", R.drawable.ic_local_taxi_castle_24dp);
        myMap.put("Shekem", R.drawable.ic_local_taxi_shekem_24dp);
        myMap.put("Habima", R.drawable.ic_local_taxi_habima_24dp);
        myMap.put("Gordon", R.drawable.ic_local_taxi_gordon_24dp);
        myMap.put("Azrieli", R.drawable.ic_local_taxi_azrieli_24dp);
        myMap.put("Hadera", R.drawable.ic_local_taxi_hadera_24dp);
        myMap.put("default", R.drawable.ic_local_taxi_default_24dp);
        return myMap;
    }
    public static final String[] stations = new String[] {
            "Castle","Shekem","Habima","Gordon","Azrieli","Hadera"
    };
}
