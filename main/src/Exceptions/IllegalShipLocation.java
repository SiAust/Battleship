package Exceptions;

public class IllegalShipLocation extends RuntimeException {
    public IllegalShipLocation() {
        super("Error! Wrong ship location! Try again:\n");
    }
}
