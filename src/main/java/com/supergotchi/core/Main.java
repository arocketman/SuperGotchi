package com.supergotchi.core;

public class Main {
    public static void main(String[] args) {
        Game game;
        if(SaveLoadUtilities.savedGameExists()) game = new Game(SaveLoadUtilities.loadGotchi());
        else game = new Game(new Gotchi("GotcheeName"));
        game.gameLoop();    
    }


}
