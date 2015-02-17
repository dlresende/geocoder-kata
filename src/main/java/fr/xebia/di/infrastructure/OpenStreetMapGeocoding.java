package fr.xebia.di.infrastructure;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import fr.xebia.di.domain.Coordinate;
import fr.xebia.di.domain.Geocoder;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Optional;

import static java.util.Optional.empty;

public class OpenStreetMapGeocoding implements Geocoder {
    private static final String OPEN_STREET_MAP_URL = "http://nominatim.openstreetmap.org/search?format=json&limit=1&q=";
    
    private final OkHttpClient httpClient;
    private final Gson gson;

    public OpenStreetMapGeocoding() {
        httpClient = new OkHttpClient();
        gson = new GsonBuilder().create();
    }
    
    @Override
    public Optional<Coordinate> geocode(String address) {
        String encodedAddress;
        try {
            encodedAddress = URLEncoder.encode(address, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return empty();
        }

        Request request = new Request.Builder().url(OPEN_STREET_MAP_URL + encodedAddress).build();
        
        try {
            Response response = httpClient.newCall(request).execute();
            
            return Optional.ofNullable(gson.fromJson(response.body().string(), OpenStreetMapResult[].class))
                    .filter(openStreetMapResults -> openStreetMapResults.length > 0)
                    .map(openStreetMapResults -> openStreetMapResults[0])
                    .map(OpenStreetMapResult::toCoordinate);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private static class OpenStreetMapResult {
        public final double lat;
        public final double lon;

        public OpenStreetMapResult(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public Coordinate toCoordinate() {
            return new Coordinate(lat, lon);
        }
    }
}
