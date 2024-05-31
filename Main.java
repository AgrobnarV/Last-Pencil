package lastpencil;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        int numPencils = getNumPencils(); // initial number of pencils
        char[] stickArray = createStickArray(numPencils); // array representing the sticks
        String[] players = new String[]{"Andrew", "Stasy"}; // player names
        String[] turnOrder = createTurnArray(getFirstPlayer(players), players);

        // play the game until no sticks are left
        int sticks = stickArray.length;
        int turn = 0;
        while (sticks > 0) {
            printSticks(sticks, stickArray);
            printPlayerTurn(turn, turnOrder);
            sticks -= removedSticks(sticks, turn, turnOrder); // Remove sticks based on the player's input
            turn++;
        }

        printWinner(turn, turnOrder);
    }

    // user input for number of pencils
    public static int getNumPencils() {
        System.out.println("How many pencils would you like to use:");
        while (true) {
            String input = scanner.nextLine();
            try {
                int numPencils = Integer.parseInt(input);
                if (numPencils <= 0) {
                    System.out.println("The number of pencils should be positive");
                } else {
                    return numPencils;
                }
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            }
        }
    }

    // removing sticks based on the current player
    public static int removedSticks(int remainingSticks, int turn, String[] turnOrder) {
        String currentPlayer = turnOrder[turn % 2];
        if (currentPlayer.equals("Andrew")) {
            return getAndrewInput(remainingSticks);
        } else {
            return getStasyInput(remainingSticks);
        }
    }

    // the player logic to remove
    public static int getAndrewInput(int remainingSticks) {
        while (true) {
            String input = scanner.nextLine();
            try {
                int num = Integer.parseInt(input);
                if (num < 1 || num > 3 || num > remainingSticks) {
                    System.out.println("Invalid input. Choose between '1', '2', or '3'");
                } else {
                    return num;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Choose between '1', '2', or '3'");
            }
        }
    }

    // the Stasy (bot) logic to remove
    public static int getStasyInput(int remainingSticks) {
        int n;
        if (remainingSticks % 4 == 1) {
            n = rand.nextInt(3) + 1;
        } else if (remainingSticks % 4 == 0) {
            n = 3;
        } else if (remainingSticks % 4 == 3) {
            n = 2;
        } else {
            n = 1;
        }
        System.out.println(n);
        return n;
    }

    // user input who is the player first
    public static String getFirstPlayer(String[] players) {
        System.out.printf("Who will be the first (%s, %s):%n", players[0], players[1]);
        while (true) {
            String input = scanner.nextLine();
            if (players[0].equalsIgnoreCase(input) || players[1].equalsIgnoreCase(input)) {
                return input;
            } else {
                System.out.printf("Choose between '%s' and '%s'%n", players[0], players[1]);
            }
        }
    }

    // array for the turn order
    public static String[] createTurnArray(String whoPlaysFirst, String[] players) {
        return whoPlaysFirst.equalsIgnoreCase(players[0]) ? players : new String[]{players[1], players[0]};
    }

    // array for the sticks
    public static char[] createStickArray(int numPencils) {
        char[] stickArray = new char[numPencils];
        Arrays.fill(stickArray, '|');
        return stickArray;
    }

    // print whose turn it is
    public static void printPlayerTurn(int turn, String[] turnOrder) {
        String currentPlayer = turnOrder[turn % 2];
        String symbol = currentPlayer.equals("Andrew") ? "!" : ":";
        System.out.printf("%s's turn%s%n", currentPlayer, symbol);
    }

    // print the winner of the game
    public static void printWinner(int turn, String[] turnOrder) {
        String winner = (turn % 2 == 0) ? turnOrder[0] : turnOrder[1];
        System.out.printf("%s won!%n", winner);
    }

    // print the current state of sticks
    public static void printSticks(int sticks, char[] stickArray) {
        for (int i = 0; i < sticks; i++) {
            System.out.print(stickArray[i]);
        }
        System.out.println();
    }
}
