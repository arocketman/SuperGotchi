package com.supergotchi.core;

import com.supergotchi.moneySystem.Shops;

public class Main {
    public static void main(String[] args) {
        Game game;
        if(SaveLoadUtilities.savedGameExists()) game = new Game(SaveLoadUtilities.loadGotchi());
        else game = new Game(new Gotchi("GotchiName"));
        Shops.initializeShops();
        game.gameLoop();
    }


}
