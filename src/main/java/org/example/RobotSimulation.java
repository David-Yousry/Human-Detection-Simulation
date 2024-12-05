package org.example;

public class RobotSimulation {

    public static void main(String[] args) throws InterruptedException {
        Robot robot = generateRandomRobot();
        System.out.println("Robot location: " + robot.getLocation().getLatitude() + ", " + robot.getLocation().getLongitude());
        System.out.println("Robot battery level: " + robot.getBatteryLevel());
        moveRobotRandomly(robot, 10);

    }

    public static Location generateRandomLocation(double minLat, double maxLat,double minLong, double maxLong){
        double latitude = minLat + Math.random() * (maxLat - minLat);
        double longitude = minLong + Math.random() * (maxLong - minLong);
        return new Location(latitude, longitude);
    }

    public static int generateBatteryLevel(int min, int max){
        return (int) (min + Math.random() * (max - min));
    }

    public static Robot generateRandomRobot(){
        Location location = generateRandomLocation(-90, 90, -180, 180);
        Robot robot = new Robot("Idle");
        robot.setLocation(location);
        robot.setBatteryLevel(generateBatteryLevel(50, 80));
        return robot;
    }

    public static void moveRobotRandomly(Robot robot, int seconds) throws InterruptedException {
        for(int i = 0; i < seconds; i++){
            Thread.sleep(5000);
            System.out.println("second: " + i);
            System.out.println("Robot location: " + robot.getLocation().getLatitude() + ", " + robot.getLocation().getLongitude());
            double distance = 10 + Math.random() * 90;
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
        }
        System.out.println("Robot location: " + robot.getLocation().getLatitude() + ", " + robot.getLocation().getLongitude());
    }

}
