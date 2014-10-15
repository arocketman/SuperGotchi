package com.supergotchi.furnitures;

import com.supergotchi.core.Gotchi;

import java.util.Random;

/**
 * Created by Andrea Capuano on 04/10/2014.
 */

public class Fridge extends Furniture {

    private static final String[] foodList = {"yesterday's fish" , "some ham" , "some bacon" , "a piece of cake" , "some cheese"};
    public Fridge(String name, int cost, int modifier) {
        super(name, cost, modifier, "Satiation");
    }

    @Override
    public String usedMessage() {
        return "You open up the fridge, look around for something you get " + foodList[(new Random()).nextInt(foodList.length)] + " , your tummy feels relieved already!";
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
