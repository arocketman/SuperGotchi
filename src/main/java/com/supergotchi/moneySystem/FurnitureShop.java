package com.supergotchi.moneySystem;

import com.supergotchi.furnitures.Bed;
import com.supergotchi.furnitures.Furniture;

import java.util.ArrayList;

/**
 * Created by Andrea on 08/10/2014.
 */
public class FurnitureShop implements Shop {

    String name;
    ArrayList<Furniture> furnituresForSale;


    public FurnitureShop(String shopName) {
        this.name = shopName;
        this.furnituresForSale = new ArrayList<Furniture>();
    }


    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void fillBuyableList() {
        this.furnituresForSale.add(new Bed("Cheap bed",1000,10));
    }
}
