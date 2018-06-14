package com.example.android.quakereport;

public class Earthquake {

    private String mMagnitude;
    private String mLocation;
    private long mTimeMilliseconds;

    /**
     * @param magnitude is the size of the earthquake
     * @param location is the city of the earthquake
     * @param mTimeMilliseconds is the unix time in millsieconds when the earthquake occurred
     */
    public Earthquake(String magnitude, String location, long mTimeMilliseconds) {
        this.mMagnitude = magnitude;
        this.mLocation = location;
        this.mTimeMilliseconds = mTimeMilliseconds;
    }

    public String getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeMilliseconds() {
        return mTimeMilliseconds;
    }
}
