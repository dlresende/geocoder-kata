package fr.xebia.di;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

import java.util.Optional;

import static java.util.Optional.empty;

public class GoogleGeocoding {
    private final GeoApiContext context;

    public GoogleGeocoding(String apiKey) {
        context = new GeoApiContext().setApiKey(apiKey);
    }

    public Optional<Coordinate> geocode(String address) {
        GeocodingResult[] results;
        try {
            results = GeocodingApi.geocode(context, address).await();
        } catch (Exception e) {
            return empty();
        }

        if (results.length == 0) {
            return empty();
        }
        
        return Optional.of(new Coordinate(results[0].geometry.location.lat, results[0].geometry.location.lng));
    }
}
