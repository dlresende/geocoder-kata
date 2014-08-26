package fr.xebia.di;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest {
    @Test
    public void testApp() {
        App app = new App();

        double distance = app.getDistance(
                "177 avenue Henri Barbusse, 92700 Colombes",
                "156 boulevard Haussmann, Paris");

        assertThat(distance).isEqualTo(14_300);
    }
}
