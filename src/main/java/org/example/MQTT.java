package org.example;

import org.eclipse.paho.client.mqttv3.*;

import java.util.List;

public class MQTT {

    public static int robotsCount = 0;
    public static String techniciansString = "";
    public static boolean once;

    public static void subscribeToRobotCount() {
        String broker = "tcp://localhost:1883";
        String clientId = "JavaSimulator";
        String robotCountTopic = "device/robotCount";

        try {
            MqttClient client = new MqttClient(broker, clientId);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            client.connect(options);

            client.subscribe(robotCountTopic, (topic1, message) -> {
                System.out.println("Received message: " + new String(message.getPayload()));
                robotsCount = Integer.parseInt(new String(message.getPayload()));
                System.out.println("Topic: " + topic1);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        // publish
    }

    public static void publishMalfunctionedRobot( Location robotLocation) {
        String broker = "tcp://localhost:1883";
        String clientId = "JavaSimulator";
        String malfunctionedRobotTopic = "device/malfunctionedRobot";
        try {
            MqttClient client = new MqttClient(broker, clientId);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            client.connect(options);

            MqttMessage message = new MqttMessage();
            message.setPayload(robotLocation.toString().getBytes());
            client.publish(malfunctionedRobotTopic, message);
            client.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //TODO: use this instead after you add id to robot
    /*
    public static void publishMalfunctionedRobot(String robotId, Location robotLocation) {
        String broker = "tcp://localhost:1883";
        String clientId = "JavaSimulator";
        String malfunctionedRobotTopic = "device/malfunctionedRobot";
        try {
            MqttClient client = new MqttClient(broker, clientId);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            client.connect(options);

            MqttMessage message = new MqttMessage();
            // set payload to location + robotID
            message.setPayload(( robotId + robotLocation.toString()).getBytes());
            client.publish(malfunctionedRobotTopic, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    */

}
