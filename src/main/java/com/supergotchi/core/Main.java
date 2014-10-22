package com.supergotchi.core;

import com.supergotchi.furnitures.Furniture;
import com.supergotchi.moneySystem.Shops;
import com.supergotchi.persistency.FurnitureDB;
import com.supergotchi.persistency.Locations;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game;
        //TODO: Fix files don't exist.
        FurnitureDB.loadFurnitures();
        Locations.loadLocations();
        Scanner scanner = new Scanner(System.in);
        System.out.println("What's the name of your gotchi?");
        String gotchiname = scanner.nextLine();
        if(SaveLoadUtils.savedGameExists(gotchiname)) game = new Game(SaveLoadUtils.loadGotchi(gotchiname));
        else game = new Game(new Gotchi(gotchiname));
        Shops.initializeShops();
        game.gameLoop();
    }


}
