import Enums.ShipEnum;
import Exceptions.IllegalCoordinates;
import Exceptions.IllegalShipLocation;
import Exceptions.WrongLengthShip;
import Model.Coordinates;
import Model.Field;
import Model.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class HyperSkillTests {

    @Test
    @Disabled("The structure of the program has altered for stage five")
    void stageOne() {
        Field field = new Field();

        // F3 F7 Aircraft Carrier, expected add ship
        field.addShip(ShipEnum.AIRCRAFT_CARRIER, new Coordinates(new String[]{"F3", "F7"}));
        String currentFieldState = "  1 2 3 4 5 6 7 8 9 10\n" +
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
        field.addShip(ShipEnum.BATTLESHIP, new Coordinates(new String[]{"A1", "D1"}));
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
                field.addShip(ShipEnum.SUBMARINE, new Coordinates(new String[]{"J7", "J10"})));

        // J10 J8 Submarine, expected add ship
        field.addShip(ShipEnum.SUBMARINE, new Coordinates(new String[]{"J10", "J8"}));
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
                field.addShip(ShipEnum.CRUISER, new Coordinates(new String[]{"B9", "D8"})));

        // B9 D9 Cruiser, expected add ship
        field.addShip(ShipEnum.CRUISER, new Coordinates(new String[]{"B9", "D9"}));
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
                field.addShip(ShipEnum.DESTROYER, new Coordinates(new String[]{"E6", "D6"})));

        // I2 J2 Destroyer, expected add ship
        field.addShip(ShipEnum.DESTROYER, new Coordinates(new String[]{"I2", "J2"}));
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

    @Test
    @Disabled("The structure of the program has altered for stage five")
    void stageTwo() {
        Field field1 = new Field();

        setupGameFieldPlayer1(field1);

        String currentFieldState = "  1 2 3 4 5 6 7 8 9 10\n" +
                "A X ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "B O ~ ~ ~ ~ ~ ~ ~ O ~\n" +
                "C O ~ ~ ~ ~ ~ ~ ~ O ~\n" +
                "D O ~ ~ ~ ~ ~ ~ ~ O ~\n" +
                "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "F ~ ~ O O O O O ~ ~ ~\n" +
                "G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "I ~ O ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "J ~ O ~ ~ ~ ~ ~ O O O\n";

        field1.fireShot(new Point("A1"));

        Assertions.assertEquals(currentFieldState, field1.toString());

        Field field2 = new Field();

        setupGameFieldPlayer1(field2);

        Assertions.assertThrows(IllegalCoordinates.class, () -> field2.fireShot(new Point("Z1")));

        currentFieldState = "  1 2 3 4 5 6 7 8 9 10\n" +
                "A O M ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "B O ~ ~ ~ ~ ~ ~ ~ O ~\n" +
                "C O ~ ~ ~ ~ ~ ~ ~ O ~\n" +
                "D O ~ ~ ~ ~ ~ ~ ~ O ~\n" +
                "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "F ~ ~ O O O O O ~ ~ ~\n" +
                "G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "I ~ O ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "J ~ O ~ ~ ~ ~ ~ O O O\n";

        field2.fireShot(new Point("A2"));
        Assertions.assertEquals(currentFieldState, field2.toString());

    }

    @Test
    @Disabled("The structure of the program has altered for stage five")
    void stageThree() {
        Field field = new Field();
        setupGameFieldPlayer1(field);

        String fieldWithFogOfWarState = "  1 2 3 4 5 6 7 8 9 10\n" +
                "A ~ M ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";
        field.fireShot(new Point("A2"));
        Assertions.assertEquals(fieldWithFogOfWarState, field.fieldWithFogOfWar());

        field = new Field();
        setupGameFieldPlayer1(field);

        fieldWithFogOfWarState = "  1 2 3 4 5 6 7 8 9 10\n" +
                "A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "F ~ ~ ~ X ~ ~ ~ ~ ~ ~\n" +
                "G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";

        field.fireShot(new Point("F4"));
        Assertions.assertEquals(fieldWithFogOfWarState, field.fieldWithFogOfWar());

    }

    @Test
    @Disabled("The structure of the program has altered for stage five")
    void stageFour() {
        Field field = new Field();
        setupGameFieldPlayer1(field);

        simulateMisses(field);
        sinkAircraftCarrier(field);
        sinkBattleship(field);
        sinkSubmarine(field);
        sinkCruiser(field);
        sinkDestroyer(field);

        String fieldState = "  1 2 3 4 5 6 7 8 9 10\n" +
                "A X M ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "B X ~ ~ M ~ ~ M ~ X ~\n" +
                "C X ~ ~ ~ ~ ~ ~ ~ X ~\n" +
                "D X ~ ~ ~ ~ M ~ ~ X ~\n" +
                "E ~ ~ ~ M ~ ~ M ~ M ~\n" +
                "F M ~ X X X X X M ~ ~\n" +
                "G ~ ~ ~ ~ ~ ~ M ~ ~ ~\n" +
                "H ~ M M ~ ~ ~ ~ ~ ~ M\n" +
                "I ~ X ~ ~ M ~ ~ M ~ ~\n" +
                "J ~ X ~ ~ ~ ~ M X X X\n";

        Assertions.assertEquals(fieldState, field.toString());
    }

    private void simulateMisses(Field field) {
        String[] locations = {"A2",
                            "B4", "B7",
                            "D6",
                            "E4", "E7", "E9",
                            "F1", "F8",
                            "G7",
                            "H2", "H3", "H10",
                            "I5", "I8",
                            "J7"};
        for (String location : locations) {
            Point point = new Point(location);
            Assertions.assertEquals("You missed. Try again:\n", field.fireShot(point));
        }
    }

    private void sinkAircraftCarrier(Field field) {
        String f = "F";
        String hitAShip = "You hit a ship! Try again:\n";
        String result = "";
        for (int i = 3; i < 8; i++) {
            result = field.fireShot(new Point(f + i));
            if (i == 7) {
                Assertions.assertEquals("You sank a ship! Specify a new target:\n", result);
            } else {
                Assertions.assertEquals(hitAShip, result);
            }
        }
    }

    private void sinkBattleship(Field field) {
        String[] xCoords = {"A", "B", "C", "D"};
        String hitAShip = "You hit a ship! Try again:\n";
        String result = "";
        for (int i = 0; i < xCoords.length; i++) {
            result = field.fireShot(new Point(xCoords[i] + 1));
            if (i == xCoords.length - 1) {
                Assertions.assertEquals("You sank a ship! Specify a new target:\n", result);
            } else {
                Assertions.assertEquals(hitAShip, result);
            }
        }
    }

    private void sinkSubmarine(Field field) {
        String j = "J";
        String hitAShip = "You hit a ship! Try again:\n";
        String result = "";
        for (int i = 8; i < 11; i++) {
            result = field.fireShot(new Point(j + i));
            if (i == 10) {
                Assertions.assertEquals("You sank a ship! Specify a new target:\n", result);
            } else {
                Assertions.assertEquals(hitAShip, result);
            }
        }
    }

    private void sinkCruiser(Field field) {
        String[] xCoords = {"B", "C", "D"};
        String hitAShip = "You hit a ship! Try again:\n";
        String result = "";
        for (int i = 0; i < xCoords.length; i++) {
            result = field.fireShot(new Point(xCoords[i] + 9));
            if (i == xCoords.length - 1) {
                Assertions.assertEquals("You sank a ship! Specify a new target:\n", result);
            } else {
                Assertions.assertEquals(hitAShip, result);
            }
        }
    }

    private void sinkDestroyer(Field field) {
        String[] xCoords = {"I", "J"};
        String hitAShip = "You hit a ship! Try again:\n";
        String result = "";
        for (int i = 0; i < xCoords.length; i++) {
            result = field.fireShot(new Point(xCoords[i] + 2));
            if (i == xCoords.length - 1) {
                Assertions.assertEquals("You sank the last ship. You won. Congratulations!", result);
            } else {
                Assertions.assertEquals(hitAShip, result);
            }
        }
    }

    @Test
    void StageFive() {
        Field player1Field = new Field();
        Field player2Field = new Field();

        setupGameFieldPlayer1(player1Field);
        setupGameFieldPlayer2(player2Field);

        String fieldsResult = "  1 2 3 4 5 6 7 8 9 10\n" +
                "A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "---------------------\n" +
                "  1 2 3 4 5 6 7 8 9 10\n" +
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

        Assertions.assertEquals(fieldsResult, player1Field.printFields(player2Field));

        fieldsResult = "  1 2 3 4 5 6 7 8 9 10\n" +
                "A ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "B ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "C ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "D ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "I ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "---------------------\n" +
                "  1 2 3 4 5 6 7 8 9 10\n" +
                "A O ~ ~ ~ ~ ~ O O O ~\n" +
                "B O ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "C O ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "D O ~ ~ ~ O O ~ ~ ~ ~\n" +
                "E O ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "G O O O O ~ ~ ~ ~ ~ ~\n" +
                "H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "I ~ O O O ~ ~ ~ ~ ~ ~\n" +
                "J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";

        Assertions.assertEquals(fieldsResult, player2Field.printFields(player1Field));


    }

    private void setupGameFieldPlayer1(Field field) {
        field.addShip(ShipEnum.AIRCRAFT_CARRIER, new Coordinates(new String[]{"F3", "F7"}));
        field.addShip(ShipEnum.BATTLESHIP, new Coordinates(new String[]{"A1", "D1"}));
        field.addShip(ShipEnum.SUBMARINE, new Coordinates(new String[]{"J10", "J8"}));
        field.addShip(ShipEnum.CRUISER, new Coordinates(new String[]{"B9", "D9"}));
        field.addShip(ShipEnum.DESTROYER, new Coordinates(new String[]{"I2", "J2"}));

                /*
                  1 2 3 4 5 6 7 8 9 10
                A O ~ ~ ~ ~ ~ ~ ~ ~ ~
                B O ~ ~ ~ ~ ~ ~ ~ O ~
                C O ~ ~ ~ ~ ~ ~ ~ O ~
                D O ~ ~ ~ ~ ~ ~ ~ O ~
                E ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                F ~ ~ O O O O O ~ ~ ~
                G ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                I ~ O ~ ~ ~ ~ ~ ~ ~ ~
                J ~ O ~ ~ ~ ~ ~ O O O
                */
    }

    private void setupGameFieldPlayer2(Field field) {
        field.addShip(ShipEnum.AIRCRAFT_CARRIER, new Coordinates(new String[]{"A1", "E1"}));
        field.addShip(ShipEnum.BATTLESHIP, new Coordinates(new String[]{"G1", "G4"}));
        field.addShip(ShipEnum.SUBMARINE, new Coordinates(new String[]{"I2", "I4"}));
        field.addShip(ShipEnum.CRUISER, new Coordinates(new String[]{"A7", "A9"}));
        field.addShip(ShipEnum.DESTROYER, new Coordinates(new String[]{"D5", "D6"}));

                /*
                  1 2 3 4 5 6 7 8 9 10
                A O ~ ~ ~ ~ ~ O O O ~
                B O ~ ~ ~ ~ ~ ~ ~ ~ ~
                C O ~ ~ ~ ~ ~ ~ ~ ~ ~
                D O ~ ~ ~ O O ~ ~ ~ ~
                E O ~ ~ ~ ~ ~ ~ ~ ~ ~
                F ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                G O O O O ~ ~ ~ ~ ~ ~
                H ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                I ~ O O O ~ ~ ~ ~ ~ ~
                J ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                */
    }
}
