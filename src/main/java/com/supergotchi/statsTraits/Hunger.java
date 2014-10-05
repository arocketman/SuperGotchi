package com.supergotchi.statsTraits;

/**
 * Created by Andrea on 04/10/2014.
 */
public class Hunger extends Stat {

    public Hunger() {
        super("Hunger", Stat.startingValue, 0.2, 0.3);
    }

    @Override
    public String notifyMessage() {
        return "I am starving.. please feed me!!!";
    }
}
