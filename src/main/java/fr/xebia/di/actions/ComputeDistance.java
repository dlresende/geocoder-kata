package fr.xebia.di.actions;

import fr.xebia.di.domain.Coordinate;
import fr.xebia.di.domain.GeoDistanceEngine;
import fr.xebia.di.domain.Geocoder;

import java.util.Optional;

import static java.util.Optional.empty;

public class ComputeDistance {
    private final Geocoder geocoder;
    private final GeoDistanceEngine distanceEngine;

    public ComputeDistance(Geocoder geocoder, GeoDistanceEngine distanceEngine) {
        this.geocoder = geocoder;
        this.distanceEngine = distanceEngine;
    }

    public Optional<Double> between(String firstAddress, String secondAddress) {
        Optional<Coordinate> firstCoordinate = geocoder.geocode(firstAddress);
        if (!firstCoordinate.isPresent()) {
            return empty();
        }
        
        Optional<Coordinate> secondCoordinate = geocoder.geocode(secondAddress);
        if (!secondCoordinate.isPresent()) {
            return empty();
        }
        
        return Optional.of(distanceEngine.evaluate(
                firstCoordinate.get(),
                secondCoordinate.get()));
    }
}
