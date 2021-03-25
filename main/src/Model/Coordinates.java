package Model;

import java.util.Arrays;

public class Coordinates {

    private final int[] coordinates;

    public Coordinates(String[] coords) {
        this.coordinates = convertStringArrToIntArr(coords);
    }

    private int[] convertStringArrToIntArr(String[] coords) {
        int[] intCoords = new int[4];
        for (int i = 0, j = 0; i < coords.length; i++, j += 2) {
            intCoords[j] = convertCharToIntCoord(coords[i].charAt(0));
            intCoords[j + 1] = convertYCoordToInt(coords[i].substring(1));
        }
        checkCoordOrder(intCoords);
        return intCoords;
    }

    private int convertCharToIntCoord(char c) {
        char converted = (char) (c - 16); /* subtracts to convert letter to respective number, A = 1, J = 10 */
        return converted == ':' ? 10 : Integer.parseInt(String.valueOf(converted));
    }

    private int convertYCoordToInt(String y) {
        return Integer.parseInt(y);
    }

    /** This method reverses the coordinates if they are in the wrong order.
     * The wrong order here is J10 J9 for example. This is solely because our
     * method for adding a ship using a loop that increments through the arrays,
     * and it would see this as a negative length and throw WrongShipLength exception. */
    private void checkCoordOrder(int[] intCoords) {
        int firstPointSum = intCoords[0] + intCoords[1];
        int secondPointSum = intCoords[2] + intCoords[3];

        if (firstPointSum > secondPointSum) {
            int[] temp = Arrays.copyOf(intCoords, intCoords.length);
            for (int i = 0, j = intCoords.length - 2; i < intCoords.length; i += 2, j -= 2) {
                intCoords[i] = temp[j];
                intCoords[i + 1] = temp[j + 1];
            }
        }
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    // getStartRow
    // getEndRow
    // getStartCol
    // getEndCol
}
