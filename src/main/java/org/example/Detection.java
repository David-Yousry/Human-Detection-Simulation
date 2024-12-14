package org.example;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Detection {
    private String robotId;
    private Location location;
    private String detectionTime;
    private String detectionType;

    public Detection(String robotId, Location location, String detectionType) {
        this.robotId = robotId;
        this.location = location;
        this.detectionType = detectionType;
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        this.detectionTime = isoFormat.format(new Date());
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

    public String getDetectionType() {
        return detectionType;
    }

    public void setDetectionType(String detectionType) {
        this.detectionType = detectionType;
    }

    @Override
    public String toString() {
        return "{" +
                "\"detectionType\": \"" + detectionType + "\"," +
                "\"robotId\": \"" + robotId + "\"," +
                "\"location\": " + location + "," +
                "\"detectionTime\": \"" + detectionTime + "\"" +
                "}";
    }
}
