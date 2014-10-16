package com.supergotchi.core;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.supergotchi.statsTraits.Stat;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by Andrea on 16/10/2014.
 */
public class SaveLoadUtils {

    static String fileName = "NEW_SAVE.json";

    public static Gotchi loadGotchi(){
        Gotchi gotchi = new Gotchi();
        JsonParser parser = new JsonParser();
        try {
            Reader reader = new FileReader(fileName);
            JsonObject loadedGotchiJson = parser.parse(reader).getAsJsonObject();
            //Loading properties
            gotchi.setName(loadedGotchiJson.get("Name").getAsString());
            gotchi.setDateOfBirth(loadedGotchiJson.get("DateOfBirth").getAsLong());
            gotchi.setSex(loadedGotchiJson.get("sex").getAsInt());
            //Loading stats
            for(Stat stat : gotchi.getStats()){
                JsonObject statObject = loadedGotchiJson.getAsJsonObject(stat.getName());
                stat.setValue(statObject.get("Value").getAsInt());
            }
            //Loading other fields
            gotchi.setHappiness(loadedGotchiJson.get("Happiness").getAsInt());
            gotchi.setDeathChance(loadedGotchiJson.get("DeathChance").getAsDouble());
            gotchi.setCoins(loadedGotchiJson.get("Coins").getAsInt());
            gotchi.setHouseID(loadedGotchiJson.get("HouseID").getAsString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return gotchi;
    }

    public static void save(Gotchi gotchi){
        Gson gson = new Gson();
        JsonObject savedJson = new JsonObject();
        savedJson.addProperty("Name", gotchi.getName());
        savedJson.addProperty("DateOfBirth",gotchi.getDateOfBirth());
        savedJson.addProperty("sex",gotchi.getSex());
        //Save stats
        for(Stat stat : gotchi.getStats()){
            JsonObject currentStat = new JsonObject();
            currentStat.addProperty("Name",stat.getName());
            currentStat.addProperty("Value",stat.getValue());
            savedJson.add(stat.getName(), currentStat);
        }
        //Back to the other fields
        savedJson.addProperty("Happiness",gotchi.getHappiness());
        savedJson.addProperty("DeathChance",gotchi.getDeathChance());
        savedJson.addProperty("Coins",gotchi.getCoins());
        savedJson.addProperty("HouseID", gotchi.getHouseID());
        try {
            Writer writer = new FileWriter(fileName);
            gson.toJson(savedJson, writer);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    public static boolean savedGameExists(){
        File file = new File(fileName);
        return file.exists();
    }

}
