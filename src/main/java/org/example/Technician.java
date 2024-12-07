package org.example;

import java.util.List;
import java.util.Random;

public class Technician {
    private String id;
    private String name;
    private Location location;
    private boolean onDuty;
    private static int counter = 0;


    private static final Location techLocation1 = new Location(40.71285, -73.00905); // New York
    private static final Location techLocation2 = new Location(40.7129, -73.0091); // Los Angeles
    private static final Location techLocation3 = new Location(40.71280, -73.00901); // London
    private static Location techLocation4 = new Location(48.8566, 2.3522); // Paris
    private static Location techLocation5 = new Location(35.6895, 139.6917); // Tokyo
    private static final List<Location> techLocations = List.of(techLocation1, techLocation2, techLocation3);

    public Technician(String id) {
        this.id = id;
        this.name = "Technician " + (++counter);
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

    public boolean isOnDuty() {
        return onDuty;
    }

    public void setOnDuty(boolean onDuty) {
        this.onDuty = onDuty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static List<Technician> setupTechnicians(List<Technician> techs) {
        Random random = new Random();
        for (Technician tech : techs) {
            tech.setLocation(techLocations.get(random.nextInt(techLocations.size())));
        }
        return techs;
    }
}
