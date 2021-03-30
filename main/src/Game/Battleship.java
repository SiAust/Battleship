package Game;

import Enums.GameSymbols;
import Enums.Ship;
import Model.Coordinates;
import Model.Field;
import Model.Point;

import java.sql.SQLOutput;
import java.util.EnumSet;
import java.util.Scanner;

public class Battleship {

    private final String INPUT_COORD = "Enter the coordinates of the %s (%d cells):\n";
    private Field field;
    private EnumSet<Ship> ships = EnumSet.allOf(Ship.class);

    private Scanner sc = new Scanner(System.in);


    public Battleship() {
        field = new Field();
    }

    public void play() {
        setupGame();
        shootYourShot();
    }

    private void setupGame() {
        System.out.println(field);
        for (Ship ship : ships) {
            System.out.printf(INPUT_COORD, ship.getName(), ship.getSize());
            while (true) {
                int[] desiredCoordinates = new Coordinates(sc.nextLine().split(" ")).getCoordinates();
                try {
                    // method to place ship throws WrongSizeShip etc
                    field.addShip(ship,desiredCoordinates);
                    break;
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }

            }
            System.out.println(field);
        }
    }

    private void shootYourShot() {
        System.out.println("The game starts!\n");
        System.out.println(field.fieldWithFogOfWar());
        Point point;
        String result;
        while (true) {
            try {
                System.out.println("Take a shot!\n");
                point = new Point(sc.nextLine());
                result = field.fireShot(point);
                System.out.println(field.fieldWithFogOfWar());
                System.out.println(result);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(field);
    }

}
