package net.breamkillerx.textrpg.worldinteract;

import net.breamkillerx.textrpg.entity.Enemy;
import net.breamkillerx.textrpg.entity.control.PlayerEntity;
import net.breamkillerx.textrpg.entity.control.inventory.Inventory;
import net.breamkillerx.textrpg.entity.control.inventory.ItemType;
import net.breamkillerx.textrpg.exception.IllegalCombatStateException;
import net.breamkillerx.textrpg.exception.InvalidGenerationException;
import net.breamkillerx.textrpg.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class Combat{
    Scanner scanner = new Scanner(System.in);
    Enemy currEnemy = new Enemy();

    public void VictorLoot(){
        double loot =Math.random()*100;
        HashMap<String, Integer> player = PlayerEntity.playerAttributes;
        Inventory inv = PlayerEntity.inventory;
        if(loot>=0.0 && loot<=29.0) {
            inv.addItemsSafe(ItemType.GOLD, 3 * currEnemy.lootMultiplier);
            System.out.println("You found " + (3 * currEnemy.lootMultiplier) + " gold!  You now have " +
                    inv.getAmount(ItemType.GOLD) + " gold!");
        } else if(loot>=30 && loot<=49){
            inv.addItemsSafe(ItemType.GOLD, 5 * currEnemy.lootMultiplier);
            System.out.println("You found " + (5 * currEnemy.lootMultiplier) + " gold!  You now have " +
                    inv.getAmount(ItemType.GOLD) + " gold!");
        }else if(loot>=50 && loot<=59){
            inv.addItemsSafe(ItemType.GOLD, 8 * currEnemy.lootMultiplier);
            System.out.println("You found " + (8 * currEnemy.lootMultiplier) + " gold!  You now have " +
                    inv.getAmount(ItemType.GOLD) + " gold!");
        } else if(loot>=60 && loot<=64) {
            inv.addItemsSafe(ItemType.GOLD, 11 * currEnemy.lootMultiplier);
            System.out.println("You found " + (11 * currEnemy.lootMultiplier) + " gold!  You now have " +
                    inv.getAmount(ItemType.GOLD) + " gold!");
        } else if((loot<64) || (loot>100)) {
                throw new InvalidGenerationException("Loot generation out of bounds!");
        }
        PlayerEntity.playerAttributes = player;
    }
    public void battle(){
        double enemyGen=Math.random()*100.0;
            if(enemyGen>=91.0 && enemyGen<=100.0) {
                currEnemy = new Enemy("Ogre");
            } else if(enemyGen>=18.0 && enemyGen<=50.0) {
                currEnemy = new Enemy("Slime");
            } else if(enemyGen>=76.0 && enemyGen<=90.0) {
                currEnemy = new Enemy("Skeleton");
            } else if(enemyGen>=51.0 && enemyGen<=75.0) {
                currEnemy = new Enemy("Zombie");
            } else if(enemyGen>=10.0 && enemyGen<=12.0) {
                currEnemy = new Enemy("Litch");
            } else if(enemyGen==15.0) {
                currEnemy = new Enemy("Dragon");
            } else if(enemyGen>=6.0 && enemyGen<=9.0) {
                currEnemy = new Enemy("Undead Warrior");
            } else if(enemyGen>=0.0 && enemyGen<=5.0) {
                currEnemy = new Enemy("Viper");
            } else if(enemyGen == 16.0 || enemyGen == 17.0) {
                currEnemy = new Enemy("Hydra");
            } else if(enemyGen == 13.0 || enemyGen == 14.0) {
                currEnemy = new Enemy("Golem");
            } else {
                throw new InvalidGenerationException("Invalid enemy generated!");
            }
        do {
            System.out.println(""
                + "Your health (" + PlayerEntity.playerAttributes.get("currHealth") + ")\t\t\t" + currEnemy.Enemy
                + "'s health (" + currEnemy.health + ")\n"
                + "What do you do?\n"
                + "A - Attack         \t\t\tB - Power Up\n"
                + "C - Weaken opponent\t\t\tD - Use an Item");
            boolean choiceValid = true;
            do {
                char choice = Character.toUpperCase(scanner.next().charAt(0));
                switch (choice) {
                    case 'A':
                        currEnemy.health -= PlayerEntity.playerAttributes.get("currAttack");
                        System.out.println("You hurt "+currEnemy.Enemy+" for " +
                                PlayerEntity.playerAttributes.get("currAttack") + " damage!");
                        System.out.println("\n");
                        choiceValid = true;
                        break;
                    case 'B':
                        PlayerEntity.playerAttributes.replace(
                                "currAttack", PlayerEntity.playerAttributes.get("currAttack")
                                        + PlayerEntity.playerAttributes.get("level"));
                        System.out.println("You gained " + PlayerEntity.playerAttributes.get("level") +
                                " attack damage!  Your current attack stat is " +
                                PlayerEntity.playerAttributes.get("currAttack") + "\n");
                        choiceValid = true;
                        break;
                    case 'C':
                        if(currEnemy.attack>=1){
                            currEnemy.attack-=PlayerEntity.playerAttributes.get("level");
                        } else if(currEnemy.attack==-1){
                            currEnemy.attack = 0;
                        }
                        System.out.println(currEnemy.Enemy+" lost " + PlayerEntity.playerAttributes.get("level")
                                + " attack damage!  Their attack stat is " +
                                currEnemy.attack + "\n");
                        choiceValid = true;
                        break;
                    case 'D':
                        if (PlayerEntity.inventory.getAmount(ItemType.POTION)>=1) {
                            System.out.println("You have " + PlayerEntity.playerAttributes.get("potions") + " potions!"
                                    + "  One potion heals you to max health, enter 'A' to use, enter 'B' to cancel.");
                            char potionCheck = scanner.next().charAt(0);
                            switch (potionCheck) {
                                case 'A' -> {
                                    PlayerEntity.playerAttributes.replace("currHealth",
                                            PlayerEntity.playerAttributes.get("baseHealth"));
                                    PlayerEntity.inventory.addItemsSafe(ItemType.POTION, -1);
                                    choiceValid = true;
                                }
                                case 'B' -> {
                                    choiceValid = false;
                                    System.out.println("Your health (" + PlayerEntity.playerAttributes.get("currHealth")
                                            + ")" + currEnemy.Enemy + "'s health (" + currEnemy.health + ")");
                                    System.out.println("""
                                            What do you do?
                                            A - Attack\t\t\tB - Power Up
                                            C - Weaken opponent\t\t\tD - Use an Item""");
                                }
                                default -> System.out.println("Invalid Input!");
                            }
                        } else {
                            System.out.println("You do not have any items!");
                            choiceValid = true;
                        }

                        break;
                    default:
                        System.out.println("Invalid Input!\n");
                }
            } while(!choiceValid);
            int choice = (int) (Math.random() * 2.1);
            switch (choice) {
                case 0 -> {
                    PlayerEntity.playerAttributes.replace("currHealth",
                            PlayerEntity.playerAttributes.get("currHealth")-currEnemy.attack);
                    System.out.println("You got hurt for " + currEnemy.attack + " damage!\n");
                }
                case 1 -> {
                    currEnemy.attack++;
                    System.out.println(currEnemy.Enemy + " gained " + PlayerEntity.playerAttributes.get("level") +
                            " attack damage!  Their current attack stat is " + currEnemy.attack + "\n");
                }
                case 2 -> {
                    if (PlayerEntity.playerAttributes.get("currAttack") >= 1) {
                        PlayerEntity.playerAttributes.replace("currAttack",
                                PlayerEntity.playerAttributes.get("currAttack")-1);
                    } else {
                        PlayerEntity.playerAttributes.replace("currAttack", 0);
                    }
                    System.out.println("You lost " + PlayerEntity.playerAttributes.get("level") + " attack damage!" +
                            "  Your attack stat is " + PlayerEntity.playerAttributes.get("currAttack") + "\n");
                }
                default -> throw new IllegalCombatStateException("Switch-case failed... somehow... anyways, error!");
            }

            if (currEnemy.health<=0){
                PlayerEntity.isAlive=false;
            } else PlayerEntity.isAlive= PlayerEntity.playerAttributes.get("currHealth") <= 0;
        } while (PlayerEntity.isAlive);
        if (currEnemy.health<=0){
            PlayerEntity.playerAttributes.replace("xp", PlayerEntity.playerAttributes.get("xp")+30);
            System.out.println("You Win and gain 30 experience!!!  You need " +
                    ((PlayerEntity.playerAttributes.get("level")*20+30)-(PlayerEntity.playerAttributes.get("xp"))) +
                    " more experience to level up!");
            VictorLoot();
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
        if (PlayerEntity.playerAttributes.get("xp")>=PlayerEntity.playerAttributes.get("level")*20+30){
            PlayerEntity.playerAttributes.replace("level", PlayerEntity.playerAttributes.get("level")+1);
            PlayerEntity.playerAttributes.replace("xp", PlayerEntity.playerAttributes.get("xp") - (
                    PlayerEntity.playerAttributes.get("level")*20+30));
            System.out.println("You leveled up!!!  Enter 'A' to upgrade health, and enter 'B' to upgrade attack!");

            boolean choiceValid = true;
            do {
                char choice = Character.toUpperCase(scanner.next().charAt(0));
                switch (choice) {
                    case 'A' -> {
                        PlayerEntity.playerAttributes.replace("baseHealth",
                                PlayerEntity.playerAttributes.get("baseHealth")+3);
                        choiceValid = true;
                    }
                    case 'B' -> {
                        PlayerEntity.playerAttributes.replace("baseAttack",
                                PlayerEntity.playerAttributes.get("baseAttack")+1);
                        choiceValid = true;
                    }
                    default -> {
                        System.out.println("Invalid Response");
                        choiceValid = false;
                    }
                }
            } while (!choiceValid);
        }
    }

}
