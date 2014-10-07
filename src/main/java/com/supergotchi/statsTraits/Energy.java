package com.supergotchi.statsTraits;

import com.supergotchi.core.Gotchi;

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

    @Override
    public void statReachesZero(Gotchi gotchi) {
        System.out.println("Your gotchi is exhausted. He faints onto the ground!");
        //Todo: Make the gotchi not controllable.
    }
}
