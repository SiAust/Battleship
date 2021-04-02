package Model.Ships;

import Model.Coordinates;
import Model.Point;

public interface Ship {

    Coordinates getCoordinates();
    int getLength();
    String getName();
    void takeHit(Point point);
    boolean isSunk();


}
