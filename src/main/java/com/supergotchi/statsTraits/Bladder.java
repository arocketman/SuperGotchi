package com.supergotchi.statsTraits;

public class Bladder extends Stat{
    //deathModifier is 0 because the monster will just force himself to go to the bathroom.
    public Bladder() {
        super("Bladder", Stat.startingValue, 0.2, 0);
    }

    @Override
    public String notifyMessage() {
        return "I NEED to go to the bathroom!";
    }
}
