package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    // pattern to match one or more digits at start of string
    private static final String REGEX_DIGIT_PATTERN = "\\d+(.*)";

    public EarthquakeAdapter(@NonNull Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitudeTextView.setText(currentEarthquake.getMagnitude());

        TextView offsetTextView = (TextView) listItemView.findViewById(R.id.offset);
        boolean offsetExists = locationContainsDigit(currentEarthquake.getLocation());
        String offset = createOffset(currentEarthquake.getLocation(), offsetExists);
        offsetTextView.setText(offset);

        TextView locationTextView = (TextView) listItemView.findViewById(R.id.primary_location);
        String primaryLocation = createPrimaryLocation(currentEarthquake.getLocation(), offsetExists);
        locationTextView.setText(primaryLocation);

        Date dateObject = new Date(currentEarthquake.getTimeMilliseconds());

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        String formattedDate = formatDate(dateObject);
        dateView.setText(formattedDate);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        String formattedTime = formatTime(dateObject);
        timeView.setText(formattedTime);

        return listItemView;
    }

    /**
     * Return the formatted date string (ie. "Mar 3, 1984) from a date object
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted time string (ie. "4:30 PM") from a date object
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    /**
     * Return whether location string contains one or more numerical digits
     */
    private boolean locationContainsDigit(String location) {
        return location.matches(REGEX_DIGIT_PATTERN);
    }

    /**
     * Return offset string
     */
    private String createOffset(String location, boolean offsetExists) {
        if (offsetExists) {
            int lastIndexOfOffset = location.indexOf('f');
            return location.substring(0, lastIndexOfOffset + 1);
        } else {
            return getContext().getString(R.string.near_the);
        }
    }

    /**
     * Return primary location string
     */
    private String createPrimaryLocation(String location, boolean offsetExists) {
        if (offsetExists) {
            int lastIndexOfOffset = location.indexOf('f');
            return location.substring(lastIndexOfOffset + 2);
        } else {
            return location;
        }
    }
}
