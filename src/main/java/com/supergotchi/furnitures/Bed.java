package com.supergotchi.furnitures;

import com.supergotchi.core.Gotchi;

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

    //TODO: When sleeping, the gotchi should be unusable. The better the bed the shorter this time period is.
    @Override
    public void interact(Gotchi gotchi) {
        super.interact(gotchi);
    }

}
