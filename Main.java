package lastpencil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        // user input the number of pencils
        System.out.println("How many pencils would you like to use:");
        int numPencils;
        while (true) {
            try {
                numPencils = Integer.parseInt(scanner.nextLine());
                if (numPencils <= 0) {
                    System.out.println("The number of pencils should be positive");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            }
        }

        // user input to choose who goes first
        System.out.println("Who will be the first (Andrew, Stasy):");
        String firstPlayer;
        while (true) {
            firstPlayer = scanner.nextLine().trim();
            if (firstPlayer.equalsIgnoreCase("Andrew") || firstPlayer.equalsIgnoreCase("Stasy")) {
                break;
            } else {
                System.out.println("Please choose between 'Andrew' and 'Stasy'");
            }
        }

        // Print the pencils
        for (int i = 0; i < numPencils; i++) {
            System.out.print("|");
        }
        System.out.println();

        System.out.println(firstPlayer + " is going first!");
    }
}
