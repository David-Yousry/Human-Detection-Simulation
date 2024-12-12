package org.example;

import java.util.List;

import static org.example.RobotSimulation.*;


public class Main {


    public static void main(String[] args) throws InterruptedException {

        SubscriberService.connectService();
        PublisherService.connectService();

        List<Robot> newRobots;
//        Robot malfunctionedRobot;
        Detection detection;

        SubscriberService.fetchRobots();
        while(SubscriberService.robots == null || SubscriberService.robots.isEmpty()){
            System.out.println("No robots available retrying in 20 seconds");
            Thread.sleep(20000);
            SubscriberService.fetchRobots();
        }

        for(Robot robot: SubscriberService.robots){
            if(robot.getLocation() == null) {
                RobotSimulation.generateRandomRobotLocation(robot);
                System.out.println("new location"+robot.getLocation());
            }
            else{
                System.out.println("we have it already:" + robot.getLocation());
            }
        }


        while (true) {
            Thread.sleep(5000);



            newRobots = moveRobotsSimultaneously(SubscriberService.robots,2);


            // to malfunction a robot
//            malfunctionedRobot = malfuncitonRobot(newRobots);
//            malfunctionedRobot.setMalfunctioned(true);
//            for (Robot robot : newRobots) {
//                System.out.println(robot.isMalfunctioned());
//            }

            detection = detect(newRobots);

            SubscriberService.subscribeToRobots();
            SubscriberService.subscribeToHumanDetection();

            PublisherService.publishDetection(detection);
            PublisherService.publishRobots(newRobots);

//            moveRobotsSimultaneously(robots,20);
        }




    }
}
