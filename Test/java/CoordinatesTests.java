import Enums.Ship;
import Exceptions.IllegalShipLocation;
import Exceptions.WrongLengthShip;
import Model.Coordinates;
import Model.Field;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class CoordinatesTests {

    @Test
    void constructorTests() {
        Coordinates coordinates = new Coordinates(new String[]{"A1","B2"});
        Assertions.assertArrayEquals(new int[]{1,1,2,2}, coordinates.getCoordinates());
    }

    @Test
    void hyperSkillStageOneTests() {
        Field field = new Field();

        // F3 F7 Aircraft Carrier, expected add ship
        field.addShip(Ship.AIRCRAFT_CARRIER, new Coordinates(new String[]{"F3", "F7"}).getCoordinates());
        String currentFieldState =  "  1 2 3 4 5 6 7 8 9 10\n" +
                                    "A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                                    "B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                                    "C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                                    "D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                                    "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                                    "F ~ ~ O O O O O ~ ~ ~\n" +
                                    "G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                                    "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                                    "I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                                    "J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";
        Assertions.assertEquals(currentFieldState, field.toString());

        // A1 D1 Battleship, expected add ship
        field.addShip(Ship.BATTLESHIP, new Coordinates(new String[]{"A1", "D1"}).getCoordinates());
        currentFieldState = "  1 2 3 4 5 6 7 8 9 10\n" +
                            "A O ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "B O ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "C O ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "D O ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "F ~ ~ O O O O O ~ ~ ~\n" +
                            "G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";
        Assertions.assertEquals(currentFieldState, field.toString());

        // J7 J10 Submarine, expected throws WrongShipLength exception
        Assertions.assertThrows(WrongLengthShip.class, () ->
                field.addShip(Ship.SUBMARINE, new Coordinates(new String[]{"J7", "J10"}).getCoordinates()));

        // J10 J8 Submarine, expected add ship
        field.addShip(Ship.SUBMARINE, new Coordinates(new String[]{"J10", "J8"}).getCoordinates());
        currentFieldState = "  1 2 3 4 5 6 7 8 9 10\n" +
                            "A O ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "B O ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "C O ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "D O ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "F ~ ~ O O O O O ~ ~ ~\n" +
                            "G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "J ~ ~ ~ ~ ~ ~ ~ O O O\n";
        Assertions.assertEquals(currentFieldState, field.toString());

        // B9 D8 Cruiser, expected IllegalShipLocation exception
        Assertions.assertThrows(IllegalShipLocation.class, () ->
                field.addShip(Ship.CRUISER, new Coordinates(new String[]{"B9", "D8"}).getCoordinates()));

        // B9 D9 Cruiser, expected add ship
        field.addShip(Ship.CRUISER, new Coordinates(new String[]{"B9", "D9"}).getCoordinates());
        currentFieldState = "  1 2 3 4 5 6 7 8 9 10\n" +
                            "A O ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "B O ~ ~ ~ ~ ~ ~ ~ O ~\n" +
                            "C O ~ ~ ~ ~ ~ ~ ~ O ~\n" +
                            "D O ~ ~ ~ ~ ~ ~ ~ O ~\n" +
                            "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "F ~ ~ O O O O O ~ ~ ~\n" +
                            "G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "J ~ ~ ~ ~ ~ ~ ~ O O O\n";
        Assertions.assertEquals(currentFieldState, field.toString());

        // E6 D6 Destroyer, expected IllegalShipLocation exception
        Assertions.assertThrows(IllegalShipLocation.class, () ->
                field.addShip(Ship.DESTROYER, new Coordinates(new String[]{"E6", "D6"}).getCoordinates()));

        // I2 J2 Destroyer, expected add ship
        field.addShip(Ship.DESTROYER, new Coordinates(new String[]{"I2", "J2"}).getCoordinates());
        currentFieldState = "  1 2 3 4 5 6 7 8 9 10\n" +
                            "A O ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "B O ~ ~ ~ ~ ~ ~ ~ O ~\n" +
                            "C O ~ ~ ~ ~ ~ ~ ~ O ~\n" +
                            "D O ~ ~ ~ ~ ~ ~ ~ O ~\n" +
                            "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "F ~ ~ O O O O O ~ ~ ~\n" +
                            "G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "I ~ O ~ ~ ~ ~ ~ ~ ~ ~\n" +
                            "J ~ O ~ ~ ~ ~ ~ O O O\n";
        Assertions.assertEquals(currentFieldState, field.toString());
    }
}
