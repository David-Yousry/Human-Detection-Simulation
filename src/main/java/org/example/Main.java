package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static org.example.RobotSimulation.*;


public class Main {


    public static void main(String[] args) throws InterruptedException {

//        EventSimulation.addTechnician(new Technician("Tech1")); // New York
//        EventSimulation.addTechnician(new Technician("Tech2")); // Los Angeles
//        EventSimulation.addTechnician(new Technician("Tech3")); // London
//        EventSimulation.addTechnician(new Technician("Tech4")); // Paris
//        EventSimulation.addTechnician(new Technician("Tech5")); // Tokyo

        // Generate a random maintenance event and robot

//        Robot robot = generateRandomRobot();
        List<Robot> robots = new ArrayList<>();
        int robotsCount;
        // remove this later when we handle the random location generation
//        Location robotLocation = new Location(40.714, -73.0095); // Example location (New York)
//        robot.setLocation(robotLocation);
        boolean techniciansPrinted = false;


        while (true) {

            Thread.sleep(500);
            MQTT.main(null);

            if(EventSimulation.technicians==null){
                System.out.println("No technicians available");
                continue;
            }else{
                if(!techniciansPrinted) {
                    techniciansPrinted = true;
                    for (Technician technician : EventSimulation.technicians) {
                        System.out.println(technician.getId() + ",, " + technician.getName());
                    }
                }
            }

            if (MQTT.robotsCount == 0) {
                System.out.println("No robots available");
                continue;
            }

            robotsCount = MQTT.robotsCount;
            System.out.println("Robots count: " + robotsCount);

            while (robotsCount > 0) {
                robots.add(generateRandomRobot());
                robotsCount--;
            }
            robotsCount = MQTT.robotsCount;

//            while(robotsCount > 0){
//                MultiThreading multiThreading = new MultiThreading(robots.get(robotsCount-1),robotsCount);
//                multiThreading.start();
//                robotsCount--;
//            }

            moveRobotsSimultaneously(robots);


//            System.out.println("######################################################################################");

//            System.out.println("Robot location: " + robot.getLocation().getLatitude() + ", " + robot.getLocation().getLongitude());
//            System.out.println("Robot battery level: " + robot.getBatteryLevel());
//            moveRobotRandomly(robot, 10);


//            System.out.println("Robot generated with status: " + robot.getStatus());
//            System.out.println("Robot's initial location: " + robot.getLocation().getLatitude() + ", " + robot.getLocation().getLongitude());

            // Find the nearest technician
//            Technician nearestTech = simulation.findNearestTechnician(robot.getLocation());
//            System.out.println("Nearest technician: " + nearestTech.getName());
//
//            // Move technician to the robot's loc
//            simulation.moveTechnician(nearestTech, robot.getLocation());
//
//            // Simulate maintenance duration
//            int duration = simulation.maintenanceDuration();
//            System.out.println("Maintenance duration: " + duration + " minutes.");

//            System.out.println("######################################################################################");
        }


    }
}
