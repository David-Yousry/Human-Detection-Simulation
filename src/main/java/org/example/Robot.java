package org.example;

public class Robot {
    private Location location;
    private String status;
    private int batteryLevel;


    public Robot(String status) {
        this.status = status;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public void moveUp(double distance){
        this.location.setLongitude(this.location.getLongitude()+distance);
    }

    public void moveDown(double distance){
        this.location.setLongitude(this.location.getLongitude()-distance);
    }

    public void moveRight(double distance){
        this.location.setLatitude(this.location.getLatitude()+distance);
    }

    public void moveLeft(double distance){
        this.location.setLatitude(this.location.getLatitude()-distance);
    }



}
