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
    private Field field;
    private EnumSet<ShipEnum> shipEnums = EnumSet.allOf(ShipEnum.class);

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
    }

    private void shootYourShot() {
        System.out.println("The game starts!\n");
        System.out.println(field.fieldWithFogOfWar());
        Point point;
        String result = "";
        while (!result.contains("Congratulations!")) {
            System.out.println("Take a shot!\n");
            try {
                point = new Point(sc.nextLine());
                result = field.fireShot(point);
                System.out.println(field.fieldWithFogOfWar());
                System.out.println(result);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(field);
    }

}
