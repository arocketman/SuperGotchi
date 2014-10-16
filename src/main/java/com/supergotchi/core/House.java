package com.supergotchi.core;


import com.supergotchi.furnitures.Bed;
import com.supergotchi.furnitures.Fridge;
import com.supergotchi.furnitures.Furniture;
import com.supergotchi.persistency.FurnitureDB;

import java.util.ArrayList;

public class House {
    private final static int HOUSE_BASEVALUE = 5000;

    private int HouseValue;
    private ArrayList<String> furnitures;
    private String ID;

    public House() {
        HouseValue = HOUSE_BASEVALUE;
        this.furnitures = new ArrayList<String>();
        this.furnitures.add(FurnitureDB.furnitures.get(0).getID());
        this.furnitures.add(FurnitureDB.furnitures.get(1).getID());
    }

    public void printFurnitureList(){
        int counter = 0;
        for(String f : furnitures){
            System.out.println(counter++ + ") " + getFurniture(f).getName());
        }
    }

    public Furniture getFurniture(String id){
        return FurnitureDB.getFurniture(id);
    }

    public void addFurniture(Furniture boughtFurniture) {
        this.furnitures.add(boughtFurniture.getID());
    }

    public int getFurnitureNumber(){
        return furnitures.size();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getHouseValue() {
        return HouseValue;
    }

    public void setHouseValue(int houseValue) {
        HouseValue = houseValue;
    }

    public ArrayList<String> getFurnitures() {
        return furnitures;
    }

    public void setFurnitures(ArrayList<String> furnitures) {
        this.furnitures = furnitures;
    }

    public Furniture getFurnitureByPosition(int itemID) {
        return getFurniture(this.furnitures.get(itemID));
    }
}
