package com.supergotchi.moneySystem;

import java.util.ArrayList;

/**
 * Created by Andrea on 08/10/2014.
 */
public class Shops {
    static ArrayList<Shop> shops = new ArrayList<Shop>();

    public static void initializeShops(){
        shops.add(new FurnitureShop("Akim's furniture"));
        shops.add(new FurnitureShop("Jim's furniture"));
        shops.add(new FurnitureShop("Loki's furniture"));
    }

    public static void printShops(){
        for(int i = 0; i < shops.size(); i++){
            System.out.println("["+i+"] " + shops.get(i).getName() );
        }
    }

    public static Shop getShop(int ID){
        return shops.get(ID);
    }
}
