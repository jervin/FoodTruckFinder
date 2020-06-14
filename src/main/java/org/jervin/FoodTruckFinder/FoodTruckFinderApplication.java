package org.jervin.FoodTruckFinder;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.jervin.FoodTruckFinder.model.FoodTruck;
import org.jervin.FoodTruckFinder.model.FoodTruckFinder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class FoodTruckFinderApplication {

	public static Options commandLineOptions() {
		Options options = new Options();
		options.addOption("lat", true,"Latitude" );
		options.addOption("lon", true,"Longitude" );
		options.addOption("foodType", true, "Food type");
		options.addOption("mi", true, "Radius of search in miles");
		return options;
	}

	public static void main(String[] args) throws Exception {
		CommandLineParser parser = new DefaultParser();
		CommandLine commandLine = parser.parse(commandLineOptions(), args);

		if (commandLine.hasOption("lat") && commandLine.hasOption("lon")) {
			double latitude = Double.parseDouble(commandLine.getOptionValue("lat"));
			double longitude = Double.parseDouble(commandLine.getOptionValue("lon"));
			String foodItems = commandLine.hasOption("foodType") ? commandLine.getOptionValue("foodType") : "";
			double radius = commandLine.hasOption("mi") ? Double.parseDouble(commandLine.getOptionValue("mi")) : 1.0d;
			List<FoodTruck> trucks = FoodTruckFinder.findTrucks(latitude, longitude, foodItems, radius);
			final ObjectMapper mapper = new ObjectMapper();
			System.out.println(mapper.writeValueAsString(trucks));
			System.exit(0);
		}

		SpringApplication.run(FoodTruckFinderApplication.class, args);
	}

}
