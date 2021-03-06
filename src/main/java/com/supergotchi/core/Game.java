package com.supergotchi.core;


import com.supergotchi.moneySystem.Shops;
import com.supergotchi.persistency.Locations;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Game class, handles the gaming loop. Takes the input from the user and if it's a valid command it calls the command handler.
 */
public class Game {
    public static final int STATS_CHANGER_CALLED_EACH_SECONDS = 60;

    Gotchi gotchi;
    StatsChanger statsChanger;
    Timer timer;

    /**
     * Constructor, builds the game based on the gotchi and starts up the stats changer that monitors and decreases values.
     * @param gotchi the gotchi you'll be playing with.
     */
    public Game(Gotchi gotchi){
        this.gotchi = gotchi;
        this.statsChanger = new StatsChanger(gotchi);
        timer = new Timer(true);
        timer.scheduleAtFixedRate(this.statsChanger,5000,STATS_CHANGER_CALLED_EACH_SECONDS * 1000);
    }

    /**
     * The gameloop. Takes a user input and checks out if that command exists. If it does, it passes up the handling to the correct method.
     */
    public void gameLoop(){
        Scanner scanner = new Scanner(System.in);
        String command = "";
        while(!command.equalsIgnoreCase("exit") && gotchi.isAlive()){
            System.out.println("Insert a command: list , interact, stats, save, shops , buy , travel. ");
            command = scanner.nextLine();
            if(command.startsWith("inter")) handleInteraction(command);
            else if(command.startsWith("list")) handleList(scanner);
            else if(command.startsWith("exit")) handleExit();
            else if(command.startsWith("stats")) handleStats();
            else if(command.startsWith("save"))handleSave();
            else if(command.startsWith("shop")) handleShop(command);
            else if(command.startsWith("buy")) handleBuy(command);
            else if(command.startsWith("travel")) handleTravel(command);
            else{
                System.out.println("Unknown command : " + command + " . Type : 'exit' to quit");
            }
        }
        if(!gotchi.isAlive()) System.out.println("Your gotchi died.. RIP");
    }

    /**
     * Changes the current position of the Gotchi to the one specified by the user.
     * @param command
     */
    private void handleTravel(String command) {
        ArrayList<Integer> results = Utils.getInputParameters(command);
        if(results.isEmpty()){
            System.out.println("Invalid syntax . Valid syntax is : 'travel LOCATION' ex: travel 2");
            Locations.printLocations();
        }
        else{
            int destination = results.get(0);
            if(destination < Locations.locations.size())
                gotchi.setCurrentPosition(destination);
            else System.out.println("That's not even a location mate");
        }

    }

    /**
     * Saves the game, both the gotchi and the locations.
     */
    private void handleSave() {
        SaveLoadUtils.save(gotchi);
        Locations.saveLocations();
        System.out.println("Game saved.");
    }

    /**
     * Handles interaction with furniture , calls the interact method of the selected furniture.
     * Syntax "interact ITEMID"
     * TODO: extend this command to other objects around gotchi's world as well (not only furniture).
     * @param command
     */
    public void handleInteraction(String command){
        ArrayList<Integer> results = Utils.getInputParameters(command);
        if(results.isEmpty()) System.out.println("Invalid syntax . Valid syntax is : 'inter ITEMID' ex: inter 2");
        else{
            int itemID = results.get(0);
            if(Utils.isValidInteraction(itemID,gotchi.getCurrentPosition())){
                Locations.getLocationFromIndex(gotchi.getCurrentPosition()).getFurnitureByPosition(itemID).interact(gotchi);
                //gotchi.getHome().getFurnitureByPosition(itemID).interact(gotchi);
            }
            else
                System.out.println("The IDs you provided are invalid!");
        }
    }

    /**
     * This will print the list of furniture of the gotchi's home.
     * @param scanner
     */
    public void handleList(Scanner scanner){
        Locations.getLocationFromIndex(gotchi.getCurrentPosition()).printFurnitureList();
    }

    /**
     * Exit method, saves the game before quitting.
     */
    public void handleExit(){
        System.out.println("Have a good one.");
        //Save on exit
        handleSave();
    }

    /**
     * Takes care of printing the shops and handling the interaction with these ones.
     * //TODO: Handle input correctly
     */
    public void handleShop(String command){
        ArrayList<Integer> results = Utils.getInputParameters(command);
        if(results.isEmpty()){
            System.out.println("Invalid syntax . Valid syntax is : 'shop SHOPID' ex: shop 1");
            Shops.printShops();
        }
        else{
            int shopID = results.get(0);
            if(shopID < Shops.getShopNumbers())
                Shops.getShop(shopID).welcomeString();
            else
                System.out.println("The IDs you provided are invalid!");
        }
    }

    /**
     * Prints the stats of the gotchi as well as the name and coins.
     */
    public void handleStats(){
        System.out.println("[Name]: " + gotchi.getName() + " [Coins]: " + gotchi.getCoins() + " [Age]: " + gotchi.getAge() + " hours");
        System.out.println(gotchi.getStatList());
    }

    /**
     * Takes care of buying a furniture. If both the syntax is correct calls onto the buy method of the shop.
     * @param command regex with syntax "buy SHOPID ITEMID"
     */
    private void handleBuy(String command) {
        ArrayList<Integer> results = Utils.getInputParameters(command);
        if(results.isEmpty()) System.out.println("Invalid syntax . Valid syntax is : 'buy SHOPID ITEMID' ex: buy 0 1");
        else{
            int shopID = results.get(0);
            int itemID = results.get(1);
            if(Utils.validBuyOperation(shopID,itemID))Shops.getShop(shopID).buy(itemID,gotchi);
            else
                System.out.println("The IDs you provided are invalid!");
        }
    }


}
