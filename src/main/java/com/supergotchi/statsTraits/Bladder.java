package com.supergotchi.statsTraits;

import com.supergotchi.core.Gotchi;

public class Bladder extends Stat{
    public final static int HAPPINESS_DECREASE_IF_PISSES_HIMSELF = 30;

    //deathModifier is 0 because the monster will just force himself to go to the bathroom.
    public Bladder() {
        super("Bladder", Stat.startingValue, 0.2, 0);
    }

    @Override
    public String notifyMessage() {
        return "I NEED to go to the bathroom!";
    }

    @Override
    public void statReachesZero(Gotchi gotchi) {
        System.out.println("Ewww... your gotchi just pissed himself.");
        this.setValue(100);
        gotchi.decreaseHappiness(HAPPINESS_DECREASE_IF_PISSES_HIMSELF);
    }
}
