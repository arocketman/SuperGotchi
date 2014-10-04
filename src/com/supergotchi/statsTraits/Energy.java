package com.supergotchi.statsTraits;

/**
 * Created by Andrea on 04/10/2014.
 */
public class Energy extends Stat {

    public Energy() {
        super("Energy", Stat.startingValue, 0.2, 0.3);
    }

    @Override
    public String notifyMessage() {
        return "I am really tired, I need to sleep.";
    }
}
