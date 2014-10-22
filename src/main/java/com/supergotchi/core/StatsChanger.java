package com.supergotchi.core;

import com.supergotchi.statsTraits.Stat;

import java.util.Random;
import java.util.TimerTask;

/**
 * This class is in charge of periodically modify the Gotchi's stats.
 */

public class StatsChanger extends TimerTask{
    Gotchi gotchi;
    public final static int CRITICAL_VALUE = 10;
    public final static int BASE_MODIFIER_HAPPINESS = 10;
    public final static int AGE_MODIFIER = 24;  //1 day older every 24 hours

    public StatsChanger(Gotchi gotchi) {
        this.gotchi = gotchi;
    }

    @Override
    public void run() {
        for(Stat stat : gotchi.getStats()){
            stat.decreaseValue((new Random()).nextInt(5) + 1);
            if(stat.getValue() == 0){
                System.out.println("Your gotchi is in critical condition!!! This is caused by low " + stat.getName());
                gotchi.decreaseHappiness((int)(BASE_MODIFIER_HAPPINESS * stat.getHappinessModifier()));
                if(gotchi.isUnhappy()) System.out.println("Your gotchi is unhappy :(");
            }
            else if(stat.getValue() <= CRITICAL_VALUE){
                System.out.println(stat.notifyMessage());
            }
        }
        gotchi.randomDeath();
    }
}
