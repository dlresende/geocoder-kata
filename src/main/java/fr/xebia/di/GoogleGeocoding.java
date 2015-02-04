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
        return getGeocodingResults(address)
                .filter(results -> results.length > 0)
                .map(results -> results[0])
                .map(result -> new Coordinate(result.geometry.location.lat, result.geometry.location.lng));
    }

    private Optional<GeocodingResult[]> getGeocodingResults(String address) {
        try {
            return Optional.ofNullable(GeocodingApi.geocode(context, address).await());
        } catch (Exception e) {
            return empty();
        }
    }
}
