package fr.xebia.di;

import fr.xebia.di.address.AddressNormalizer;
import fr.xebia.di.address.AddressParser;
import fr.xebia.di.address.postalcode.PostalCodeResolver;
import fr.xebia.di.geolocalization.GeoDistanceEngine;
import fr.xebia.di.geolocalization.Geolocalizer;

import java.util.Locale;

public class App {
    private final AddressNormalizer normalizer;
    private final AddressParser parser;
    private final Geolocalizer geolocalizer;
    private final GeoDistanceEngine distanceEngine;

    public App() {
        this.normalizer = new AddressNormalizer(Locale.FRANCE, new PostalCodeResolver(Locale.FRANCE));
        this.parser = new AddressParser(Locale.FRANCE);
        this.geolocalizer = new Geolocalizer();
        this.distanceEngine = new GeoDistanceEngine();
    }

    public double getDistance(String firstAddress, String secondAddress) {
        return distanceEngine.evaluate(
                geolocalizer.getCoordinates(normalizer.normalize(parser.parse(firstAddress))),
                geolocalizer.getCoordinates(normalizer.normalize(parser.parse(secondAddress))));
    }
}
