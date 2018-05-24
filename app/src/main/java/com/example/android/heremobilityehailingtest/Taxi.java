package com.example.android.heremobilityehailingtest;

public class Taxi {

    private String mName;
    public void setName(String name) {
        mName = name;
    }
    public String getName() {
        return mName;
    }

    private String mStation;
    public void setStation(String station) {
        mStation = station;
    }
    public String getStation() {
        return mStation;
    }

    private int mIconResource;
    public void setIconResource(int iconResource) {
        mIconResource = iconResource;
    }
    public int getIconResource() {
        return mIconResource;
    }

    private int mEtaMinutes;
    public void setEtaMinutes(int etaMinutes) {
        mEtaMinutes = etaMinutes;
    }
    public int getEtaMinutes() {
        return mEtaMinutes;
    }
    public String getConvertedEta() {
        int minutes = mEtaMinutes%60;
        int hours = mEtaMinutes/60;
        int days = mEtaMinutes/(24*60);
        hours = hours-24*days;
        if (days==0 && hours==0) {
            return Integer.toString(minutes) + "m";
        }
        else if (days==0 && hours>0) {
            if (minutes<10) return Integer.toString(hours) + "h 0" + Integer.toString(minutes) + "m";
            else return Integer.toString(hours) + "h " + Integer.toString(minutes) + "m";
        }
        else if (days>0 && hours>0) {
            if (minutes<10 && hours<10) return Integer.toString(days) + "d 0" + Integer.toString(hours) + "h 0" + Integer.toString(minutes) + "m";
            else if (minutes<10 && hours>=10) return Integer.toString(days) + "d " + Integer.toString(hours) + "h 0" + Integer.toString(minutes) + "m";
            else if (minutes>=10 && hours<10) return Integer.toString(days) + "d 0" + Integer.toString(hours) + "h " + Integer.toString(minutes) + "m";
            else return Integer.toString(days) + "d " + Integer.toString(hours) + "h " + Integer.toString(minutes) + "m";
        }
        else return "?m";
    }
}
