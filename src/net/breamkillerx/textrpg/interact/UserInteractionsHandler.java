package net.breamkillerx.textrpg.interact;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import net.breamkillerx.textrpg.entity.control.inventory.ItemType;
import net.breamkillerx.textrpg.util.Util;
import net.breamkillerx.textrpg.worldinteract.Combat;
import net.breamkillerx.textrpg.entity.control.PlayerEntity;
import net.breamkillerx.textrpg.world.World;
import net.breamkillerx.textrpg.worldinteract.Shop;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Scanner;

public class UserInteractionsHandler {
    Scanner scanner = new Scanner(System.in);
    PlayerEntity player = loadPlayer();
    Combat combat = new Combat(scanner, player);
    Shop shop = new Shop(scanner, player);
    public void lobby(){
        System.out.println("""
                
                Welcome back to base camp!
                What would you like to do?
                Enter 'A' to battle an enemy!
                Enter 'B' to go to the item shop!
                Enter 'C' to go to your stats!
                Enter 'Q' to quit!""");
        Scanner scanner = new Scanner(System.in);
        char response = Util.readValidInput(scanner, "ABCQ");
        switch (response) {
            case 'A' -> combat.battle();
            case 'B' -> shop.shop();
            case 'C' -> stats();
            case 'Q' -> World.quit = true;
        }
        save();
    }
    public void stats() {
        System.out.println(""
                + "You are level " + player.getAttribute("level") + ".\n"
                + "You need " + (((player.getAttribute("level") * 20) + 30) - player.getAttribute("xp")) + " more experience to level up!\n"
                + "Your Health is " + player.getAttribute("baseHealth") + ".\n"
                + "Your Attack is " + player.getAttribute("baseAttack") + ".\n"
                + "You have " + player.inventory.getAmount(ItemType.GOLD) + " gold!\n"
                + "Press 'A' to go to the beastiary, press any other key to continue.");
        char choice = Character.toUpperCase(scanner.next().charAt(0));
        if (choice == 'A') {
            player.getStatistics().forEach((id, val) -> System.out.println(id + ": " + val));
        }
    }

    private static PlayerEntity loadPlayer(){
        JsonMapper mapper = new JsonMapper();
        try {
            var saveData = Files.readString(Path.of("saveData.json"));
            return mapper.readValue(saveData, PlayerEntity.class);
        }catch (Exception e) {
            System.out.println("Savedata not found or invalid, new save will be generated!");
        }
        return new PlayerEntity();
    }

    public void save() {
        JsonMapper mapper = new JsonMapper();
        try {
            var saveData = mapper.writeValueAsString(player);
            Files.writeString(Path.of("saveData.json"), saveData);
        } catch (Exception e) {
            System.err.println("An Error occured whilst saving Data");
            e.printStackTrace();
        }
    }
}
