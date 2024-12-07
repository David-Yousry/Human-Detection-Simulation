package org.example;

import java.util.List;

public class RobotSimulation {


    public static Location generateRandomLocation(double minLat, double maxLat,double minLong, double maxLong){
        double latitude = minLat + Math.random() * (maxLat - minLat);
        double longitude = minLong + Math.random() * (maxLong - minLong);
        return new Location(latitude, longitude);
    }

    public static int generateBatteryLevel(int min, int max){
        return (int) (min + Math.random() * (max - min));
    }

    public static Robot generateRandomRobot(){
        Location location = generateRandomLocation(
                Location.MIN_LATITUDE,
                Location.MAX_LATITUDE,
                Location.MIN_LONGITUDE,
                Location.MAX_LONGITUDE);
        Robot robot = new Robot("Maintenance");
        robot.setLocation(location);
        robot.setBatteryLevel(generateBatteryLevel(50, 80));
        return robot;
    }

    public static void moveRobotRandomly(Robot robot, int seconds) throws InterruptedException {
        robot.setEvent(null);
        for(int i = 0; i < seconds; i++){
            Thread.sleep(3000);
            System.out.println("second: " + i);
            System.out.println("Robot location: " + robot.getLocation().getLatitude() + ", " + robot.getLocation().getLongitude());
            double distance = 0.001 + Math.random() * 0.004;
            System.out.println("Moving robot " + distance + " units");
            int direction = (int) (Math.random() * 4);
            switch (direction){
                case 0:
                    System.out.println("Moving up");
                    robot.moveUp(distance);
                    break;
                case 1:
                    System.out.println("Moving down");
                    robot.moveDown(distance);
                    break;
                case 2:
                    System.out.println("Moving right");
                    robot.moveRight(distance);
                    break;
                case 3:
                    System.out.println("Moving left");
                    robot.moveLeft(distance);
                    break;
            }
            EventSimulation.generateEvent(robot);
            if(robot.getEvent() != null ){
                Thread.sleep(3000);
                if(robot.getEvent() != null && !robot.getEvent().getEventType().equals("humanDetection")) {
                    System.out.println("Event generated at: " + robot.getEvent().getLocation().getLatitude() + ", " + robot.getEvent().getLocation().getLongitude());
                    break;
                }
            }
        }
        System.out.println("Robot location: " + robot.getLocation().getLatitude() + ", " + robot.getLocation().getLongitude());
    }


    public static void moveRobotsSimultaneously(List<Robot> robots) throws InterruptedException {
        while(true){
            Thread.sleep(1);
            for(Robot robot: robots) {
                if(!robot.isMovable()) continue;
                robot.setEvent(null);
                System.out.println("second: ");
                System.out.println("Robot"+  robots.indexOf(robot)+" location: " + robot.getLocation().getLatitude() + ", " + robot.getLocation().getLongitude());
                double distance = 0.001 + Math.random() * 0.004;
                System.out.println("Moving robot " + distance + " units");
                int direction = (int) (Math.random() * 4);
                switch (direction) {
                    case 0:
                        System.out.println("Moving up");
                        robot.moveUp(distance);
                        break;
                    case 1:
                        System.out.println("Moving down");
                        robot.moveDown(distance);
                        break;
                    case 2:
                        System.out.println("Moving right");
                        robot.moveRight(distance);
                        break;
                    case 3:
                        System.out.println("Moving left");
                        robot.moveLeft(distance);
                        break;
                }
                EventSimulation.generateEvent(robot);
                if (robot.getEvent() != null) {
                    Thread.sleep(3000);
                    if (robot.getEvent() != null && !robot.getEvent().getEventType().equals("humanDetection")) {
                        robot.setMovable(false);
                        System.out.println("Event for robot"+  robots.indexOf(robot)+" generated at: " + robot.getEvent().getLocation().getLatitude() + ", " + robot.getEvent().getLocation().getLongitude());
                        MultiThreading multiThreading = new MultiThreading(robot, robots.indexOf(robot));
                        multiThreading.start();

                    }
                }
            }

        }

    }

}
