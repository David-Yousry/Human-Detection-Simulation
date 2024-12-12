package org.example;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.List;

public class PublisherService {

    static MqttClient client;

    // connection to MQTT broker
    public static void connectService(){
        try{
            client = new MqttClient(Constants.MQTT_URI, "Publisher");
            client.connect();
            System.out.println("Publisher connected to MQTT broker");
        }catch (MqttException e) {
            e.printStackTrace();
        }
    }


    public static void publishRobots(List<Robot> robots){
        String robotTopic = "device/robots";
        try {
            MqttMessage message = new MqttMessage();
            message.setPayload(robots.toString().getBytes());

            client.publish(robotTopic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public static void publishDetection(Detection detection){
        String DetectionTopic = "device/Detection";
        try {
            MqttMessage message = new MqttMessage();
            message.setPayload(detection.toString().getBytes());

            client.publish(DetectionTopic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


}
