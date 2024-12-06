package org.example;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        EventSimulation simulation = new EventSimulation();

        simulation.addTechnician(new Technician("Tech1", 40.7128, -73.00905)); // New York
        simulation.addTechnician(new Technician("Tech2", 34.0522, -118.2437)); // Los Angeles
        simulation.addTechnician(new Technician("Tech3", 51.5074, -0.1278)); // London
        simulation.addTechnician(new Technician("Tech4", 48.8566, 2.3522)); // Paris
        simulation.addTechnician(new Technician("Tech5", 35.6895, 139.6917)); // Tokyo

        // Generate a random maintenance event and robot
        Robot robot = new Robot("Maintenance");
        Location robotLocation = new Location(40.714, -73.0095); // Example location (New York)
        robot.setLocation(robotLocation);

        System.out.println("Robot generated with status: " + robot.getStatus());
        System.out.println("Robot's initial location: " + robot.getLocation().getLatitude() + ", " + robot.getLocation().getLongitude());

        // Find the nearest technician
        Technician nearestTech = simulation.findNearestTechnician(robot.getLocation());
        System.out.println("Nearest technician: " + nearestTech.getName());

        // Move technician to the robot's loc
        simulation.moveTechnician(nearestTech, robot.getLocation());

        // Simulate maintenance duration
        int duration = simulation.maintenanceDuration();
        System.out.println("Maintenance duration: " + duration + " minutes.");

    }
}
