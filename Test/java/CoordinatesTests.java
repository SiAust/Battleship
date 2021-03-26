import Enums.Ship;
import Exceptions.IllegalShipLocation;
import Exceptions.WrongLengthShip;
import Model.Coordinates;
import Model.Field;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class CoordinatesTests {

    @Test
    void constructorTests() {
        Coordinates coordinates = new Coordinates(new String[]{"A1","B2"});
        Assertions.assertArrayEquals(new int[]{1,1,2,2}, coordinates.getCoordinates());
    }


}
