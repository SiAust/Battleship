package Exceptions;

public class ErrorShipOverlapException extends BattleshipException {

    public ErrorShipOverlapException() {
        super("Error! You placed it too close to another one. Try again:\n");
    }
}
