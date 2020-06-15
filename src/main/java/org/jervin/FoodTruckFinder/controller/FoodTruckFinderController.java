package org.jervin.FoodTruckFinder.controller;

import org.jervin.FoodTruckFinder.model.CSVReader;
import org.jervin.FoodTruckFinder.model.FoodTruck;
import org.jervin.FoodTruckFinder.model.FoodTruckFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FoodTruckFinderController {
    private static final Logger logger = LoggerFactory.getLogger(FoodTruckFinderController.class);

    @GetMapping("/foodtruckfinder")
    public List<FoodTruck> findTrucks(@RequestParam(name="lat", required=true) double latitude,
                                      @RequestParam(name="long", required=true) double longitude,
                                      @RequestParam(name="foodType", defaultValue = "") String foodItems,
                                      @RequestParam(name="mi", defaultValue = "1.0") double distance) throws IOException {
        logger.info("latitude: " + latitude + ", longitude:" + longitude + ", foodType: " + foodItems + ", distance: " + distance);
        return FoodTruckFinder.findTrucks(latitude, longitude, foodItems, distance);
    }
}
