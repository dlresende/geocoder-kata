package fr.xebia.di;

import fr.xebia.di.Coordinate;
import fr.xebia.di.OpenStreetMapGeocoding;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class OpenStreetMapGeocodingIT {
    @Test
    public void testGeocode() {
        Optional<Coordinate> coordinate = new OpenStreetMapGeocoding().geocode("156 Boulevard Haussmann, 75008 Paris, France");
        
        assertThat(coordinate.isPresent()).isTrue();
        assertThat(coordinate.get()).isEqualTo(new Coordinate(48.875264, 2.3110709));
    }
}