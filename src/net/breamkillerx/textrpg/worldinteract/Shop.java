package net.breamkillerx.textrpg.worldinteract;

import net.breamkillerx.textrpg.entity.control.PlayerEntity;
import net.breamkillerx.textrpg.entity.control.inventory.ItemType;

import java.util.Scanner;

public class Shop {
    public Scanner scanner;
    public Shop(Scanner scanner) {
        this.scanner = scanner;
    }
    public void shop(){
        boolean exitshop = false;
        do {
            System.out.println(""
                    + "Welcome to the shop!  Below are the items you can purchase!"
                    + "Enter 'A' to buy a Potion - 3 gold"
                    + "Enter 'B' to buy an Attack Upgrade - 5 gold"
                    + "Enter 'C' to buy a Health Upgrade - 5 gold"
                    + "Enter 'D' to go leave the shop!");
            boolean choiceValid = false;
            char shopchoice;
            do {
                shopchoice = Character.toUpperCase(scanner.next().charAt(0));
                switch (shopchoice) {
                    case 'A' -> {
                        if (PlayerEntity.inventory.getAmount(ItemType.GOLD) >= 3) {
                            PlayerEntity.inventory.addItem(ItemType.POTION);
                            PlayerEntity.inventory.addItemsSafe(ItemType.GOLD, -3);
                            System.out.println("You bought a potion!  You now have " +
                                    PlayerEntity.inventory.getAmount(ItemType.GOLD) + " gold left!");
                        } else {
                            System.out.println("You do not have enough gold!");
                        }
                        choiceValid = true;
                    }
                    case 'B' -> {
                        if (PlayerEntity.inventory.getAmount(ItemType.GOLD) >= 5) {
                            PlayerEntity.playerAttributes.replace("baseAttack", PlayerEntity.playerAttributes.get("baseAttack") + 1);
                            PlayerEntity.inventory.addItemsSafe(ItemType.GOLD, -5);;
                            System.out.println("You bought an Attack Upgrade! Your attack is now " +
                                    PlayerEntity.playerAttributes.get("baseAttack") + ". You have " +
                                    PlayerEntity.inventory.getAmount(ItemType.GOLD) + " gold left!");
                        } else {
                            System.out.println("You do not have enough gold!");
                        }
                        choiceValid = true;
                    }
                    case 'C' -> {
                        if (PlayerEntity.inventory.getAmount(ItemType.GOLD) >= 5) {
                            PlayerEntity.playerAttributes.replace("baseHealth", PlayerEntity.playerAttributes.get("baseHealth") + 3);
                            PlayerEntity.inventory.addItemsSafe(ItemType.GOLD, -5);;
                            System.out.println("You bought a health upgrade!  Your max health is " +
                                    PlayerEntity.playerAttributes.get("baseHealth") + ". You have " +
                                    PlayerEntity.inventory.getAmount(ItemType.GOLD) + " gold left!");
                        } else {
                            System.out.println("You do not have enough gold!");
                        }
                        choiceValid = true;
                    }
                    case 'D' -> {
                        exitshop = true;
                        choiceValid = true;
                    }
                    default -> {
                        choiceValid = false;
                        System.out.println("Invalid input!  You put " + shopchoice + ".  Choose 'A', 'B', 'C', or 'D'.");
                    }
                }

            } while (!choiceValid);
        } while(!exitshop);
    }
}