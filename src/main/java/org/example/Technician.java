package org.example;

public class Technician {
    private String name;
    private Location location;

    public Technician(String name, double latitude, double longitude) {
        this.name = name;
        this.location = new Location(latitude, longitude);
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
