package org.jervin.FoodTruckFinder.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public List<FoodTruck> getTrucks() throws IOException {
        URL url = CSVReader.class.getClassLoader().getResource("Mobile_Food_Facility_Permit.csv");
        List<FoodTruck> trucks = new ArrayList<>();
        Iterable<CSVRecord> iterable = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(new InputStreamReader(url.openStream()));
        for (CSVRecord record : iterable) {
            FoodTruck foodTruck = new FoodTruck(Long.parseLong(record.get(FoodTruckHeaderKeys.LOCATION_ID)),
                    Double.parseDouble(record.get(FoodTruckHeaderKeys.LATITUDE)),
                    Double.parseDouble(record.get(FoodTruckHeaderKeys.LONGITUDE)),
                    record.get(FoodTruckHeaderKeys.APPLICANT),
                    record.get(FoodTruckHeaderKeys.LOCATION_DESCRIPTION),
                    record.get(FoodTruckHeaderKeys.ADDRESS),
                    record.get(FoodTruckHeaderKeys.FOODITEMS),
                    record.get(FoodTruckHeaderKeys.HOURS_OF_OPERATION));
            trucks.add(foodTruck);
        }
        return trucks;
    }

}
