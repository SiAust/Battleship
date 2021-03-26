package Model;

import Exceptions.IllegalCoordinates;

public class Point {

    int[] point;

    public Point(String point) {
        this.point = convertToIntArr(point);
    }

    private int[] convertToIntArr(String point) {
        this.point = new int[2];
        // todo check the point is valid else throw exception
        if (point.length() > 3) {
            throw new IllegalCoordinates();
        }
        this.point[0] = convertXToInt(point.charAt(0));
        this.point[1] = convertYToInt(point.substring(1));
        return this.point;
    }

    private int convertXToInt(char x) {
        char converted = (char) (x - 16); /* subtracts to convert letter to respective number, A = 1, J = 10 */
        if ((int) converted < 49 || (int) converted > 58) { // ASCII 49 - 58 characters 1 - 9 and : for 10
            throw new IllegalCoordinates();
        }
        return converted == ':' ? 10 : Integer.parseInt(String.valueOf(converted));
    }

    private int convertYToInt(String y) {
        int intY = Integer.parseInt(String.valueOf(y));
        if (intY > 10) {
            throw new IllegalCoordinates();
        }
        return intY;
    }

    public int[] getPoint() {
        return point;
    }

    public int getX() {
        return point[0];
    }

    public int getY() {
        return point[1];
    }
}
