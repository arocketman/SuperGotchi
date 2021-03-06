package com.supergotchi.moneySystem;

import com.supergotchi.core.Gotchi;
import com.supergotchi.furnitures.Bed;
import com.supergotchi.furnitures.Fridge;
import com.supergotchi.furnitures.Furniture;
import com.supergotchi.furnitures.Toilet;
import com.supergotchi.persistency.FurnitureDB;

import java.util.ArrayList;

/**
 * Created by Andrea on 08/10/2014.
 */
public class FurnitureShop implements Shop {

    String name;
    ArrayList<Furniture> furnituresForSale;


    public FurnitureShop(String shopName) {
        this.name = shopName;
        this.furnituresForSale = FurnitureDB.furnitures;
        fillBuyableList();
    }


    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void fillBuyableList() {
    }

    @Override
    public void buy(int buyableID, Gotchi gotchi) {
        if(buyableID >= this.furnituresForSale.size()){
            System.out.println("We do not have that furniture around here, maybe you can check somewhere else.");
            return;
        }
        else{
            Furniture boughtFurniture = this.furnituresForSale.get(buyableID);
            int furnitureCost = boughtFurniture.getCost();
            if(gotchi.getCoins() < furnitureCost){
                System.out.println("You do not have enough money! You require : " + boughtFurniture.getCost() + " coins");
                return;
            }
            //Let's do the transaction.
            gotchi.decreaseMoney(furnitureCost);
            gotchi.getHome().addFurniture(boughtFurniture);
            System.out.println("Congratulations you just bought : " + boughtFurniture.getName() + " for : " + furnitureCost + " coins. You now have: " + gotchi.getCoins() + " coins");
        }
    }

    @Override
    public void printBuyablesList() {
        for(int i = 0; i < furnituresForSale.size(); i++){
            System.out.println("ID: " + i + " ->" + furnituresForSale.get(i).toString());
        }
    }

    @Override
    public void welcomeString() {
        System.out.println("Welcome to : " + name.toString() + " , we have the very best furniture in GotchiTown. Here are our merchandise: ");
        printBuyablesList();
    }

    @Override
    public int getItemsNumber() {
        return this.furnituresForSale.size();
    }


}
