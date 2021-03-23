package Model;

import Enums.Ship;
import Exceptions.ErrorShipOverlapException;
import Exceptions.IllegalShipLocation;
import Exceptions.WrongLengthShip;

import java.util.Arrays;

import static Enums.GameSymbols.*;

public class Field {

    String[][] field = new String[11][11];

    public Field() {
        createField();
    }

    private void createField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                /* top left corner blank */
                if (i == 0 && j == 0) {
                    field[i][j] = " ";
                }
                /* top row numbered */
                if (i == 0 && j > 0) {
                    field[i][j] = String.valueOf(j); // adding 0 to make it 49?
                }
                if (i > 0 && j == 0) {
                    String c = "";
                    switch (i) {
                        case 1:
                            c = "A";
                            break;
                        case 2:
                            c = "B";
                            break;
                        case 3:
                            c = "C";
                            break;
                        case 4:
                            c = "D";
                            break;
                        case 5:
                            c = "E";
                            break;
                        case 6:
                            c = "F";
                            break;
                        case 7:
                            c = "G";
                            break;
                        case 8:
                            c = "H";
                            break;
                        case 9:
                            c = "I";
                            break;
                        case 10:
                            c = "J";
                            break;
                    }
                    field[i][j] = c;
                }
                if (i > 0 && j > 0) {
                    field[i][j] = FOG_OF_WAR.getSymbol();
                }
            }
        }
    }
    /** throws WrongSizeShip, IllegalCoordinates, ErrorShipOverlapException */
    public void addShip(Ship ship, int[] coordinates) {
        /* Check we're placing a ship horizontally (row indices are equal [0] == [2]) */
        if (coordinates[0] == coordinates[2]) {

            if (ship.getSize() !=
                    (Math.max(coordinates[1], coordinates[3]) + 1) // inclusive cell
                    - Math.min(coordinates[1], coordinates[3])) {
                throw new WrongLengthShip(ship.getName());
            }

            // place ship
            validateShipPlacement(coordinates, true);

            System.out.println("Same row"); // todo remove

        /* Check we're placing a ship vertically (column indices are equal [1] == [3]) */
        } else if (coordinates[1] == coordinates[3]) {

            if (ship.getSize() !=
                    (Math.max(coordinates[0], coordinates[2]) + 1) // inclusive cell
                    - Math.min(coordinates[0], coordinates[2])) {
                throw new WrongLengthShip(ship.getName());
            }

            // place ship
            validateShipPlacement(coordinates, false);

            System.out.println("Same column"); // todo remove
        } else { // illegal coordinates
            throw new IllegalShipLocation();
        }

    }

    private void validateShipPlacement(int[] coordinates, boolean isHorizontallyOriented)
            throws ErrorShipOverlapException {
        if (isHorizontallyOriented) {
            checkCellsUnoccupied(coordinates, true);
            checkAdjacentCellsUnoccupied(coordinates, true);
            adjustCellsForNewShip(coordinates, true);
        } else {
            checkCellsUnoccupied(coordinates, false);
            checkAdjacentCellsUnoccupied(coordinates, false);
            adjustCellsForNewShip(coordinates, false);
        }

    }

    /**
     * Checks whether a cell is occupied with another ship
     * @param coordinates the coordinates, [0] is the start row, [1] is the start column,
     *                    [2] is the end row, [3] is the end column.
     * @throws ErrorShipOverlapException a ship is blocking our placement */
    private void checkCellsUnoccupied(int[] coordinates, boolean isHorizontal) {
        if (isHorizontal) {
            for (int i = coordinates[1]; i < coordinates[3]; i++) { // iterate over the columns
                if (field[coordinates[0]][i].equals(SHIP.getSymbol())) {
                    throw new ErrorShipOverlapException(); // a ship already occupies this cell
                }
            }
        } else {
            for (int i = coordinates[0]; i < coordinates[2]; i++) { // iterate over the columns
                if (field[i][coordinates[1]].equals(SHIP.getSymbol())) {
                    throw new ErrorShipOverlapException(); // a ship already occupies this cell
                }
            }
        }
    }

    private void checkAdjacentCellsUnoccupied(int[] coordinates, boolean isHorizontal) {
        if (isHorizontal) {
            for (int i = coordinates[0] - 1; i < coordinates[2] + 1; i++) { // start at top left, finish bottom right
                for (int j = coordinates[1] - 1; j < coordinates[3] + 1; j++) { // check each column, skip ship cells
                    if (field[i][j].equals(SHIP.getSymbol())) {
                        throw new ErrorShipOverlapException();
                    }
                }
            }
        }
    }

    private void adjustCellsForNewShip(int[] coordinates, boolean isHorizontal) {
        String shipSymbol = SHIP.getSymbol();
        if (isHorizontal) {
            for (int i = coordinates[1]; i <= coordinates[3]; i++) {
                field[coordinates[0]][i] = shipSymbol; // field[row][column]
            }
        } else {
            for (int i = coordinates[0]; i <= coordinates[2]; i++) {
                field[i][coordinates[1]] = shipSymbol; // field[row][column]
            }
        }
    }

    @Override
    public String toString() {
        return Arrays.deepToString(field)
                .replace("], ", "\n")
                .replace("[", "")
                .replace("]", "") // fixme why is this extra bracket needed?
                .replace(",", "")
                .concat("\n");
    }
}
