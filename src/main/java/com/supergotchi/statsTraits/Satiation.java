package com.supergotchi.statsTraits;

import com.supergotchi.core.Gotchi;

/**
 * Created by Andrea on 04/10/2014.
 */
public class Satiation extends Stat {

    public Satiation() {
        super("Satiation", Stat.startingValue, 0.2, 0.3);
    }

    @Override
    public String notifyMessage() {
        return "I am starving.. please feed me!!!";
    }

    @Override
    public void statReachesZero(Gotchi gotchi) {
        System.out.println("Your gotchi is starving to death!!!");
        //Todo: Increment deathChance.. a lot! The guy is starving to death!
}
        }
