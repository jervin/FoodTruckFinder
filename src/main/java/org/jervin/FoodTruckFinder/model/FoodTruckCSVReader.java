package org.jervin.FoodTruckFinder.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class FoodTruckCSVReader {

    protected List<FoodTruck> getTrucksFromURL(final URL url) throws IOException {
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

    protected List<FoodTruck> getTrucksFromSF() throws IOException {
        URL url = new URL("https://data.sfgov.org/api/views/rqzj-sfat/rows.csv");
        return getTrucksFromURL(url);
    }

    protected List<FoodTruck> getTrucksFromEmbeddedCSV() {
        URL url = FoodTruckCSVReader.class.getClassLoader().getResource("Mobile_Food_Facility_Permit.csv");
        try {
            return getTrucksFromURL(url);
        } catch (IOException e) {
            // TODO: Log this
            throw new RuntimeException(e);  // We really should not see this broken.
        }
    }

    private volatile List<FoodTruck> cached = null;

    public List<FoodTruck> getTrucks() {
        if(cached != null && !cached.isEmpty())
            return new ArrayList<>(cached);
        try {
            cached = getTrucksFromSF();
            return new ArrayList<>(cached);
        } catch(IOException ioe) {
            // TODO: Log this
            System.out.println("Error: " + ioe);
            ioe.printStackTrace();
        }
        // Not caching this value, since it is the fallback embedded CSV.
        return getTrucksFromEmbeddedCSV();
    }

}
