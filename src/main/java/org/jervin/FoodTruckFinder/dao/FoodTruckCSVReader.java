package org.jervin.FoodTruckFinder.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.jervin.FoodTruckFinder.model.FoodTruck;
import org.jervin.FoodTruckFinder.model.FoodTruckHeaderKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodTruckCSVReader implements IFoodTrucksDAO {
    public static final String SFGOV_URL = "https://data.sfgov.org/api/views/rqzj-sfat/rows.csv";
    private static final Logger logger = LoggerFactory.getLogger(FoodTruckCSVReader.class);


    protected List<FoodTruck> getTrucksFromURL(final URL url) throws IOException {
        List<FoodTruck> trucks = new ArrayList<>();
        final InputStream in = url.openStream();
        try {
            Iterable<CSVRecord> iterable = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(new InputStreamReader(in));
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
        } finally {
            in.close();
        }
    }

    protected List<FoodTruck> getTrucksFromSF() throws IOException {
        URL url = new URL(SFGOV_URL);
        return getTrucksFromURL(url);
    }

    protected List<FoodTruck> getTrucksFromEmbeddedCSV() {
        URL url = FoodTruckCSVReader.class.getClassLoader().getResource("Mobile_Food_Facility_Permit.csv");
        try {
            return getTrucksFromURL(url);
        } catch (IOException e) {
            logger.error("Cannot access embedded Mobile_Food_Facility_Permit.csv", e);
            throw new RuntimeException(e);  // We really should not see this broken.
        }
    }

    private volatile List<FoodTruck> cached = null;

    @Override
    public boolean checkHealth() {
        try {
            getTrucksFromURL(new URL(SFGOV_URL));
            return true;
        } catch (IOException ioe) {
            return false;
        }
    }

    public List<FoodTruck> getTrucks() {
        if(cached != null && !cached.isEmpty())
            return new ArrayList<>(cached);
        try {
            cached = getTrucksFromSF();
            return new ArrayList<>(cached);
        } catch(IOException ioe) {
            logger.error("Could not access the SF Gov web site to get the latest CSV", ioe);
        }
        // Not caching this value, since it is the fallback embedded CSV.
        return getTrucksFromEmbeddedCSV();
    }

}
