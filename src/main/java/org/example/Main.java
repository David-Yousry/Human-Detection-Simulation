package org.example;

import java.util.List;

import static org.example.RobotSimulation.*;


public class Main {


    public static void main(String[] args) throws InterruptedException {






        SubscriberService.connectService();
        PublisherService.connectService();

        List<Robot> newRobots;
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
            //TODO make it sleep for 10 milliseconds, and make the detection percentage so low
            // so that the robots will move more frequently and the detection will be less frequent
            Thread.sleep(2000);



            // TODO make it move for 5 seconds then save in DB
            newRobots = moveRobotsSimultaneously(SubscriberService.robots,5);


            // TODO make the detection a 10% chance
            detection = detect(newRobots);

            SubscriberService.subscribeToRobots();
            SubscriberService.subscribeToHumanDetection();

            PublisherService.publishDetection(detection);
            PublisherService.publishRobots(newRobots);

//            moveRobotsSimultaneously(robots,20);
        }




    }
}
