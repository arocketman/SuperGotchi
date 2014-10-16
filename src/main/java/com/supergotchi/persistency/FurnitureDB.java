package com.supergotchi.persistency;

import com.google.gson.*;
import com.supergotchi.furnitures.Bed;
import com.supergotchi.furnitures.Fridge;
import com.supergotchi.furnitures.Furniture;
import com.supergotchi.furnitures.Toilet;

import java.io.*;
import java.util.ArrayList;

public class FurnitureDB {
    public static ArrayList<Furniture> furnitures = new ArrayList<Furniture>();
    public static final String FURNITURE_FILESAVE = "furniture.json";

    public static Furniture getFurniture(String furnitureID){
        for(Furniture furniture : furnitures){
            if(furniture.getID().equalsIgnoreCase(furnitureID)) return furniture;
        }
        return null;
    }

    /**
     * Loads furniture array from the furniture.json file.
     */
    public static void loadFurnitures(){
        JsonParser parser = new JsonParser();
        Reader reader = null;
        try {
            reader = new FileReader(FURNITURE_FILESAVE);
            JsonArray arrayOfFurniture = parser.parse(reader).getAsJsonArray();
            for(JsonElement furniture : arrayOfFurniture){
                JsonObject furnitureObject = furniture.getAsJsonObject();
                Furniture concreteFurniture = getFurnitureClassFromJson(furnitureObject);
                concreteFurniture.setCost(furnitureObject.get("Cost").getAsInt());
                concreteFurniture.setName(furnitureObject.get("Name").getAsString());
                concreteFurniture.setFurnitureID(furnitureObject.get("furnitureID").getAsString());
                concreteFurniture.setModifiedStat(furnitureObject.get("ModifiedStat").getAsString());
                concreteFurniture.setModifier(furnitureObject.get("Modifier").getAsInt());
                furnitures.add(concreteFurniture);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    /**
     * This saves the furnitures onto a file. This is supposed to be used just the one time. //TODO: Probably needs a better solution
     */
    public static void saveFurnitures(){
        furnitureDB();
        JsonArray furnituresJson = new JsonArray();
        for(Furniture furniture : furnitures) {
            JsonObject furnitureObject = new JsonObject();
            furnitureObject.addProperty("Name", furniture.getName());
            furnitureObject.addProperty("Cost", furniture.getCost());
            furnitureObject.addProperty("Modifier", furniture.getModifier());
            furnitureObject.addProperty("ModifiedStat", furniture.getModifiedStat());
            furnitureObject.addProperty("furnitureID", furniture.getID());
            furnitureObject.addProperty("ClassName", furniture.getClass().getName());
            furnituresJson.add(furnitureObject);
        }
        Gson gson = new Gson();
        try {
            Writer writer = new FileWriter(FURNITURE_FILESAVE);
            gson.toJson(furnituresJson, writer);
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Just holds the furniture, used only when saving. TEST DATA. //TODO: Needs to be deleted and replaced with a single furniture adding method.
     */
    public static void furnitureDB(){
        //Bedroom
        furnitures.add(new Bed("Cheap bed",1000,30));
        furnitures.add(new Bed("Comfy bed",3000,50));
        furnitures.add(new Bed("Great bed",5000,70));
        furnitures.add(new Bed("Royal bed",10000,100));
        //Kitchen
        furnitures.add(new Fridge("Cheap fridge",1000,30));
        furnitures.add(new Fridge("Good  fridge",3000,50));
        furnitures.add(new Fridge("Great fridge",5000,70));
        furnitures.add(new Fridge("Royal fridge",10000,100));
        //Bathroom
        //TODO: The modifier should be always the same, but how is a toilet better than another one really?
        furnitures.add(new Toilet("Cheap toilet",1000,100));
        furnitures.add(new Toilet("Good  toilet",3000,100));
        furnitures.add(new Toilet("Great toilet",5000,100));
        furnitures.add(new Toilet("Royal toilet",10000,100));
    }

    /**
     * Used to get a child of Furniture (ex: Bed, Fridge) from the JsonFile.
     * @param object the json Object to get the stuff with.
     * @return the furniture.
     */
    public static Furniture getFurnitureClassFromJson(JsonObject object){
        try {
            return (Furniture)Class.forName(object.get("ClassName").getAsString().replaceAll("\"","")).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
