package com.supergotchi.furnitures;

public class Toilet extends Furniture{

    public Toilet(String name, int cost, int modifier) {
        super(name, cost, modifier, "Bladder");
    }

    @Override
    public String usedMessage() {
        return "You sit on the John... you enjoy yourself to a newspaper while you do your stuff.";
    }

    @Override
    public String cantUseThisMessage() {
        return "If you have to go you have to go... but if you don't... you can't..";
    }
}
