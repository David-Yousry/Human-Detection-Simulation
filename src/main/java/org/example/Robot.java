package org.example;

public class Robot {
    private String id;
    private Location location;
    private boolean isMalfunctioned = false;





    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isMalfunctioned() {
        return isMalfunctioned;
    }

    public void setMalfunctioned(boolean isMovable) {
        this.isMalfunctioned = isMovable;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": \"" + id + "\"/" +
                "\"location\": " + location + "/" +
                "\"isMalfunctioned\": " + isMalfunctioned +
                "}";
    }



}
