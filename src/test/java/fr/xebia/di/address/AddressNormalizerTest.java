package fr.xebia.di.address;

import fr.xebia.di.address.postalcode.PostalCodeResolver;
import org.junit.Test;

import static java.util.Locale.FRANCE;
import static org.assertj.core.api.Assertions.assertThat;

public class AddressNormalizerTest {
    @Test
    public void should_uppercase_town() {
        AddressNormalizer normalizer = new AddressNormalizer(FRANCE, new PostalCodeResolver(FRANCE));

        String normalized = normalizer.normalize("177 avenue Henri Barbusse, 92700 Colombes");

        assertThat(normalized).isEqualTo("177 avenue Henri Barbusse, 92700 COLOMBES");
    }

    @Test
    public void should_append_postal_code() {
        AddressNormalizer normalizer = new AddressNormalizer(FRANCE, new PostalCodeResolver(FRANCE));

        String normalized = normalizer.normalize("156 boulevard Haussmann, Paris");

        assertThat(normalized).isEqualTo("156 boulevard Haussmann, 75008 PARIS");
    }
}