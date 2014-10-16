package com.supergotchi.persistency;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.supergotchi.core.Gotchi;
import com.supergotchi.core.House;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Locations {
    public static ArrayList<House> locations = new ArrayList<House>();
    public static String fileName = "LOCATIONS.json";



    public static House getHouse(String houseID){
        for(House house : locations){
            if(house.getID().equalsIgnoreCase(houseID)) return house;
        }
        return null;
    }

    public static void loadLocations(){
        JsonParser parser = new JsonParser();
        Reader reader = null;
        try {
            reader = new FileReader(fileName);
            JsonArray arrayOfLocations = parser.parse(reader).getAsJsonArray();
            for(JsonElement location : arrayOfLocations){
                House house = new House();
                JsonObject locationObject = location.getAsJsonObject();
                house.setID(locationObject.get("ID").getAsString());
                if (locationObject.has("furnitures")) {
                    ArrayList<String> items = new Gson().fromJson(locationObject.get("furnitures"), new TypeToken<List<String>>(){}.getType());
                    house.setFurnitures(items);
                }
                house.setHouseValue(locationObject.get("HouseValue").getAsInt());
                locations.add(house);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void saveLocations(){
        JsonArray locationsJson = new JsonArray();
        for(House house : locations) {
            JsonObject houseObject = new JsonObject();
            houseObject.addProperty("ID", house.getID());
            houseObject.addProperty("HouseValue", house.getHouseValue());
            //Furniture
            JsonArray itemList = new JsonArray();
            ArrayList<String> furnitures = house.getFurnitures();
            if (furnitures.size() > 0) {
                for (String item : furnitures) {
                    JsonPrimitive itemJson = new JsonPrimitive(item);
                    itemList.add(itemJson);
                }
                houseObject.add("furnitures", itemList);
            }
            locationsJson.add(houseObject);
        }
        Gson gson = new Gson();
        try {
            Writer writer = new FileWriter(fileName);
            gson.toJson(locationsJson, writer);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
