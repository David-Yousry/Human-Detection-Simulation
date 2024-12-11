package org.example;

import java.util.Date;

public class HumanDetection {
    private String robotId;
    private Location location;
    private String detectionTime;

    public HumanDetection(String robotId, Location location) {
        this.robotId = robotId;
        this.location = location;
        this.detectionTime = new Date().toString();
    }

    public String getRobotId() {
        return robotId;
    }

    public void setRobotId(String robotId) {
        this.robotId = robotId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDetectionTime() {
        return detectionTime;
    }

    public void setDetectionTime(String DetectionTime) {
        this.detectionTime = DetectionTime;
    }

    @Override
    public String toString() {
        return "{" +
                "\"robotId\": \"" + robotId + "\"," +
                "\"location\": " + location + "," +
                "\"detectionTime\": \"" + detectionTime + "\"" +
                "}";
    }
}
