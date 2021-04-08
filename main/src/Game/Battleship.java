package Game;

import Enums.ShipEnum;
import Model.Coordinates;
import Model.Field;
import Model.Point;
import Model.Ships.AircraftCarrier;
import Model.Ships.Ship;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Scanner;

public class Battleship {

    private final String INPUT_COORD = "Enter the coordinates of the %s (%d cells):\n";
    private final String PRESS_ENTER = "Press enter and pass the move to another player";

    private Field player1field = new Field();
    private Field player2Field = new Field();

    private EnumSet<ShipEnum> shipEnums = EnumSet.allOf(ShipEnum.class);

    private Scanner sc = new Scanner(System.in);

    public void play() {
        setupGame();
        // game loop, player one shoots, then player two
        String result = "";
        int player = 2;
        while (true) {
            player = player == 2 ? 1 : 2;
            result = shootYourShot(player);
            if (result.contains("Congratulations!")) {
                break;
            }
            System.out.println(result);
            System.out.println(PRESS_ENTER);
            sc.nextLine();
        }
        System.out.println(result);
    }

    private void setupGame() {
        // Setup player one field
        setupPlayerField(player1field, 1);

        // setup player two field
        setupPlayerField(player2Field, 2);

    }

    private void setupPlayerField(Field field, int player) {
        System.out.printf("Player %d, place your ships on the game field%n%n", player);
        System.out.println(field);
        for (ShipEnum shipEnum : shipEnums) {
            System.out.printf(INPUT_COORD, shipEnum.getName(), shipEnum.getSize());
            while (true) {
                try {
                    // method to place ship throws WrongSizeShip etc
                    field.addShip(shipEnum, new Coordinates(sc.nextLine().split(" ")));
                    break;
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }

            }
            System.out.println(field);
        }
        System.out.println(PRESS_ENTER + "\n...");
        sc.nextLine();
    }

    private String shootYourShot(int player) {
        String result;
        Point point;
        if (player == 1) {
            printFields(player);
            try {
                point = new Point(sc.nextLine());
                result = player2Field.fireShot(point);
            } catch (Exception e) {
                result = e.getMessage();
            }
        } else {
            printFields(player);
            try {
                point = new Point(sc.nextLine());
                result = player1field.fireShot(point);
            } catch (Exception e) {
                result = e.getMessage();
            }
        }
        return result;
    }

    /** Prints the player fields to console according to which player is taking their turn.
     * Their opponents field will be shown above their own with the  current players
     * attempted hits, and misses. The opponents ships are hidden. */
    private void printFields(int player) {
        String topField;
        String bottomField;
        if (player == 1) {
            topField = player2Field.fieldWithFogOfWar();
            bottomField = player1field.toString();
        } else {
            topField = player1field.fieldWithFogOfWar();
            bottomField = player2Field.toString();
        }
        System.out.println(
                topField
                + "---------------------\n"
                + bottomField
                + "\nPlayer " + player + ", it's your turn:\n");
    }

}
