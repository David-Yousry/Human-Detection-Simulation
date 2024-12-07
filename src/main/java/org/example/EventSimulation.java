package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventSimulation {

    public static List<Technician> technicians;


    public static void generateEvent(Robot robot) {
        Random random = new Random();
        int eventHappens = random.nextInt(0,10);
        if (eventHappens>5) return;

        String[] eventTypes = {"humanDetection", "lowBattery", "maintenance"};
        String eventType = eventTypes[random.nextInt(eventTypes.length)];
        System.out.println("Event type: " + eventType);

        robot.setEvent( new Event(eventType, robot.getLocation().getLatitude(), robot.getLocation().getLongitude()));

    }

    // Calculate distance between two locations using Haversine formula
    public static double calculateDistance(Location loc1, Location loc2) {
        final int R = 6371; // Earth radius in kilometers
        double latDistance = Math.toRadians(loc2.getLatitude() - loc1.getLatitude());
        double lonDistance = Math.toRadians(loc2.getLongitude() - loc1.getLongitude());
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(loc1.getLatitude())) * Math.cos(Math.toRadians(loc2.getLatitude()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    // Find the nearest technician
    public static Technician findNearestTechnician(Location eventLocation) {
        Technician nearestTech = null;
        double shortestDistance = Double.MAX_VALUE;

        for (Technician tech : technicians) {
            double distance = calculateDistance(eventLocation, tech.getLocation());
            if (distance < shortestDistance && !tech.isOnDuty()) {
                shortestDistance = distance;
                nearestTech = tech;
            }
        }
        nearestTech.setOnDuty(true);
        return nearestTech;
    }


    public static void moveTechnician(Technician technician, Location targetLocation) throws InterruptedException {
        double totalDistance = calculateDistance(technician.getLocation(), targetLocation);
        double remainingDistance = totalDistance;

        while (remainingDistance > 0.0001) {

            double stepSize = Math.min(remainingDistance, 0.01); // Larger step size (0.0005 degrees)

            // Calculate the step in latitude and longitude
            double latStep = (targetLocation.getLatitude() - technician.getLocation().getLatitude()) * stepSize / totalDistance;
            double lonStep = (targetLocation.getLongitude() - technician.getLocation().getLongitude()) * stepSize / totalDistance;

            // Move technician toward the robot
            technician.getLocation().setLatitude(technician.getLocation().getLatitude() + latStep);
            technician.getLocation().setLongitude(technician.getLocation().getLongitude() + lonStep);

            remainingDistance = calculateDistance(technician.getLocation(), targetLocation);

            System.out.println("Technician " + technician.getName() + " moved to ["
                    + technician.getLocation().getLatitude() + ", "
                    + technician.getLocation().getLongitude() + "]");

            // Simulate time delay for each movement
            Thread.sleep(1); // Adjust sleep time to control speed
        }

        technician.setLocation(targetLocation);
        System.out.println("Technician Location: " + technician.getLocation().getLatitude() + ", " + technician.getLocation().getLongitude());

        System.out.println("Technician " + technician.getName() + " has reached the target location.");
        technician.setOnDuty(false);
    }




    // Simulate maintenance duration
    public static int maintenanceDuration() {
        return new Random().nextInt(5) + 1; // Random duration between 1 and 5 minutes
    }
}
