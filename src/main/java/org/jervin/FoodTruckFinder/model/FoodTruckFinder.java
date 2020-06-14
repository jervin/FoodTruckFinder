package org.jervin.FoodTruckFinder.model;

import org.jervin.FoodTruckFinder.controller.DistanceCalculator;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FoodTruckFinder {
    public static List<FoodTruck> findTrucks(double latitude,
                                             double longitude,
                                             String foodItems,
                                             double distance) throws IOException {
        List<FoodTruck> trucks = new CSVReader().getTrucks();
        return trucks.stream()
                .filter(truck -> DistanceCalculator.distance(latitude, longitude, truck.getLatitude(), truck.getLongitude()) <= distance )
                .filter(truck -> truck.getFoodItems().toLowerCase().contains(foodItems.toLowerCase()))
                .collect(Collectors.toList());
    }
}
