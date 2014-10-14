package com.supergotchi.core;

import com.supergotchi.moneySystem.Shops;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    /**
     * Checks if the buy parameters are correct or out of bounds
     * @param shopID the ID of the shop you want to buy from
     * @param itemID the item you are willing to buy.
     * @return
     */
    public static boolean validBuyOperation(int shopID , int itemID){
        if((shopID < Shops.getShopNumbers()) && shopID >= 0)
            return itemID < Shops.getShop(shopID).getItemsNumber() && itemID >= 0;
        return false;
    }

    /**
     * Will return an array consisting of the integers taken from the user input. If it's empty the command is invalid.
     * @param command
     * @return
     */
    public static ArrayList<Integer> getInputParameters(String command){
        String regex;
        ArrayList<Integer> results = new ArrayList<Integer>();
        //Buy
        if(command.startsWith("buy")){
            regex="(Buy)(\\s+)(\\d+).*?(\\d+)";	// Regex "buy int1 int2"
            Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher m = p.matcher(command);
            if (m.find()){
                int shopID=Integer.valueOf(m.group(3));
                int itemID=Integer.valueOf(m.group(4));
                results.add(shopID);
                results.add(itemID);
            }
        }
        //Interaction
        else if(command.startsWith("inter")){
            regex="(Inter)(\\s+)(\\d+).*?";	// Regex "inter int1"
            Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher m = p.matcher(command);
            if (m.find()){
                int itemID=Integer.valueOf(m.group(3));
                results.add(itemID);
            }

        }
        return results;
 }

    public static boolean isValidInteraction(int itemID , Gotchi g){
        return g.getHome().getFurnitureNumber() > itemID;
    }
}

