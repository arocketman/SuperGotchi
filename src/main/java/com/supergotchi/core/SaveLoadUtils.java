package com.supergotchi.core;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.supergotchi.persistency.Locations;
import com.supergotchi.statsTraits.Stat;

import java.io.*;

/**
 * Handles the save and load of the gotchi.
 */
public class SaveLoadUtils {

    static String GOTCHI_PROFILES_FOLDER = new File("").getAbsolutePath() + "//profiles//";

    /**
     * Loads a gotchi from the file NEW_SAVE.json
     * @return
     */
    public static Gotchi loadGotchi(String ID){
        Gotchi gotchi = new Gotchi();
        JsonParser parser = new JsonParser();
        try {
            Reader reader = new FileReader(GOTCHI_PROFILES_FOLDER + ID + ".json");
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
            gotchi.setCoins(loadedGotchiJson.get("Coins").getAsInt());
            gotchi.setHouseID(loadedGotchiJson.get("HouseID").getAsString());
            gotchi.setCurrentPosition(Locations.getIndexFromID(gotchi.getHouseID()));
            gotchi.setAlive(loadedGotchiJson.get("Alive").getAsBoolean());
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
        savedJson.addProperty("Coins",gotchi.getCoins());
        savedJson.addProperty("HouseID", gotchi.getHouseID());
        savedJson.addProperty("Alive",gotchi.isAlive());
        try {
            Writer writer = new FileWriter(GOTCHI_PROFILES_FOLDER + gotchi.getName() + ".json");
            gson.toJson(savedJson, writer);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    public static boolean savedGameExists(String gotchiName){
        File file = new File(GOTCHI_PROFILES_FOLDER + gotchiName + ".json");
        return file.exists();
    }

}
