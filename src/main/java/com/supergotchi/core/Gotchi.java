package com.supergotchi.core;

import com.supergotchi.statsTraits.*;

public class Gotchi {
    //Constants
    private final static int BASE_HAPPINESS = 100;
    private final static int UNHAPPINESS_VALUE = 10;
    private final static int BASE_COINS = 5000;
    private final static int SEX_MALE = 0;
    private final static int SEX_FEMALE = 1;
    //Fields
    private String Name;
    private int age;
    private int sex;
    private Trait[] Traits;
    private Stat[] Stats;
    private int happiness;
    private double deathChance;
    private int coins;
    private House Home;

    public Gotchi(String name) {
        Name = name;
        Stats = new Stat[]{new Energy(), new Satiation(), new Bladder()};
        happiness = BASE_HAPPINESS;
        coins = BASE_COINS;
        Home = new House();
    }

    public Stat getStat(String statName){
        for(Stat s : Stats){
            if(s.getName().equalsIgnoreCase(statName)) return s;
        }
        return null;
    }

    public String getName() {
        return Name;
    }

    public int getAge() {
        return age;
    }

    public House getHome() {
        return Home;
    }

    public int getCoins() {
        return coins;
    }

    public int getHappiness() {
        return happiness;
    }

    public int getSex() {
        return sex;
    }

    public Stat[] getStats() {
        return Stats;
    }

    public double getDeathChance() {
        return deathChance;
    }

    public void setDeathChance(double deathChance) {
        this.deathChance = deathChance;
    }

    public void decreaseHappiness(int i){
        this.happiness -= i;
        if(happiness <= 0){
            happiness = 0;
            //TODO: What to do when happiness is equal to 0 ?
        }
    }

    public boolean isUnhappy(){
        return this.happiness <= UNHAPPINESS_VALUE;
    }

    public String getStatList(){
        String statsString = "";
        for(Stat stat : Stats){
            statsString += stat.getName() + ": " + stat.getValue() + " || ";
        }
        return statsString;
    }

    public void decreaseMoney(int amount){
        this.coins -= amount;
    }
}
