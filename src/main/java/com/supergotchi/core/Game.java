package com.supergotchi.core;


import com.supergotchi.moneySystem.Shops;

import java.util.Scanner;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Andrea on 04/10/2014.
 */
public class Game {
    Gotchi gotchi;
    StatsChanger statsChanger;
    Timer timer;

    public Game(Gotchi gotchi){
        this.gotchi = gotchi;
        this.statsChanger = new StatsChanger(gotchi);
        timer = new Timer(true);
        timer.scheduleAtFixedRate(this.statsChanger,5000,10 * 1000);
    }

    public void handleInteraction(Scanner scanner){
        System.out.println("Insert the ID of the furnitures you want to interact with:");
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

    public void handleShop(){
        Shops.printShops();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert the ID of the shop you'd like to visit: ");
        int choice = scanner.nextInt();
        Shops.getShop(choice).welcomeString();
    }

    public void handleStats(){
        System.out.println("[Name : ] " + gotchi.getName() + " [Coins] " + gotchi.getCoins());
        System.out.println(gotchi.getStatList());
    }

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
        //Save on exit
        SaveLoadUtilities.saveGotchi(gotchi);
    }

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
