package org.jervin.FoodTruckFinder;
import org.jervin.FoodTruckFinder.controller.DistanceCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * TODO: 0
 * 66Note need more tests, walking distance calculations.
 */
public class DistanceCalculatorTest {
    @Test
    public void testCalculation() throws Exception {
        // Roughly the distance from Big Ben in London to the Statue of Liberty in New York... in miles.
        Assertions.assertEquals(8971.7737,
                DistanceCalculator.distance(51.5007,  0.1246, 40.6892, 74.0445), 0.1);
    }
}
