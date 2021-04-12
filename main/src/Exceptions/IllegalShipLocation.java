package Exceptions;

public class IllegalShipLocation extends BattleshipException {
    public IllegalShipLocation() {
        super("Error! Wrong ship location! Try again:\n");
    }
}
