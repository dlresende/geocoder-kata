package fr.xebia.di.address;

import fr.xebia.di.address.postalcode.PostalCodeResolver;
import org.junit.Test;

import static fr.xebia.di.test.DiAssertions.assertThat;
import static java.util.Locale.FRANCE;

public class AddressNormalizerTest {
    @Test
    public void should_uppercase_town() {
        AddressNormalizer normalizer = new AddressNormalizer(FRANCE, new PostalCodeResolver(FRANCE));

        Address normalized = normalizer.normalize(new Address(177, "avenue", "Henri Barbusse", "92700", "Colombes"));

        assertThat(normalized).town("COLOMBES");
    }

    @Test
    public void should_append_postal_code() {
        AddressNormalizer normalizer = new AddressNormalizer(FRANCE, new PostalCodeResolver(FRANCE));

        Address normalized = normalizer.normalize(new Address(156, "boulevard", "Haussmann", null, "PARIS"));

        assertThat(normalized).postalCode("75008");
    }
}
