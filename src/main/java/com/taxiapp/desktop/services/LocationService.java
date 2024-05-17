package com.taxiapp.desktop.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LocationService {
    public static Coordinates parseCoordinatesFromUrl(String url) {
        Pattern pattern = Pattern.compile("@([\\d.-]+),([\\d.-]+)");

        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            double latitude = Double.parseDouble(matcher.group(1));
            double longitude = Double.parseDouble(matcher.group(2));
            return new Coordinates(latitude, longitude);
        }
        return null;
    }

    public static class Coordinates {
        public double latitude;
        public double longitude;

        Coordinates(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return "{\"latitude\":" + latitude + ", \"longitude\":" + longitude + "}";
        }

    }
}
