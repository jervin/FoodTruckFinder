package org.jervin.FoodTruckFinder;

import org.jervin.FoodTruckFinder.controller.FoodTruckFinderController;
import org.jervin.FoodTruckFinder.model.FoodTruck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
class FoodTruckFinderApplicationTests {
	@Autowired
	FoodTruckFinderController controller;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(controller);
	}

	@Test
	void smokeTest() throws IOException {
		List<FoodTruck> trucks = controller.findTrucks(37.78, -122.43, "", 1.0d);
		Assertions.assertNotNull(trucks);
		Assertions.assertFalse(trucks.isEmpty());
	}
}
