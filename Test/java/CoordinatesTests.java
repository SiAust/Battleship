import Model.Coordinates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CoordinatesTests {

    @Test
    void constructorTests() {
        Coordinates coordinates = new Coordinates(new String[]{"A1","B2"});
        Assertions.assertArrayEquals(new int[]{1,1,2,2}, coordinates.getCoordinates());
    }


}
