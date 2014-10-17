package com.supergotchi.statsTraits;

import com.supergotchi.core.Gotchi;

/**
 * Abstract class for a Stat. Each specific stat (Energy, Hunger) inherits from this one.
 */
public abstract class Stat {
    //The default starting value for the particular stat.
    public final static int startingValue = 50;
    //Fields
    private String Name;
    private double Value;
    //Modifiers of happiness and deathChance. The higher the more probable is a death cause.
    private double HappinessModifier;
    private double DeathChanceModifier;

    public Stat(String name, double value, double happinessModifier, double deathChanceModifier) {
        Name = name;
        Value = value;
        HappinessModifier = happinessModifier;
        DeathChanceModifier = deathChanceModifier;
    }

    /**What happens when the stat is really low and needs attention.*/
    public abstract String notifyMessage();
    /**What happens when the stat reaches 0.*/
    public abstract void statReachesZero(Gotchi gotchi);

    public String getName() {
        return Name;
    }

    public double getValue() {
        return Value;
    }

    public double getHappinessModifier() {
        return HappinessModifier;
    }

    public double getDeathChanceModifier() {
        return DeathChanceModifier;
    }

    public void setValue(double value) {
        Value = value;
    }

    public void decreaseValue(double value){
        this.Value -= value;
        if(this.Value <= 0) this.Value = 0;
    }

    public void addValue(int modifier){
        this.Value += modifier;
        if(this.Value > 100) this.Value = 100;
    }
}
