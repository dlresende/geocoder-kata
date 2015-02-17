package fr.xebia.di.domain;

import java.util.Optional;

public interface Geocoder {
    Optional<Coordinate> geocode(String address);
}
