package net.breamkillerx.textrpg.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Util {

    public static char readValidInput(Scanner scanner, String validChars){
        while (true) {
            char response = Character.toUpperCase(scanner.next().charAt(0));
            if (validChars.indexOf(response) != -1)
                return response;
            String options = Arrays.stream(validChars.split("")).map(chr -> '\'' + chr + '\'').collect(Collectors.joining(", "));
            System.out.println("Invalid Input, you put '" + response + "', but valid responses are " + options);
        }
    }

}
