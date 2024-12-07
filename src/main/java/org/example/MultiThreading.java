package org.example;


public class MultiThreading extends Thread {

//    EventSimulation simulation = new EventSimulation();

    private Robot robot;
    private Technician technician;
    private int threadNumber;

    public MultiThreading(Robot robot, int threadNumber) {
        this.robot = robot;
        this.threadNumber = threadNumber;
    }

    public void setTechnician( Technician technician){
        this.technician = technician;
    }

    public void setRobot( Robot robot){
        this.robot = robot;
    }



    public void run() {

            try {
                System.out.println("#################### Hello from thread "+ threadNumber +"#####################################");
                System.out.println("Hello from thread "+ threadNumber +"####"+"Robot"+" location: " + robot.getLocation().getLatitude() + ", " + robot.getLocation().getLongitude());
                System.out.println("Hello from thread "+ threadNumber +"####"+"Robot battery level: " + robot.getBatteryLevel());

                Technician nearestTech = EventSimulation.findNearestTechnician(robot.getLocation());
                System.out.println("Hello from thread "+ threadNumber +"####"+"Nearest technician: " + nearestTech.getName());
                EventSimulation.moveTechnician(nearestTech, robot.getLocation());
                int duration = EventSimulation.maintenanceDuration();
                System.out.println("Hello from thread "+ threadNumber +"####"+"Maintenance duration: " + duration + " minutes.");
                robot.setMovable(true);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

    }

}


//                System.out.println("############################# Hello from Thread "+ threadNumber + " #############################");
//
//                System.out.println("Robot location: " + robot.getLocation().getLatitude() + ", " + robot.getLocation().getLongitude());
//                System.out.println("Robot battery level: " + robot.getBatteryLevel());
//                moveRobotRandomly(robot, 10);
//                if(robot.getEvent() == null ){
//                    System.out.println("Event generated at: " + robot.getEvent().getLocation().getLatitude() + ", " + robot.getEvent().getLocation().getLongitude());
//                    continue;
//                }
//                Technician nearestTech = simulation.findNearestTechnician(robot.getLocation());
//                System.out.println("Nearest technician: " + nearestTech.getName());
//
//                // Move technician to the robot's loc
//                simulation.moveTechnician(nearestTech, robot.getLocation());
//
//                // Simulate maintenance duration
//                int duration = simulation.maintenanceDuration();
//                System.out.println("Maintenance duration: " + duration + " minutes.");