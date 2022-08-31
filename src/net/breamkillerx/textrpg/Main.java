package net.breamkillerx.textrpg;

import java.util.Scanner;

import net.breamkillerx.textrpg.entity.control.PlayerEntity;
import net.breamkillerx.textrpg.entity.control.inventory.ItemType;
import net.breamkillerx.textrpg.worldinteract.*;
import net.breamkillerx.textrpg.interact.*;
import net.breamkillerx.textrpg.world.*;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserInteractionsHandler uihandler = new UserInteractionsHandler();

        System.out.println("Welcome to TextRPG!");
        while (!World.quit) {
            uihandler.lobby();
        }
    }
}





