package org.jervin.FoodTruckFinder.controller;

import org.jervin.FoodTruckFinder.model.CSVReader;
import org.jervin.FoodTruckFinder.model.FoodTruck;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class FoodTruckFinderController {

    @GetMapping("/foodtruckfinder")
    public List<FoodTruck> findTrucks(@RequestParam(name="lat", required=true) int latitude,
                                @RequestParam(name="long", required=true) int longitude) throws IOException {
        List<FoodTruck> trucks = new CSVReader().readInData();
        return trucks;
    }
}
