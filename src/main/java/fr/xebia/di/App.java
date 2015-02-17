package fr.xebia.di;

import java.util.Optional;

import static java.util.Optional.empty;

public class App {
    private GoogleGeocoding googleGeocoding;
    private GeoDistanceEngine distanceEngine;

    public App() {
        String apiKey = Optional.ofNullable(System.getProperty("google.apiKey"))
                .orElseThrow(() -> new RuntimeException("please provide system property google.apiKey"));
        
        this.googleGeocoding = new GoogleGeocoding(apiKey);
        this.distanceEngine = new GeoDistanceEngine();
    }

    public Optional<Double> getDistance(String firstAddress, String secondAddress) {
        Optional<Coordinate> firstCoordinate = googleGeocoding.geocode(firstAddress);
        if (!firstCoordinate.isPresent()) {
            return empty();
        }
        
        Optional<Coordinate> secondCoordinate = googleGeocoding.geocode(secondAddress);
        if (!secondCoordinate.isPresent()) {
            return empty();
        }
        
        return Optional.of(distanceEngine.evaluate(
                firstCoordinate.get(),
                secondCoordinate.get()));
    }
}
