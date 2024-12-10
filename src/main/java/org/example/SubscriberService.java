package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.*;
import org.bson.Document;
import org.eclipse.paho.client.mqttv3.MqttClient;

import java.util.ArrayList;
import java.util.List;

public class SubscriberService {

    static MongoCollection<Document> robotsCollection;
    static MqttClient client;
    static MongoClient mongoClient;
    static MongoDatabase database;
    static List<Robot> robots;

    public static void connectService() {
        try {
            client = new MqttClient(Constants.MQTT_URI, "Subscriber");
            client.connect();
            System.out.println("Subscriber connected to MQTT broker");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int fetchRobots() {
        String uri = Constants.DB_URI;
        Gson gson = new GsonBuilder().create();
        try {
            mongoClient = MongoClients.create(uri);
            database = mongoClient.getDatabase("DB");
            robotsCollection = database.getCollection("robots");
            robots = new ArrayList<>();
            FindIterable<Document> documents = robotsCollection.find();



            for(Document document : documents){
                String json = document.toJson();
                Robot robot = gson.fromJson(json, Robot.class);
                robots.add(robot);
            }

            robots.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return robots.size();
    }


    public static void subscribeToRobots() {
        System.out.println("Subscribing to robots");
        String robotTopic = "device/robots";
        try {
            client.subscribe(robotTopic, (topic1, message) -> {
                String payload = new String(message.getPayload());
                payload = payload.substring(1, payload.length() - 1);
                // robot1.toString(), robot2.toString(), robot3.toString()
                robots = new ArrayList<>();
                String[] parts = payload.split(",");
                // robot1.toString()
                //{"id": "1"/"location": Lat>40.71783978833001_Long>-73.00914826748333/"isMalfunctioned": false}
                //robot2.toString()
                // robot3.toString()
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(Location.class, new LocationDeserializer())
                        .create();
                robots.clear();
                for(String part : parts){
                    String jsonString = part.replace("/\"", ",\"");
                    Robot robot = gson.fromJson(jsonString, Robot.class);
                    robots.add(robot);
                }
                robots.forEach(System.out::println);

                InsertRobotsInDB(robots);

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void InsertRobotsInDB(List<Robot> robots){
        Gson gson = new Gson();
        String json;
        Document document;
        for(Robot robot: robots){
            json = gson.toJson(robot);
            document = Document.parse(json);
            robotsCollection.updateOne(new Document("id", robot.getId()), new Document("$set", document));
            System.out.println("Robot"+robot.getId()+" saved successfully!");
        }
    }





}
