package com.example.android.quakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    /** Sample JSON response for a USGS query */
    private static final String USGS_RECENT_EARTHQUAKES_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";

    /** Tag for the log messages */
    public static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Earthquake> extractEarthquakes() {

        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            // COMPLETED: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

            // Convert SAMPLE_JSON_RESPONSE String into a JSONObject
            JSONObject sampleResponse = new JSONObject(SAMPLE_JSON_RESPONSE);

            // Extract “features” JSONArray
            JSONArray features = sampleResponse.getJSONArray("features");

            // Loop through each feature in the array
            for (int i = 0; i < features.length(); i++) {
                // Get earthquake JSONObject at position i
                JSONObject earthquakeJSON = features.getJSONObject(i);
                // Get “properties” JSONObject
                JSONObject properties = earthquakeJSON.getJSONObject("properties");
                // Extract “mag” for magnitude
                Double mag = properties.getDouble("mag");
                // Extract “place” for location
                String place = properties.getString("place");
                // Extract the value for the key called "time"
                long time = properties.getLong("time");
                String url = properties.getString("url");

                // Create Earthquake java object from magnitude, location, and time
                Earthquake earthquake = new Earthquake(mag, place, time, url);
                // Add earthquake to list of earthquakes
                earthquakes.add(earthquake);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }

}