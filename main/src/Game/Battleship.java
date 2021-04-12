package Game;

import Enums.ShipEnum;
import Exceptions.BattleshipException;
import Model.Coordinates;
import Model.Field;
import Model.Point;

import java.util.EnumSet;
import java.util.Scanner;

public class Battleship {

    private final String INPUT_COORD = "Enter the coordinates of the %s (%d cells):\n";
    private final String PRESS_ENTER = "Press enter and pass the move to another player";

    private final Field player1field = new Field();
    private final Field player2Field = new Field();

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
                    if (!(e instanceof BattleshipException)) {
                        System.out.println("Enter valid coordinate. Example: A1 A4, B2 D2\n");
                    } else {
                        System.out.println(e.getMessage());
                    }
                }
            }
            System.out.println(field);
        }
        System.out.println(PRESS_ENTER + "\n...");
        sc.nextLine();
    }

    private String shootYourShot(int player) {
        String playerTurnPrompt = "\nPlayer %d, it's your turn:\n";
        String result;
        Point point;
        if (player == 1) {
            System.out.println(player1field.printFields(player2Field)
                + String.format(playerTurnPrompt, player));
            try {
                point = new Point(sc.nextLine());
                result = player2Field.fireShot(point);
            } catch (Exception e) {
                result = e.getMessage();
            }
        } else {
            System.out.println(player2Field.printFields(player1field)
                + String.format(playerTurnPrompt, player));
            try {
                point = new Point(sc.nextLine());
                result = player1field.fireShot(point);
            } catch (Exception e) {
                result = e.getMessage();
            }
        }
        return result;
    }



}
