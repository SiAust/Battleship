package Exceptions;

public class ErrorShipOverlapException extends RuntimeException{

    public ErrorShipOverlapException() {
        super("Error! You placed it too close to another one. Try again:\n");
    }
}
