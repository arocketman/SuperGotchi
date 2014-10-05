package com.supergotchi.furnitures;

import com.supergotchi.core.Gotchi;
import com.supergotchi.statsTraits.Stat;

/**
 * Created by Andrea Capuano on 04/10/2014.
 */
public abstract class Furniture {
    //Fields
    String Name;
    int Cost;
    int Modifier;
    String ModifiedStat;
    //Type is used for deserialization purposes.
    String ClassName;

    protected Furniture(String name, int cost, int modifier, String modifiedStat) {
        Name = name;
        Cost = cost;
        Modifier = modifier;
        ModifiedStat = modifiedStat;
        ClassName = getClass().getName();
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
            stat.setValue(stat.getValue() + Modifier);
            System.out.println(usedMessage());
            System.out.println(stat.getName() + " new value: " + stat.getValue());
        }
    }

    public String getName() {
        return Name;
    }
}
