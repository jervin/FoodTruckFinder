package org.jervin.FoodTruckFinder.controller;

public class DistanceCalculator {
    /**
     * This method calculates the distance between two points using the Haversine formula,
     *  as specified by the two lats and longs, in miles.  Haversine assumes that the Earth is
     *  a perfect sphere, but given we are talking about walking distances to find Food Trucks, the difference
     *  between that and a more accurate Spheroid will not be significant.
     *
     *  Algorithm acquired from: https://www.geeksforgeeks.org/haversine-formula-to-find-distance-between-two-points-on-a-sphere/
     *
     * @param lat1
     * @param lon1
     * @param lat2
     * @param lon2
     * @return
     */
    public static double distance(double lat1, double lon1,
                                  double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        // convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        // Converting from kilometers to miles
        return (rad * c)/0.621371;
    }
}
