package com.example.android.quakereport;

public class Earthquake {

    private double mMagnitude;
    private String mLocation;
    private long mTimeMilliseconds;
    private String mUrl;

    /**
     * @param magnitude is the size of the earthquake
     * @param location is the city of the earthquake
     * @param timeMilliseconds is the unix time in millsieconds when the earthquake occurred
     * @param url is the url of the earthquake on USGS website
     */
    public Earthquake(Double magnitude, String location, long timeMilliseconds, String url) {
        this.mMagnitude = magnitude;
        this.mLocation = location;
        this.mTimeMilliseconds = timeMilliseconds;
        this.mUrl = url;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeMilliseconds() {
        return mTimeMilliseconds;
    }

    public String getUrl() {
        return mUrl;
    }
}
