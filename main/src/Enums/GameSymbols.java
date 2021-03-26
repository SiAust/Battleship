package Enums;

public enum GameSymbols {

    FOG_OF_WAR("~"),
    SHIP("O"),
    HIT("X"),
    MISS("M");

    private final String symbol;

    GameSymbols(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
