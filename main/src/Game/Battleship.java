package Game;

import Enums.Ship;
import Model.Coordinates;
import Model.Field;

import java.util.EnumSet;
import java.util.Scanner;

public class Battleship {

    private final String INPUT_COORD = "Enter the coordinates of the %s (%d cells):\n";
    private Field field;
    private EnumSet<Ship> ships = EnumSet.allOf(Ship.class);

    private Scanner scanner = new Scanner(System.in);


    public Battleship() {
        field = new Field();
    }

    public void play() {
//        Coordinates coordinates = new Coordinates("F2", "A1"); // 62, 11
//        System.out.println(Arrays.toString(coordinates.getCoordinatesIndices()));
        Scanner sc = new Scanner(System.in);
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

}
