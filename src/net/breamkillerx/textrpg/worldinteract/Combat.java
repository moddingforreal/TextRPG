package net.breamkillerx.textrpg.worldinteract;

import net.breamkillerx.textrpg.entity.Enemy;
import net.breamkillerx.textrpg.entity.control.PlayerEntity;
import net.breamkillerx.textrpg.entity.control.inventory.Inventory;
import net.breamkillerx.textrpg.entity.control.inventory.ItemType;
import net.breamkillerx.textrpg.exception.IllegalCombatStateException;
import net.breamkillerx.textrpg.exception.InvalidGenerationException;
import net.breamkillerx.textrpg.util.Util;
import net.breamkillerx.textrpg.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;

public class Combat{
    Scanner scanner;
    PlayerEntity playerEntity;
    public Combat(Scanner scannerIn, PlayerEntity playerIn){
        scanner = scannerIn;
        playerEntity = playerIn;
    }

    public void VictorLoot(Enemy currEnemy) {
        double loot = Math.random() * 100;
        HashMap<String, Integer> player = playerEntity.playerAttributes;
        Inventory inv = playerEntity.inventory;
        if (loot >= 0.0 && loot <= 29.0) {
            inv.addItemsSafe(ItemType.GOLD, 3 * currEnemy.lootMultiplier);
            System.out.println("You found " + (3 * currEnemy.lootMultiplier) + " gold!  You now have " +
                    inv.getAmount(ItemType.GOLD) + " gold!");
        } else if (loot >= 30 && loot <= 49) {
            inv.addItemsSafe(ItemType.GOLD, 5 * currEnemy.lootMultiplier);
            System.out.println("You found " + (5 * currEnemy.lootMultiplier) + " gold!  You now have " +
                    inv.getAmount(ItemType.GOLD) + " gold!");
        } else if (loot >= 50 && loot <= 59) {
            inv.addItemsSafe(ItemType.GOLD, 8 * currEnemy.lootMultiplier);
            System.out.println("You found " + (8 * currEnemy.lootMultiplier) + " gold!  You now have " +
                    inv.getAmount(ItemType.GOLD) + " gold!");
        } else if (loot >= 60 && loot <= 64) {
            inv.addItemsSafe(ItemType.GOLD, 11 * currEnemy.lootMultiplier);
            System.out.println("You found " + (11 * currEnemy.lootMultiplier) + " gold!  You now have " +
                    inv.getAmount(ItemType.GOLD) + " gold!");
        } else if ((loot < 64) || (loot > 100)) {
            throw new InvalidGenerationException("Loot generation out of bounds!");
        }
        playerEntity.playerAttributes = player;
    }
    public void battle(){
        double enemyGen=Math.random()*100.0;
        Enemy currEnemy;
        int level = playerEntity.playerAttributes.get("level");
            if(enemyGen>=91.0 && enemyGen<=100.0) {
                currEnemy = new Enemy("Ogre", level);
            } else if(enemyGen>=18.0 && enemyGen<=50.0) {
                currEnemy = new Enemy("Slime", level);
            } else if(enemyGen>=76.0 && enemyGen<=90.0) {
                currEnemy = new Enemy("Skeleton", level);
            } else if(enemyGen>=51.0 && enemyGen<=75.0) {
                currEnemy = new Enemy("Zombie", level);
            } else if(enemyGen>=10.0 && enemyGen<=12.0) {
                currEnemy = new Enemy("Litch", level);
            } else if(enemyGen==15.0) {
                currEnemy = new Enemy("Dragon", level);
            } else if(enemyGen>=6.0 && enemyGen<=9.0) {
                currEnemy = new Enemy("Undead Warrior", level);
            } else if(enemyGen>=0.0 && enemyGen<=5.0) {
                currEnemy = new Enemy("Viper", level);
            } else if(enemyGen == 16.0 || enemyGen == 17.0) {
                currEnemy = new Enemy("Hydra",level);
            } else if(enemyGen == 13.0 || enemyGen == 14.0) {
                currEnemy = new Enemy("Golem", level);
            } else {
                throw new InvalidGenerationException("Invalid enemy generated!");
            }

        do {
            boolean repeat;
            do {
                repeat = false;
                System.out.println(""
                        + "Your health (" + playerEntity.playerAttributes.get("currHealth") + ")\t\t\t" + currEnemy.Enemy
                        + "'s health (" + currEnemy.health + ")\n"
                        + "What do you do?\n"
                        + "A - Attack         \t\t\tB - Power Up\n"
                        + "C - Weaken opponent\t\t\tD - Use an Item");

                char choice = Util.readValidInput(scanner, "ABCD");
                switch (choice) {
                    case 'A':
                        currEnemy.health -= playerEntity.playerAttributes.get("currAttack");
                        System.out.println("You hurt " + currEnemy.Enemy + " for " +
                                playerEntity.playerAttributes.get("currAttack") + " damage!");
                        System.out.println("\n");
                        break;
                    case 'B':
                        playerEntity.playerAttributes.replace(
                                "currAttack", playerEntity.playerAttributes.get("currAttack")
                                        + playerEntity.playerAttributes.get("level"));
                        System.out.println("You gained " + playerEntity.playerAttributes.get("level") +
                                " attack damage!  Your current attack stat is " +
                                playerEntity.playerAttributes.get("currAttack") + "\n");
                        break;
                    case 'C':
                        if (currEnemy.attack >= 1) {
                            currEnemy.attack -= playerEntity.playerAttributes.get("level");
                        } else if (currEnemy.attack == -1) {
                            currEnemy.attack = 0;
                        }
                        System.out.println(currEnemy.Enemy + " lost " + playerEntity.playerAttributes.get("level")
                                + " attack damage!  Their attack stat is " +
                                currEnemy.attack + "\n");
                        break;
                    case 'D':
                        if (playerEntity.inventory.getAmount(ItemType.POTION) >= 1) {
                            System.out.println("You have " + playerEntity.playerAttributes.get("potions") + " potions!"
                                    + "  One potion heals you to max health, enter 'A' to use, enter 'B' to cancel.");
                            char potionCheck = Util.readValidInput(scanner, "AB");
                            switch (potionCheck) {
                                case 'A' -> {
                                    playerEntity.playerAttributes.replace("currHealth",
                                            playerEntity.playerAttributes.get("baseHealth"));
                                    playerEntity.inventory.addItemsSafe(ItemType.POTION, -1);
                                }
                                case 'B' -> {
                                    System.out.println("canceled!");
                                    repeat = true;
                                }
                            }
                        } else {
                            System.out.println("You do not have any items!");
                            repeat = true;
                        }
                        break;
                }
            }
            while (repeat);

            int choice = (int) (Math.random() * 2.1);
            switch (choice) {
                case 0 -> {
                    playerEntity.playerAttributes.replace("currHealth",
                            playerEntity.playerAttributes.get("currHealth")-currEnemy.attack);
                    System.out.println("You got hurt for " + currEnemy.attack + " damage!\n");
                }
                case 1 -> {
                    currEnemy.attack++;
                    System.out.println(currEnemy.Enemy + " gained " + playerEntity.playerAttributes.get("level") +
                            " attack damage!  Their current attack stat is " + currEnemy.attack + "\n");
                }
                case 2 -> {
                    if (playerEntity.playerAttributes.get("currAttack") >= 1) {
                        playerEntity.playerAttributes.replace("currAttack",
                                playerEntity.playerAttributes.get("currAttack")-1);
                    } else {
                        playerEntity.playerAttributes.replace("currAttack", 0);
                    }
                    System.out.println("You lost " + playerEntity.playerAttributes.get("level") + " attack damage!" +
                            "  Your attack stat is " + playerEntity.playerAttributes.get("currAttack") + "\n");
                }
                default -> throw new IllegalCombatStateException("Switch-case failed... somehow... anyways, error!");
            }

            if (currEnemy.health<=0){
                playerEntity.isAlive=false;
            } else playerEntity.isAlive= playerEntity.playerAttributes.get("currHealth") <= 0;
        } while (playerEntity.isAlive);
        if (currEnemy.health<=0){
            playerEntity.playerAttributes.replace("xp", playerEntity.playerAttributes.get("xp")+30);
            System.out.println("You Win and gain 30 experience!!!  You need " +
                    ((playerEntity.playerAttributes.get("level")*20+30)-(playerEntity.playerAttributes.get("xp"))) +
                    " more experience to level up!");
            VictorLoot(currEnemy);
            Map<String, Integer> playerKills = World.playerKills;
            Consumer<String> updateKills = s -> playerKills.replace(s, playerKills.get(s)+1);
            switch (currEnemy.enemyId) {
                case 1 -> updateKills.accept("killsSlime");
                case 2 -> updateKills.accept("killsZombie");
                case 3 -> updateKills.accept("killsSkeleton");
                case 4 -> updateKills.accept("killsOgre");
                case 5 -> updateKills.accept("killsViper");
                case 6 -> updateKills.accept("killsUndeadWarrior");
                case 7 -> updateKills.accept("killsLitch");
                case 8 -> updateKills.accept("killsGolem");
                case 9 -> updateKills.accept("killsHydra");
                case 10 -> updateKills.accept("killsDragon");
            }
        } else {
            System.out.println("You Lose!!!");

        }
        if (playerEntity.playerAttributes.get("xp")>=playerEntity.playerAttributes.get("level")*20+30) {
            playerEntity.playerAttributes.replace("level", playerEntity.playerAttributes.get("level") + 1);
            playerEntity.playerAttributes.replace("xp", playerEntity.playerAttributes.get("xp") - (
                    playerEntity.playerAttributes.get("level") * 20 + 30));
            System.out.println("You leveled up!!!  Enter 'A' to upgrade health, and enter 'B' to upgrade attack!");

            char choice = Util.readValidInput(scanner, "AB");
            switch (choice) {
                case 'A' -> playerEntity.playerAttributes.replace("baseHealth",
                        playerEntity.playerAttributes.get("baseHealth") + 3);
                case 'B' -> playerEntity.playerAttributes.replace("baseAttack",
                        playerEntity.playerAttributes.get("baseAttack") + 1);
            }
        }
    }
}
