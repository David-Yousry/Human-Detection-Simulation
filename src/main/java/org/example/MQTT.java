package org.example;

import org.eclipse.paho.client.mqttv3.*;

import java.util.List;

public class MQTT {

    public static int robotsCount = 0;
    public static String techniciansString = "";

    public static void main(String[] args) {
        String broker = "tcp://localhost:1883";
        String clientId = "JavaSimulator";
        String robotCountTopic = "device/robotCount";
        String techniciansTopic = "device/technicians";

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

            client.subscribe(techniciansTopic, (topic1, message) -> {
//                System.out.println("Received message: " + new String(message.getPayload()));
                techniciansString = new String(message.getPayload());
                EventSimulation.technicians = MyJsonParser.createTechniciansFromJson(techniciansString);
                System.out.println("Topic: " + topic1);
            });

//            client.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    public static void suscribeToTechnicians(String techniciansTopic) {
//        String broker = "tcp://localhost:1883";
//        String clientId = "JavaSimulator";
//        try {
//            MqttClient client = new MqttClient(broker, clientId);
//            MqttConnectOptions options = new MqttConnectOptions();
//            options.setCleanSession(true);
//            client.connect(options);
//
//            client.subscribe(techniciansTopic, (topic1, message) -> {
//                System.out.println("Received message: " + new String(message.getPayload()));
//                technicians = new String(message.getPayload());
//                System.out.println("Topic: " + topic1);
//            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//    }
}
