package com.supergotchi.core;

import com.supergotchi.com.supergotchi.furnitures.Bed;
import com.supergotchi.com.supergotchi.furnitures.Fridge;
import com.supergotchi.com.supergotchi.furnitures.Furniture;

import java.util.ArrayList;

public class House {
    private final static int HOUSE_BASEVALUE = 5000;

    private int HouseValue;
    private ArrayList<Furniture> furnitures;

    public House() {
        HouseValue = HOUSE_BASEVALUE;
        this.furnitures = new ArrayList<Furniture>();
        //TODO: Delete the following code, just for testing.
        this.furnitures.add(new Fridge("Cheap fridge",1000,10));
        this.furnitures.add(new Bed("Cheap bed",1000,10));
    }

    /** Prints all of the furniture in the house **/
    public void printFurnitureList(){
        int counter = 0;
        for(Furniture f : furnitures){
            System.out.println(counter++ + ") " + f.getName());
        }
    }

    public Furniture getFurniture(int id){
        return furnitures.get(id);
    }
}
