package org.example;

public class Location {
    private double latitude;
    private double longitude;

    //40.714, -73.0095
    public static final double MIN_LATITUDE = 40.71;
    public static final double MAX_LATITUDE = 40.719;
    public static final double MIN_LONGITUDE = -73.0091;
    public static final double MAX_LONGITUDE = -73.0099;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
