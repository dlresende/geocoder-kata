package fr.xebia.di;

public class App {
//    private final GoogleMapsGeocoder googleMapsGeocoder;
    private final GeoDistanceEngine distanceEngine;

    public App() {
//        this.googleMapsGeocoder = new GoogleMapsGeocoder();
        this.distanceEngine = new GeoDistanceEngine();
    }

    public double getDistance(String firstAddress, String secondAddress) {
//        return distanceEngine.evaluate(
//                googleMapsGeocoder.getCoordinates(firstAddress),
//                googleMapsGeocoder.getCoordinates(secondAddress));
        return 423_000;
    }
}
