package lastpencil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numPencils;
        String firstPlayer;

        // Input the number of pencils
        System.out.println("How many pencils would you like to use:");
        while (true) {
            String input = scanner.nextLine();
            try {
                numPencils = Integer.parseInt(input);
                if (numPencils <= 0) {
                    System.out.println("The number of pencils should be positive");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            }
        }

        // Input to choose who goes first
        System.out.println("Who will be the first (Andrew, Stasy):");
        while (true) {
            firstPlayer = scanner.nextLine().trim();
            if (firstPlayer.equalsIgnoreCase("Andrew") || firstPlayer.equalsIgnoreCase("Stasy")) {
                break;
            } else {
                System.out.println("Choose between 'Andrew' and 'Stasy'");
            }
        }

        // Game loop
        int pencilsRemaining = numPencils;
        String currentPlayer = firstPlayer;
        String otherPlayer = firstPlayer.equalsIgnoreCase("Andrew") ? "Stasy" : "Andrew";
        while (true) {
            // Print pencils
            for (int i = 0; i < pencilsRemaining; i++) {
                System.out.print("|");
            }
            System.out.println();
            System.out.println(currentPlayer + "'s turn!");
            
            // limit to 3 pencils per turn
            int pencilsRemoving;
            while (true) {
                String input = scanner.nextLine();
                try {
                    pencilsRemoving = Integer.parseInt(input);
                    if (pencilsRemoving < 1 || pencilsRemoving > 3) {
                        System.out.println("Possible values: '1', '2' or '3'");
                    } else if (pencilsRemoving > pencilsRemaining) {
                        System.out.println("Too many pencils were taken");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Possible values: '1', '2' or '3'");
                }
            }
            pencilsRemaining -= pencilsRemoving; 

            // win cause
            if (pencilsRemaining == 0) {
                System.out.println(otherPlayer + " won!");
                break;
            }

            // Switch players
            String temp = currentPlayer;
            currentPlayer = otherPlayer;
            otherPlayer = temp;
        }
        scanner.close();
    }
}
