package com.supergotchi.core;

import com.google.gson.*;
import com.supergotchi.furnitures.Furniture;
import com.supergotchi.statsTraits.Stat;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

public class SaveLoadUtilities {
    private static final String SAVED_FILE = "savedGame.json";

    /**
     * Saves the gotchi onto the savedGame file
     * @param gotchi the gotchi to be saved
     */
    public static void saveGotchi(Gotchi gotchi){
        Gson gson = new Gson();
        String json = gson.toJson(gotchi);
        try {
            FileWriter fw = new FileWriter(SAVED_FILE);
            fw.write(json);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes care of the whole loading of the gotchi
     * @return the loaded gotchi
     */
    public static Gotchi loadGotchi(){
        Gson gson = getBuiltGson();
        String json = null;
        try {
            FileReader fr = new FileReader(SAVED_FILE);
            BufferedReader br = new BufferedReader(fr);
            json = br.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gotchi loadedGotchi = gson.fromJson(json,Gotchi.class);
        System.out.println("hi");
        return loadedGotchi;
    }

    /**
     * Checks if there's a pre-existing saved game.
     * @return
     */
    public static boolean savedGameExists(){
        File file = new File(SAVED_FILE);
        return file.exists();
    }

    /**
     * Builds the Gson with all type adapters needed.
     * @return the built Gson with all the adapters utilized to correctly deserialize the class
     */
    public static Gson getBuiltGson(){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Stat.class, new StatDeserializer());
        builder.registerTypeAdapter(Furniture.class, new FurnitureDeserializer());
        return builder.create();
    }

    public static String removeQuotes(String inputString){
        return inputString.replace("\"","");
    }
}

/**
 * Used to deserialize a Stat (abstract).
 */
class StatDeserializer implements JsonDeserializer<Stat>{

    @Override
    public Stat deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject object = jsonElement.getAsJsonObject();
        String name = object.get("Name").toString();
        double value = object.get("Value").getAsDouble();
        //Fixing strings
        name = SaveLoadUtilities.removeQuotes(name);
        try {
            Stat stat = (Stat)Class.forName("com.supergotchi.statsTraits." + name).newInstance();
            stat.setValue(value);
            return stat;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}


/**
 * Used to deserialize a Furniture.
 */
class FurnitureDeserializer implements JsonDeserializer<Furniture>{

    @Override
    public Furniture deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject object = jsonElement.getAsJsonObject();
        //Getting parameters
        String name = object.get("Name").toString();
        int cost = object.get("Cost").getAsInt();
        int modifier = object.get("Modifier").getAsInt();
        String className = object.get("ClassName").toString();
        //Fixing strings
        name = SaveLoadUtilities.removeQuotes(name);
        className = SaveLoadUtilities.removeQuotes(className);

        try {
            Constructor constructor = Class.forName(className).getConstructor(String.class, int.class, int.class);
            Furniture furniture = (Furniture)constructor.newInstance(name,cost,modifier);
            return furniture;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
