package com.supergotchi.core;

import java.util.Scanner;

/**
 * Created by Andrea on 04/10/2014.
 */
public class Main {
    public static void main(String[] args) {
        //TODO: Load previously saved game if exists
        Game game = new Game(new Gotchi("Rufus"));
        game.gameLoop();
    }


}
