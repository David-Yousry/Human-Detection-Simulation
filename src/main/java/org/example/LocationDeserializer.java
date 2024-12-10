package org.example;

import com.google.gson.*;
import java.lang.reflect.Type;

public class LocationDeserializer implements JsonDeserializer<Location> {
    @Override
    public Location deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String locationString = json.getAsString(); // Extract the raw string
        String[] parts = locationString.split("_");

        // Parse the latitude and longitude
        double latitude = Double.parseDouble(parts[0].replace("Lat>", ""));
        double longitude = Double.parseDouble(parts[1].replace("Long>", ""));

        // Create and return a Location object
        return new Location(latitude,longitude);
    }
}
