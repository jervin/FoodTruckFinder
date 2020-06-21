package org.jervin.FoodTruckFinder.model;

import org.jervin.FoodTruckFinder.model.FoodTruck;
import org.jervin.FoodTruckFinder.model.FoodTruckFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FoodTruckFinderTest {

    private IFoodTruckFinder finder = new FoodTruckFinder();

    private List<FoodTruck> genList() throws Exception {
        List<FoodTruck> list = new ArrayList<>();
        list.add(new FoodTruck(1336165, 37.7818671175741, -122.4900712081,
                "Anas Goodies Catering",
                "122.429885247603",
                "1346 EDDY ST", "FOOD Stuff", ""));
        list.add(new FoodTruck(1163785, 37.7815967457602d, -122.43485778844,
                "SOHOMEI, LLC",
                "EDDY ST: STEINER ST to PIERCE ST (1600 - 1699)",
                "1652 EDDY ST", "FOOD Stuff", ""));
        list.add(new FoodTruck(931093, 37.7412902847707, -122.494377920435,
                "Swell Cream & Coffee",
                "Doesn't matter",
                "1652 Null Drive", "FOOD Stuff", ""));
        return list;
    }

    @Test
    public void testFilterAndSort() throws Exception {
        List<FoodTruck> input = genList();
        List<FoodTruck> trucks = ((FoodTruckFinder)finder).filterAndSort(37.78, -122.43, "", 10.0d, input);
        Assertions.assertEquals(1163785l, trucks.get(0).getLocationID());
        Assertions.assertEquals(1336165l, trucks.get(1).getLocationID());
    }
}
