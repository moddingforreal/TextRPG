package net.breamkillerx.textrpg;

import java.util.Scanner;
import net.breamkillerx.textrpg.worldinteract.*;
import net.breamkillerx.textrpg.interact.*;
import net.breamkillerx.textrpg.world.*;


public class Main {

    public static void main(String[] args) {
        Combat combat = new Combat();
        Scanner scanner = new Scanner(System.in);
        UserInteractionsHandler uihandler = new UserInteractionsHandler();


        while (!World.quit) {
            System.out.println("Welcome to TextRPG!");
            uihandler.lobby();
        }
    }
}





