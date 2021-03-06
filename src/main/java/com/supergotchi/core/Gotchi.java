package com.supergotchi.core;

import com.supergotchi.persistency.Locations;
import com.supergotchi.statsTraits.*;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Gotchi class representing the gotchi itself.
 */
public class Gotchi {
    //Constants
    private final static int BASE_HAPPINESS = 100;
    private final static int UNHAPPINESS_VALUE = 10;
    private final static int BASE_COINS = 5000;
    private final static int SEX_MALE = 0;
    private final static int SEX_FEMALE = 1;
    //Fields
    private String Name;
    private long DateOfBirth;
    private int sex;
    private boolean Alive;
    //private Trait[] Traits;
    private Stat[] Stats;
    private int happiness;
    private int coins;
    private String HouseID;
    private int currentPosition;

    public Gotchi(String name) {
        Name = name;
        Stats = new Stat[]{new Energy(), new Satiation(), new Bladder()};
        happiness = BASE_HAPPINESS;
        coins = BASE_COINS;
        DateOfBirth = System.currentTimeMillis();
        HouseID = UUID.randomUUID().toString();
        Alive = true;
        this.setCurrentPosition(Locations.getIndexFromID(HouseID));
        House house = new House();
        house.setID(HouseID);
        Locations.locations.add(house);
        Locations.saveLocations();
    }

    public Gotchi(){
        Stats = new Stat[]{new Energy(), new Satiation(), new Bladder()};
        happiness = BASE_HAPPINESS;
        coins = BASE_COINS;
        DateOfBirth = System.currentTimeMillis();
        HouseID = UUID.randomUUID().toString();
        Alive = true;
    }

    public boolean isAlive() {
        return Alive;
    }

    public void setAlive(boolean alive) {
        Alive = alive;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public House getHome(){
        return Locations.getHouse(this.HouseID);
    }

    public String getHouseID() {
        return HouseID;
    }

    public void setHouseID(String houseID) {
        HouseID = houseID;
    }

    public Stat getStat(String statName){
        for(Stat s : Stats){
            if(s.getName().equalsIgnoreCase(statName)) return s;
        }
        return null;
    }

    /**
     * Calculates if the gotchi dies based on the current deathChance which is the sum of the chances of the stats that are currently 0 valued.
     * @return if the gotchi is alive or dead
     */
    public boolean randomDeath(){
        double deathChance = 0;
        for(Stat stat : Stats){
            if(stat.getValue() == 0){
                deathChance += stat.getDeathChanceModifier();
            }
        }
        if(Math.random() < deathChance) this.Alive = false;
        return this.Alive;
    }

    public long getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(long dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
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

    public void decreaseHappiness(int i){
        this.happiness -= i;
        if(happiness <= 0){
            happiness = 0;
            //TODO: What to do when happiness is equal to 0 ?
        }
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public void setCoins(int coins) {
        this.coins = coins;
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

    public long getAge(){
        Date currentTime = new Date();
        long duration =System.currentTimeMillis() -  this.DateOfBirth;
        long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
        return diffInHours;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }
}
