package fr.xebia.di.address;

import fr.xebia.di.address.postalcode.PostalCodeResolver;
import org.junit.Test;

import java.util.Optional;

import static fr.xebia.di.test.DiAssertions.assertThat;
import static java.util.Locale.FRANCE;

public class AddressNormalizerTest {
    @Test
    public void should_uppercase_town() {
        AddressNormalizer normalizer = new AddressNormalizer(FRANCE, new PostalCodeResolver(FRANCE));

        NormalizedAddress normalized = normalizer.normalize(new Address(177, "avenue", "Henri Barbusse", Optional.of("92700"), "Colombes"));

        assertThat(normalized).town("COLOMBES");
    }

    @Test
    public void should_append_postal_code() {
        AddressNormalizer normalizer = new AddressNormalizer(FRANCE, new PostalCodeResolver(FRANCE));

        NormalizedAddress normalized = normalizer.normalize(new Address(156, "boulevard", "Haussmann", Optional.empty(), "PARIS"));

        assertThat(normalized).postalCode("75008");
    }
}
