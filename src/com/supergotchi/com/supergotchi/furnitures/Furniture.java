package com.supergotchi.com.supergotchi.furnitures;

import com.supergotchi.core.Gotchi;
import com.supergotchi.statsTraits.Stat;

/**
 * Created by Andrea Capuano on 04/10/2014.
 */
public abstract class Furniture {
    String Name;
    int Cost;
    int Modifier;
    String ModifiedStat;

    protected Furniture(String name, int cost, int modifier, String modifiedStat) {
        Name = name;
        Cost = cost;
        Modifier = modifier;
        ModifiedStat = modifiedStat;
    }

    public abstract String usedMessage();
    public abstract String cantUseThisMessage();

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
