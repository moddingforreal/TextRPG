package net.breamkillerx.textrpg.interact;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import net.breamkillerx.textrpg.util.Util;
import net.breamkillerx.textrpg.worldinteract.Combat;
import net.breamkillerx.textrpg.entity.control.PlayerEntity;
import net.breamkillerx.textrpg.world.World;
import net.breamkillerx.textrpg.worldinteract.Shop;

import java.io.FileWriter;
import java.util.Map;
import java.util.Scanner;

public class UserInteractionsHandler {
    Scanner scanner = new Scanner(System.in);
    PlayerEntity player = new PlayerEntity();
    Combat combat = new Combat(scanner, player);
    Shop shop = new Shop(scanner, player);
    public void lobby(){
        System.out.println("""
                Welcome back to base camp!
                What would you like to do?
                Enter 'A' to battle an enemy!
                Enter 'B' to go to the item shop!
                Enter 'C' to go to your stats!""");
        Scanner scanner = new Scanner(System.in);
        char response = Util.readValidInput(scanner, "ABC");
        switch (response) {
            case 'A' -> combat.battle();
            case 'B' -> shop.shop();
            case 'C' -> stats();
        }

    }
    public void stats(){
        Map<String, Integer> entries = player.playerAttributes;
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

    public void save() {
        JsonMapper mapper = new JsonMapper();
        try (FileWriter writer = new FileWriter("saveData.json")) {
            var saveData = mapper.writeValueAsString(player);
            writer.write(saveData);
        } catch (Exception e) {
            System.err.println("An Error occured whilst saving Data");
            e.printStackTrace();
        }
    }
}
