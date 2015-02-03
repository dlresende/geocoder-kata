package fr.xebia.di;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.Offset.offset;

public class AppTest {
    @Test
    public void testApp() {
        App app = new App();

        double distance = app.getDistance(
                "156 Boulevard Haussmann, 75008 Paris, France",
                "Utrechtseweg 49, 1213 TL Hilversum, Pays-Bas");

        assertThat(distance).isCloseTo(423_000, offset(1_000d));
    }
}
