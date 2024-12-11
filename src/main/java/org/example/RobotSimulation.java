package org.example;

import java.util.List;
import java.util.Random;

public class RobotSimulation {



    public static Location generateRandomLocation(double minLat, double maxLat,double minLong, double maxLong){
        double latitude = minLat + Math.random() * (maxLat - minLat);
        double longitude = minLong + Math.random() * (maxLong - minLong);
        return new Location(latitude, longitude);
    }


    public static Robot generateRandomRobotLocation(Robot robot){
        Location location = generateRandomLocation(
                Location.MIN_LATITUDE,
                Location.MAX_LATITUDE,
                Location.MIN_LONGITUDE,
                Location.MAX_LONGITUDE);
        robot.setLocation(location);
        return robot;
    }

//    public static void moveRobotRandomly(Robot robot, int seconds) throws InterruptedException {
//        for(int i = 0; i < seconds; i++){
//            Thread.sleep(3000);
//            System.out.println("second: " + i);
//            System.out.println("Robot location: " + robot.getLocation().getLatitude() + ", " + robot.getLocation().getLongitude());
//            double distance = 0.001 + Math.random() * 0.004;
//            System.out.println("Moving robot " + distance + " units");
//            int direction = (int) (Math.random() * 4);
//            switch (direction){
//                case 0:
//                    System.out.println("Moving up");
//                    robot.moveUp(distance);
//                    break;
//                case 1:
//                    System.out.println("Moving down");
//                    robot.moveDown(distance);
//                    break;
//                case 2:
//                    System.out.println("Moving right");
//                    robot.moveRight(distance);
//                    break;
//                case 3:
//                    System.out.println("Moving left");
//                    robot.moveLeft(distance);
//                    break;
//            }
//            EventSimulation.generateEvent(robot);
//            if(robot.getEvent() != null ){
//                Thread.sleep(3000);
//                if(robot.getEvent() != null && !robot.getEvent().getEventType().equals("humanDetection")) {
//                    System.out.println("Event generated at: " + robot.getEvent().getLocation().getLatitude() + ", " + robot.getEvent().getLocation().getLongitude());
//                    break;
//                }
//            }
//        }
//        System.out.println("Robot location: " + robot.getLocation().getLatitude() + ", " + robot.getLocation().getLongitude());
//    }


    public static List<Robot> moveRobotsSimultaneously(List<Robot> robots,int seconds) throws InterruptedException {
        while(seconds > 0){
            Thread.sleep(1000);
            for(Robot robot: robots) {
                if(robot.isMalfunctioned()){
                    System.out.println("cant move malfunctioned robot with id" + robot.getId());
                    continue;
                }
                System.out.println("second: "+ seconds);
                System.out.println("Robot "+  robots.indexOf(robot)+" location: " + robot.getLocation().getLatitude() + ", " + robot.getLocation().getLongitude());
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
            }
            seconds--;

        }
        return robots;

    }

    public static Robot malfuncitonRobot(List<Robot> robots){
        // pick a random robot
        Random random = new Random();
        return robots.get(random.nextInt(robots.size()));

    }

    public static HumanDetection detectHuman(List<Robot> robots){
        Random random = new Random();
        Robot detectionRobot = robots.get(random.nextInt(robots.size()));

        Location humanLocation = generateRandomLocation(
                detectionRobot.getLocation().getLatitude() - 0.001,
                detectionRobot.getLocation().getLatitude() + 0.001,
                detectionRobot.getLocation().getLongitude() - 0.001,
                detectionRobot.getLocation().getLongitude() + 0.001
        );

        return new HumanDetection(detectionRobot.getId(), humanLocation);

    }

}
