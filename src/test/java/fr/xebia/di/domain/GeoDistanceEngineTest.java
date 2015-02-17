package fr.xebia.di.domain;

import fr.xebia.di.domain.Coordinate;
import fr.xebia.di.domain.GeoDistanceEngine;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;

public class GeoDistanceEngineTest {
    @Test
    public void should_compute_distance_from_two_coordinates() {
        GeoDistanceEngine geoDistanceEngine = new GeoDistanceEngine();

        double distance = geoDistanceEngine.evaluate(
                new Coordinate(48.8755587, 2.3110176),
                new Coordinate(52.2114033, 5.1826765));

        assertThat(distance).isCloseTo(423_000, offset(1_000d));
    }
}