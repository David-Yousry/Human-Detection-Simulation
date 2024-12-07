package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.Technician;

import java.util.ArrayList;
import java.util.List;

public class MyJsonParser {


//    public static void main(String[] args) {
//        if(MQTT.technicians.isEmpty()){
//            System.out.println("No technicians available");
//            return;
//        }
//        String jsonString = MQTT.technicians;
//
//        List<Technician> technicians = createTechniciansFromJson(jsonString);
//        for (Technician technician : technicians) {
//            System.out.println(technician.getId());
//        }
//    }

    public static List<Technician> createTechniciansFromJson(String jsonString) {
        List<Technician> technicians = new ArrayList<>();

        try {
            // Parse the JSON string to a JsonArray
            JsonArray jsonArray = JsonParser.parseString(jsonString).getAsJsonArray();

            // Iterate through the JsonArray
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();

                // Extract the _id field
                if (jsonObject.has("_id")) {
                    String id = jsonObject.get("_id").getAsString();

                    // Create a Technician object with the id and add it to the list
                    technicians.add(new Technician(id));
                }
            }
        } catch (Exception e) {
            System.out.println("Error parsing JSON: " + e.getMessage());
        }
        return Technician.setupTechnicians(technicians);
    }
}
