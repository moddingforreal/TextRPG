package net.breamkillerx.textrpg.worldinteract;

import net.breamkillerx.textrpg.entity.control.PlayerEntity;
import net.breamkillerx.textrpg.entity.control.inventory.ItemType;
import net.breamkillerx.textrpg.util.Util;

import java.util.Scanner;

public class Shop {
    Scanner scanner;
    PlayerEntity playerEntity;
    public Shop(Scanner scanner, PlayerEntity playerIn) {
        this.scanner = scanner;
        playerEntity = playerIn;
    }
    public void shop() {
        boolean exitshop = false;
        do {
            System.out.println(""
                    + "Welcome to the shop!  Below are the items you can purchase!"
                    + "Enter 'A' to buy a Potion - 3 gold"
                    + "Enter 'B' to buy an Attack Upgrade - 5 gold"
                    + "Enter 'C' to buy a Health Upgrade - 5 gold"
                    + "Enter 'D' to go leave the shop!");

            char shopChoice = Util.readValidInput(scanner, "ABCD");
            switch (shopChoice) {
                case 'A' -> {
                    if (playerEntity.inventory.getAmount(ItemType.GOLD) >= 3) {
                        playerEntity.inventory.addItem(ItemType.POTION);
                        playerEntity.inventory.addItemsSafe(ItemType.GOLD, -3);
                        System.out.println("You bought a potion!  You now have " +
                                playerEntity.inventory.getAmount(ItemType.GOLD) + " gold left!");
                    } else {
                        System.out.println("You do not have enough gold!");
                    }
                }
                case 'B' -> {
                    if (playerEntity.inventory.getAmount(ItemType.GOLD) >= 5) {
                        playerEntity.playerAttributes.replace("baseAttack", playerEntity.playerAttributes.get("baseAttack") + 1);
                        playerEntity.inventory.addItemsSafe(ItemType.GOLD, -5);
                        System.out.println("You bought an Attack Upgrade! Your attack is now " +
                                playerEntity.playerAttributes.get("baseAttack") + ". You have " +
                                playerEntity.inventory.getAmount(ItemType.GOLD) + " gold left!");
                    } else {
                        System.out.println("You do not have enough gold!");
                    }
                }
                case 'C' -> {
                    if (playerEntity.inventory.getAmount(ItemType.GOLD) >= 5) {
                        playerEntity.playerAttributes.replace("baseHealth", playerEntity.playerAttributes.get("baseHealth") + 3);
                        playerEntity.inventory.addItemsSafe(ItemType.GOLD, -5);
                        System.out.println("You bought a health upgrade!  Your max health is " +
                                playerEntity.playerAttributes.get("baseHealth") + ". You have " +
                                playerEntity.inventory.getAmount(ItemType.GOLD) + " gold left!");
                    } else {
                        System.out.println("You do not have enough gold!");
                    }
                }
                case 'D' -> exitshop = true;
            }
        } while (!exitshop);
    }
}
