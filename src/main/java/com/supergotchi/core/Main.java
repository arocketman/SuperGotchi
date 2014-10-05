package com.supergotchi.core;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game;
        if(SaveLoadUtilities.savedGameExists()) game = new Game(SaveLoadUtilities.loadGotchi());
        game = new Game(new Gotchi("GotcheeName"));
        game.gameLoop();
    }


}
