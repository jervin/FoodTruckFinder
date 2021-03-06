package org.jervin.FoodTruckFinder.model;

public enum FoodTruckHeaderKeys {
    LOCATION_ID("locationid"),
    APPLICANT("Applicant"),
    LOCATION_DESCRIPTION("LocationDescription"),
    ADDRESS("Address"),
    LATITUDE("Latitude"),
    LONGITUDE("Longitude"),
    FOODITEMS("FoodItems"),
    HOURS_OF_OPERATION("dayshours");


    private final String description;

    FoodTruckHeaderKeys(String description) {
        this.description = description;
    }
    public String toString() { return description; }
}
