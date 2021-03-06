package Model;

import Enums.ShipEnum;
import Enums.GameSymbols;
import Exceptions.ErrorShipOverlapException;
import Exceptions.IllegalShipLocation;
import Exceptions.WrongLengthShip;
import Model.Ships.*;

import java.util.*;


public class Field {

    String[][] field = new String[11][11];

//    List<Ship> shipsOnField = new ArrayList<>();
    /** Stores the ships on the field and whether they are sunk */
    Map<Ship, Boolean> shipsOnField = new HashMap<>();

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
                    field[i][j] = GameSymbols.FOG_OF_WAR.getSymbol();
                }
            }
        }
    }

    /** throws WrongSizeShip, IllegalCoordinates */
    public void addShip(ShipEnum shipEnum, Coordinates coord) {
        int[] coordinates = coord.getCoordinates();
        /* Check we're placing a ship horizontally (row indices are equal [0] == [2]) */
        if (coordinates[0] == coordinates[2]) {

            if (shipEnum.getSize() !=
                    (Math.max(coordinates[1], coordinates[3]) + 1) // inclusive cell
                            - Math.min(coordinates[1], coordinates[3])) {
                throw new WrongLengthShip(shipEnum.getName());
            }

            // place ship
            validateShipPlacement(coordinates, true);

            /* Check we're placing a ship vertically (column indices are equal [1] == [3]) */
        } else if (coordinates[1] == coordinates[3]) {

            if (shipEnum.getSize() !=
                    (Math.max(coordinates[0], coordinates[2]) + 1) // inclusive cell
                            - Math.min(coordinates[0], coordinates[2])) {
                throw new WrongLengthShip(shipEnum.getName());
            }

            // place ship
            validateShipPlacement(coordinates, false);

        } else { // illegal coordinates
            throw new IllegalShipLocation();
        }

        /* We identify the ship and add it to the list of ships on the field.
         * We keep the list so when a shot is fired, it can be recorded against the right ship,
         * through checking the point matches the ships coordinates. The ship takes a hit, and
         * will check if it is sunk */
        switch (shipEnum) {
            case AIRCRAFT_CARRIER:
                shipsOnField.put(new AircraftCarrier(coord), false);
                break;
            case BATTLESHIP:
                shipsOnField.put(new Battleship(coord), false);
                break;
            case SUBMARINE:
                shipsOnField.put(new Submarine(coord), false);
                break;
            case CRUISER:
                shipsOnField.put(new Cruiser(coord), false);
                break;
            case DESTROYER:
                shipsOnField.put(new Destroyer(coord), false);
                break;
        }
    }


    private void validateShipPlacement(int[] coordinates, boolean isHorizontallyOriented)
            throws ErrorShipOverlapException {
        if (isHorizontallyOriented) {
            checkCellsUnoccupied(coordinates, true);
            checkAdjacentCellsUnoccupied(coordinates);
            adjustCellsForNewShip(coordinates, true);
        } else {
            checkCellsUnoccupied(coordinates, false);
            checkAdjacentCellsUnoccupied(coordinates);
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
                if (field[coordinates[0]][i].equals(GameSymbols.SHIP.getSymbol())) {
                    throw new ErrorShipOverlapException(); // a ship already occupies this cell
                }
            }
        } else {
            for (int i = coordinates[0]; i < coordinates[2]; i++) { // iterate over the columns
                if (field[i][coordinates[1]].equals(GameSymbols.SHIP.getSymbol())) {
                    throw new ErrorShipOverlapException(); // a ship already occupies this cell
                }
            }
        }
    }

    /** By the rules of the game all ships should not sit adjacent to any other ship.
     * There should be at least one empty cell between one ship and another.
     * @param coordinates length == 4 , [rowStart][colStart][rowEnd][colEnd]
     * @throws IllegalShipLocation if the ship is too close to another */
    private void checkAdjacentCellsUnoccupied(int[] coordinates) {

        // prevent IndexOutOfBounds exception
        int rowBoundary = Math.min(coordinates[2] + 2, field.length);
        int colBoundary = Math.min(coordinates[3] + 2, field[0].length);

        // Checks all cells adjacent to the proposed position of the ship
        for (int i = coordinates[0] - 1; i < rowBoundary; i++) {
            for (int j = coordinates[1] - 1; j < colBoundary; j++) { // check each column
                // skip positions where we will legally place the ship
                if (i >= coordinates[0] && i <= coordinates[2]
                        &&
                        j >= coordinates[1] && j <= coordinates[3]) {
                    continue;
                }
                if (field[i][j].equals(GameSymbols.SHIP.getSymbol())) {
                    throw new IllegalShipLocation();
                }
            }
        }
    }

    private void adjustCellsForNewShip(int[] coordinates, boolean isHorizontal) {
        String shipSymbol = GameSymbols.SHIP.getSymbol();
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

    public String fireShot(Point point) {
        String cell = field[point.getX()][point.getY()];
        // fixme hitting an already 'hit' cell returns a hit.
        if (GameSymbols.SHIP.getSymbol().equals(cell) || GameSymbols.HIT.getSymbol().equals(cell)) {
            field[point.getX()][point.getY()] = GameSymbols.HIT.getSymbol();
            return isShipSunk(point) ?
                    isAllShipsSunk() ? "You sank the last ship. You won. Congratulations!"
                            : "You sank a ship!\n"
                    : "You hit a ship!\n";
        } else {
            field[point.getX()][point.getY()] = GameSymbols.MISS.getSymbol();
            return "You missed!\n";
        }
    }

    /** Checks the ships on the field have been sunk.
     * Iterates through shipsOnField and will change the Map boolean to true
     * if the ship has taken enough hits.
     * @param point This is a location of the current hit point against the ship.
     * @return whether the ship is sunk, i.e. all cells are now hit. */
    private boolean isShipSunk(Point point) {
        for (Ship ship : shipsOnField.keySet()) {
            if (ship.getCoordinates().isPointWithin(point)) {
                ship.takeHit(point);
                if (ship.isSunk()) {
                    shipsOnField.put(ship, true);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isAllShipsSunk() {
        return shipsOnField.keySet().stream().allMatch(Ship::isSunk);
    }

    /** Simulates a Battleship game field with fog of war by replacing any GameSymbol.SHIP markers
     * with an empty cell marker, GameSymbol.FOG_OF_WAR marker. GameSymbol.HIT and GameSymbol.MISS
     * will remain visible.
     * @see GameSymbols
     */
    public String fieldWithFogOfWar() {
        return this.toString().replace(GameSymbols.SHIP.getSymbol(), GameSymbols.FOG_OF_WAR.getSymbol());
    }

    /** Prints both the player fields to console. The opponents field will be shown
     * above this instance, with the current players attempted hits, and misses.
     * The opponents ships are hidden. */
    public String printFields(Field opponentField) {
        String topField = opponentField.fieldWithFogOfWar();
        String bottomField = this.toString();
        return topField + "---------------------\n" + bottomField;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(field) // todo replace with regex?
                .replace("], ", "\n")
                .replace("[", "")
                .replace("]", "")
                .replace(",", "")
                .concat("\n");
    }
}
