package org.jervin.FoodTruckFinder.model;

import org.jervin.FoodTruckFinder.controller.DistanceCalculator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FoodTruckFinder {
    protected static List<FoodTruck> filterAndSort(double latitude,
                                                   double longitude,
                                                   String foodItems,
                                                   double distance,
                                                   List<FoodTruck> trucks) {
        final Comparator<FoodTruck> comparator = (t0, t1) -> {
            double distance0 = DistanceCalculator.distance(longitude, latitude, t0.getLongitude(), t0.getLongitude());
            double distance1 = DistanceCalculator.distance(longitude, latitude, t1.getLongitude(), t1.getLongitude());
            double diff = distance0 - distance1;
            if (diff > 0.0)
                return 1;
            if (diff == 0.0)
                return 0;
            return -1;
        };

        if (distance > 0.0d) {
            // We have specified a distance, so we are going to filter based on it.
            return trucks.stream()
                    .filter(truck -> DistanceCalculator.distance(latitude, longitude, truck.getLatitude(), truck.getLongitude()) <= distance)
                    .filter(truck -> truck.getFoodItems().toLowerCase().contains(foodItems.toLowerCase()))
                    .sorted(comparator)
                    .collect(Collectors.toList());
        }

        return trucks.stream()
                .filter(truck -> DistanceCalculator.distance(latitude, longitude, truck.getLatitude(), truck.getLongitude()) <= 100.0d)
                .filter(truck -> truck.getFoodItems().toLowerCase().contains(foodItems.toLowerCase()))
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    public static List<FoodTruck> findTrucks(double latitude,
                                             double longitude,
                                             String foodItems,
                                             double distance) {
        List<FoodTruck> trucks = new FoodTruckCSVReader().getTrucks();
        return filterAndSort(latitude, longitude, foodItems, distance, trucks);
    }
}
