package net.breamkillerx.textrpg.worldinteract;

import net.breamkillerx.textrpg.entity.Enemy;
import net.breamkillerx.textrpg.entity.EnemyType;
import net.breamkillerx.textrpg.entity.control.PlayerEntity;
import net.breamkillerx.textrpg.entity.control.inventory.ItemType;
import net.breamkillerx.textrpg.exception.IllegalCombatStateException;
import net.breamkillerx.textrpg.util.Util;

import java.util.Scanner;

public class Combat{
    Scanner scanner;
    PlayerEntity playerEntity;
    public Combat(Scanner scannerIn, PlayerEntity playerIn){
        scanner = scannerIn;
        playerEntity = playerIn;
    }

    public void VictorLoot(Enemy currEnemy) {
        double loot = Math.random() * 100;
        int gold;
        if(loot < 30)
            gold = 3;
        else if(loot < 50)
            gold = 5;
        else if(loot < 60)
            gold = 8;
        else if(loot < 65)
            gold = 11;
        else return;
        int goldFound = gold * currEnemy.getLootMultiplier();
        playerEntity.inventory.addItems(ItemType.GOLD, goldFound);
        System.out.println("You found " + goldFound + " gold!  You now have " +
                playerEntity.inventory.getAmount(ItemType.GOLD) + " gold!");
    }
    public void battle() {
        double enemyGen = Math.random() * 100.0;
        EnemyType enemyType;
        if (enemyGen < 30)
            enemyType = EnemyType.SLIME;
        else if (enemyGen < 55)
            enemyType = EnemyType.ZOMBIE;
        else if (enemyGen < 70)
            enemyType = EnemyType.SKELETON;
        else if (enemyGen < 80)
            enemyType = EnemyType.OGRE;
        else if (enemyGen < 86)
            enemyType = EnemyType.VIPER;
        else if (enemyGen < 91)
            enemyType = EnemyType.UNDEAD_WARRIOR;
        else if (enemyGen < 94)
            enemyType = EnemyType.LICH;
        else if (enemyGen < 97)
            enemyType = EnemyType.HYDRA;
        else if (enemyGen < 99)
            enemyType = EnemyType.GOLEM;
        else
            enemyType = EnemyType.DRAGON;
        int level = playerEntity.playerAttributes.get("level");
        Enemy currEnemy = new Enemy(enemyType, level);

        do {
            boolean repeat;
            do {
                repeat = false;
                System.out.println(""
                        + "Your health (" + playerEntity.playerAttributes.get("currHealth") + ")\t\t\t" + currEnemy.getName()
                        + "'s health (" + currEnemy.health + ")\n"
                        + "What do you do?\n"
                        + "A - Attack         \t\t\tB - Power Up\n"
                        + "C - Weaken opponent\t\t\tD - Use an Item");

                char choice = Util.readValidInput(scanner, "ABCD");
                switch (choice) {
                    case 'A':
                        int enemyHealth = currEnemy.health;
                        currEnemy.health = Math.min(enemyHealth - playerEntity.getAttribute("currAttack"), 0);
                        System.out.println("You hurt " + currEnemy.getName() + " for " +
                                (enemyHealth - currEnemy.health) + " damage!\n");
                        break;
                    case 'B':
                        int currAttack = playerEntity.updateAttribute("currAttack", _currAttack -> _currAttack + level);
                        System.out.println("You gained " + level + " attack damage!  Your current attack stat is " + currAttack + "\n");
                        break;
                    case 'C':
                        if (currEnemy.attack > 0) {
                            int enemyAttack = currEnemy.attack;
                            currEnemy.attack = Math.min(enemyAttack - level, 0);
                            System.out.println(currEnemy.getName() + " lost " + (enemyAttack - currEnemy.attack)
                                    + " attack damage!  Their attack stat is " + currEnemy.attack + "\n");
                        }
                        break;
                    case 'D':
                        int potions = playerEntity.inventory.getAmount(ItemType.POTION);
                        if (potions >= 1) {
                            System.out.println("You have " + potions + " potions!"
                                    + "  One potion heals you to max health, enter 'A' to use, enter 'B' to cancel.");
                            char potionCheck = Util.readValidInput(scanner, "AB");
                            switch (potionCheck) {
                                case 'A' -> {
                                    playerEntity.putStat("currHealth", playerEntity.getAttribute("baseHealth"));
                                    playerEntity.inventory.addItems(ItemType.POTION, -1);
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
                    playerEntity.updateAttribute("currHealth", health -> health - currEnemy.attack);
                    System.out.println("You got hurt for " + currEnemy.attack + " damage!\n");
                }
                case 1 -> {
                    currEnemy.attack += level;
                    System.out.println(currEnemy.getName() + " gained " + level +
                            " attack damage!  Their current attack stat is " + currEnemy.attack + "\n");
                }
                case 2 -> {
                    int currAttack = playerEntity.getAttribute("currAttack");
                    if(currAttack > 0) {
                        int newAttack = Math.min(currAttack - level, 0);
                        playerEntity.putAttribute("currAttack", newAttack);
                        System.out.println("You lost " + (currAttack - newAttack) + " attack damage!" +
                                "  Your attack stat is " + newAttack + "\n");
                    }
                }
                default -> throw new IllegalCombatStateException("Switch-case failed... somehow... anyways, error!");
            }
            playerEntity.isAlive = playerEntity.getAttribute("currHealth") <= 0;
        } while (playerEntity.isAlive && currEnemy.isAlive());

        if (currEnemy.health <= 0) {
            int xp = playerEntity.updateAttribute("xp", _xp -> _xp + 30);
            System.out.println("You Win and gain 30 experience!!!  You need " +
                    (level * 20 + 30 - xp) + " more experience to level up!");
            VictorLoot(currEnemy);
            playerEntity.updateStat(currEnemy.getKillStatName(), val -> val + 1);
        } else {
            System.out.println("You Lose!!!");
            playerEntity.respawn();
        }
        int xp = playerEntity.getAttribute("xp");
        if (xp >= level * 20 + 30) {
            int newLevel = playerEntity.updateAttribute("level", _level -> _level + 1);
            playerEntity.updateAttribute("xp", _xp -> _xp - (newLevel * 20 + 30));
            System.out.println("You leveled up!!!  Enter 'A' to upgrade health, and enter 'B' to upgrade attack!");

            char choice = Util.readValidInput(scanner, "AB");
            switch (choice) {
                case 'A' -> playerEntity.updateAttribute("baseHealth", baseHealth -> baseHealth + 3);
                case 'B' -> playerEntity.updateAttribute("baseAttack", baseAttack -> baseAttack + 1);
            }
        }
    }
}
