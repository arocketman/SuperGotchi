package com.supergotchi.core;

import com.supergotchi.furnitures.Furniture;
import com.supergotchi.moneySystem.Shops;
import com.supergotchi.persistency.FurnitureDB;
import com.supergotchi.persistency.Locations;

public class Main {
    public static void main(String[] args) {
        Game game;
        //TODO: Fix files don't exist.
        FurnitureDB.loadFurnitures();
        Locations.loadLocations();
        if(SaveLoadUtils.savedGameExists()) game = new Game(SaveLoadUtils.loadGotchi());
        else game = new Game(new Gotchi("Gregory"));
        Shops.initializeShops();
        game.gameLoop();
    }


}
