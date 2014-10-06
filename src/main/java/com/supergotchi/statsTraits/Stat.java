package com.supergotchi.statsTraits;

/**
 * Created by Andrea on 04/10/2014.
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

    public abstract String notifyMessage();

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
}
