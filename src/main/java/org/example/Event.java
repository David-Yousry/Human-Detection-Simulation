package org.example;

public class Event {
    private String eventType;
    private Location location;

    public Event(String eventType, double latitude, double longitude) {
        this.eventType = eventType;
        this.location = new Location(latitude, longitude);
    }

    public String getEventType() {
        return eventType;
    }

    public Location getLocation() {
        return location;
    }

//
}
