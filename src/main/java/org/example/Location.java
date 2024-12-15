package org.example;

public class Location {
    private double latitude;
    private double longitude;

    public static final double MIN_LATITUDE = 29.85;
    public static final double MAX_LATITUDE = 30.20;
    public static final double MIN_LONGITUDE = 30.80;
    public static final double MAX_LONGITUDE = 31.30;
    public static final double DETECTION_LATITUDE_OFFSET = 0.0548;
    public static final double DETECTION_LONGITUDE_OFFSET = 0.063;
    public static final double MAX_MOVEMENT_OFFSET = 0.008;
    public static final double MIN_MOVEMENT_OFFSET = 0.003;


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

    public String toString() {
        return "Lat>" + latitude + "_Long>" + longitude;
    }
}
