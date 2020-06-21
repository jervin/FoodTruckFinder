package org.jervin.FoodTruckFinder;
import org.jervin.FoodTruckFinder.controller.DistanceCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * TODO: Note need more tests, walking distance calculations.
 */
public class DistanceCalculatorTest {
    @Test
    public void testCalculation() throws Exception {
        // Roughly the distance from Big Ben in London to the Statue of Liberty in New York... in miles.
        Assertions.assertEquals(8971.7737,
                DistanceCalculator.distance(51.5007,  0.1246, 40.6892, 74.0445), 0.1);
    }
    @Test
    public void testCompareTwoLatLongs() throws Exception {
        double latitude = 37.78d;
        double longitude = -122.43d;

        double latitude1 = 37.7815967457602d;
        double longitude1 =  -122.43485778844d;
        double distance1 = DistanceCalculator.distance(latitude, longitude, latitude1, longitude1);

        double latitude2 = 37.7808684365681;
        double longitude2 =  -122.425467726618;
        double distance2 = DistanceCalculator.distance(latitude, longitude, latitude2, longitude2);

        Assertions.assertTrue(distance2 < distance1);

        double latitude3 = 37.7412902847707;
        double longitude3 =  -122.494377920435;
        double distance3 = DistanceCalculator.distance(latitude, longitude, latitude3, longitude3);

        Assertions.assertTrue(distance2 < distance3);
    }
}
