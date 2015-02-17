package fr.xebia.di.infrastructure;

import fr.xebia.di.actions.ComputeDistance;
import fr.xebia.di.domain.GeoDistanceEngine;
import fr.xebia.di.domain.Geocoder;

import java.util.Optional;

public class App {
    public static void main(String[] args) {
        validateInput(args);

        ComputeDistance computeDistance = getComputeDistance();
        Optional<Double> distance = computeDistance.between(args[0], args[1]);
        System.out.println(distance);
    }

    private static void validateInput(String[] args) {
        if(args.length != 2) {
            System.err.println("Please provide first and second address. Example: command <first> <second>");
            System.exit(1);
        }
    }

    private static ComputeDistance getComputeDistance() {
        Geocoder geocoder = new OpenStreetMapGeocoding();
        GeoDistanceEngine distanceEngine = new GeoDistanceEngine();
        return new ComputeDistance(geocoder, distanceEngine);
    }
}
