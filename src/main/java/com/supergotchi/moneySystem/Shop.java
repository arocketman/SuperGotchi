package com.supergotchi.moneySystem;

import com.supergotchi.core.Gotchi;
import com.supergotchi.furnitures.Furniture;

import java.util.ArrayList;

/**
 * Created by Andrea on 08/10/2014.
 */
public interface Shop {
    public void setName(String name);
    public String getName();
    public void fillBuyableList();
    public void buy(int buyableID , Gotchi gotchi);
    public void printBuyablesList();
    public void welcomeString();

}
