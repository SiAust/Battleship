package Exceptions;

public class IllegalCoordinates extends RuntimeException {
    public IllegalCoordinates() {
        super("Error! You entered the wrong coordinates! Try again:");
    }
}
