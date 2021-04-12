package Exceptions;

public class WrongLengthShip extends BattleshipException {

    public WrongLengthShip(String ship) {
        super(String.format("Error! Wrong length of the %s! Try again:\n", ship));
    }
}
