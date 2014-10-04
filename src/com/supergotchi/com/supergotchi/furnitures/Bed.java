package com.supergotchi.com.supergotchi.furnitures;

import com.supergotchi.core.Gotchi;

/**
 * Created by Andrea Capuano on 04/10/2014.
 */

public class Bed extends Furniture {

    public Bed(String name, int cost, int modifier) {
        super(name, cost, modifier, "energy");
    }

    @Override
    public String usedMessage() {
        return "You lay down onto the bed and quickly fall asleep. ZzzZzzZzz";
    }

    @Override
    public String cantUseThisMessage() {
        return "Why bother, you are not sleepy!";
    }

    @Override
    public void interact(Gotchi gotchi) {
        super.interact(gotchi);
    }

}
