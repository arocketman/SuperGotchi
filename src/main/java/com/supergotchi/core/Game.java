package com.supergotchi.core;


import com.supergotchi.moneySystem.Shops;

import java.util.Scanner;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        while(!command.equalsIgnoreCase("exit")){
            System.out.println("Insert a command: list , interact, stats, save, shop . ");
            command = scanner.nextLine();
            if(command.startsWith("inter")) handleInteraction(scanner);
            else if(command.startsWith("list")) handleList(scanner);
            else if(command.startsWith("exit")) handleExit();
            else if(command.startsWith("stats")) handleStats();
            else if(command.startsWith("save")) SaveLoadUtilities.saveGotchi(this.gotchi);
            else if(command.startsWith("shops")) handleShop();
            else if(command.startsWith("buy")) handleBuy(command);
            else{
                System.out.println("Unknown command : " + command + " . Type : 'exit' to quit");
            }
        }
    }

    /**
     * Handles interaction with furniture , calls the interact method of the selected furniture.
     * TODO: extend this command to other objects around gotchi's world as well (not only furniture).
     * @param scanner
     */
    public void handleInteraction(Scanner scanner){
        System.out.println("Insert the ID of the furnitures you want to interact with:");
        //TODO: Input santization.
        int furnitureID = scanner.nextInt();
        gotchi.getHome().getFurniture(furnitureID).interact(gotchi);

    }

    /**
     * This will print the list of furniture of the gotchi's home.
     * @param scanner
     */
    public void handleList(Scanner scanner){
        this.gotchi.getHome().printFurnitureList();
    }

    /**
     * Exit method, saves the game before quitting.
     */
    public void handleExit(){
        System.out.println("Have a good one.");
        //Save on exit
        SaveLoadUtilities.saveGotchi(gotchi);
    }

    /**
     * Takes care of printing the shops and handling the interaction with these ones.
     */
    public void handleShop(){
        Shops.printShops();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert the ID of the shop you'd like to visit: ");
        int choice = scanner.nextInt();
        Shops.getShop(choice).welcomeString();
    }

    /**
     * Prints the stats of the gotchi as well as the name and coins.
     */
    public void handleStats(){
        System.out.println("[Name : ] " + gotchi.getName() + " [Coins] " + gotchi.getCoins());
        System.out.println(gotchi.getStatList());
    }

    /**
     * Takes care of buying a furniture. If both the syntax is correct calls onto the buy method of the shop.
     * @param command regex with syntax "buy SHOPID ITEMID"
     * TODO: Fix array out of bounds.
     */
    private void handleBuy(String command) {
        String re1="(Buy)(\\s+)(\\d+).*?(\\d+)";	// Regex "buy int1 int2

        Pattern p = Pattern.compile(re1,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(command);

        if (!m.find()){
            System.out.println("Correct syntax : buy SHOPID ITEMID ");
        }
        else
        {
            int shopID=Integer.valueOf(m.group(3));
            int itemID=Integer.valueOf(m.group(4));
            Shops.getShop(shopID).buy(itemID,gotchi);
        }
    }


}
