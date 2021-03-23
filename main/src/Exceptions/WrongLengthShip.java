package Exceptions;

public class WrongLengthShip extends RuntimeException {

    public WrongLengthShip(String ship) {
        super(String.format("Error! Wrong length of the %s! Try again:\n", ship));
    }
}
