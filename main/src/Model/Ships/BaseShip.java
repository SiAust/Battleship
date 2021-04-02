package Model.Ships;

import Model.Coordinates;
import Model.Point;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseShip implements Ship {

    Coordinates coordinates;
    String name;
    int length;
    Set<Point> hitsOnShip = new HashSet<>();

    BaseShip(Coordinates coordinates, String name, int length) {
        this.coordinates = coordinates;
        this.name = name;
        this.length = length;
    }

    @Override
    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void takeHit(Point point) {
        hitsOnShip.add(point);
    }

    @Override
    public boolean isSunk() {
        return hitsOnShip.size() == length;
    }
}
