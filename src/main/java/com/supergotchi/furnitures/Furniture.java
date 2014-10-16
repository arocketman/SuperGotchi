package com.supergotchi.furnitures;

import com.supergotchi.core.Gotchi;
import com.supergotchi.moneySystem.Buyable;
import com.supergotchi.statsTraits.Stat;

import java.util.UUID;

/**
 * Created by Andrea Capuano on 04/10/2014.
 */
public abstract class Furniture implements Buyable {
    //Fields
    String Name;
    int Cost;
    int Modifier;
    String ModifiedStat;
    //Type is used for deserialization purposes.
    String ClassName;
    String furnitureID;

    protected Furniture(String name, int cost, int modifier, String modifiedStat) {
        Name = name;
        Cost = cost;
        Modifier = modifier;
        ModifiedStat = modifiedStat;
        ClassName = getClass().getName();
        furnitureID = UUID.randomUUID().toString();
    }

    protected Furniture(){

    }

    /**Message when the furnitures has been used */
    public abstract String usedMessage();
    /**Message if the furnitures cannot be used (ex: fridge can't be used if hunger is maxed) */
    public abstract String cantUseThisMessage();

    /**
     * Adds the modified stat to the gotchi, or returns cantUseThisMessage if it's not possible.
     * @param gotchi the gotchi
     */
    public void interact(Gotchi gotchi){
        Stat stat = gotchi.getStat(ModifiedStat);
        if(stat.getValue() >= 100) System.out.println(cantUseThisMessage());
        else {
            stat.addValue(Modifier);
            System.out.println(usedMessage());
            System.out.println(stat.getName() + " new value: " + stat.getValue());
        }
    }

    @Override
    public String getName() {
        return Name;
    }

    @Override
    public int getCost() {
        return this.Cost;
    }

    @Override
    public void setCost(int price) {
        this.Cost = price;
    }

    @Override
    public void setName(String name) {
        this.Name = name;
    }

    @Override
    public String toString() {
        return "Name : " + Name + ", " +
               "Price : " + Cost + ", " +
               "Efficiency: " + String.valueOf(Modifier) ;
    }

    public String getID() {
        return furnitureID;
    }

    public String getModifiedStat() {
        return ModifiedStat;
    }

    public void setModifiedStat(String modifiedStat) {
        ModifiedStat = modifiedStat;
    }

    public int getModifier() {
        return Modifier;
    }

    public void setModifier(int modifier) {
        Modifier = modifier;
    }

    public String getFurnitureID() {
        return furnitureID;
    }

    public void setFurnitureID(String furnitureID) {
        this.furnitureID = furnitureID;
    }
}
