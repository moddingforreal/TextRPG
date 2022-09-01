package net.breamkillerx.textrpg.worldinteract;

import net.breamkillerx.textrpg.entity.control.PlayerEntity;
import net.breamkillerx.textrpg.entity.control.inventory.ItemType;
import net.breamkillerx.textrpg.util.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

public class Shop {
    Scanner scanner;
    PlayerEntity playerEntity;
    static final Map<Character, ShopOption> SHOP_OPTIONS;

    public Shop(Scanner scanner, PlayerEntity playerIn) {
        this.scanner = scanner;
        playerEntity = playerIn;
    }

    public void shop() {
        boolean exitshop = false;
        do {
            System.out.println("""
                    
                    Welcome to the shop!  Below are the items you can purchase!
                    Enter 'A' to buy a Potion - 3 gold
                    Enter 'B' to buy an Attack Upgrade - 5 gold
                    Enter 'C' to buy a Health Upgrade - 5 gold
                    Enter 'D' to go leave the shop!
                    """);

            char shopChoice = Util.readValidInput(scanner, "ABCD");
            if (shopChoice != 'D') {
                var option = SHOP_OPTIONS.get(shopChoice);
                option.buy(playerEntity);
            } else {
                exitshop = true;
            }
        } while (!exitshop);
    }

    static {
        SHOP_OPTIONS = new HashMap<>();
        SHOP_OPTIONS.put('A', ShopOption.buyItem(3, ItemType.POTION, 1, "You bought a potion!"));
        SHOP_OPTIONS.put('B', ShopOption.statUpgrade(5, "baseAttack", baseAttack -> baseAttack + 1, baseAttack -> "You bought an Attack Upgrade! Your attack is now " + baseAttack + "."));
        SHOP_OPTIONS.put('C', ShopOption.statUpgrade(5, "baseHealth", baseHealth -> baseHealth + 1, baseHealth -> "You bought a health upgrade!  Your max health is now " + baseHealth + "."));
    }

    record ShopOption(int cost, Function<PlayerEntity, String> boughtMessageSupplier) {

        public void buy(PlayerEntity player) {
            int gold = player.inventory.getAmount(ItemType.GOLD);
            if (gold >= cost) {
                int newGold = gold - cost;
                player.inventory.addItems(ItemType.GOLD, -cost);
                System.out.println(boughtMessageSupplier.apply(player) + " You have " + newGold + " gold left!");
            } else
                System.out.println("You do not have enough gold!");
        }

        public static ShopOption statUpgrade(int cost, String attribute, Function<Integer, Integer> modifyStatFunc, Function<Integer, String> boughtMessageSupplier) {
            return new ShopOption(cost, player -> {
                int newStatValue = player.updateAttribute(attribute, modifyStatFunc);
                return boughtMessageSupplier.apply(newStatValue);
            });
        }

        public static ShopOption buyItem(int cost, ItemType itemType, int amount, String buyMessage) {
            return new ShopOption(cost, player -> {
                player.inventory.addItems(itemType, amount);
                return buyMessage;
            });
        }
    }
}
