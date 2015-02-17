package fr.xebia.di.actions;

import fr.xebia.di.domain.Coordinate;
import fr.xebia.di.domain.GeoDistanceEngine;
import fr.xebia.di.domain.Geocoder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ProvideSystemProperty;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;
import static org.mockito.BDDMockito.given;

public class ComputeDistanceTest {
    @Rule
    public ProvideSystemProperty initializeGoogleApiKey = new ProvideSystemProperty("google.apiKey", "AIza...");
    
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    
    @Mock
    private Geocoder geocoder;

    @Mock
    private GeoDistanceEngine geoDistanceEngine;

    @InjectMocks
    private ComputeDistance computeDistance;

    @Test
    public void should_compute_distance() {
        Coordinate xebiaFrance = new Coordinate(48.8755587, 2.3110176);
        Coordinate xebiaNetherland = new Coordinate(52.2114033, 5.1826765);
        given(geocoder.geocode("156 Boulevard Haussmann, 75008 Paris, France")).willReturn(Optional.of(xebiaFrance));
        given(geocoder.geocode("Utrechtseweg 49, 1213 TL Hilversum, Pays-Bas")).willReturn(Optional.of(xebiaNetherland));
        given(geoDistanceEngine.evaluate(xebiaFrance, xebiaNetherland)).willReturn(423199d);

        Optional<Double> distance = computeDistance.between(
                "156 Boulevard Haussmann, 75008 Paris, France",
                "Utrechtseweg 49, 1213 TL Hilversum, Pays-Bas");

        assertThat(distance.isPresent()).isTrue();
        assertThat(distance.get()).isEqualTo(423199d);
    }

    @Test
    public void should_not_compute_distance_if_first_address_cannot_be_gecoded() {
        given(geocoder.geocode("not a valid address")).willReturn(Optional.empty());

        Optional<Double> distance = computeDistance.between(
                "not a valid address",
                null);

        assertThat(distance.isPresent()).isFalse();
    }

    @Test
    public void should_not_compute_distance_if_second_address_cannot_be_gecoded() {
        given(geocoder.geocode("156 Boulevard Haussmann, 75008 Paris, France")).willReturn(Optional.of(new Coordinate(48.8755587, 2.3110176)));
        given(geocoder.geocode("not a valid address")).willReturn(Optional.empty());

        Optional<Double> distance = computeDistance.between(
                "156 Boulevard Haussmann, 75008 Paris, France",
                "not a valid address");

        assertThat(distance.isPresent()).isFalse();
    }
}
