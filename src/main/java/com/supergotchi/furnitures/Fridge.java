package com.supergotchi.furnitures;

import com.supergotchi.core.Gotchi;

/**
 * Created by Andrea Capuano on 04/10/2014.
 */

public class Fridge extends Furniture {

    public Fridge(String name, int cost, int modifier) {
        super(name, cost, modifier, "hunger");
    }

    @Override
    public String usedMessage() {
        return "You open up the fridge, look around for something you get a [RANDOM FOOD] , your tummy feels relieved already!";
    }

    @Override
    public String cantUseThisMessage() {
        return "You are not even hungry..";
    }

    @Override
    public void interact(Gotchi gotchi) {
        super.interact(gotchi);
    }
}
