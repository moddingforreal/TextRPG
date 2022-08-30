package net.breamkillerx.textrpg.interact;

import net.breamkillerx.textrpg.worldinteract.Combat;
import net.breamkillerx.textrpg.entity.control.PlayerEntity;
import net.breamkillerx.textrpg.world.World;
import net.breamkillerx.textrpg.worldinteract.Shop;

import java.util.Map;
import java.util.Scanner;

public class UserInteractionsHandler {
    Scanner scanner = new Scanner(System.in);
    Combat combat = new Combat();
    Shop shop = new Shop(scanner);
    public void lobby(){
        System.out.println(""
                + "Welcome back to base camp!\n"
                + "What would you like to do?\n"
                + "Enter 'A' to battle an enemy!\n"
                + "Enter 'B' to go to the item shop!\n"
                + "Enter 'C' to go to your stats!");
        boolean inputValid = true;
        char response;
        Scanner scanner = new Scanner(System.in);
        do {
            response = Character.toUpperCase(scanner.next().charAt(0));
            switch (response) {
                case 'A':
                    combat.battle();
                    inputValid=true;
                    break;
                case 'B':
                    shop.shop();
                    inputValid=true;
                    break;
                case 'C':
                    stats();
                    inputValid=true;
                    break;
                default:
                    System.out.println("Invalid Input, you put " + response + " you need to put 'A', 'B', or 'C'.");
                    inputValid=false;
                    break;
            }
        } while (!inputValid);

    }
    public void stats(){
        Map<String, Integer> entries = PlayerEntity.playerAttributes;
        System.out.println(""
            + "You are level " + entries.get("level") + ".\n"
            + "You need " + (((entries.get("level")*20)+30)-entries.get("xp")) + " more experience to level up!\n"
            + "Your Health is " + entries.get("baseHealth") + ".\n"
            + "Your Attack is " + entries.get("baseAttack") + ".\n"
            + "You have " + entries.get("gold") + " gold!\n"
            + "Press 'A' to go to the beastiary, press any other key to continue.");
        char choice = Character.toUpperCase(scanner.next().charAt(0));
        if (choice =='A'){
            for (Map.Entry<String, Integer> entry : World.playerKills.entrySet()) {
                System.out.println("You killed " + entry.getValue() + " " + entry.getKey() + "!");
            }
        }
    }
}
