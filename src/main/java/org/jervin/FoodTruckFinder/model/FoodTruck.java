package org.jervin.FoodTruckFinder.model;

import java.util.Objects;

public class FoodTruck {
    private final long locationID;
    private final double latitude;
    private final double longitude;
    private final String applicant;
    private final String locationDescription;
    private final String address;
    private final String foodItems;
    private final String hoursOfOperation;

    public FoodTruck(long locationID,
                     double latitude,
                     double longitude,
                     String applicant,
                     String locationDescription,
                     String address,
                     String foodItems,
                     String hoursOfOperation) {
        this.locationID = locationID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.applicant = applicant;
        this.locationDescription = locationDescription;
        this.address = address;
        this.foodItems = foodItems;
        this.hoursOfOperation = hoursOfOperation;
    }

    public long getLocationID() { return locationID; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public String getApplicant() { return applicant; }
    public String getLocationDescription() { return locationDescription; }
    public String getAddress() { return address; }
    public String getFoodItems() { return foodItems; }
    public String getHoursOfOperation() { return hoursOfOperation; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodTruck)) return false;
        FoodTruck foodTruck = (FoodTruck) o;
        return locationID == foodTruck.locationID &&
                latitude == foodTruck.latitude &&
                longitude == foodTruck.longitude &&
                applicant.equals(foodTruck.applicant) &&
                locationDescription.equals(foodTruck.locationDescription) &&
                address.equals(foodTruck.address) &&
                foodItems.equals(foodTruck.foodItems);
    }
    @Override
    public int hashCode() {
        return Objects.hash(locationID, latitude, longitude, applicant, locationDescription, address, foodItems);
    }
    @Override
    public String toString() {
        return "FoodTruck{" +
                "locationID=" + locationID +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", applicant='" + applicant + '\'' +
                ", locationDescription='" + locationDescription + '\'' +
                ", address='" + address + '\'' +
                ", foodItems='" + foodItems + '\'' +
                ", hoursOfOperation='" + hoursOfOperation + '\'' +
                '}';
    }
}
