package Exceptions;

public class IllegalCoordinates extends BattleshipException {
    public IllegalCoordinates() {
        super("Error! You entered the wrong coordinates! Try again:");
    }
}
