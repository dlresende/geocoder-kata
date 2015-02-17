package fr.xebia.di;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class OpenStreetMapGeocodingIT {
    @Test
    public void testGeocode() {
        Optional<Coordinate> coordinate = new OpenStreetMapGeocoding().search("156 Boulevard Haussmann, 75008 Paris, France");
        
        assertThat(coordinate.isPresent()).isTrue();
        assertThat(coordinate.get()).isEqualTo(new Coordinate(48.875264, 2.3110709));
    }
}