package fr.xebia.di;

import fr.xebia.di.address.AddressNormalizer;
import fr.xebia.di.address.StandardAddressParser;
import fr.xebia.di.geolocalization.GeoDistanceEngine;
import fr.xebia.di.geolocalization.Geolocalizer;

import java.util.Locale;

public class App {
    private final AddressNormalizer normalizer;
    private final StandardAddressParser parser;
    private final Geolocalizer geolocalizer;
    private final GeoDistanceEngine distanceEngine;

    public App() {
        this.normalizer = new AddressNormalizer(Locale.FRANCE);
        this.parser = new StandardAddressParser(Locale.FRANCE);
        this.geolocalizer = new Geolocalizer();
        this.distanceEngine = new GeoDistanceEngine();
    }

    public double getDistance(String firstAddress, String secondAddress) {
        return distanceEngine.evaluate(
                geolocalizer.getCoordinates(parser.parse(normalizer.normalize(firstAddress))),
                geolocalizer.getCoordinates(parser.parse(normalizer.normalize(secondAddress))));
    }
}
