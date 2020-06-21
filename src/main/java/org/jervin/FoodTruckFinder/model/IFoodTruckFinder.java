package org.jervin.FoodTruckFinder.model;
import java.util.List;

public interface IFoodTruckFinder {
     List<FoodTruck> findTrucks(double latitude, double longitude, String foodItems, double distance);
}
