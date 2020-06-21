package org.jervin.FoodTruckFinder.dao;
import org.jervin.FoodTruckFinder.model.FoodTruck;

import java.util.List;

public interface IFoodTrucksDAO {
    boolean checkHealth();
    List<FoodTruck> getTrucks();
}
