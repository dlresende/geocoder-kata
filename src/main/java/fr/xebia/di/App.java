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

    public static void main(String[] args) {
        if(args.length != 2) {
            System.err.println("Please provide first and second address. Example: command <first> <second>");
            System.exit(1);
        }
        Optional<Double> distance = new App().getDistance(args[0], args[1]);
        System.out.println(distance);
    }
}
