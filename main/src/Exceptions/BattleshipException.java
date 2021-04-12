package Exceptions;

public class BattleshipException extends RuntimeException {

    BattleshipException(String error) {
        super(error);
    }
}
