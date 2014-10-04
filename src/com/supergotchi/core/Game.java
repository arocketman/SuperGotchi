package com.supergotchi.core;

import java.util.Scanner;

/**
 * Created by Andrea on 04/10/2014.
 */
public class Game {
    Gotchi gotchi;

    public Game(Gotchi gotchi){
        this.gotchi = gotchi;
    }

    public void handleInteraction(Scanner scanner){
        System.out.println("Insert the ID of the furniture you want to interact with:");
        //TODO: Input santization.
        int furnitureID = scanner.nextInt();
        gotchi.getHome().getFurniture(furnitureID).interact(gotchi);

    }

    public void handleList(Scanner scanner){
        this.gotchi.getHome().printFurnitureList();
    }

    public void handleExit(){
        System.out.println("Have a good one.");
    }

    public void gameLoop(){
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while(!command.equalsIgnoreCase("exit")){
            System.out.println("Insert a command: list , interact");
            command = scanner.next();
            if(command.startsWith("inter")) handleInteraction(scanner);
            else if(command.startsWith("list")) handleList(scanner);
            else if(command.startsWith("exit")) handleExit();
            else{
                System.out.println("Unknown command : " + command + " . Type : 'exit' to quit");
            }
        }
    }
}
